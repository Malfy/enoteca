package model;

import java.util.ArrayList;

import javax.xml.crypto.Data;

public class prodotto implements Comparable{
	private int idProdotto;
	private int idFornitore;
	private float prezzoInterno;
	private int idVigneto;
	private int id_Casa;
	private int valuta;
	private float prezzo;
	private String nome;
	private int anno;
	private float gradi;
	private String descrizione;
	private int idTipologia;
	private tipologia tipo=new tipologia();
	private casa prodCasa=new casa();
	private luvaglio uvaglio=new luvaglio();
	private String Cibi;
	private ArrayList<entrate> entrate=new ArrayList<entrate>();
	private ArrayList<uscite> uscite=new ArrayList<uscite>();
	private fornitore fornitore;
	
	
	
	public fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public int getIdFornitore() {
		return idFornitore;
	}

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	public float getPrezzoInterno() {
		return prezzoInterno;
	}

	public void setPrezzoInterno(float prezzoInterno) {
		this.prezzoInterno = prezzoInterno;
	}

	public int getIdVigneto() {
		return idVigneto;
	}

	public void setIdVigneto(int idVigneto) {
		this.idVigneto = idVigneto;
	}

	public ArrayList<entrate> getEntrate() {
		return entrate;
	}

	public void setEntrate(ArrayList<entrate> entrate) {
		this.entrate = entrate;
	}

	public ArrayList<uscite> getUscite() {
		return uscite;
	}

	public void setUscite(ArrayList<uscite> uscite) {
		this.uscite = uscite;
	}

	public String getCibi() {
		return Cibi;
	}

	public void setCibi(String cibi) {
		Cibi = cibi;
	}

	public int getValuta() {
		return valuta;
	}

	public void setValuta(int valuta) {
		this.valuta = valuta;
	}

	public luvaglio getUvaglio() {
		return uvaglio;
	}

	public void setUvaglio(luvaglio listaUvaglio) {
		this.uvaglio = listaUvaglio;
	}

	public int getIdUvaglio() {
		return idVigneto;
	}

	public void setIdUvaglio(int idUvaglio) {
		this.idVigneto = idUvaglio;
	}

	public tipologia getTipo() {
		return tipo;
	}

	public void setTipo(tipologia tipo) {
		this.tipo = tipo;
	}

	public casa getProdCasa() {
		return prodCasa;
	}

	public void setProdCasa(casa prodCasa) {
		this.prodCasa = prodCasa;
	}

	public float getGradi(){
		return this.gradi;
	}
	
	public void setGradi(float gradi){
		this.gradi=gradi;
	}
	
	public int getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(int id) {
		this.idProdotto = id;
	}
	public int getId_Casa() {
		return id_Casa;
	}
	public void setId_Casa(int id_Casa) {
		this.id_Casa = id_Casa;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdTipologia() {
		// TODO Auto-generated method stub
		return idTipologia;
	}

	public void setIdTipologia(int idTipologia) {
		this.idTipologia = idTipologia;
	}

	public int compareTo(prodotto o) {
		// TODO Auto-generated method stub
		return uvaglio.getNome().compareTo(o.getUvaglio().getNome());
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return uvaglio.getNome().compareTo(((prodotto) o).getUvaglio().getNome());
	}


	

}
