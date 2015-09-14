package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JOptionPane;

import model.casa;
import model.regione;
import model.tipologia;
import model.uscite;
import dbconnection.ConnectionManager;

public class TipologiaDAO {
	public static ArrayList<tipologia> getListaTipo() throws Exception{
		
		ArrayList<tipologia> lista=new ArrayList<tipologia>();
		Statement stmt =null;
		ResultSet rs= null;
		tipologia rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM tipologia ORDER BY tipo";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new tipologia();
				rg.setId(rs.getInt("idTipologia"));
				rg.setTipo(rs.getString("Tipo"));
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	

	public static void modificaTipologia(tipologia tipo) throws JarException, SQLException, Exception{
		String UPDATE;
		
			UPDATE="UPDATE tipologia SET Tipo=?	WHERE idTipologia=?";
	
		Connection connessione = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
			ps.setInt(2, tipo.getId());
			ps.setString(1, tipo.getTipo());
			ps.execute();
		
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
	}
	
	
	public static boolean Cancella(int id) throws Exception{
		String sql="DELETE FROM tipologia WHERE idTipologia=?";
		PreparedStatement ps=null;
		Connection connessione=ConnectionManager.getConnection();
			boolean res=false;
		try {
			connessione.setAutoCommit(false);
				if(ProdottoDAO.controlloTipologia(id)){
					System.out.println("weeee");
					ps=connessione.prepareStatement(sql);
					ps.setInt(1,id);
					ps.executeUpdate();
					connessione.commit();
					res=true;
				}
		}
		catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, e.getMessage());
			throw e;
		
		}
		
		
		return res;
	}
	
	
	
	public static int getId(String tipo) throws Exception{
		int id;
		Statement stmt =null;
		ResultSet rs= null;
		
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		if(tipo.equals("Nessuna selezione"))
			return 0;
		try{
			String sql="SELECT * FROM tipologia WHERE tipo='"+tipo+"'";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			rs.next();
			id=rs.getInt("idTipologia");
			connessione.close();
		}
		finally{			
		}
		return id;
	}
	
	public static boolean inserisciTipologia(String nome) throws  Exception{
		PreparedStatement ps=null;
		boolean res= true;
		boolean test=true;
					//INSERT INTO `enoteca`.`regione` (`Nome`) VALUES ('abruzzo');
		String insert="INSERT INTO tipologia (Tipo) VALUES (?);";
		Connection connessione = ConnectionManager.getConnection();
		try {
			ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setString(1, nome);
			ArrayList<tipologia> listaTipologia =TipologiaDAO.getListaTipo();
			for(tipologia e:listaTipologia){
				if(nome.equals(e.getTipo())){
					test=false;
				}
			}
			if(!test)
				res=false;
			else
			 ps.execute();			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return res;
	}
	
	public static tipologia getUscite(int id) throws Exception{
		tipologia tipo=new tipologia();
		Statement stmt =null;
		ResultSet rs= null;
		tipologia rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM tipologia WHERE idTipologia=?";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			ps.setInt(1, id);
			stmt=connessione.createStatement();
			rs=ps.executeQuery();
			if(rs.next()){
				rg=new tipologia();
				rg.setId(rs.getInt("idTipologia"));
				rg.setTipo(rs.getString("tipo"));
				tipo=rg;	
				connessione.close();	
			}
	}catch (Exception e) {
		System.out.println(e);
	}
		return tipo;
	}
		

}
