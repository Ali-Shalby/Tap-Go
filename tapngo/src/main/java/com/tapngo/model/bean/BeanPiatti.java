package com.tapngo.model.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanPiatti {
    String piatto;
    private List<BeanPiatto> listPiatti;

    // Costruttore bean per restituzione lista
    public BeanPiatti() {
        this.listPiatti = new ArrayList<>();
    }

    public BeanPiatti(String piatto) {
        this.listPiatti = new ArrayList<>();
        this.piatto = piatto;
    }
    // Metodo per la restituzione della lista di piatti
    public List<BeanPiatto> getPiatti() {
        return listPiatti;
    }
    // Metodo per l'aggiunta di un piatto alla lista di piatti
    public void addPiatto(BeanPiatto beanPiatto){listPiatti.add(beanPiatto);}
}
