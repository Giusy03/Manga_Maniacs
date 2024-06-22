package model;


import java.io.IOException;
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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import Bean.OrderBean;
import Bean.UserBean;
import Bean.mangaBean;
import Bean.addressBean;
import Bean.PagamentoBean;
import model.DriverManagerConnectionPool;
import model.CartModel;
import java.time.LocalDate;   



public class OrderModel {
	private static final String TABLE_NAME = "ordine";

	
	public Collection<OrderBean> AllUserOrder(int id) throws SQLException{
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<OrderBean> ordini = new LinkedList<OrderBean>();
		Collection<mangaBean> mangas = new LinkedList<mangaBean>();
		
		String sql = "SELECT * FROM " +TABLE_NAME +" WHERE id_utente = '" + id + "'";
		
		System.out.println(sql);
		System.out.println("id utente:" + id);
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			
			OrderBean order = new OrderBean();
			
			order.setId(rs.getInt("id"));
			order.setSomma_tot(rs.getDouble("somma_tot"));
			order.setData_ordine(rs.getString("data_ordine"));
			order.setNumeroCarta(rs.getString("numeroCarta"));
			order.setIndirizzo(rs.getString("indirizzo"));
			
			ordini.add(order);
			
			
			
			
		}
		DriverManagerConnectionPool.releaseConnection(connessione);
		
