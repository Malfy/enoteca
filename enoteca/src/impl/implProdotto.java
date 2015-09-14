package impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ProdottoDAO;

import dbconnection.ConnectionManager;

import model.prodotto;

public class implProdotto {

	public ArrayList<prodotto> getListaProdotto() throws Exception  {
		ConnectionManager conn = null;
		ArrayList<prodotto> listaProdotto = null;
		try {		
			listaProdotto = ProdottoDAO.getListaProdotto();
			ConnectionManager.commit(conn);
		}
		catch (SQLException e) {
			ConnectionManager.rollback(conn);
			e.printStackTrace();
			throw e;
		}
		finally {
			
		}
		return listaProdotto;
	}
	
	
}
