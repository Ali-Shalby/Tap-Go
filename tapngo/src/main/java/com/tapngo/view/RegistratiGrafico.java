package com.tapngo.view;

import com.tapngo.controller.RegistratiController;
import com.tapngo.exception.DAOException;
import com.tapngo.model.domain.ApplicazioneStage;
import com.tapngo.model.domain.Role;
import com.tapngo.model.utility.Popup;
import com.tapngo.model.utility.ScreenSize;
import com.tapngo.model.bean.CredentialsBean;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegistratiGrafico {

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private PasswordField textFieldRipetiPassword;
    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldCognome;

    @FXML
    private ComboBox<Role> comboBoxRuolo;

    @FXML
    private TextField textFieldUsername;



    public void initialize() {
        // Configura la ComboBox con i valori dell'enum Role
        comboBoxRuolo.setItems(FXCollections.observableArrayList(Role.values()));
    }

    // Chiamata al controller del login
    public void loginView() throws IOException {
        // Carica il file FXML per la vista del login
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/login.fxml"));

        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage = ApplicazioneStage.getStage();

        // Imposta la nuova scena con il layout caricato
        Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());

        // Cambia la scena dello stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML // Metodo per effettuare la registrazione chiamato dalla view
    public void confermaRegistrazione() {

        // Prendo i dati dai campi di testo della view registrazione
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        String ripetiPassword = textFieldRipetiPassword.getText();
        String nome = textFieldNome.getText();
        String cognome = textFieldCognome.getText();
        Role ruolo = comboBoxRuolo.getValue();


        // Mostra un avviso se anche uno dei campi non è stato selezionato
        if (nome.isEmpty() || cognome.isEmpty() || ruolo == null || username.isEmpty() || password.isEmpty() || ripetiPassword.isEmpty()) {
            Popup.mostraPopup("Attenzione", "Si prega di selezionare tutte le opzioni prima di procedere!", "warning");
            return;
        }

        // Creazione di un oggetto bean e riempimento con i dati acquisiti
        CredentialsBean beanRegistrazione = new CredentialsBean();
        beanRegistrazione.setNome(nome);
        beanRegistrazione.setCognome(cognome);
        beanRegistrazione.setRipetiPassword(ripetiPassword);
        beanRegistrazione.setRole(ruolo);
        beanRegistrazione.setPassword(password);


        // Variabile che indica se l'email è valida
        boolean emailValida = true;

        // Gestione eccezione per validità email
        try {
            beanRegistrazione.setUsername(username);
        } catch (IllegalArgumentException e) {
            Popup.mostraPopup("Errore email", "L'email fornita non è valida!", "error");
            emailValida = false;  // L'email non è valida, quindi si ferma il flusso
        }

        // Continua solo se l'email è valida
        if (emailValida) {

            // Chiama il controller applicativo per effettuare la registrazione
            RegistratiController controllerRegistrati = new RegistratiController();

            // Mostra il popup in base all'esito della query
            try {
                controllerRegistrati.effettuaRegistrazione(beanRegistrazione);

                // Carica il file FXML per la vista del login
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/login.fxml"));

                // Ottieni lo stage attuale dalla classe ApplicazioneStage
                Stage stage = ApplicazioneStage.getStage();

                // Imposta la nuova scena con il layout caricato
                Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());

                // Cambia la scena dello stage
                stage.setScene(scene);
                stage.show();

                Popup.mostraPopup("Successo", "Ti sei registrato con successo!", "success");
            } catch (IllegalArgumentException e) {
                Popup.mostraPopup("Attenzione", "Password non coincidenti!", "warning");
            } catch (DAOException | SQLException | IOException e) {
                Popup.mostraPopup("Errore", "Si è verificato un errore durante la registrazione.", "error");
            }
        }
    }
}