package DAOOnline;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.JarException;

import model.Connessione;
import model.casa;
import model.entrate;
import model.prodotto;
import model.uscite;
import dbconnection.ConnectionManager;
import dbconnection.ConnectionManagerOnline;

public class EntrateDAOOnline extends Exception {

	public static boolean eliminaAllEntrateProdotto(prodotto prodotto,Connessione utente) throws JarException, SQLException, Exception{
		String DELETE="DELETE FROM entrate WHERE prodotto_idProdotto=? ";
						


			PreparedStatement ps=null;
			Connection connessione=ConnectionManagerOnline.getConnection(utente);
				boolean res=false;
			try {
				connessione.setAutoCommit(false);
				ps=connessione.prepareStatement(DELETE);
				ps.setInt(1,prodotto.getIdProdotto());
				
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
//				chiudiStatement(ps);
			}
			return res;
	}
	
	public static void aggiornaEntrata(entrate entrata,boolean verifica,Connessione utente) throws JarException, SQLException, Exception{
		String UPDATE;

		
		if(verifica){
			UPDATE="UPDATE entrate SET Quantita_entrate=(Quantita_entrate+?) WHERE Prodotto_idProdotto=? AND giorno_entrate=?";
		}
		else{
			UPDATE="UPDATE entrate SET Quantita_entrate=? WHERE Prodotto_idProdotto=? AND giorno_entrate=?";
		}
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
			ps.setInt(2, entrata.getIdProdotto());
			ps.setInt(1, entrata.getQuantita());
			ps.setDate(3,  entrata.getData());
			ps.execute();
			
		} catch (Exception e) {
		}
		
	}
	public static boolean eliminaEntrateProdotto(entrate uscite,Connessione utente) throws JarException, SQLException, Exception{
		String DELETE="DELETE FROM entrate WHERE prodotto_idProdotto=? AND giorno_entrate=?";
						


			PreparedStatement ps=null;
			Connection connessione=ConnectionManagerOnline.getConnection(utente);
				boolean res=false;
			try {
				connessione.setAutoCommit(false);
				ps=connessione.prepareStatement(DELETE);
				ps.setInt(1,uscite.getIdProdotto());
				ps.setDate(2, uscite.getData());
				if (ps.executeUpdate()>0){
					res=true;
				connessione.commit();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				throw e;			
			}finally{
				ResultSet rs = null;
				ConnectionManager.closeRes(connessione, rs, ps);
			}
			return res;
	}	
	
	public static void setEntrata(entrate entrata,Connessione utente) throws JarException, SQLException, Exception{
		String insert="INSERT INTO entrate (Prodotto_idProdotto, Quantita_entrate, giorno_entrate) VALUES (?, ?, ?)";
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setInt(1, entrata.getIdProdotto());
			ps.setInt(2, entrata.getQuantita());
			ps.setDate(3,  entrata.getData());
			ps.execute();
			
		} catch (Exception e) {
		}
		
	}
	
	public static boolean controlloData(entrate controllo,Connessione utente) throws JarException, Exception{
		String select="select * from entrate where Prodotto_idProdotto=? AND giorno_entrate=?";
		boolean test=false;
		ResultSet rs=null;
		Connection connessione= ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps = connessione.prepareStatement(select);
		ps.setInt(1, controllo.getIdProdotto());
		ps.setDate(2, controllo.getData());
		rs = ps.executeQuery();
		while(rs.next()){
			test=true;				
		}
		
		return test;
	}

	public static entrate getEntrateSommaQuantita(entrate ent,Connessione utente) throws Exception{
		
		entrate lista=new entrate();
		Statement stmt =null;
		ResultSet rs= null;
		entrate rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT idEntrate,Prodotto_idProdotto,sum(Quantita_Entrate),Giorno_entrate" +
					"FROM entrate where giorno_Entrate=?";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			ps.setDate(1, ent.getData());
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			rs.next();
				rg=new entrate();
				rg.setId(rs.getInt("idEntrate"));
				rg.setIdProdotto(rs.getInt("Prodotto_idProdotto"));
				rg.setData(rs.getDate("giorno_entrate"));
				rg.setQuantita(rs.getInt("sum(Quantita_entrate)"));
			
			
			connessione.close();
		}
		finally{
			
		}
		return rg;
	}
	
	
	
	public static ArrayList<entrate> getAllEntrate(Connessione utente) throws Exception{
		
		ArrayList<entrate> lista=new ArrayList<entrate>();
		Statement stmt =null;
		ResultSet rs= null;
		entrate rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM entrate ORDER BY giorno_entrate";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			while(rs.next()){
				rg=new entrate();
				rg.setId(rs.getInt("idEntrate"));
				rg.setIdProdotto(rs.getInt("Prodotto_idProdotto"));
				rg.setData(rs.getDate("giorno_entrate"));
				rg.setQuantita(rs.getInt("Quantita_entrate"));
			
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		return lista;
	}
	
	
	public static ArrayList<entrate> getEntrate(int id,Connessione utente) throws Exception{
	
		ArrayList<entrate> lista=new ArrayList<entrate>();
		Statement stmt =null;
		ResultSet rs= null;
		entrate rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM entrate WHERE Prodotto_idProdotto=? ORDER BY giorno_entrate";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			ps.setInt(1, id);
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			while(rs.next()){
				rg=new entrate();
				rg.setId(rs.getInt("idEntrate"));
				rg.setIdProdotto(rs.getInt("Prodotto_idProdotto"));
				rg.setData(rs.getDate("giorno_entrate"));
				rg.setQuantita(rs.getInt("Quantita_entrate"));
			
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		return lista;
	}

}