		return ordini;
	}
	
	public Collection<OrderBean> AllAdminOrder() throws SQLException{
		
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<OrderBean> ordini = new LinkedList<OrderBean>();
		
		String sql = "SELECT * FROM " + TABLE_NAME;
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			
			OrderBean order = new OrderBean();
			
			order.setId(rs.getInt("id"));
			order.setIva_tot(rs.getDouble("iva_tot"));
			order.setSomma_tot(rs.getDouble("somma_tot"));
			order.setData_ordine(rs.getString("data_ordine"));
			order.setNumeroCarta(rs.getString("numeroCarta"));
			order.setIndirizzo(rs.getString("indirizzo"));
			
			ordini.add(order);
			
			
		}
		DriverManagerConnectionPool.releaseConnection(connessione);
		
		return ordini;
	}
	
	public void doSave (OrderBean order, CartModel cart) throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		
		String sql = "INSERT INTO "+ TABLE_NAME + "(id_utente ,iva_tot, somma_tot, data_ordine, numeroCarta, indirizzo) VALUES (? ,?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO contiene (id_manga, id_ordine, iva_acquisto, prezzo_acquisto, quantita,TitoloManga) VALUES (?,?,?,?,?,?)";
		System.out.println(sql2);
		
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(sql);
		
		statement.setInt(1, order.getId_utente());
		statement.setDouble(2, model.CartModel.calcolaIvaTotale());
		statement.setDouble(3, model.CartModel.calcolaTotale());
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
		LocalDate data_ordine = LocalDate.now();
		statement.setDate(4, Date.valueOf(data_ordine));
		statement.setString(5, order.getNumeroCarta());
		statement.setString(6, order.getIndirizzo());
		
		
		statement.executeUpdate();
		System.out.println(statement.toString());
		connessione.commit();
		
		statement = connessione.prepareStatement(sql2);
		
		for(int i=0; i<cart.size(); i++) {
			mangaBean manga = cart.returnproductByIndex(i);
			statement.setInt(1, manga.getId());
			statement.setInt(2, this.ultimoOrdine().getId());
			statement.setDouble(3, manga.getIva());
			statement.setDouble(4, manga.getPrezzo());
			statement.setInt(5, cart.returnquantityByIndex(i));
			statement.setString(6,manga.getTitolo() );
			statement.executeUpdate();
		
		}
		connessione.commit();
		
		DriverManagerConnectionPool.releaseConnection(connessione);
		
		
	}
	
	public synchronized OrderBean ultimoOrdine()  throws SQLException {
	    Connection connessione = null;
	    PreparedStatement statement = null;

	    String selectSQL = "SELECT * FROM "+TABLE_NAME+" ORDER BY id desc";


//	    System.out.println(selectSQL);
	    try {
	    connessione = DriverManagerConnectionPool.getConnection();
	    statement = connessione.prepareStatement(selectSQL);

	    ResultSet rs = statement.executeQuery(selectSQL);

	    if(rs.next()){
	    	OrderBean order =new OrderBean();

	        order.setId(rs.getInt("id"));

	        order.setId_utente(rs.getInt("id_utente"));
	        order.setIva_tot(rs.getDouble("iva_tot"));
	        order.setSomma_tot(rs.getDouble("somma_tot"));
	        order.setData_ordine(rs.getString("data_ordine"));
	        order.setNumeroCarta(rs.getString("numeroCarta"));
			order.setIndirizzo(rs.getString("indirizzo"));
			



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
	
	public OrderBean viewOrderDetails(int id) throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		OrderBean order = new OrderBean();
		ArrayList<mangaBean> mangas = new ArrayList<mangaBean>();
		System.out.println("Entro nel dettaglio");
		
		String selectSQL = "SELECT id_manga, iva_acquisto, prezzo_acquisto,TitoloManga, quantita FROM mangamaniacs.contiene "
			+"where id_ordine = '" + id + "'";
		System.out.println(selectSQL);
		
		
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE id ='" + id + "'";
		
		connessione = DriverManagerConnectionPool.getConnection();
		
		statement = connessione.prepareStatement(selectSQL);
		
		
		
		 ResultSet rs = statement.executeQuery(selectSQL);
		 
		 while(rs.next()) {
			 
			mangaBean manga = new mangaBean();
			manga.setId(rs.getInt("id_manga"));
			manga.setTitolo(rs.getString("TitoloManga"));
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
				order.setNumeroCarta(rs.getString("numeroCarta"));
				order.setIndirizzo(rs.getString("indirizzo"));
				
				
				
			}
			
		 return order;
	}
	
	public PDDocument generateInvoice(OrderBean order, UserBean user, int id) throws SQLException, IOException {
		
		
		
		//Creating the PDF
		PDDocument MyPDF = new PDDocument();
		//Creating a Blank Page
		PDPage newpage = new PDPage();
		//Adding the blank page to our PDF
		MyPDF.addPage(newpage);
		
		//Getting the required Page
		//0 index for the first page
		PDPage mypage = MyPDF.getPage(0);
		//Initializing the content stream
		PDPageContentStream cs = new PDPageContentStream(MyPDF, mypage);
		
		cs.beginText(); 
		cs.setFont(PDType1Font.TIMES_ROMAN, 18); 
		cs.newLineAtOffset(260, 670);
		//String variable to store the text
		String InvoiceTitle = new String("Ordine n�" + id);
		//Writing the text to the PDF File
		cs.showText(InvoiceTitle);
		cs.endText();
		
		//INFO UTENTE
		 cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	     cs.setLeading(20f);
	     cs.newLineAtOffset(60, 610);
	     cs.showText("Cliente: " + user.getNome() + " "  + user.getCognome());
	     cs.endText();
	     
	     //TABELLA PRODOTTI
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	     cs.newLineAtOffset(80, 540);
	     cs.showText("Product Name");
	     cs.endText();
	     
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	     cs.newLineAtOffset(300, 540);
	     cs.showText("Unit Price");
	     cs.endText();
	     
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	     cs.newLineAtOffset(400, 540);
	     cs.showText("Quantity");
	     cs.endText();
	     
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	     cs.newLineAtOffset(500, 540);
	     cs.showText("IVA");
	     cs.endText();
	     
	     //INSERISCO PRODOTTI
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	     cs.setLeading(20f);
	     cs.newLineAtOffset(80, 520);
	     for( mangaBean manga:order.getManga() ) {
	       cs.showText(manga.getTitolo());
	       cs.newLine();
	     }
	     cs.endText();
	     
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	     cs.setLeading(20f);
	     cs.newLineAtOffset(315, 520);
	     for( mangaBean manga:order.getManga() ) {
	       cs.showText(Double.toString(manga.getPrezzo()));
	       cs.newLine();
	     }
	     cs.endText();
	     
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	     cs.setLeading(20f);
	     cs.newLineAtOffset(425, 520);
	     for( mangaBean manga:order.getManga() ) {
	       cs.showText(Integer.toString(manga.getQuantita()));
	       cs.newLine();
	     }
	     cs.endText();
	     
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	     cs.setLeading(20f);
	     cs.newLineAtOffset(500, 520);
	     for( mangaBean manga:order.getManga() ) {
	       cs.showText(Double.toString(manga.getIva()) + "%");
	       cs.newLine();
	     }
	     cs.endText();
	     
	     //INDRIZZO
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	     cs.setLeading(20f);
	     cs.newLineAtOffset(60, 400);
	     cs.showText("Indirizzo: " + order.getIndirizzo());
	     cs.newLine();
	     cs.endText();
	     
	     //METODO DI PAGAMENTO
	     cs.beginText();
	     cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	     cs.setLeading(20f);
	     cs.newLineAtOffset(60, 380);
	     cs.showText("Metodo Di Pagamento: " + order.getNumeroCarta());
	     cs.newLine();
	     cs.endText();
	     
	     //TOTALE
	     cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(460, 355);
	      cs.showText("Total: ");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      //Calculating where total is to be written using number of products
	      cs.newLineAtOffset(500, 355);
	      cs.showText(Double.toString(order.getSomma_tot()) + " Euro");
	      cs.endText();
	      
	      cs.close();
		
	      return MyPDF;
	      
		
	}
}
