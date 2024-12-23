package com.tapngo.model.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanRistoranti {

    private String nome;
    private String città;
    private String tipologia;
    private String prezzo;
    private Integer valutazione;
    private String tipo;
    private List<BeanRistorante> listRistoranti;

    // Costruttore bean per restituzione lista
    public BeanRistoranti() {
        this.listRistoranti = new ArrayList<>();
    }

    public BeanRistoranti(String nome, String città, String tipologia, String prezzo, String valutazione, String tipo) {
        this.nome = nome;
        this.città = città;
        this.tipologia = tipologia;
        this.prezzo = prezzo;
        this.valutazione = valutazione.length();
        this.tipo = tipo;
    }
    public BeanRistoranti(String città, String tipologia, String prezzo, String tipo) {
        this.città = città;
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

    public String getCittà() {
        return città;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCittà(String città) {
        this.città = città;
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
