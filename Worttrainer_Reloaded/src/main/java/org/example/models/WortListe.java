package org.example.models;

import java.io.Serializable;

/**
 * Diese Klasse repräsentiert eine Liste von WortEinträgen (Wort und URL).
 * Sie bietet Funktionen zum Hinzufügen, Entfernen und Abrufen der Länge der Liste.
 */
public class WortListe implements Serializable {
    // Ein Array, das die WortEinträge speichert
    private WortEintrag[] worte;

    /**
     * Konstruktor für die Erstellung einer WortListe.
     * Erstellt die Liste basierend auf den übergebenen Worten und URLs.
     * @param worte Ein Array von Wörtern.
     * @param urls Ein Array von URLs, die den Wörtern entsprechen.
     * @throws IllegalArgumentException Wenn die Länge der Arrays nicht übereinstimmt.
     */
    public WortListe(String[] worte, String[] urls) {
        // Überprüfung, ob beide Arrays gleich lang sind
        if (worte.length != urls.length) {
            throw new IllegalArgumentException("Worte und URLs müssen die gleiche Länge haben.");
        }

        // Initialisierung des worte-Arrays als WortEintrag-Array
        this.worte = new WortEintrag[worte.length];

        // Befüllen des worte-Arrays mit WortEintrag-Objekten
        for (int i = 0; i < worte.length; i++) {
            this.worte[i] = new WortEintrag(worte[i], urls[i]);
        }
    }

    /**
     * Entfernt ein Wort aus der Liste.
     * @param wort Das zu entfernende Wort.
     * @return true, wenn das Wort gefunden und entfernt wurde, false andernfalls.
     */
    public boolean removeWort(String wort) {
        boolean existiert = false; // Gibt an, ob das Wort gefunden wurde

        // Erstellen eines neuen Arrays, das ein Element weniger hat
        WortEintrag[] newWords = new WortEintrag[worte.length - 1];

        // Durchlaufen der aktuellen Liste
        for (int i = 0; i < this.worte.length; i++) {
            // Wenn das Wort nicht mit dem gesuchten übereinstimmt, wird es in das neue Array eingefügt
            if (!this.worte[i].getWort().equals(wort)) {
                newWords[i] = new WortEintrag(this.worte[i].getWort(), this.worte[i].getUrl());
            } else {
                existiert = true; // Das Wort wurde gefunden
            }
        }

        // Wenn das Wort gefunden wurde, aktualisieren wir die Liste mit dem neuen Array
        if (existiert) {
            this.worte = newWords;
        }

        return existiert; // Rückgabe, ob das Wort entfernt wurde oder nicht
    }

    /**
     * Fügt der Liste ein neues Wort mit der zugehörigen URL hinzu.
     * @param wort Das hinzuzufügende Wort.
     * @param url Die zugehörige URL.
     */
    public void WortDazu(String wort, String url) {
        // Erstellen eines neuen WortEintrags
        WortEintrag we = new WortEintrag(wort, url);

        // Erstellen eines neuen Arrays, das ein Element mehr hat
        WortEintrag[] newWords = new WortEintrag[worte.length + 1];

        // Kopieren der bestehenden Einträge in das neue Array
        for (int i = 0; i < this.worte.length; i++) {
            newWords[i] = this.worte[i];
        }

        // Hinzufügen des neuen WortEintrags am Ende
        newWords[worte.length] = we;

        // Aktualisieren der Liste
        this.worte = newWords;
    }

    /**
     * Gibt die Länge der WortListe zurück.
     * @return Die Anzahl der WortEinträge in der Liste.
     */
    public int getLength() {
        return this.worte.length;
    }

    /**
     * Überschreibt die toString-Methode, um eine formatierte Ausgabe der WortListe zu erhalten.
     * @return Eine Zeichenkette, die die Liste der WortEinträge darstellt.
     */
    public String toString() {
        return this.worte.toString(); // Gibt die Array-Darstellung der WortEinträge zurück
    }
}
