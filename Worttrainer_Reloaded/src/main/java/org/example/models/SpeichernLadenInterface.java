package org.example.models;

public interface SpeichernLadenInterface {
    void save(WortTrainer wortTrainer, String filePath);
    WortTrainer load(String filePath);
}