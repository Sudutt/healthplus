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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.common.CreateReport;

@SuppressWarnings("serial")
public class SalesReport extends JFrame {

	private JPanel contentPane;
	private JTable rep;

	public SalesReport(String onlineUser) {
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
		
		JLabel windowMessage = new JLabel("Sales Report");
		windowMessage.setHorizontalAlignment(SwingConstants.CENTER);
		windowMessage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		windowMessage.setBounds(678, 51, 210, 50);
		contentPane.add(windowMessage);
		
		CreateReport fullrep = new CreateReport();
		DefaultTableModel model = new DefaultTableModel(fullrep.getData(), fullrep.getColumns()) {
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
		rep = new JTable(model);
		rep.setBackground(SystemColor.control);
		rep.setBounds(276, 144, 900, 444);
		rep.setAutoCreateRowSorter(true);
		JScrollPane pvsp = new JScrollPane(rep);
		pvsp.setBounds(276, 144, 803, 444);
		contentPane.add(pvsp);

		JButton returnToMenu = new JButton("Back");
		returnToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerMenu mm2 = new ManagerMenu(onlineUser);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				defaultScreen.setFullScreenWindow(null);
				mm2.setVisible(true);
				close();
			}
		});
		returnToMenu.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		returnToMenu.setBorder(null);
		returnToMenu.setBounds(625, 620, 100, 25);
		contentPane.add(returnToMenu);
		
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
