package Beschleuniger;

import Math.KomplexeZahl;
import Bus.Steuerbus;
import Bus.DDA;
import Bus.ADA;
import controll.Anweisung;
import controll.Operand;

/**
 * Komplexzahl-Beschleuniger mit Bus-Anbindung
 * 
 * ============================================================================
 * 
 * Dieser Beschleuniger implementiert die Hardware-Emulation mit:
 * - 2 Registern für komplexe Zahlen (Speicher)
 * - Steuerung über Steuerbus (Operation + Anweisung)
 * - Datenübertragung über Datenbus (DDA)
 * - Adressierung über Adressbus (ADA)
 * 
 * Register:
 * - Register 0: Realteil der ersten komplexen Zahl
 * - Register 1: Imaginärteil der ersten komplexen Zahl
 * - Register 2: Realteil der zweiten komplexen Zahl
 * - Register 3: Imaginärteil der zweiten komplexen Zahl
 * - Register 4: Realteil des Ergebnisses
 * - Register 5: Imaginärteil des Ergebnisses
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 3.0 - 11.11.2024
 */
public class Komplexbeschleuniger {

    // ========================================================================
    // REGISTER-ADRESSEN (Speicheradressen)
    // ========================================================================
    
    /** Adresse für Realteil von Register A */
    public static final int REG_A_REAL = 0;
    
    /** Adresse für Imaginärteil von Register A */
    public static final int REG_A_IMAG = 1;
    
    /** Adresse für Realteil von Register B */
    public static final int REG_B_REAL = 2;
    
    /** Adresse für Imaginärteil von Register B */
    public static final int REG_B_IMAG = 3;
    
    /** Adresse für Realteil des Ergebnisses */
    public static final int REG_ERGEBNIS_REAL = 4;
    
    /** Adresse für Imaginärteil des Ergebnisses */
    public static final int REG_ERGEBNIS_IMAG = 5;
    
    // ========================================================================
    // INTERNE REGISTER (Speicher)
    // ========================================================================
    
    /**
     * Interne Speicherzellen für die Register
     * Index: 0-5 für die 6 Register (2 komplexe Zahlen + Ergebnis)
     */
    private double[] speicher;
    
    // ========================================================================
    // BUS-ANBINDUNG
    // ========================================================================
    
    /** Steuerbus für Steuerungssignale (Operation + Anweisung) */
    private Steuerbus steuerbus;
    
    /** Datenbus für Datenübertragung */
    private DDA datenbus;
    
    /** Adressbus für Adressierung */
    private ADA adressbus;

    // ========================================================================
    // LOKALE STEUERUNG (für direkte Nutzung ohne Bus)
    // ========================================================================
    
    /** Lokale Operation (für Tests ohne Bus) */
    private Operand operation;
    
    /** Lokale Anweisung (für Tests ohne Bus) */
    private Anweisung anweisung;

    // ========================================================================
    // KONSTRUKTOREN
    // ========================================================================

    /**
     * Erzeugt einen neuen Komplexzahl-Beschleuniger mit Bus-Anbindung
     * 
     * @param steuerbus Steuerbus für Steuerungssignale
     * @param datenbus Datenbus für Datenübertragung
     * @param adressbus Adressbus für Adressierung
     */
    public Komplexbeschleuniger(Steuerbus steuerbus, DDA datenbus, ADA adressbus) {
        // Speicher initialisieren (6 Register: A_real, A_imag, B_real, B_imag, Ergebnis_real, Ergebnis_imag)
        this.speicher = new double[6];
        for (int i = 0; i < speicher.length; i++) {
            speicher[i] = 0.0;
        }
        
        // Busse verbinden
        this.steuerbus = steuerbus;
        this.datenbus = datenbus;
        this.adressbus = adressbus;
        
        // Lokale Steuerung initialisieren
        this.operation = new Operand("addieren");
        this.anweisung = new Anweisung("berechnung");
    }

    /**
     * Erzeugt einen neuen Komplexzahl-Beschleuniger mit initialen Werten
     * (ohne Bus-Anbindung für einfache Tests)
     * 
     * @param a Erste komplexe Zahl (für Register A)
     * @param b Zweite komplexe Zahl (für Register B)
     */
    public Komplexbeschleuniger(KomplexeZahl a, KomplexeZahl b) {
        // Speicher initialisieren
        this.speicher = new double[6];
        
        // Register A setzen
        speicher[REG_A_REAL] = a.getRealteil();
        speicher[REG_A_IMAG] = a.getImaginaerteil();
        
        // Register B setzen
        speicher[REG_B_REAL] = b.getRealteil();
        speicher[REG_B_IMAG] = b.getImaginaerteil();
        
        // Ergebnis initialisieren
        speicher[REG_ERGEBNIS_REAL] = 0.0;
        speicher[REG_ERGEBNIS_IMAG] = 0.0;
        
        // Busse auf null (können später verbunden werden)
        this.steuerbus = null;
        this.datenbus = null;
        this.adressbus = null;
        
        // Lokale Steuerung
        this.operation = new Operand("addieren");
        this.anweisung = new Anweisung("berechnung");
    }

