package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class SignUpPassAsk extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String idacc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPassAsk frame = new SignUpPassAsk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUpPassAsk() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password contain at least 8 characters which including");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 367, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblLetter = new JLabel("at least 1 letter, 1 digit and 1 special character");
		lblLetter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLetter.setBounds(10, 39, 367, 51);
		contentPane.add(lblLetter);
	}
}
