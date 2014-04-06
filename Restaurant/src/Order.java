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

public class Order {
	private List<Dish> dishes;
	private int id;
	private int cost;
	private Customer customer;
	{
		dishes = new ArrayList<Dish>();
	}
	
	public List<Dish> getDishes() {
		return dishes;
	}
	
	public void setDishes (List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public int getId () {
		return this.id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getCost () {
		return this.cost;
	}
	
	public void setCost (int cost) {
		this.cost = cost;
	}
	
	public Customer getCustomer () {
		return this.customer;
	}
	
	public void setCustomer (Customer customer) {
		this.customer = customer;
	}
}