    // ========================================================================
    // BUS-VERBINDUNG
    // ========================================================================

    /**
     * Verbindet den Steuerbus
     * 
     * @param steuerbus Steuerbus-Instanz
     */
    public void connectSteuerbus(Steuerbus steuerbus) {
        this.steuerbus = steuerbus;
    }

    /**
     * Verbindet den Datenbus
     * 
     * @param datenbus Datenbus-Instanz
     */
    public void connectDatenbus(DDA datenbus) {
        this.datenbus = datenbus;
    }

    /**
     * Verbindet den Adressbus
     * 
     * @param adressbus Adressbus-Instanz
     */
    public void connectAdressbus(ADA adressbus) {
        this.adressbus = adressbus;
    }

    // ========================================================================
    // HAUPTMETHODEN
    // ========================================================================

    /**
     * Führt einen Taktzyklus aus (wie Hardware-Beschleuniger)
     * 
     * Diese Methode liest die Steuerungssignale vom Steuerbus und führt
     * die entsprechende Operation aus. Sie entspricht einem Taktsignal
     * in der echten Hardware.
     */
    public void takt() {
        // Wenn Steuerbus verbunden ist, von dort lesen
        if (steuerbus != null) {
            // Steuerungssignale vom Bus lesen
            Steuerbus.Operation op = steuerbus.getOperation();
            Steuerbus.Anweisung anw = steuerbus.getAnweisung();
            
            // In lokale Operand/Anweisung umwandeln und ausführen
            String opString = convertOperation(op);
            String anwString = convertAnweisung(anw);
            
            if (opString != null && anwString != null) {
                this.operation = new Operand(opString);
                this.anweisung = new Anweisung(anwString);
                execute(this.operation, this.anweisung);
            }
        } else {
            // Falls kein Bus verbunden ist, lokale Steuerung verwenden
            execute(this.operation, this.anweisung);
        }
    }

    /**
     * Führt die ausgewählte Operation und Anweisung aus
     * 
     * @param operation Die durchzuführende Operation
     * @param anweisung Die Anweisung (berechnung, ausgabe, speichern)
     */
    public void execute(Operand operation, Anweisung anweisung) {
        this.operation = operation;
        this.anweisung = anweisung;
        run();
    }

    /**
     * Interne Methode zur Ausführung der Operation
     */
    private void run() {
        String befehl = anweisung.getAnweisung();
        String op = operation.getOp();

        switch (befehl) {
            case "berechnung":
                berechne(op);
                break;
            case "ausgabe":
                // Ausgabe auf den Datenbus
                if (datenbus != null) {
                    KomplexeZahl ergebnis = getErgebnis();
                    datenbus.write(ergebnis);
                    if (adressbus != null) {
                        adressbus.write(REG_ERGEBNIS_REAL);
                    }
                }
                break;
            case "speichern":
                // Speichern des Ergebnisses in Register A
                speicher[REG_A_REAL] = speicher[REG_ERGEBNIS_REAL];
                speicher[REG_A_IMAG] = speicher[REG_ERGEBNIS_IMAG];
                break;
            default:
                throw new IllegalArgumentException("Unbekannte Anweisung: " + befehl);
        }
    }

    /**
     * Führt die mathematische Operation aus
     * 
     * @param op Die Operation als String
     */
    private void berechne(String op) {
        double aReal = speicher[REG_A_REAL];
        double aImag = speicher[REG_A_IMAG];
        double bReal = speicher[REG_B_REAL];
        double bImag = speicher[REG_B_IMAG];

        switch (op) {
            case "addieren":
                speicher[REG_ERGEBNIS_REAL] = aReal + bReal;
                speicher[REG_ERGEBNIS_IMAG] = aImag + bImag;
                break;
            case "subtrahieren":
                speicher[REG_ERGEBNIS_REAL] = aReal - bReal;
                speicher[REG_ERGEBNIS_IMAG] = aImag - bImag;
                break;
            case "multiplizieren":
                // (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
                speicher[REG_ERGEBNIS_REAL] = aReal * bReal - aImag * bImag;
                speicher[REG_ERGEBNIS_IMAG] = aReal * bImag + aImag * bReal;
                break;
            case "dividieren":
                // (a + bi) / (c + di) = [(ac + bd) + (bc - ad)i] / (c² + d²)
                double denominator = bReal * bReal + bImag * bImag;
                if (denominator == 0) {
                    throw new ArithmeticException("Division durch Null");
                }
                speicher[REG_ERGEBNIS_REAL] = (aReal * bReal + aImag * bImag) / denominator;
                speicher[REG_ERGEBNIS_IMAG] = (aImag * bReal - aReal * bImag) / denominator;
                break;
            default:
                throw new IllegalArgumentException("Unbekannte Operation: " + op);
        }
    }

