package com.tapngo.model.dao;

import com.tapngo.exception.DAOException;
import com.tapngo.model.domain.ListRistoranti;
import com.tapngo.model.domain.Ristorante;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecuperaRistorantiDAO {
    public ListRistoranti recuperaRistorantiExecute(Ristorante ristorante) throws DAOException, SQLException {

        // Parametri
        ListRistoranti listRistoranti = new ListRistoranti();
        CallableStatement cs = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call cerca_ristoranti(?, ?, ?, ?)}");
            cs.setString(1, ristorante.getNome());
            cs.setString(2, ristorante.getCitta());
            cs.setString(3, ristorante.getTipologia());
            cs.setString(4, ristorante.getPrezzo());
            cs.setInt(5, ristorante.getValutazione());

            // Esegui la stored procedure
            boolean status = cs.execute();

            // Controllo esecuzione
            if(status) {
                ResultSet rs = cs.getResultSet();

                // Itera attraverso il ResultSet e popola la lista di ricette
                while (rs.next()) {
                    Ristorante restaurant = new Ristorante(rs.getString("nome"));
                    restaurant.setCitta(rs.getString("città"));
                    restaurant.setTipologia(rs.getString("tipologia"));
                    restaurant.setPrezzo(rs.getInt("prezzoMedio"));
                    restaurant.setIndirizzo(rs.getString("indirzzo"));
                    restaurant.setValutazione(rs.getInt("valutazione"));
                    restaurant.setImmagine(rs.getBlob("immagine"));
                    restaurant.setNumTelefono(rs.getString("numTelefono"));
                    restaurant.setTipo(rs.getString("tipo"));

                    // Aggiungi ricetta alla lista ricette
                    listRistoranti.addRistorante(restaurant);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore lista ristorante: " + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return listRistoranti;
    }
}
