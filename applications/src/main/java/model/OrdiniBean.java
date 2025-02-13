package model;

import java.io.Serializable;

public class OrdiniBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934254L;

    public OrdiniBean() {
        Numeroprodotti = "";
        Dataacquisto = "";
        Account = 0;
        Prodotto = "";
        Indirizzo = "";
        Carta = "";
    }

    public void setNumeroprodotti(String newNumeroprodotti) {
        Numeroprodotti = newNumeroprodotti;
    }

    public String getNumeroprodotti() {
        return Numeroprodotti;
    }

    public void setDataacquisto(String newDataacquisto) {
        Dataacquisto = newDataacquisto;
    }

    public String getDataacquisto() {
        return Dataacquisto;
    }

    public void setCode(int newCode) {
        Code = newCode;
    }

    public int getCode() {
        return Code;
    }

    public void setAccount(int newAccount) {
        Account = newAccount;
    }

    public int getAccount() {
        return Account;
    }

    public void setProdotto(String newProdotto) {
        Prodotto = newProdotto;
    }

    public String getProdotto() {
        return Prodotto;
    }

    public void setIndirizzo(String newIndirizzo) {
        Indirizzo = newIndirizzo;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setCarta(String newCarta) {
        Carta = newCarta;
    }

    public String getCarta() {
        return Carta;
    }

    private String Numeroprodotti;
    private String Dataacquisto;
    private int Code;
    private int Account;
    private String Prodotto;
    private String Indirizzo;
    private String Carta;

    public void add(OrdiniBean pagamento) {
        // TODO Auto-generated method stub

    }
}
