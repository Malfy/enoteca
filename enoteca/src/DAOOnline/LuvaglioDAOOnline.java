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
import model.luvaglio;
import model.regione;
import dbconnection.ConnectionManager;
import dbconnection.ConnectionManagerOnline;

public class LuvaglioDAOOnline {
	
	public static ArrayList<luvaglio> getListaUvaglio(Connessione utente) throws Exception{
		ArrayList<luvaglio> lista=new ArrayList<luvaglio>();
		Statement stmt =null;
		ResultSet rs= null;
		luvaglio rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
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
	
	public static int[] getIdVigneti(String idR,Connessione utente){
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
	
	
	
	
	
	
	
	public static boolean inserisciVigneto(String nome,Connessione utente) throws  Exception{
		PreparedStatement ps=null;
		boolean res= false;
					//INSERT INTO `enoteca`.`regione` (`Nome`) VALUES ('abruzzo');
		String insert="INSERT INTO UVAGLIO (Nome) VALUES (?);";
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		try {
			ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setString(1, nome);
			ArrayList<luvaglio> regioni =new ArrayList<luvaglio>();
			regioni=LuvaglioDAOOnline.getListaUvaglio(utente);
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
	
	public static int getId(String vigneto,Connessione utente) throws Exception{
		int id;
		Statement stmt =null;
		ResultSet rs= null;
		
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
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
	
	
	
	public static boolean modVigneto(luvaglio luvaglio,Connessione utente) throws JarException, SQLException, Exception{
		String UPDATE;
		boolean ritorno=true;
		UPDATE="UPDATE uvaglio SET Nome=? WHERE iduvaglio=?";

		Connection connessione = ConnectionManagerOnline.getConnection(utente);
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
	
	
	public static luvaglio getProdUvaglio(int idUvaglio,Connessione utente) throws Exception{
		luvaglio uvaglio=new luvaglio();
		Statement stmt =null;
		ResultSet rs= null;
		luvaglio rg=null;
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
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
