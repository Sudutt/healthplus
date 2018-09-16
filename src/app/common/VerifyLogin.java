package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class VerifyLogin {
	String userid, userpassword;
	public VerifyLogin(String id, String pass) {
		userid=id;
		userpassword=pass;
	}
	 
	public boolean check() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
            if(conn==null) {
				System.out.println("Nahi degi");
				throw new NullPointerException();
			}
			String getUser = "select * from users"
					+ " where (username=? and upassword=?)";
			PreparedStatement ps = conn.prepareStatement(getUser);
			ps.setString(1, this.userid);
			ps.setString(2, this.userpassword);
			ResultSet rs = ps.executeQuery();
			if(rs.next()==false) {
				conn.close();
				return false;
			}
			else {
				conn.close();
				return true;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.out.println("Error in check method: "+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
