package com.tapngo.view;

import com.tapngo.controller.EffettuaOrdineControllerApplicativo;
import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.*;
import com.tapngo.model.dao.ConnectionFactory;
import com.tapngo.model.domain.ApplicazioneStage;
import com.tapngo.model.utility.Credentials;
import com.tapngo.model.utility.Popup;
import com.tapngo.model.utility.ScreenSize;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import static com.tapngo.ApplicationStart.getHostServicesInstance;

public class ClienteControllerGrafico2 {
    @FXML
    Label labelTitle;
    @FXML
    Label totalLabel;
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
    private GridPane cardContainer;
    @FXML
    private VBox cardContainer2;
    @FXML
    private TextField cardNumberField; // Riferimento al campo "Numero Carta"

    @FXML
    private TextField expiryDateField; // Riferimento al campo "Data di Scadenza"

    @FXML
    private TextField cvcField; // Riferimento al campo "CVC"
    private String cucina;
    private String username;
    private BeanCarrello carrelloBean;
    private EffettuaOrdineControllerApplicativo ordine;
    private static final String NAMEAPP = "Tap&go";
    private static final String CONTENUTO = "Sezione non ancora implementata!";
    private static final String TYPE = "construction";
    private static final String WARNING_MESSAGE_TITLE = "Attenzione";
    private static final String WARNING_POPUP_TYPE = "warning";
    private static final String ERROR_MESSAGE_TITLE = "Errore";
    private static final String ERROR_POPUP_TYPE = "error";
    private static final String MESSAGGIO = "In costruzione";
    private static final String BACKGROUND_COLOR ="-fx-background-color: white;";
    private static final String SUB_LABEL_STYLE ="-fx-font-size: 15px; -fx-text-fill: #666666;";
    private static final String DEFAULT_IMAGE = "C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/default_image.png";
    private static final String TITLE_TEXT_STYLE = "-fx-font-size: 20px; -fx-font-weight: bold;";
    private static final String LABEL_TEXT_STYLE = "-fx-font-size: 16px;";
    private static final String CARD_STYLE = "-fx-border-color: gray; -fx-border-width: 1; -fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10;";

    public void setLabelTitle(String title) {
        labelTitle.setText(title);
    }
    public void setCucina(String cucina) { this.cucina = cucina; }
    public void setTotalLabel(double t){totalLabel.setText("Totale: "+t+"€");}
    public void setUsername(String username){this.username = username;}
    public void inizializzaOrdine(EffettuaOrdineControllerApplicativo ordine){
        this.ordine = ordine;
    }
    private void inizializzaCarrello(BeanCarrello carrelloBean) {
        this.carrelloBean = carrelloBean;
    }
    @FXML
    private void tornaIndietro() throws IOException{

        Stage stageChangeView = ApplicazioneStage.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();


        // Carica l'FXML
        fxmlLoader.setLocation(getClass().getResource("/com/tapngo/clienteView2.fxml"));
        Parent rootNode = fxmlLoader.load();

        // Ottieni il controller associato
        Object controller = fxmlLoader.getController();

        ((ClienteControllerGrafico2)controller).setLabelTitle("                 Ciao " + Credentials.getNome() + ",\n    cosa vuoi mangiare oggi?");

        // Imposta la scena
        Scene scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        stageChangeView.setTitle(NAMEAPP);
        stageChangeView.setScene(scene);
        stageChangeView.centerOnScreen();
        stageChangeView.show();

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
            Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/login2.fxml"));

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
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/filtriRicercaView2.fxml"));

        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage = ApplicazioneStage.getStage();
        ClienteControllerGrafico2 controller = fxmlLoader.getController();
        controller.setCucina(cucina);
        controller.setUsername(username);
        // Imposta la nuova scena con il layout caricato
        Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());

