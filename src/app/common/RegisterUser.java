package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterUser {
	Connection conn;
	int count;
	public RegisterUser() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            String makeTableUsers = "create table if not exists users("+
				"	`id`	INTEGER NOT NULL,\r\n" + 
				"	`type`	TEXT NOT NULL,\r\n" + 
				"	`firstName`	TEXT NOT NULL,\r\n" + 
				"	`lastName`	TEXT NOT NULL,\r\n" + 
				"	`username`	TEXT NOT NULL,\r\n" + 
				"	`upassword`	TEXT NOT NULL,\r\n" + 
				"	`approval`	TEXT NOT NULL,\r\n" + 
				"	`status`	TEXT NOT NULL,\r\n" +
				"	PRIMARY KEY(`id`)\r\n" + 
				");";
			PreparedStatement ps = conn.prepareStatement(makeTableUsers);
			ps.executeUpdate();
			String no= "select * from users";
			ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			this.count=0;
			while(rs.next())
				this.count = rs.getInt("id");
			conn.close();
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertUserData(String type, String fn, String ln, String un, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            String no= "select * from users";
			PreparedStatement ps = conn.prepareStatement(no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(un.equals(rs.getString("username")))
					return false;
			}
			//Now safe, inserting user
			String insertUser = "insert into users values(?,?,?,?,?,?,?,?) ";
			ps = conn.prepareStatement(insertUser);
			ps.setInt(1, this.count+1);
			ps.setString(2, type);
			ps.setString(3, fn);
			ps.setString(4, ln);
			ps.setString(5, un);
			ps.setString(6, pass);
			ps.setString(7, (type=="Manager")?"APPROVED":"PENDING");
			ps.setString(8, "OFFLINE");
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
