package model;

import java.util.ArrayList;

public class prodottoHasFornitore {
	private int idProdotto;
	private int idFornitore;
	private float prezzo;
	private fornitore fornitore;
	private prodotto prodotto;
	
	
	public prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(prodotto prodotto) {
		this.prodotto = prodotto;
	}
	public fornitore getFornitore() {
		return fornitore;
	}
	public void setFornitore(fornitore fornitore) {
		this.fornitore = fornitore;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public int getIdFornitore() {
		return idFornitore;
	}
	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	

}
