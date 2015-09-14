package DAOOnline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.jar.JarException;

import javax.swing.JOptionPane;

import model.Connessione;
import model.regione;
import dbconnection.ConnectionManager;
import dbconnection.ConnectionManagerOnline;

public class TestConnessioneOnline {
	
	public static boolean test(Connessione utente) throws  Exception{
		boolean ritorno=false;
	
		try{
			Connection connessione = ConnectionManagerOnline.getConnectionTest(utente.getLink(),
					utente.getNome(),utente.getPassword());
		
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
