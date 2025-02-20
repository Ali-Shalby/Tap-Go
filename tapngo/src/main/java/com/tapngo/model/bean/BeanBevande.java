package com.tapngo.model.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanBevande {
    String bevanda;
    private List<BeanBevanda> listBevande;

    // Costruttore bean per restituzione lista
    public BeanBevande() {
        this.listBevande = new ArrayList<>();
    }

    public BeanBevande(String bevanda) {
        this.listBevande = new ArrayList<>();
        this.bevanda = bevanda;
    }
    // Metodo per la restituzione della lista di bevande
    public List<BeanBevanda> getBevande() {
        return listBevande;
    }
    // Metodo per l'aggiunta di una bevanda alla lista di bevande
    public void addBevanda(BeanBevanda beanBevanda){listBevande.add(beanBevanda);}
}
