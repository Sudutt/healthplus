package app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EmployeeMenu extends JFrame {

	private JPanel contentPane;

	public EmployeeMenu(String onlineUser) {
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
		productName.setFont(new Font("Berlin Sans FB", Font.PLAIN, 80));
		productName.setBounds(510, 60, 360, 110);
		contentPane.add(productName);
		
		JLabel greetUserLabel = new JLabel("Welcome "+onlineUser);
		greetUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetUserLabel.setForeground(Color.YELLOW);
		greetUserLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		greetUserLabel.setBounds(510, 181, 360, 51);
		contentPane.add(greetUserLabel);
		
		JButton sellLauncher = new JButton("Take Order");
		sellLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerBill cbill1 = new CustomerBill(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				cbill1.setVisible(true);
				close();
			}
		});
		sellLauncher.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		sellLauncher.setBorder(null);
		sellLauncher.setBounds(573, 264, 240, 43);
		contentPane.add(sellLauncher);
		
		JButton buyLauncher = new JButton("Refill Inventory");
		buyLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VendorInvoice vbill1 = new VendorInvoice(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				vbill1.setVisible(true);
				close();
			}
		});
		buyLauncher.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		buyLauncher.setBorder(null);
		buyLauncher.setBounds(573, 333, 240, 43);
		contentPane.add(buyLauncher);
		
		JButton newVendorLauncher = new JButton("Add New Vendor");
		newVendorLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddVendorScreen newven1 = new AddVendorScreen(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				newven1.setVisible(true);
				close();
			}
		});
		newVendorLauncher.setBorder(null);
		newVendorLauncher.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newVendorLauncher.setBounds(573, 467, 240, 43);
		contentPane.add(newVendorLauncher);
		
		JButton newMedicineLauncher = new JButton("Add New Medicine");
		newMedicineLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddMedicineScreen newmed1 = new AddMedicineScreen(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				newmed1.setVisible(true);
				close();
			}
		});
		newMedicineLauncher.setBorder(null);
		newMedicineLauncher.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newMedicineLauncher.setBounds(573, 401, 240, 43);
		contentPane.add(newMedicineLauncher);
		
		JLabel employeeSystemMessage = new JLabel("");
		employeeSystemMessage.setBounds(510, 539, 389, 43);
		contentPane.add(employeeSystemMessage);
		
		JButton employeeLogout = new JButton("Logout");
		employeeLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
		            String getUser = "update users set status=? where firstName=?";
		            PreparedStatement ps = conn.prepareStatement(getUser);
					ps.setString(1, "OFFLINE");
					ps.setString(2, onlineUser);
					ps.executeUpdate();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				LoginScreen loginScreen1 = new LoginScreen();
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				loginScreen1.setVisible(true);
				close();
			}
		});
		employeeLogout.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		employeeLogout.setBorder(null);
		employeeLogout.setBounds(975, 602, 133, 43);
		contentPane.add(employeeLogout);
				
		JLabel genScreenBackground = new JLabel("");
		genScreenBackground.setIcon(new ImageIcon(EmployeeMenu.class.getResource("/app/resources/border.jpg")));
		genScreenBackground.setBounds(0, 0, 1370, 752);
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
