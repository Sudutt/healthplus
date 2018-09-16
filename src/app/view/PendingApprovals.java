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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.common.CreatePendingMedTable;
import app.common.CreatePendingUserTable;
import app.common.CreatePendingVendorTable;

@SuppressWarnings("serial")
public class PendingApprovals extends JFrame {

	private JPanel contentPane;
	private JTable pendingEmployees;
	private JTable pendingVendors;
	private JTable pendingMedicines;

	public PendingApprovals(String onlineUser) {
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

		JLabel windowMessage = new JLabel("Pending Approval Requests");
		windowMessage.setHorizontalAlignment(SwingConstants.CENTER);
		windowMessage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		windowMessage.setBounds(623, 51, 320, 50);
		contentPane.add(windowMessage);
		
		CreatePendingUserTable userinfo = new CreatePendingUserTable();
		DefaultTableModel model1 = new DefaultTableModel(userinfo.getData(), userinfo.getColumns()) {
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
		pendingEmployees = new JTable(model1);
		pendingEmployees.setBackground(SystemColor.control);
		pendingEmployees.setBounds(482, 142, 575, 200);
		JScrollPane pesp = new JScrollPane(pendingEmployees);
		pesp.setBounds(482, 142, 575, 150);
		contentPane.add(pesp);

		JButton approveEmployees = new JButton("Approve Employees");
		approveEmployees.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rep = "FAILURE";
				if(userinfo.approveUsers(pendingEmployees.getSelectedRows(),userinfo.getData()));
					rep = "SUCCESS";				
				JOptionPane.showMessageDialog(null, "Approval Status: "+rep);
				PendingApprovals pa1 = new PendingApprovals(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				pa1.setVisible(true);
				close();
			}
		});
		approveEmployees.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		approveEmployees.setBounds(213, 187, 210, 50);
		contentPane.add(approveEmployees);

		CreatePendingVendorTable vendorinfo = new CreatePendingVendorTable();
		DefaultTableModel model2 = new DefaultTableModel(vendorinfo.getData(), vendorinfo.getColumns()) {
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
		pendingVendors = new JTable(model2);
		pendingVendors.setBackground(SystemColor.control);
		pendingVendors.setBounds(482, 303, 575, 200);
		JScrollPane pvsp = new JScrollPane(pendingVendors);
		pvsp.setBounds(482, 303, 575, 150);
		contentPane.add(pvsp);

		JButton approveVendors = new JButton("Approve Vendors");
		approveVendors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rep = "FAILURE";
				if(vendorinfo.approveVends(pendingVendors.getSelectedRows(),vendorinfo.getData()));
					rep = "SUCCESS";
				JOptionPane.showMessageDialog(null, "Approval Status: "+rep);
				PendingApprovals pa1 = new PendingApprovals(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				pa1.setVisible(true);
				close();
			}
		});
		approveVendors.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		approveVendors.setBounds(213, 357, 210, 50);
		contentPane.add(approveVendors);

		CreatePendingMedTable medinfo = new CreatePendingMedTable();
		DefaultTableModel model3 = new DefaultTableModel(medinfo.getData(), medinfo.getColumns()) {
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
		pendingMedicines= new JTable(model3);
		pendingMedicines.setBackground(SystemColor.control);
		pendingMedicines.setBounds(482, 464, 575, 200);
		JScrollPane pmsp = new JScrollPane(pendingMedicines);
		pmsp.setBounds(482, 464, 575, 150);
		contentPane.add(pmsp);

		JButton approveMedicines = new JButton("Approve Medicines");
		approveMedicines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rep = "FAILURE";
				if(medinfo.approveMeds(pendingMedicines.getSelectedRows(),medinfo.getData()));
					rep = "SUCCESS";
				JOptionPane.showMessageDialog(null, "Approval Status: "+rep);
				PendingApprovals pa1 = new PendingApprovals(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				pa1.setVisible(true);
				close();
			}
		});
		approveMedicines.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		approveMedicines.setBounds(213, 514, 210, 50);
		contentPane.add(approveMedicines);

		JButton backout = new JButton("Back");
		backout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerMenu em2 = new ManagerMenu(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				em2.setVisible(true);
				close();
			}
		});
		backout.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		backout.setBounds(788, 636, 108, 31);
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
