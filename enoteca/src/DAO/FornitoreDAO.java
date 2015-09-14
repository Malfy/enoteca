package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.JarException;

import model.casa;
import model.fornitore;
import model.prodottoHasFornitore;
import dbconnection.ConnectionManager;

public class FornitoreDAO {
	
	
	public static boolean inserisciFornitore(fornitore fornitore) throws JarException, SQLException, Exception{
		String INSERT="INSERT INTO fornitore  (agente, nome, email, telefono_01, fax, sitoWeb, telefono_02) VALUES (?,?,?,?,?,?,?);";


		boolean ritorno=false;
		PreparedStatement ps = null;
		Connection connessione= ConnectionManager.getConnection();
		
		try {
			connessione.setAutoCommit(false);
			ps = connessione.prepareStatement(INSERT);
			ps.setString(1, fornitore.getAgente());
			ps.setString(2, fornitore.getNome());
			ps.setString(3, fornitore.getEmail());
			ps.setString(4, fornitore.getTelefono1());
			ps.setString(5, fornitore.getFax());
			ps.setString(6, fornitore.getWeb());
			ps.setString(7, fornitore.getTelefono2());
			ArrayList<fornitore> listaFornitore=FornitoreDAO.getFornitore();
			boolean test=true;
			for(fornitore e:listaFornitore){
				System.out.println(fornitore.getNome());
				if(!e.getNome().equals(null) & !e.getNome().equals("") & e.getNome().equals(fornitore.getNome())){
					test=false;
				}
			}
			if(test){
				ps.executeUpdate();
			}
			connessione.commit();				

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
		
		return true;
	}

	public static boolean modificaFornitore(fornitore fornitore) throws JarException, SQLException, Exception{
		String UPDATE="UPDATE fornitore SET nome=?, email=?, telefono_01=?, fax=?, sitoWeb=?, telefono_02=?, agente=? WHERE idFornitore=?";
		boolean ritorno=false;
		PreparedStatement ps = null;
		Connection connessione= ConnectionManager.getConnection();
		
		try {
			connessione.setAutoCommit(false);
			ps = connessione.prepareStatement(UPDATE);
			ps.setString(1, fornitore.getNome());
			ps.setString(2, fornitore.getEmail());
			ps.setString(3, fornitore.getTelefono1());
			ps.setString(4, fornitore.getFax());
			ps.setString(5, fornitore.getWeb());
			ps.setString(6, fornitore.getTelefono2());
			ps.setString(7, fornitore.getAgente());
			ps.setInt(8, fornitore.getId());
			ps.executeUpdate();
			connessione.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
		
		return true;
	}
	
	
	public static int	getNomeFornitore(String nome) throws JarException, Exception{
		Statement stmt =null;
		ResultSet rs= null;
		int id=0;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT idFornitore FROM fornitore WHERE ?=nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			ps.setString(1, nome);
			rs=ps.executeQuery();
			while(rs.next()){				
				
				id=rs.getInt("idFornitore");
			}
			connessione.close();
			
		}
		finally{
			
		}
		
		return id;
	}
	
	
	public static ArrayList<fornitore> getRicercaFornitore(String nome) throws Exception{
		ArrayList<fornitore> listaFornitore=new ArrayList<fornitore>();
		Statement stmt =null;
		ResultSet rs= null;
		fornitore rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM fornitore WHERE nome LIKE '%"+nome+"%'";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new fornitore();
				rg.setId(rs.getInt("idFornitore"));
				rg.setNome(rs.getString("Nome"));
				rg.setAgente(rs.getString("agente"));
				rg.setEmail(rs.getString("email"));
				rg.setTelefono1(rs.getString("telefono_01"));
				rg.setTelefono2(rs.getString("telefono_02"));
				rg.setFax(rs.getString("fax"));
				rg.setWeb(rs.getString("sitoWeb"));
				
				listaFornitore.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return listaFornitore;
	}
	
	
	public static ArrayList<fornitore> getFornitore() throws Exception{
		ArrayList<fornitore> listaFornitore=new ArrayList<fornitore>();
		Statement stmt =null;
		ResultSet rs= null;
		fornitore rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM fornitore order by nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new fornitore();
				rg.setNome("");
				rg.setId(rs.getInt("idFornitore"));
				rg.setAgente(rs.getString("agente"));
				rg.setNome(rs.getString("Nome"));
				rg.setEmail(rs.getString("email"));
				rg.setTelefono1(rs.getString("telefono_01"));
				rg.setTelefono2(rs.getString("telefono_02"));
				rg.setFax(rs.getString("fax"));
				rg.setWeb(rs.getString("sitoWeb"));
				
				listaFornitore.add(rg);
			}
			connessione.close();
		}
		finally{
			
		}
		
		return listaFornitore;
	}
	
	
	
	
	
	public static fornitore getFornitoreProd(int id) throws Exception{
		fornitore Fornitore=new fornitore();
		Statement stmt =null;
		ResultSet rs= null;
		fornitore rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
		if(id!=0){
			String sql="SELECT * FROM fornitore WHERE ?=idFornitore";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			ps.setInt(1, id);
			rs=ps.executeQuery();
			int a=0;
			rs.next();
				a++;
				rg=new fornitore();
				rg.setId(rs.getInt("idFornitore"));
				rg.setNome(rs.getString("Nome"));
				rg.setEmail(rs.getString("email"));
				rg.setTelefono1(rs.getString("telefono_01"));
				rg.setTelefono2(rs.getString("telefono_02"));
				rg.setFax(rs.getString("fax"));
				rg.setWeb(rs.getString("sitoWeb"));
				rg.setAgente(rs.getString("Agente"));
				Fornitore=rg;
			connessione.close();
		}
			
		}
		finally{
			
		}
		
		return Fornitore;
	}
}
