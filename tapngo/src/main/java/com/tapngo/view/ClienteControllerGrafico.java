package com.tapngo.view;

import com.tapngo.controller.EffettuaOrdineControllerApplicativo;
import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.*;
import com.tapngo.model.dao.ConnectionFactory;
import com.tapngo.model.domain.ApplicazioneStage;
import com.tapngo.model.utility.Credentials;
import com.tapngo.model.utility.Popup;
import com.tapngo.model.utility.ScreenSize;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;


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
    @FXML
    private VBox cardContainer;
    @FXML
    private ScrollPane scrollPane;
    private String cucina;
    private static final String DEFAULT_IMAGE = "/default_image.png";

    private static final String MESSAGGIO = "In costruzione";
    private EffettuaOrdineControllerApplicativo ordine;
    private static final String NAMEAPP = "Tap&go";
    private static final String CONTENUTO = "Sezione non ancora implementata!";
    private static final String TYPE = "construction";
    private static final String WARNING_MESSAGE_TITLE = "Attenzione";
    private static final String WARNING_POPUP_TYPE = "warning";
    private static final String ERROR_MESSAGE_TITLE = "Errore";
    private static final String ERROR_POPUP_TYPE = "error";



    // Setta il titolo della categoria della barra di navigazione superiore
    public void setLabelTitle(String title) {
        labelTitle.setText(title);
    }

    public void setCucina(String cucina) { this.cucina = cucina; }

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
        ClienteControllerGrafico controller = fxmlLoader.getController();
        controller.setCucina(cucina);
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
    public void mostraRistoranti() throws IOException {


        if (cittaComboBox.getValue() == null || tipologiaComboBox.getValue() == null || prezzoComboBox.getValue() == null || valutazioneComboBox.getValue() == null){
            Popup.mostraPopup(WARNING_MESSAGE_TITLE, "Si prega di selezionare tutte le opzioni prima di procedere!", WARNING_POPUP_TYPE);
        }
        else {
            try {
                BeanRistoranti filtri = new BeanRistoranti(nomeRistorante.getText(), cittaComboBox.getValue().toString(), tipologiaComboBox.getValue().toString(), prezzoComboBox.getValue().toString(), valutazioneComboBox.getValue().toString(), cucina);
                ordine = new EffettuaOrdineControllerApplicativo();
                BeanRistoranti listaRistoranti = ordine.mostraRistoranti(filtri);
                if (listaRistoranti.getListRistoranti().isEmpty()){
                    Popup.mostraPopup(WARNING_MESSAGE_TITLE,"non ci sono attività ristorative che ripsecchiano i filtri scelti",WARNING_POPUP_TYPE);
                }else{
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Stage stage = ApplicazioneStage.getStage();
                    Scene scene;
                    String fxmlFile = "/com/tapngo/elencoRistorantiView.fxml";
                    Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
                    ClienteControllerGrafico controller = fxmlLoader.getController();

                    for (BeanRistorante beanRistorante : listaRistoranti.getListRistoranti()) {
                        BorderPane element = cardRistorante(beanRistorante);
                        controller.cardContainer.getChildren().add(element);
                    }

                    scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
                    stage.setTitle(NAMEAPP);
                    stage.setScene(scene);
                    stage.show();
                }
            }catch (DAOException | SQLException e) {
                Popup.mostraPopup( ERROR_MESSAGE_TITLE, "Si è verificato un errore durante durante il caricamento dei ristoranti.", ERROR_POPUP_TYPE);
            }
        }
    }

    private BorderPane cardRistorante(BeanRistorante ristoranteBean){
        BorderPane card = new BorderPane();


        HBox imgBox;
        ImageView imageView;
        Image image;
        try {
            if (ristoranteBean.getImmagine() != null && ristoranteBean.getImmagine().getBinaryStream() != null) {
                InputStream inputStream = ristoranteBean.getImmagine().getBinaryStream();
                image = new Image(inputStream, 100, 100, true, true); // Imposta dimensioni fisse e preserva il rapporto
            } else {
                image = new Image("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/default_image.png");
            }
        } catch (SQLException e) {
            image = new Image("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/default_image.png");
        }

        imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(65);
        Rectangle clip = new Rectangle(65, 70);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        imageView.setClip(clip);
        imgBox = new HBox(imageView);
        card.setLeft(imgBox);

        // Creazione del nome del ristorante
        Label titleLabel = new Label(ristoranteBean.getNome());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

        // Creazione della locazione del ristorante
        ImageView indicazioneIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/indicazione_icon.png");
        indicazioneIcon.setFitHeight(14);
        indicazioneIcon.setFitWidth(14);
        Label locazioneLabel = new Label(ristoranteBean.getIndirizzo() + ", " + ristoranteBean.getCitta());
        locazioneLabel.setStyle("-fx-font-size: 10px;");
        HBox indicazioniBox = new HBox(indicazioneIcon, locazioneLabel);
        indicazioniBox.setSpacing(5);

        // Creazione della tipologia di cucina
        ImageView cucinaIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/cucina_icon.png");
        cucinaIcon.setFitHeight(14);
        cucinaIcon.setFitWidth(14);
        Label cucinaLabel = new Label(ristoranteBean.getTipologia());
        cucinaLabel.setStyle("-fx-font-size: 10px;");
        HBox cucinaBox = new HBox(cucinaIcon, cucinaLabel);
        cucinaBox.setSpacing(5);

        // Creazione dell'icona per la valutazione
        ImageView valutazioneIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/valutazione_icon.png");
        valutazioneIcon.setFitHeight(14);
        valutazioneIcon.setFitWidth(14);
        Label valutazioneLabel;
        valutazioneLabel = new Label(ristoranteBean.getValutazione() + "/5");
        valutazioneLabel.setStyle("-fx-font-size: 12px;");

        // Creazione di uno spazio vuoto (Region) tra la valutzione e il prezzo
        Region spacer = new Region();
        spacer.setMinWidth(10);

        // Creazione dell'icona per durata della preparazione
        ImageView prezzoIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/prezzo_icon.png");
        prezzoIcon.setFitHeight(14);
        prezzoIcon.setFitWidth(14);
        Label prezzoLabel = new Label(ristoranteBean.getPrezzo() + "€");
        prezzoLabel.setStyle("-fx-font-size: 12.1px;");

        // Parte bottom per info valutazione e prezzo
        HBox infoBox = new HBox(valutazioneIcon, valutazioneLabel, spacer, prezzoIcon, prezzoLabel);
        infoBox.setSpacing(5);

        // VBox che contiene le informazioni del cuoco e calorie-durata
        VBox detailsBox = new VBox(cucinaBox,indicazioniBox, infoBox);
        detailsBox.setSpacing(5);

        // Creazione della struttura principale
        VBox titleAndDetailsBox = new VBox(titleLabel, detailsBox);
        titleAndDetailsBox.setSpacing(5);
        titleAndDetailsBox.setAlignment(Pos.CENTER_LEFT);

        // Creazione della struttura principale
        HBox mainContent = new HBox(imgBox, titleAndDetailsBox);
        mainContent.setSpacing(10);
        mainContent.setAlignment(Pos.CENTER_LEFT);

        // Impostazione dell'elemento grafico
        card.setCenter(mainContent);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10;");

        card.setOnMouseClicked(event -> {
            try {
                mostraMenu(ristoranteBean);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return card;
    }

    private BorderPane cardPiatto(BeanPiatto piattoBean){
        BorderPane card = new BorderPane();


        HBox imgBox;
        ImageView imageView;
        Image image;
        try {
            if (piattoBean.getImmagine() != null && piattoBean.getImmagine().getBinaryStream() != null) {
                InputStream inputStream = piattoBean.getImmagine().getBinaryStream();
                image = new Image(inputStream, 100, 100, true, true); // Imposta dimensioni fisse e preserva il rapporto
            } else {
                image = new Image("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/default_image.png");
            }
        } catch (SQLException e) {
            image = new Image("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/default_image.png");
        }

        imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(65);
        Rectangle clip = new Rectangle(65, 70);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        imageView.setClip(clip);
        imgBox = new HBox(imageView);
        card.setLeft(imgBox);

        // Creazione del nome del piatto
        Label titleLabel = new Label(piattoBean.getNome());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

        // Creazione dell'icona per la valutazione
        ImageView diminuisciIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/meno_icon.png");
        diminuisciIcon.setFitHeight(14);
        diminuisciIcon.setFitWidth(14);
        ImageView aumentaIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/piu_icon.png");
        aumentaIcon.setFitHeight(14);
        aumentaIcon.setFitWidth(14);
        Label quantitaLabel;
        quantitaLabel = new Label(piattoBean.getQuantita().toString());
        quantitaLabel.setStyle("-fx-font-size: 12px;");

        // Creazione di uno spazio vuoto (Region) tra la valutzione e il prezzo
        Region spacer = new Region();
        spacer.setMinWidth(30);

        // Creazione della label oer il prezzo
        Label prezzoLabel = new Label(piattoBean.getPrezzo() + "€");
        prezzoLabel.setStyle("-fx-font-size: 12.1px;");

        // Parte bottom per info valutazione e prezzo
        HBox infoBox = new HBox(prezzoLabel, spacer, diminuisciIcon, quantitaLabel, aumentaIcon);
        infoBox.setSpacing(5);

        // Creazione della struttura principale
        VBox titleAndDetailsBox = new VBox(titleLabel, infoBox);
        titleAndDetailsBox.setSpacing(5);
        titleAndDetailsBox.setAlignment(Pos.CENTER_LEFT);

        // Creazione della struttura principale
        HBox mainContent = new HBox(imgBox, titleAndDetailsBox);
        mainContent.setSpacing(10);
        mainContent.setAlignment(Pos.CENTER_LEFT);

        // Creazione dell'icona delle info
        ImageView infoIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/info_icon.png");
        infoIcon.setFitHeight(24);
        infoIcon.setFitWidth(24);

        infoIcon.setOnMouseClicked(event -> {mostraDettagliPiatto(piattoBean);});

        // Impostazione dell'elemento grafico
        card.setCenter(mainContent);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10;");


        return card;
    }
    private BorderPane cardBevanda(BeanBevanda bevandaBean){
        BorderPane card = new BorderPane();


        HBox imgBox;
        ImageView imageView;
        Image image;
        try {
            if (bevandaBean.getImmagine() != null && bevandaBean.getImmagine().getBinaryStream() != null) {
                InputStream inputStream = bevandaBean.getImmagine().getBinaryStream();
                image = new Image(inputStream, 100, 100, true, true); // Imposta dimensioni fisse e preserva il rapporto
            } else {
                image = new Image("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/default_image.png");
            }
        } catch (SQLException e) {
            image = new Image("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/default_image.png");
        }

        imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(65);
        Rectangle clip = new Rectangle(65, 70);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        imageView.setClip(clip);
        imgBox = new HBox(imageView);
        card.setLeft(imgBox);

        // Creazione del nome del piatto
        Label titleLabel = new Label(bevandaBean.getNome());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

        // Creazione dell'icona per la valutazione
        ImageView diminuisciIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/meno_icon.png");
        diminuisciIcon.setFitHeight(14);
        diminuisciIcon.setFitWidth(14);
        ImageView aumentaIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/piu_icon.png");
        aumentaIcon.setFitHeight(14);
        aumentaIcon.setFitWidth(14);
        Label quantitaLabel;
        quantitaLabel = new Label(bevandaBean.getQuantita().toString());
        quantitaLabel.setStyle("-fx-font-size: 12px;");

        // Creazione di uno spazio vuoto (Region) tra la valutzione e il prezzo
        Region spacer = new Region();
        spacer.setMinWidth(30);

        // Creazione della label oer il prezzo
        Label prezzoLabel = new Label(bevandaBean.getPrezzo() + "€");
        prezzoLabel.setStyle("-fx-font-size: 12.1px;");

        // Parte bottom per info valutazione e prezzo
        HBox infoBox = new HBox(prezzoLabel, spacer, diminuisciIcon, quantitaLabel, aumentaIcon);
        infoBox.setSpacing(5);

        // Creazione della struttura principale
        VBox titleAndDetailsBox = new VBox(titleLabel, infoBox);
        titleAndDetailsBox.setSpacing(5);
        titleAndDetailsBox.setAlignment(Pos.CENTER_LEFT);

        // Creazione della struttura principale
        HBox mainContent = new HBox(imgBox, titleAndDetailsBox);
        mainContent.setSpacing(10);
        mainContent.setAlignment(Pos.CENTER_LEFT);

        // Creazione dell'icona delle info
        ImageView infoIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/info_icon.png");
        infoIcon.setFitHeight(24);
        infoIcon.setFitWidth(24);

        infoIcon.setOnMouseClicked(event -> {mostraDettagliBevanda(bevandaBean);});

        // Impostazione dell'elemento grafico
        card.setCenter(mainContent);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10;");


        return card;
    }
    private void mostraMenu(BeanRistorante ristoranteBean) throws IOException{
        try {
            BeanPiatti piatti = ordine.mostraPiatti(ristoranteBean);
            BeanBevande bevande = ordine.mostraBevande(ristoranteBean);
            if (piatti.getPiatti().isEmpty() && bevande.getBevande().isEmpty()){
                Popup.mostraPopup(WARNING_MESSAGE_TITLE,"non è stato possibile leggere il menù della ristorazione scelta",WARNING_POPUP_TYPE);
            }else{
                FXMLLoader fxmlLoader = new FXMLLoader();
                Stage stage = ApplicazioneStage.getStage();
                Scene scene;
                String fxmlFile = "/com/tapngo/menùRistoranteView.fxml";
                Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
                ClienteControllerGrafico controller = fxmlLoader.getController();

                for (BeanBevanda beanBevanda : bevande.getBevande()) {
                    BorderPane bevanda = cardBevanda(beanBevanda);
                    controller.cardContainer.getChildren().add(bevanda);
                }
                for (BeanPiatto beanPiatto : piatti.getPiatti()) {
                    BorderPane piatto = cardPiatto(beanPiatto);
                    controller.cardContainer.getChildren().add(piatto);
                }

                scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
                stage.setTitle(NAMEAPP);
                stage.setScene(scene);
                stage.show();
            }
        }catch (DAOException | SQLException e) {
            Popup.mostraPopup( ERROR_MESSAGE_TITLE, "Si è verificato un errore durante il caricamento del menù.", ERROR_POPUP_TYPE);
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
        Popup.mostraPopup(MESSAGGIO, CONTENUTO, TYPE);
    }
    public void profiloView() {
        Popup.mostraPopup(MESSAGGIO, CONTENUTO, TYPE);
    }
    public void storicoOrdiniView() {
        Popup.mostraPopup(MESSAGGIO, CONTENUTO, TYPE);
    }

}
