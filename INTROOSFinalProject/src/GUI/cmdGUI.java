package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.AbstractAction;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;

public class cmdGUI{
	private JFrame frameMain = new JFrame("INTROOS");
	private JPanel panelMain = new JPanel();
	private JScrollPane scrlMain = new JScrollPane();
	private JTextPane txtCMD = new JTextPane();
	private JTextField txtInput = new JTextField();
	
	private ArrayList<String> inputs = new ArrayList<String>();
	
	public cmdGUI(){
		panelMain.setLayout(new MigLayout("", "[927.00,grow]", "[435.00,grow][35px:35px]"));
		
		panelMain.add(scrlMain, "cell 0 0,grow");
		
		txtCMD.setEditable(false);
		txtCMD.setForeground(Color.WHITE);
		txtCMD.setBackground(Color.BLACK);
		txtCMD.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		scrlMain.setViewportView(txtCMD);
		
		txtInput.setForeground(Color.WHITE);
		txtInput.setBackground(Color.BLACK);
		txtInput.setCaretColor(Color.WHITE);
		txtInput.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		panelMain.add(txtInput, "cell 0 1,grow");
		
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.getContentPane().add(panelMain);
		frameMain.setVisible(true);
		frameMain.pack();
		
		txtInput.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtCMD.setText(txtCMD.getText().concat(txtInput.getText() + "\n"));
				txtInput.setText("");
				
				int index = 0;
				
				inputs.add(txtInput.getText());
				String[] line = inputs.get(index).split(" ");
				String firstWord = line[0];
				
				File s;
				File d;
				// Added try-catch because eclipse said so
				if (errorChecking(line) == false)
				{
					// and i think iincrement mo dapat si "index"? 
					switch (firstWord.toLowerCase())
					{
						case "cd":
							
						case "cd..":
						
						case "dir":
						
						case "copy":
							s = new File(line[1]);
							d = new File (line[2]);
							
							try {
								copyFile(s, d);
							} catch (IOException e) {
								System.out.println("Error Copy");
							}
							break;
						case "move":
							s = new File(line[1]);
							d = new File (line[2]);
							
							try {
								moveFile(s, d);
							} catch (IOException e) {
								System.out.println("Error Move");
							}
							break;
						case "ren":
							// rename function here
							break;
						case "del": 
							s = new File(line[1]);
							
							try {
								deleteFile(s);
							} catch (IOException e) {
								System.out.println("Error Delete");
							}
							break;
					}
				}
				else
				{
					txtCMD.setText(txtCMD.getText().concat("\nError input!"));
					//di yata need ito kasi you're making a new "line" everytime Enter button
					//is pressed 
					//line = "";
				}
				
				
				
			}
		});
	}
	
	private static void copyFile( File from, File to ) throws IOException {
	    Files.copy( from.toPath(), to.toPath() );
	}
	
	private static void deleteFile( File from ) throws IOException {
	    Files.delete( from.toPath() );
	}
	
	private static void moveFile( File from, File to ) throws IOException {
	    copyFile(from, to);
	    deleteFile(from);
	}
	
	private static void showContents( File dir ) throws IOException {
		 File[] filesList = dir.listFiles();
	        for(File f : filesList){
	            if(f.isDirectory())
	                showContents(f);
	            if(f.isFile()){
	                System.out.println(f.getName());
	                System.out.println(getSize(f));
	                System.out.println(getDateCreated(f));
	                System.out.println(getDateModified(f));
	                System.out.println(getUser(f));
	            }
	        }
	}
	
	private static String getSize(File f){
		String size = "";
		
		if(f.exists()){
			
			long bytes = f.length();
			long kilobytes = (bytes / 1024);
			long megabytes = (kilobytes / 1024);
			long gigabytes = (megabytes / 1024);
			long terabytes = (gigabytes / 1024);
			long petabytes = (terabytes / 1024);
			long exabytes = (petabytes / 1024);
			long zettabytes = (exabytes / 1024);
			long yottabytes = (zettabytes / 1024);
			
			if(bytes < 1000){
				size = bytes + " bytes";
			}else if(kilobytes < 1000){
				size = kilobytes + " KB";
			}else if(megabytes < 1000){
				size = megabytes + "MB";
			}else if(megabytes < 1000){
				size = gigabytes + "GB";
			}else if(gigabytes < 1000){
				size = terabytes + "TB";
			}	
		}

		return size;
	}
	
	private static String getDateCreated( File f ) throws IOException {
		Path path = f.toPath();
		BasicFileAttributes attr;
		String date = "";
		 
	    attr = Files.readAttributes(path, BasicFileAttributes.class);
	    date = "Date Created: " + attr.creationTime().toString().substring(0, 10);

	    return date;
	}
	
	private static String getDateModified( File f ) throws IOException {
		 Path path = f.toPath();
		 BasicFileAttributes attr;
		 String date = "";
		 
	    attr = Files.readAttributes(path, BasicFileAttributes.class);
	    date = "Date Modified: " + attr.lastModifiedTime().toString().substring(0, 10);

	    return date;
	}
	
	private static String getUser( File f ) throws IOException {
		String name[];
		UserPrincipal owner = Files.getOwner(f.toPath());
		
		name = owner.getName().split("-");
		name[0] = "User: " + name[0];
		
		return name[0];
	}
	
	public boolean errorChecking(String[] line){
		//added this variable
		boolean error = false;
		switch(line[0].toUpperCase()){
			case "CD":
				if (line.length == 2)
					error = false;
				else error = true;
				break;
			case "DIR":
				if (line.length == 2)
					error = false;
				else error = true;
				break;
			case "COPY":
				if (line.length == 3)
					error = false;
				else error = true;
				break;
			case "MOVE":
				if (line.length == 3)
					error = false;
				else error = true;
				break;
			case "DEL":
				if (line.length == 2)
					error = false;
				else error = true;
				break;
			case "REN":
				if (line.length == 3)
					error = false;
				else error = true;
				break;
		}
		return error;
	}
}
