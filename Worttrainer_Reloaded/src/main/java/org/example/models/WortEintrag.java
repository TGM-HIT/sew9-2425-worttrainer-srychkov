package org.example.models;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@XmlRootElement
/**
 * Diese Klasse repräsentiert einen WortEintrag, der ein Wort und eine URL speichert.
 * Sie bietet Methoden zum Abrufen und Überprüfen der URL.
 */
public class WortEintrag implements Serializable {
    // Private Felder für das Wort und die URL
    @XmlElement
    private String wort;
    @XmlElement
    private String url;

    /**
     * Konstruktor für das Erstellen eines neuen WortEintrags.
     * Validiert die Eingabewerte für Wort und URL.
     *
     * @param wort Das Wort, das gespeichert werden soll.
     * @param url Die URL, die mit dem Wort verknüpft ist.
     * @throws IllegalArgumentException Wenn das Wort oder die URL ungültig ist.
     */
    public WortEintrag(String wort, String url) {
        if (wort == null || wort.trim().isEmpty()) {
            throw new IllegalArgumentException("Wort darf nicht null oder leer sein.");
        }
        if (!checkURL(url)) {
            throw new IllegalArgumentException("Ungültige URL: " + url);
        }
        this.wort = wort;
        this.url = url;
    }

    /**
     * Getter-Methode, um das Wort abzurufen.
     *
     * @return Das gespeicherte Wort.
     */
    public String getWort() {
        return wort;
    }

    /**
     * Getter-Methode, um die URL abzurufen.
     *
     * @return Die gespeicherte URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Diese Methode überprüft, ob die gegebene URL gültig ist.
     * Sie konvertiert den String in ein URL-Objekt und überprüft seine Syntax.
     *
     * @param url Die zu prüfende URL.
     * @return true, wenn die URL gültig ist, false wenn nicht.
     */
    public static boolean checkURL(String url) {
        try {
            // Versucht, die URL zu einer URI zu konvertieren und auf Fehler zu prüfen
            new URL(url).toURI();
            return true; // URL ist gültig
        } catch (URISyntaxException e) {
            return false; // URL hat eine ungültige Syntax
        } catch (MalformedURLException e) {
            // Bei einem ungültigen URL-Format wird eine RuntimeException ausgelöst
            throw new RuntimeException("Fehler beim Erstellen der URL: " + e.getMessage(), e);
        }
    }

    /**
     * Überschreibt die toString-Methode, um eine formatierte Ausgabe des WortEintrags zu erhalten.
     *
     * @return Eine Zeichenkette, die das Wort und die URL darstellt.
     */
    @Override
    public String toString() {
        return "Wort: " + wort + "\nURL: " + url + "\n";
    }
}
