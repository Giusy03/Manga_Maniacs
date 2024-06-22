package model;
import java.util.ArrayList;
import java.util.List;

import Bean.mangaBean;


public class CartModel {

		private static ArrayList<mangaBean> products;
		private static  ArrayList<Integer> quantity;

		public CartModel() {
			products = new ArrayList<mangaBean>();
			quantity = new ArrayList<Integer>();
		}
		
		public void addProduct(mangaBean manga) {
			for(int i = 0; i < products.size(); i++) {
				mangaBean mangaContenitore = products.get(i);
				if(manga.getId()==mangaContenitore.getId())
				{
				
					System.out.println("Oggetto creato aggiunto");
					return;
				}
			
						
			}
			products.add(manga);
			quantity.add(1);
			System.out.println("Oggetto carrello creato");
		}
		
		public void deleteProduct(mangaBean manga) {
			int i=0;
			for(mangaBean prod : products) {
				if(prod.getId() == manga.getId()) {
					
					remove(i);
					break;
				}
				i++;
			}
		}

		public ArrayList<mangaBean> getProducts() {
			return products;
		}
		public ArrayList<Integer> getQuantity() {
			return quantity;
		}
		
		
		public int size() {
			return products.size();
		}
		
		public void remove(int index) {
			products.remove(index);
			quantity.remove(index);
		}
		
		public void removeAll() {
			products.removeAll(products);
			quantity.removeAll(quantity);
		}
		public void setquantity(int clickP, int pos) {
			quantity.set(pos, clickP);
		}
		
		
		public mangaBean returnproductByIndex(int index) {
			return(products.get(index));
		}
		public int returnquantityByIndex(int index) {
			return(quantity.get(index));
		}
		public int totProdotti() {
			int tot=0;
			for(mangaBean prod : products) {
				
			tot++;
			}
				
			return tot;
		}
		public boolean prodottoExist(int id) {
			
			for(mangaBean prod : products) {
			if(id==prod.getId())	
				return true;
			}
				
			return false;
		}
		
		
		
		public static double calcolaTotale() {
			
			double totale = 0.0;
			
			
			for(int i=0; i<products.size(); i++) {
				mangaBean manga = products.get(i);
				
				double prezzo = manga.getPrezzo();
				double iva = manga.getIva();
				
				
				totale += prezzo + ((prezzo*iva)/100)* quantity.get(i);
				
			}
			
			return totale;
			
		}
		public static double calcolaIvaTotale() {
			
			double ivatotale = 0.0;
			
			
			for(int i=0; i<products.size(); i++) {
				mangaBean manga = products.get(i);
				
				double prezzo = manga.getPrezzo();
				double iva = manga.getIva();
				
				
				ivatotale += ((prezzo*iva)/100)* quantity.get(i);
				
			}
			
			return ivatotale;
			
		}

		
		
		
}
