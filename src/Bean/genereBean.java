package Bean;

public class genereBean {
	private int id ;
	private String nome;
	private String descrizione;
	private int id_prodotto;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getId_prodotto() {
		return id_prodotto;
	}
	public void setId_prodotto(int id_prodotto) {
		this.id_prodotto = id_prodotto;
	}
	@Override
	public String toString() {
		return "genereBean [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", id_prodotto="
				+ id_prodotto + "]";
	}
}
