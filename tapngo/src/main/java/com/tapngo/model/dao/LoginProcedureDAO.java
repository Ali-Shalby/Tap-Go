package com.tapngo.model.dao;

import com.tapngo.exception.DAOException;
import com.tapngo.model.domain.Role;
import com.tapngo.model.utility.Credentials;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO {

    public void loginExecute() throws DAOException, SQLException {
        int role;
        String nome;
        String cognome;
        CallableStatement cs = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            cs = conn.prepareCall("{call login(?,?,?,?,?)}");

            // Imposta i parametri IN
            cs.setString(1, Credentials.getUsername());
            cs.setString(2, Credentials.getPassword());

            // Registra il parametro OUT
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);



            // Esegui la chiamata
            cs.executeQuery();

            // Ottieni il valore dei parametri OUT (ruolo, nome, cognome)
            role = cs.getInt(3);
            nome = cs.getString(4);
            cognome = cs.getString(5);


            // Verifica se il ruolo è valido
            if (role == 3) {
                throw new DAOException("Credenziali non valide: username o password errati.");
            }

            // Imposta il ruolo nelle credenziali
            Credentials.setRole(Role.fromInt(role));
            Credentials.setNome(nome);
            Credentials.setCognome(cognome);

        } catch (SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        } finally {
            if (cs!= null){
                cs.close();
            }
        }
    }
}