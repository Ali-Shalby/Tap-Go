package com.tapngo.model.bean;

import java.sql.Blob;

public class BeanBevanda {
    private String nome;
    private Float prezzo;
    private String descrizione;
    private Boolean alcolico;
    private Blob immagine;

    public BeanBevanda(String nome, Float prezzo, String descrizione, Boolean alcolico, Blob immagine) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.alcolico = alcolico;
        this.immagine = immagine;
    }
}
