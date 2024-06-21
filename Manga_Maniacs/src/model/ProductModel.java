package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import bean.mangaBean;

import model.DriverManagerConnectionPool;


public class ProductModel  {
	static genereModel modelGen = new genereModel() ;
	private static final String TABLE_NAME = "manga";
	

	
	public synchronized Collection<mangaBean> doRetrieveAll(String sort)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<mangaBean> beans = new LinkedList<mangaBean>();
		
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  unlimitedmanga.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  unlimitedmanga.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n" +
				" where disponibilita>0"+
				" GROUP BY manga.id";
		
		
		if (sort != null && !sort.equals("")) {
			selectSQL=selectSQL+" order by "+sort;
		}
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				mangaBean manga = new mangaBean();
			
				manga.setId(rs.getInt("id"));
				
				
				
				manga.setTitolo((String)rs.getString("Titolo"));
				manga.setPrezzo(rs.getDouble("prezzo"));
				manga.setEditore(rs.getString("editore"));
				manga.setAutore(rs.getString("autore"));
				manga.setIva(rs.getDouble("iva"));
				manga.setDescrizione(rs.getString("descrizione"));
				manga.setImg(rs.getString("img"));
				manga.setInv(rs.getBoolean("inv"));
				manga.setQuantita(rs.getInt("quantita"));
				
				
				beans.add(manga);
			}
	}finally {
		try {
			if (statement != null)
				statement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connessione);
		}

	}
		
		if (beans != null && beans.size() != 0) {
			Iterator<?> it = beans.iterator();
			while (it.hasNext()) {
				mangaBean bean = (mangaBean) it.next();
				try {
					
					
					
					bean.setGeneri(modelGen.RetrieveAllByIdManga(bean.getId()));
					
					
					
				} catch (  SQLException e) {
					
					System.out.println("Error:" + e.getMessage());
				}

			}
		}
		
		System.out.println(beans.toString());
		return beans;
	}
	
	public synchronized Collection<mangaBean> doRetrieveAllGenere(int genere )  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<mangaBean> beans = new LinkedList<mangaBean>();
		
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  unlimitedmanga.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  unlimitedmanga.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n"
				+ "join unlimitedmanga.appartiene"
				+ "   on appartiene.id_manga= manga.id";
				System.out.println("genere id =="+genere);
		if(genere>=0)
			selectSQL+=" where disponibilita>0 and  appartiene.id_genere = " + genere ;
		else
			selectSQL+= " where disponibilita>0";
		

               selectSQL+=" GROUP BY manga.id";
            
			
					
				
		System.out.println(selectSQL);
		
		
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				mangaBean manga = new mangaBean();
			
				manga.setId(rs.getInt("id"));
				
				
				
				manga.setTitolo((String)rs.getString("Titolo"));
				manga.setPrezzo(rs.getDouble("prezzo"));
				manga.setEditore(rs.getString("editore"));
				manga.setAutore(rs.getString("autore"));
				manga.setIva(rs.getDouble("iva"));
				manga.setDescrizione(rs.getString("descrizione"));
				manga.setImg(rs.getString("img"));
				manga.setInv(rs.getBoolean("inv"));
				manga.setQuantita(rs.getInt("quantita"));
				
				
				beans.add(manga);
			}
	}finally {
		try {
			if (statement != null)
				statement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connessione);
		}

	}
		
		if (beans != null && beans.size() != 0) {
			Iterator<?> it = beans.iterator();
			while (it.hasNext()) {
				mangaBean bean = (mangaBean) it.next();
				try {
					
					
					
					bean.setGeneri(modelGen.RetrieveAllByIdManga(bean.getId()));
					
					
					
				} catch (  SQLException e) {
					
					System.out.println("Error:" + e.getMessage());
				}

			}
		}
		return beans;
	}
	
	
	
	
	
	
	
	
	
	
	public synchronized Collection<mangaBean> doRetrieveAllAdmin(String sort)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<mangaBean> beans = new LinkedList<mangaBean>();
		
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  unlimitedmanga.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  unlimitedmanga.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n" +
				
				" GROUP BY manga.id";
		
		
		if (sort != null && !sort.equals("")) {
			selectSQL=selectSQL+" order by "+sort;
		}
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				mangaBean manga = new mangaBean();
			
				manga.setId(rs.getInt("id"));
				
				
				
				manga.setTitolo((String)rs.getString("Titolo"));
				manga.setPrezzo(rs.getDouble("prezzo"));
				manga.setEditore(rs.getString("editore"));
				manga.setAutore(rs.getString("autore"));
				manga.setIva(rs.getDouble("iva"));
				manga.setDescrizione(rs.getString("descrizione"));
				manga.setImg(rs.getString("img"));
				manga.setInv(rs.getBoolean("inv"));
				manga.setQuantita(rs.getInt("quantita"));
				
				
				beans.add(manga);
			}
	}finally {
		try {
			if (statement != null)
				statement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connessione);
		}

	}
		if (beans != null && beans.size() != 0) {
			Iterator<?> it = beans.iterator();
			while (it.hasNext()) {
				mangaBean bean = (mangaBean) it.next();
				try {
					
					
					
					bean.setGeneri(modelGen.RetrieveAllByIdManga(bean.getId()));
					
					
					
				} catch (  SQLException e) {
					
					System.out.println("Error:" + e.getMessage());
				}

			}
		}
		return beans;
	}
	public synchronized void doSave(mangaBean bean)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		
		String insertSQL = "INSERT INTO " + ProductModel.TABLE_NAME
				+ " ( Titolo,prezzo ,editore,autore,iva,descrizione,img) VALUES ( ?, ?, ?, ?, ?,?,?)";
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(insertSQL);
			
			statement.setString(1,bean.getTitolo());
			statement.setDouble(2,bean.getPrezzo() );
			statement.setString(3,bean.getEditore());
			statement.setString(4,bean.getAutore());
			statement.setDouble(5,bean.getIva() );
			statement.setString(6,bean.getDescrizione() );
			statement.setString(7,bean.getImg() );
			
			
						

			statement.executeUpdate();

			connessione.commit();
		
	}finally {
		try {
			if (statement != null)
				statement.close();
		} finally {
			if (connessione != null)
				connessione.close();
		}
	}
	}
	public synchronized mangaBean selectManga(int id)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  unlimitedmanga.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  unlimitedmanga.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n" +
				" where disponibilita>0 && manga.id= '"+id+"'"+
				" GROUP BY manga.id";
		
