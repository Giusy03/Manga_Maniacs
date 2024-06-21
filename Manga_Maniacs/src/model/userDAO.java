package model;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.userBean;

import model.DriverManagerConnectionPool;

public class userDAO {
	private static final String TABLE_NAME = "utente";
	/*public void selectTable( )  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		
		ResultSet ris ;
		System.out.println("0");
		String query ="SELECT * FROM utente ";
		try {
			System.out.println("1");
			connessione =DriverManagerConnectionPool.getConnection();
			System.out.println("2");
			statement = connessione.prepareStatement(query);
			System.out.println("3");
			ris =statement.executeQuery();
			System.out.println("4");
			if (ris.next()) {
				System.out.println(ris.getString("username"));
			}
			
		}finally {
			try {
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connessione);
			}
		}
		}*/
		
	
	
	public userBean signIn(String username, String password) throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		userBean utente;
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE username = '" + username + "' && psw = '" + password +"'";
		try {
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(query);
			

			ResultSet rs = statement.executeQuery(query);

			if(rs.next()) {
		
				utente = new userBean();
				utente.setId(rs.getInt("id"));
				utente.setUsername(rs.getString("username"));
				
				utente.setPwd(rs.getString("psw"));
				utente.setEmail(rs.getString("email"));
				
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				
				if(rs.getString("ruolo").equalsIgnoreCase("admin"))
				utente.setAmministratore(true);
				else
					utente.setAmministratore(false);
				return utente;
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
	
	public userBean signUp(String username, String password) throws SQLException {
		
			Connection connessione = null;
			PreparedStatement statement = null;
			
			String sql = "INSERT INTO "+TABLE_NAME+" (username, psw)" + " VALUES (?,?)";
			
			try {
				connessione = DriverManagerConnectionPool.getConnection();
				statement= connessione.prepareStatement(sql);
				
				statement.setString(1, username);
				statement.setString(2, password);
				
				System.out.println(statement.toString());
				
				statement.executeUpdate();

				connessione.commit();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
					DriverManagerConnectionPool.releaseConnection(connessione);
				}
			}
		
		return null;
	}
	public synchronized Collection<userBean> RetrieveAll()  throws SQLException {

		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<userBean> beans = new LinkedList<userBean>();
		
		
		String selectSQL  = "SELECT * FROM "+TABLE_NAME ;
		
		
		
		
	
	    
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(selectSQL);
		
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				userBean utente = new userBean();
			
utente.setUsername(rs.getString("username"));
				
				utente.setPwd(rs.getString("psw"));
				utente.setEmail(rs.getString("email"));
				
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				
				if(rs.getString("ruolo").equalsIgnoreCase("admin"))
				utente.setAmministratore(true);
				else
					utente.setAmministratore(false);
				
				
				
				beans.add(utente);
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
	
	public synchronized boolean modifyUtenteDati(userBean user)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =  "UPDATE "+TABLE_NAME+" SET nome = ?, cognome = ?, email = ? WHERE id = ?";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			
			statement.setString(1,user.getNome());
			statement.setString(2,user.getCognome());
			statement.setString(3,user.getEmail());
			statement.setInt(4,user.getId());
			
			
			
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
public synchronized boolean modifyUtentePsw(userBean user,String psw)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =  "UPDATE "+TABLE_NAME+" SET psw = ? WHERE id = ?";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			
			statement.setString(1,psw);
			
			
			statement.setInt(2,user.getId());
			
			
			
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
public synchronized boolean modifyUtenteUsername(userBean user,String Username)  throws SQLException {
	
	Connection connessione = null;
	PreparedStatement statement = null;
	int result = 0;
	
	String deletSQL =  "UPDATE "+TABLE_NAME+" SET username = ? WHERE id = ?";
			
	try {
		System.out.println(deletSQL);
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(deletSQL);
		
		statement.setString(1,Username);
		
		
		statement.setInt(2,user.getId());
		
		
		
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

