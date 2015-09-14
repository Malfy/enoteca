package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.jar.JarException;

import model.Connessione;

public class ConnectionManagerOnline {
	private static ResourceBundle props = ResourceBundle.getBundle("db");
	
	
	 public static synchronized Connection getConnectionTest(String url,String login,String pass) throws Exception,JarException,SQLException {
	    	Connection conn=null;
	    	String driver=props.getString("connection.direct.driver");
	    	try {
	    		Class.forName(driver);
	    		conn=(Connection) DriverManager.getConnection(url, login, pass);
	    	}
	    	catch (SQLException e) {
	    		
				throw new RuntimeException(e);
			}
	    	return conn;
	    }
	
	
	 public static synchronized Connection getConnection(Connessione utente) throws Exception,JarException,SQLException {
	    	Connection conn=null;
	    	String driver=props.getString("connection.direct.driver");

	    	try {
	    		Class.forName(driver);
	    		conn=(Connection) DriverManager.getConnection(utente.getLink(), utente.getNome(), utente.getPassword());
	    	}
	    	catch (SQLException e) {
	    		
				throw new RuntimeException(e);
			}
	    	return conn;
	    }
	 
	 public static void closeRes(Connection connection, ResultSet rs, Statement st) {
	    	try {
	    		if (connection!=null)
	    			connection.close();
	    		if (rs!=null)
	    			rs.close();
	    		if (st!=null)
	    			st.close();
	    	}
	    	catch (Exception e) {
	    		System.out.println(e);
			}
	    }

	public static void commit(ConnectionManager conn) {
		// TODO Auto-generated method stub
		
	}

	public static void rollback(ConnectionManager conn) {
		// TODO Auto-generated method stub
		
	}
	
}
