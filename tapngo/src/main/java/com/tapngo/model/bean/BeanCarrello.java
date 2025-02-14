package com.tapngo.model.bean;

import com.tapngo.model.domain.ItemCarrello;

import java.util.ArrayList;
import java.util.List;

public class BeanCarrello {

    private final String ristorante;
    private List<BeanItemCarrello> items;
    private Integer numElementi;

    private float totalPrice;


    public BeanCarrello(BeanRistorante beanRistorante){
            items = new ArrayList<>();
            this.ristorante = beanRistorante.getNome();
        }

    public void setNumElementi(Integer numElementi) {
        this.numElementi = numElementi;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
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

}