    // ========================================================================
    // BUS-OPERATIONEN
    // ========================================================================

    /**
     * Liest Daten vom Datenbus und speichert sie im angegebenen Register
     * 
     * @param registerAdresse Adresse des Zielregisters
     */
    public void readFromBus(int registerAdresse) {
        if (datenbus != null) {
            KomplexeZahl data = datenbus.read();
            if (registerAdresse >= 0 && registerAdresse < speicher.length) {
                if (registerAdresse % 2 == 0) {
                    // Realteil
                    speicher[registerAdresse] = data.getRealteil();
                } else {
                    // Imaginärteil
                    speicher[registerAdresse] = data.getImaginaerteil();
                }
            }
        }
    }

    /**
     * Schreibt Daten vom angegebenen Register auf den Datenbus
     * 
     * @param registerAdresse Adresse des Quellenregisters
     */
    public void writeToBus(int registerAdresse) {
        if (datenbus != null && registerAdresse >= 0 && registerAdresse < speicher.length) {
            double value = speicher[registerAdresse];
            // Für den Bus benötigen wir eine komplexe Zahl
            // Wenn Realteil: Imaginärteil = 0
            // Wenn Imaginärteil: Realteil = 0
            if (registerAdresse % 2 == 0) {
                datenbus.write(value, 0);
            } else {
                datenbus.write(0, value);
            }
        }
    }

    /**
     * Liest die Steuerung vom Steuerbus
     */
    public void readSteuerungFromBus() {
        if (steuerbus != null) {
            Steuerbus.Operation op = steuerbus.getOperation();
            Steuerbus.Anweisung anw = steuerbus.getAnweisung();
            
            String opString = convertOperation(op);
            String anwString = convertAnweisung(anw);
            
            if (opString != null) {
                this.operation = new Operand(opString);
            }
            if (anwString != null) {
                this.anweisung = new Anweisung(anwString);
            }
        }
    }

    // ========================================================================
    // HILFSMETHODEN FÜR KONVERTIERUNG
    // ========================================================================

    /**
     * Konvertiert Steuerbus.Operation in String für Operand-Klasse
     * 
     * @param op Steuerbus-Operation
     * @return String für Operand-Klasse
     */
    private String convertOperation(Steuerbus.Operation op) {
        if (op == null) return null;
        switch (op) {
            case ADD: return "addieren";
            case SUB: return "subtrahieren";
            case MUL: return "multiplizieren";
            case DIV: return "dividieren";
            default: return null;
        }
    }

    /**
     * Konvertiert Steuerbus.Anweisung in String für Anweisung-Klasse
     * 
     * @param anw Steuerbus-Anweisung
     * @return String für Anweisung-Klasse
     */
    private String convertAnweisung(Steuerbus.Anweisung anw) {
        if (anw == null) return null;
        switch (anw) {
            case BERECHNUNG: return "berechnung";
            case AUSGABE: return "ausgabe";
            case SPEICHERN: return "speichern";
            default: return null;
        }
    }

    // ========================================================================
    // GETTER UND SETTER FÜR REGISTER
    // ========================================================================

    /**
     * Gibt Register A als komplexe Zahl zurück
     * 
     * @return Komplexe Zahl aus Register A
     */
    public KomplexeZahl getRegisterA() {
        return new KomplexeZahl(speicher[REG_A_REAL], speicher[REG_A_IMAG]);
    }

    /**
     * Setzt Register A
     * 
     * @param a Komplexe Zahl für Register A
     */
    public void setRegisterA(KomplexeZahl a) {
        speicher[REG_A_REAL] = a.getRealteil();
        speicher[REG_A_IMAG] = a.getImaginaerteil();
    }

    /**
     * Setzt Register A mit Real- und Imaginärteil
     * 
     * @param realteil Realteil
     * @param imaginaerteil Imaginärteil
     */
    public void setRegisterA(double realteil, double imaginaerteil) {
        speicher[REG_A_REAL] = realteil;
        speicher[REG_A_IMAG] = imaginaerteil;
    }

    /**
     * Gibt Register B als komplexe Zahl zurück
     * 
     * @return Komplexe Zahl aus Register B
     */
    public KomplexeZahl getRegisterB() {
        return new KomplexeZahl(speicher[REG_B_REAL], speicher[REG_B_IMAG]);
    }

