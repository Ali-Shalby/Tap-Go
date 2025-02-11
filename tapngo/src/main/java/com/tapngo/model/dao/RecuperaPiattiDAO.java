package com.tapngo.model.dao;

import com.tapngo.exception.DAOException;
import com.tapngo.model.domain.*;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecuperaPiattiDAO {

    public ListPiatti recuperaPiattiExecute(Ristorante ristorante) throws DAOException, SQLException {

        // Parametri
        ListPiatti listPiatti = new ListPiatti();
        CallableStatement cs = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call cerca_piatti(?)}");
            cs.setString(1, ristorante.getNome());

            // Esegui la stored procedure
            boolean status = cs.execute();

            // Controllo esecuzione
            if(status) {
                ResultSet rs = cs.getResultSet();

                // Itera attraverso il ResultSet e popola la lista di ricette
                while (rs.next()) {
                    Piatto piatto = FactoryPiatto.createPiatto(rs.getString("tipo"));
                    piatto.setNome(rs.getString("nomeAlimento"));
                    piatto.setPrezzo(rs.getFloat("prezzoAlimento"));
                    piatto.setDescrizione(rs.getString("descrizione"));
                    piatto.setIngredienti(rs.getString("ingredienti"));
                    piatto.setUrlVideo(rs.getString("urlVideo"));
                    piatto.setImmagine(rs.getBlob("immagine"));


                    // Aggiungi ricetta alla lista ricette
                    listPiatti.addPiatto(piatto);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore caricamento piatti: " + e.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return listPiatti;
    }
}
