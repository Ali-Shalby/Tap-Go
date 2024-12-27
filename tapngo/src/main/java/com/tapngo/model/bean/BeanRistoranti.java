package com.tapngo.model.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanRistoranti {

    private String nome;
    private String citta;
    private String tipologia;
    private String prezzo;
    private Integer valutazione;
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
            this.prezzo = "tanto";
        } else if (prezzo.contains("Medio")) {
            this.prezzo = "medio";
        } else if (prezzo.contains("Poco")) {
            this.prezzo = "poco";
        } else {
            this.prezzo = prezzo;
        }
        if(valutazione == "Indifferente"){
            this.valutazione = 0;
        }
        else{
            this.valutazione = valutazione.length();
        }
        this.tipo = tipo;
    }
    public BeanRistoranti(String citta, String tipologia, String prezzo, String tipo) {
        this.citta = citta;
        this.tipologia = tipologia;
        this.prezzo = prezzo;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public Integer getValutazione() {
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

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public void setValutazione(Integer valutazione) {
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
