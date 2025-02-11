package com.tapngo.model.domain;

public class Dolce extends Piatto{
    // Attributi
    private String tipoDolce;               // Esempio: "torta", "biscotti", "mousse"
    private String ingredientiSpeciali;     // Esempio: "cioccolato", "frutta", "noci"

    // Getter
    public String getTipoDolce() {
        return tipoDolce;
    }
    public String getIngredientiSpeciali() {
        return ingredientiSpeciali;
    }

    // Setter
    public void setTipoDolce(String tipoDolce) {
        this.tipoDolce = tipoDolce;
    }
    public void setIngredientiSpeciali(String ingredientiSpeciali) {
        this.ingredientiSpeciali = ingredientiSpeciali;
    }

    @Override
    public String getCategoria() {
        return "Dolci";
    }
}
