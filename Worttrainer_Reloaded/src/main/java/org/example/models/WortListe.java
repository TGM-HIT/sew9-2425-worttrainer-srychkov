package org.example.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.io.Serializable;

/**
 * Diese Klasse repräsentiert eine Liste von WortEinträgen (Wort und URL).
 * Sie bietet Funktionen zum Hinzufügen, Entfernen und Abrufen der Länge der Liste.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WortListe implements Serializable {
    // Ein Array, das die WortEinträge speichert
    @XmlElement(name = "wortEintrag")
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

        // Zähle, wie viele gültige Wörter bleiben werden
        int neueLaenge = 0;
        for (WortEintrag eintrag : worte) {
            if (!eintrag.getWort().equals(wort)) {
                neueLaenge++;
            } else {
                existiert = true; // Das Wort wurde gefunden
            }
        }

        // Wenn das Wort nicht gefunden wurde, gibt es nichts zu entfernen
        if (!existiert) {
            return false;
        }

        // Erstellen eines neuen Arrays mit der neuen Länge
        WortEintrag[] newWords = new WortEintrag[neueLaenge];
        int index = 0;

        // Füllen des neuen Arrays
        for (WortEintrag eintrag : worte) {
            if (!eintrag.getWort().equals(wort)) {
                newWords[index++] = eintrag;
            }
        }

        // Aktualisieren der Liste
        this.worte = newWords;

        return true; // Das Wort wurde erfolgreich entfernt
    }

    /**
     * Fügt der Liste ein neues Wort mit der zugehörigen URL hinzu.
     * @param wort Das hinzuzufügende Wort.
     * @param url Die zugehörige URL.
     */
    public void wortHinzufuegen(String wort, String url) {
        // Erstellen eines neuen WortEintrags
        WortEintrag we = new WortEintrag(wort, url);

        // Erstellen eines neuen Arrays, das ein Element mehr hat
        WortEintrag[] newWords = new WortEintrag[worte.length + 1];

        // Kopieren der bestehenden Einträge in das neue Array
        System.arraycopy(this.worte, 0, newWords, 0, this.worte.length);

        // Hinzufügen des neuen WortEintrags am Ende
        newWords[worte.length] = we;

        // Aktualisieren der Liste
        this.worte = newWords;
    }

    /**
     * Gibt das Wort am angegebenen Index zurück.
     * @param index Der Index des gewünschten Wortes.
     * @return Das Wort am angegebenen Index.
     * @throws IndexOutOfBoundsException Wenn der Index ungültig ist.
     */
    public String getWort(int index) {
        if (index < 0 || index >= worte.length) {
            throw new IndexOutOfBoundsException("Ungültiger Index: " + index);
        }
        return worte[index].getWort();
    }

    /**
     * Gibt die Länge der WortListe zurück.
     * @return Die Anzahl der WortEinträge in der Liste.
     */
    public int getLength() {
        return this.worte.length;
    }
    public String getUrl(int index){
        return worte[index].getUrl();
    }
    /**
     * Überschreibt die toString-Methode, um eine formatierte Ausgabe der WortListe zu erhalten.
     * @return Eine Zeichenkette, die die Liste der WortEinträge darstellt.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (WortEintrag eintrag : worte) {
            sb.append(eintrag.toString()).append("\n");
        }
        return sb.toString();
    }
}
