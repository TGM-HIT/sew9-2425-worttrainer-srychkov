package org.example.models;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@XmlRootElement
/**
 * Diese Klasse repräsentiert einen WortEintrag, der ein Wort und eine URL speichert.
 * Sie bietet Methoden zum Abrufen und Überprüfen der URL.
 */
public class WortEintrag implements Serializable {

    private static final long serialVersionUID = 1L;

    // Define a pattern for validating image URLs
    private static final Pattern IMG_PATTERN = Pattern.compile(".*\\.(jpg|jpeg|png|webp|avif|gif)$", Pattern.CASE_INSENSITIVE);

    // Private fields for the word and URL
    @XmlElement
    private String wort;

    @XmlElement
    private String url;

    /**
     * Constructor to create a new WortEintrag.
     * Validates the input values for wort and url.
     *
     * @param wort The word to be stored.
     * @param url  The URL associated with the word.
     * @throws IllegalArgumentException If the word or URL is invalid.
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


    public static boolean checkURL(String url) {
        try {
            URL urlObj = new URL(url);
            urlObj.toURI(); // Syntaktische Prüfung der URL

            // Zusätzliche HTTP-Anfrage, um sicherzustellen, dass die URL erreichbar ist und ein Bild zurückgibt
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("HEAD"); // Nur den Header anfordern, nicht den gesamten Inhalt
            connection.setConnectTimeout(5000);  // Timeout auf 5 Sekunden setzen
            connection.connect();

            // Überprüfen, ob die URL existiert und ein gültiges Bild zurückgibt
            String contentType = connection.getContentType();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK || !contentType.startsWith("image/")) {
                JOptionPane.showMessageDialog(null, "Die URL existiert nicht oder verweist nicht auf ein Bild.");
                throw new RuntimeException("Die URL existiert nicht oder verweist nicht auf ein Bild.");
            }

            return isImgUrl(url); // Prüfung auf Bild-Dateiendung als zusätzliche Absicherung
        } catch (URISyntaxException | MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Sie haben eine ungültige URL bei der Erstellung von einem Wort benutzt.");
            throw new RuntimeException("Fehler beim Erstellen der URL: " + e.getMessage(), e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Die URL konnte nicht erreicht werden.");
            throw new RuntimeException("Fehler beim Zugriff auf die URL: " + e.getMessage(), e);
        }
    }


    /**
     * Getter method to retrieve the word.
     *
     * @return The stored word.
     */
    public String getWort() {
        return wort;
    }

    /**
     * Getter method to retrieve the URL.
     *
     * @return The stored URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Method to check if the given URL points to an image.
     *
     * @param url The URL to check.
     * @return true if the URL is an image URL, false otherwise.
     */
    public static boolean isImgUrl(String url) {
        if (url == null) {
            return false;
        }
        Matcher matcher = IMG_PATTERN.matcher(url);
        return matcher.matches();
    }

    /**
     * This method checks if the given URL is valid.
     * It converts the string into a URL object and checks its syntax.
     *
     * @param url The URL to check.
     * @return true if the URL is valid, false otherwise.
     */
    /**
    public static boolean checkURL(String url) {
        try {
            new URL(url).toURI(); // Try to convert the URL to a URI to check for syntax errors
            if(isImgUrl(url)) {
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Sie haben eine ungültige URL bei der Erstellung von einem Wort benutzt.");
                throw new RuntimeException("Fehler beim Erstellen der URL: ");
            }
        } catch (URISyntaxException | MalformedURLException e) {
            // If the URL is invalid, show an error dialog and throw a RuntimeException
            JOptionPane.showMessageDialog(null, "Sie haben eine ungültige URL bei der Erstellung von einem Wort benutzt.");
            throw new RuntimeException("Fehler beim Erstellen der URL: " + e.getMessage(), e);
        }
    }
**/
    /**
     * Overrides the toString method to provide a formatted output of the WortEintrag.
     *
     * @return A string representing the word and URL.
     */
    @Override
    public String toString() {
        return "Wort: " + wort + "\nURL: " + url + "\n";
    }
}
