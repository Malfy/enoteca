package DAOOnline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarException;

import java.sql.*;



import dbconnection.ConnectionManager;
import dbconnection.ConnectionManagerOnline;

import model.Connessione;
import model.prodotto;
import model.prodottoHasFornitore;
import model.regione;



public class ProdottoDAOOnline extends SQLException {	
	
	public static ArrayList<prodotto> getListaProdotto(Connessione utente)	throws Exception {

String select=new String();

select = "SELECT * FROM prodotto ORDER BY nome";

ResultSet rs = null;
prodotto prodotto = null;
Connection connessione=ConnectionManagerOnline.getConnection(utente);
PreparedStatement ps=null;
ArrayList<prodotto> lista = new ArrayList<prodotto>();
try {
ps= (PreparedStatement) connessione.prepareStatement(select);
rs = ps.executeQuery(select);
int a=0;
while (rs.next()) {
prodotto = new prodotto();
prodotto.setIdProdotto(rs.getInt("idProdotto"));
prodotto.setIdFornitore(rs.getInt("idFornitore"));
prodotto.setId_Casa(rs.getInt("Casa_idCasa"));
prodotto.setAnno(rs.getInt("Annata"));
prodotto.setPrezzo(rs.getFloat("Prezzo_Publico"));
prodotto.setPrezzoInterno(rs.getFloat("Prezzo_interno"));
prodotto.setGradi(rs.getFloat("Gradi"));
prodotto.setDescrizione(rs.getString("Descrizione"));				
prodotto.setNome(rs.getString("Nome"));
prodotto.setIdTipologia(rs.getInt("idTipologia"));
prodotto.setValuta(rs.getInt("Valutazione"));
prodotto.setCibi(rs.getString("Cibo"));
prodotto.setUvaglio(LuvaglioDAOOnline.getProdUvaglio(rs.getInt("idUvaglio"),utente));
prodotto.setProdCasa(CasaDAOOnline.getCasaProd(rs.getInt("Casa_idCasa"),utente));
prodotto.setFornitore(FornitoreDAOOnline.getFornitoreProd((rs.getInt("idFornitore")),utente));
prodotto.setTipo(TipologiaDAOOnline.getUscite(rs.getInt("idTipologia"),utente));
prodotto.setUscite(UsciteDAOOnline.getUscite(rs.getInt("idProdotto"),utente));
prodotto.setEntrate(EntrateDAOOnline.getEntrate(rs.getInt("idProdotto"),utente));


lista.add(a,prodotto);
a++;
}
connessione.close();
} finally {
}
return lista;
}
	
