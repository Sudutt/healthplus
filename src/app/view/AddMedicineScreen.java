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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.common.AddMedicine;

@SuppressWarnings("serial")
public class AddMedicineScreen extends JFrame {

	private JPanel contentPane;
	private JTextField tradeName;
	private JTextField newMedicineQuantity;
	private JTextField newBuyCostPerItem;
	private JTextField newSellCostPerItem;

	public AddMedicineScreen(String onlineUser) {
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

		JLabel windowMessage = new JLabel("Add New Medicine");
		windowMessage.setHorizontalAlignment(SwingConstants.CENTER);
		windowMessage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		windowMessage.setBounds(678, 51, 210, 50);
		contentPane.add(windowMessage);
		
		JComboBox<String> vendorSelect = new JComboBox<String>();
		vendorSelect.setBackground(SystemColor.control);
		vendorSelect.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		String vends[] = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
			String no = "select * from vendors";
			PreparedStatement ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			int size=0;
			while(rs.next()) {
				size++;
			}
			rs = ps.executeQuery();
			vends = new String[size];	int i=0;
			while(rs.next()) {
				vends[i]=rs.getString("name");
				i++;
			}
			conn.close();
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		vendorSelect.setModel(new DefaultComboBoxModel<String>(vends));
		vendorSelect.setBounds(637, 198, 300, 40);
		contentPane.add(vendorSelect);
		
		JLabel vendorSelectLabel = new JLabel("Vendor");
		vendorSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vendorSelectLabel.setForeground(Color.BLACK);
		vendorSelectLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		vendorSelectLabel.setBounds(386, 198, 199, 38);
		contentPane.add(vendorSelectLabel);
		vendorSelectLabel.setLabelFor(vendorSelect);

		JTextField newGenericName = new JTextField();
		newGenericName.setBackground(SystemColor.control);
		newGenericName.setName("");
		newGenericName.setMargin(new Insets(2, 10, 2, 10));
		newGenericName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newGenericName.setBorder(null);
		newGenericName.setBounds(637, 249, 300, 38);
		contentPane.add(newGenericName);
		newGenericName.setColumns(10);
		
		JLabel newGenericNameLabel = new JLabel("Generic Name of Medicine");
		newGenericNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newGenericNameLabel.setLabelFor(newGenericName);
		newGenericNameLabel.setForeground(Color.BLACK);
		newGenericNameLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		newGenericNameLabel.setBounds(386, 247, 199, 38);
		contentPane.add(newGenericNameLabel);
		
		tradeName = new JTextField();
		tradeName.setBackground(SystemColor.control);
		tradeName.setName("");
		tradeName.setMargin(new Insets(2, 10, 2, 10));
		tradeName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		tradeName.setColumns(10);
		tradeName.setBorder(null);
		tradeName.setBounds(637, 298, 300, 38);
		contentPane.add(tradeName);
		
		JLabel tradeNameLabel = new JLabel("Trade Name of Medicine");
		tradeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tradeNameLabel.setForeground(Color.BLACK);
		tradeNameLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		tradeNameLabel.setBounds(386, 296, 199, 38);
		contentPane.add(tradeNameLabel);
		tradeNameLabel.setLabelFor(tradeName);
		
		newMedicineQuantity = new JTextField();
		newMedicineQuantity.setBackground(SystemColor.control);
		newMedicineQuantity.setName("");
		newMedicineQuantity.setMargin(new Insets(2, 10, 2, 10));
		newMedicineQuantity.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newMedicineQuantity.setColumns(10);
		newMedicineQuantity.setBorder(null);
		newMedicineQuantity.setBounds(637, 347, 300, 38);
		contentPane.add(newMedicineQuantity);
		
		JLabel newMedicineQuantityLabel = new JLabel("Quantity");
		newMedicineQuantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newMedicineQuantityLabel.setLabelFor(newMedicineQuantity);
		newMedicineQuantityLabel.setForeground(Color.BLACK);
		newMedicineQuantityLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		newMedicineQuantityLabel.setBounds(386, 345, 199, 38);
		contentPane.add(newMedicineQuantityLabel);
		
		newBuyCostPerItem = new JTextField();
		newBuyCostPerItem.setName("");
		newBuyCostPerItem.setMargin(new Insets(2, 10, 2, 10));
		newBuyCostPerItem.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newBuyCostPerItem.setColumns(10);
		newBuyCostPerItem.setBorder(null);
		newBuyCostPerItem.setBackground(SystemColor.menu);
		newBuyCostPerItem.setBounds(637, 396, 300, 38);
		contentPane.add(newBuyCostPerItem);
		
		JLabel newBuyCostPerItemLabel = new JLabel("Cost Price (p.u.)");
		newBuyCostPerItemLabel.setLabelFor(newBuyCostPerItem);
		newBuyCostPerItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newBuyCostPerItemLabel.setForeground(Color.BLACK);
		newBuyCostPerItemLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		newBuyCostPerItemLabel.setBounds(386, 399, 199, 38);
		contentPane.add(newBuyCostPerItemLabel);
		
		JButton updateMedAttempt = new JButton("Update");
		updateMedAttempt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)  {
				AddMedicine ven = new AddMedicine();
				int res = ven.insertMedData(vendorSelect.getSelectedItem().toString(), newGenericName.getText(), tradeName.getText(),Integer.parseInt(newMedicineQuantity.getText())
						,Double.parseDouble(newBuyCostPerItem.getText()),Double.parseDouble(newSellCostPerItem.getText()));
				if(res!=-1||res!=-2) {
					JOptionPane.showMessageDialog(null,"Update Request Sent for Approval.\nMedicine Code = "+res);
					EmployeeMenu em4 = new EmployeeMenu(onlineUser);
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
					defaultScreen.setFullScreenWindow(null);
					em4.setVisible(true);
					close();
				}
				else if(res==-2) 
					JOptionPane.showMessageDialog(null,"Medicine Already Exists");					
				else 
					JOptionPane.showMessageDialog(null,"Medicine Not inserted");		
			}
		});
		
		newSellCostPerItem = new JTextField();
		newSellCostPerItem.setName("");
		newSellCostPerItem.setMargin(new Insets(2, 10, 2, 10));
		newSellCostPerItem.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		newSellCostPerItem.setColumns(10);
		newSellCostPerItem.setBorder(null);
		newSellCostPerItem.setBackground(SystemColor.menu);
		newSellCostPerItem.setBounds(635, 445, 300, 38);
		contentPane.add(newSellCostPerItem);
		
		JLabel newSellCostPerItemLabel = new JLabel("Selling Price (p.u.)");
		newSellCostPerItemLabel.setLabelFor(newSellCostPerItem);
		newSellCostPerItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newSellCostPerItemLabel.setForeground(Color.BLACK);
		newSellCostPerItemLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		newSellCostPerItemLabel.setBounds(386, 448, 199, 38);
		contentPane.add(newSellCostPerItemLabel);
		updateMedAttempt.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		updateMedAttempt.setBorderPainted(false);
		updateMedAttempt.setBorder(null);
		updateMedAttempt.setBounds(637, 542, 120, 60);
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
		backout.setBounds(780, 542, 120, 60);
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
