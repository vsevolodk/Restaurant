import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.*;


public class DishDAO {
	private static Connection ConnectionDB () throws Exception {
		String URL = "jdbc:mysql://localhost:3306/restaurant";
		Class.forName("com.mysql.jdbc.Driver").newInstance();		
		Connection connection = DriverManager.getConnection(URL, "root", "wudiwud");
		return connection;
	}
	
	public static void create (Dish dish) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT `id` "
					+ "FROM `categories` "
					+ "WHERE `name` = ?"
			);
			st.setString(1, dish.getCategory().getName());
			
			ResultSet res = st.executeQuery();
			res.next();
			
			int id = res.getInt(1);
			
			st = connection.prepareStatement(""
					+ "INSERT INTO `dishes` (`category`, `name`) "
					+ "VALUES (?, ?)"
			);
			st.setInt(1, id);
			st.setString(2, dish.getName());
			st.executeUpdate();
			
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Dish read (String whatName, String whatCategoryName) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT D.`name` AS `dish`, C.`name` AS `category` "
					+ "FROM "
					+ "(SELECT `id`, `name` "
					+ "FROM `categories` "
					+ "WHERE `name` = ?) AS C "
					+ "INNER JOIN "
					+ "(SELECT `category`, `name` "
					+ "FROM `dishes` "
					+ "WHERE `name` = ?) AS D "
					+ "ON D.`category` = C.`id` "
			);
			
			st.setString(2, whatName);
			st.setString(1, whatCategoryName);
			ResultSet result = st.executeQuery();
			result.next();
			Dish dish = new Dish(result.getString(1), CategoryDAO.read(result.getString(2)));
			return dish;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void update (Dish dish, String name, Category category) {
		try {
			Connection connection = ConnectionDB();
			
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT C.`id` AS `category` "
					+ "FROM "
					+ "(SELECT `id`, `name` "
					+ "FROM `categories` "
					+ "WHERE `name` = ?) AS C "
					+ "INNER JOIN "
					+ "(SELECT `category`, `name` "
					+ "FROM `dishes` "
					+ "WHERE `name` = ?) AS D "
					+ "ON D.`category` = C.`id` "
			);
			st.setString(1, dish.getCategory().getName());
			st.setString(2, dish.getName());
			ResultSet res = st.executeQuery();
			res.next();
			int prevCategory = res.getInt(1);
			
			st = connection.prepareStatement(""
					+ "SELECT `id` "
					+ "FROM `categories` "
					+ "WHERE `name` = ? "
			);
			st.setString(1, category.getName());
			
			res = st.executeQuery();
			res.next();
			int newCategory = res.getInt(1);
			
			st = connection.prepareStatement(""
					+ "UPDATE `dishes` SET `name` = ?, `category` = ? "
					+ "WHERE `name` = ? AND `category` = ? "
			);
			st.setString(1, name);
			st.setInt(2, newCategory);
			st.setString(3, dish.getName());
			st.setInt(4, prevCategory);
			
			st.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delete (Dish dish) {
		try {
			Connection connection = ConnectionDB();
			
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT D.`id` "
					+ "FROM "
					+ "(SELECT `id` "
					+ "FROM `categories` "
					+ "WHERE `name` = ?) AS C "
					+ "INNER JOIN "
					+ "(SELECT `id`, `name`, `category` "
					+ "FROM `dishes`"
					+ "WHERE `name` = ?) AS D "
					+ "ON C.`id` = D.`category` "
			);
			st.setString(2, dish.getName());
			st.setString(1, dish.getCategory().getName());			
			
			ResultSet res = st.executeQuery();
			res.next();
			int id = res.getInt(1);
			
			st = connection.prepareStatement(""
					+ "DELETE FROM `dishes` "
					+ "WHERE `id` = ?"
			);			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
