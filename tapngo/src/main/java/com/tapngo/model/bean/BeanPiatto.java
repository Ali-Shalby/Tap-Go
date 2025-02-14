package com.tapngo.model.bean;

import java.sql.Blob;

public class BeanPiatto implements BeanItemCarrello{
    private Float prezzo;
    private String ingredienti;
    private String descrizione;
    private String urlVideo;
    private Blob immagine;
    private String nome;

    private Integer quantita = 0;

    public BeanPiatto(String nome, Float prezzo, String ingredienti, String descrizione, String urlVideo, Blob immagine ) {
        this.prezzo = prezzo;
        this.ingredienti = ingredienti;
        this.descrizione = descrizione;
        this.urlVideo = urlVideo;
        this.immagine = immagine;
        this.nome = nome;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public Blob getImmagine() {
        return immagine;
    }

    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
