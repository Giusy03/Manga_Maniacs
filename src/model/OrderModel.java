package model;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import Bean.OrderBean;
import Bean.UserBean;
import Bean.mangaBean;

public class OrderModel {
    private static final String TABLE_NAME = "ordine";
    
    //Metodi per criptare la carta
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final byte[] SECRET_KEY = "0EpE]1Gg(dF<Tf62".getBytes(); // Deve essere di 16 bytes

    private SecretKeySpec getSecretKeySpec() {
        return new SecretKeySpec(SECRET_KEY, ENCRYPTION_ALGORITHM);
    }

    private String encrypt(String data){
        Cipher cipher;
		try {
			cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec());
			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
    }

    private String decrypt(String encryptedData){
        Cipher cipher;
		try {
			cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec());
			return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
    }
    private static String maskCreditCard(String numeroCarta) {
        int length = numeroCarta.length();
        String masked = "";
        for (int i = 0; i < length - 4; i++) {
            masked += "*";
        }
        masked += numeroCarta.substring(length - 4);
        return masked;
    }
    //Fine

    public Collection<OrderBean> AllUserOrder(int id) throws SQLException{
        Connection connessione = null;
        PreparedStatement statement = null;
        Collection<OrderBean> ordini = new LinkedList<OrderBean>();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_utente = ?";
        connessione = DriverManagerConnectionPool.getConnection();
        statement = connessione.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            OrderBean order = new OrderBean();
            order.setId(rs.getInt("id"));
            order.setSomma_tot(rs.getDouble("somma_tot"));
            order.setData_ordine(rs.getString("data_ordine"));
            order.setNumeroCarta(maskCreditCard(decrypt(rs.getString("numeroCarta"))));
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

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            OrderBean order = new OrderBean();
            order.setId(rs.getInt("id"));
            order.setIva_tot(rs.getDouble("iva_tot"));
            order.setSomma_tot(rs.getDouble("somma_tot"));
            order.setData_ordine(rs.getString("data_ordine"));
            order.setNumeroCarta(maskCreditCard(decrypt(rs.getString("numeroCarta"))));
            order.setIndirizzo(rs.getString("indirizzo"));
            ordini.add(order);
        }
        DriverManagerConnectionPool.releaseConnection(connessione);
        return ordini;
    }

    public void doSave(OrderBean order, CartModel cart) throws SQLException{
        Connection connessione = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (id_utente, iva_tot, somma_tot, data_ordine, numeroCarta, indirizzo) VALUES (?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO contiene (id_manga, id_ordine, iva_acquisto, prezzo_acquisto, quantita, TitoloManga) VALUES (?, ?, ?, ?, ?, ?)";

        connessione = DriverManagerConnectionPool.getConnection();
        statement = connessione.prepareStatement(sql);

        statement.setInt(1, order.getId_utente());
        statement.setDouble(2, model.CartModel.calcolaIvaTotale());
        statement.setDouble(3, model.CartModel.calcolaTotale());

        LocalDate data_ordine = LocalDate.now();
        statement.setDate(4, Date.valueOf(data_ordine));
        statement.setString(5, encrypt(order.getNumeroCarta()));
        statement.setString(6, order.getIndirizzo());

        statement.executeUpdate();
        connessione.commit();

        statement = connessione.prepareStatement(sql2);

        for (int i = 0; i < cart.size(); i++) {
            mangaBean manga = cart.returnproductByIndex(i);
            statement.setInt(1, manga.getId());
            statement.setInt(2, this.ultimoOrdine().getId());
            statement.setDouble(3, manga.getIva());
            statement.setDouble(4, manga.getPrezzo());
            statement.setInt(5, cart.returnquantityByIndex(i));
            statement.setString(6, manga.getTitolo());
            statement.executeUpdate();
        }
        connessione.commit();
        DriverManagerConnectionPool.releaseConnection(connessione);
    }

    public synchronized OrderBean ultimoOrdine() throws SQLException{
        Connection connessione = null;
        PreparedStatement statement = null;
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC";

        try {
            connessione = DriverManagerConnectionPool.getConnection();
            statement = connessione.prepareStatement(selectSQL);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                OrderBean order = new OrderBean();
                order.setId(rs.getInt("id"));
                order.setId_utente(rs.getInt("id_utente"));
                order.setIva_tot(rs.getDouble("iva_tot"));
                order.setSomma_tot(rs.getDouble("somma_tot"));
                order.setData_ordine(rs.getString("data_ordine"));
                order.setNumeroCarta(maskCreditCard(decrypt(rs.getString("numeroCarta"))));
                order.setIndirizzo(rs.getString("indirizzo"));
                return order;
            } else {
                return null;
            }
        } finally {
            if (statement != null) statement.close();
            DriverManagerConnectionPool.releaseConnection(connessione);
        }
    }

    public OrderBean viewOrderDetails(int id) throws SQLException{
        Connection connessione = null;
        PreparedStatement statement = null;
        OrderBean order = new OrderBean();
        ArrayList<mangaBean> mangas = new ArrayList<mangaBean>();

        String selectSQL = "SELECT id_manga, iva_acquisto, prezzo_acquisto, TitoloManga, quantita FROM contiene WHERE id_ordine = ?";
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        connessione = DriverManagerConnectionPool.getConnection();
        statement = connessione.prepareStatement(selectSQL);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            mangaBean manga = new mangaBean();
            manga.setId(rs.getInt("id_manga"));
            manga.setTitolo(rs.getString("TitoloManga"));
            manga.setIva(rs.getDouble("iva_acquisto"));
            manga.setPrezzo(rs.getDouble("prezzo_acquisto"));
            manga.setQuantita(rs.getInt("quantita"));
            mangas.add(manga);
        }
        order.setManga(mangas);

        statement = connessione.prepareStatement(sql);
        statement.setInt(1, id);
        rs = statement.executeQuery();

        if (rs.next()) {
            order.setSomma_tot(rs.getDouble("somma_tot"));
            order.setIva_tot(rs.getDouble("iva_tot"));
            order.setData_ordine(rs.getString("data_ordine"));
            order.setNumeroCarta(maskCreditCard(decrypt(rs.getString("numeroCarta"))));
            order.setIndirizzo(rs.getString("indirizzo"));
        }
        return order;
    }

    public PDDocument generateInvoice(OrderBean order, UserBean user, int id) throws SQLException, IOException {
        PDDocument MyPDF = new PDDocument();
        PDPage newpage = new PDPage();
        MyPDF.addPage(newpage);
        PDPageContentStream content = new PDPageContentStream(MyPDF, newpage);
        content.beginText();
        content.setFont(PDType1Font.TIMES_ROMAN, 12);
        content.newLineAtOffset(100, 700);
        content.showText("Nome: " + user.getNome() + " " + user.getCognome());
        content.newLine();
        content.showText("Data ordine: " + order.getData_ordine());
        content.newLine();
        content.showText("Indirizzo: " + order.getIndirizzo());
        content.newLine();
        content.showText("Numero carta: " + maskCreditCard(order.getNumeroCarta()));
        content.newLine();
        content.showText("Totale ordine: " + order.getSomma_tot());
        content.endText();
        content.close();
        return MyPDF;
    }
}