    /**
     * Setzt Register B
     * 
     * @param b Komplexe Zahl für Register B
     */
    public void setRegisterB(KomplexeZahl b) {
        speicher[REG_B_REAL] = b.getRealteil();
        speicher[REG_B_IMAG] = b.getImaginaerteil();
    }

    /**
     * Setzt Register B mit Real- und Imaginärteil
     * 
     * @param realteil Realteil
     * @param imaginaerteil Imaginärteil
     */
    public void setRegisterB(double realteil, double imaginaerteil) {
        speicher[REG_B_REAL] = realteil;
        speicher[REG_B_IMAG] = imaginaerteil;
    }

    /**
     * Gibt das Ergebnis als komplexe Zahl zurück
     * 
     * @return Ergebnis der letzten Berechnung
     */
    public KomplexeZahl getErgebnis() {
        return new KomplexeZahl(speicher[REG_ERGEBNIS_REAL], speicher[REG_ERGEBNIS_IMAG]);
    }

    /**
     * Setzt das Ergebnis
     * 
     * @param ergebnis Neue komplexe Zahl als Ergebnis
     */
    public void setErgebnis(KomplexeZahl ergebnis) {
        speicher[REG_ERGEBNIS_REAL] = ergebnis.getRealteil();
        speicher[REG_ERGEBNIS_IMAG] = ergebnis.getImaginaerteil();
    }

    /**
     * Gibt den Wert eines Registers zurück
     * 
     * @param registerAdresse Adresse des Registers
     * @return Wert des Registers
     */
    public double getRegisterWert(int registerAdresse) {
        if (registerAdresse >= 0 && registerAdresse < speicher.length) {
            return speicher[registerAdresse];
        }
        return 0.0;
    }

    /**
     * Setzt den Wert eines Registers
     * 
     * @param registerAdresse Adresse des Registers
     * @param wert Neuer Wert
     */
    public void setRegisterWert(int registerAdresse, double wert) {
        if (registerAdresse >= 0 && registerAdresse < speicher.length) {
            speicher[registerAdresse] = wert;
        }
    }

    // ========================================================================
    // GETTER UND SETTER FÜR BUSSE
    // ========================================================================

    /**
     * Gibt den Steuerbus zurück
     * 
     * @return Steuerbus-Instanz
     */
    public Steuerbus getSteuerbus() {
        return steuerbus;
    }

    /**
     * Gibt den Datenbus zurück
     * 
     * @return Datenbus-Instanz
     */
    public DDA getDatenbus() {
        return datenbus;
    }

    /**
     * Gibt den Adressbus zurück
     * 
     * @return Adressbus-Instanz
     */
    public ADA getAdressbus() {
        return adressbus;
    }

    // ========================================================================
    // GETTER UND SETTER FÜR LOKALE STEUERUNG
    // ========================================================================

    /**
     * Gibt die aktuelle Operation zurück
     * 
     * @return Aktuelle Operation
     */
    public Operand getOperation() {
        return operation;
    }

    /**
     * Setzt die aktuelle Operation
     * 
     * @param operation Neue Operation
     */
    public void setOperation(Operand operation) {
        this.operation = operation;
    }

    /**
     * Gibt die aktuelle Anweisung zurück
     * 
     * @return Aktuelle Anweisung
     */
    public Anweisung getAnweisung() {
        return anweisung;
    }

    /**
     * Setzt die aktuelle Anweisung
     * 
     * @param anweisung Neue Anweisung
     */
    public void setAnweisung(Anweisung anweisung) {
        this.anweisung = anweisung;
    }

    // ========================================================================
    // HILFSMETHODEN
    // ========================================================================

    /**
     * Setzt alle Register zurück
     */
    public void reset() {
        for (int i = 0; i < speicher.length; i++) {
            speicher[i] = 0.0;
        }
    }

    /**
     * Gibt den aktuellen Zustand als String zurück
     */
    @Override
    public String toString() {
        return "Komplexbeschleuniger {" +
                "\n  Register A: " + getRegisterA() +
                "\n  Register B: " + getRegisterB() +
                "\n  Ergebnis: " + getErgebnis() +
                "\n  Operation: " + (operation != null ? operation.getOp() : "keine") +
                "\n  Anweisung: " + (anweisung != null ? anweisung.getAnweisung() : "keine") +
                "\n  Steuerbus: " + (steuerbus != null ? "verbunden" : "nicht verbunden") +
                "\n  Datenbus: " + (datenbus != null ? "verbunden" : "nicht verbunden") +
                "\n  Adressbus: " + (adressbus != null ? "verbunden" : "nicht verbunden") +
                "\n}";
    }
}
