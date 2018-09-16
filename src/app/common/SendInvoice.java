package app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SendInvoice {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	int txnIdNo;
	Object[][] data;
	int data_count;
	public SendInvoice() {
		data = new Object[100][6];
		data_count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/database1?user=root&password=root");
			String makeTableSales = "create table if not exists sales("+
					"	`txn_id`	INTEGER NOT NULL,\r\n" + 
					"	`dt`	TEXT NOT NULL,\r\n" + 
					"	`tm`	TEXT NOT NULL,\r\n" + 
					"	`md`	TEXT NOT NULL,\r\n" + 
					"	`nm`	TEXT NOT NULL,\r\n" + 
					"	`amt`	REAL NOT NULL,\r\n" +
					"	`bal`	REAL NOT NULL,\r\n"+ 
					"	PRIMARY KEY(`txn_id`)\r\n" + 
					");";
			ps = conn.prepareStatement(makeTableSales);
			ps.executeUpdate();
			String no= "select * from sales";
			ps = conn.prepareStatement(no);
			rs = ps.executeQuery();
			this.txnIdNo=0;
			while(rs.next()) {
				this.txnIdNo++;	
			}
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addMedWithQty(int med_id, int qty) {
		try {
			String initQ= "SELECT * FROM meds WHERE id=?";
			ps = conn.prepareStatement(initQ);
			ps.setInt(1, med_id);
			rs = ps.executeQuery();
			rs.next();
			int initQty = rs.getInt(4);

			String forStock = "UPDATE meds SET qty=? WHERE id=?";
			ps = conn.prepareStatement(forStock);
			ps.setInt(1,(initQty+qty));
			ps.setInt(2, med_id);
			ps.executeUpdate();

			data[data_count][0]=data_count+1;
			data[data_count][1]=rs.getString(2);
			data[data_count][2]=rs.getString(3);
			data[data_count][3]=rs.getDouble(5);
			data[data_count][4]=qty;
			data[data_count][5]=Double.parseDouble(data[data_count][3].toString())*qty;
			data_count++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet checkMedInStock(String gname, String tname, int med_id) {
		try {
			String possibleMeds = "SELECT * FROM meds "
					+ "WHERE (id=? OR generic_name=? OR trade_name=?)";
			ps = conn.prepareStatement(possibleMeds);
			ps.setString(2, gname);
			ps.setString(3, tname);
			ps.setInt(1, med_id);
			rs = ps.executeQuery();
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void finishBill(String cname) {
		try {
			//Now for the sales update
			double initBal;
			if(txnIdNo!=0) {
				String initB= "SELECT bal FROM sales WHERE txn_id=?";
				ps = conn.prepareStatement(initB);
				ps.setInt(1, txnIdNo-1);
				rs = ps.executeQuery();
				rs.next();
				initBal = rs.getInt(1);
			}
			else {
				initBal = 0;
			}

			String forSales = "INSERT INTO sales VALUES(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(forSales);
			ps.setInt(1, this.txnIdNo);
			ps.setString(2, java.time.LocalDate.now().toString());
			ps.setString(3, java.time.LocalTime.now().toString());
			ps.setString(4, "PURCHASE");
			ps.setString(5, cname);
			double sale = fullAmount();
			ps.setDouble(6, sale);
			ps.setDouble(7, initBal-sale);
			ps.executeUpdate();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double fullAmount() {
		if(data_count==0)
			return 0.0;
		else {
			double amt=0.0;
			for(int i=0; i<data_count; i++)
				amt+=Double.parseDouble(data[i][5].toString());
			return amt;
		}

	}

	public String[] getColumns() {
		return new String[] {"S.No.","Generic Name","Trade Name","Cost per Item","Quantity","Total Cost"};
	}

	public Object[][] getData() {
		return data;
	}
}