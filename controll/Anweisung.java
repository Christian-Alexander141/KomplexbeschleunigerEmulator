package controll;

/**
 * Klasse für Anweisungen (berechnung, ausgabe, speichern)
 * 
 * ============================================================================
 * 
 * Diese Klasse wurde von der KI erstellt, um die Java-Namenskonventionen
 * (Klassennamen beginnen mit Großbuchstaben) einzuhalten.
 * 
 * Die ursprüngliche Version (anweisung.java) bleibt für Kompatibilität erhalten.
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 2.0 - 11.11.2024
 */
public class Anweisung {

    private String anweisung;
    
    public Anweisung(String anweisung) {
        if (!check(anweisung)) {
            throw new IllegalArgumentException("Ungültige Anweisung");
        }
        else {
            this.anweisung = anweisung;
        }
    }

    public boolean check (String anweisung) {
        if (anweisung.equals("ausgabe") || anweisung.equals("berechnung") || anweisung.equals("speichern")) {
            return true;
        } else {
            return false;
        }
    }

    public String getAnweisung() {
        return anweisung;
    }

    public void setAnweisung(String anweisung) {
        if (!check(anweisung)) {
            throw new IllegalArgumentException("Ungültige Anweisung");
        }
        else {
            this.anweisung = anweisung;
        }
    }
    
}
