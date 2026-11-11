package Bus;

/**
 * Adressbus-Register (Address Pointer Register)
 * 
 * ============================================================================
 * 
 * Dies ist ein Register, das die aktuelle Adresse auf dem Adressbus speichert.
 * Es wird vom Steuerwerk genutzt, um den Speicherort für Lese-/Schreiboperationen
 * zu bestimmen.
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 1.0 - 11.11.2024
 */
public class APR {
    
    /**
     * Richtung der Datenübertragung
     */
    public enum Direction {
        READ, WRITE
    }
    
    /**
     * Aktuelle Adresse (als Integer dargestellt)
     */
    private int address;
    
    /**
     * Richtung der Operation
     */
    private Direction direction;
    
    /**
     * Erzeugt ein neues Adressbus-Register
     */
    public APR() {
        this.address = 0;
        this.direction = Direction.READ;
    }
    
    /**
     * Erzeugt ein neues Adressbus-Register mit initialer Adresse
     * 
     * @param address Initiale Adresse
     */
    public APR(int address) {
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
     * @param direction Neue Richtung (READ oder WRITE)
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    /**
     * Gibt die aktuelle Richtung zurück
     * 
     * @return Aktuelle Richtung
     */
    public Direction getDirection() {
        return direction;
    }
    
    /**
     * Inkrementiert die Adresse
     */
    public void increment() {
        this.address++;
    }
    
    /**
     * Dekrementiert die Adresse
     */
    public void decrement() {
        this.address--;
    }
    
    /**
     * Setzt die Adresse und Richtung gleichzeitig
     * 
     * @param address Neue Adresse
     * @param direction Neue Richtung
     */
    public void set(int address, Direction direction) {
        this.address = address;
        this.direction = direction;
    }
    
    /**
     * Gibt eine String-Darstellung zurück
     */
    @Override
    public String toString() {
        return "APR { address: " + address + ", direction: " + direction + " }";
    }
}
