package org.example;

import org.example.models.SpeichernLaden;
import org.example.models.WortListe;
import org.example.models.WortTrainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Main Klasse
 * @author Sergej Rychkov
 * @version 16-10-2024
 */
public class Handler {
    public WortTrainer wt;
    private JFrame frame; // Fenster für das Spiel
    private JLabel wordLabel; // Label für das aktuelle Wort
    private JLabel statsLabel; // Label für die Statistiken
    private JButton addButton; // Button zum Hinzufügen eines Wortes
    private JButton exitButton; // Button zum Beenden des Spiels
    private JButton checkButton; // Button zur Überprüfung der Antwort
    private JTextField answerField; // Textfeld für die Eingabe der Antwort
    private JLabel imageLabel; // Label für das Bild

    public static void main(String[] args) {
        new Handler(); // Controller Instanz wird erstellt
    }

    public Handler() {
        // Versuch, gespeicherte Daten zu laden
        try {
            this.wt = SpeichernLaden.load(".\\worttrainer.save", false);
        } catch (RuntimeException e) {
            // Wenn keine Daten vorhanden sind, erstelle einen neuen WortTrainer
            this.wt = new WortTrainer(new WortListe(new String[]{
                    "Dog", "Cat"},
                    new String[]{
                            "https://static.vecteezy.com/system/resources/thumbnails/005/857/332/small_2x/funny-portrait-of-cute-corgi-dog-outdoors-free-photo.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/800px-Cat03.jpg"
                    }));
        }

        // Spiel starten
        startGame();
    }

    private void startGame() {
        // GUI erstellen
        frame = new JFrame("Wort Trainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel für das Wort und die Statistiken
        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout());

        // Label für das aktuelle Wort
        wordLabel = new JLabel();
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordPanel.add(wordLabel);

        // Label für die Statistiken
        statsLabel = new JLabel();
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordPanel.add(statsLabel);

        // Panel in das Hauptfenster hinzufügen
        frame.add(wordPanel, BorderLayout.NORTH);

        // Label für das Bild
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 200)); // Festlegen der Größe für das Bild
        frame.add(imageLabel, BorderLayout.CENTER);

        // Panel für die Eingabe und den Überprüfen-Button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Textfeld für die Eingabe der Antwort
        answerField = new JTextField();
        answerField.setHorizontalAlignment(SwingConstants.CENTER);
        answerField.setPreferredSize(new Dimension(200, 30)); // Größe des Textfeldes festlegen
        inputPanel.add(answerField); // Textfeld zum Eingabe-Panel hinzufügen

        // Button zur Überprüfung der Antwort
        checkButton = new JButton("Uberprufen");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(); // Methode zur Überprüfung der Antwort aufrufen
            }
        });
        inputPanel.add(checkButton); // Button zum Eingabe-Panel hinzufügen

        // Eingabe-Panel nach unten hinzufügen
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Button zum Hinzufügen eines Wortes
        addButton = new JButton("Wort hinzufugen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWord(); // Methode zum Hinzufügen eines Wortes aufrufen
            }
        });
        frame.add(addButton, BorderLayout.WEST);

        // Button zum Beenden des Spiels
        exitButton = new JButton("Spiel beenden");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                JOptionPane.showMessageDialog(frame, "Das Spiel wurde beendet. Die Daten wurden gespeichert.");
                System.exit(0); // Anwendung beenden
            }
        });
        frame.add(exitButton, BorderLayout.EAST);

        // Spiel aktualisieren
        update();
        frame.setVisible(true); // Fenster sichtbar machen
    }

    public void update() {
        // Anzeige der bisherigen Statistiken
        String stats = "Eingaben: " + getInputs() + " | Richtig: " + getCorrect();
        statsLabel.setText(stats);

        // Aktuelles Wort abrufen und anzeigen
        String currentWord = wt.randomWort();
        wordLabel.setText("Aktuelles Wort: " + currentWord);
        answerField.setText(""); // Textfeld leeren

        // Bild anzeigen
        showImage(currentWord); // Bild basierend auf dem aktuellen Wort anzeigen
    }

    public int getCorrect() {
        return this.wt.getRichtig();
    }

    public int getInputs() {
        return this.wt.getEingaben();
    }

    public void check(String input) {
        // Überprüfen, ob die Eingabe null ist
        if (input == null) return;

        // Eingabe überprüfen
        wt.checkWort(input);
    }

    public void save() {
        SpeichernLaden.save(this.wt, ".\\worttrainer.save", false);
    }

    public void load() {
        this.wt = SpeichernLaden.load(".\\worttrainer.save", false);
        update(); // GUI nach dem Laden aktualisieren
    }

    public void newGame() {
        this.wt = new WortTrainer(new WortListe(new String[]{
                "Dog", "Cat"},
                new String[]{
                        "https://static.vecteezy.com/system/resources/thumbnails/005/857/332/small_2x/funny-portrait-of-cute-corgi-dog-outdoors-free-photo.jpg",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/800px-Cat03.jpg"
                }));
        update(); // GUI nach der Neuerstellung aktualisieren
    }

    public void addWord() {
        // Eingabefelder für das Wort und die URL
        String word = JOptionPane.showInputDialog("Bitte geben Sie das Wort ein:");
        String url = JOptionPane.showInputDialog("Bitte geben Sie die zugehörige URL ein:");

        // Überprüfen, ob die Eingabe gültig ist
        if (word != null && !word.isEmpty() && url != null && !url.isEmpty()) {
            wt.wortHinzufuegen(word, url);
            JOptionPane.showMessageDialog(frame, "Wort erfolgreich hinzugefügt!");
            update(); // GUI aktualisieren
        } else {
            JOptionPane.showMessageDialog(frame, "Bitte geben Sie ein gültiges Wort und eine URL ein.");
        }
    }

    /**
     * Zeigt ein Bild von der URL an, die dem aktuellen Wort zugeordnet ist.
     */
    private void showImage(String word) {
        String imageUrl = wt.getImageUrlForWord(word); // Stelle sicher, dass diese Methode existiert
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                URL url = new URL(imageUrl);
                ImageIcon icon = new ImageIcon(url);
                imageLabel.setIcon(icon); // Bild im Label anzeigen
                imageLabel.setText(""); // Text im Label leeren
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Fehler beim Laden des Bildes: " + e.getMessage());
            }
        } else {
            imageLabel.setIcon(null); // Kein Bild anzeigen, wenn die URL nicht vorhanden ist
        }
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText(); // Text aus dem Eingabefeld abrufen
        if (userAnswer != null && !userAnswer.isEmpty()) {
            // Überprüfung der Eingabe
            if (wt.checkWort(userAnswer)) {
                JOptionPane.showMessageDialog(frame, "Richtige Antwort!");
            } else {
                JOptionPane.showMessageDialog(frame, "Falsche Antwort! Versuche es erneut.");
            }
            // Nach der Überprüfung ein neues Wort und Bild anzeigen
            update(); // Aktualisieren der Anzeige mit einem neuen Wort und Bild
        } else {
            JOptionPane.showMessageDialog(frame, "Bitte gib eine Antwort ein.");
        }
    }
}
