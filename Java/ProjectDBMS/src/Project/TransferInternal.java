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

public class TransferInternal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField Receiver;
	private static JTextField Amount;

	/**
	 * Launch the application.
	 */
	public static void main(String usrsend, long bal) {
		Actions mysql = new Actions();
		mysql.Connect();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferInternal frame = new TransferInternal();
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
					
					NumberFormat longFormat = NumberFormat.getIntegerInstance();
					NumberFormatter numberFormatter = new NumberFormatter(longFormat);
					numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
					numberFormatter.setAllowsInvalid(false); //this is the key!!
					numberFormatter.setMinimum(0l); //Optional
					JFormattedTextField Amount = new JFormattedTextField(numberFormatter);
					Amount.setFont(new Font("Tahoma", Font.BOLD, 14));
					Amount.setColumns(10);
					Amount.setBounds(160, 204, 180, 20);
					contentPane.add(Amount);
					
					JButton OK = new JButton("Okay");
					OK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String usrgettemp = Receiver.getText();
							String money = Amount.getText();
							String usrget = mysql.getIDacc(usrgettemp);
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
		TransferInternalPIN.main(usrsend, usrget, money);
	}
	
	/**
	 * Create the frame.
	 */
	public TransferInternal() {
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
		Title.setBounds(80, 95, 307, 45);
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
