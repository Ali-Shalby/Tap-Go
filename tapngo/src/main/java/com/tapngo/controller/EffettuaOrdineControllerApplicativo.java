package com.tapngo.controller;

import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.*;
import com.tapngo.model.dao.*;
import com.tapngo.model.domain.*;
import com.tapngo.view.BancaControllerGrafico;

import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EffettuaOrdineControllerApplicativo {

    ListRistoranti listRistoranti;
    ListPiatti piatti;
    ListBevande bevande;
    Carrello carrello;
    public BeanRistoranti mostraRistoranti(BeanRistoranti filtri) throws DAOException, SQLException {

        String nomeRistorante = filtri.getNome();
        String citta = filtri.getCitta();
        String tipologia = filtri.getTipologia();
        Integer prezzo = filtri.getPrezzo();
        double valutazione = filtri.getValutazione();
        String tipo = filtri.getTipo();

        Ristorante cercaRistorante = new Ristorante(nomeRistorante, citta, tipologia,prezzo,valutazione,tipo);
        RecuperaRistorantiDAO recuperaRistorantiDAO = new RecuperaRistorantiDAO();
        listRistoranti = recuperaRistorantiDAO.recuperaRistorantiExecute(cercaRistorante) ;
        BeanRistoranti ristorantiBean = new BeanRistoranti (tipo);

        // Itero attraverso la lista di ristoranti e li aggiungo al BeanRistoranti
        for (Ristorante ristorante : listRistoranti.getListaRistoranti()) {
            BeanRistorante beanRistorante = new BeanRistorante(ristorante.getNome(),
                    ristorante.getCitta(),
                    ristorante.getTipologia(),
                    ristorante.getPrezzo(),
                    ristorante.getIndirizzo(),
                    ristorante.getImmagine(),
                    ristorante.getValutazione()
            );

            ristorantiBean.addRistorante(beanRistorante);
        }


        return ristorantiBean;
    }

    public BeanPiatti mostraPiatti(BeanRistorante ristoranteBean) throws DAOException, SQLException {

        String nomeRistorante = ristoranteBean.getNome();
        BeanPiatti piattiBean = new BeanPiatti(nomeRistorante);
        for(Ristorante ristorante: listRistoranti.getListaRistoranti()){
            if(Objects.equals(ristorante.getNome(), nomeRistorante)){

                RecuperaPiattiDAO recuperaPiattiDAO = new RecuperaPiattiDAO();
                piatti = recuperaPiattiDAO.recuperaPiattiExecute(ristorante);
            }
        }
        // Itero attraverso la lista di piatti e li aggiungo al BeanPiatti
        for (Piatto piatto : piatti.getListaPiatti()) {
            BeanPiatto beanPiatto = new BeanPiatto(piatto.getNome(),
                    piatto.getPrezzo(),
                    piatto.getIngredienti(),
                    piatto.getDescrizione(),
                    piatto.getUrlVideo(),
                    piatto.getImmagine()
            );

           piattiBean.addPiatto(beanPiatto);
        }
        return  piattiBean;
    }

    public BeanBevande mostraBevande(BeanRistorante ristoranteBean) throws DAOException, SQLException {

        String nomeRistorante = ristoranteBean.getNome();
        BeanBevande bevandeBean = new BeanBevande(nomeRistorante);
        for(Ristorante ristorante: listRistoranti.getListaRistoranti()){
            if(Objects.equals(ristorante.getNome(), nomeRistorante)){

                RecuperaBevandeDAO recuperaBevandeDAO = new RecuperaBevandeDAO();
                bevande = recuperaBevandeDAO.recuperaBevandeExecute(ristorante);
            }
        }
        // Itero attraverso la lista di bevande e le aggiungo al BeanBevande
        for (Bevanda bevanda : bevande.getListaBevande()) {
            BeanBevanda beanBevanda = new BeanBevanda(bevanda.getNome(),
                    bevanda.getPrezzo(),
                    bevanda.getDescrizione(),
                    bevanda.getAlcolico(),
                    bevanda.getImmagine()
            );

            bevandeBean.addBevanda(beanBevanda);
        }
        return  bevandeBean;
    }
    public BeanCarrello creaCarrello(BeanRistorante ristoranteBean, String username){
        String nomeRistorante = ristoranteBean.getNome();
        for(Ristorante ristorante: listRistoranti.getListaRistoranti()){
            if(Objects.equals(ristorante.getNome(), nomeRistorante)){

                carrello = new Carrello(ristorante, username);
            }
        }
        BeanCarrello carrelloBean = new BeanCarrello();
        carrelloBean.setRistorante(nomeRistorante);
        return carrelloBean;
    }
    public void aggiungiAlCarrello(BeanItemCarrello itemBean) {

        String nomeItem = itemBean.getNome();
        for (ItemCarrello itemScelto : piatti.getListaPiatti()) {
            if (Objects.equals(itemScelto.getNome(), nomeItem)) {
                carrello.add(itemScelto);
                itemBean.setQuantita(itemBean.getQuantita() + 1);
            }
        }
        for (ItemCarrello itemScelto : bevande.getListaBevande()) {
            if (Objects.equals(itemScelto.getNome(), nomeItem)) {
                carrello.add(itemScelto);
                itemBean.setQuantita(itemBean.getQuantita() + 1);
            }
        }
    }

    public void rimuoviDalCarrello(BeanItemCarrello itemBean) {
        String nomeItem = itemBean.getNome();

        // Crea una copia della lista per l'iterazione
        List<ItemCarrello> copiaLista = new ArrayList<>(carrello.getListaItems());

        for (ItemCarrello itemScelto : copiaLista) {
            if (Objects.equals(itemScelto.getNome(), nomeItem)) {
                carrello.remove(itemScelto); // Modifica la lista originale
                itemBean.setQuantita(itemBean.getQuantita() - 1);
                break;
            }
        }

    }

    public BeanCarrello mostraCarrello() {

        BeanCarrello carrelloBean = new BeanCarrello();
        carrelloBean.setRistorante(carrello.getRistorante());


        // Itera sugli item nel carrello
        for (ItemCarrello item : carrello.getListaItems()) {
            boolean trovato = false;

            // Controlla se l'item è già presente nel carrelloBean
            for (BeanItemCarrello beanItem : carrelloBean.getListaItems()) {
                if (Objects.equals(item.getNome(), beanItem.getNome())) {
                    // Se l'item è già presente, incrementa la quantità
                    beanItem.setQuantita(beanItem.getQuantita() + 1);
                    trovato = true;
                    break;
                }
            }

            // Se l'item non è stato trovato nel carrelloBean, aggiungilo
            if (!trovato) {
                if (piatti.getListaPiatti().contains(item)) {
                    // Se è un piatto, crea un BeanPiatto
                    BeanPiatto beanPiatto = new BeanPiatto(item.getNome());
                    beanPiatto.setImmagine(item.getImmagine());
                    beanPiatto.setPrezzo(item.getPrezzo());
                    beanPiatto.setQuantita(1);
                    carrelloBean.add(beanPiatto);
                } else {
                    // Se è una bevanda, crea un BeanBevanda
                    BeanBevanda beanBevanda = new BeanBevanda(item.getNome());
                    beanBevanda.setImmagine(item.getImmagine());
                    beanBevanda.setPrezzo(item.getPrezzo());
                    beanBevanda.setQuantita(1);
                    carrelloBean.add(beanBevanda);
                }
            }
        }
        carrelloBean.setTotalPrice(carrello.getTotalPrice());
        carrelloBean.setNumElementi(carrello.getNumElementi());

        return carrelloBean;
    }

    public void effettuaOrdine(BeanCreditCard carta, BeanCarrello carrelloBean) throws Exception {


        String data = carta.getScadenza();
        double importo = carrelloBean.getTotalPrice();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MM/yy");
        YearMonth scadenzaCarta = YearMonth.parse(data, formatter);
        YearMonth dataCorrente = YearMonth.now();
        if(scadenzaCarta.isBefore(dataCorrente)){
            throw new IllegalArgumentException("la carta inserita è scaduta");
        }else{
            BeanCreditCard datiBanca = new BeanCreditCard(carta.getCardNumber(), carta.getScadenza(), carta.getCvc());
            BancaControllerGrafico banca = new BancaControllerGrafico();
            String stato = banca.mandaPagamento(datiBanca, importo);
            if(stato != "accettato"){
                throw new Exception("ci sono stati errori di comunicazione con la banca");
            }
            salvaOrdine();

        }

    }
    public void salvaOrdine() throws DAOException, SQLException {
        SalvaOrdineDAO salvaOrdineDAO = new SalvaOrdineDAO();
        salvaOrdineDAO.salvaOrdineExecute(carrello);
        SalvaOrdineFS salvaOrdineFS = new SalvaOrdineFS();
        salvaOrdineFS.salvaOrdineSuFile(carrello);
    }


}
