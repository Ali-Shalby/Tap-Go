package com.tapngo.model.domain;

public class Antipasto extends Piatto{
    // Attributi
    private String tipoAntipasto;              // Esempio: "tagliere", "fritti"
    private String tipoAccompagnamento;     // Esempio: "pane", "focaccia", "salsa"
    private String livelloSpezie;           // Esempio: "basso", "medio", "piccante"

    // Getter
    public String getTipoAntipasto() {
        return tipoAntipasto;
    }
    public String getTipoAccompagnamento() {
        return tipoAccompagnamento;
    }
    public String getLivelloSpezie() {
        return livelloSpezie;
    }

    // Setter
    public void setTipoAntipasto(String tipoAntipasto) {
        this.tipoAntipasto = tipoAntipasto;
    }
    public void setTipoAccompagnamento(String tipoAccompagnamento) {
        this.tipoAccompagnamento = tipoAccompagnamento;
    }
    public void setLivelloSpezie(String livelloSpezie) {
        this.livelloSpezie = livelloSpezie;
    }

    @Override
    public String getCategoria() {
        return "Pasto veloce";
    }
}
