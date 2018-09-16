package app.view;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.common.AddVendor;

@SuppressWarnings("serial")
public class AddVendorScreen extends JFrame {

	private JPanel contentPane;
	private JTextField newPhoneNo;
	private JTextField vendorName;
	private JTextField newVendorAddress;

	public AddVendorScreen(String onlineUser) {
		setBounds(new Rectangle(-8, -8, 1388, 743));
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUserForm.class.getResource("/app/resources/soft_icon.png")));
		setTitle("Healthplus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel productName = new JLabel("Healthplus");
		productName.setForeground(Color.ORANGE);
		productName.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50));
		productName.setBounds(190, 31, 233, 75);
		contentPane.add(productName);

		JLabel windowMessage = new JLabel("Add New Vendor");
		windowMessage.setHorizontalAlignment(SwingConstants.CENTER);
		windowMessage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		windowMessage.setBounds(678, 51, 210, 50);
		contentPane.add(windowMessage);
		
		vendorName = new JTextField();
		vendorName.setName("");
		vendorName.setMargin(new Insets(2, 10, 2, 10));
		vendorName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		vendorName.setColumns(10);
		vendorName.setBorder(null);
		vendorName.setBackground(SystemColor.menu);
		vendorName.setBounds(637, 198, 300, 38);
		contentPane.add(vendorName);
		
		JLabel vendorNameLabel = new JLabel("Name of Firm/Vendor");
		vendorNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vendorNameLabel.setForeground(Color.BLACK);
		vendorNameLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		vendorNameLabel.setBounds(386, 198, 199, 38);
		contentPane.add(vendorNameLabel);

		newVendorAddress = new JTextField();
		newVendorAddress.setBackground(SystemColor.control);
		newVendorAddress.setName("");
		newVendorAddress.setMargin(new Insets(2, 10, 2, 10));
		newVendorAddress.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newVendorAddress.setBorder(null);
		newVendorAddress.setBounds(637, 249, 300, 136);
		contentPane.add(newVendorAddress);
		newVendorAddress.setColumns(10);
		
		JLabel newVendorAddressLabel = new JLabel("Address");
		newVendorAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newVendorAddressLabel.setLabelFor(newVendorAddress);
		newVendorAddressLabel.setForeground(Color.BLACK);
		newVendorAddressLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		newVendorAddressLabel.setBounds(386, 247, 199, 38);
		contentPane.add(newVendorAddressLabel);
		
		newPhoneNo = new JTextField();
		newPhoneNo.setBackground(SystemColor.control);
		newPhoneNo.setName("");
		newPhoneNo.setMargin(new Insets(2, 10, 2, 10));
		newPhoneNo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newPhoneNo.setColumns(10);
		newPhoneNo.setBorder(null);
		newPhoneNo.setBounds(637, 396, 300, 38);
		contentPane.add(newPhoneNo);
		
		JLabel newPhoneNoLabel = new JLabel("Phone No.");
		newPhoneNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newPhoneNoLabel.setLabelFor(newPhoneNo);
		newPhoneNoLabel.setForeground(Color.BLACK);
		newPhoneNoLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		newPhoneNoLabel.setBounds(386, 396, 199, 38);
		contentPane.add(newPhoneNoLabel);
		
		JButton updateMedAttempt = new JButton("Update");
		updateMedAttempt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddVendor ven = new AddVendor();
				int res = ven.insertVendorData(vendorName.getText(), newVendorAddress.getText(), newPhoneNo.getText());
				if(res!=-1||res!=-2) {
					JOptionPane.showMessageDialog(null,"Update Request Sent for Approval.\nVendor Code = "+res);
					EmployeeMenu em4 = new EmployeeMenu(onlineUser);
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
					defaultScreen.setFullScreenWindow(null);
					em4.setVisible(true);
					close();
				}
				else if(res==-2) 
					JOptionPane.showMessageDialog(null,"Vendor Already Exists");					
				else 
					JOptionPane.showMessageDialog(null,"Vendor Not Inserted");			
			}
		});
		updateMedAttempt.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		updateMedAttempt.setBorderPainted(false);
		updateMedAttempt.setBorder(null);
		updateMedAttempt.setBounds(635, 480, 120, 60);
		contentPane.add(updateMedAttempt);
		
		JButton backout = new JButton("Back");
		backout.setBorder(null);
		backout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EmployeeMenu em2 = new EmployeeMenu(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				em2.setVisible(true);
				close();
			}
		});
		backout.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		backout.setBounds(780, 480, 120, 60);
		contentPane.add(backout);

		JLabel genScreenBackground = new JLabel("");
		genScreenBackground.setIcon(new ImageIcon(EmployeeMenu.class.getResource("/app/resources/border.jpg")));
		genScreenBackground.setBounds(0, 0, 1370, 750);
		contentPane.add(genScreenBackground);
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
