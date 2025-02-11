package com.tapngo.model.bean;

import java.sql.Blob;

public class BeanPiatto {
    private Float prezzo;
    private String ingredienti;
    private String descrizione;
    private String urlVideo;
    private Blob immagine;
    private String nome;

    public BeanPiatto(String nome, Float prezzo, String ingredienti, String descrizione, String urlVideo, Blob immagine ) {
        this.prezzo = prezzo;
        this.ingredienti = ingredienti;
        this.descrizione = descrizione;
        this.urlVideo = urlVideo;
        this.immagine = immagine;
        this.nome = nome;
    }
}
