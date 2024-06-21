package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.genereBean;


public class genereModel {
private static final String TABLE_NAME = "genere";
private static final String TABLE_NAME_CONTEINER = "appartiene";

	
	public synchronized Collection<genereBean> doRetrieveAll()  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<genereBean> beans = new LinkedList<genereBean>();
		
		
		String selectSQL = "SELECT * FROM " + genereModel.TABLE_NAME;
		
		
		
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				 genereBean genere = new genereBean();
			
				genere.setId(rs.getInt("id"));
				
				genere.setNome((String)rs.getString("nome"));
				
				
				
				beans.add(genere);
			}
	}finally {
		try {
			if (statement != null)
				statement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connessione);
		}

	}
		return beans;
	}
	public synchronized Collection<genereBean> RetrieveAllByIdManga(int id)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<genereBean> beans = new LinkedList<genereBean>();
		
		
		String selectSQL = "SELECT id_genere,id_manga,nome,nome FROM unlimitedmanga.manga" 
			+"	inner join  unlimitedmanga.appartiene"
			+"	 on manga.id=appartiene.id_manga "
			+"	join  unlimitedmanga.genere "
				+" on genere.id=appartiene.id_genere"
				+" where    manga.id='"+id+"'";
		
		
		
		
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				 genereBean genere = new genereBean();
			
				genere.setId(rs.getInt("id_genere"));
				genere.setId_prodotto(rs.getInt("id_manga"));
				genere.setNome((String)rs.getString("nome"));
			
				
				
				
				beans.add(genere);
			}
	}finally {
		try {
			if (statement != null)
				statement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connessione);
		}

	}
		return beans;
	}
	public synchronized boolean deleteAppartenenza(genereBean genere)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =  "DELETE FROM " + genereModel.TABLE_NAME_CONTEINER + " WHERE id_genere = ?  && id_manga= ? ";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			
		
			statement.setInt(1,genere.getId());
			statement.setInt(2,genere.getId_prodotto());
			
		
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

	public synchronized boolean addAppartenenza(genereBean genere)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =   "INSERT INTO " + genereModel.TABLE_NAME_CONTEINER
				+ " (id_genere,id_manga) VALUES ( ?, ?)";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			
		
			statement.setInt(1,genere.getId());
			statement.setInt(2,genere.getId_prodotto());
			
		
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
	
public synchronized void doSaveGenere(genereBean bean)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		
		String insertSQL = "INSERT INTO " + genereModel.TABLE_NAME
				+ " ( nome,descrizione) VALUES ( ?, ?)";
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(insertSQL);
			
			statement.setString(1,bean.getNome());
			statement.setString(2,bean.getDescrizione());
			
			
			
						

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

public synchronized boolean doDelete(int id)  throws SQLException {
	
	Connection connessione = null;
	PreparedStatement statement = null;
	int result = 0;
	
	String deletSQL =  "DELETE FROM " + genereModel.TABLE_NAME + " WHERE id = ?";
			
	try {
		System.out.println(deletSQL);
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(deletSQL);
		statement.setInt(1,id);
	
		System.out.println((String)statement.toString());

		result=statement.executeUpdate();

	
	
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
	
		

}
