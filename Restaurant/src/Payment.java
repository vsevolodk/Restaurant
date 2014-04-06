import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.mysql.jdbc.*;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import com.thoughtworks.xstream.XStream;


public class Payment {
	private int id;
	private Customer customer;
	private Order order;
	private int cost;
	
	public int getId () {
		return this.id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public Customer getCustomer () {
		return this.customer;
	}
	
	public void setCustomer (Customer customer) {
		this.customer = customer;
	}
	
	public Order getOrder () {
		return this.order;
	}
	
	public void setOrder (Order order) {
		this.order = order;
	}
	
	public int getCost () {
		return this.cost;
	}
	
	public void setCost (int cost) {
		this.cost = cost;
	}
}
