package com.tapngo.model.dao;

import com.tapngo.model.domain.Carrello;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SalvaOrdineFS {

    private static final Logger logger = Logger.getLogger(SalvaOrdineFS.class.getName());


    public void salvaOrdineSuFile(Carrello carrello) throws IOException {
        String nomeUtente = carrello.getUsername();
        String nomeFile = costruisciNomeFile(nomeUtente);
        gestisciCreazioneFile(nomeFile);
        double prezzo = carrello.getTotalPrice();
        Integer elementi = carrello.getNumElementi();
        String ristorante = carrello.getRistorante();
        salvaOrdine(nomeFile, ristorante, elementi, prezzo);

    }

    private String costruisciNomeFile(String nome) {
        return "tapngo/Ordini/" + nome + ".dat";
    }
    private void gestisciCreazioneFile(String nomeFile) {
        File file = new File(nomeFile);
        if (!file.exists()) {
            try {
                creaDirectory(file);
                creaFile(file);
            } catch (IOException e) {
                throw new IllegalArgumentException("Impossibile creare il file: " + nomeFile, e);
            }
        }
    }
    private void creaDirectory(File file) throws IOException {
        File dirParents = file.getParentFile();
        if (dirParents != null && !dirParents.exists()) {
            boolean directoryCreata = dirParents.mkdirs();
            if (!directoryCreata) {
                throw new IOException("Impossibile creare le directory necessarie per il file: " + file.getPath());
            }
        }
    }
    private void creaFile(File file) throws IOException {
        boolean fileCreato = file.createNewFile();
        if (fileCreato) {
            logger.log(Level.INFO, "File creato con successo!");
        }
    }
    private void salvaOrdine(String nomeFile, String ristorante, Integer elementi, double prezzo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            // Crea una stringa con tutti i dati concatenati
            String ordine = "Ristorante: " + ristorante + ", Elementi: " + elementi + ", Prezzo: " + prezzo;

            // Scrivi la stringa nel file
            writer.write(ordine);


        } catch (IOException e) {
            throw new IOException("Errore durante il salvataggio dell'ordine", e);
        }
    }
}
