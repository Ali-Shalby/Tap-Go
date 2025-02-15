package com.tapngo.model.bean;

import java.sql.Blob;

public class BeanRistorante {
    private String nome;
    private String citta;
    private String tipologia;
    private Integer prezzo;
    private double valutazione;
    private Blob immagine;
    private String indirizzo;

    public BeanRistorante(String nome, String citta, String tipologia, Integer prezzo, String indirizzo, Blob immagine, double valutazione) {
        this.nome = nome;
        this.citta = citta;
        this.tipologia = tipologia;
        this.prezzo = prezzo;
        this.indirizzo = indirizzo;
        this.immagine = immagine;
        this.valutazione = valutazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Blob getImmagine() {
        return immagine;
    }

    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}
