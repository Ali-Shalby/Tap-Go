package com.tapngo.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

    private final String ristorante;
    private List<ItemCarrello> items;
    private Integer numElementi = 0;

    public Carrello(Ristorante ristorante){
        items = new ArrayList<>();
        this.ristorante = ristorante.getNome();
    }

    public void remove(ItemCarrello item){
        items.remove(item);
        numElementi--;
    }

    public void add(ItemCarrello item){
        items.add(item);
        numElementi++;
    }

    public float getTotalPrice(){
        float totalPrice = 0;
        for(ItemCarrello item : items){
            totalPrice += item.getPrezzo();
        }
        return totalPrice;
    }

    public Integer getNumElementi() {
        return numElementi;
    }
    public List<ItemCarrello> getListaItems(){
        return items;
    }
}
