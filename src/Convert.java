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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JProgressBar;

public class Convert {
	String path;
	int row;
	BufferedReader b;
	String readLine = "";

	private JFrame frmThong;

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
		System.out.println(path);
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
		
		//tao TextField cho file
		JTextField textFieldfile = new JTextField();
		textFieldfile.setBounds(230, 30, 182, 23);
		frmThong.getContentPane().add(textFieldfile);
		textFieldfile.setColumns(10);
		
		//Browser button
		JButton btnNewButton = new JButton("Browser");
		btnNewButton.setBounds(102,29, 120, 23);
		frmThong.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					textFieldfile.setText(selectedFile.getAbsolutePath());
					path = selectedFile.getAbsolutePath();
					
					try {
						b = new BufferedReader(new FileReader(selectedFile));
			            System.out.println("Reading file using Buffered Reader");			   
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		//Input file text
		JLabel lblFile = new JLabel("Input file:");
		lblFile.setBounds(22, 33, 70, 14);
		frmThong.getContentPane().add(lblFile);
		
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
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String[] broken_text = null;
					float open_chinh = 0, high_chinh = 0, low_chinh = 0, close_chinh;
					String date_chinh = null, time_chinh = null;
					PrintWriter writer = new PrintWriter("converted.txt");
					int count = 0;
					while ((readLine = b.readLine()) != null) {
						broken_text = readLine.split(",");
					    String date_phu = broken_text[0];
					    String[] time_phu = broken_text[1].split(":");
					    String hour_phu = time_phu[0];
					    int min_phu = Integer.valueOf(time_phu[1]);
					    float open_phu = Float.valueOf(broken_text[2]);
					    float high_phu = Float.valueOf(broken_text[3]);
					    float low_phu  = Float.valueOf(broken_text[4]);
					    float close_phu = Float.valueOf(broken_text[5]); 
					   
					    if(min_phu % 5 == 0 ) {
					    	count += 1;
					    	date_chinh = date_phu;
					    	time_chinh = broken_text[1];
					    	open_chinh = open_phu;
					    	high_chinh = high_phu;
					    	low_chinh = low_phu;					    	
					    }
					    if(count > 1) {
					    	if(high_phu > high_chinh) {
					    		high_chinh=high_phu;
					    	}
					    	if(low_phu < low_chinh) {
								low_chinh = low_phu;
							}
					    	count += 1;
					    }
						if(count==5) {
							System.out.println(min_phu);
							close_chinh = close_phu;
							writer.println(date_chinh+","+time_chinh+","+open_chinh+","+high_chinh+","+low_chinh+","+close_chinh);
							count = 0;
						}
					}
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(102, 170, 232, 14);
		frmThong.getContentPane().add(progressBar);
	}
	
}

