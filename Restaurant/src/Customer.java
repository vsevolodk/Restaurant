import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.*;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import com.thoughtworks.xstream.XStream;

public class Customer {
	private String name;
	private String password;
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public Customer () {
		super();
	}
	
	public Customer (String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public Customer (String name) {
		this.name = name;
	}
	
	public boolean equals (Object o) {
		if (o != null && (o instanceof Customer)) {
			return ((Customer) o).name.equals(this.name);
		}
		else {
			return false;
		}
	}
	
	public int hashCode () {
		return name.hashCode();
	}
}
