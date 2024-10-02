package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class SignUpPINAsk extends JFrame {

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
					SignUpPINAsk frame = new SignUpPINAsk();
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
	public SignUpPINAsk() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 230, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PIN contains 6 digits");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 11, 159, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblForExample = new JLabel("For example: 666666");
		lblForExample.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblForExample.setBounds(20, 39, 183, 51);
		contentPane.add(lblForExample);
	}
	
}
