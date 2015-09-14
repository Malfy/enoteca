package DAOOnline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.JarException;

import model.Connessione;
import model.entrate;
import model.prodotto;
import model.uscite;
import dbconnection.ConnectionManager;
import dbconnection.ConnectionManagerOnline;

public class UsciteDAOOnline {

	public static boolean eliminaAllUsciteProdotto(prodotto prodotto,Connessione utente) throws JarException, SQLException, Exception{
		String DELETE="DELETE FROM uscite WHERE prodotto_idProdotto=?";
						


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
			}
			return res;
	}
	
	public static boolean eliminaUsciteProdotto(uscite uscite,Connessione utente) throws JarException, SQLException, Exception{
		String DELETE="DELETE FROM uscite WHERE prodotto_idProdotto=? AND giorno_uscite=?";
						


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
				System.out.println(e.getMessage().toString());
				throw e;			
			}finally{
				ResultSet rs = null;
				ConnectionManager.closeRes(connessione, rs, ps);
			}
			return res;
	}	
	
	
	
	public static void setUscita(uscite uscita,Connessione utente) throws JarException, SQLException, Exception{
		String insert="INSERT INTO uscite (Prodotto_idProdotto, Quantita_uscite, giorno_uscite) VALUES (?, ?, ?)";
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setInt(1, uscita.getIdProdotto());
			ps.setInt(2, uscita.getQuantita());
			ps.setDate(3,  uscita.getData());
			ps.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
	}
	
	
	public static boolean controlloData(uscite controllo,Connessione utente) throws JarException, Exception{
		String select="select * from uscite where Prodotto_idProdotto=? AND giorno_uscite=?";
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
	
	
	public static void aggiornaUscite(uscite entrata,boolean verifica,Connessione utente) throws JarException, SQLException, Exception{
		String UPDATE;

		
		if(verifica){
			UPDATE="UPDATE uscite SET Quantita_Uscite=(Quantita_Uscite+?) WHERE Prodotto_idProdotto=? AND giorno_uscite=?";
		}
		else{
			UPDATE="UPDATE uscite SET Quantita_Uscite=? WHERE Prodotto_idProdotto=? AND giorno_uscite=?";
		}
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
			ps.setInt(2, entrata.getIdProdotto());
			ps.setInt(1, entrata.getQuantita());
			ps.setDate(3,  entrata.getData());
			ps.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
	}
	
	public static uscite getUsciteSommaQuantita(uscite ent,Connessione utente) throws Exception{
		
		entrate lista=new entrate();
		Statement stmt =null;
		ResultSet rs= null;
		uscite rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT idUscite,Prodotto_idProdotto,sum(Quantita_Uscite),Giorno_Uscite" +
					"FROM enoteca.uscite where giorno_Uscite=?";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			System.out.println(ent.getData());
			ps.setDate(1, ent.getData());
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			rs.next();
			rg=new uscite();
			rg.setId(rs.getInt("idEntrate"));
			rg.setIdProdotto(rs.getInt("Prodotto_idProdotto"));
			rg.setData(rs.getDate("giorno_entrate"));
			rg.setQuantita(rs.getInt("sum(Quantita_Uscite)"));
			connessione.close();
			
		}
			catch (Exception e) {
				System.out.println(e.getMessage());
				
				// TODO: handle exception
			}
		finally{
			
		}
		return rg;
	}
	
	
	public static ArrayList<uscite> getAllUscite(Connessione utente) throws Exception{
		ArrayList<uscite> lista=new ArrayList<uscite>();
		Statement stmt =null;
		ResultSet rs= null;
		uscite rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM uscite ORDER BY giorno_uscite";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			while(rs.next()){
				rg=new uscite();
				rg.setId(rs.getInt("idUscite"));
				rg.setIdProdotto(rs.getInt("Prodotto_idProdotto"));
				rg.setData(rs.getDate("giorno_Uscite"));
				rg.setQuantita(rs.getInt("Quantita_Uscite"));
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}		
		return lista;
	}
	
	
	
	
	
	public static ArrayList<uscite> getUscite(int id,Connessione utente) throws Exception{
		ArrayList<uscite> lista=new ArrayList<uscite>();
		Statement stmt =null;
		ResultSet rs= null;
		uscite rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM uscite WHERE Prodotto_idProdotto=?";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			ps.setInt(1, id);
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			while(rs.next()){
				rg=new uscite();
				rg.setId(rs.getInt("idUscite"));
				rg.setIdProdotto(rs.getInt("Prodotto_idProdotto"));
				rg.setData(rs.getDate("giorno_Uscite"));
				rg.setQuantita(rs.getInt("Quantita_Uscite"));
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}		
		return lista;
	}

}
