package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Hasta() {
		

	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);

	}

	public boolean register(String tcno, String password, String name) throws SQLException {

		int key = 0;
		int count = 0;
		String query = "INSERT INTO user  " + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT*FROM user WHERE tcno= '" + tcno + "'");

			while (rs.next()) {
				count++;
				Helper.showMsg("Bu T.C. Kimlik numarasýna ait baþka bir kayýt bulunmaktadýr.");

			}
			if (count >= 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = 1;
			}

		
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addRandevu(int doktor_id, int hasta_id, String doktor_name,String hasta_name, String randevuDate) throws SQLException {
		int key=0;
		
		String query = " INSERT INTO randevu "+ "(doktor_id, doktor_name, hasta_id, hasta_name, randevu_date)VALUES " + "(?,?,?,?,?)";
		
		try {
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setInt(1, doktor_id);
			preparedStatement.setString(2 , doktor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, randevuDate);
			preparedStatement.executeUpdate();
			key=1;
			}
		catch(SQLException e) {
		e.printStackTrace();
			
		}
		if(key==1) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
	
	
	
	
	public boolean updateWhourStatus(int doktor_id, String wdate) throws SQLException {

		int key = 0;

		String query = "UPDATE whour SET status =? WHERE doktor_id = ? AND wdate= ?";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "Pasif");
			preparedStatement.setInt(2, doktor_id);
			preparedStatement.setString(3, wdate);

			preparedStatement.executeUpdate();
			key = 1;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}

	}
}
