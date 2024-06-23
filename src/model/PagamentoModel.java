package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import Bean.PagamentoBean;

public class PagamentoModel {
	
	private static final String TABLE_NAME = "pagamento";
	
	
	public void nuovoMetodo(PagamentoBean pagamento) throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		
		
		
		String sql = "INSERT INTO " + TABLE_NAME + " (numeroCarta, nomeTitolare, cognomeTitolare, cvv, scadenza, idUtente) VALUES (?,?,?,?,?,?)";
		
		
		
		
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(sql);
			
			statement.setString(1,pagamento.getNumeroCarta());
			statement.setString(2, pagamento.getNomeTitolare());
			statement.setString(3, pagamento.getCognomeTitoare());
			statement.setInt(4, pagamento.getCvv());
			statement.setString(5, pagamento.getScadenza());
			statement.setInt(6, pagamento.getIdUtente());
			
			System.out.println(statement.toString());
			
			statement.executeUpdate();

			connessione.commit();
			
			DriverManagerConnectionPool.releaseConnection(connessione);
			
			System.out.println("Cata registrata");
		
		
	

}
		
		
	
	
	public Collection<PagamentoBean> mostraMetodo(int id) throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		
		Collection<PagamentoBean> pagamenti = new LinkedList<PagamentoBean>();
		String sql = "SELECT * FROM " +TABLE_NAME +" WHERE idUtente = '" + id + "'";
		System.out.println(sql);
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			
			PagamentoBean pagamento = new PagamentoBean();
			
			pagamento.setNumeroCarta(rs.getString("numeroCarta"));
			pagamento.setScadenza(rs.getString("scadenza"));
			pagamento.setCvv(rs.getInt("cvv"));
			pagamento.setNomeTitolare(rs.getString("nomeTitolare"));
			pagamento.setCognomeTitoare(rs.getString("cognomeTitolare"));
			
			
			
			
			pagamenti.add(pagamento);
			
			System.out.println(pagamenti.toString());
			
			
			
		}
		DriverManagerConnectionPool.releaseConnection(connessione);
		
		return pagamenti;
		
	}
	
public synchronized boolean rimuoviMetodo(String numeroCarta)  throws SQLException {
		
		Connection connessione = null;
		PreparedStatement statement = null;
		int result = 0;
		
		String deletSQL =  " DELETE FROM mangamaniacs.pagamento  WHERE pagamento.numeroCarta = ? ";
				
		try {
			System.out.println(deletSQL);
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(deletSQL);
			statement.setString(1,numeroCarta);
		
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
