package com.thread.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadLocalDemo {

	ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	
	private Connection getConnection(){
		Connection con = local.get();
		if(con==null){
			try {
				Class.forName("...");
				con = DriverManager.getConnection("");
				local.set(con);		
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return con;
	}
	
	public static void main(String[] args) {
		ThreadLocalDemo tld = new ThreadLocalDemo();
		tld.getConnection();
	}
	
}
