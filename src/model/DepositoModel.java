package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import Bean.depositoBean;



public class DepositoModel {
private static final String TABLE_NAME = "deposito";
private static final String TABLE_NAME_CONTEINER = "collocamento";

	
	public synchronized Collection<depositoBean> doRetrieveAll()  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<depositoBean> beans = new LinkedList<depositoBean>();
		
		
		String selectSQL = "SELECT * FROM " + DepositoModel.TABLE_NAME;
		
		
		
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				 depositoBean deposito = new depositoBean();
			
				deposito.setId(rs.getInt("id"));
				
				deposito.setNome((String)rs.getString("nome"));
				
				
				
				beans.add(deposito);
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
	
	public synchronized Collection<depositoBean> RetrieveAllByIdManga(int id) throws SQLException {
	    Connection connessione = null;
	    PreparedStatement statement = null;
	    Collection<depositoBean> beans = new LinkedList<depositoBean>();

	    String selectSQL = "SELECT id_deposito, id_manga, disponibilita, nome FROM mangamaniacs.manga"
	        + " INNER JOIN mangamaniacs.collocamento ON manga.id = collocamento.id_manga"
	        + " JOIN mangamaniacs.deposito ON deposito.id = collocamento.id_deposito"
	        + " WHERE manga.id = ?"
	        + " GROUP BY id_deposito, id_manga, disponibilita, nome";

	    try {
	        connessione = DriverManagerConnectionPool.getConnection();
	        statement = connessione.prepareStatement(selectSQL);
	        statement.setInt(1, id);

	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            depositoBean deposito = new depositoBean();
	            deposito.setId(rs.getInt("id_deposito"));
	            deposito.setId_prodotto(rs.getInt("id_manga"));
	            deposito.setNome(rs.getString("nome"));
	            deposito.setQuantity(rs.getInt("disponibilita"));
	            beans.add(deposito);
	        }
	    } finally {
	        try {
	            if (statement != null) statement.close();
	        } finally {
	            DriverManagerConnectionPool.releaseConnection(connessione);
	        }
	    }
	    return beans;
	}

	public synchronized boolean CollocamentoEsiste(depositoBean deposito) throws SQLException {
	    Connection connessione = null;
	    PreparedStatement statement = null;

	    String selectSQL = "SELECT id_deposito, id_manga, disponibilita, nome FROM mangamaniacs.manga"
	        + " INNER JOIN mangamaniacs.collocamento ON manga.id = collocamento.id_manga"
	        + " JOIN mangamaniacs.deposito ON deposito.id = collocamento.id_deposito"
	        + " WHERE manga.id = ? AND collocamento.id_deposito = ?"
	        + " GROUP BY id_deposito, id_manga, disponibilita, nome";

	    try {
	        connessione = DriverManagerConnectionPool.getConnection();
	        statement = connessione.prepareStatement(selectSQL);
	        statement.setInt(1, deposito.getId_prodotto());
	        statement.setInt(2, deposito.getId());

	        ResultSet rs = statement.executeQuery();
	        return rs.next();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	        } finally {
	            DriverManagerConnectionPool.releaseConnection(connessione);
	        }
	    }
	}
	
	public synchronized Collection<depositoBean> notInRetrieveAllByIdManga(int id)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<depositoBean> beans = new LinkedList<depositoBean>();
		
		
		String selectSQL = "SELECT id,nome FROM mangamaniacs.deposito "
				+ "where id not in ( "
				+ "Select id_deposito FROM mangamaniacs.manga "
				+ "inner join mangamaniacs.collocamento "
				+ "on manga.id=collocamento.id_manga "
				+ "Join mangamaniacs.deposito "
				+ "on deposito.id=collocamento.id_deposito "
				+ "where manga.id='"+id+"' ) ";
				
				
			
          		
		
		
		
		
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				 depositoBean deposito = new depositoBean();
			
				deposito.setId(rs.getInt("id"));
				deposito.setId_prodotto(id);
				deposito.setNome((String)rs.getString("nome"));
				
				
				
				
				beans.add(deposito);
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
	public synchronized boolean modifyConteiner(depositoBean deposito)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =  "UPDATE collocamento SET disponibilita = ? WHERE id_manga = ? && id_deposito= ?";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			
			statement.setInt(1,deposito.getQuantity());
			statement.setInt(2,deposito.getId_prodotto());
			statement.setInt(3,deposito.getId());
			
		
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
		
public synchronized boolean addConteiner(depositoBean deposito)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =   "INSERT INTO " + DepositoModel.TABLE_NAME_CONTEINER
				+ " (id_deposito,id_manga,disponibilita) VALUES ( ?, ?,?)";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			
		
			statement.setInt(1,deposito.getId());
			statement.setInt(2,deposito.getId_prodotto());
			statement.setInt(3,deposito.getQuantity());
		
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

}

	
