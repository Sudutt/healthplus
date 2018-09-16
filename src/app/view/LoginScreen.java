package app.view; 

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.common.VerifyLogin;

@SuppressWarnings("serial")
public class LoginScreen extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
			    LoginScreen frame = new LoginScreen();
				try {
					frame.setVisible(true);
					defaultScreen.setFullScreenWindow(null);
				}  
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setBounds(new Rectangle(-8, -8, 1388, 743));
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUserForm.class.getResource("/app/resources/soft_icon.png")));
		setTitle("Healthplus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel productName = new JLabel("Healthplus");
		productName.setForeground(Color.WHITE);
		productName.setFont(new Font("Berlin Sans FB", Font.PLAIN, 80));
		productName.setBounds(510, 60, 360, 110);
		contentPane.add(productName);
		
		username = new JTextField();
		username.setName("");
		username.setMargin(new Insets(2, 10, 2, 10));
		username.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		username.setBorder(null);
		username.setBounds(610, 260, 300, 51);
		contentPane.add(username);
		username.setColumns(10);

		JLabel usernamePrompt = new JLabel("Username");
		usernamePrompt.setHorizontalAlignment(SwingConstants.TRAILING);
		usernamePrompt.setForeground(Color.WHITE);
		usernamePrompt.setFont(new Font("Gadugi", Font.BOLD, 20));
		usernamePrompt.setBounds(429, 260, 129, 51);
		contentPane.add(usernamePrompt);
		usernamePrompt.setLabelFor(username);
		
		password = new JPasswordField();
		password.setMargin(new Insets(2, 10, 2, 10));
		password.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 28));
		password.setBorder(null);
		password.setBounds(610, 350, 300, 51);
		contentPane.add(password);

		JLabel passwordPrompt = new JLabel("Password");
		passwordPrompt.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordPrompt.setForeground(Color.WHITE);
		passwordPrompt.setFont(new Font("Gadugi", Font.BOLD, 20));
		passwordPrompt.setBounds(429, 354, 129, 51);
		contentPane.add(passwordPrompt);
		passwordPrompt.setLabelFor(password);
		
		JButton loginAttempt = new JButton("Login");
		loginAttempt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VerifyLogin obj = new VerifyLogin(username.getText(),new String(password.getPassword()));
				if(obj.check()==false) {
					JOptionPane.showMessageDialog(null,"Invalid credentials");
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
			            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
			            String getUser = "update users set status=? where username=?";
			            PreparedStatement ps = conn.prepareStatement(getUser);
						ps.setString(1, "ONLINE");
						ps.setString(2, username.getText());
						ps.executeUpdate(); 
						getUser = "select * from users where username=?";
						ps = conn.prepareStatement(getUser);
						ps.setString(1, username.getText());
						ResultSet rs = ps.executeQuery();	rs.first();
						String userFirst = rs.getString("firstName");
						if(rs.getString("type").equals("Manager")) {
							conn.close();
							ManagerMenu user1 = new ManagerMenu(userFirst);
							GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
							GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
							defaultScreen.setFullScreenWindow(null);
							user1.setVisible(true);
							close();
						}
						else{
							conn.close();
							EmployeeMenu user1 = new EmployeeMenu(userFirst);
							GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
							GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
							defaultScreen.setFullScreenWindow(null);
							user1.setVisible(true);
							close();
						}
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		loginAttempt.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		loginAttempt.setBorderPainted(false);
		loginAttempt.setBorder(null);
		loginAttempt.setBounds(610, 443, 116, 59);
		contentPane.add(loginAttempt);
		
		JLabel lblNewUser = new JLabel("New User?");
		lblNewUser.setForeground(Color.WHITE);
		lblNewUser.setFont(new Font("Gadugi", Font.BOLD, 20));
		lblNewUser.setBounds(540, 555, 116, 59);
		contentPane.add(lblNewUser);
		
		JButton btnRegisterHere = new JButton("Register Here");
		btnRegisterHere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegisterHere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewUserForm user1 = new NewUserForm();
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				user1.setVisible(true);
				close();
			}
		});
		btnRegisterHere.setForeground(Color.WHITE);
		btnRegisterHere.setFont(new Font("Gadugi", Font.BOLD, 20));
		btnRegisterHere.setContentAreaFilled(false); 
		btnRegisterHere.setBorderPainted(false);
		btnRegisterHere.setBorder(null);
		lblNewUser.setLabelFor(btnRegisterHere);
		btnRegisterHere.setBounds(666, 555, 150, 59);
		contentPane.add(btnRegisterHere);
		
		JLabel loginScreenBackground = new JLabel("");
		loginScreenBackground.setIcon(new ImageIcon(LoginScreen.class.getResource("/app/resources/login_background.jpg")));
		loginScreenBackground.setBounds(0, 0, 1370, 750);
		contentPane.add(loginScreenBackground);
	}
	public void close() {
		try {
			this.setVisible(false);
			this.dispose();
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
}
