package Bus;

import Math.KomplexeZahl;

/**
 * Datenbus-Register (Data Pointer Register)
 * 
 * ============================================================================
 * 
 * Dies ist ein Register, das Daten (insbesondere komplexe Zahlen) speichert,
 * die über den Datenbus übertragen werden sollen.
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 1.0 - 11.11.2024
 */
public class DPR {
    
    /**
     * Richtung der Datenübertragung
     */
    public enum Direction {
        READ, WRITE
    }
    
    /**
     * Daten (als komplexe Zahl dargestellt)
     */
    private KomplexeZahl data;
    
    /**
     * Richtung der Operation
     */
    private Direction direction;
    
    /**
     * Erzeugt ein neues Datenbus-Register
     */
    public DPR() {
        this.data = new KomplexeZahl(0, 0);
        this.direction = Direction.READ;
    }
    
    /**
     * Erzeugt ein neues Datenbus-Register mit initialen Daten
     * 
     * @param data Initiale Daten (komplexe Zahl)
     */
    public DPR(KomplexeZahl data) {
        this.data = data;
        this.direction = Direction.READ;
    }
    
    /**
     * Setzt die Daten
     * 
     * @param data Neue Daten
     */
    public void setData(KomplexeZahl data) {
        this.data = data;
    }
    
    /**
     * Gibt die aktuellen Daten zurück
     * 
     * @return Aktuelle Daten
     */
    public KomplexeZahl getData() {
        return data;
    }
    
    /**
     * Setzt die Richtung
     * 
     * @param direction Neue Richtung
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    /**
     * Gibt die Richtung zurück
     * 
     * @return Aktuelle Richtung
     */
    public Direction getDirection() {
        return direction;
    }
    
    /**
     * Setzt Daten und Richtung gleichzeitig
     * 
     * @param data Neue Daten
     * @param direction Neue Richtung
     */
    public void set(KomplexeZahl data, Direction direction) {
        this.data = data;
        this.direction = direction;
    }
    
    /**
     * Setzt die Daten mit Real- und Imaginärteil
     * 
     * @param realteil Realteil
     * @param imaginaerteil Imaginärteil
     */
    public void setData(double realteil, double imaginaerteil) {
        this.data.setset(realteil, imaginaerteil);
    }
    
    /**
     * Gibt den Realteil der Daten zurück
     * 
     * @return Realteil
     */
    public double getRealteil() {
        return data.getRealteil();
    }
    
    /**
     * Gibt den Imaginärteil der Daten zurück
     * 
     * @return Imaginärteil
     */
    public double getImaginaerteil() {
        return data.getImaginaerteil();
    }
    
    /**
     * Gibt eine String-Darstellung zurück
     */
    @Override
    public String toString() {
        return "DPR { data: " + data + ", direction: " + direction + " }";
    }
}
