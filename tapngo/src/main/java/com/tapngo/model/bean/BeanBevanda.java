package com.tapngo.model.bean;

import java.sql.Blob;

public class BeanBevanda implements BeanItemCarrello{
    private String nome;
    private Float prezzo;
    private String descrizione;
    private String alcolico;
    private Blob immagine;
    private Integer quantita = 0;

    public BeanBevanda(String nome, Float prezzo, String descrizione, Boolean alcolico, Blob immagine) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        if(Boolean.TRUE.equals(alcolico)){
            this.alcolico = "alcolico";
        }else{this.alcolico = "non alcolico";}
        this.immagine = immagine;
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

    public String getAlcolico() {
        return alcolico;
    }

    public void setAlcolico(String alcolico) {
        this.alcolico = alcolico;
    }

    public Blob getImmagine() {
        return immagine;
    }

    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }
}
