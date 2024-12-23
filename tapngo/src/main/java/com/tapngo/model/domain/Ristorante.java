package com.tapngo.model.domain;


import java.sql.Blob;

public class Ristorante {
    private String nome;
    private String città;
    private String tipologia;
    private String prezzo;
    private Integer valutazione;
    private String tipo;
    private String indirizzo;
    private String numTelefono;
    private transient Blob immagine;

    public Ristorante(String nome, String città, String tipologia, String prezzo, Integer valutazione, String tipo) {
        this.nome = nome;
        this.città = città;
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

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        if(prezzo < 20) {
            this.prezzo = "Basso";
        }
        else if((prezzo>=20) && (prezzo <40 )){
            this.prezzo = "Medio";
        }
        else{
            this.prezzo = "Alto";
        }
    }

    public Integer getValutazione() {
        return valutazione;
    }

    public void setValutazione(Integer valutazione) {
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