        // Cambia la scena dello stage
        stage.setScene(scene);
        stage.show();
    }
    public void onClickedCucinaCasalinga() throws IOException {
        cucina = "casa";
        filtriView();
    }
    public void onClickedRistorante() throws IOException {
        cucina = "ristorante";
        filtriView();
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
                    Popup.mostraPopup(WARNING_MESSAGE_TITLE,"non ci sono attività ristorative che rispecchiano i filtri scelti",WARNING_POPUP_TYPE);
                }else{
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Stage stage = ApplicazioneStage.getStage();
                    Scene scene;
                    String fxmlFile = "/com/tapngo/elencoRistorantiView2.fxml";
                    Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
                    ClienteControllerGrafico2 controller = fxmlLoader.getController();

                    for (BeanRistorante beanRistorante : listaRistoranti.getListRistoranti()) {
                        BorderPane element = cardRistorante(beanRistorante);
                        controller.cardContainer.getChildren().add(element);
                    }

                    scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
                    stage.setTitle(NAMEAPP);
                    controller.setUsername(username);
                    stage.setScene(scene);
                    stage.show();
                }
            }catch (DAOException | SQLException e) {
                Popup.mostraPopup( ERROR_MESSAGE_TITLE, "Si è verificato un errore durante durante il caricamento dei ristoranti.", ERROR_POPUP_TYPE);
            }
        }
    }
    private void mostraMenu(BeanRistorante ristoranteBean) throws IOException{
        try {
            BeanPiatti piatti = ordine.mostraPiatti(ristoranteBean);
            BeanBevande bevande = ordine.mostraBevande(ristoranteBean);
            carrelloBean = ordine.creaCarrello(ristoranteBean, username);
            if (piatti.getPiatti().isEmpty() && bevande.getBevande().isEmpty()){
                Popup.mostraPopup(WARNING_MESSAGE_TITLE,"non è stato possibile leggere il menù della ristorazione scelta",WARNING_POPUP_TYPE);
            }else{

                FXMLLoader fxmlLoader = new FXMLLoader();
                Stage stage = ApplicazioneStage.getStage();
                Scene scene;
                String fxmlFile = "/com/tapngo/menùRistoranteView2.fxml";
                Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
                ClienteControllerGrafico2 controller = fxmlLoader.getController();
                for (BeanBevanda beanBevanda : bevande.getBevande()) {
                    BorderPane bevanda = cardBevanda(beanBevanda);
                    controller.cardContainer2.getChildren().add(bevanda);
                }
                for (BeanPiatto beanPiatto : piatti.getPiatti()) {
                    BorderPane piatto = cardPiatto(beanPiatto);
                    controller.cardContainer2.getChildren().add(piatto);
                }

                scene = new Scene(rootNode, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
                stage.setTitle(NAMEAPP);
                controller.inizializzaOrdine(ordine);
                inizializzaCarrello(carrelloBean);
                stage.setScene(scene);
                stage.show();
            }

        }catch (DAOException | SQLException e) {
            Popup.mostraPopup( ERROR_MESSAGE_TITLE, "Si è verificato un errore durante il caricamento del menù.", ERROR_POPUP_TYPE);
        }

    }
    public void anteprimaOrdine() {
        carrelloBean = this.ordine.mostraCarrello();
        if (carrelloBean.getListaItems().isEmpty()) {
            Popup.mostraPopup(WARNING_MESSAGE_TITLE, "Selezionare qualcosa dal menù prima di proseguire con l'ordine", WARNING_POPUP_TYPE);
        } else {
            // Crea un nuovo Stage per il popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Dettagli ordine");

            // Crea il contenuto del popup
            BorderPane popupContent = new BorderPane();
            popupContent.setStyle(BACKGROUND_COLOR);

            try {
                // Crea un VBox per il titolo
                VBox titoloBox = new VBox();
                titoloBox.setPadding(new Insets(20, 20, 10, 20)); // Padding per il titolo

                // Aggiungi un titolo sopra la lista degli elementi
                Label titoloLista = new Label("Elementi nel Carrello");
                titoloLista.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");
                titoloLista.setAlignment(Pos.CENTER); // Centra il testo nel Label
                titoloLista.setMaxWidth(Double.MAX_VALUE); // Occupa tutta la larghezza disponibile
                titoloBox.getChildren().add(titoloLista);

                // Aggiungi il titoloBox nella parte superiore del BorderPane
                popupContent.setTop(titoloBox);

                // Crea un VBox per gli elementi del carrello
                VBox itemsBox = new VBox();
                itemsBox.setSpacing(20); // Aumenta la spaziatura tra gli elementi
                itemsBox.setPadding(new Insets(10, 20, 20, 20)); // Padding per gli elementi

                // Aggiungi gli elementi del carrello
                for (BeanItemCarrello item : carrelloBean.getListaItems()) {
                    HBox popupOrdineContent = createContentOrdine(item);
                    // Aggiungi un bordo e un padding a ciascun elemento
                    popupOrdineContent.setStyle("-fx-border-color: #CCCCCC; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 10;");
                    itemsBox.getChildren().add(popupOrdineContent);
                }

                // Inserisci il contenuto nel ScrollPane
                ScrollPane localScrollPane = new ScrollPane(itemsBox);
                localScrollPane.setFitToWidth(true);

                // Imposta la visualizzazione all'inizio
                localScrollPane.setVvalue(0);

                // Aggiungi il ScrollPane al centro del BorderPane
                popupContent.setCenter(localScrollPane);

                // Pulsante per confermare l'ordine
                Button confirmButton = new Button("Conferma ordine");
                confirmButton.setStyle("-fx-border-radius: 10; -fx-background-radius: 15; -fx-border-color: gray; -fx-background-insets: 0;");

                EventHandler<ActionEvent> confirmHandler = (confirmEvent) -> {
                    try {
                        metodoPagamentoView();
                        popupStage.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                };
                confirmButton.setOnAction(confirmHandler);

                // Allineamento bottone "Conferma ordine"
                HBox buttonBox = new HBox(10);
                buttonBox.setAlignment(Pos.CENTER);
                buttonBox.getChildren().add(confirmButton);
                buttonBox.setPadding(new Insets(10)); // Aggiungi un po' di padding

                // Crea un VBox per il prezzo totale
                Label totalPriceLabel = new Label("Totale: " + carrelloBean.getTotalPrice() + "€");
                totalPriceLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #333333;fx-font-family: 'Calibri'");

                VBox totalPriceBox = new VBox(totalPriceLabel);
                totalPriceBox.setAlignment(Pos.CENTER); // Centra il prezzo totale
                totalPriceBox.setPadding(new Insets(10, 0, 10, 0)); // Aggiungi padding sopra e sotto

                // Aggiungi il prezzo totale e il bottone in un VBox separato
                VBox bottomBox = new VBox(totalPriceBox, buttonBox);
                bottomBox.setSpacing(10); // Spaziatura tra prezzo totale e bottone
                bottomBox.setAlignment(Pos.CENTER); // Centra tutto il contenuto

                // Aggiungi il bottomBox nella parte inferiore del BorderPane
                popupContent.setBottom(bottomBox);

                // Imposta il layout come scena del popup
                Scene popupScene = new Scene(popupContent, 600, 600);
                popupStage.setScene(popupScene);

                // Esecuzione codice passato per forzare lo scroll all'inizio dopo il rendering della GUI
                popupStage.show();
                Platform.runLater(() -> localScrollPane.setVvalue(0));

            } catch (SQLException e) {
                Popup.mostraPopup(ERROR_MESSAGE_TITLE, "Errore nel caricamento dei dettagli dell'ordine.", ERROR_POPUP_TYPE);
            }
        }
    }
    public HBox createContentOrdine(BeanItemCarrello item) throws SQLException {
        // Crea HBox per contenere l'immagine e il testo
        HBox popupInitialContent = new HBox();
        popupInitialContent.setAlignment(Pos.CENTER_LEFT);
        popupInitialContent.setSpacing(10); // Spaziatura tra immagine e testo

        // Gestione grafica dell'immagine del piatto
        ImageView immagineItem;
        if (item.getImmagine() != null && item.getImmagine().getBinaryStream() != null) {
            immagineItem = new ImageView(new Image(item.getImmagine().getBinaryStream()));
        } else {
            immagineItem = new ImageView(DEFAULT_IMAGE);
        }
        immagineItem.setFitWidth(70); // Larghezza fissa per l'immagine
        immagineItem.setFitHeight(65); // Altezza fissa per l'immagine
        immagineItem.setPreserveRatio(true);

        // Applica un ritaglio arrotondato all'immagine
        Rectangle clip = new Rectangle(immagineItem.getFitWidth(), immagineItem.getFitHeight());
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        immagineItem.setClip(clip);

        // Crea un VBox per organizzare il testo (nome, quantità e prezzo)
        VBox textBox = new VBox();
        textBox.setAlignment(Pos.CENTER_LEFT);
        textBox.setSpacing(5); // Spaziatura tra nome e dettagli

        // Titolo del piatto (nome)
        Label titolo = new Label(item.getNome());
        titolo.setStyle(TITLE_TEXT_STYLE);

        // Crea un HBox per quantità e prezzo
        HBox detailsBox = new HBox();
        detailsBox.setAlignment(Pos.CENTER_LEFT);
        detailsBox.setSpacing(20); // Aumenta la spaziatura tra quantità e prezzo

        // Dettaglio quantità
        Label quantita = new Label("x" + item.getQuantita());
        quantita.setStyle(SUB_LABEL_STYLE);

        // Prezzo
        Label prezzo = new Label(String.format("%.2f€", item.getPrezzo() * item.getQuantita()));
        prezzo.setStyle(SUB_LABEL_STYLE);

        // Aumenta il margine sinistro per spostare ulteriormente il prezzo a destra
        HBox.setMargin(prezzo, new Insets(0, 0, 0, 40)); // Margine sinistro di 40px

        // Aggiungi quantità e prezzo al detailsBox
        detailsBox.getChildren().addAll(quantita, prezzo);

        // Aggiungi titolo e detailsBox al textBox
        textBox.getChildren().addAll(titolo, detailsBox);

        // Aggiungi immagine e textBox all'HBox principale
        popupInitialContent.getChildren().addAll(immagineItem, textBox);

        // Restituisci il contenuto
        return popupInitialContent;
    }
    public void metodoPagamentoView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/metodoPagamentoView2.fxml"));

        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage = ApplicazioneStage.getStage();
        ClienteControllerGrafico2 controller = fxmlLoader.getController();

        // Imposta la nuova scena con il layout caricato
        Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        controller.inizializzaOrdine(ordine);
        controller.inizializzaCarrello(carrelloBean);

        // Cambia la scena dello stage
        stage.setScene(scene);
        stage.show();

    }
    public void cartaDiCreditoView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("/com/tapngo/cartaDiCreditoView2.fxml"));

        // Ottieni lo stage attuale dalla classe ApplicazioneStage
        Stage stage = ApplicazioneStage.getStage();
        ClienteControllerGrafico2 controller = fxmlLoader.getController();

        // Imposta la nuova scena con il layout caricato
        Scene scene = new Scene(root, ScreenSize.getSceneWidth(), ScreenSize.getSceneHeight());
        controller.inizializzaOrdine(ordine);
        controller.inizializzaCarrello(carrelloBean);
        controller.setTotalLabel(carrelloBean.getTotalPrice());
        // Cambia la scena dello stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void paga() throws IOException {
        String cardNumber = cardNumberField.getText();
        String scadenza = expiryDateField.getText();
        String cvc = cvcField.getText();
        if(cardNumber.isEmpty() || scadenza.isEmpty() || cvc.isEmpty()){
            Popup.mostraPopup(WARNING_MESSAGE_TITLE, "Si prega di selezionare tutte le opzioni prima di procedere!", WARNING_POPUP_TYPE);
            return;
        }
        try {
            BeanCreditCard creditCard = new BeanCreditCard(cardNumber, scadenza, cvc);
            ordine.effettuaOrdine(creditCard,carrelloBean);
            Popup.mostraPopup("Successo", "ordine inviato", "success");
            tornaIndietro();
        }catch (Exception e ) {
            Popup.mostraPopup("Errore carta di credito", e.getMessage(), ERROR_POPUP_TYPE);
            cartaDiCreditoView();
        }

    }


    @FXML
    private void terminaOrdine() throws IOException {
        try {
            ordine.salvaOrdine();
            Popup.mostraPopup("Successo", "ordine inviato", "success");
            tornaIndietro();
        }catch (DAOException | SQLException e) {
            Popup.mostraPopup( ERROR_MESSAGE_TITLE, "Si è verificato un errore durante il salvataggio dell'ordine.", ERROR_POPUP_TYPE);
        }


    }
    private BorderPane cardRistorante(BeanRistorante ristoranteBean) {
        BorderPane card = new BorderPane();

        // Immagine del ristorante
        HBox imgBox;
        ImageView imageView;
        Image image;
        try {
            if (ristoranteBean.getImmagine() != null && ristoranteBean.getImmagine().getBinaryStream() != null) {
                InputStream inputStream = ristoranteBean.getImmagine().getBinaryStream();
                image = new Image(inputStream, 120, 120, true, true); // Immagine più grande
            } else {
                image = new Image(DEFAULT_IMAGE);
            }
        } catch (SQLException e) {
            image = new Image(DEFAULT_IMAGE);
        }

        imageView = new ImageView(image);
        imageView.setFitHeight(120); // Immagine più grande
        imageView.setFitWidth(110);
        Rectangle clip = new Rectangle(110, 120); // Clipping più grande
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        imgBox = new HBox(imageView);
        card.setLeft(imgBox);

        // Nome del ristorante
        Label titleLabel = new Label(ristoranteBean.getNome());
        titleLabel.setStyle(TITLE_TEXT_STYLE); // Titolo più grande

        // Locazione del ristorante
        ImageView indicazioneIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/indicazione_icon.png");
        indicazioneIcon.setFitHeight(20); // Icona più grande
        indicazioneIcon.setFitWidth(20);
        Label locazioneLabel = new Label(ristoranteBean.getIndirizzo() + ", " + ristoranteBean.getCitta());
        locazioneLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande
        HBox indicazioniBox = new HBox(indicazioneIcon, locazioneLabel);
        indicazioniBox.setSpacing(10); // Più spazio tra icona e testo

        // Tipologia di cucina
        ImageView cucinaIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/cucina_icon.png");
        cucinaIcon.setFitHeight(20); // Icona più grande
        cucinaIcon.setFitWidth(20);
        Label cucinaLabel = new Label(ristoranteBean.getTipologia());
        cucinaLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande
        HBox cucinaBox = new HBox(cucinaIcon, cucinaLabel);
        cucinaBox.setSpacing(10); // Più spazio tra icona e testo

        // Valutazione
        ImageView valutazioneIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/valutazione_icon.png");
        valutazioneIcon.setFitHeight(20); // Icona più grande
        valutazioneIcon.setFitWidth(20);
        Label valutazioneLabel = new Label(ristoranteBean.getValutazione() + "/5");
        valutazioneLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande

        // Spazio vuoto
        Region spacer = new Region();
        spacer.setMinWidth(20); // Più spazio

        // Prezzo
        ImageView prezzoIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/prezzo_icon.png");
        prezzoIcon.setFitHeight(20); // Icona più grande
        prezzoIcon.setFitWidth(20);
        Label prezzoLabel = new Label(ristoranteBean.getPrezzo() + "€");
        prezzoLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande

        // Box per valutazione e prezzo
        HBox infoBox = new HBox(valutazioneIcon, valutazioneLabel, spacer, prezzoIcon, prezzoLabel);
        infoBox.setSpacing(10); // Più spazio tra elementi

        // Box per dettagli
        VBox detailsBox = new VBox(cucinaBox, indicazioniBox, infoBox);
        detailsBox.setSpacing(10); // Più spazio tra righe

        // Box principale per titolo e dettagli
        VBox titleAndDetailsBox = new VBox(titleLabel, detailsBox);
        titleAndDetailsBox.setSpacing(10); // Più spazio tra titolo e dettagli
        titleAndDetailsBox.setAlignment(Pos.CENTER_LEFT);

        // Contenuto principale
        HBox mainContent = new HBox(imgBox, titleAndDetailsBox);
        mainContent.setSpacing(20); // Più spazio tra immagine e contenuto
        mainContent.setAlignment(Pos.CENTER_LEFT);

        // Imposta il contenuto della card
        card.setCenter(mainContent);
        card.setPadding(new Insets(20)); // Più spazio interno
        card.setStyle(CARD_STYLE);

        card.setOnMouseClicked(event -> {
            try {
                mostraMenu(ristoranteBean);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return card;
    }
    private BorderPane cardPiatto(BeanPiatto piattoBean) {
        BorderPane card = new BorderPane();

        // Immagine del piatto
        HBox imgBox;
        ImageView imageView;
        Image image;
        try {
            if (piattoBean.getImmagine() != null && piattoBean.getImmagine().getBinaryStream() != null) {
                InputStream inputStream = piattoBean.getImmagine().getBinaryStream();
                image = new Image(inputStream, 120, 120, true, true); // Immagine più grande
            } else {
                image = new Image(DEFAULT_IMAGE);
            }
        } catch (SQLException e) {
            image = new Image(DEFAULT_IMAGE);
        }

        imageView = new ImageView(image);
        imageView.setFitHeight(100); // Immagine più grande
        imageView.setFitWidth(90);
        Rectangle clip = new Rectangle(90, 100); // Clipping più grande
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        imgBox = new HBox(imageView);
        card.setLeft(imgBox);

        // Nome del piatto
        Label titleLabel = new Label(piattoBean.getNome());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;"); // Testo più grande

        // Icone per aumentare/diminuire la quantità
        ImageView diminuisciIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/meno_icon.png");
        diminuisciIcon.setFitHeight(20); // Icona più grande
        diminuisciIcon.setFitWidth(20);
        ImageView aumentaIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/piu_icon.png");
        aumentaIcon.setFitHeight(20); // Icona più grande
        aumentaIcon.setFitWidth(20);
        Label quantitaLabel = new Label(piattoBean.getQuantita().toString());
        quantitaLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande

        // Aggiungi azioni alle icone
        aumentaIcon.setOnMouseClicked(event -> aggiungiAlCarrello(piattoBean, quantitaLabel));
        diminuisciIcon.setOnMouseClicked(event -> rimuoviDalCarrello(piattoBean, quantitaLabel));

        // Prezzo
        Label prezzoLabel = new Label(piattoBean.getPrezzo() + "€");
        prezzoLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande

        // Spaziatore per spingere infoBox a destra
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Lo spaziatore occupa tutto lo spazio disponibile



        HBox infoBox = new HBox(diminuisciIcon, quantitaLabel, aumentaIcon);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setSpacing(10);
        // Creazione di un GridPane per titleAndInfoBox
        GridPane titleAndInfoBox = new GridPane();
        titleAndInfoBox.setAlignment(Pos.CENTER_LEFT);
        titleAndInfoBox.setMinWidth(1200);
        titleAndInfoBox.setHgap(10); // Spazio orizzontale tra le colonne

// Definisci le colonne
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30); // 40% della larghezza per il titolo e il prezzo
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS); // Lo spaziatore occupa lo spazio rimanente
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS); // Lo spaziatore occupa lo spazio rimanente
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(30); // 30% della larghezza per le icone di quantità
        titleAndInfoBox.getColumnConstraints().addAll(col1, col2, col3, col4, col5);

// Aggiungi gli elementi al GridPane
        titleAndInfoBox.add(titleLabel, 0, 0); // Titolo e prezzo nella prima colonna
        titleAndInfoBox.add(prezzoLabel, 2, 0);  // Icone di quantità nella terza colonna
        titleAndInfoBox.add(infoBox,4,0);

        // Contenuto principale
        HBox mainContent = new HBox(imgBox, titleAndInfoBox);
        mainContent.setSpacing(20); // Più spazio tra immagine e contenuto
        mainContent.setAlignment(Pos.CENTER_LEFT);

        // Icona delle info
        ImageView infoIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/info_icon.png");
        infoIcon.setFitHeight(30); // Icona più grande
        infoIcon.setFitWidth(30);
        StackPane infoPane = new StackPane(infoIcon);
        StackPane.setAlignment(infoIcon, Pos.TOP_RIGHT);

        infoIcon.setOnMouseClicked(event -> {mostraDettagliPiatto(piattoBean);});

        // Imposta il contenuto della card
        card.setCenter(mainContent);
        card.setRight(infoPane);
        card.setPadding(new Insets(15)); // Più spazio interno
        card.setStyle(CARD_STYLE);

        return card;
    }
    private void mostraDettagliPiatto(BeanPiatto piattoBean) {

        // Crea un nuovo Stage per il popup
        Stage popupStage = new Stage();
        popupStage.setTitle("Dettagli piatto");

        // Crea il contenuto del popup
        VBox popupContent = new VBox();
        popupContent.setSpacing(10);
        popupContent.setPadding(new Insets(20));
        popupContent.setStyle(BACKGROUND_COLOR);
        popupContent.setAlignment(Pos.CENTER);

        try{
            // Creazione grafica di titolo, prezzo, immagine piatto e descrizione
            VBox popupInitialContent = createInitialContent(piattoBean);


            // Creazione grafica delle sezioni dedicate a ingredienti e link del video
            VBox popupOtherContent = createFinalContent(piattoBean);

            // Aggiungi tutti i contenuti creati al layout principale
            popupContent.getChildren().addAll(popupInitialContent, popupOtherContent);

            // Inserisci il contenuto nel ScrollPane
            ScrollPane localScrollPane = new ScrollPane(popupContent);
            localScrollPane.setFitToWidth(true);

            // Imposta la visualizzazione all'inizio
            localScrollPane.setVvalue(0);

            // Imposta il layout come scena del popup
            Scene popupScene = new Scene(localScrollPane, 600, 600);
            popupStage.setScene(popupScene);
            // Esecuzione codice passato per forzare lo scroll all'inizio dopo il rendering della GUI
            popupStage.show();
            Platform.runLater(() -> localScrollPane.setVvalue(0));
            // Mostra il popup
            popupStage.show();
        } catch (DAOException | SQLException e) {
            Popup.mostraPopup(ERROR_MESSAGE_TITLE, "Errore nel caricamento dei dettagli del piatto.", ERROR_POPUP_TYPE);
        }
    }

    // Creazione grafica di titolo, dettagli, immagine e descrizione per il popup "mostraDettagliPiatto"
    private VBox createInitialContent(BeanPiatto piattoBean) throws SQLException {

        // Crea VBox per il titolo e dettagli
        VBox popupInitialContent = new VBox();
        popupInitialContent.setAlignment(Pos.CENTER);
        popupInitialContent.setSpacing(5);

        // Titolo del piatto
        Label titolo = new Label(piattoBean.getNome());
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 22px;");
        titolo.setAlignment(Pos.CENTER);

        // Dettaglio prezzo sopra l'immagine
        Label prezzo = new Label(piattoBean.getPrezzo() + "€");
        prezzo.setStyle(SUB_LABEL_STYLE);
        prezzo.setAlignment(Pos.CENTER);

        // Gestione grafica dell'immagine del piatto
        ImageView immaginePiatto;
        if (piattoBean.getImmagine() != null && piattoBean.getImmagine().getBinaryStream() != null) {
            immaginePiatto = new ImageView(new Image(piattoBean.getImmagine().getBinaryStream()));
            immaginePiatto.setFitWidth(250);
            immaginePiatto.setFitHeight(150);
        } else {
            immaginePiatto = new ImageView(DEFAULT_IMAGE);
            immaginePiatto.setFitWidth(140);
            immaginePiatto.setFitHeight(140);
        }
        immaginePiatto.setPreserveRatio(true);
        Rectangle clip = new Rectangle(immaginePiatto.getFitWidth(), immaginePiatto.getFitHeight());
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        immaginePiatto.setClip(clip);

        // Descrizione del piatto
        Label descrizione = new Label(piattoBean.getDescrizione());
        descrizione.setStyle("-fx-font-size: 13px; -fx-text-fill: #666666; -fx-text-alignment: justify;");
        descrizione.setWrapText(true);

        // Aggiungi gli elementi iniziali al VBox
        popupInitialContent.getChildren().addAll(titolo, prezzo, immaginePiatto, descrizione);

        // Restituisci il contenuto delle informazioni iniziali
        return popupInitialContent;
    }

    // Creazione grafica delle sezioni dedicate a ingredienti e link del video per il popup "mostraDettagliPiatto"
    private VBox createFinalContent(BeanPiatto beanPiatto) throws DAOException {

        // Crea VBox per le altre informazioni
        VBox popupFinalContent = new VBox();
        popupFinalContent.setAlignment(Pos.CENTER_LEFT);
        popupFinalContent.setSpacing(10);

        // Ingredienti del piatto
        Label ingredientiLabel = new Label("Ingredienti");
        ingredientiLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13.9px;");
        VBox.setMargin(ingredientiLabel, new Insets(10, 0, 0, 0));
        VBox ingredientiBox = new VBox(10);
        String ingredienti = beanPiatto.getIngredienti();
        if (ingredienti == null || ingredienti.isEmpty()) {
            Label ingredientiMessage = new Label("Non sono stati specificati gli ingredienti");
            ingredientiBox.getChildren().add(ingredientiMessage);
        } else {
            Label ingredients = new Label(beanPiatto.getIngredienti());
            ingredientiBox.getChildren().add(ingredients);
        }

        // Video URL
        Label videoLabel = new Label("Video Preparazione");
        videoLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13.8px;");
        VBox.setMargin(videoLabel, new Insets(10, 0, 0, 0));
        VBox linkVideoBox = new VBox(10);
        String linkVideo = beanPiatto.getUrlVideo();
        if (linkVideo == null || linkVideo.isEmpty()) {
            Label linkVideoMessage = new Label("Non è presente il link per il video");
            linkVideoBox.getChildren().add(linkVideoMessage);
        } else {
            Hyperlink videoLink = new Hyperlink(beanPiatto.getUrlVideo());
            videoLink.setStyle(LABEL_TEXT_STYLE);
            videoLink.setOnAction(e -> getHostServicesInstance().showDocument(beanPiatto.getUrlVideo()));
            linkVideoBox.getChildren().add(videoLink);
        }
        VBox.setMargin(linkVideoBox, new Insets(0, 0, 20, 0));

        // Aggiungi le altre informazioni al VBox
        popupFinalContent.getChildren().addAll(ingredientiLabel, ingredientiBox, videoLabel, linkVideoBox);

        // Restituisci le informazioni aggiuntive
        return popupFinalContent;
    }
    private BorderPane cardBevanda(BeanBevanda bevandaBean) {
        BorderPane card = new BorderPane();

        // Immagine della bevanda
        HBox imgBox;
        ImageView imageView;
        Image image;
        try {
            if (bevandaBean.getImmagine() != null && bevandaBean.getImmagine().getBinaryStream() != null) {
                InputStream inputStream = bevandaBean.getImmagine().getBinaryStream();
                image = new Image(inputStream, 120, 120, true, true); // Immagine più grande
            } else {
                image = new Image(DEFAULT_IMAGE);
            }
        } catch (SQLException e) {
            image = new Image(DEFAULT_IMAGE);
        }

        imageView = new ImageView(image);
        imageView.setFitHeight(100); // Immagine più grande
        imageView.setFitWidth(90);
        Rectangle clip = new Rectangle(90, 100); // Clipping più grande
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        imgBox = new HBox(imageView);
        card.setLeft(imgBox);

        // Nome della bevanda
        Label titleLabel = new Label(bevandaBean.getNome());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;"); // Testo più grande

        // Icone per aumentare/diminuire la quantità
        ImageView diminuisciIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/meno_icon.png");
        diminuisciIcon.setFitHeight(20); // Icona più grande
        diminuisciIcon.setFitWidth(20);
        ImageView aumentaIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/piu_icon.png");
        aumentaIcon.setFitHeight(20); // Icona più grande
        aumentaIcon.setFitWidth(20);
        Label quantitaLabel = new Label(bevandaBean.getQuantita().toString());
        quantitaLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande

        // Aggiungi azioni alle icone
        aumentaIcon.setOnMouseClicked(event -> aggiungiAlCarrello(bevandaBean, quantitaLabel));
        diminuisciIcon.setOnMouseClicked(event -> rimuoviDalCarrello(bevandaBean, quantitaLabel));

        // Prezzo
        Label prezzoLabel = new Label(bevandaBean.getPrezzo() + "€");
        prezzoLabel.setStyle(LABEL_TEXT_STYLE); // Testo più grande

        // Spaziatore per spingere infoBox a destra
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Lo spaziatore occupa tutto lo spazio disponibile



        HBox infoBox = new HBox(diminuisciIcon, quantitaLabel, aumentaIcon);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setSpacing(10);
        // Creazione di un GridPane per titleAndInfoBox
        GridPane titleAndInfoBox = new GridPane();
        titleAndInfoBox.setAlignment(Pos.CENTER_LEFT);
        titleAndInfoBox.setMinWidth(1200);
        titleAndInfoBox.setHgap(10); // Spazio orizzontale tra le colonne

// Definisci le colonne
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30); // 40% della larghezza per il titolo e il prezzo
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS); // Lo spaziatore occupa lo spazio rimanente
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS); // Lo spaziatore occupa lo spazio rimanente
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(30); // 30% della larghezza per le icone di quantità
        titleAndInfoBox.getColumnConstraints().addAll(col1, col2, col3, col4, col5);

