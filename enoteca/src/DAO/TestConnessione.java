package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.jar.JarException;

import javax.swing.JOptionPane;

import model.regione;
import dbconnection.ConnectionManager;

public class TestConnessione {
	
	public static boolean test() throws  Exception{
		boolean ritorno=false;
	
		try{
			Connection connessione = ConnectionManager.getConnection();
		
			connessione.close();
		}
		catch (Exception e) {
			ritorno=true;
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally{
		}
		
		
		return ritorno;
	}

}
