package com.tapngo.model.domain;

public class PrimoPiatto extends Piatto{
    // Attributi
    private String carboidrato;               // Esempio: "pasta", "riso", "couscous"
    private String sugo;                    // Esempio: "pomodoro", "bianco"
    private String tipoAroma;               // Esempio: "parmigiano", "peperoncino", "erbe aromatiche"

    // Getter
    public String getTipoPasta() {
        return carboidrato;
    }
    public String getSugo() {
        return sugo;
    }
    public String getTipoAroma() {
        return tipoAroma;
    }

    // Setter
    public void setTipoPasta(String carboidrato) {
        this.carboidrato = carboidrato;
    }
    public void setSugo(String sugo) {
        this.sugo = sugo;
    }
    public void setTipoAroma(String tipoAroma) {
        this.tipoAroma = tipoAroma;
    }

    @Override
    public String getCategoria() {
        return "Primi piatti";
    }
}
