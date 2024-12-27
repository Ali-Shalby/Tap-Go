package com.tapngo.view;

import com.tapngo.controller.EffettuaOrdineControllerApplicativo;
import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.BeanRistoranti;
import com.tapngo.model.dao.ConnectionFactory;
import com.tapngo.model.domain.ApplicazioneStage;
import com.tapngo.model.utility.Credentials;
import com.tapngo.model.utility.Popup;
import com.tapngo.model.utility.ScreenSize;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class ClienteControllerGrafico {
    @FXML
    private Label labelTitle;
    @FXML
    private TextField nomeRistorante;
    @FXML
    private ComboBox cittaComboBox;
    @FXML
    private ComboBox tipologiaComboBox;
    @FXML
    private ComboBox prezzoComboBox;
    @FXML
    private ComboBox valutazioneComboBox;
    private String cucina;
    private String messaggio = "In costruzione";
    private EffettuaOrdineControllerApplicativo ordine;
    private static final String NAMEAPP = "Tap&go";





    // Setta il titolo della categoria della barra di navigazione superiore
    public void setLabelTitle(String title) {
        labelTitle.setText(title);
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
    @FXML
    private void tornaIndietro() throws IOException{

        Stage stageChangeView = ApplicazioneStage.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();


        // Carica l'FXML
        fxmlLoader.setLocation(getClass().getResource("/com/tapngo/clienteView.fxml"));
        Parent rootNode = fxmlLoader.load();

        // Ottieni il controller associato
        Object controller = fxmlLoader.getController();

        ((ClienteControllerGrafico)controller).setLabelTitle("                 Ciao " + Credentials.getNome() + ",\n    cosa vuoi mangiare oggi?");

        // Imposta la scena
        Scene scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        stageChangeView.setTitle(NAMEAPP);
        stageChangeView.setScene(scene);
        stageChangeView.centerOnScreen();
        stageChangeView.show();

    }
    @FXML
    public void mostraRistoranti() throws DAOException, SQLException {

        if (cittaComboBox.getValue() == null || tipologiaComboBox.getValue() == null || prezzoComboBox.getValue() == null || valutazioneComboBox.getValue() == null){
            Popup.mostraPopup("attenzione", "Si prega di selezionare tutte le opzioni prima di procedere!", "warning");
        }
        else {
            BeanRistoranti filtri = new BeanRistoranti(nomeRistorante.getText(), cittaComboBox.getValue().toString(), tipologiaComboBox.getValue().toString(), prezzoComboBox.getValue().toString(), valutazioneComboBox.getValue().toString(), cucina);
            ordine = new EffettuaOrdineControllerApplicativo();
            BeanRistoranti listaRistoranti = ordine.mostraRistoranti(filtri);
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
        Popup.mostraPopup(messaggio, "Sezione non ancora implementata!", "construction");
    }
    public void profiloView() {
        Popup.mostraPopup(messaggio, "Sezione non ancora implementata!", "construction");
    }
    public void storicoOrdiniView() {
        Popup.mostraPopup(messaggio, "Sezione non ancora implementata!", "construction");
    }

}
