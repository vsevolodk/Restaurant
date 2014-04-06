import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.*;


public class CustomerDAO {
	private static Connection ConnectionDB () throws Exception {
		String URL = "jdbc:mysql://localhost:3306/restaurant";
		Class.forName("com.mysql.jdbc.Driver").newInstance();	
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection connection = DriverManager.getConnection(URL, "root", "wudiwud");
		return connection;
	}
	
	public static void create (Customer customer) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "INSERT INTO `customers` (`name`, `password`) "
					+ "VALUES (?, ?)"
			);
			st.setString(1, customer.getName());
			st.setString(2, customer.getPassword());
			
			st.executeUpdate();
			
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Customer read (String name) {
		try {
			Customer customer;
		
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT `name`, `password` "
					+ "FROM `customers` "
					+ "WHERE `name` = ?"
			);
			st.setString(1, name);
			ResultSet res = st.executeQuery();
			res.next();
			customer = new Customer(res.getString(1), res.getString(2));
			return customer;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void update (Customer customer, String name, String pwd) throws Exception {
		
		Connection connection = ConnectionDB();
		PreparedStatement st = connection.prepareStatement(""
				+ "UPDATE `customers` "
				+ "SET `name`= ?, `password`= ? "
				+ "WHERE `name` = ?"
		);
		st.setString(1, name);
		st.setString(2, pwd);
		st.setString(3, customer.getName());
		
		st.executeUpdate();
	}
	
	public static void delete (Customer customer) throws Exception {		
		Connection connection = ConnectionDB();
		PreparedStatement st = connection.prepareStatement(""
				+ "DELETE FROM `customers` "
				+ "WHERE `name` = ?"
		);
		st.setString(1, customer.getName());
		
		st.executeUpdate();
	}
	
}
