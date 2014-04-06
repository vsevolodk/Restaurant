import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CategoryDAO {
	private static Connection ConnectionDB () throws Exception {
		String URL = "jdbc:mysql://localhost:3306/restaurant";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection connection = DriverManager.getConnection(URL, "root", "wudiwud");
		return connection;
	}
	
	public static void create (Category category) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "INSERT INTO `categories`(`name`) "
					+ "VALUES (?)"
			);
			st.setString(1, category.getName());
			st.executeUpdate();
			
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Category read (String whatName) {
		Category category = null;
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "SELECT `name` "
					+ "FROM `categories` "
					+ "WHERE `name` = ?"
			);
			st.setString(1, whatName);
			ResultSet result = st.executeQuery();
			result.next();
			category = new Category(result.getString(1));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public static void update (Category category, String name) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "UPDATE `categories` SET `name`= ? WHERE `name` = ?"
			);
			st.setString(1, name);
			st.setString(2, category.getName());
			st.executeUpdate();
			
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delete (Category category) {
		try {
			Connection connection = ConnectionDB();
			PreparedStatement st = connection.prepareStatement(""
					+ "DELETE FROM `categories` WHERE `name` = ?"
			);
			st.setString(1, category.getName());
			st.executeUpdate();
			
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
