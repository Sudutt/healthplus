package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatePendingMedTable {

	public boolean approveMeds(int selectedRows[], Object[][] data) {
		for(int index : selectedRows) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
				PreparedStatement ps = conn.prepareStatement("UPDATE meds SET approval=? WHERE id = ?");
				ps.setString(1, "APPROVED");
				ps.setInt(2, (Integer) data[index][0]);
				ps.executeUpdate();
				conn.close();
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public String[] getColumns() {
		return new String[] {"ID","Generic Name","Trade Name","Vendor Name","Quantity","Buy Cost per Item","Sell Cost per Item"};
	}
	
	public Object[][] getData() {
		Object[][] data = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
			PreparedStatement ps = conn.prepareStatement("SELECT id, generic_name, trade_name, vendor_name, qty, buy_cost, sell_cost FROM meds WHERE approval=?");
			ps.setString(1, "PENDING");
			ResultSet rs = ps.executeQuery();
			int size=0;
			while(rs.next()) {size++;	}
			
			rs = ps.executeQuery();
			data = new Object[size][7];
			int i=0, j=0;
			while(rs.next()) {
				for(j=0; j<7; j++)
					data[i][j]=rs.getObject(j+1);
				i++;
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return data;
	}
}
