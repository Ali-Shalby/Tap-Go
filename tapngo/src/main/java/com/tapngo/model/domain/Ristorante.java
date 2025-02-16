package com.tapngo.model.domain;


import java.sql.Blob;

public class Ristorante {
    private String nome;
    private String citta;
    private String tipologia;
    private Integer prezzo;
    private double valutazione;
    private String tipo;
    private String indirizzo;
    private String numTelefono;
    private Blob immagine;

    public Ristorante(String nome, String citta, String tipologia, Integer prezzo, double valutazione, String tipo) {
        this.nome = nome;
        this.citta = citta;
        this.tipologia = tipologia;
        this.prezzo = prezzo;
        this.valutazione = valutazione;
        this.tipo = tipo;
    }

    public Ristorante(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public Blob getImmagine() {
        return immagine;
    }

    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }
}
