package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import java.util.LinkedList;

import Bean.addressBean;
public class AddressModel {
	public Collection<addressBean> addressCatalog() throws SQLException{
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<addressBean> indirizzi = new LinkedList<addressBean>();
		String query = "SELECT * FROM indirizzo";
		
		try {
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(query);
			System.out.println("Indirizzo: " + statement);

			ResultSet rs = statement.executeQuery(query);

			while(rs.next()) {
				addressBean bean = new addressBean();
				bean.setId(rs.getInt("id"));
				bean.setComune(rs.getString("comune"));
				bean.setCap(rs.getString("cap"));
				bean.setCivico(rs.getInt("civico"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setVia(rs.getString("via"));
				bean.setId_utente(rs.getString("id_utente"));
				indirizzi.add(bean);
			}
		}finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connessione);
			}
		}
		return indirizzi;
	}
	
	//questo metodo restituisce tutti gli indirizzi riferiti ad un utente
	
	public Collection<addressBean> addressCatalogByID(String id_utente) throws SQLException{
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<addressBean> indirizzi = new LinkedList<addressBean>();
		String query = "SELECT * FROM indirizzo WHERE id_utente = '" + id_utente + "'";
		
		try {
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(query);
			System.out.println("Indirizzo: " + statement);

			ResultSet rs = statement.executeQuery(query);

			while(rs.next()) {
				addressBean bean = new addressBean();
				bean.setId(rs.getInt("id"));
				bean.setComune(rs.getString("comune"));
				bean.setCap(rs.getString("cap"));
				bean.setCivico(rs.getInt("civico"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setVia(rs.getString("via"));
				bean.setId_utente(rs.getString("id_utente"));
				indirizzi.add(bean);
			}
		}finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connessione);
			}
		}
		return indirizzi;
	}
	
	public addressBean addressByID(int id) throws SQLException{
		Connection connessione = null;
		PreparedStatement statement = null;
		addressBean address = null;
		String query = "SELECT * FROM indirizzo WHERE id = '" + id + "'";
		
		try {
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(query);
			System.out.println("Indirizzo: " + statement);

			ResultSet rs = statement.executeQuery(query);

			while(rs.next()) {
				address = new addressBean();
				address.setId(rs.getInt("id"));
				address.setComune(rs.getString("comune"));
				address.setCap(rs.getString("cap"));
				address.setCivico(rs.getInt("civico"));
				address.setProvincia(rs.getString("provincia"));
				address.setVia(rs.getString("via"));
				address.setId_utente(rs.getString("id_utente"));
			}
		}finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connessione);
			}
		}
		return address;
	}
	
	//metodo che aggiunge un indirizzo e lo collega ad un utente
	
	public boolean insert(String comune, int civico, String via, String cap, String provincia,
			int id_utente) {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		String query = "INSERT INTO indirizzo (comune, provincia, cap, via, civico, id_utente)" +
				" VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(query);
			statement.setString(1, comune);
			statement.setString(2, provincia);
			statement.setString(3, cap);
			statement.setString(4, via);
			statement.setLong(5, civico);
			statement.setInt(6, id_utente);
			System.out.println("Registrazione " + statement);
			System.out.println(statement.executeUpdate());
			connessione.commit();
			return true;
		}
		catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		finally {
			try {
				if (statement != null)
					try {
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} finally {
				try {
					DriverManagerConnectionPool.releaseConnection(connessione);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
public synchronized boolean doDelete(int id)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =  " DELETE FROM mangamaniacs.indirizzo  WHERE indirizzo.id = ? ";
				
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

}
