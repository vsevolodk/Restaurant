import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.mysql.jdbc.*;

public class PaymentDAO {
	static Connection connectionDB () throws Exception {
		String URL = "jdbc:mysql://localhost:3306/restaurant";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection connection = DriverManager.getConnection(URL, "root", "wudiwud");
		return connection;
	}
	
	public static Payment read (int id) {
		try {
			Connection connection = connectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT C.`name`, A.`order`, A.`cost` "
					+ "(SELECT P.`customer`, P.`order`, P.`cost` "
					+ "FROM `payments` AS P "
					+ "WHERE `id` = ?) AS A "
					+ "INNER JOIN "
					+ "(SELECT C.`id`, C.`name` "
					+ "FROM `customers`) AS C "
					+ "ON C.`id` = A.`customer` "
			);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			res.next();
			
			Payment payment = new Payment();
			payment.setId(id);
			payment.setCustomer(CustomerDAO.read(res.getString(1)));
			payment.setOrder(OrderDAO.read(res.getInt(2)));
			payment.setCost(res.getInt(3));
			
			return payment;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void create (Payment payment) {
		try {
			Connection connection = connectionDB();
			
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT `id` "
					+ "FROM `customers` "
					+ "WHERE `name` = ? "					
			);
			st.setString(1, payment.getCustomer().getName());
			ResultSet res = st.executeQuery();
			res.next();
			int idCustomer = res.getInt(1);
			
			st = connection.prepareStatement(""
					+ "INSERT INTO `payments`(`id`, `customer`, `order`, `cost`) "
					+ "VALUES (?, ?, ?, ?)"					
			);
			st.setInt(1, payment.getId());
			st.setInt(2, idCustomer);
			st.setInt(3, payment.getOrder().getId());
			st.setInt(4, payment.getCost());
			st.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delete (Payment payment) {
		try {
			Connection connection = connectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "DELETE FROM `payments` "
					+ "WHERE `id` = ?"					
			);
			st.setInt(1, payment.getId());
			st.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Deprecated
	void update () {
		//if will be needed
	}
}
