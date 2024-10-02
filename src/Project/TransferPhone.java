package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TransferPhone extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField Receiver;

	/**
	 * Launch the application.
	 */
	public static void main(String usrsend, long bal) {
		Actions mysql = new Actions();
		mysql.Connect();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferPhone frame = new TransferPhone();
					frame.setVisible(true);
					
					JLabel IDSend = new JLabel("Source: " + usrsend);
					IDSend.setForeground(new Color(245, 222, 179));
					IDSend.setFont(new Font("Tahoma", Font.BOLD, 14));
					IDSend.setBounds(30, 11, 280, 45);
					contentPane.add(IDSend);
					
					JLabel Balance = new JLabel("Balance: " + mysql.getBalance(usrsend) + " VND");
					Balance.setForeground(new Color(245, 222, 179));
					Balance.setFont(new Font("Tahoma", Font.BOLD, 14));
					Balance.setBounds(30, 39, 280, 45);
					contentPane.add(Balance);
					
					Receiver = new JTextField();
					Receiver.setFont(new Font("Tahoma", Font.BOLD, 14));
					Receiver.setBounds(160, 160, 180, 20);
					contentPane.add(Receiver);
					Receiver.setColumns(10);
					
					JComboBox comboBox = new JComboBox();
					comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
					comboBox.setModel(new DefaultComboBoxModel(new String[] {"30,000", "50,000", "100,000", "200,000", "300,000", "500,000"}));
					comboBox.setBounds(160, 204, 150, 22);
					contentPane.add(comboBox);
					
					JButton OK = new JButton("Okay");
					OK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String usrgettemp = Receiver.getText();
							String money = comboBox.getSelectedItem().toString();
							String usrget = mysql.getPhonefromTel(usrgettemp);
							if(usrget.compareTo("1")==0 || usrget.compareTo(usrsend)==0) {
								FailAcc failacc = new FailAcc();
								failacc.setVisible(true);
							}
							else if(money.compareTo("")!=0) {
								long amount = Long.valueOf(money.replaceAll(",",""));
								if(amount>bal) {
									FailMoney failmoney = new FailMoney();
									failmoney.setVisible(true);
								}
								else {
									frame.callPIN(usrsend, usrget, money);
									frame.dispose();
								}
							}
						}
					});
					OK.setBackground(new Color(47, 79, 79));
					OK.setForeground(new Color(245, 222, 179));
					OK.setFont(new Font("Tahoma", Font.BOLD, 14));
					OK.setBounds(67, 404, 120, 40);
					contentPane.add(OK);
					
					JButton Cancel = new JButton("Cancel");
					Cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							frame.callApp(usrsend, mysql.getName(usrsend));
						}
					});
					Cancel.setForeground(new Color(245, 222, 179));
					Cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
					Cancel.setBackground(new Color(128, 0, 0));
					Cancel.setBounds(250, 404, 120, 40);
					contentPane.add(Cancel);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void callApp(String usracc, String nameacc) {
		Application.main(usracc, nameacc);
	}
	
	public void callPIN(String usrsend, String usrget, String money) {
		TransferPhonePIN.main(usrsend, usrget, money);
	}
	
	/**
	 * Create the frame.
	 */
	public TransferPhone() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(110, 60, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("PHONE RECHARGE");
		Title.setFont(new Font("Tahoma", Font.BOLD, 24));
		Title.setForeground(new Color(245, 222, 179));
		Title.setBounds(100, 95, 307, 45);
		contentPane.add(Title);
		
		JLabel ReceiverLabel = new JLabel("Receiver: ");
		ReceiverLabel.setForeground(new Color(245, 222, 179));
		ReceiverLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ReceiverLabel.setBounds(80, 148, 91, 45);
		contentPane.add(ReceiverLabel);
		
		JLabel AmountLabel = new JLabel("Amount: ");
		AmountLabel.setForeground(new Color(245, 222, 179));
		AmountLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		AmountLabel.setBounds(80, 192, 91, 45);
		contentPane.add(AmountLabel);	
		
		//
		//

	}
}
