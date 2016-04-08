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
	private String input;
	private String parameter1 = "";
	private String parameter2 = "";
	
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
				
				System.out.println("Input: ");
				input = txtInput.getText();						// scans input 
				System.out.println("Your input is: " + input);	// for checking
				
				String[] line = input.split(" ");				// splits the whole line per word
				
				boolean error = false;
				switch (line[0].toLowerCase()) 					// command (first word of the line)
				{
					case "copy": 
					case "ren":
					case "move":
						boolean quote = false;					// checker if there is a " at the end of the word
						int i = 1;			
						parameter1 = "";
						parameter2 = "";
						if(line[i].charAt(0) == '\"')
						{
							for (i = 1; i < line.length; i++)
							{
								if (line[i].charAt(line[i].length()-1) == '\"')		// checks if current word has " at the end of the word 
								{
									quote = true;									
									break;
								}
							}
							if (quote == true)										// if word has " at the end
							{
								for (int a = 1; a <= i; a++)
									parameter1 = parameter1 + " " + line[a];		// concatenates 
								parameter1 = parameter1.substring(2, parameter1.length()-1);
							}
						}
						else											// if there is no " "
							parameter1 = line[i];
						
						i++;											// counter for next word
						System.out.println("I = " + i);
						
						try
						{
							quote = false;								// resets
							while (line[i] == " ")
								i++;
							
							for (int x = 0; x <= i; x++)
								System.out.println(x + " word = " + line[x] + "*");
							
							if(line[i].charAt(0) == '\"')				// if word starts with "
							{
								int b = i;
								for (b = i; b < line.length; b++)		// checks if current word (after the first parameter) has " at the end
								{
									if (line[b].charAt(line[b].length()-1) == '\"')
									{
										quote = true;
										break;
									}
								}
								if (quote == true)
								{
									for (int a = i; a <= b; a++)
										parameter2 = parameter2 + " " + line[a];
									parameter2 = parameter2.substring(2, parameter2.length()-1);
								}
							}
							else
								parameter2 = line[i];
							
							if (line[i++].equals(null) == false)
							{
								error = true;
								System.out.println("Sobra parameters!");
							}
							
							System.out.println("First parameter is: *" + parameter1);
							System.out.println("Second parameter is: *" + parameter2);
						}
						catch(ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e)
						{
							System.out.println("Error!");
							error = true;
						}
						
						if (error == false)
						{
							File s = new File(parameter1);
							File d = new File(parameter2);
							
							switch (line[0].toLowerCase())
							{
								case "copy":
								try {
									copyFile(s, d);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									//e1.printStackTrace();
								}
									break;
								case "move":
								try {
									moveFile(s, d);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									//e.printStackTrace();
								}
									break;
								case "ren":
									// rename function
									break;
							}
						}
						else
						{
							// errorrrrr
						}
						break;
						
					case "del":
						quote = false;					// checker if there is a " at the end of the word
						i = 1;			
						parameter1 = "";
						parameter2 = "";
						if(line[i].charAt(0) == '\"')
						{
							for (i = 1; i < line.length; i++)
							{
								if (line[i].charAt(line[i].length()-1) == '\"')		// checks if current word has " at the end of the word 
								{
									quote = true;									
									break;
								}
							}
							if (quote == true)										// if word has " at the end
							{
								for (int a = 1; a <= i; a++)
									parameter1 = parameter1 + " " + line[a];		// concatenates 
								parameter1 = parameter1.substring(2, parameter1.length()-1);
							}
						}
						else											// if there is no " "
							parameter1 = line[i];
						
						System.out.println("Parameter is: " + parameter1);
						
						try
						{
							i++;
							if (line[i].equals(null) == false)
							{
								error = true;
								System.out.println("Too many parameters!");
							}
								
						}
						catch (ArrayIndexOutOfBoundsException e)
						{
							
						}
						
						if (error == false)
						{
							File s = new File(parameter1);
							try {
								deleteFile(s);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
						}
						break;
						
					default:
						System.out.println("No such instruction!");
				}
				
				txtInput.setText("");
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