// Aggiungi gli elementi al GridPane
        titleAndInfoBox.add(titleLabel, 0, 0); // Titolo e prezzo nella prima colonna
        titleAndInfoBox.add(prezzoLabel, 2, 0);  // Icone di quantità nella terza colonna
        titleAndInfoBox.add(infoBox,4,0);

        // Contenuto principale
        HBox mainContent = new HBox(imgBox, titleAndInfoBox);
        mainContent.setSpacing(20); // Più spazio tra immagine e contenuto
        mainContent.setAlignment(Pos.CENTER_LEFT);

        // Icona delle info
        ImageView infoIcon = new ImageView("C:/Users/marco/OneDrive/Desktop/project ISPW/Tap-Go/tapngo/src/main/images/info_icon.png");
        infoIcon.setFitHeight(30); // Icona più grande
        infoIcon.setFitWidth(30);
        StackPane infoPane = new StackPane(infoIcon);
        StackPane.setAlignment(infoIcon, Pos.TOP_RIGHT);

        infoIcon.setOnMouseClicked(event -> {mostraDettagliBevanda(bevandaBean);});

        // Imposta il contenuto della card
        card.setCenter(mainContent);
        card.setRight(infoPane);
        card.setPadding(new Insets(15)); // Più spazio interno
        card.setStyle(CARD_STYLE);

        return card;
    }
    private void mostraDettagliBevanda(BeanBevanda bevandaBean) {

        // Crea un nuovo Stage per il popup
        Stage popupStage = new Stage();
        popupStage.setTitle("Dettagli bevanda");

        // Crea il contenuto del popup
        VBox popupContent = new VBox();
        popupContent.setSpacing(10);
        popupContent.setPadding(new Insets(20));
        popupContent.setStyle(BACKGROUND_COLOR);
        popupContent.setAlignment(Pos.CENTER);

        try{
            // Creazione grafica di titolo, prezzo, immagine piatto e descrizione
            VBox popupInitialContent = createInitialContent1(bevandaBean);


            // Aggiungi tutti i contenuti creati al layout principale
            popupContent.getChildren().addAll(popupInitialContent);

            // Inserisci il contenuto nel ScrollPane
            ScrollPane localScrollPane = new ScrollPane(popupContent);
            localScrollPane.setFitToWidth(true);

            // Imposta la visualizzazione all'inizio
            localScrollPane.setVvalue(0);

            // Imposta il layout come scena del popup
            Scene popupScene = new Scene(localScrollPane, 600, 600);
            popupStage.setScene(popupScene);
            // Esecuzione codice passato per forzare lo scroll all'inizio dopo il rendering della GUI
            popupStage.show();
            Platform.runLater(() -> localScrollPane.setVvalue(0));
            // Mostra il popup
            popupStage.show();
        } catch (SQLException e) {
            Popup.mostraPopup(ERROR_MESSAGE_TITLE, "Errore nel caricamento dei dettagli del piatto.", ERROR_POPUP_TYPE);
        }
    }
    private VBox createInitialContent1(BeanBevanda bevandaBean) throws SQLException {

        // Crea VBox per il titolo e dettagli
        VBox popupInitialContent = new VBox();
        popupInitialContent.setAlignment(Pos.CENTER);
        popupInitialContent.setSpacing(5);

        // Titolo del piatto
        Label titolo = new Label(bevandaBean.getNome());
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 22px;");
        titolo.setAlignment(Pos.CENTER);

        // Dettaglio prezzo e alcolico sopra l'immagine
        Label prezzo = new Label(bevandaBean.getPrezzo() + "€"+ " • " + bevandaBean.getAlcolico());
        prezzo.setStyle(SUB_LABEL_STYLE);
        prezzo.setAlignment(Pos.CENTER);

        // Gestione grafica dell'immagine del piatto
        ImageView immaginePiatto;
        if (bevandaBean.getImmagine() != null && bevandaBean.getImmagine().getBinaryStream() != null) {
            immaginePiatto = new ImageView(new Image(bevandaBean.getImmagine().getBinaryStream()));
            immaginePiatto.setFitWidth(250);
            immaginePiatto.setFitHeight(150);
        } else {
            immaginePiatto = new ImageView(DEFAULT_IMAGE);
            immaginePiatto.setFitWidth(140);
            immaginePiatto.setFitHeight(140);
        }
        immaginePiatto.setPreserveRatio(true);
        Rectangle clip = new Rectangle(immaginePiatto.getFitWidth(), immaginePiatto.getFitHeight());
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        immaginePiatto.setClip(clip);

        // Descrizione della bevanda
        Label descrizione = new Label(bevandaBean.getDescrizione());
        descrizione.setStyle("-fx-font-size: 13px; -fx-text-fill: #666666; -fx-text-alignment: justify;");
        descrizione.setWrapText(true);



        // Aggiungi gli elementi iniziali al VBox
        popupInitialContent.getChildren().addAll(titolo, prezzo, immaginePiatto, descrizione);

        // Restituisci il contenuto delle informazioni iniziali
        return popupInitialContent;
    }
    private void aggiungiAlCarrello(BeanItemCarrello item, Label quantitaLabel ){
        ordine.aggiungiAlCarrello(item);
        Platform.runLater(() -> quantitaLabel.setText(String.valueOf(item.getQuantita())));
    }

    private void rimuoviDalCarrello(BeanItemCarrello item, Label quantitaLabel){
        if(item.getQuantita()==0){
            Popup.mostraPopup(WARNING_MESSAGE_TITLE,"non puoi togliere qualcosa che non c'è",WARNING_POPUP_TYPE);
        }else{ordine.rimuoviDalCarrello(item);
            Platform.runLater(() -> quantitaLabel.setText(String.valueOf(item.getQuantita())));}
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

