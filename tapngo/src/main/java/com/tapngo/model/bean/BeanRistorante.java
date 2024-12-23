package com.tapngo.model.bean;

import java.sql.Blob;

public class BeanRistorante {
    private String nome;
    private String città;
    private String tipologia;
    private String prezzo;
    private Integer valutazione;
    private Blob immagine;
    private String indirizzo;

    public BeanRistorante(String nome, String indirizzo, Blob immagine, Integer valutazione) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.immagine = immagine;
        this.valutazione = valutazione;
    }
}
