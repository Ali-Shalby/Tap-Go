package com.tapngo.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

    private final String ristorante;
    private final String username;
    private List<ItemCarrello> items;
    private Integer numElementi = 0;

    public Carrello(Ristorante ristorante, String username){
        items = new ArrayList<>();
        this.ristorante = ristorante.getNome();
        this.username = username;
    }

    public void remove(ItemCarrello item){
        items.remove(item);
        numElementi--;
    }

    public void add(ItemCarrello item){
        items.add(item);
        numElementi++;
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for(ItemCarrello item : items){
            totalPrice += item.getPrezzo();
        }
        return Math.round(totalPrice*100.0)/100.0;
    }
    public String getUsername(){
        return username;
    }

    public Integer getNumElementi() {
        return numElementi;
    }
    public List<ItemCarrello> getListaItems(){
        return items;
    }

    public String getRistorante() {
        return ristorante;
    }
}