	public static boolean eliminaProdotto(prodotto prodotto,Connessione utente) throws JarException, SQLException, Exception{
		String DELETE="DELETE FROM prodotto WHERE idProdotto=?";
						
			PreparedStatement ps=null;
			EntrateDAOOnline.eliminaAllEntrateProdotto(prodotto,utente);
			UsciteDAOOnline.eliminaAllUsciteProdotto(prodotto,utente);

			
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
				throw e;
			
			}finally{
				ResultSet rs = null;
				ConnectionManager.closeRes(connessione, rs, ps);
			}
			return res;
	}
	
	public static boolean modificaProdottoTab(prodotto prodotto,Connessione utente)throws JarException, Exception{
//		"UPDATE `enoteca`.`prodotto` SET `Casa_idCasa`=1, `Prezzo_Publico`=11, `idTipologia`=1, `idUvaglio`=2, `Cibo`='iiii' WHERE `idProdotto`='24'"


		String UPDATE="UPDATE prodotto SET Casa_idCasa=(select idCasa from casa WHERE nome=?)," +
										" Prezzo_Publico=?," +
										" idTipologia=(SELECT idTipologia FROM tipologia where tipo=?), " +
										"idUvaglio=(select max(idUvaglio) from uvaglio WHERE nome=?)," +
										" Cibo=? ," +
										" Nome=?," +
										"prezzo_interno=?" +
										" WHERE idProdotto=?";
		PreparedStatement pstmt=null;
		boolean ritorno=false;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		try{
			connessione.setAutoCommit(false);
			pstmt=connessione.prepareStatement(UPDATE);
			pstmt.setString(1, prodotto.getProdCasa().getNome());
			pstmt.setFloat(2, prodotto.getPrezzo());
			pstmt.setString(3, prodotto.getTipo().getTipo());
			pstmt.setString(4, prodotto.getUvaglio().getNome());
			pstmt.setString(5, prodotto.getCibi());		
			pstmt.setString(6, prodotto.getNome());
			pstmt.setFloat(7, prodotto.getPrezzoInterno());
			pstmt.setInt(8, prodotto.getIdProdotto());
			
			if(pstmt.executeUpdate()>0)
				ritorno=true;
			connessione.commit();
			}
			catch(SQLException e){
				connessione.rollback();
				e.printStackTrace();
				throw e;
			}
			finally{
			}
	
		return ritorno;
	}
	
	public static boolean modificaProdotto(prodotto prodotto,Connessione utente)throws JarException, Exception{
//		"UPDATE `enoteca`.`prodotto` SET `Casa_idCasa`=1, `Prezzo_Publico`=11, `idTipologia`=1, `idUvaglio`=2, `Cibo`='iiii' WHERE `idProdotto`='24'"


		String UPDATE="UPDATE prodotto SET Casa_idCasa=(select idCasa from casa WHERE nome=?)," +
										" Prezzo_Publico=?," +
										" idTipologia=(SELECT idTipologia FROM tipologia where tipo=?), " +
										"idUvaglio=(select max(idUvaglio) from uvaglio WHERE nome=?)," +
										" Cibo=? ," +
										" Descrizione=? , Nome=?," +
										"prezzo_interno=?," +
										"Annata=?, Gradi=?," +
										" idFornitore=(select idfornitore from fornitore where nome=?)" +
										" WHERE idProdotto=?";
		PreparedStatement pstmt=null;
		boolean ritorno=false;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		try{
			connessione.setAutoCommit(false);
			pstmt=connessione.prepareStatement(UPDATE);
			pstmt.setString(1, prodotto.getProdCasa().getNome());
			pstmt.setFloat(2, prodotto.getPrezzo());
			pstmt.setString(3, prodotto.getTipo().getTipo());
			pstmt.setString(4, prodotto.getUvaglio().getNome());
			pstmt.setString(5, prodotto.getCibi());			
			pstmt.setString(6,prodotto.getDescrizione());
			pstmt.setString(7, prodotto.getNome());
			pstmt.setFloat(8, prodotto.getPrezzoInterno());
			pstmt.setInt(9, prodotto.getAnno());
			pstmt.setFloat(10, prodotto.getGradi());
			pstmt.setString(11, prodotto.getFornitore().getNome());
			pstmt.setInt(12, prodotto.getIdProdotto());
			
			if(pstmt.executeUpdate()>0)
				ritorno=true;
			connessione.commit();
			}
			catch(SQLException e){
				connessione.rollback();
				e.printStackTrace();
				throw e;
			}
			finally{
			}
	
		return ritorno;
	}


	public static ArrayList<prodotto> getListaProdotto(prodotto ricProdotto, boolean ordinaNome,boolean ordinaCasa,Connessione utente)	throws Exception {
		
		String select="SELECT * FROM prodotto WHERE";
		boolean test=true;
		if(ricProdotto.getIdProdotto()!=0){
			select=select+" idProdotto = '"+ricProdotto.getIdProdotto()+"'";			
		}
		else{
			
		
			if(!ricProdotto.getNome().equals("Nessuna selezione") && !ricProdotto.getNome().equals("")){
				test=false;
				select=select+" Nome LIKE '%"+ricProdotto.getNome()+"%'";			
			}
			if(!ricProdotto.getUvaglio().getNome().equals("") && !ricProdotto.getUvaglio().getNome().equals("Nessuna selezione")){
				
				int[] idC=LuvaglioDAOOnline.getIdVigneti(ricProdotto.getUvaglio().getNome(),utente);
				boolean testOr=false;
				if(idC[0]!=0){
					
					if(!test)
						select=select+" AND (";
					else
						select=select+" (";
					}
			
					
				for(int a=0;a<idC.length;a++){
					if(idC[a]!=0){
						select=select+" idUvaglio='"+idC[a]+"'";
						testOr=true;					
						if(testOr && idC[a+1]!=0){
							select+=" or ";
						}

					}
				}
				if(idC[0]!=0){
					select+=" ) ";
					test=false;					
				}
			}
			if(ricProdotto.getId_Casa()!=0){
				if(!test)
					select=select+" AND ";
				select=select+" Casa_idCasa = ' "+ricProdotto.getId_Casa()+"'";
				test=false;
			}
			if(!ricProdotto.getCibi().equals("")){
				if(!test)
					select=select+" AND ";
				test=false;
				select=select+" Cibo LIKE '%"+ricProdotto.getCibi()+"%'";			
			}
			if(ricProdotto.getIdTipologia()!=0){
				if(!test)
					select=select+" AND ";
				select=select+" idTipologia = '"+ricProdotto.getIdTipologia()+"'";
				test=false;
			}
			if(ricProdotto.getProdCasa().getIdRegione()!=0){
				int[] idC=RegioneDAOOnline.getIdCasa(ricProdotto.getProdCasa().getIdRegione(),utente);
				boolean testOr=false;
				if(idC[0]!=0){
					
					if(!test)
						select=select+" AND (";
					else
						select=select+" (";
					}
			
					
				for(int a=0;a<idC.length;a++){
					if(idC[a]!=0){
						select=select+" Casa_idCasa='"+idC[a]+"'";
						testOr=true;					
						if(testOr && idC[a+1]!=0){
							select+=" or ";
						}

					}
				}
				if(idC[0]!=0){
					select+=" ) ";
					test=false;					
				}
				
			}
		//Casa_idCasa=(SELECT idCasa FROM casa Where Regione_idRegione=1);
				
			if(test)
				select = "SELECT * FROM prodotto ORDER BY casa_idCasa,nome";	

		}
		ResultSet rs = null;
		prodotto prodotto = null;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		ArrayList<prodotto> lista = new ArrayList<prodotto>();
		try {
			ps= (PreparedStatement) connessione.prepareStatement(select);
			rs = ps.executeQuery(select);
			int a=0;
			while (rs.next()) {
				
				prodotto = new prodotto();
				prodotto.setIdProdotto(rs.getInt("idProdotto"));
				prodotto.setId_Casa(rs.getInt("Casa_idCasa"));
				prodotto.setAnno(rs.getInt("Annata"));
				prodotto.setPrezzo(rs.getFloat("Prezzo_Publico"));
				prodotto.setPrezzoInterno(rs.getFloat("Prezzo_interno"));
				prodotto.setGradi(rs.getFloat("Gradi"));
				prodotto.setDescrizione(rs.getString("Descrizione"));				
				prodotto.setNome(rs.getString("Nome"));
				prodotto.setIdTipologia(rs.getInt("idTipologia"));
				prodotto.setValuta(rs.getInt("Valutazione"));
				prodotto.setCibi(rs.getString("Cibo"));
				prodotto.setUvaglio(LuvaglioDAOOnline.getProdUvaglio(rs.getInt("idUvaglio"),utente));
				prodotto.setProdCasa(CasaDAOOnline.getCasaProd(rs.getInt("Casa_idCasa"),utente));
				prodotto.setFornitore(FornitoreDAOOnline.getFornitoreProd((rs.getInt("idFornitore")),utente));
				prodotto.setTipo(TipologiaDAOOnline.getUscite(rs.getInt("idTipologia"),utente));
				prodotto.setUscite(UsciteDAOOnline.getUscite(rs.getInt("idProdotto"),utente));
				prodotto.setEntrate(EntrateDAOOnline.getEntrate(rs.getInt("idProdotto"),utente));
				lista.add(a,prodotto);
				a++;
			}
			connessione.close();
		} finally {
		}
		return lista;
	}
	
	
	
	//_____________________-----------------_______________
	
	
