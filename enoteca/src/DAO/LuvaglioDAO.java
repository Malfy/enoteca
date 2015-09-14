package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JOptionPane;

import model.luvaglio;
import model.regione;
import dbconnection.ConnectionManager;

public class LuvaglioDAO {
	
	public static ArrayList<luvaglio> getListaUvaglio() throws Exception{
		ArrayList<luvaglio> lista=new ArrayList<luvaglio>();
		Statement stmt =null;
		ResultSet rs= null;
		luvaglio rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM uvaglio ORDER BY nome";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new luvaglio();
				rg.setId(rs.getInt("iduvaglio"));
				rg.setNome(rs.getString("Nome"));				
				lista.add(rg);
			}
			connessione.close();
		}
		finally{			
		}
		return lista;
	}
	
	public static int[] getIdVigneti(String idR){
		int[] idC=new int[100] ;
		System.out.println(idR);
		String select="SELECT idUvaglio FROM uvaglio Where Nome LIKE  '%"+idR+"%'";
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
				idC[a]=rs.getInt("idUvaglio");
				a++;
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return idC;
	}
	
	
	
	public static boolean Cancella(int id) throws Exception{
		String sql="DELETE FROM uvaglio WHERE idUvaglio=?";
		PreparedStatement ps=null;
		boolean res=false;
		if(ProdottoDAO.controlloVigneto(id)){
			
		Connection connessione=ConnectionManager.getConnection();
			
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
			JOptionPane.showConfirmDialog(null, e.getMessage());
			throw e;
		
		}
		
		}
		else
			res=false;
		return res;
	}
	
	
	
	public static boolean inserisciVigneto(String nome) throws  Exception{
		PreparedStatement ps=null;
		boolean res= false;
					//INSERT INTO `enoteca`.`regione` (`Nome`) VALUES ('abruzzo');
		String insert="INSERT INTO UVAGLIO (Nome) VALUES (?);";
		Connection connessione = ConnectionManager.getConnection();
		try {
			ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setString(1, nome);
			ArrayList<luvaglio> regioni =new ArrayList<luvaglio>();
			regioni=LuvaglioDAO.getListaUvaglio();
			boolean test = true;
			for(int a=0;a<regioni.size();a++){
				if(nome.equals(regioni.get(a).getNome())){
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
	
	public static int getId(String vigneto) throws Exception{
		int id;
		Statement stmt =null;
		ResultSet rs= null;
		
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		if(vigneto.equals("Nessuna selezione"))
			return 0;
		try{
			String sql="SELECT * FROM uvaglio WHERE nome='"+vigneto+"'";
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			rs.next();
			id=rs.getInt("iduvaglio");
			connessione.close();
		}
		finally{			
		}
		return id;
	}
	
	
	
	public static boolean modVigneto(luvaglio luvaglio) throws JarException, SQLException, Exception{
		String UPDATE;
		boolean ritorno=true;
		UPDATE="UPDATE uvaglio SET Nome=? WHERE iduvaglio=?";

		Connection connessione = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
			ps.setInt(2, luvaglio.getId());
			ps.setString(1, luvaglio.getNome());
			ritorno=ps.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return ritorno;
	}
	
	
	public static luvaglio getProdUvaglio(int idUvaglio) throws Exception{
		luvaglio uvaglio=new luvaglio();
		Statement stmt =null;
		ResultSet rs= null;
		luvaglio rg=null;
		Connection connessione = ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			String sql="SELECT * FROM uvaglio WHERE idUvaglio="+idUvaglio;
			ps=(PreparedStatement) connessione.prepareStatement(sql);
			stmt=connessione.createStatement();
			rs=ps.executeQuery(sql);
			int a=0;
			while(rs.next()){
				a++;
				rg=new luvaglio();
				rg.setId(rs.getInt("iduvaglio"));
				rg.setNome(rs.getString("Nome"));				
				uvaglio=rg;
			}
			connessione.close();
		}
		finally{			
		}
		return uvaglio;
	}
	
	

}
