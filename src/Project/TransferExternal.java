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
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TransferExternal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
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
					TransferExternal frame = new TransferExternal();
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
					
					JComboBox comboBox = new JComboBox();
					comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
					comboBox.setModel(new DefaultComboBoxModel(new String[] {"Vietcombank", "TP Bank", "Agribank", "Sacombank", "BIDV"}));
					comboBox.setBounds(196, 169, 174, 25);
					contentPane.add(comboBox);
					

					
					JButton OK = new JButton("Okay");
					OK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String chosen = comboBox.getSelectedItem().toString();
							frame.callNext(usrsend,bal,chosen);
							frame.dispose();
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
	
	public void callNext(String usrsend, long bal, String bankget) {
		TransferExternalNext.main(usrsend, bal, bankget);
	}
	
//	public void callPIN(String usrsend, String usrget, String money) {
//		TransferExternalPIN.main(usrsend, usrget, money);
//	}
	
	/**
	 * Create the frame.
	 */
	public TransferExternal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(110, 60, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("EXTERNAL TRANSFER");
		Title.setFont(new Font("Tahoma", Font.BOLD, 28));
		Title.setForeground(new Color(245, 222, 179));
		Title.setBounds(67, 95, 344, 45);
		contentPane.add(Title);
		
		JLabel BankLabel = new JLabel("Choose Bank: ");
		BankLabel.setForeground(new Color(245, 222, 179));
		BankLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		BankLabel.setBounds(67, 159, 120, 45);
		contentPane.add(BankLabel);
		
		//
		//
	}
}
