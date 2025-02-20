package com.tapngo.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.CredentialsBean;
import com.tapngo.model.domain.Role;
import com.tapngo.model.utility.Credentials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.sql.SQLException;

@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class LoginControllerTest {

    LoginController loginController = new LoginController();

    @Test
    void loginSuccessfulCliente() {

        // Inizializzo un bean con le credenziali di un cliente che so che esiste
        CredentialsBean credentialsBean = new CredentialsBean();
        credentialsBean.setUsername("ali.shalby03@gmail.com");
        credentialsBean.setPassword("a");

        try {
            // Eseguo il metodo di login
            loginController.start(credentialsBean);
        } catch (DAOException | SQLException e) {
            fail("Errore durante il login: " + e.getMessage());
        }

        // Verifica che il ruolo dell'utente sia impostato correttamente
        assertEquals(Role.CLIENTE, Credentials.getRole());
    }

    @Test
    void loginUnsuccessfulCliente() {

        // Inizializzo un bean con le credenziali di un cliente che so che non esiste
        CredentialsBean credentialsBean = new CredentialsBean();
        credentialsBean.setUsername("utente.inesistente@nonesiste.com");
        credentialsBean.setPassword("nonesisto");

        // Azzero il ruolo perchè settato dal test di prima
        Credentials.setRole(null);

        try {
            // Eseguo il metodo di login
            loginController.start(credentialsBean);
        } catch (DAOException e) {
            // Verifica che il ruolo dell'utente sia impostato correttamente a null
            assertNull(Credentials.getRole());
        } catch (SQLException e) {
            fail("Errore durante il login: " + e.getMessage());
        }
    }
}
