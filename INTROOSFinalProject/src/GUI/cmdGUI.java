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

public class cmdGUI{
	private JFrame frameMain = new JFrame("INTROOS");
	private JPanel panelMain = new JPanel();
	private JScrollPane scrlMain = new JScrollPane();
	private JTextPane txtCMD = new JTextPane();
	private JTextField txtInput = new JTextField();
	
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
				// TODO Auto-generated method stub
				// dito boss pat
				txtCMD.setText(txtCMD.getText().concat(txtInput.getText() + "\n"));
				txtInput.setText("");
			}
		});
	}
}
