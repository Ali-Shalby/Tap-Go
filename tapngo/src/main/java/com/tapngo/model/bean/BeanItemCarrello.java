package com.tapngo.model.bean;

import java.sql.Blob;

public interface BeanItemCarrello {
    double getPrezzo();
    String getNome();
    Integer getQuantita();
    void setQuantita(Integer valore);
    Blob getImmagine();

}
