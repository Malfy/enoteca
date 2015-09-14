package model;

import java.util.ArrayList;

public class fornitore {
	private int id;
	private String nome;
	private String telefono1;
	private String telefono2;
	private String fax;
	private String email;
	private String agente;
	private String web;
	private ArrayList<prodotto> prodotto;
	
	
	public String getAgente() {
		return agente;
	}
	public void setAgente(String agente) {
		this.agente = agente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public ArrayList<prodotto> getProdotto() {
		return prodotto;
	}
	public void setProdotto(ArrayList<prodotto> prodotto) {
		this.prodotto = prodotto;
	}
	
	
	

}
