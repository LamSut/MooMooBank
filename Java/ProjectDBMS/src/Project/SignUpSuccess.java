package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class SignUpSuccess extends JFrame {

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
					SignUpSuccess frame = new SignUpSuccess();
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
	public SignUpSuccess() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Congratulation! Signed up successfully!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 21, 322, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblYourIdBanki = new JLabel("Your ID Account Number is: ");
		lblYourIdBanki.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYourIdBanki.setBounds(20, 55, 334, 51);
		contentPane.add(lblYourIdBanki);
	}
	
	public SignUpSuccess(String id) {
		idacc=new String(id);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Congratulation! Signed up successfully!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 21, 322, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblYourIdBanki = new JLabel("Your ID Account Number is: "+idacc);
		lblYourIdBanki.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYourIdBanki.setBounds(20, 55, 334, 51);
		contentPane.add(lblYourIdBanki);
	}
}
