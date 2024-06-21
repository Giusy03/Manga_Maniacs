package model;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import bean.orderBean;
import bean.mangaBean;

import model.DriverManagerConnectionPool;
import model.CartModel;
import java.time.LocalDate;   



public class OrderModel {
	private static final String TABLE_NAME = "ordine";

	
	public Collection<orderBean> AllUserOrder(int id) throws SQLException{
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<orderBean> ordini = new LinkedList<orderBean>();
		Collection<mangaBean> mangas = new LinkedList<mangaBean>();
		
		String sql = "SELECT * FROM " +TABLE_NAME +" WHERE id_utente = '" + id + "'";
		
		System.out.println(sql);
		System.out.println("id utente:" + id);
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			
			orderBean order = new orderBean();
			
			order.setId(rs.getInt("id"));
			order.setSomma_tot(rs.getDouble("somma_tot"));
			order.setData_ordine(rs.getString("data_ordine"));
			
			ordini.add(order);
			
			
			
			
		}
		DriverManagerConnectionPool.releaseConnection(connessione);
		
		return ordini;
	}
	
	public Collection<orderBean> AllAdminOrder() throws SQLException{
		
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<orderBean> ordini = new LinkedList<orderBean>();
		
		String sql = "SELECT * FROM " + TABLE_NAME;
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			
			orderBean order = new orderBean();
			
			order.setId(rs.getInt("id"));
			order.setIva_tot(rs.getDouble("iva_tot"));
			order.setSomma_tot(rs.getDouble("somma_tot"));
			order.setData_ordine(rs.getString("data_ordine"));
			ordini.add(order);
			
			
		}
		DriverManagerConnectionPool.releaseConnection(connessione);
		
		return ordini;
	}
	
	public void doSave (orderBean order, CartModel cart) throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		
		String sql = "INSERT INTO "+ TABLE_NAME + "(id_utente ,iva_tot, somma_tot, data_ordine) VALUES (? ,?, ?, ?)";
		String sql2 = "INSERT INTO contiene (id_manga, id_ordine, iva_acquisto, prezzo_acquisto, quantita) VALUES (?,?,?,?,?)";
		System.out.println(sql2);
		
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(sql);
		
		statement.setInt(1, order.getId_utente());
		statement.setDouble(2, order.getIva_tot());
		statement.setDouble(3, model.CartModel.calcolaTotale());
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
		LocalDate data_ordine = LocalDate.now();
		statement.setDate(4, Date.valueOf(data_ordine));
		
		
		statement.executeUpdate();
		connessione.commit();
		
		statement = connessione.prepareStatement(sql2);
		
		for(int i=0; i<cart.size(); i++) {
			mangaBean manga = cart.returnproductByIndex(i);
			statement.setInt(1, manga.getId());
			statement.setInt(2, this.ultimoOrdine().getId());
			statement.setDouble(3, manga.getIva());
			statement.setDouble(4, manga.getPrezzo());
			statement.setInt(5, cart.returnquantityByIndex(i));
			
			statement.executeUpdate();
		
		}
		connessione.commit();
		
		DriverManagerConnectionPool.releaseConnection(connessione);
	}
	
	public synchronized orderBean ultimoOrdine()  throws SQLException {
	    Connection connessione = null;
	    PreparedStatement statement = null;

	    String selectSQL = "SELECT * FROM "+TABLE_NAME+" ORDER BY id desc";


//	    System.out.println(selectSQL);
	    try {
	    connessione = DriverManagerConnectionPool.getConnection();
	    statement = connessione.prepareStatement(selectSQL);

	    ResultSet rs = statement.executeQuery(selectSQL);

	    if(rs.next()){
	    	orderBean order =new orderBean();

	        order.setId(rs.getInt("id"));

	        order.setId_utente(rs.getInt("id_utente"));
	        order.setIva_tot(rs.getDouble("iva_tot"));
	        order.setSomma_tot(rs.getDouble("somma_tot"));
	        order.setData_ordine(rs.getString("data_ordine"));



	    return order;
	    }
	    else {

	        return null;
	    }

	    }finally {
	        try {
	            if (statement != null)
	                statement.close();
	        } finally {
	            DriverManagerConnectionPool.releaseConnection(connessione);
	        }
	    }

	}
	
	public orderBean viewOrderDetails(int id) throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		orderBean order = new orderBean();
		ArrayList<mangaBean> mangas = new ArrayList<mangaBean>();
		System.out.println("Entro nel dettaglio");
		
		String selectSQL = "SELECT id, Titolo, editore, autore, img, iva_acquisto, prezzo_acquisto, quantita FROM unlimitedmanga.manga "
			+"inner join  unlimitedmanga.contiene "
				+"on manga.id=contiene.id_manga " 
				+"where id_ordine = '" + id + "'";
		System.out.println(selectSQL);
		
		
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE id ='" + id + "'";
		
		connessione = DriverManagerConnectionPool.getConnection();
		
		statement = connessione.prepareStatement(selectSQL);
		
		
		
		 ResultSet rs = statement.executeQuery(selectSQL);
		 
		 while(rs.next()) {
			 
			mangaBean manga = new mangaBean();
			manga.setId(rs.getInt("id"));
			manga.setTitolo(rs.getString("Titolo"));
			manga.setEditore(rs.getString("editore"));
			manga.setAutore(rs.getString("autore"));
			manga.setImg(rs.getString("img"));
			manga.setIva(rs.getDouble("iva_acquisto"));
			manga.setPrezzo(rs.getDouble("prezzo_acquisto"));
			manga.setQuantita(rs.getInt("quantita"));
			
			mangas.add(manga);
			System.out.println(manga.toString());
			
			 
		 }
		 
		 order.setManga(mangas);
		 //System.out.println(mangas.toString());
		 
		  rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				
				order.setSomma_tot(rs.getDouble("somma_tot"));
				order.setIva_tot(rs.getDouble("iva_tot"));
				order.setData_ordine(rs.getString("data_ordine"));
				
				
			}
			
		 return order;
	
		
		
	
  }
}
