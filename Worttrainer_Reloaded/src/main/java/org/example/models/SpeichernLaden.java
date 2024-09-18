package org.example.models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SpeichernLaden {

    /**
     * H
     */
    public static void speichern(WortTrainer wt, String path){
        if(wt == null){
            throw new NullPointerException("WortTrainer was null");
        }
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))) {
            os.writeObject(wt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Hier wird ein File ausgewählt und als WortTrainer hochgeladen.
     * Dadurch wird ein vorheriges WortTrainer geladen
     * @return der WortTrainer File mit allen Bildern etc.
     */
    public WortTrainer laden(){

        return null;
    }
}
