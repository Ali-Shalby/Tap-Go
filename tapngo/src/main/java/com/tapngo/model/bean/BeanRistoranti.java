package com.tapngo.model.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanRistoranti {

    private String nome;
    private String citta;
    private String tipologia;
    private Integer prezzo;
    private Float valutazione;
    private String tipo;
    private List<BeanRistorante> listRistoranti;

    // Costruttore bean per restituzione lista
    public BeanRistoranti() {
        this.listRistoranti = new ArrayList<>();
    }

    public BeanRistoranti(String nome, String citta, String tipologia, String prezzo, String valutazione, String tipo) {
        this.nome = nome;
        this.citta = citta;
        this.tipologia = tipologia;
        if (prezzo.contains("Tanto")) {
            this.prezzo = 40;
        } else if (prezzo.contains("Medio")) {
            this.prezzo = 20;
        } else if (prezzo.contains("Poco")) {
            this.prezzo = 10;
        } else {
            this.prezzo = 0;
        }
        if(valutazione.equals("Indifferente")){
            this.valutazione = (float) 0;
        }
        else{
            this.valutazione = (float) valutazione.length();
        }
        this.tipo = tipo;
    }
    public BeanRistoranti(String tipo) {
        this.listRistoranti = new ArrayList<>();
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipologia() {
        return tipologia;
    }

    public Integer getPrezzo() {
        return prezzo;
    }

    public Float getValutazione() {
        return valutazione;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCitta() {
        return citta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
    }

    public void setValutazione(Float valutazione) {
        this.valutazione = valutazione;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    // Metodo per la restituzione della lista di ricette
    public List<BeanRistorante> getListRistoranti() {
        return listRistoranti;
    }

    // Metodo per l'aggiunta di una ricetta alla lista di ricette
    public void addRistorante(BeanRistorante beanRistorante){
        listRistoranti.add(beanRistorante);
    }
}
