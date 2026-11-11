package Bus;

import Math.KomplexeZahl;

/**
 * Datenbus (Data Data Address)
 * 
 * ============================================================================
 * 
 * Der Datenbus überträgt Daten (insbesondere komplexe Zahlen) zwischen Komponenten.
 * Er wird genutzt, um Registerinhalte, Speicherwerte oder Berechnungsergebnisse
 * zu transportieren.
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 1.0 - 11.11.2024
 */
public class DDA {
    
    /**
     * Richtung der Datenübertragung
     */
    public enum Direction {
        READ, WRITE
    }
    
    /**
     * Aktuelle Daten auf dem Bus (als komplexe Zahl)
     */
    private KomplexeZahl data;
    
    /**
     * Richtung der Operation
     */
    private Direction direction;
    
    /**
     * Erzeugt einen neuen Datenbus
     */
    public DDA() {
        this.data = new KomplexeZahl(0, 0);
        this.direction = Direction.READ;
    }
    
    /**
     * Erzeugt einen neuen Datenbus mit initialen Daten
     * 
     * @param data Initiale Daten
     */
    public DDA(KomplexeZahl data) {
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
     * Liest Daten vom Bus
     * 
     * @return Die gelesenen Daten
     */
    public KomplexeZahl read() {
        return this.data;
    }
    
    /**
     * Schreibt Daten auf den Bus
     * 
     * @param data Daten zum Schreiben
     */
    public void write(KomplexeZahl data) {
        this.data = data;
        this.direction = Direction.WRITE;
    }
    
    /**
     * Schreibt Daten und Richtung auf den Bus
     * 
     * @param data Daten
     * @param direction Richtung
     */
    public void write(KomplexeZahl data, Direction direction) {
        this.data = data;
        this.direction = direction;
    }
    
    /**
     * Schreibt Daten mit Real- und Imaginärteil auf den Bus
     * 
     * @param realteil Realteil
     * @param imaginaerteil Imaginärteil
     */
    public void write(double realteil, double imaginaerteil) {
        this.data.setset(realteil, imaginaerteil);
        this.direction = Direction.WRITE;
    }
    
    /**
     * Gibt eine String-Darstellung zurück
     */
    @Override
    public String toString() {
        return "DDA { data: " + data + ", direction: " + direction + " }";
    }
}
