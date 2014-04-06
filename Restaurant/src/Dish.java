import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.*;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import com.thoughtworks.xstream.XStream;

/**
 * Dish
 * @author Vsevolod
 * @version 1.0
 *
 */
public class Dish {
	
	/**
	 * Name of the object
	 */
	private String name;
	private Category category;
	
	/**
	 * Set value of field name
	 * @param name
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Return value of field name
	 * @return String
	 */
	public String getName () {
		return this.name;
	}
	
	/**
	 * Category of object of dish
	 */
	
	/**
	 * Set value of field type
	 * @param type
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * Return value of field type
	 * @return Category
	 */
	public Category getCategory() {
		return this.category;
	}
	
	public Dish () {
		super();
	}
	
	public Dish (String name, Category category) {
		this.name = name;
		this.category = category;
	}
	
	public String toString () {
		return this.name;
	}
	
	public int hashCode () {
		return name.hashCode();
	}
	
	public boolean equals (Dish dish, Object o) {
		if (o != null && (o instanceof Dish)) {
			return ((Dish) o).getName().equals(dish.getName());
		}
		else {
			return false;
		}
	}
}
