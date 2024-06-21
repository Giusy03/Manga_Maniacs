package model;
import java.util.ArrayList;
import java.util.List;

import bean.mangaBean;


public class CartModel {

		private static List<mangaBean> products;
		private static  List<Integer> quantity;

		public CartModel() {
			products = new ArrayList<mangaBean>();
			quantity = new ArrayList<Integer>();
		}
		
		public void addProduct(mangaBean manga) {
			for(int i = 0; i < products.size(); i++) {
				mangaBean mangaContenitore = products.get(i);
				if(manga.getId()==mangaContenitore.getId())
				{
					Integer cont= quantity.get(i);
					cont++;
					 System.out.println("Index oggetto creato i=="+i);
				 quantity.add(i,cont);
				 System.out.println("Oggetto creato aggiunto");
				 return;
				}
			
						
			}
			products.add(manga);
			quantity.add(1);
			System.out.println("Oggetto carrello creato");
		}
		
		public void deleteProduct(mangaBean manga) {
			for(mangaBean prod : products) {
				if(prod.getId() == manga.getId()) {
					products.remove(prod);
					break;
				}
			}
		}

		public List<mangaBean> getProducts() {
			return products;
		}
		public List<Integer> getQuantity() {
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
		
		
		
		public static double calcolaTotale() {
			
			double totale = 0.0;
			
			for(int i=0; i<products.size(); i++) {
				
				totale += products.get(i).getPrezzo() * quantity.get(i);
				
			}
			
			return totale;
			
		}
		
		
		
}
