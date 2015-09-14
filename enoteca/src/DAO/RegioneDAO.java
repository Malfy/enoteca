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
import dbconnection.ConnectionManager;

public class RegioneDAO {
	
	public static boolean Cancella(int id) throws Exception{
		String sql="DELETE FROM regione WHERE idRegione=?";
		PreparedStatement ps=null;
		Connection connessione=ConnectionManager.getConnection();
			boolean res=false;
		try {
			connessione.setAutoCommit(false);
//			if(CasaDAO.controlloRegione(id)!=0){
				ps=connessione.prepareStatement(sql);
				ps.setInt(1,id);
				ps.executeUpdate();
				connessione.commit();
				res=true;
//			}
		}
		catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "<html>Evidentemente hai assegnato a questa regione uno o piu produttori<BR/>" +
												" Vedi su produttori quale regione hai associato!</html>");
			throw e;
		
		}
		
		
		return res;
	}
	
	
	public static int[] getIdCasa(int idR){
		int[] idC=new int[100] ;
		String select="SELECT idCasa FROM casa Where Regione_idRegione= '"+idR+"'";
		PreparedStatement ps=null;
		Statement stmt =null;
		ResultSet rs= null;
		Connection connessione;
		
		try {
			connessione = ConnectionManager.getConnection();
			ps=(PreparedStatement) connessione.prepareStatement(select);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(select);
			int a=0;
			while(rs.next()){
				idC[a]=rs.getInt("idCasa");
				a++;
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return idC;
	}
	
	
	public static regione getRegione(String casa) throws Exception{
		regione regione=new regione();
		Statement stmt =null;
		ResultSet rs= null;
		regione rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM regione where idRegione = (select regione_idregione from casa where nome='"+casa+"')";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			
			rs.next();
				rg=new regione();
				rg.setId(rs.getInt("idRegione"));
				rg.setNome(rs.getString("Nome"));				
				regione=rg;
			connessione.close();
		}
		finally{
			
		}
		
		return regione;
	}
	
	
	public static regione getCasaRegione(int id) throws Exception{
		regione regione=new regione();
		Statement stmt =null;
		ResultSet rs= null;
		regione rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM regione WHERE idRegione="+id;
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			
			rs.next();
				rg=new regione();
				rg.setId(rs.getInt("idRegione"));
				rg.setNome(rs.getString("Nome"));				
				regione=rg;
			connessione.close();
		}
		finally{
			
		}
		
		return regione;
	}
	public static int getId(String nome) throws Exception{
		int id;
		Statement stmt =null;
		ResultSet rs= null;
		
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		if(nome.equals("Nessuna selezione"))
			return 0;
		try{
			String sql="SELECT * FROM regione WHERE nome='"+nome+"'";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			rs.next();
			id=rs.getInt("idRegione");
			connessione.close();
		}
		finally{			
		}
		return id;
	}
	
	
	public static boolean modRegione(regione regione) throws JarException, SQLException, Exception{
		String UPDATE;
		boolean ritorno=true;
		UPDATE="UPDATE regione SET Nome=? WHERE idRegione=?";

		Connection connessione = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
			ps.setInt(2, regione.getId());
			ps.setString(1, regione.getNome());
			ritorno=ps.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return ritorno;
	}
	
	
	
	public static ArrayList<regione> getRegioni() throws Exception{
		ArrayList<regione> lista=new ArrayList<regione>();
		Statement stmt =null;
		ResultSet rs= null;
		regione rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM regione ORDER BY nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new regione();
				rg.setId(rs.getInt("idRegione"));
				rg.setNome(rs.getString("Nome"));				
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	public static boolean inserisciRegione(String regione) throws Exception, Exception{
		PreparedStatement ps=null;
		boolean res= false;
					//INSERT INTO `enoteca`.`regione` (`Nome`) VALUES ('abruzzo');
		String insert="INSERT INTO REGIONE (Nome) VALUES (?);";
		Connection connessione = ConnectionManager.getConnection();
		try {
			ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setString(1, regione);
			ArrayList<regione> regioni =new ArrayList<regione>();
			regioni=RegioneDAO.getRegioni();
			boolean test = true;
			for(int a=0;a<regioni.size();a++){
				if(regione.equals(regioni.get(a).getNome())){
					test=false;		
				}			
							
			}
			if(test){
				if(ps.executeUpdate()>0){
					res=true;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return res;
	}
	
}
