package com.tapngo.view;

import com.tapngo.model.bean.BeanCarrello;
import com.tapngo.model.bean.BeanCreditCard;

public class BancaControllerGrafico {

    public String mandaPagamento(BeanCreditCard datiBanca, BeanCarrello carrello) {
        if(carrello.getTotalPrice()>0 && datiBanca.getCardNumber()!=null){
            return "accettato";
        }else{return "rifiutato";}
    }
}
