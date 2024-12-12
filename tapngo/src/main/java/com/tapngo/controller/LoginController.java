package com.tapngo.controller;

import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.CredentialsBean;
import com.tapngo.model.dao.ConnectionFactory;
import com.tapngo.model.dao.LoginProcedureDAO;
import com.tapngo.model.utility.Credentials;

import java.sql.SQLException;

public class LoginController {

    // Costruttore per inizializzare il DAO

    public LoginController() {}

    // Metodo per effettuare il login
    public void start(CredentialsBean credB) throws DAOException, SQLException {

        // Setta le credenziali dal bean
        Credentials.setUsername(credB.getUsername());
        Credentials.setPassword(credB.getPassword());

        // Esegui la procedura di login nel database
        new LoginProcedureDAO().loginExecute();

        // Cambia il ruolo della connessione in base al ruolo dell'utente
        if (Credentials.getRole() != null) {
            try {
                ConnectionFactory.changeRole(Credentials.getRole());
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}