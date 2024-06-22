package Bean;

public class addressBean {

	private int id;
	private String comune;
	private String cap;
	private String via;
	private String provincia;
	private int civico;
	private String id_utente;
	
	public addressBean() {
		id = 0;
		comune = "";
		cap = "";
		via = "";
		provincia = "";
		civico = 0;
		id_utente = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getCivico() {
		return civico;
	}

	public void setCivico(int civico) {
		this.civico = civico;
	}

	public String getId_utente() {
		return id_utente;
	}

	public void setId_utente(String id_utente) {
		this.id_utente = id_utente;
	}
	
	

}
