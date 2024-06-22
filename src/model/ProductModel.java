package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import Bean.mangaBean;


public class ProductModel  {
	static genereModel modelGen = new genereModel() ;
	private static final String TABLE_NAME = "manga";	
	
	public synchronized Collection<mangaBean> doRetrieveAllFilter(String ordine, String[] autori, String[] anni, String[] editori, String[] generi, String Titolo) throws SQLException {
	    Connection connessione = null;
	    PreparedStatement statement = null;
	    Collection<mangaBean> beans = new LinkedList<mangaBean>();

	    StringBuilder selectSQL = new StringBuilder("SELECT manga.id, manga.titolo, collocamento.id_deposito, deposito.nome AS deposito_nome, "
	            + "appartiene.id_genere, genere.nome AS genere_nome, SUM(collocamento.disponibilita) AS quantita "
	            + "FROM " + ProductModel.TABLE_NAME
	            + " INNER JOIN mangamaniacs.collocamento ON manga.id = collocamento.id_manga "
	            + "JOIN mangamaniacs.deposito ON deposito.id = collocamento.id_deposito "
	            + "JOIN mangamaniacs.appartiene ON manga.id = appartiene.id_manga "
	            + "JOIN mangamaniacs.genere ON genere.id = appartiene.id_genere "
	            + "WHERE inv = 0 ");

	    if (Titolo != null && !Titolo.isEmpty()) {
	        selectSQL.append("AND manga.Titolo LIKE ? ");
	    }

	    if (autori != null && autori.length > 0) {
	        selectSQL.append("AND (manga.autore=?");
	        for (int i = 1; i < autori.length; i++) {
	            selectSQL.append(" OR manga.autore=?");
	        }
	        selectSQL.append(") ");
	    }

	    if (anni != null && anni.length > 0) {
	        selectSQL.append("AND (manga.data=?");
	        for (int i = 1; i < anni.length; i++) {
	            selectSQL.append(" OR manga.data=?");
	        }
	        selectSQL.append(") ");
	    }

	    if (generi != null && generi.length > 0) {
	        selectSQL.append("AND (genere.nome=?");
	        for (int i = 1; i < generi.length; i++) {
	            selectSQL.append(" OR genere.nome=?");
	        }
	        selectSQL.append(") ");
	    }

	    if (editori != null && editori.length > 0) {
	        selectSQL.append("AND (manga.editore=?");
	        for (int i = 1; i < editori.length; i++) {
	            selectSQL.append(" OR manga.editore=?");
	        }
	        selectSQL.append(") ");
	    }

	    selectSQL.append(" GROUP BY manga.id, collocamento.id_deposito, deposito.nome, appartiene.id_genere, genere.nome");

	    if (ordine != null) {
	        switch (ordine) {
	            case "A-Z":
	                selectSQL.append(" ORDER BY manga.Titolo ASC");
	                break;
	            case "Z-A":
	                selectSQL.append(" ORDER BY manga.Titolo DESC");
	                break;
	            case "Meno recenti":
	                selectSQL.append(" ORDER BY manga.data ASC");
	                break;
	            case "Piu recenti":
	                selectSQL.append(" ORDER BY manga.data DESC");
	                break;
	            default:
	                break;
	        }
	    }

	    try {
	        connessione = DriverManagerConnectionPool.getConnection();
	        statement = connessione.prepareStatement(selectSQL.toString());

	        int paramIndex = 1;
	        if (Titolo != null && !Titolo.isEmpty()) {
	            statement.setString(paramIndex++, "%" + Titolo + "%");
	        }
	        if (autori != null) {
	            for (String autore : autori) {
	                statement.setString(paramIndex++, autore);
	            }
	        }
	        if (anni != null) {
	            for (String anno : anni) {
	                statement.setString(paramIndex++, anno);
	            }
	        }
	        if (generi != null) {
	            for (String genere : generi) {
	                statement.setString(paramIndex++, genere);
	            }
	        }
	        if (editori != null) {
	            for (String editore : editori) {
	                statement.setString(paramIndex++, editore);
	            }
	        }

	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            mangaBean manga = new mangaBean();
	            manga.setId(rs.getInt("id"));
	            manga.setTitolo(rs.getString("nome"));
	            manga.setPrezzo(rs.getDouble("prezzo"));
	            manga.setEditore(rs.getString("editore"));
	            manga.setAutore(rs.getString("autore"));
	            manga.setIva(rs.getDouble("iva"));
	            manga.setDescrizione(rs.getString("descrizione"));
	            manga.setImg(rs.getString("img"));
	            manga.setInv(rs.getBoolean("inv"));
	            manga.setQuantita(rs.getInt("quantita"));
	            manga.setDataProdotto(rs.getString("data"));
	            manga.setLingua(rs.getString("lingua"));
	            beans.add(manga);
	        }
	    } finally {
	        if (statement != null) {
	            statement.close();
	        }
	        if (connessione != null) {
	            DriverManagerConnectionPool.releaseConnection(connessione);
	        }
	    }

