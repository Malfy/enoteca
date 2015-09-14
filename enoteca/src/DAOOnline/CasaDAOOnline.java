package DAOOnline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JOptionPane;

import model.Connessione;
import model.casa;
import model.regione;
import dbconnection.ConnectionManager;
import dbconnection.ConnectionManagerOnline;

public class CasaDAOOnline {
	
	
	public static ArrayList<casa> getCasaRegione(int id,Connessione utente) throws Exception{
		ArrayList<casa> lista=new ArrayList<casa>();
		Statement stmt =null;
		ResultSet rs= null;
		casa rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM casa Where regione_idRegione='"+id+"' ORDER BY Nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new casa();
				rg.setId(rs.getInt("idCasa"));
				rg.setNome(rs.getString("Nome"));
				rg.setIdRegione(rs.getInt("Regione_idRegione"));
				rg.setRegione(RegioneDAOOnline.getCasaRegione(rs.getInt("Regione_idRegione"),utente));
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	public static ArrayList<casa> getCasa(Connessione utente) throws Exception{
		ArrayList<casa> lista=new ArrayList<casa>();
		Statement stmt =null;
		ResultSet rs= null;
		casa rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM casa ORDER BY Nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new casa();
				rg.setId(rs.getInt("idCasa"));
				rg.setNome(rs.getString("Nome"));
				rg.setIdRegione(rs.getInt("Regione_idRegione"));
				rg.setRegione(RegioneDAOOnline.getCasaRegione(rs.getInt("Regione_idRegione"),utente));
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	public static int controlloRegione(int id,Connessione utente) throws Exception{
		Statement stmt =null;
		ResultSet rs= null;
		int r=0;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
	
		try{
			String sql="SELECT * FROM casa WHERE regione_idregione='"+id+"' ORDER BY Nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			rs.next();
			r=rs.getInt("idCasa");
			connessione.close();
		}
		finally{			
		}
		return id;
	}
	
	
	public static int getId(String casa,Connessione utente) throws Exception{
		int id;
		Statement stmt =null;
		ResultSet rs= null;
		
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		if(casa.equals("Nessuna selezione"))
			return 0;
		try{
			String sql="SELECT * FROM casa WHERE nome='"+casa+"'";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			rs.next();
			id=rs.getInt("idCasa");
			connessione.close();
		}
		finally{			
		}
		return id;
	}
	
	
	
	
	
	public static casa getCasaProd(int id,Connessione utente) throws Exception{
		
		casa lista=new casa();
		Statement stmt =null;
		ResultSet rs= null;
		casa rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM casa where ?=idCasa ORDER BY Nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			ps.setInt(1, id);			
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			while(rs.next()){
				rg=new casa();
				rg.setId(rs.getInt("idCasa"));
				rg.setNome(rs.getString("Nome"));
				rg.setIdRegione(rs.getInt("Regione_idRegione"));
				rg.setRegione(RegioneDAOOnline.getCasaRegione(rs.getInt("Regione_idRegione"),utente));
				lista=rg;
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	public static boolean modCasa(casa casa,Connessione utente) throws JarException, SQLException, Exception{
		String UPDATE;
		
		UPDATE="UPDATE casa SET Regione_idRegione=?, Nome=? WHERE idCasa=?";

		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
			ps.setInt(1, casa.getIdRegione());
			ps.setString(2, casa.getNome());
			ps.setInt(3, casa.getId());
			ps.execute();
			System.out.println("idR "+casa.getIdRegione()+" id "+casa.getId()+" nome "+casa.getNome()+"  mmmhmmm");
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return true;
	}
	

}
