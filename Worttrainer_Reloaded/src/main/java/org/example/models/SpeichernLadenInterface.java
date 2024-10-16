package org.example.models;

/**
 * Interface für unterschiedliche Speicher und Lade Methoden
 */
public interface SpeichernLadenInterface {
    void save(WortTrainer wortTrainer, String filePath);
    WortTrainer load(String filePath);
}