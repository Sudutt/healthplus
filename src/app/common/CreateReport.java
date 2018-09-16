package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateReport {

	Object[][] data;
	public CreateReport() {
		data=new Object[100][7];
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            String no= "select * from sales";
			PreparedStatement ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			int sno=0;
			while(rs.next()) {
				for(int i=0; i<7; i++)
					data[sno][i]=rs.getObject(i+1);
				sno++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getColumns() {
		return new String[] {"S.No.","Date","Time","Mode","Party Name","Amount","Net Balance"};
	}

	public Object[][] getData() {
		return data;
	}
}
