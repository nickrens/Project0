package dev.rens.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnection {

private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			
			if(conn == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
//				String endpoint = "database-1.cl4pmwb5pwnv.us-east-1.rds.amazonaws.com";
//				String url = "jdbc:oracle:thin:@" + endpoint + ":1521:ORCL";
//				String username = "nickrens";
//				String password = "password";
				
				Properties props = new Properties();
				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile().replaceAll("%20", " "));
				props.load(input);				
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				
				conn = DriverManager.getConnection(url, username, password);
				return conn;
			}else {
				return conn;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static void main(String[] args) {
		Connection c = getConnection();
		System.out.println(c);
	}
}
