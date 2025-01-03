package com.tapngo.controller;

import com.tapngo.exception.DAOException;
import com.tapngo.model.bean.BeanRistorante;
import com.tapngo.model.bean.BeanRistoranti;
import com.tapngo.model.dao.RecuperaRistorantiDAO;
import com.tapngo.model.domain.ListRistoranti;
import com.tapngo.model.domain.Ristorante;

import java.sql.SQLException;

public class EffettuaOrdineControllerApplicativo {

    ListRistoranti listRistoranti;
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
}
