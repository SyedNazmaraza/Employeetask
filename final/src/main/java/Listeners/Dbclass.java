package Listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbclass {
	
	private String dburl;
	private String user;
	private String pass;
	private Connection c;
	
	public Dbclass(String dburl, String user, String pass) {
		super();
		this.dburl = dburl;
		this.user = user;
		this.pass = pass;
	}
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(dburl, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return this.c;
	}
	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
