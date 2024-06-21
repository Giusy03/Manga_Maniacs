package bean;

import java.util.Collection;

public class mangaBean {
	private int id;
	private String Titolo;
	private Double prezzo;
	private String editore;
	private String autore;
	private Double iva;
	private String descrizione;
	private int quantita;
	private String img=null;
	private boolean inv=false;
	private Collection<genereBean> generi;
	
	
	
	
	@Override
	public String toString() {
		return "mangaBean [id=" + id + ", Titolo=" + Titolo + ", prezzo=" + prezzo + ", editore=" + editore
				+ ", autore=" + autore + ", iva=" + iva + ", descrizione=" + descrizione + ", quantita=" + quantita
				+ ", img=" + img + ", inv=" + inv + ", generi=" + generi + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return Titolo;
	}
	public void setTitolo(String titolo) {
		Titolo = titolo;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public String getEditore() {
		return editore;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public boolean isInv() {
		return inv;
	}
	public void setInv(boolean inv) {
		this.inv = inv;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public Collection<genereBean> getGeneri() {
		return generi;
	}
	public void setGeneri(Collection<genereBean> generi) {
		this.generi = generi;
	}
	
	
	
	
	
	
	
	

}
