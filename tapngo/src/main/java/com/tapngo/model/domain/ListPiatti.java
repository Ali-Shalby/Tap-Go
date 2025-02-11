package com.tapngo.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListPiatti {
    private List<Piatto> listaPiatti = new ArrayList<>();

    // Metodo per aggiungere un ristorante alla lista
    public void addPiatto(Piatto piatto) {this.listaPiatti.add(piatto);}

    // Metodo per ottenere la lista dei ristoranti
    public List<Piatto> getListaPiatti(){
        return listaPiatti;
    }
}
