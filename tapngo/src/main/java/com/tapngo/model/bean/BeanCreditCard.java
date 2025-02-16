package com.tapngo.model.bean;

public class BeanCreditCard {
    private String cardNumber;
    private String scadenza;
    private String cvc;

    // Costruttore con controlli di validità
    public BeanCreditCard(String cardNumber, String scadenza, String cvc) throws IllegalArgumentException {
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Il numero della carta deve contenere esattamente 16 cifre.");
        }
        if (!isValidExpiryDate(scadenza)) {
            throw new IllegalArgumentException("La data di scadenza deve essere nel formato MM/YY.");
        }
        if (!isValidCVC(cvc)) {
            throw new IllegalArgumentException("Il CVC deve contenere esattamente 3 cifre.");
        }

        this.cardNumber = cardNumber;
        this.scadenza = scadenza;
        this.cvc = cvc;
    }

    // Metodo per validare il numero della carta (12 cifre)
    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    // Metodo per validare la data di scadenza (formato MM/YY)
    private boolean isValidExpiryDate(String expiryDate) {
        return expiryDate != null && expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}");
    }

    // Metodo per validare il CVC (3 cifre)
    private boolean isValidCVC(String cvc) {
        return cvc != null && cvc.matches("\\d{3}");
    }

    // Getter per il numero della carta
    public String getCardNumber() {
        return cardNumber;
    }

    // Getter per la data di scadenza
    public String getScadenza() {
        return scadenza;
    }

    // Getter per il CVC
    public String getCvc() {
        return cvc;
    }
}