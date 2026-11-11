package Bus;

/**
 * Steuerbus
 * 
 * ============================================================================
 * 
 * Der Steuerbus überträgt Steuerungssignale zwischen der CPU und dem
 * Beschleuniger. Er bestimmt, welche Operation ausgeführt werden soll und
 * welche Anweisung (berechnung, ausgabe, speichern) durchzuführen ist.
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 1.0 - 11.11.2024
 */
public class Steuerbus {

    /**
     * Richtung der Datenübertragung
     */
    public enum Direction {
        READ, WRITE
    }

    /**
     * Steuerungssignal - Operationen für den Beschleuniger
     */
    public enum Operation {
        ADD, SUB, MUL, DIV, NONE
    }
    
    /**
     * Steuerungssignal - Anweisungen für den Beschleuniger
     */
    public enum Anweisung {
        BERECHNUNG, AUSGABE, SPEICHERN, NONE
    }

    /**
     * Datenwert (für Adressen oder einfache Signale)
     */
    private int data;
    
    /**
     * Richtung der Operation
     */
    private Direction direction;
    
    /**
     * Aktuelle Operation
     */
    private Operation operation;
    
    /**
     * Aktuelle Anweisung
     */
    private Anweisung anweisung;

    /**
     * Erzeugt einen neuen Steuerbus
     */
    public Steuerbus() {
        this.data = 0;
        this.direction = Direction.READ;
        this.operation = Operation.NONE;
        this.anweisung = Anweisung.NONE;
    }

    /**
     * Erzeugt einen neuen Steuerbus mit initialen Werten
     * 
     * @param direction Richtung
     * @param data Datenwert
     */
    public Steuerbus(Direction direction, int data) {
        this.direction = direction;
        this.data = data;
        this.operation = Operation.NONE;
        this.anweisung = Anweisung.NONE;
    }

    /**
     * Setzt die Daten
     * 
     * @param data Neue Daten
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Gibt die Daten zurück
     * 
     * @return Aktuelle Daten
     */
    public int getData() {
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
     * Setzt die Operation
     * 
     * @param operation Neue Operation
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Gibt die Operation zurück
     * 
     * @return Aktuelle Operation
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Setzt die Anweisung
     * 
     * @param anweisung Neue Anweisung
     */
    public void setAnweisung(Anweisung anweisung) {
        this.anweisung = anweisung;
    }

    /**
     * Gibt die Anweisung zurück
     * 
     * @return Aktuelle Anweisung
     */
    public Anweisung getAnweisung() {
        return anweisung;
    }

    /**
     * Setzt alle Steuerungssignale auf einmal
     * 
     * @param operation Operation
     * @param anweisung Anweisung
     */
    public void setSteuerung(Operation operation, Anweisung anweisung) {
        this.operation = operation;
        this.anweisung = anweisung;
    }

    /**
     * Sendet ein Signal an den Bus
     * 
     * @param operation Operation
     * @param anweisung Anweisung
     * @param direction Richtung
     */
    public void send(Operation operation, Anweisung anweisung, Direction direction) {
        this.operation = operation;
        this.anweisung = anweisung;
        this.direction = direction;
    }

    /**
     * Sendet ein Signal mit Daten
     * 
     * @param operation Operation
     * @param anweisung Anweisung
     * @param data Daten
     * @param direction Richtung
     */
    public void send(Operation operation, Anweisung anweisung, int data, Direction direction) {
        this.operation = operation;
        this.anweisung = anweisung;
        this.data = data;
        this.direction = direction;
    }

    /**
     * Liest die aktuellen Steuerungssignale
     * 
     * @return Array mit [operation, anweisung]
     */
    public Object[] readSteuerung() {
        return new Object[]{operation, anweisung};
    }

    /**
     * Setzt alle Werte zurück
     */
    public void reset() {
        this.data = 0;
        this.direction = Direction.READ;
        this.operation = Operation.NONE;
        this.anweisung = Anweisung.NONE;
    }

    /**
     * Gibt eine String-Darstellung zurück
     */
    @Override
    public String toString() {
        return "Steuerbus { " +
               "data: " + data +
               ", direction: " + direction +
               ", operation: " + operation +
               ", anweisung: " + anweisung +
               " }";
    }
}
