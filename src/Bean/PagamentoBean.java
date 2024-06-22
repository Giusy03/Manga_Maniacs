package Bean;

public class PagamentoBean {

	
	private String numeroCarta;
	private String nomeTitolare;
	private String cognomeTitoare;
	private int cvv;
	private String scadenza;
	private int idUtente;
	
	
	public PagamentoBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getNumeroCarta() {
		return numeroCarta;
	}


	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}


	public String getNomeTitolare() {
		return nomeTitolare;
	}


	public void setNomeTitolare(String nomeTitolare) {
		this.nomeTitolare = nomeTitolare;
	}


	public String getCognomeTitoare() {
		return cognomeTitoare;
	}


	public void setCognomeTitoare(String cognomeTitoare) {
		this.cognomeTitoare = cognomeTitoare;
	}


	public int getCvv() {
		return cvv;
	}


	public void setCvv(int cvv) {
		this.cvv = cvv;
	}


	public String getScadenza() {
		return scadenza;
	}


	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}


	public int getIdUtente() {
		return idUtente;
	}


	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}


	@Override
	public String toString() {
		return "PagamentoBean [numeroCarta=" + numeroCarta + ", nomeTitolare=" + nomeTitolare + ", cognomeTitoare="
				+ cognomeTitoare + ", cvv=" + cvv + ", scadenza=" + scadenza + ", idUtente=" + idUtente + "]";
	}
	
	
	
}
