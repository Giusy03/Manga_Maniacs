package bean;

public class depositoBean {
	
	private int id;
	private String nome;
	private int id_prodotto;
	private int quantity;
	
	
	public int getId_prodotto() {
		return id_prodotto;
	}
	public void setId_prodotto(int id_prodotto) {
		this.id_prodotto = id_prodotto;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "depositoBean [id=" + id + ", nome=" + nome + ", id_prodotto=" + id_prodotto + ", quantity=" + quantity
				+ "]";
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
	

}
