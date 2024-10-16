package org.example.gui;

import org.example.Controller;
import javax.swing.*;

/**
 * GUI-Klasse zur Interaktion mit dem WortTrainer.
 * Ermöglicht die Anzeige von Wortpaaren, Eingabe von Antworten und Anzeige von Statistiken.
 */

public class GUI {
/*
    private final Controller controller; // Referenz auf den Controller
    private String currentWord; // Das aktuelle Wort, das gelernt werden soll

    public GUI(Controller controller) {
        this.controller = controller; // Controller initialisieren
        startGame(); // Spiel starten
    }

    /**
     * Startet das Spiel, indem das erste Wortpaar zufällig ausgewählt wird.
     */
    /**

    private void startGame() {
        currentWord = controller.wt.randomWort(); // Zufälliges Wort auswählen
        update(); // GUI aktualisieren
    }

    /**
     * Aktualisiert die GUI und zeigt die aktuelle Statistik und das Bild an.
     */
    /*
    public void update() {
        // Anzeige der bisherigen Statistiken
        String stats = "Eingaben: " + controller.getInputs() + "\n" +
                "Richtig: " + controller.getCorrect() + "\n";

        // Abfrage des vorherigen Ergebnisses
        String previousResult = controller.getInputs() > 0
                ? (controller.wt.checkWort(currentWord) ? "Vorherige Antwort: Richtig" : "Vorherige Antwort: Falsch")
                : "";

        // Nachricht zusammenstellen
        String message = "Aktuelles Wort: " + currentWord + "\n" + stats + previousResult +
                "\nGeben Sie das passende Wort ein (oder leer lassen zum Beenden):";

        // Eingabefeld für das Wort
        String input = JOptionPane.showInputDialog(null, message);

        // Wenn die Eingabe nicht leer ist, überprüfen wir das Wort
        if (input != null && !input.isEmpty()) {
            boolean isCorrect = controller.check(input); // Eingabe überprüfen
            currentWord = controller.wt.randomWort(); // Nächstes Wort zufällig auswählen
            // Zeige das Ergebnis der Überprüfung an
            JOptionPane.showMessageDialog(null, isCorrect ? "Richtig!" : "Falsch. Versuchen Sie es erneut.");
            update(); // GUI erneut aktualisieren
        } else {
            // Wenn die Eingabe leer ist, speichern wir den aktuellen Zustand
            controller.save();
            JOptionPane.showMessageDialog(null, "Das Spiel wurde beendet. Die Daten wurden gespeichert.");
            System.exit(0); // Anwendung beenden
        }
    }

    /**
     * Holt die Benutzereingabe aus dem Eingabefeld.
     *
     * @return Die vom Benutzer eingegebene Zeichenfolge oder null, wenn nichts eingegeben wurde.
     */
    /*
    public String getInput() {
        return JOptionPane.showInputDialog("Geben Sie das Wort ein:"); // Eingabeaufforderung für den Benutzer
    }
*/
}