//		System.out.println(selectSQL);
		try {
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(selectSQL);
		
		ResultSet rs = statement.executeQuery(selectSQL);
		
		if(rs.next()){
			mangaBean manga =new mangaBean();
			
			manga.setId(rs.getInt("id"));
			
			
			
			manga.setTitolo((String)rs.getString("Titolo"));
			manga.setPrezzo(rs.getDouble("prezzo"));
			manga.setEditore(rs.getString("editore"));
			manga.setAutore(rs.getString("autore"));
			manga.setIva(rs.getDouble("iva"));
			manga.setDescrizione(rs.getString("descrizione"));
			manga.setImg(rs.getString("img"));
			manga.setInv(rs.getBoolean("inv"));
			manga.setQuantita(rs.getInt("quantita"));
			
			
		return manga;
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
	public synchronized mangaBean selectMangaAdmin(int id)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  unlimitedmanga.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  unlimitedmanga.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n" +
				" where   manga.id= '"+id+"'"+
				" GROUP BY manga.id";
		
//		System.out.println(selectSQL);
		try {
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(selectSQL);
		
		ResultSet rs = statement.executeQuery(selectSQL);
		
		if(rs.next()){
			mangaBean manga =new mangaBean();
			
			manga.setId(rs.getInt("id"));
			
			
			
			manga.setTitolo((String)rs.getString("Titolo"));
			manga.setPrezzo(rs.getDouble("prezzo"));
			manga.setEditore(rs.getString("editore"));
			manga.setAutore(rs.getString("autore"));
			manga.setIva(rs.getDouble("iva"));
			manga.setDescrizione(rs.getString("descrizione"));
			manga.setImg(rs.getString("img"));
			manga.setInv(rs.getBoolean("inv"));
			manga.setQuantita(rs.getInt("quantita"));
			
			
		return manga;
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


public synchronized boolean doDelete(int id)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =  " DELETE FROM unlimitedmanga."+ProductModel.TABLE_NAME+" WHERE manga.id = ? ";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			statement.setInt(1,id);
		
			System.out.println((String)statement.toString());

			result=statement.executeUpdate();

			connessione.commit();
		
	}finally {
		try {
			if (statement != null)
				statement.close();
		} finally {
			if (connessione != null)
				connessione.close();
		}
	}
		return (result != 0);
	}
public synchronized boolean modifyManga(mangaBean manga)  throws SQLException {
	
	Connection connessione = null;
	PreparedStatement statement = null;
	int result = 0;
	
	String deletSQL =  "UPDATE manga SET Titolo = ?, prezzo = ?, editore = ?, autore = ?, iva = ?, descrizione = ?,  inv = ? WHERE id = ?";
			
	try {
		System.out.println(deletSQL);
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(deletSQL);
		
		statement.setString(1,manga.getTitolo());
		statement.setDouble(2,manga.getPrezzo());
		statement.setString(3,manga.getEditore());
		statement.setString(4,manga.getAutore());
		statement.setDouble(5,manga.getIva());
		statement.setString(6,manga.getDescrizione());
		
		statement.setBoolean(7,manga.isInv());
		statement.setInt(8,manga.getId());
	
		System.out.println((String)statement.toString());

		result=statement.executeUpdate();
System.out.println("risultalto "+result);
		connessione.commit();
	
}finally {
	try {
		if (statement != null)
			statement.close();
	} finally {
		if (connessione != null)
			connessione.close();
	}
}
	return (result != 0);
}
public synchronized mangaBean ultimeManda()  throws SQLException {
	Connection connessione = null;
	PreparedStatement statement = null;
	
	String selectSQL = "SELECT * FROM "+ProductModel.TABLE_NAME+" ORDER BY id desc";
	
	
//	System.out.println(selectSQL);
	try {
	connessione = DriverManagerConnectionPool.getConnection();
	statement = connessione.prepareStatement(selectSQL);
	
	ResultSet rs = statement.executeQuery(selectSQL);
	
	if(rs.next()){
		mangaBean manga =new mangaBean();
		
		manga.setId(rs.getInt("id"));
		
		
		
		manga.setTitolo((String)rs.getString("Titolo"));
		manga.setPrezzo(rs.getDouble("prezzo"));
		manga.setEditore(rs.getString("editore"));
		manga.setAutore(rs.getString("autore"));
		manga.setIva(rs.getDouble("iva"));
		manga.setDescrizione(rs.getString("descrizione"));
		manga.setImg(rs.getString("img"));
		manga.setInv(rs.getBoolean("inv"));
		
		
		
	return manga;
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


}