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
 * Class of categories of dishes
 * @author Vsevolod
 * @version 1.0
 *
 */
public class Category {
	
	/**
	 * Field name contains name of object of the class Category
	 */
	private String name;
	
	/**
	 * 
	 * @param name String which will be an object name 
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return String Name of object of the class Category
	 */
	public String getName () {
		return this.name;
	}
	
	/**
	 * The constructor without parameters
	 */
	public Category () {
		super();
	}
	
	/**
	 * The constructor with parameters
	 * @param name Name of object
	 */
	public Category (String name) {
		this.name = name;
	}
	
	/**
	 * @return Name of object
	 */
	public String toString () {
		return this.name;
	}
	
	/**
	 * Save object in "/src/save/categories/name.txt"
	 */
	public String toXML() {
		XStream xstream = new XStream();
		return xstream.toXML(this);
	}
	
	static Category fromXml (String s) {
		Category temp = null;
		try {
			XStream xstream = new XStream();
			temp = (Category) xstream.fromXML(s);
		}
		catch(ClassCastException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * Return true, if and only if, then {@code o.name == this.name}
	 */
	public boolean equals (Object o) {
		if (o != null && (o instanceof Category)) {
			return ((Category) o).name.equals(this.name);
		}
		else {
			return false;
		}
	}
	
	public int hashCode () {
		return name.hashCode();
	}
}