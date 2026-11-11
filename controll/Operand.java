package controll;

/**
 * Klasse für Operanden (addieren, subtrahieren, multiplizieren, dividieren)
 * 
 * ============================================================================
 * 
 * Diese Klasse wurde von der KI erstellt, um die Java-Namenskonventionen
 * (Klassennamen beginnen mit Großbuchstaben) einzuhalten.
 * 
 * Die ursprüngliche Version (operand.java) bleibt für Kompatibilität erhalten.
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 2.0 - 11.11.2024
 */
public class Operand {

   String op;

    public Operand(String op) {
        this.op = op;
    }
    
    public boolean check (String op) {
        if (op.equals("addieren") || op.equals("subtrahieren") || op.equals("multiplizieren") || op.equals("dividieren")) {
            return true;
        } else {
            return false;
        }
    }

    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }

}
