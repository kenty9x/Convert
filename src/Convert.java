import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		frmThong.setTitle("Thong");
		frmThong.setBounds(100, 100, 450, 300);
		frmThong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThong.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Browser");
		btnNewButton.setBounds(269, 11, 120, 23);
		frmThong.getContentPane().add(btnNewButton);
		
		JLabel lblFile = new JLabel("File:");
		lblFile.setBounds(22, 15, 46, 14);
		frmThong.getContentPane().add(lblFile);
		
		textField = new JTextField();
		textField.setBounds(55, 12, 187, 20);
		frmThong.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
