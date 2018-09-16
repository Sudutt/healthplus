package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddVendor {
	Connection conn;
	int count;
	public AddVendor(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            String makeTableUsers = "create table if not exists vendors("+
				"	`name`	TEXT NOT NULL,\r\n" + 
				"	`address`	TEXT NOT NULL,\r\n" + 
				"	`phone_no`	TEXT NOT NULL,\r\n" + 
				"	`approval`	TEXT NOT NULL,\r\n"+
				"	`id`	INTEGER NOT NULL,\r\n" + 
				"	PRIMARY KEY(`id`)\r\n" + 
				");";
			PreparedStatement ps = conn.prepareStatement(makeTableUsers);
			ps.executeUpdate();
			String no= "select * from vendors";
			ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			this.count=0;
			while(rs.next())
				this.count=rs.getInt(5);
			this.count++;
			conn.close();
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int insertVendorData(String nm, String add, String phno) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            String no= "select * from vendors";
			PreparedStatement ps;
			ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("name")==nm)
					return -2;
			}
			//Now safe, inserting vendor
			String insertUser = "insert into vendors values(?,?,?,?,?) ";
			ps = conn.prepareStatement(insertUser);
			ps.setString(1, nm);
			ps.setString(2, add);
			ps.setString(3, phno);
			ps.setString(4, "PENDING");
			ps.setInt(5, this.count);
			ps.executeUpdate();
			conn.close();
			return count;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
