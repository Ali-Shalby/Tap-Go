package com.tapngo.model.dao;

import com.tapngo.exception.DAOException;
import com.tapngo.model.domain.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecuperaBevandeDAO {
    public ListBevande recuperaBevandeExecute(Ristorante ristorante) throws DAOException, SQLException {

        // Parametri
        ListBevande listBevande = new ListBevande();
        CallableStatement cs = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call cerca_bevanda(?)}");
            cs.setString(1, ristorante.getNome());

            // Esegui la stored procedure
            boolean status = cs.execute();

            // Controllo esecuzione
            if(status) {
                ResultSet rs = cs.getResultSet();

                // Itera attraverso il ResultSet e popola la lista di ricette
                while (rs.next()) {
                    Bevanda bevanda = new Bevanda(rs.getBoolean("alcolico"));
                    bevanda.setNome(rs.getString("nomeAlimento"));
                    bevanda.setPrezzo(rs.getDouble("prezzoAlimento"));
                    bevanda.setDescrizione(rs.getString("descrizione"));
                    bevanda.setImmagine(rs.getBlob("immagine"));


                    // Aggiungi ricetta alla lista ricette
                    listBevande.addBevanda(bevanda);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore caricamento bevande: " + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return listBevande;
    }
}
