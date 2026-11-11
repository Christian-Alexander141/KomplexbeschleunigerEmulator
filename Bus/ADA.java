package Bus;

/**
 * Adressbus (Address Data Address)
 * 
 * ============================================================================
 * 
 * Der Adressbus überträgt Adressen zwischen Komponenten.
 * Er wird genutzt, um Speicheradressen oder Registeradressen zu kommunizieren.
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 1.0 - 11.11.2024
 */
public class ADA {
    
    /**
     * Richtung der Datenübertragung
     */
    public enum Direction {
        READ, WRITE
    }
    
    /**
     * Aktuelle Adresse auf dem Bus
     */
    private int address;
    
    /**
     * Richtung der Operation
     */
    private Direction direction;
    
    /**
     * Erzeugt einen neuen Adressbus
     */
    public ADA() {
        this.address = 0;
        this.direction = Direction.READ;
    }
    
    /**
     * Erzeugt einen neuen Adressbus mit initialer Adresse
     * 
     * @param address Initiale Adresse
     */
    public ADA(int address) {
        this.address = address;
        this.direction = Direction.READ;
    }
    
    /**
     * Setzt die Adresse
     * 
     * @param address Neue Adresse
     */
    public void setAddress(int address) {
        this.address = address;
    }
    
    /**
     * Gibt die aktuelle Adresse zurück
     * 
     * @return Aktuelle Adresse
     */
    public int getAddress() {
        return address;
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
     * Liest die Adresse vom Bus
     * 
     * @return Die gelesene Adresse
     */
    public int read() {
        return this.address;
    }
    
    /**
     * Schreibt eine Adresse auf den Bus
     * 
     * @param address Adresse zum Schreiben
     */
    public void write(int address) {
        this.address = address;
        this.direction = Direction.WRITE;
    }
    
    /**
     * Schreibt Adresse und Richtung auf den Bus
     * 
     * @param address Adresse
     * @param direction Richtung
     */
    public void write(int address, Direction direction) {
        this.address = address;
        this.direction = direction;
    }
    
    /**
     * Gibt eine String-Darstellung zurück
     */
    @Override
    public String toString() {
        return "ADA { address: " + address + ", direction: " + direction + " }";
    }
}
