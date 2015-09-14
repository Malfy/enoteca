package model;

import java.util.ArrayList;

public class prodottoAll {
	private prodotto prodModel=new prodotto();
	private casa prodCasa;
	private fornitore prodForn;
	private ArrayList<entrate> prodEntr;
	private ArrayList<uscite> prodUsc;
	public prodotto getProdModel() {
		return prodModel;
	}
	public void setProdModel(prodotto prodModel) {
		this.prodModel = prodModel;
	}
	public casa getProdCasa() {
		return prodCasa;
	}
	public void setProdCasa(casa prodCasa) {
		this.prodCasa = prodCasa;
	}
	public fornitore getProdForn() {
		return prodForn;
	}
	public void setProdForn(fornitore prodForn) {
		this.prodForn = prodForn;
	}
	public ArrayList<entrate> getProdEntr() {
		return prodEntr;
	}
	public void setProdEntr(ArrayList<entrate> prodEntr) {
		this.prodEntr = prodEntr;
	}
	public ArrayList<uscite> getProdUsc() {
		return prodUsc;
	}
	public void setProdUsc(ArrayList<uscite> prodUsc) {
		this.prodUsc = prodUsc;
	}
	
	
	
	
	
	
	

}
