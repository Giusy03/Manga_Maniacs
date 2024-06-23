package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.LinkedList;

import Bean.UserBean;

public class userDAO {
    private static final String TABLE_NAME = "utente";

    // Metodo per fare l'HASH della password
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public UserBean signIn(String username, String password) throws SQLException {
        Connection connessione = null;
        PreparedStatement statement = null;
        UserBean utente;
        String hashedPassword = hashPassword(password);
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND psw = ?";
        try {
            connessione = DriverManagerConnectionPool.getConnection();
            statement = connessione.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                utente = new UserBean();
                utente.setId(rs.getInt("id"));
                utente.setUsername(rs.getString("username"));
                utente.setPwd(rs.getString("psw"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setAmministratore(rs.getString("ruolo").equalsIgnoreCase("admin"));
                return utente;
            } else {
                return null;
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connessione);
            }
        }
    }

    public UserBean signUp(String username, String password, String email) throws SQLException {
        Connection connessione = null;
        PreparedStatement statement = null;
        String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO " + TABLE_NAME + " (username, psw, email) VALUES (?, ?, ?)";
        try {
            connessione = DriverManagerConnectionPool.getConnection();
            statement = connessione.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.setString(3, email);

            statement.executeUpdate();
            connessione.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connessione);
            }
        }
        return null;
    }

    public synchronized Collection<UserBean> RetrieveAll() throws SQLException {
        Connection connessione = null;
        PreparedStatement statement = null;
        Collection<UserBean> beans = new LinkedList<UserBean>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        try {
            connessione = DriverManagerConnectionPool.getConnection();
            statement = connessione.prepareStatement(selectSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserBean utente = new UserBean();
                utente.setId(rs.getInt("id"));
                utente.setUsername(rs.getString("username"));
                utente.setPwd(rs.getString("psw"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setAmministratore(rs.getString("ruolo").equalsIgnoreCase("admin"));
                beans.add(utente);
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connessione);
            }
        }
        return beans;
    }

    public synchronized boolean modifyUtenteDati(UserBean user) throws SQLException {
        Connection connessione = null;
        PreparedStatement statement = null;
        int result = 0;
        String updateSQL = "UPDATE " + TABLE_NAME + " SET nome = ?, cognome = ?, email = ? WHERE id = ?";

        try {
            connessione = DriverManagerConnectionPool.getConnection();
            statement = connessione.prepareStatement(updateSQL);
            statement.setString(1, user.getNome());
            statement.setString(2, user.getCognome());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());

            result = statement.executeUpdate();
            connessione.commit();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connessione);
            }
        }
        return (result != 0);
    }

    public synchronized boolean modifyUtentePsw(UserBean user, String psw) throws SQLException {
        Connection connessione = null;
        PreparedStatement statement = null;
        int result = 0;
        String hashedPassword = hashPassword(psw);
        String updateSQL = "UPDATE " + TABLE_NAME + " SET psw = ? WHERE id = ?";
        try {
            connessione = DriverManagerConnectionPool.getConnection();
            statement = connessione.prepareStatement(updateSQL);
            statement.setString(1, hashedPassword);
            statement.setInt(2, user.getId());

            result = statement.executeUpdate();
            connessione.commit();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connessione);
            }
        }
        return (result != 0);
    }

    public synchronized boolean modifyUtenteUsername(UserBean user, String Username) throws SQLException {
        Connection connessione = null;
        PreparedStatement statement = null;
        int result = 0;
        String updateSQL = "UPDATE " + TABLE_NAME + " SET username = ? WHERE id = ?";
        try {
            connessione = DriverManagerConnectionPool.getConnection();
            statement = connessione.prepareStatement(updateSQL);
            statement.setString(1, Username);
            statement.setInt(2, user.getId());

            result = statement.executeUpdate();
            connessione.commit();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connessione);
            }
        }
        return (result != 0);
    }
}
