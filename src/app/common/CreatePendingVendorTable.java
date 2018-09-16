package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatePendingVendorTable {

	public boolean approveVends(int selectedRows[], Object[][] data) {
		for(int index : selectedRows) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
				PreparedStatement ps = conn.prepareStatement("UPDATE vendors SET approval=? WHERE id = ?");
				ps.setString(1, "APPROVED");
				ps.setInt(2, (Integer) data[index][0]);
				ps.executeUpdate();
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public String[] getColumns() {
		return new String[] {"ID","Name","Address","Phone No."};
	}
	
	public Object[][] getData() {
		Object[][] data = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
			PreparedStatement ps = conn.prepareStatement("SELECT id, name, address, phone_no FROM vendors WHERE approval=?");
			ps.setString(1, "PENDING");
			ResultSet rs = ps.executeQuery();
			int size=0;
			while(rs.next()) {size++;	}
			
			rs = ps.executeQuery();
			data = new Object[size][4];
			int i=0, j=0;
			while(rs.next()) {
				for(j=0; j<4; j++)
					data[i][j]=rs.getObject(j+1);
				i++;
			}
			
		} catch (SQLException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return data;
	}
}
