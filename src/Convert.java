import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.List;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

public class Convert {

	private JFrame frmThong;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Convert window = new Convert();
					window.frmThong.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Convert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThong = new JFrame();
		frmThong.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmThong.setTitle("FX Convert");
		frmThong.setBounds(100, 100, 450, 300);
		frmThong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThong.getContentPane().setLayout(null);
		
		
		
		//Browser button
		JButton btnNewButton = new JButton("Browser");
		btnNewButton.setBounds(304, 29, 120, 23);
		frmThong.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new docfile());
		btnNewButton.doClick();
		
		//Input file text
		JLabel lblFile = new JLabel("Input file:");
		lblFile.setBounds(22, 33, 70, 14);
		frmThong.getContentPane().add(lblFile);
		
		textField = new JTextField();
		textField.setBounds(102, 30, 182, 20);
		frmThong.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTimeframe = new JLabel("TimeFrame:");
		lblTimeframe.setBounds(22, 90, 70, 14);
		frmThong.getContentPane().add(lblTimeframe);
		
		Choice choice = new Choice();
		choice.add("M5");
		choice.add("M15");
		choice.add("H1");
		choice.add("H4");
		choice.add("D1");
		
		choice.setBounds(103, 90, 139, 20);
		frmThong.getContentPane().add(choice);
		
		JTextArea textArea = new JTextArea("You selected " + choice.getSelectedItem() + " timeframe. Push convert button to start...");
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textArea.setEditable(false);
		textArea.setBounds(55, 137, 326, 22);
		frmThong.getContentPane().add(textArea);
		
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int row;
				textArea.setText("You selected " + choice.getSelectedItem() + " timeframe. Push convert button to start...");
				if(choice.getSelectedItem()=="M5") {row=5;}
				if(choice.getSelectedItem()=="M15") {row=15;}
				if(choice.getSelectedItem()=="H1") {row=60;}
				if(choice.getSelectedItem()=="H4") {row=240;}
				if(choice.getSelectedItem()=="D1") {row=1440;}
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("Convert");
		btnNewButton_1.setBounds(179, 203, 89, 23);
		frmThong.getContentPane().add(btnNewButton_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(102, 170, 232, 14);
		frmThong.getContentPane().add(progressBar);
	}
}

class docfile implements ActionListener {
	//pop-up file path	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
		} else {
			System.out.println("No Selection ");
		}

	}
}