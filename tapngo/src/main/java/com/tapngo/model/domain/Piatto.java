package com.tapngo.model.domain;

import java.io.Serializable;
import java.sql.Blob;

public abstract class Piatto implements Serializable,ItemCarrello{

    protected String nomeRistorante;
    protected double prezzo;
    protected String ingredienti;
    protected String descrizione;
    protected String urlVideo;
    protected transient Blob immagine;
    protected String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeRistorante() {
        return nomeRistorante;
    }

    public void setNomeRistorante(String nomeRistorante) {
        this.nomeRistorante = nomeRistorante;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
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
    // Realizzazione
    public abstract String getCategoria();
}
