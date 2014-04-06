import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.mysql.jdbc.*;


public class OrderDAO {
	static Connection ConnectionDB () throws Exception {
		String URL = "jdbc:mysql://localhost:3306/restaurant";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection connection = DriverManager.getConnection(URL, "root", "wudiwud");
		return connection;
	}
	
	public static void create (Order order, int id) {
		try {
			Connection connection = ConnectionDB();
			
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT `id` "
					+ "FROM `customers` "
					+ "WHERE `name` = ? "
			);
			st.setString(1, order.getCustomer().getName());
			
			ResultSet res = st.executeQuery();
			res.next();
			int idCustomer = res.getInt(1);
			
			st = connection.prepareStatement(""
					+ "INSERT INTO `orders`(`id`, `customer`, `cost`) "
					+ "VALUES (?, ?, ?)"
			);
			
			st.setInt(1, id);			
			st.setInt(2, idCustomer);
			st.setInt(3, order.getCost());
			st.executeUpdate();
			
			st = connection.prepareStatement(""
					+ "INSERT INTO `order'sdishes` (`dish`, `order`) "
					+ "VALUES (?, ?)"
			);
			
			PreparedStatement st2 = connection.prepareStatement(""
					+ "SELECT `id` "
					+ "FROM `dishes` "
					+ "WHERE `name` = ?"
			);			
			
			for (Dish var : order.getDishes()) {
				st2.setString(1, var.getName());
				res = st2.executeQuery();
				res.next();
				st.setInt(1, res.getInt(1));
				st.setInt(2, id);
				st.executeUpdate();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Order read (int id) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT `name`, Cus.`cost` "
					+ "FROM `customers` "
					+ "INNER JOIN "
					+ "(SELECT `customer`, `cost` "
					+ "FROM `orders` "
					+ "WHERE `id` = ? ) AS Cus "
					+ "ON Cus.`customer` = `id` "
			);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			result.next();
			Order order = new Order();
			order.setCustomer(CustomerDAO.read(result.getString(1)));
			order.setCost(result.getInt(2));			
			order.setId(id);
			
			st = connection.prepareStatement(""
					+ "SELECT A.`name`, C.`name` "
					+ "FROM `categories` AS C "
					+ "INNER JOIN "
					+ "(SELECT D.`name`, D.`category` "
					+ "FROM "
					+ "(SELECT `dish`, `order` "
					+ "FROM `order'sdishes` "
					+ "WHERE `order` = ?) AS OD "
					+ "INNER JOIN "
					+ "(SELECT `id`, `name`, `category`"
					+ "FROM `dishes`) AS D "
					+ "ON OD.`dish` = D.`id`) AS A "
					+ "ON C.`id` = A.`category` "
			);
			st.setInt(1, id);
			
			result = st.executeQuery();
			
			List<Dish> d = new ArrayList<Dish>();
			while (result.next()) {
				d.add(DishDAO.read(result.getString(1), result.getString(2)));
			}
			
			order.setDishes(d);
			
			return order;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Deprecated
	public static void update () {
		//if will be needed
	}
	
	public static void delete (Order order) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "DELETE FROM `orders` "
					+ "WHERE `id` = ?"
			);
			st.setInt(1, order.getId());
			st.executeUpdate();
			
			st = connection.prepareStatement(""
					+ "DELETE FROM `order'sdishes` "
					+ "WHERE `order` = ?"
			);
			
			st.setInt(1, order.getId());
			st.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
