package org.example.models;

import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Diese Klasse bietet Funktionen zum Speichern und Laden eines WortTrainer-Objekts.
 * Die Speicherung kann im XML-Format oder als Java-Serialized-Objekt erfolgen.
 */
public class SpeichernLaden implements SpeichernLadenInterface{

    /**
     * Speichert das WortTrainer-Objekt in einer Datei.
     * Wählt das Format (XML oder Java-Serialization) basierend auf dem übergebenen xml-Flag.
     *
     * @param wt   das WortTrainer-Objekt, das gespeichert werden soll
     * @param path der Pfad zur Datei, in der das Objekt gespeichert werden soll
     * @param useXml  true, um XML zu verwenden; false für Java-Serialization
     */
    public static void save(WortTrainer wt, String path, boolean useXml) {
        if (useXml) {
            saveXml(wt, path); // Speichere im XML-Format
        } else {
            saveObject(wt, path); // Speichere im Java-Serialized-Format
        }
    }


    /**
     * Lädt ein WortTrainer-Objekt aus einer Datei.
     * Wählt das Format (XML oder Java-Serialization) basierend auf dem übergebenen xml-Flag.
     *
     * @param path der Pfad zur Datei, aus der das Objekt geladen werden soll
     * @param useXml  true, um XML zu verwenden; false für Java-Serialization
     * @return das geladene WortTrainer-Objekt
     */public static WortTrainer load(String path, boolean useXml) {
        if (useXml) {
            return loadXml(path); // Lade im XML-Format
        } else {
            return loadObject(path); // Lade im Java-Serialized-Format
        }
    }


    /**
     * Speichert das WortTrainer-Objekt im XML-Format.
     *
     * @param wt   das WortTrainer-Objekt, das gespeichert werden soll
     * @param path der Pfad zur Datei, in der das Objekt gespeichert werden soll
     */
    private static void saveXml(WortTrainer wt, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(WortTrainer.class); // Erstelle JAXB-Kontext
            Marshaller marshaller = context.createMarshaller(); // Erstelle Marshaller
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // Formatierte Ausgabe aktivieren
            marshaller.marshal(wt, new File(path)); // Marshalle das WortTrainer-Objekt
        } catch (JAXBException e) {
            throw new RuntimeException("Fehler beim Speichern im XML-Format: " + e.getMessage(), e); // Fehlerbehandlung
        }
    }

    /**
     * Lädt ein WortTrainer-Objekt aus einer XML-Datei.
     *
     * @param path der Pfad zur Datei, aus der das Objekt geladen werden soll
     * @return das geladene WortTrainer-Objekt
     */
    private static WortTrainer loadXml(String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(WortTrainer.class); // Erstelle JAXB-Kontext
            Unmarshaller unmarshaller = context.createUnmarshaller(); // Erstelle Unmarshaller
            return (WortTrainer) unmarshaller.unmarshal(new File(path)); // Unmarshalle die Datei
        } catch (JAXBException e) {
            throw new RuntimeException("Fehler beim Laden aus XML-Datei: " + e.getMessage(), e); // Fehlerbehandlung
        }
    }

    /**
     * Speichert das WortTrainer-Objekt im Java-Serialized-Format.
     *
     * @param wt   das WortTrainer-Objekt, das gespeichert werden soll
     * @param path der Pfad zur Datei, in der das Objekt gespeichert werden soll
     */
    private static void saveObject(WortTrainer wt, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(wt); // Schreibe das WortTrainer-Objekt
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Speichern des Objekts: " + e.getMessage(), e); // Fehlerbehandlung
        }
    }

    /**
     * Lädt ein WortTrainer-Objekt aus einer Java-Serialized-Datei.
     *
     * @param path der Pfad zur Datei, aus der das Objekt geladen werden soll
     * @return das geladene WortTrainer-Objekt
     */
    private static WortTrainer loadObject(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (WortTrainer) ois.readObject(); // Lese das WortTrainer-Objekt
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Fehler beim Laden des Objekts: " + e.getMessage(), e); // Fehlerbehandlung
        }
    }

    /**
     * Überprüft die Eingabeparameter für die save-Methode.
     *
     * @param wt   das WortTrainer-Objekt
     * @param path der Pfad zur Datei
     * @throws IllegalArgumentException wenn ein Parameter ungültig ist
     */
    private static void validateParameters(WortTrainer wt, String path) {
        if (wt == null) {
            throw new IllegalArgumentException("WortTrainer darf nicht null sein");
        }
        validatePath(path);
    }

    /**
     * Überprüft den Pfad auf Validität.
     *
     * @param path der Pfad zur Datei
     * @throws IllegalArgumentException wenn der Pfad ungültig ist
     */
    private static void validatePath(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Pfad darf nicht null oder leer sein");
        }
    }

    @Override
    public void save(WortTrainer wortTrainer, String filePath) {

    }

    @Override
    public WortTrainer load(String filePath) {
        return null;
    }
}
