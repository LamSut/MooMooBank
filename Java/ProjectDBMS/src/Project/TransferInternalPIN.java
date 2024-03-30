package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TransferInternalPIN extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JPasswordField PIN;

	/**
	 * Launch the application.
	 */
	public static void main(String usrsend, String usrget, String money) {
		Actions mysql = new Actions();
		mysql.Connect();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferInternalPIN frame = new TransferInternalPIN();
					frame.setVisible(true);
					
					JLabel ReceiveID = new JLabel(usrget);
					ReceiveID.setForeground(new Color(245, 222, 179));
					ReceiveID.setFont(new Font("Tahoma", Font.BOLD, 14));
					ReceiveID.setBounds(133, 80, 268, 40);
					contentPane.add(ReceiveID);
					
					JLabel Name = new JLabel(mysql.getName(usrget));
					Name.setForeground(new Color(245, 222, 179));
					Name.setFont(new Font("Tahoma", Font.BOLD, 14));
					Name.setBounds(101, 120, 300, 40);
					contentPane.add(Name);
					
					JLabel Amount = new JLabel(money + " VND");
					Amount.setForeground(new Color(245, 222, 179));
					Amount.setFont(new Font("Tahoma", Font.BOLD, 14));
					Amount.setBounds(120, 160, 200, 40);
					contentPane.add(Amount);
					
					PIN = new JPasswordField();
					PIN.setFont(new Font("Tahoma", Font.PLAIN, 16));
					PIN.setBounds(101, 207, 91, 30);
					contentPane.add(PIN);
					
					String correct = mysql.getPIN(usrsend);
					
					JButton OK = new JButton("Okay");
					OK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String getPIN = String.valueOf(PIN.getPassword());
							if(getPIN.compareTo(correct)==0) {
								long remain = mysql.getBalance(usrsend);
								long amount = Long.valueOf(money.replaceAll(",",""));
								remain = remain - amount;
								mysql.InTransfer(usrsend, usrget, amount);

								frame.dispose();
								frame.callPINyes(usrsend,usrget,"MooMooBank", mysql.getBalance(usrsend));
							}
							else {
								PINno fail = new PINno();
								fail.setVisible(true);
							}
						}
					});
					OK.setBackground(new Color(47, 79, 79));
					OK.setForeground(new Color(245, 222, 179));
					OK.setFont(new Font("Tahoma", Font.BOLD, 16));
					OK.setBounds(72, 390, 120, 40);
					contentPane.add(OK);
					
					JButton Cancel = new JButton("Cancel");
					Cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							frame.callInTransfer(usrsend, mysql.getBalance(usrsend));
						}
					});
					Cancel.setForeground(new Color(245, 222, 179));
					Cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
					Cancel.setBackground(new Color(128, 0, 0));
					Cancel.setBounds(254, 390, 120, 40);
					contentPane.add(Cancel);
					
					
					
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
	
	public void callInTransfer(String usr, long bal) {
		TransferInternal.main(usr, bal);
	}
	
	public void callPINyes(String idsend,String idget, String bankget, long bal) {
		PINyes.main(idsend,idget,bankget, bal);
	}
	
	public TransferInternalPIN() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(110, 60, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("INTERNAL TRANSFER");
		Title.setFont(new Font("Tahoma", Font.BOLD, 24));
		Title.setForeground(new Color(245, 222, 179));
		Title.setBounds(85, 30, 323, 45);
		contentPane.add(Title);
		
		JLabel PINLabel = new JLabel("PIN: ");
		PINLabel.setForeground(new Color(245, 222, 179));
		PINLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		PINLabel.setBounds(40, 200, 64, 45);
		contentPane.add(PINLabel);
		
		JLabel AmountLabel = new JLabel("Amount: ");
		AmountLabel.setForeground(new Color(245, 222, 179));
		AmountLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		AmountLabel.setBounds(40, 160, 91, 40);
		contentPane.add(AmountLabel);
		
		JLabel ReceiveIDLabel = new JLabel("Receive ID:  ");
		ReceiveIDLabel.setForeground(new Color(245, 222, 179));
		ReceiveIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ReceiveIDLabel.setBounds(40, 80, 98, 40);
		contentPane.add(ReceiveIDLabel);
		
		JLabel NameLabel = new JLabel("Name: ");
		NameLabel.setForeground(new Color(245, 222, 179));
		NameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		NameLabel.setBounds(40, 120, 91, 40);
		contentPane.add(NameLabel);
		
		//
		//
		
	}
}