	    if (beans != null && !beans.isEmpty()) {
	        for (mangaBean bean : beans) {
	            try {
	                bean.setGeneri(modelGen.RetrieveAllByIdManga(bean.getId()));
	            } catch (SQLException e) {
	                System.out.println("Error:" + e.getMessage());
	            }
	        }
	    }

	    return beans;
	}

	public synchronized Collection<mangaBean> doRetrieveAll(String sort)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<mangaBean> beans = new LinkedList<mangaBean>();
		
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  mangamaniacs.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  mangamaniacs.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n" +
				" where inv=0  "+
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
				manga.setDataProdotto(rs.getString("data"));
				manga.setLingua(rs.getString("lingua"));
				
				
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
	
	public synchronized Collection<mangaBean> doRetrieveAllTitolo(String titolo)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		Collection<mangaBean> beans = new LinkedList<mangaBean>();
		
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  mangamaniacs.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  mangamaniacs.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n" +
				" where  inv=0  and  manga.Titolo LIKE '%"+titolo+"%'"+
				" GROUP BY manga.id";
		
		
	
	    
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
				manga.setDataProdotto(rs.getString("data"));
				manga.setLingua(rs.getString("lingua"));
				
				
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
		selectSQL+=" inner join  mangamaniacs.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  mangamaniacs.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n"
				+ "join mangamaniacs.appartiene"
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
				manga.setDataProdotto(rs.getString("data"));
				manga.setLingua(rs.getString("lingua"));
				
				
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
		selectSQL+=" inner join  mangamaniacs.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  mangamaniacs.deposito \r\n" + 
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
				manga.setDataProdotto(rs.getString("data"));
				manga.setLingua(rs.getString("lingua"));
				
				
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
				+ " ( Titolo,prezzo ,editore,autore,iva,descrizione,data,lingua) VALUES ( ?, ?, ?, ?, ?,?,?,?)";
		try {
			
			connessione = DriverManagerConnectionPool.getConnection();
			statement = connessione.prepareStatement(insertSQL);
			
			statement.setString(1,bean.getTitolo());
			statement.setDouble(2,bean.getPrezzo() );
			statement.setString(3,bean.getEditore());
			statement.setString(4,bean.getAutore());
			statement.setDouble(5,bean.getIva() );
			statement.setString(6,bean.getDescrizione() );
			statement.setString(7,bean.getDataProdotto() );
			statement.setString(8,bean.getLingua() );
			
			
						

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
		mangaBean manga =new mangaBean();
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  mangamaniacs.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  mangamaniacs.deposito \r\n" + 
				" on deposito.id=collocamento.id_deposito\r\n" +
				" where inv=0 && manga.id= '"+id+"'"+
				" GROUP BY manga.id";
		
//		System.out.println(selectSQL);
		try {
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(selectSQL);
		
		ResultSet rs = statement.executeQuery(selectSQL);
		
		if(rs.next()){
			
			
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
			manga.setDataProdotto(rs.getString("data"));
			manga.setLingua(rs.getString("lingua"));
			
			
	
			
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
		
		if (manga != null) {
			
			
			try {
				
				
				
				manga.setGeneri(modelGen.RetrieveAllByIdManga(manga.getId()));
				
				
				
			} catch (  SQLException e) {
				
				System.out.println("Error:" + e.getMessage());
			}

		}
	
	
	
	return manga;
		
	}
	public synchronized mangaBean selectMangaAdmin(int id)  throws SQLException {
		Connection connessione = null;
		PreparedStatement statement = null;
		
		String selectSQL = "SELECT *,sum(disponibilita)as quantita FROM " + ProductModel.TABLE_NAME;
		selectSQL+=" inner join  mangamaniacs.collocamento\r\n" + 
				" on manga.id=collocamento.id_manga \r\n" + 
				"join  mangamaniacs.deposito \r\n" + 
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
			manga.setDataProdotto(rs.getString("data"));
			manga.setLingua(rs.getString("lingua"));
			
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
		
		String deletSQL =  " DELETE FROM mangamaniacs."+ProductModel.TABLE_NAME+" WHERE manga.id = ? ";
				
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
public synchronized boolean modifyManga(mangaBean manga)  throws SQLException, FileNotFoundException {
	

	Connection connessione = null;
	PreparedStatement statement = null;
	int result = 0;
	
	String deletSQL =  "UPDATE manga SET Titolo = ?, prezzo = ?, editore = ?, autore = ?, iva = ?, descrizione = ?,  inv = ?	,	data=?,lingua=? WHERE id = ?";
			
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
		statement.setString(8,manga.getDataProdotto() );
		statement.setString(9,manga.getLingua() );
		statement.setInt(10,manga.getId());
		

	
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

public synchronized boolean updatoImg(mangaBean manga)  throws SQLException {
	
	Connection connessione = null;
	PreparedStatement statement = null;
	int result = 0;
	
	String deletSQL =  "UPDATE manga SET img = ?, imgBl = ? WHERE id = ?";
	
	
	
	
			if(manga.getImg()==null)
				return false;
			
	try {
		System.out.println(deletSQL);
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(deletSQL);
		
		File file = new File(manga.getImg());
		FileInputStream fis = new FileInputStream(file);
		statement.setString(1,"getPicture?id="+manga.getId());
		statement.setBinaryStream(2,fis);
		
		
		statement.setInt(3,manga.getId());
		
		System.out.println((String)statement.toString());

		result=statement.executeUpdate();
System.out.println("risultalto "+result);
		connessione.commit();
	
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
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
		manga.setDataProdotto(rs.getString("data"));
		manga.setLingua(rs.getString("lingua"));
		
		
		
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
public synchronized byte[] imgLoad(String id)  throws SQLException {
	Connection connessione = null;
	PreparedStatement statement = null;
	byte[] bt=null;
	
	String selectSQL = "SELECT imgBl FROM " + ProductModel.TABLE_NAME+" where  manga.id="+id;
	
	
//	System.out.println(selectSQL);
	try {
	connessione = DriverManagerConnectionPool.getConnection();
	statement = connessione.prepareStatement(selectSQL);
	
	ResultSet rs = statement.executeQuery(selectSQL);
	
	if(rs.next()){
		bt=rs.getBytes("imgBl");
		
		
	return bt;
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

public synchronized Collection<String> doRetrieveAllAttribto(String attributo)  throws SQLException {
	Connection connessione = null;
	PreparedStatement statement = null;
	Collection<String> beans = new LinkedList<String>();
	
	
	String selectSQL = "SELECT "+attributo+" FROM " + ProductModel.TABLE_NAME;
	selectSQL+=" GROUP BY "+attributo;
	
	
	

    
	try {
		
		connessione = DriverManagerConnectionPool.getConnection();
		statement = connessione.prepareStatement(selectSQL);
	
		ResultSet rs = statement.executeQuery(selectSQL);
		while (rs.next()) {
			String Opzineattributo = new String();
			Opzineattributo=rs.getString(attributo);
		
			
			
	
			
			
			beans.add(Opzineattributo);
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






}