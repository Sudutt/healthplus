package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMedicine  {
	Connection conn;
	int count;
	public AddMedicine(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            String makeTableUsers = "create table if not exists meds("+
    			"	`vendor_name`	TEXT NOT NULL,\r\n" + 
				"	`generic_name`	TEXT NOT NULL,\r\n" + 
				"	`trade_name`	TEXT NOT NULL,\r\n" + 
				"	`qty`	INTEGER NOT NULL,\r\n" + 
				"	`buy_cost`	REAL NOT NULL,\r\n" +
				"	`sell_cost`	REAL NOT NULL,\r\n" +
				"	`approval`	TEXT NOT NULL,\r\n"+ 
				"	`id`	INTEGER NOT NULL,\r\n" + 
				"	PRIMARY KEY(`id`)\r\n" + 
				");";
			PreparedStatement ps = conn.prepareStatement(makeTableUsers);
			ps.executeUpdate();
			String no= "select * from meds";
			ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			this.count=0;
			while(rs.next())
				count++;
			conn.close();
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMedData(String vname, String gname, String tname, int qty, double cp, double sp) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            String no= "select * from meds";
			PreparedStatement ps;
			ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("generic_name")==gname)
					return -2;
			}
			//Now safe, inserting vendor
			String insertUser = "insert into meds values(?,?,?,?,?,?,?,?) ";
			ps = conn.prepareStatement(insertUser);
			ps.setString(1, vname);
			ps.setString(2, gname);
			ps.setString(3, tname);
			ps.setInt(4, qty);
			ps.setDouble(5, cp);
			ps.setDouble(6, sp);
			ps.setString(7, "PENDING");
			ps.setInt(8, this.count);
			ps.executeUpdate();
			conn.close();
			return count;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
