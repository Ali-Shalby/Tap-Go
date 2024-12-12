package com.tapngo.view;

import com.tapngo.exception.DAOException;
import com.tapngo.model.dao.ConnectionFactory;
import com.tapngo.model.domain.ApplicazioneStage;
import com.tapngo.model.utility.Credentials;
import com.tapngo.model.utility.Popup;
import com.tapngo.model.utility.ScreenSize;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ClienteControllerGrafico {
    @FXML
    private Label labelTitle;

    private String cucina;


    // Setta il titolo della categoria della barra di navigazione superiore
    public void setLabelTitle(String title) {
        labelTitle.setText(title);
    }

    public void initialize() throws DAOException, SQLException {

        // Inizializza il titolo con il messaggio interattivo
        setLabelTitle("                  Ciao " + Credentials.getNome() + ",\n    cosa vuoi mangiare oggi?");
    }

    public void filtriView() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/filtriRicercaView.fxml"));

        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage = ApplicazioneStage.getStage();

        // Imposta la nuova scena con il layout caricato
        Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());

        // Cambia la scena dello stage
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException, SQLException {
        // Ottieni la scelta dell'utente al popup
        boolean confermato = Popup.mostraPopupConferma("Conferma Logout", "Sei sicuro di voler effettuare il logout?");

        if (confermato) {

            // Azzera le credenziali dell'utente
            Credentials.setUsername(null);
            Credentials.setPassword(null);
            Credentials.setRole(null);

            // Chiude la connessione
            ConnectionFactory.closeConnection();

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

    }

    public void onClickedCucinaCasalinga() throws IOException {
        cucina = "casa";
        filtriView();
    }
    public void onClickedRistorante() throws IOException {
        cucina = "ristorante";
        filtriView();
    }
    public void preferitiView() {
        Popup.mostraPopup("In costruzione", "Sezione non ancora implementata!", "construction");
    }
    public void profiloView() {
        Popup.mostraPopup("In costruzione", "Sezione non ancora implementata!", "construction");
    }
    public void storicoOrdiniView() {
        Popup.mostraPopup("In costruzione", "Sezione non ancora implementata!", "construction");
    }
}
