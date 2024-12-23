package com.tapngo.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListRistoranti {
    private List<Ristorante> listaRistoranti = new ArrayList<>();

    // Metodo per aggiungere una ricetta alla lista
    public void addRistorante(Ristorante ristorante) {
        this.listaRistoranti.add(ristorante);
    }

    // Metodo per ottenere la lista delle ricette
    public List<Ristorante> getListaRistoranti(){
        return listaRistoranti;
    }
}
