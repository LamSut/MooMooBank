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
import javax.swing.AbstractListModel;

public class ShowHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField Receiver;
	private static JTextField Amount;

	/**
	 * Launch the application.
	 */
	public static void main(String idacc) {
		Actions mysql = new Actions();
		mysql.Connect();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowHistory frame = new ShowHistory(idacc);
					frame.setVisible(true);
					
					JButton CloseButton = new JButton("CLOSE");
					CloseButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
						}
					});
					CloseButton.setForeground(new Color(245, 222, 179));
					CloseButton.setFont(new Font("Tahoma", Font.BOLD, 16));
					CloseButton.setBackground(new Color(128, 0, 0));
					CloseButton.setBounds(140, 330, 100, 40);
					contentPane.add(CloseButton);
					

					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	/**
	 * Create the frame.
	 */
	public ShowHistory() {
		Actions mysql = new Actions();
		mysql.Connect();
		String values [] = mysql.getHistory("9907543817");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(110, 60, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("HISTORY");
		Title.setFont(new Font("Tahoma", Font.BOLD, 22));
		Title.setForeground(new Color(245, 222, 179));
		Title.setBounds(135, 22, 200, 45);
		contentPane.add(Title);
		
		JButton CloseButton = new JButton("CLOSE");
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CloseButton.setForeground(new Color(245, 222, 179));
		CloseButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		CloseButton.setBackground(new Color(128, 0, 0));
		CloseButton.setBounds(147, 330, 100, 40);
		contentPane.add(CloseButton);
	
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setFont(new Font("Tahoma", Font.BOLD, 14));
		list.setBounds(50, 78, 285, 228);
		contentPane.add(list);
	}
	
	public ShowHistory(String idacc) {
		Actions mysql = new Actions();
		mysql.Connect();
		String values [] = mysql.getHistory(idacc);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(110, 60, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("HISTORY");
		Title.setFont(new Font("Tahoma", Font.BOLD, 22));
		Title.setForeground(new Color(245, 222, 179));
		Title.setBounds(140, 22, 200, 45);
		contentPane.add(Title);
		
	
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setFont(new Font("Tahoma", Font.BOLD, 14));
		list.setBounds(25, 78, 325, 228);
		contentPane.add(list);
	}
}
