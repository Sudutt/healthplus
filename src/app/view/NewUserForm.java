package app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.common.RegisterUser;

@SuppressWarnings("serial")
public class NewUserForm extends JFrame {

	private JPanel contentPane;
	private JTextField lastName;
	private JTextField userName;
	private JPasswordField passwordField;

	public NewUserForm() {
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
		
		JLabel newUserFormPrompt = new JLabel("New User Registration");
		newUserFormPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		newUserFormPrompt.setForeground(Color.WHITE);
		newUserFormPrompt.setFont(new Font("Gadugi", Font.BOLD, 20));
		newUserFormPrompt.setBounds(510, 181, 360, 51);
		contentPane.add(newUserFormPrompt);

		JComboBox<String> userType = new JComboBox<String>();
		userType.setBackground(Color.WHITE);
		userType.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		userType.setModel(new DefaultComboBoxModel<String>(new String[] {"Manager", "Employee"}));
		userType.setBounds(610, 274, 300, 40);
		contentPane.add(userType);
		
		JLabel userTypeLabel = new JLabel("User Type");
		userTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userTypeLabel.setForeground(Color.WHITE);
		userTypeLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		userTypeLabel.setBounds(359, 274, 199, 38);
		contentPane.add(userTypeLabel);
		userTypeLabel.setLabelFor(userType);

		JTextField firstName = new JTextField();
		firstName.setName("");
		firstName.setMargin(new Insets(2, 10, 2, 10));
		firstName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		firstName.setBorder(null);
		firstName.setBounds(610, 325, 300, 38);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameLabel.setLabelFor(firstName);
		firstNameLabel.setForeground(Color.WHITE);
		firstNameLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		firstNameLabel.setBounds(359, 323, 199, 38);
		contentPane.add(firstNameLabel);
		
		lastName = new JTextField();
		lastName.setName("");
		lastName.setMargin(new Insets(2, 10, 2, 10));
		lastName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		lastName.setColumns(10);
		lastName.setBorder(null);
		lastName.setBounds(610, 374, 300, 38);
		contentPane.add(lastName);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameLabel.setForeground(Color.WHITE);
		lastNameLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		lastNameLabel.setBounds(359, 372, 199, 38);
		contentPane.add(lastNameLabel);
		lastNameLabel.setLabelFor(lastName);
		
		userName = new JTextField();
		userName.setName("");
		userName.setMargin(new Insets(2, 10, 2, 10));
		userName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		userName.setColumns(10);
		userName.setBorder(null);
		userName.setBounds(610, 423, 300, 38);
		contentPane.add(userName);
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setLabelFor(userName);
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		userNameLabel.setBounds(359, 421, 199, 38);
		contentPane.add(userNameLabel);
		
		passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(2, 10, 2, 10));
		passwordField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 28));
		passwordField.setBorder(null);
		passwordField.setBounds(610, 472, 300, 38);
		contentPane.add(passwordField);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PasswordLabel.setLabelFor(passwordField);
		PasswordLabel.setForeground(Color.WHITE);
		PasswordLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		PasswordLabel.setBounds(359, 475, 199, 38);
		contentPane.add(PasswordLabel);
		
		JPasswordField reenterPasswordField = new JPasswordField();
		reenterPasswordField.setMargin(new Insets(2, 10, 2, 10));
		reenterPasswordField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 28));
		reenterPasswordField.setBorder(null);
		reenterPasswordField.setBounds(610, 521, 300, 38);
		contentPane.add(reenterPasswordField);
		
		JLabel reenterPasswordLabel = new JLabel("Re-enter Password");
		reenterPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reenterPasswordLabel.setForeground(Color.WHITE);
		reenterPasswordLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		reenterPasswordLabel.setBounds(359, 521, 199, 38);
		contentPane.add(reenterPasswordLabel);
		reenterPasswordLabel.setLabelFor(reenterPasswordField);
		
		JButton registerAttempt = new JButton("Register");
		registerAttempt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterUser r1 = new RegisterUser();
				if(!(new String(passwordField.getPassword()).equals(new String(reenterPasswordField.getPassword()))))
					JOptionPane.showMessageDialog(null,"Passwords do not Match");	
				else if(r1.insertUserData(userType.getSelectedItem().toString(), firstName.getText(), lastName.getText(), userName.getText(), new String(passwordField.getPassword()))) {
						//Finalizing Display
					 	String approvStat="Welcome to Healthplus";
					 	if (userType.getSelectedItem()!="Manager")
					 		approvStat = "Approval will be done by Manager.\n"+approvStat;
						JOptionPane.showMessageDialog(null,approvStat);	
					 	LoginScreen loginScreen3 = new LoginScreen();
					 	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					 	GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
						defaultScreen.setFullScreenWindow(null);
						loginScreen3.setVisible(true);
						close();
				}
				else
					JOptionPane.showMessageDialog(null,"Username Taken.\nTry another one");	
			}
		});
		registerAttempt.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		registerAttempt.setBorderPainted(false);
		registerAttempt.setBorder(null);
		registerAttempt.setBounds(610, 593, 116, 59);
		contentPane.add(registerAttempt);
		
		JLabel registerScreenBackground = new JLabel("");
		registerScreenBackground.setIcon(new ImageIcon(NewUserForm.class.getResource("/app/resources/login_background.jpg")));
		registerScreenBackground.setBounds(0, 0, 1370, 750);
		contentPane.add(registerScreenBackground);
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
