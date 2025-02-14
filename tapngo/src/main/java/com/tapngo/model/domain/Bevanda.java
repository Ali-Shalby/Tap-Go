package com.tapngo.model.domain;

import java.sql.Blob;

public class Bevanda implements ItemCarrello{
    private String nome;
    private Float prezzo;
    private String descrizione;
    private Boolean alcolico;
    private Blob immagine;


    public Bevanda(Boolean alcolico) {
        this.alcolico = alcolico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Boolean getAlcolico() {
        return alcolico;
    }

    public void setAlcolico(Boolean alcolico) {
        this.alcolico = alcolico;
    }

    public Blob getImmagine() {
        return immagine;
    }

    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }
}
