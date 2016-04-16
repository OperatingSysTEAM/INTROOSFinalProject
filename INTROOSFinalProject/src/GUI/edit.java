package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

/**
 * Class used for editing a file
 * @author Patricia
 *
 */
public class edit {
	private JFrame editframe = new JFrame("Edit");
//	cmdGUI cmdgui = new cmdGUI();
	
	/**
	 * Constructor of the edit class
	 * @param file
	 */
	public edit(String file){
//        cmdgui.getFrame().setEnabled(false);
//        cmdgui.getFrame().setVisible(false);
		editframe.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][]"));
		editframe.setSize(500, 500);
		
		JTextArea textArea = new JTextArea();
		editframe.getContentPane().add(textArea, "cell 0 0,grow");
		
		JButton btnSave = new JButton("Save");
		editframe.getContentPane().add(btnSave, "cell 0 1,grow");
		
		editframe.setVisible(true);
		
		BufferedReader br = null;
        String Line = null;
        try{
        	br = new BufferedReader(new FileReader(file));

	        while ((Line = br.readLine()) != null) {
	            System.out.println(Line);
	            textArea.setText(textArea.getText().concat(Line + "\n"));
	        }
        }catch(IOException ex){
        	
        }
        
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
                    System.out.println("edit");
                    String content = textArea.getText();
                    content = content.replaceAll("(?!\\r)\\n", "\r\n");
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Save all changes?", "Warning", dialogButton);
                    if (dialogResult == 0) {
                        System.out.println("Yes");
                        try {
                            final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                            bw.write(content);
                            bw.close();
                            editframe.dispose();
//                            cmdgui.getFrame().setEnabled(true);
                        } catch (IOException ex) {
                           
                        }
                    } else {
                        System.out.println("No");
//                        cmdgui.getFrame().setEnabled(true);
                        editframe.dispose();
                    }
				}
			}
		});
		
		
	}
}
