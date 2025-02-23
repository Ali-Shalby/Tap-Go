package com.tapngo.view;

import com.tapngo.exception.DAOException;
import com.tapngo.model.domain.ApplicazioneStage;
import com.tapngo.model.utility.Credentials;
import com.tapngo.model.utility.Popup;
import com.tapngo.model.utility.ScreenSize;
import com.tapngo.controller.LoginController;
import com.tapngo.model.bean.CredentialsBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginGrafico {


    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private TextField textFieldUsername;



    private static final String ERROR_POPUP_TYPE = "error";

    @FXML // Acquisizione credienziali e passaggio al controller del login
    protected void onLoginButtonClick() throws IOException {

        // Gestione eccezione per validità email
        CredentialsBean credB = null;
        try {
            credB = new CredentialsBean(textFieldUsername.getText(), textFieldPassword.getText());
        } catch (IllegalArgumentException e) {
            Popup.mostraPopup("Errore email", "L'email fornita non è valida!", ERROR_POPUP_TYPE);
            loginView();
        }

        // Verifica se credB è stato istanziato correttamente
        if (credB != null) {
            LoginController loginController = new LoginController();

            // Chiamata al login controller per effettuare il login
            try {
                loginController.start(credB);


                // Controlla il ruolo dell'utente e carica la view appropriata
               if (Credentials.getRole() != null) {
                    cambiaViewDopoLogin();
                }
            } catch (LoadException e) {
                Popup.mostraPopup("Caricamento non riuscito", "Impossibile caricamento della pagina!", ERROR_POPUP_TYPE);
                loginView();
            } catch (DAOException | SQLException | IOException e) {
                Popup.mostraPopup("Errore", "Hai sbagliato username o password, per favore ricontrolla!", ERROR_POPUP_TYPE);
                loginView();
            }
        }
    }

    // Metodo che gestisce il caricamento della view in base al ruolo
    private void cambiaViewDopoLogin() throws IOException, DAOException, SQLException {

        String fxmlFile;
        Stage stageChangeView = ApplicazioneStage.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        if (Credentials.getRole().getId() == 1) {
            fxmlFile = "/com/tapngo/clienteView.fxml"; // View per cliente
        } else {
            fxmlFile = "/com/tapngo/ristoratoreView.fxml"; // View per ristoratore
        }

        // Carica l'FXML
        fxmlLoader.setLocation(getClass().getResource(fxmlFile));
        Parent rootNode = fxmlLoader.load();

        // Ottieni il controller associato
        Object controller = fxmlLoader.getController();


        if (controller instanceof ClienteControllerGrafico clienteControllerGrafico) { // Se il controller è per il cliente
            clienteControllerGrafico.setLabelTitle("                Ciao " + Credentials.getNome() + ",\n   cosa vuoi mangiare oggi?");
        } else if (controller instanceof RistoratoreControllerGrafico ristoratoreControllerGrafico) { // Se il controller è per il ristoratore
            ristoratoreControllerGrafico.setLabelTitle("                  Ciao " + Credentials.getNome() + ",\n    cosa vuoi gestire oggi?");
        }

        // Imposta la scena
        Scene scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        stageChangeView.setTitle("Tap&go");
        if (controller instanceof ClienteControllerGrafico clienteControllerGrafico) { // Se il controller è per il cliente
            clienteControllerGrafico.setUsername(Credentials.getUsername());}
        stageChangeView.setScene(scene);
        stageChangeView.centerOnScreen();
        stageChangeView.show();
    }

    @FXML // Metodo per il login
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
    // Chiamata al controller della registrazione
    public void registratiView() throws IOException {


        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage = ApplicazioneStage.getStage();

        // Carica il file FXML per la vista del login
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/registratiView.fxml"));

        // Imposta la nuova scena con il layout caricato
        Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());

        // Cambia la scena dello stage
        stage.setScene(scene);
        stage.show();
    }

    public void cambiaGrafica() throws IOException{
        // Invoca il metodo per il cambio grafica
        ScreenSize.changeGUI();

        // Settaggi scena
        FXMLLoader fxmlLoader;
        Stage stage = ApplicazioneStage.getStage();
        Scene scene;
        String fxmlFile;

        // Caricamento del login in base alla GUI
        if (ScreenSize.getGUI() == 0){
            fxmlFile = "/com/tapngo/login.fxml";
        } else {
            fxmlFile = "/com/tapngo/login2.fxml";
        }

        // Caricamento della scena
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        stage.setTitle("Tap&go");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}