package com.tapngo.model.dao;

import com.tapngo.exception.DAOException;
import com.tapngo.model.domain.Carrello;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class SalvaOrdineDAO {
    public void salvaOrdineExecute(Carrello carrello) throws DAOException, SQLException {

        // Parametri
        CallableStatement cs = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call aggiungi_ordine(?, ?)}");
            cs.setString(1, carrello.getUsername());
            cs.setString(2, carrello.getRistorante());

            // Esegui la stored procedure
            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore registrazione: " + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }
}
