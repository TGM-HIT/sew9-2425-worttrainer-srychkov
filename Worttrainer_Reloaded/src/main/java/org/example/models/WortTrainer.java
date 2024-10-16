package org.example.models;

import java.io.Serializable;
import java.util.Random;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * Diese Klasse repräsentiert einen Worttrainer, der Benutzer beim Lernen von Wörtern unterstützt.
 * Sie ermöglicht das Abrufen zufälliger Wörter und das Überprüfen der Eingaben des Benutzers.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WortTrainer implements Serializable {
    // Die WortListe, die die Wörter enthält
    @XmlElement
    private WortListe wl;

    // Der aktuelle Index des Wortes, das trainiert wird
    private int currentIndex;

    // Zähler für die Anzahl der Eingaben des Benutzers
    private int eingaben = 0;

    // Zähler für die Anzahl der richtigen Antworten des Benutzers
    private int richtig = 0;

    // Zufallszahlengenerator
    private Random random = new Random();

    /**
     * Getter-Methode, um den aktuellen Index abzurufen.
     * @return Der aktuelle Index.
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Getter-Methode, um die Anzahl der Eingaben abzurufen.
     * @return Die Anzahl der Benutzer-Eingaben.
     */
    public int getEingaben() {
        return eingaben;
    }

    /**
     * Getter-Methode, um die Anzahl der richtigen Antworten abzurufen.
     * @return Die Anzahl der richtigen Antworten.
     */
    public int getRichtig() {
        return richtig;
    }

    /**
     * Konstruktor für den WortTrainer.
     * @param wl Die WortListe, die verwendet wird.
     * @throws IllegalArgumentException Wenn die WortListe null ist.
     */
    public WortTrainer(WortListe wl) {
        if (wl == null) {
            throw new IllegalArgumentException("WortListe kann nicht null sein");
        }
        this.wl = wl; // Initialisierung der WortListe
    }

    /**
     * Wählt ein zufälliges Wort aus der WortListe aus.
     * @return Das zufällig ausgewählte Wort.
     */
    public String randomWort() {
        // Setzt den currentIndex auf einen zufälligen Wert innerhalb der Grenzen der WortListe
        this.currentIndex = random.nextInt(wl.getLength());
        return this.wl.getWort(currentIndex); // Gibt das Wort am aktuellen Index zurück
    }
    public String getImageUrlForWord(String word) {
        // Beispielimplementation. Du musst diese Methode anpassen,
        // um die korrekte URL für das gegebene Wort zurückzugeben.
        for (int i = 0; i < this.wl.getLength(); i++) {
            if (this.wl.getWort(i).equalsIgnoreCase(word)) {
                return this.wl.getUrl(i); // gibt die zugehörige URL zurück
            }
        }
        return null; // falls kein Bild gefunden wurde
    }

    /**
     * Fügt ein neues Wort mit der zugehörigen URL zur WortListe hinzu.
     * @param wort Das hinzuzufügende Wort.
     * @param url Die zugehörige URL.
     */
    public void wortHinzufuegen(String wort, String url) {
        this.wl.wortHinzufuegen(wort, url); // Ruft die Methode zum Hinzufügen des Wortes auf
    }

    /**
     * Überprüft, ob das eingegebene Wort korrekt ist.
     * @param wort Das zu überprüfende Wort.
     * @return true, wenn das Wort korrekt ist, andernfalls false.
     */
    public boolean checkWort(String wort) {
        eingaben++; // Erhöht den Eingabezähler
        if (this.wl.getWort(currentIndex).equals(wort)) {
            richtig++; // Erhöht den Zähler für richtige Antworten
            return true; // Das Wort ist korrekt
        }
        return false; // Das Wort ist nicht korrekt
    }

    /**
     * Überprüft, ob das eingegebene Wort korrekt ist, ignoriert dabei die Groß- und Kleinschreibung.
     * @param wort Das zu überprüfende Wort.
     * @return true, wenn das Wort korrekt ist, andernfalls false.
     */
    public boolean checkWortIgnoreLU(String wort) {
        eingaben++; // Erhöht den Eingabezähler
        if (this.wl.getWort(currentIndex).equalsIgnoreCase(wort)) {
            richtig++; // Erhöht den Zähler für richtige Antworten
            return true; // Das Wort ist korrekt
        }
        return false; // Das Wort ist nicht korrekt
    }

    /**
     * Getter-Methode für die WortListe.
     * @return Die WortListe des WortTrainings.
     */
    public WortListe getWl() {
        return wl; // Gibt die WortListe zurück
    }
}
