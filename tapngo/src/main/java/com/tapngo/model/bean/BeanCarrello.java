package com.tapngo.model.bean;

import com.tapngo.model.domain.ItemCarrello;

import java.util.ArrayList;
import java.util.List;

public class BeanCarrello {

    private String ristorante;
    private List<BeanItemCarrello> items;
    private Integer numElementi;

    private double totalPrice;


    public BeanCarrello(){
            items = new ArrayList<>();
        }

    public void setNumElementi(Integer numElementi) {
        this.numElementi = numElementi;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void add(BeanItemCarrello item){
        items.add(item);
    }

    public void remove(BeanItemCarrello item){
        items.remove(item);
    }

    public Integer getNumElementi(){
        return numElementi;
    }
    public List<BeanItemCarrello> getListaItems(){
        return items;
    }

    public void setRistorante(String ristorante) {
        this.ristorante = ristorante;
    }
}
