package org.example.models;
import java.io.Serializable;
import java.net.URI;

/**
 * In dieser Klasse wird ein WortEintrag erstellt und kontrolliert
 */
public class WortEintrag {
    private String wort;
    private String url;
    public WortEintrag(String wort, String url) {
        this.wort = wort;
        this.url = url;
    }
    public String getWort(){
        return wort;
    }

    public String getUrl() {
        return url;
    }

    public String toString(){
        return "-" + wort + '\n' + "-" + url + '\n';
    }
}
