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

public class LoginGrafico2 {

    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField textFieldPassword;


    private static final String ERROR_POPUP_TYPE = "error";

    @FXML // Acquisizione credienziali e passaggio al controller del login
    protected void onLoginButtonClick() throws IOException {

        // Gestione eccezione per validità email
        CredentialsBean credB2 = null;
        try {
            credB2 = new CredentialsBean(textFieldUsername.getText(), textFieldPassword.getText());
        } catch (IllegalArgumentException e) {
            Popup.mostraPopup("Errore email", "L'email fornita non è valida!", ERROR_POPUP_TYPE);
            loginView();
        }

        // Verifica se credB è stato istanziato correttamente
        if (credB2 != null) {
            LoginController loginController = new LoginController();

            // Chiamata al login controller per effettuare il login
            try {
                loginController.start(credB2);


                // Controlla il ruolo dell'utente e carica la view appropriata
                if (Credentials.getRole() != null) {
                    cambiaViewDopoLogin();
                }
            } catch (LoadException e) {
                Popup.mostraPopup("Caricamento non riuscito", "Impossibile caricamento della pagina!", ERROR_POPUP_TYPE);
                loginView();
            } catch (SQLException | DAOException |  IOException e) {
                Popup.mostraPopup("Errore", "Hai sbagliato username o password, per favore ricontrolla!", ERROR_POPUP_TYPE);
                loginView();
            }
        }
    }

    // Metodo che gestisce il caricamento della view in base al ruolo
    private void cambiaViewDopoLogin() throws IOException, DAOException, SQLException {

        String fxmlFile1;
        Stage stageChangeView1 = ApplicazioneStage.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        if (Credentials.getRole().getId() == 1) {
            fxmlFile1 = "/com/tapngo/clienteView2.fxml"; // View per cliente
        } else {
            fxmlFile1 = "/com/tapngo/ristoratoreView2.fxml"; // View per ristoratore
        }

        // Carica l'FXML
        fxmlLoader.setLocation(getClass().getResource(fxmlFile1));
        Parent rootNode = fxmlLoader.load();

        // Ottieni il controller associato
        Object controller = fxmlLoader.getController();


        if (controller instanceof ClienteControllerGrafico2 clienteControllerGrafico2) { // Se il controller è per il cliente
            clienteControllerGrafico2.setLabelTitle("                Ciao " + Credentials.getNome() + ",\n   cosa vuoi mangiare oggi?");
        } else if (controller instanceof RistoratoreControllerGrafico ristoratoreControllerGrafico2) { // Se il controller è per il ristoratore
            ristoratoreControllerGrafico2.setLabelTitle("                  Ciao " + Credentials.getNome() + ",\n    cosa vuoi gestire oggi?");
        }

        // Imposta la scena
        Scene scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        stageChangeView1.setTitle("Tap&go");
        if (controller instanceof ClienteControllerGrafico clienteControllerGrafico) { // Se il controller è per il cliente
            clienteControllerGrafico.setUsername(Credentials.getUsername());}
        stageChangeView1.setScene(scene);
        stageChangeView1.centerOnScreen();
        stageChangeView1.show();
    }

    @FXML // Metodo per il login
    public void loginView() throws IOException {

        // Carica il file FXML per la vista del login
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/login2.fxml"));

        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage1 = ApplicazioneStage.getStage();

        // Imposta la nuova scena con il layout caricato
        Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());

        // Cambia la scena dello stage
        stage1.setScene(scene);
        stage1.show();
    }
    // Chiamata al controller della registrazione
    public void registratiView() throws IOException {


        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage = ApplicazioneStage.getStage();

        // Carica il file FXML per la vista del login
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/registratiView2.fxml"));

        // Imposta la nuova scena con il layout caricato
        Scene scene1 = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());

        // Cambia la scena dello stage
        stage.setScene(scene1);
        stage.show();
    }

    public void cambiaGrafica() throws IOException{
        // Invoca il metodo per il cambio grafica
        ScreenSize.changeGUI();

        // Settaggi scena
        FXMLLoader fxmlLoader;
        Stage stage1 = ApplicazioneStage.getStage();
        Scene scene1;
        String fxmlFile1;

        // Caricamento del login in base alla GUI
        if (ScreenSize.getGUI() == 0){
            fxmlFile1 = "/com/tapngo/login.fxml";
        } else {
            fxmlFile1 = "/com/tapngo/login2.fxml";
        }

        // Caricamento della scena
        fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile1));
        scene1 = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        stage1.setTitle("Tap&go");
        stage1.setScene(scene1);
        stage1.centerOnScreen();
        stage1.show();
    }

}