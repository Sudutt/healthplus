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
public class ManagerMenu extends JFrame {

	private JPanel contentPane;

	public ManagerMenu(String onlineUser) {
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
		
		JButton approvalLauncher = new JButton("Pending Requests");
		approvalLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PendingApprovals pa1 = new PendingApprovals(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				pa1.setVisible(true);
				close();
			}
		});
		approvalLauncher.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		approvalLauncher.setBorder(null);
		approvalLauncher.setBounds(573, 298, 240, 43);
		contentPane.add(approvalLauncher);
		
		JButton salesReportLauncher = new JButton("Sales Report");
		salesReportLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SalesReport rep1 = new SalesReport(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				rep1.setVisible(true);
				close();
			}
		});
		salesReportLauncher.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		salesReportLauncher.setBorder(null);
		salesReportLauncher.setBounds(573, 398, 240, 43);
		contentPane.add(salesReportLauncher);
		
		JLabel managerSystemMessage = new JLabel("");
		managerSystemMessage.setBounds(510, 539, 389, 43);
		contentPane.add(managerSystemMessage);
				
		JButton managerLogout = new JButton("Logout");
		managerLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
		            String getUser = "update users set status=? where firstName=?";
		            PreparedStatement ps = conn.prepareStatement(getUser);
					ps.setString(1, "OFFLINE");
					ps.setString(2, onlineUser);
					ps.executeUpdate();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				LoginScreen loginScreen2 = new LoginScreen();
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				loginScreen2.setVisible(true);
				close();
			}
		});
		managerLogout.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		managerLogout.setBorder(null);
		managerLogout.setBounds(975, 602, 133, 43);
		contentPane.add(managerLogout);
		
		JLabel genScreenBackground = new JLabel("");
		genScreenBackground.setIcon(new ImageIcon(ManagerMenu.class.getResource("/app/resources/border.jpg")));
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
