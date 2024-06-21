package bean;


import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;



public class orderBean {

		private int id;
		private int id_utente;
		private double iva_tot;
		private double somma_tot;
		private  String data_ordine;
		private ArrayList<mangaBean> manga;
		
		
		

		public orderBean() {
			
		
			manga = new ArrayList<mangaBean>();
			
		}

		public ArrayList<mangaBean> getManga() {
			return manga;
		}

		public void setManga(ArrayList<mangaBean> manga) {
			this.manga = manga;
		}

		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public int getId_utente() {
			return id_utente;
		}
		
		public void setId_utente(int id_utente) {
			this.id_utente = id_utente;
		}
		
		public double getIva_tot() {
			return iva_tot;
		}
		
		public void setIva_tot(double iva_tot) {
			this.iva_tot = iva_tot;
		}
		
		public double getSomma_tot() {
			return somma_tot;
		}
		
		public void setSomma_tot(double somma_tot) {
			this.somma_tot = somma_tot;
		}
		
		public String  getData_ordine() {
			return data_ordine;
		}
		
		public void setData_ordine(String data_ordine) {
			
			this.data_ordine = data_ordine;
			
		}

}
