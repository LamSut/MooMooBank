package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PINyes extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String idsend,String idget, String bankget, long bal) {
		Actions mysql = new Actions();
		mysql.Connect();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PINyes frame = new PINyes();
					frame.setVisible(true);
					
					JLabel IDSend = new JLabel("From: " + idsend);
					IDSend.setFont(new Font("Tahoma", Font.BOLD, 16));
					IDSend.setBounds(25, 80, 205, 30);
					contentPane.add(IDSend);
					
					JLabel IDGet = new JLabel("To: " + idget);
					IDGet.setFont(new Font("Tahoma", Font.BOLD, 16));
					IDGet.setBounds(25, 125, 205, 30);
					contentPane.add(IDGet);
					
					JLabel Bank = new JLabel("Bank: " + bankget);
					Bank.setFont(new Font("Tahoma", Font.BOLD, 14));
					Bank.setBounds(25, 170, 322, 30);
					contentPane.add(Bank);
					
					JLabel Remain = new JLabel("Balance Remain: " + bal + " VND");
					Remain.setFont(new Font("Tahoma", Font.BOLD, 14));
					Remain.setBounds(25, 215, 322, 30);
					contentPane.add(Remain);
					
					JButton Back = new JButton("Back To Home");
					Back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							frame.callApp(idsend, mysql.getName(idsend));
						}
					});
					Back.setBackground(new Color(128, 0, 0));
					Back.setForeground(new Color(245, 245, 220));
					Back.setFont(new Font("Tahoma", Font.BOLD, 14));
					Back.setBounds(146, 400, 150, 50);
					contentPane.add(Back);
									
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void callApp(String usracc, String nameacc) {
		Application.main(usracc, nameacc);
	}
	
	public PINyes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Congrats = new JLabel("Successful Transaction!");
		Congrats.setHorizontalAlignment(SwingConstants.CENTER);
		Congrats.setFont(new Font("Tahoma", Font.BOLD, 22));
		Congrats.setBounds(55, 11, 322, 54);
		contentPane.add(Congrats);
		
		//

	}
}
