package DAO;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JOptionPane;

import model.fornitore;
import model.prodotto;
import model.prodottoHasFornitore;
import dbconnection.ConnectionManager;

public class FornitoreHasProdottoDAO {
	
	public static boolean eliminaRelazione(prodottoHasFornitore relazione) throws JarException, SQLException, Exception{
		String DELETE="DELETE FROM fornitore_has_prodotto WHERE prodotto_idProdotto=? AND Fornitore_idFornitore=?";
			PreparedStatement ps=null;
			Connection connessione=ConnectionManager.getConnection();
				boolean res=false;
			try {
				connessione.setAutoCommit(false);
				ps=connessione.prepareStatement(DELETE);
				ps.setInt(1,relazione.getIdProdotto());
				ps.setInt(2,relazione.getIdFornitore());
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
	
	
	public static boolean eliminaRelazioneProdottoFornitore(prodotto prodotto) throws JarException, SQLException, Exception{
		String DELETE="DELETE FROM fornitore_has_prodotto WHERE prodotto_idProdotto=?";
						
			PreparedStatement ps=null;
			Connection connessione=ConnectionManager.getConnection();
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
				JOptionPane.showConfirmDialog(null, e.getMessage());
				throw e;
			
			}finally{
				ResultSet rs = null;
				ConnectionManager.closeRes(connessione, rs, ps);
			}
			return res;
	}
	
	public static boolean inserisciRelazioneProdForn(prodottoHasFornitore relazione) throws Exception, Exception{
		PreparedStatement ps=null;
		boolean res= false;
		String insert="INSERT INTO fornitore_has_prodotto (Fornitore_idFornitore, Prodotto_idProdotto, prezzo_interno) VALUES (?, ?, ?)";
		Connection connessione = ConnectionManager.getConnection();
		try {
			System.out.println(relazione.getIdFornitore()+" "+relazione.getIdProdotto()+" "+relazione.getPrezzo());
			ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setInt(1, relazione.getIdFornitore());
			ps.setInt(2, relazione.getIdProdotto());
			ps.setFloat(3, relazione.getPrezzo());
			
			if(ps.executeUpdate()>0)
				System.out.println("");

			connessione.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return res;
	}
	
	
	public static ArrayList<prodotto> getProdottoFornitore(fornitore fornitore) throws Exception{
		boolean ritorno=false;
		String select="SELECT * FROM fornitore_has_prodotto WHERE fornitore_idFornitore=?";
		ArrayList<prodotto> listaProdotto=new ArrayList<prodotto>();
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement ps= null;
		Connection connessione= ConnectionManager.getConnection();
		ps= connessione.prepareStatement(select);
		ps.setInt(1, fornitore.getId());
		
		stmt=connessione.createStatement();
		rs=ps.executeQuery();
		while(rs.next()){
			if(rs.getInt("prodotto_idProdotto")>0){
				
				listaProdotto.add(ProdottoDAO.getProdotto(rs.getInt("prodotto_idProdotto")));
			}
		}
		
		return listaProdotto;
	}
	
	public static boolean controllo(int idP,int idF) throws Exception{
		boolean ritorno=false;
		String select="SELECT * FROM enoteca.fornitore_has_prodotto where Fornitore_idFornitore=? and Prodotto_idProdotto=? ";
		
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement ps= null;
		Connection connessione= ConnectionManager.getConnection();
		ps= connessione.prepareStatement(select);
		ps.setInt(1, idF);
		ps.setInt(2, idP);
		stmt=connessione.createStatement();
		rs=ps.executeQuery();
		while(rs.next()){
			ritorno=true;
		}
		
		return ritorno;
	}
	
	
	
	
	public static boolean setRelazione(prodottoHasFornitore relazione) throws JarException, SQLException, Exception {
		String UPDATE="UPDATE fornitore_has_prodotto SET prezzo_interno=? WHERE Fornitore_idFornitore=? AND Prodotto_idProdotto=?";
		boolean ritorno=false;
		PreparedStatement ps = null;
		Connection connessione= ConnectionManager.getConnection();
		
		try {
			connessione.setAutoCommit(false);
			ps = connessione.prepareStatement(UPDATE);
			ps.setFloat(1, relazione.getPrezzo());
			ps.setInt(2, relazione.getIdFornitore());
			ps.setInt(3, relazione.getIdProdotto());
			ps.executeUpdate();
			connessione.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
			
		
		return ritorno;
		
	}
	
	
	
public static ArrayList<prodottoHasFornitore> getFornitorePrezzo(int idP)	throws Exception {
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<prodottoHasFornitore> proHasFor=new ArrayList<prodottoHasFornitore>();
		prodottoHasFornitore appoggio=null;
		Connection connessione=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		FornitoreDAO fornitoreDao=new FornitoreDAO();
		
		try {
			
			//select min(prezzo_interno) from enoteca.fornitore_has_prodotto  WHERE Prodotto_idProdotto=1
			String select = "select * from fornitore_has_prodotto  WHERE Prodotto_idProdotto=?";
			ps= (PreparedStatement) connessione.prepareStatement(select);
			ps.setInt(1, idP);
			
			stmt = connessione.createStatement();
			rs = ps.executeQuery();
			while(rs.next()){
				appoggio=new prodottoHasFornitore();
				appoggio.setIdProdotto(idP);
				appoggio.setPrezzo(rs.getFloat("prezzo_interno"));
				appoggio.setIdFornitore(rs.getInt("Fornitore_idFornitore"));
				appoggio.setFornitore(fornitoreDao.getFornitoreProd(rs.getInt("Fornitore_idFornitore")));
				proHasFor.add(appoggio);
			}
			connessione.close();
		} finally {
		}
		
		return proHasFor;
	}

public static prodottoHasFornitore getPrezzo(prodottoHasFornitore relazione)	throws Exception {
	
	Statement stmt = null;
	ResultSet rs = null;
	prodottoHasFornitore proHasFor=new prodottoHasFornitore();
	Connection connessione=ConnectionManager.getConnection();
	PreparedStatement ps=null;
	
	try {
		
		//select min(prezzo_interno) from enoteca.fornitore_has_prodotto  WHERE Prodotto_idProdotto=1
		String select = "select prezzo_interno from fornitore_has_prodotto  WHERE Fornitore_idFornitore=? AND Prodotto_idProdotto=?";
		ps= (PreparedStatement) connessione.prepareStatement(select);
		ps.setInt(1, relazione.getIdFornitore());
		ps.setInt(2, relazione.getIdProdotto());
		stmt = connessione.createStatement();
		rs = ps.executeQuery();
		rs.next();
		proHasFor.setPrezzo(rs.getFloat("prezzo_interno"));
		proHasFor.setIdFornitore(relazione.getIdFornitore());
		proHasFor.setIdProdotto(relazione.getIdProdotto());
		connessione.close();
	} finally {
	}
	
	return proHasFor;
}

	
public static prodottoHasFornitore getPrezzoInferiore(int idP)	throws Exception {
		
		Statement stmt = null;
		ResultSet rs = null;
		prodottoHasFornitore proHasFor=new prodottoHasFornitore();
		Connection connessione=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		
		try {
			
			//select min(prezzo_interno) from enoteca.fornitore_has_prodotto  WHERE Prodotto_idProdotto=1
			String select = "select min(prezzo_interno),Fornitore_idFornitore from enoteca.fornitore_has_prodotto  WHERE Prodotto_idProdotto=?";
			ps= (PreparedStatement) connessione.prepareStatement(select);
			ps.setInt(1, idP);
			
			stmt = connessione.createStatement();
			rs = ps.executeQuery();
			rs.next();
			proHasFor.setPrezzo(rs.getFloat("min(prezzo_interno)"));
			proHasFor.setIdFornitore(rs.getInt("Fornitore_idFornitore"));
			connessione.close();
		} finally {
		}
		
		return proHasFor;
	}
	

}
