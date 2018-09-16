package app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.common.HandleBill;

@SuppressWarnings("serial")
public class CustomerBill extends JFrame {

	private JPanel contentPane;
	private JTextField medicineGenericName;
	private JTextField medicineTradeName;
	private JTextField medicineStackCode;
	private JTextField medicineQuantity;
	private JTextField totalBillAmount;
	private JTextField custName;
	private JTable customerBill;
	private JScrollPane cbsp;

	public CustomerBill(String onlineUser) {
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

		JLabel windowMessage = new JLabel("Customer Bill");
		windowMessage.setHorizontalAlignment(SwingConstants.CENTER);
		windowMessage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		windowMessage.setBounds(678, 51, 210, 50);
		contentPane.add(windowMessage);

		medicineGenericName = new JTextField();
		medicineGenericName.setText("Text Here");
		medicineGenericName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		medicineGenericName.setBounds(386, 137, 180, 25);
		contentPane.add(medicineGenericName);
		medicineGenericName.setColumns(10);

		JLabel medicineGenericNameLabel = new JLabel("Generic Name");
		medicineGenericNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		medicineGenericNameLabel.setLabelFor(medicineGenericName);
		medicineGenericNameLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		medicineGenericNameLabel.setBounds(270, 137, 110, 25);
		contentPane.add(medicineGenericNameLabel);

		medicineTradeName = new JTextField();
		medicineTradeName.setText("Text Here");
		medicineTradeName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		medicineTradeName.setBounds(386, 167, 180, 25);
		contentPane.add(medicineTradeName);
		medicineTradeName.setColumns(10);

		JLabel medicineTradeNameLabel = new JLabel("Trade Name");
		medicineTradeNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		medicineTradeNameLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		medicineTradeNameLabel.setBounds(270, 167, 110, 25);
		contentPane.add(medicineTradeNameLabel);

		medicineStackCode = new JTextField();
		medicineStackCode.setText("0");
		medicineStackCode.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		medicineStackCode.setBounds(386, 197, 180, 25);
		contentPane.add(medicineStackCode);
		medicineStackCode.setColumns(10);

		JLabel medicineStackCodeLabel = new JLabel("Stack Code");
		medicineStackCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		medicineStackCodeLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		medicineStackCodeLabel.setBounds(270, 197, 110, 25);
		contentPane.add(medicineStackCodeLabel);

		medicineQuantity = new JTextField();
		medicineQuantity.setText("0");
		medicineQuantity.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		medicineQuantity.setBounds(386, 227, 180, 25);
		contentPane.add(medicineQuantity);
		medicineQuantity.setColumns(10);

		JLabel medicineQuantityLabel = new JLabel("Quantity");
		medicineQuantityLabel.setHorizontalAlignment(SwingConstants.LEFT);
		medicineQuantityLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		medicineQuantityLabel.setBounds(270, 227, 110, 25);
		contentPane.add(medicineQuantityLabel);

		HandleBill billinfo = new HandleBill();
		DefaultTableModel model4 = new DefaultTableModel(billinfo.getData(), billinfo.getColumns()) {
		    @Override
			public boolean isCellEditable(int row, int column)
		    {	        return false;		    }
		    @SuppressWarnings("unused")
			public boolean isCellSelectable() 
		    {			return false;			}
		    @SuppressWarnings("unused")
			public boolean isRowSelectable()
		    {			return true;			}
		    };
		customerBill = new JTable(model4);
		customerBill.setBackground(SystemColor.control);
		customerBill.setBounds(482, 464, 575, 200);
		customerBill.setAutoCreateRowSorter(true);
		cbsp = new JScrollPane(customerBill);
		cbsp.setBounds(270, 263, 735, 300);
		contentPane.add(cbsp);

		JLabel ErrorMessage = new JLabel("");
		ErrorMessage.setBounds(678, 206, 210, 25);
		contentPane.add(ErrorMessage);
		
		JButton addMedToBill = new JButton("Add Medicine");
		addMedToBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMedToBill.setEnabled(false);
				ResultSet rs = billinfo.checkMedInStock(medicineGenericName.getText(), medicineTradeName.getText(), 
						Integer.parseInt(medicineStackCode.getText()), Integer.parseInt(medicineQuantity.getText()));
				try {
					if(rs!=null && rs.next()!=false) {
						medicineGenericName.setText(rs.getString(2));
						medicineTradeName.setText(rs.getString(3));
						medicineStackCode.setText(rs.getString(8));
						addMedToBill.setEnabled(true);
						billinfo.addMedWithQty(Integer.parseInt(medicineStackCode.getText()), Integer.parseInt(medicineQuantity.getText()));
						DefaultTableModel model = new DefaultTableModel(billinfo.getData(), billinfo.getColumns()) {
						    @Override
							public boolean isCellEditable(int row, int column)
						    {	        return false;		    }
						    @SuppressWarnings("unused")
							public boolean isCellSelectable() 
						    {			return false;			}
						    @SuppressWarnings("unused")
							public boolean isRowSelectable()
						    {			return true;			}
						    };
						customerBill= new JTable(model);
						customerBill.setBackground(SystemColor.control);
						customerBill.setBounds(482, 464, 575, 200);
						customerBill.setAutoCreateRowSorter(true);
						cbsp = new JScrollPane(customerBill);
						cbsp.setBounds(270, 263, 735, 300);
						contentPane.add(cbsp);
						totalBillAmount.setText(billinfo.fullAmount()+"");
					}
					else
						ErrorMessage.setText("Not Available in Reqd Qty");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addMedToBill.setEnabled(false);
		addMedToBill.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		addMedToBill.setBorder(null);
		addMedToBill.setBounds(678, 168, 210, 25);
		contentPane.add(addMedToBill);

		JButton searchMedInDatabase = new JButton("Check Availability");
		searchMedInDatabase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMedToBill.setEnabled(false);
				ResultSet rs = billinfo.checkMedInStock(medicineGenericName.getText(), medicineTradeName.getText(), 
						Integer.parseInt(medicineStackCode.getText()), Integer.parseInt(medicineQuantity.getText()));
				try {
					if(rs!=null && rs.next()!=false) {
						medicineGenericName.setText(rs.getString(2));
						medicineTradeName.setText(rs.getString(3));
						medicineStackCode.setText(rs.getString(8));
						addMedToBill.setEnabled(true);
						ErrorMessage.setText("");
					}
					else
						ErrorMessage.setText("Not Available in Reqd Qty");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		searchMedInDatabase.setBorder(null);
		searchMedInDatabase.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		searchMedInDatabase.setBounds(678, 137, 210, 25);
		contentPane.add(searchMedInDatabase);
		
		custName = new JTextField();
		custName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		custName.setColumns(10);
		custName.setBounds(386, 574, 180, 25);
		contentPane.add(custName);
		
		JLabel custNameLabel = new JLabel("Customer Name");
		custNameLabel.setLabelFor(custName);
		custNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		custNameLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		custNameLabel.setBounds(260, 574, 120, 25);
		contentPane.add(custNameLabel);

		totalBillAmount = new JTextField();
		totalBillAmount.setEditable(false);
		totalBillAmount.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		totalBillAmount.setColumns(10);
		totalBillAmount.setBounds(798, 574, 180, 25);
		contentPane.add(totalBillAmount);

		JLabel totalBillAmountLabel = new JLabel("Total Amount");
		totalBillAmountLabel.setLabelFor(totalBillAmount);
		totalBillAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		totalBillAmountLabel.setFont(new Font("Gadugi", Font.PLAIN, 16));
		totalBillAmountLabel.setBounds(678, 575, 110, 25);
		contentPane.add(totalBillAmountLabel);

		JButton confirmBill = new JButton("Confirm Bill");
		confirmBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				totalBillAmount.setText(billinfo.fullAmount()+"");
				billinfo.finishBill(custName.getText());
				JOptionPane.showMessageDialog(null, "Sale Completed.\nAmount: Rs. "+totalBillAmount.getText());
				EmployeeMenu em1 = new EmployeeMenu(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				em1.setVisible(true);
				close();
			}
		});
		confirmBill.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		confirmBill.setBorder(null);
		confirmBill.setBounds(480, 621, 210, 25);
		contentPane.add(confirmBill);

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
		backout.setBounds(763, 621, 120, 25);
		contentPane.add(backout);
		
		JLabel genScreenBackground = new JLabel("");
		genScreenBackground.setIcon(new ImageIcon(EmployeeMenu.class.getResource("/app/resources/border.jpg")));
		genScreenBackground.setBounds(10, 0, 1360, 717);
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
