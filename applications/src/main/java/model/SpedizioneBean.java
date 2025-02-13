package model;

import java.io.Serializable;

public class SpedizioneBean implements Serializable {
	private static final long serialVersionUID = 2856723757650934254L;
	
	public SpedizioneBean() {
	    stato = "";
	    provincia = "";
	    citta = "";
	    cap = "";
	    indirizzo = "";
	    civico = "";
	}
	
	public void setStato(String newStato) {
		stato = newStato;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setProvincia(String newProvincia) {
		provincia = newProvincia;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setCitta(String newCitta) {
		citta = newCitta;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCap(String newCap) {
		cap = newCap;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setIndirizzo(String newIndirizzo) {
		indirizzo = newIndirizzo;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setCivico(String newCivico) {
		civico = newCivico;
	}
	
	public String getCivico() {
		return civico;
	}
	
	public void setAccount(int newAccount) {
		account = newAccount;
	}
	
	public int getAccount() {
		return account;
	}
	
	private String stato;
	private String provincia;
	private String citta;
	private String cap;
	private String indirizzo;
	private String civico;
	private int account;
}
