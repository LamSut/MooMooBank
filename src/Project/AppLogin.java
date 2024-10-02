package Project;

import java.sql.*;
import java.awt.EventQueue;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class AppLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JPasswordField Password;
	private static JTextField Username;

	/**
	 * Launch the AppLogin.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Actions mysql = new Actions();
				mysql.Connect();
				System.out.println("Starting Project...");
				
				try {	
					AppLogin frame = new AppLogin();
					frame.setVisible(true);
					

					//TYPE IN IDACC
					Username = new JTextField();
					Username.setFont(new Font("Tahoma", Font.BOLD, 14));
					Username.setBounds(150, 110, 220, 25);
					contentPane.add(Username);
					Username.setColumns(10);
					
					//TYPE IN PASSWORD
					Password = new JPasswordField();
					Password.setFont(new Font("Tahoma", Font.BOLD, 14));
					Password.setBounds(150, 145, 220, 25);
					contentPane.add(Password);
					
					//SIGN IN BUTTON
					JButton SignIn = new JButton("Login");
					SignIn.setForeground(Color.WHITE);
					SignIn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String usr = Username.getText();
							String pass = String.valueOf(Password.getPassword());
							String correct = new String("");
							correct = mysql.getPass(usr);
							if(pass.compareTo(correct)==0 && pass.compareTo("1")!=0)
							{
								String name = mysql.getName(usr);
								frame.callApp(usr, name);
								frame.dispose();
							}
							else if(usr.compareTo("")!=0 && pass.compareTo("")!=0) {
								if(correct.compareTo("1")==0) {
									LogNoAcc noacc = new LogNoAcc();
									noacc.setVisible(true);
								}
								else { 
									LogFailed fail = new LogFailed();
									fail.setVisible(true);
								}
							}
						}
					});
					SignIn.setFont(new Font("Tahoma", Font.BOLD, 14));
					SignIn.setBackground(Color.BLACK);
					SignIn.setHorizontalAlignment(SwingConstants.CENTER);
					SignIn.setBounds(155, 185, 80, 30);
					contentPane.add(SignIn);

					
					//SIGN UP
					JButton SignUp = new JButton("Sign Up");
					SignUp.setForeground(Color.WHITE);
					SignUp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.callSignUp();
						}
					});
					SignUp.setFont(new Font("Tahoma", Font.BOLD, 14));
					SignUp.setBackground(new Color(110, 50, 50));
					SignUp.setHorizontalAlignment(SwingConstants.CENTER);
					SignUp.setBounds(275, 185, 90, 30);
					contentPane.add(SignUp);
					
					
					JButton Exit = new JButton();
					Exit.setForeground(Color.WHITE);
					Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
						}
					});
					Exit.setFont(new Font("Tahoma", Font.BOLD, 10));
					Exit.setBackground(Color.white);
					Exit.setBounds(359, 400, 55, 50);
					Exit.setIcon(new ImageIcon("F:\\Cong Viec\\Java\\Ionic-Ionicons-Exit-outline.512.png"));
					contentPane.add(Exit);
					
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void callApp(String usracc, String nameacc) {
		Application.main(usracc, nameacc);
	}
	
	public void callSignUp() {
		SignUp.main(null);
	}
	
	public AppLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		JLabel Welcome = new JLabel("Welcome to MooMooBank! ", SwingConstants.LEADING);
		Welcome.setBounds(46, 11, 396, 34);
		Welcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		contentPane.add(Welcome);
		
		JLabel PleaseEnter = new JLabel("Please enter your information: ", SwingConstants.LEADING);
		PleaseEnter.setBounds(52, 60, 404, 25);
		PleaseEnter.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(PleaseEnter);
		
		JLabel LabelUsername = new JLabel("Account ID:");
		LabelUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		LabelUsername.setBounds(52, 110, 112, 25);
		contentPane.add(LabelUsername);
		
		
		JLabel LabelPassword = new JLabel("Password:");
		LabelPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		LabelPassword.setBounds(52, 145, 122, 25);
		contentPane.add(LabelPassword);
		
		
		JLabel MooPic = new JLabel("New label");
		MooPic.setIcon(new ImageIcon("F:\\Cong Viec\\Java\\moooo.png"));
		MooPic.setBounds(-25, 220, 353, 374);
		contentPane.add(MooPic);

	}
	
	public String getUsername() {
		return Username.getText();
	}
	
	public String getPassword() {
		return String.valueOf(Password.getPassword());
	}
	
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				Actions mysql = new Actions();
//				mysql.Connect();
//				System.out.println("Starting Project...");
//				
//				try {	
//					AppLogin frame = new AppLogin();
//					frame.setVisible(true);
//					
//
//					//TYPE IN IDACC
//					Username = new JTextField();
//					Username.setFont(new Font("Tahoma", Font.BOLD, 14));
//					Username.setBounds(130, 110, 180, 20);
//					contentPane.add(Username);
//					Username.setColumns(10);
//					
//					//TYPE IN PASSWORD
//					Password = new JPasswordField();
//					Password.setFont(new Font("Tahoma", Font.BOLD, 14));
//					Password.setBounds(130, 145, 180, 20);
//					contentPane.add(Password);
//					
//					//SIGN IN BUTTON
//					JButton SignIn = new JButton("Login");
//					SignIn.setForeground(Color.WHITE);
//					SignIn.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							String usr = Username.getText();
//							String pass = String.valueOf(Password.getPassword());
//							String correct = new String("");
//							correct = mysql.getPass(usr);
//							if(pass.compareTo(correct)==0 && pass.compareTo("1")!=0)
//							{
//								String name = mysql.getName(usr);
//								frame.callApp(usr, name);
//								frame.dispose();
//							}
//							else if(usr.compareTo("")!=0 && pass.compareTo("")!=0) {
//								if(correct.compareTo("1")==0) {
//									LogNoAcc noacc = new LogNoAcc();
//									noacc.setVisible(true);
//								}
//								else { 
//									LogFailed fail = new LogFailed();
//									fail.setVisible(true);
//								}
//							}
//						}
//					});
//					SignIn.setFont(new Font("Tahoma", Font.BOLD, 14));
//					SignIn.setBackground(Color.BLACK);
//					SignIn.setHorizontalAlignment(SwingConstants.CENTER);
//					SignIn.setBounds(30, 185, 80, 30);
//					contentPane.add(SignIn);
//
//					
//					//SIGN UP
//					JButton SignUp = new JButton("Sign Up");
//					SignUp.setForeground(Color.WHITE);
//					SignUp.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							frame.callSignUp();
//						}
//					});
//					SignUp.setFont(new Font("Tahoma", Font.BOLD, 14));
//					SignUp.setBackground(new Color(110, 50, 50));
//					SignUp.setHorizontalAlignment(SwingConstants.CENTER);
//					SignUp.setBounds(316, 185, 90, 30);
//					contentPane.add(SignUp);
//					
//					//FORGOT PASS
//					JButton Forgot = new JButton("Forgot Password?");
//					Forgot.setForeground(Color.WHITE);
//					Forgot.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							
//						}
//					});
//					Forgot.setFont(new Font("Tahoma", Font.BOLD, 11));
//					Forgot.setBackground(Color.WHITE);
//					Forgot.setForeground(Color.BLACK);
//					Forgot.setHorizontalAlignment(SwingConstants.CENTER);
//					Forgot.setBounds(138, 185, 145, 30);
//					contentPane.add(Forgot);
//					
//					JButton Exit = new JButton();
//					Exit.setForeground(Color.WHITE);
//					Exit.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							frame.dispose();
//						}
//					});
//					Exit.setFont(new Font("Tahoma", Font.BOLD, 10));
//					Exit.setBackground(Color.white);
//					Exit.setBounds(359, 400, 55, 50);
//					Exit.setIcon(new ImageIcon("F:\\Cong Viec\\Java\\Ionic-Ionicons-Exit-outline.512.png"));
//					contentPane.add(Exit);
//					
//				} catch (Exception e) {e.printStackTrace();}
//			}
//		});
//	}
	
	
//	public AppLogin() {
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(0, 0, 450, 500);
//		contentPane = new JPanel();
//		contentPane.setForeground(Color.BLACK);
//		contentPane.setBackground(new Color(255, 255, 255));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(null);
//		setContentPane(contentPane);
//		
//		
//		JLabel Welcome = new JLabel("Welcome to MooMooBank! ", SwingConstants.LEADING);
//		Welcome.setBounds(28, 11, 396, 34);
//		Welcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
//		contentPane.add(Welcome);
//		
//		JLabel PleaseEnter = new JLabel("Please enter your information: ", SwingConstants.LEADING);
//		PleaseEnter.setBounds(30, 60, 404, 25);
//		PleaseEnter.setFont(new Font("Tahoma", Font.BOLD, 18));
//		contentPane.add(PleaseEnter);
//		
//		JLabel LabelUsername = new JLabel("Account ID:");
//		LabelUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
//		LabelUsername.setBounds(30, 110, 112, 14);
//		contentPane.add(LabelUsername);
//		
//		
//		JLabel LabelPassword = new JLabel("Password:");
//		LabelPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
//		LabelPassword.setBounds(30, 145, 122, 14);
//		contentPane.add(LabelPassword);
//		
//		
//		JLabel MooPic = new JLabel("New label");
//		MooPic.setIcon(new ImageIcon("F:\\Cong Viec\\Java\\moooo.png"));
//		MooPic.setBounds(-25, 220, 353, 374);
//		contentPane.add(MooPic);
//
//	}
}
