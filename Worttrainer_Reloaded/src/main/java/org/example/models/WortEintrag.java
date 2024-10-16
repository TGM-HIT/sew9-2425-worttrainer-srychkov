package org.example.models;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Diese Klasse repräsentiert einen WortEintrag, der ein Wort und eine URL speichert.
 * Sie bietet Methoden zum Abrufen und Überprüfen der URL.
 */
public class WortEintrag {
    // Private Felder für das Wort und die URL
    private String wort;
    private String url;

    /**
     * Konstruktor für das Erstellen eines neuen WortEintrags.
     * @param wort Das Wort, das gespeichert werden soll.
     * @param url Die URL, die mit dem Wort verknüpft ist.
     */
    public WortEintrag(String wort, String url) {
        this.wort = wort;
        this.url = url;
    }

    /**
     * Getter-Methode, um das Wort abzurufen.
     * @return Das gespeicherte Wort.
     */
    public String getWort(){
        return wort;
    }

    /**
     * Getter-Methode, um die URL abzurufen.
     * @return Die gespeicherte URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Diese Methode überprüft, ob die gegebene URL gültig ist.
     * Sie konvertiert den String in ein URL-Objekt und überprüft seine Syntax.
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
            throw new RuntimeException(e);
        }
    }

    /**
     * Überschreibt die toString-Methode, um eine formatierte Ausgabe des WortEintrags zu erhalten.
     * @return Eine Zeichenkette, die das Wort und die URL darstellt.
     */
    public String toString(){
        return "-" + wort + '\n' + "-" + url + '\n';
    }
}
