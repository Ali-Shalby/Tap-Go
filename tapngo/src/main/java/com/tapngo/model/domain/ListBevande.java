package com.tapngo.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListBevande {
    private List<Bevanda> listaBevande = new ArrayList<>();

    // Metodo per aggiungere un ristorante alla lista
    public void addBevanda(Bevanda bevanda) {this.listaBevande.add(bevanda);}

    // Metodo per ottenere la lista dei ristoranti
    public List<Bevanda> getListaBevande(){
        return listaBevande;
    }
}
