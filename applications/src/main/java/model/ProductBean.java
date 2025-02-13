package model;

public class ProductBean {
	private int Code;
    private String Nome;
    private String Descrizione;
    private String Casa;
    private double Prezzo;
    private String Display;
    private String Fotocamera;
    private String Archiviazione;
    private String Autenticazione;
    private String Chip;
    private String SIM;
    private String Bluetooth;
    private String Connettori;
    private String Rete;
    private String Batteria;
    private String DimPes;
    private String SO;
    private String Acqua;
	private int quantity;

    

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String Descrizione) {
        this.Descrizione = Descrizione;
    }
    
    public String getCasa() {
        return Casa;
    }

    public void setCasa(String Casa) {
        this.Casa = Casa;
    }

    public double getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(double Prezzo) {
        this.Prezzo = Prezzo;
    }
    
    public String getDisplay() {
        return Display;
    }

    public void setDisplay(String Display) {
        this.Display = Display;
    }
    
    public String getFotocamera() {
        return Fotocamera;
    }

    public void setFotocamera(String Fotocamera) {
        this.Fotocamera = Fotocamera;
    }
    
    public String getArchiviazione() {
        return Archiviazione;
    }

    public void setArchiviazione(String Archiviazione) {
        this.Archiviazione = Archiviazione;
    }

    public String getAutenticazione() {
        return Autenticazione;
    }

    public void setAutenticazione(String Autenticazione) {
        this.Autenticazione = Autenticazione;
    }

    public String getChip() {
        return Chip;
    }

    public void setChip(String Chip) {
        this.Chip = Chip;
    }
    
    public String getSIM() {
        return SIM;
    }

    public void setSIM(String SIM) {
        this.SIM = SIM;
    }
    
    public String getBluetooth() {
        return Bluetooth;
    }

    public void setBluetooth(String Bluetooth) {
        this.Bluetooth = Bluetooth;
    }
    
    public String getConnettori() {
        return Connettori;
    }

    public void setConnettori(String Connettori) {
        this.Connettori = Connettori;
    }
    
    public String getRete() {
        return Rete;
    }

    public void setRete(String Rete) {
        this.Rete = Rete;
    }
    
    public String getBatteria() {
        return Batteria;
    }

    public void setBatteria(String Batteria) {
        this.Batteria = Batteria;
    }
    
    public String getDimPes() {
        return DimPes;
    }

    public void setDimPes(String DimPes) {
        this.DimPes = DimPes;
    }
    
    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }
    
    public String getAcqua() {
        return Acqua;
    }

    public void setAcqua(String Acqua) {
        this.Acqua = Acqua;
    }
    
    
    
    
    public void setQuantity(int newQuantity) {
		quantity = newQuantity;
	}
	
	public void decreaseQuantity() {
		if (quantity > 0) {
			quantity--;
		}
	}
	
	public void addQuantity() {
		quantity++;
	}
	
	public int getQuantity() {
		return quantity;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
