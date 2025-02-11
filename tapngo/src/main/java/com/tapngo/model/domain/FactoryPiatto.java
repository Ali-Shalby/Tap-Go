package com.tapngo.model.domain;

public class FactoryPiatto {
    public static Piatto createPiatto(String categoria) {
        switch (categoria.toLowerCase()) {
            case "primo":
                return new PrimoPiatto();
            case "secondo":
                return new SecondoPiatto();
            case "contorno":
                return new Contorno();
            case "dolce":
                return new Dolce();
            case "antipasto":
                return new Antipasto();
            default:
                throw new IllegalArgumentException("Categoria non valida: " + categoria);
        }
    }
}
