package com.tapngo.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListBevande {
    private List<Bevanda> listaBevande = new ArrayList<>();

    // Metodo per aggiungere una bevanda alla lista
    public void addBevanda(Bevanda bevanda) {this.listaBevande.add(bevanda);}

    // Metodo per ottenere la lista delle bevande
    public List<Bevanda> getListaBevande(){
        return listaBevande;
    }
}
