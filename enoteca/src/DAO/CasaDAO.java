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

public class CasaDAO {
	
	
	public static ArrayList<casa> getCasaRegione(int id) throws Exception{
		ArrayList<casa> lista=new ArrayList<casa>();
		Statement stmt =null;
		ResultSet rs= null;
		casa rg=null;
		Connection connessione = ConnectionManager.getConnection();
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
				rg.setRegione(RegioneDAO.getCasaRegione(rs.getInt("Regione_idRegione")));
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	public static ArrayList<casa> getCasa() throws Exception{
		ArrayList<casa> lista=new ArrayList<casa>();
		Statement stmt =null;
		ResultSet rs= null;
		casa rg=null;
		Connection connessione = ConnectionManager.getConnection();
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
				rg.setRegione(RegioneDAO.getCasaRegione(rs.getInt("Regione_idRegione")));
				lista.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	public static int controlloRegione(int id) throws Exception{
		Statement stmt =null;
		ResultSet rs= null;
		int r=0;
		Connection connessione = ConnectionManager.getConnection();
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
	
	
	public static int getId(String casa) throws Exception{
		int id;
		Statement stmt =null;
		ResultSet rs= null;
		
		Connection connessione = ConnectionManager.getConnection();
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
	
	
	public static boolean Cancella(int id) throws Exception{
		String sql="DELETE FROM casa WHERE idCasa=?";
		PreparedStatement ps=null;
		Connection connessione=ConnectionManager.getConnection();
			boolean res=false;
		try {
			connessione.setAutoCommit(false);
//			if(CasaDAO.controlloRegione(id)!=0){
				if(ProdottoDAO.controlloCasa(id)){
					ps=connessione.prepareStatement(sql);
					ps.setInt(1,id);
					ps.executeUpdate();
					connessione.commit();
					res=true;
				}
//			}
		}
		catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, e.getMessage());
			throw e;
		
		}
		
		
		return res;
	}
	
	
	
	public static casa getCasaProd(int id) throws Exception{
		
		casa lista=new casa();
		Statement stmt =null;
		ResultSet rs= null;
		casa rg=null;
		Connection connessione = ConnectionManager.getConnection();
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
				rg.setRegione(RegioneDAO.getCasaRegione(rs.getInt("Regione_idRegione")));
				lista=rg;
			}
			connessione.close();
		}
		finally{
			
		}
		
		return lista;
	}
	
	public static boolean modCasa(casa casa) throws JarException, SQLException, Exception{
		String UPDATE;
		
		UPDATE="UPDATE casa SET Regione_idRegione=?, Nome=? WHERE idCasa=?";

		Connection connessione = ConnectionManager.getConnection();
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
	
	
	public static boolean inserisciCasa(int id,String nome) throws Exception, Exception{
		PreparedStatement ps=null;
		boolean res= false;
					//INSERT INTO `enoteca`.`regione` (`Nome`) VALUES ('abruzzo');
		String insert="INSERT INTO CASA (regione_idregione,Nome) VALUES (?,?);";
		Connection connessione = ConnectionManager.getConnection();
		try {
			ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setString(2, nome);
			ArrayList<casa> listaCasa =new ArrayList<casa>();
			listaCasa=CasaDAO.getCasa();
			boolean test = true;
			for(int a=0;a<listaCasa.size();a++){
				System.out.println(nome+"<--->"+listaCasa.get(a).getNome());
				if(nome.equals(listaCasa.get(a).getNome())){
					test=false;		
				}			
							
			}
			if(test){
				if(ps.executeUpdate()>0){
					res=true;
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Risulta gia inserito!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return res;
	}
	

}
