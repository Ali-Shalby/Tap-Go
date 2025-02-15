package com.tapngo.model.bean;

import java.sql.Blob;

public interface BeanItemCarrello {
    public double getPrezzo();
    public String getNome();
    public Integer getQuantita();
    public void setQuantita(Integer valore);
    public Blob getImmagine();

}
