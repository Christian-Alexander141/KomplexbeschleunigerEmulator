package Math;

/**
 * Klasse zur Darstellung komplexer Zahlen
 * 
 * ============================================================================
 * 
 * Diese Klasse wurde im Rahmen der Bachelorarbeit erstellt.
 * Die toString()-Methode wurde von der KI leicht verbessert,
 * um negative Imaginärteile korrekt darzustellen.
 * 
 * ZWECK:
 * - Speicherung von Real- und Imaginärteil einer komplexen Zahl
 * - Bereitstellung von Gettern und Settern für beide Teile
 * - Einfache Manipulation komplexer Zahlen
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung (toString()-Verbesserung)
 * @version 1.0 - 11.11.2024
 */
public class KomplexeZahl {
    
    /**
     * Realteil der komplexen Zahl (a in a + bi)
     */
    private double realteil;
    
    /**
     * Imaginärteil der komplexen Zahl (b in a + bi)
     */
    private double imaginaerteil;

    /**
     * Erzeugt eine neue komplexe Zahl
     * 
     * @param realteil Realteil der Zahl (a in a + bi)
     * @param imaginaerteil Imaginärteil der Zahl (b in a + bi)
     */
    public KomplexeZahl(double realteil, double imaginaerteil) {
        this.realteil = realteil;
        this.imaginaerteil = imaginaerteil;
    }

    /**
     * Gibt den Realteil zurück
     * 
     * @return Realteil der komplexen Zahl
     */
    public double getRealteil() {
        return realteil;
    }

    /**
     * Gibt den Imaginärteil zurück
     * 
     * @return Imaginärteil der komplexen Zahl
     */
    public double getImaginaerteil() {
        return imaginaerteil;
    }

    /**
     * Gibt Real- und Imaginärteil als Array zurück
     * 
     * @return Array mit [realteil, imaginaerteil]
     */
    public double [] getset() {
        double [] set = {realteil, imaginaerteil};
        return set;
    }
    
    /**
     * Setzt den Realteil
     * 
     * @param realteil Neuer Realteil
     */
    public void setRealteil(double realteil) {
        this.realteil = realteil;
    }

    /**
     * Setzt den Imaginärteil
     * 
     * @param imaginaerteil Neuer Imaginärteil
     */
    public void setImaginaerteil(double imaginaerteil) {
        this.imaginaerteil = imaginaerteil;
    }

    /**
     * Setzt Real- und Imaginärteil gleichzeitig
     * 
     * @param realteil Neuer Realteil
     * @param imaginaerteil Neuer Imaginärteil
     */
    public void setset(double realteil, double imaginaerteil) {
        this.realteil = realteil;
        this.imaginaerteil = imaginaerteil;
    }
    
    /**
     * Gibt eine String-Darstellung der komplexen Zahl zurück
     * 
     * FORMAT:
     * - Positiver Imaginärteil: "a + bi"
     * - Negativer Imaginärteil: "a - bi" (statt "a + -bi")
     * 
     * @return String-Darstellung im Format "a + bi" oder "a - bi"
     */
    public String toString() {
        if (imaginaerteil >= 0) {
            return realteil + " + " + imaginaerteil + "i";
        } else {
            // Bei negativem Imaginärteil: a - |b|i statt a + -|b|i
            return realteil + " - " + (-imaginaerteil) + "i";
        }
    }
}
