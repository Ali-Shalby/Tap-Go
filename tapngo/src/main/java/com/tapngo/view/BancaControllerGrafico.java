package com.tapngo.view;

import com.tapngo.model.bean.BeanCreditCard;

public class BancaControllerGrafico {

    public String mandaPagamento(BeanCreditCard datiBanca, double importo) {
        if(importo>0){
            return "accettato";
        }
        return "rifiutato";
    }
}
