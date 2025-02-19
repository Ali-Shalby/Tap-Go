package com.tapngo.model.domain;

import java.sql.Blob;

public interface ItemCarrello {
    double getPrezzo();
    String getNome();

    Blob getImmagine();

}
