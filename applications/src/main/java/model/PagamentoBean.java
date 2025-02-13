package model;

import java.io.Serializable;

public class PagamentoBean implements Serializable {
	private static final long serialVersionUID = 2856723757650934254L;
	
	public PagamentoBean() {
		Tipocarta="";
		Numerocarta="";
		Datascadenza="";
	}
	
	public void setTipocarta(String newTipocarta) {
		Tipocarta = newTipocarta;
	}
	
	public String getTipocarta() {
		return Tipocarta;
	}
	
	public void setNumerocarta(String newNumerocarta) {
		Numerocarta = newNumerocarta;
	}
	
	public String getNumerocarta() {
		return Numerocarta;
	}
	
	public void setDatascadenza(String newDatascadenza) {
		Datascadenza = newDatascadenza;
	}
	
	public String getDatascadenza() {
		return Datascadenza;
	}
	
	public void setCode(int newCode) {
		Code = newCode;
	}
	
	public int getCode() {
		return Code;
	}
	
	private String Tipocarta;
	private String Numerocarta;
	private String Datascadenza;
	private int Code;

	public void add(PagamentoBean pagamento) {
		// TODO Auto-generated method stub
		
	}
}
