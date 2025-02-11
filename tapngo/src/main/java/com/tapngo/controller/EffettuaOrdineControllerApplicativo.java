package com.tapngo.controller;

import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.*;
import com.tapngo.model.dao.RecuperaBevandeDAO;
import com.tapngo.model.dao.RecuperaPiattiDAO;
import com.tapngo.model.dao.RecuperaRistorantiDAO;
import com.tapngo.model.domain.*;

import java.sql.SQLException;
import java.util.Objects;

public class EffettuaOrdineControllerApplicativo {

    ListRistoranti listRistoranti;
    ListPiatti piatti;
    ListBevande bevande;
    public BeanRistoranti mostraRistoranti(BeanRistoranti filtri) throws DAOException, SQLException {

        String nomeRistorante = filtri.getNome();
        String citta = filtri.getCitta();
        String tipologia = filtri.getTipologia();
        Integer prezzo = filtri.getPrezzo();
        Float valutazione = filtri.getValutazione();
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
}