public static ArrayList<prodotto> getListaProdottoPerEtichetta(prodotto ricProdotto, boolean ordinaNome,boolean ordinaCasa,Connessione utente)	throws Exception {
		
		String select="SELECT * FROM prodotto WHERE"+" Nome = '" + ricProdotto.getNome()+"'";
		
		ResultSet rs = null;
		prodotto prodotto = null;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		ArrayList<prodotto> lista = new ArrayList<prodotto>();
		try {
			ps= (PreparedStatement) connessione.prepareStatement(select);
			System.out.println(select.toString());
			rs = ps.executeQuery(select);
			int a=0;
			while (rs.next()) {
				
				prodotto = new prodotto();
				prodotto.setIdProdotto(rs.getInt("idProdotto"));
				prodotto.setId_Casa(rs.getInt("Casa_idCasa"));
				prodotto.setAnno(rs.getInt("Annata"));
				prodotto.setPrezzo(rs.getFloat("Prezzo_Publico"));
				prodotto.setPrezzoInterno(rs.getFloat("Prezzo_interno"));
				prodotto.setGradi(rs.getFloat("Gradi"));
				prodotto.setDescrizione(rs.getString("Descrizione"));				
				prodotto.setNome(rs.getString("Nome"));
				prodotto.setIdTipologia(rs.getInt("idTipologia"));
				prodotto.setValuta(rs.getInt("Valutazione"));
				prodotto.setCibi(rs.getString("Cibo"));
				prodotto.setUvaglio(LuvaglioDAOOnline.getProdUvaglio(rs.getInt("idUvaglio"),utente));
				prodotto.setProdCasa(CasaDAOOnline.getCasaProd(rs.getInt("Casa_idCasa"),utente));
				prodotto.setFornitore(FornitoreDAOOnline.getFornitoreProd((rs.getInt("idFornitore")),utente));
				prodotto.setTipo(TipologiaDAOOnline.getUscite(rs.getInt("idTipologia"),utente));
				prodotto.setUscite(UsciteDAOOnline.getUscite(rs.getInt("idProdotto"),utente));
				prodotto.setEntrate(EntrateDAOOnline.getEntrate(rs.getInt("idProdotto"),utente));
				lista.add(a,prodotto);
				a++;
			}
			connessione.close();
		} finally {
		}
		return lista;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//_________________---------------------_________________
	
	public static boolean inserisciProdotto(prodotto prodotto,Connessione utente) throws Exception, Exception{
		String insert="INSERT INTO prodotto (Casa_idCasa, Annata, Nome, Prezzo_Publico, Gradi, Descrizione, idTipologia, idUvaglio,cibo,prezzo_interno,idFornitore) " +
				"VALUES ((select idCasa from casa WHERE nome=?)," +
				"?,?,?,?,?," +
				"(SELECT idTipologia FROM tipologia where tipo=?)" +
				",(select idUvaglio from uvaglio WHERE nome=?),?,?,?)";

		PreparedStatement ps=null;
		boolean res= false;
		
		Connection connessione = ConnectionManagerOnline.getConnection(utente);
		try {
			ps = (PreparedStatement) connessione.prepareStatement(insert);
			ps.setString(1, prodotto.getProdCasa().getNome());
			ps.setInt(2, prodotto.getAnno());
			ps.setString(3, prodotto.getNome());
			ps.setFloat(4, prodotto.getPrezzo());
			ps.setFloat(5, prodotto.getGradi());
			ps.setString(6, prodotto.getDescrizione());
			ps.setString(7, prodotto.getTipo().getTipo());
			ps.setString(8, prodotto.getUvaglio().getNome());
			ps.setString(9, prodotto.getCibi());
			ps.setFloat(10, prodotto.getPrezzoInterno());
			ps.setInt(11, FornitoreDAOOnline.getNomeFornitore((String)prodotto.getFornitore().getNome(),utente));
//			ArrayList<prodotto> prodotti =new ArrayList<prodotto>();
//			prodotti= ProdottoDAO.getListaProdotto();
			boolean test = true;
			if(controlloInserimento(prodotto.getNome(),prodotto.getAnno(),utente))
				test=true;
			else 
				test= false;
//			for(int a=0;a<prodotti.size();a++){
//				if(prodotto.getAnno()==prodotti.get(a).getAnno() && prodotto.getNome().equals(prodotti.get(a).getNome())){
//					test=false;		
//				}
			//mhm controllo

			
//			}
			if(test){
				if(ps.executeUpdate()>0){
					res=true;
				}
			}
			
			connessione.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n\n"+e.getCause());
		}
		
		return res;
	}
	
	public static boolean controlloInserimento(String nome,int anno,Connessione utente) throws JarException, Exception{
		String UPDATE="SELECT * FROM Prodotto where nome=? AND Annata=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean ritorno=true;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
		ps.setString(1, nome);
		ps.setInt(2, anno);
		rs=ps.executeQuery();
		while(rs.next()){
			ritorno=false;
		}
		return ritorno;
		
	}
	
	
	
	public static int getUltimoIdProdotto(Connessione utente) throws JarException, SQLException, Exception{
		String select=new String();

		select = "SELECT max(idProdotto) FROM enoteca.prodotto;";

		ResultSet rs = null;
		int id;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try {
		ps= (PreparedStatement) connessione.prepareStatement(select);
		rs = ps.executeQuery(select);
		int a=0;
		rs.next();
		id=rs.getInt("max(idProdotto)");
		connessione.close();
		} finally {
		}
		
		return id;
	}
	
	
	
	public static prodotto getProdotto(int id,Connessione utente) throws JarException, SQLException, Exception{
		String select=new String();

		select = "SELECT * FROM prodotto WHERE idProdotto='"+id+"'";

		ResultSet rs = null;
		prodotto prodotto = null;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		ArrayList<prodotto> lista = new ArrayList<prodotto>();
		try {
		ps= (PreparedStatement) connessione.prepareStatement(select);
		rs = ps.executeQuery(select);
		int a=0;
		rs.next();
		prodotto = new prodotto();
		prodotto.setIdProdotto(rs.getInt("idProdotto"));
		prodotto.setId_Casa(rs.getInt("Casa_idCasa"));
		prodotto.setAnno(rs.getInt("Annata"));
		prodotto.setPrezzo(rs.getFloat("Prezzo_Publico"));
		prodotto.setPrezzoInterno(rs.getFloat("prezzo_interno"));
		prodotto.setGradi(rs.getFloat("Gradi"));
		prodotto.setDescrizione(rs.getString("Descrizione"));				
		prodotto.setNome(rs.getString("Nome"));
		prodotto.setIdTipologia(rs.getInt("idTipologia"));
		prodotto.setValuta(rs.getInt("Valutazione"));
		prodotto.setCibi(rs.getString("Cibo"));
		prodotto.setUvaglio(LuvaglioDAOOnline.getProdUvaglio(rs.getInt("idUvaglio"),utente));
		prodotto.setProdCasa(CasaDAOOnline.getCasaProd(rs.getInt("Casa_idCasa"),utente));
		prodotto.setFornitore(FornitoreDAOOnline.getFornitoreProd((rs.getInt("idFornitore")),utente));
		prodotto.setTipo(TipologiaDAOOnline.getUscite(rs.getInt("idTipologia"),utente));
		prodotto.setUscite(UsciteDAOOnline.getUscite(rs.getInt("idProdotto"),utente));
		prodotto.setEntrate(EntrateDAOOnline.getEntrate(rs.getInt("idProdotto"),utente));
	
		connessione.close();
		} finally {
		}
		
		return prodotto;
	}
	public static boolean controlloTipologia(int id,Connessione utente) throws JarException, Exception{
		String UPDATE="SELECT * FROM Prodotto where idTipologia=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean ritorno=true;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
			ritorno=false;
		}
		return ritorno;
		
	}
	
	public static boolean controlloCasa(int id,Connessione utente) throws JarException, Exception{
		String UPDATE="SELECT * FROM Prodotto where casa_idcasa=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean ritorno=true;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
			ritorno=false;
		}
		return ritorno;
		
	}
	public static boolean controlloVigneto(int id,Connessione utente) throws JarException, Exception{
		String UPDATE="SELECT * FROM Prodotto where idUvaglio=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean ritorno=true;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		ps = (PreparedStatement) connessione.prepareStatement(UPDATE);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
			ritorno=false;
		}
		return ritorno;
		
	}
	
	
	public static int getIdUltimoProdotto(String nome,int anno,Connessione utente) throws JarException, SQLException, Exception{
		String select= "SELECT idProdotto from prodotto where nome='"+nome+"' AND Annata='"+anno+"'";

		ResultSet rs = null;
		prodotto prodotto = null;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		PreparedStatement ps=null;
		try {
		ps= (PreparedStatement) connessione.prepareStatement(select);
		rs = ps.executeQuery();
		
		rs.next();
		prodotto = new prodotto();
		prodotto.setIdProdotto(rs.getInt("idProdotto"));
		connessione.close();
		} finally {
		}
		
		return prodotto.getIdProdotto();
	}
	
	public static boolean setFornitore(int idP,int idF,Connessione utente)throws JarException, Exception{

		String UPDATE="UPDATE prodotto SET idFornitore=?" +
										" WHERE idProdotto=?";
		PreparedStatement pstmt=null;
		boolean ritorno=false;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		try{
			connessione.setAutoCommit(false);
			pstmt=connessione.prepareStatement(UPDATE);
			pstmt.setInt(1,idF);
			pstmt.setInt(2, idP);
			
			if(pstmt.executeUpdate()>0)
				ritorno=true;
			connessione.commit();
			}
			catch(SQLException e){
				connessione.rollback();
				e.printStackTrace();
				throw e;
			}
			finally{
			}
	
		return ritorno;
	}
	
	
	public static boolean deleteFornitore(int id,Connessione utente)throws JarException, Exception{

		String UPDATE="UPDATE prodotto SET idFornitore=?" +
										" WHERE idProdotto=?";
		PreparedStatement pstmt=null;
		boolean ritorno=false;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		try{
			connessione.setAutoCommit(false);
			pstmt=connessione.prepareStatement(UPDATE);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, id);
			
			if(pstmt.executeUpdate()>0)
				ritorno=true;
			connessione.commit();
			}
			catch(SQLException e){
				connessione.rollback();
				e.printStackTrace();
				throw e;
			}
			finally{
			}
	
		return ritorno;
	}
	public static boolean setPrezzoInterno(int id,float prezzo,Connessione utente)throws JarException, Exception{

		String UPDATE="UPDATE prodotto SET prezzo_interno=?" +
										" WHERE idProdotto=?";
		PreparedStatement pstmt=null;
		boolean ritorno=false;
		Connection connessione=ConnectionManagerOnline.getConnection(utente);
		try{
			connessione.setAutoCommit(false);
			pstmt=connessione.prepareStatement(UPDATE);
			pstmt.setFloat(1, prezzo);
			pstmt.setInt(2, id);
			
			if(pstmt.executeUpdate()>0)
				ritorno=true;
			connessione.commit();
			}
			catch(SQLException e){
				connessione.rollback();
				e.printStackTrace();
				throw e;
			}
			finally{
			}
	
		return ritorno;
	}
	

}
