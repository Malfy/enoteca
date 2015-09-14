package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.jar.JarException;

import model.prodotto;
import dbconnection.ConnectionManager;

public class UtenteDb {
	
	public static boolean eliminaAllUsciteProdotto(String nome,String password) throws JarException, SQLException, Exception{
		String DELETE="CREATE USER '"+nome+"'@'%' IDENTIFIED BY '"+password+"';"+
				"GRANT USAGE ON * . * TO '"+nome+"'@'%' IDENTIFIED BY '"+password+"' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;"
				+"GRANT ALL PRIVILEGES ON `enoteca` . * TO 'prova'@'%';";
						


			PreparedStatement ps=null;
			Connection connessione=ConnectionManager.getConnection();
				boolean res=false;
			try {
				connessione.setAutoCommit(false);
				ps=connessione.prepareStatement(DELETE);
			
				
				if (ps.executeUpdate()>0){
					res=true;
				connessione.commit();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println(e.getMessage().toString());
				throw e;			
			}finally{
				ResultSet rs = null;
				ConnectionManager.closeRes(connessione, rs, ps);
			}
			return res;
	}

}
