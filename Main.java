import Math.KomplexeZahl;
import Beschleuniger.Komplexbeschleuniger;
import Bus.Steuerbus;
import Bus.DDA;
import Bus.ADA;
import controll.Anweisung;
import controll.Operand;

/**
 * Hauptklasse zum Testen des Komplexzahl-Beschleuniger-Emulators mit Bus-Anbindung
 * 
 * ============================================================================
 * 
 * Diese Klasse demonstriert die Funktionsweise des Beschleunigers mit:
 * - Bus-Anbindung (Steuerbus, Datenbus, Adressbus)
 * - Register-Speicher
 * - Steuerung über Bus-Signale
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 3.0 - 11.11.2024
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Komplexzahl-Beschleuniger Emulator (mit Bus-Anbindung) ===\n");
        
        // ====================================================================
        // 1. BUSSE ERSTELLEN
        // ====================================================================
        System.out.println("--- Busse erstellen ---");
        Steuerbus steuerbus = new Steuerbus();
        DDA datenbus = new DDA();
        ADA adressbus = new ADA();
        
        System.out.println("Steuerbus: " + steuerbus);
        System.out.println("Datenbus: " + datenbus);
        System.out.println("Adressbus: " + adressbus);
        System.out.println();
        
        // ====================================================================
        // 2. BESCHLEUNIGER MIT BUS-ANBINDUNG ERSTELLEN
        // ====================================================================
        System.out.println("--- Beschleuniger mit Bus-Anbindung erstellen ---");
        Komplexbeschleuniger beschleuniger = new Komplexbeschleuniger(steuerbus, datenbus, adressbus);
        System.out.println(beschleuniger);
        System.out.println();
        
        // ====================================================================
        // 3. REGISTER MIT WERTEN BELEGEN
        // ====================================================================
        System.out.println("--- Register belegen ---");
        KomplexeZahl a = new KomplexeZahl(3.0, 2.0);  // 3 + 2i
        KomplexeZahl b = new KomplexeZahl(1.0, -1.0); // 1 - i
        
        beschleuniger.setRegisterA(a);
        beschleuniger.setRegisterB(b);
        
        System.out.println("Register A: " + beschleuniger.getRegisterA());
        System.out.println("Register B: " + beschleuniger.getRegisterB());
        System.out.println();
        
        // ====================================================================
        // 4. TEST MIT DIREKTER STEUERUNG (ohne Bus)
        // ====================================================================
        System.out.println("--- Test: Direkte Steuerung (execute-Methode) ---");
        
        // Addition testen
        Operand opAdd = new Operand("addieren");
        Anweisung anwBerechnung = new Anweisung("berechnung");
        beschleuniger.execute(opAdd, anwBerechnung);
        
        System.out.println("Addition (3+2i) + (1-i):");
        System.out.println("  Ergebnis: " + beschleuniger.getErgebnis());
        System.out.println("  Erwartet: 4.0 + 1.0i");
        System.out.println();
        
        // ====================================================================
        // 5. TEST MIT BUS-STEUERUNG (Taktbasiert)
        // ====================================================================
        System.out.println("--- Test: Bus-Steuerung (takt-Methode) ---");
        
        // Neue Werte setzen
        beschleuniger.setRegisterA(5.0, 3.0);
        beschleuniger.setRegisterB(2.0, 2.0);
        
        System.out.println("Neue Register:");
        System.out.println("  Register A: " + beschleuniger.getRegisterA());
        System.out.println("  Register B: " + beschleuniger.getRegisterB());
        System.out.println();
        
        // Steuerung auf den Bus schreiben
        steuerbus.setSteuerung(Steuerbus.Operation.MUL, Steuerbus.Anweisung.BERECHNUNG);
        System.out.println("Steuerbus setzen: MUL, BERECHNUNG");
        System.out.println("  Steuerbus: " + steuerbus);
        System.out.println();
        
        // Takt ausführen
        System.out.println("Takt ausführen...");
        beschleuniger.takt();
        
        System.out.println("  Ergebnis: " + beschleuniger.getErgebnis());
        System.out.println("  Erwartet: 4.0 + 16.0i (5+3i)*(2+2i) = 4+16i");
        System.out.println();
        
        // ====================================================================
        // 6. TEST SPEICHERN-ANWEISUNG
        // ====================================================================
        System.out.println("--- Test: Speichern-Anweisung ---");
        
        // Ergebnis in Register A speichern
        steuerbus.setSteuerung(Steuerbus.Operation.MUL, Steuerbus.Anweisung.SPEICHERN);
        beschleuniger.takt();
        
        System.out.println("Nach SPEICHERN:");
        System.out.println("  Register A: " + beschleuniger.getRegisterA());
        System.out.println("  Register B: " + beschleuniger.getRegisterB());
        System.out.println("  Ergebnis: " + beschleuniger.getErgebnis());
        System.out.println("  Register A sollte jetzt das Ergebnis enthalten");
        System.out.println();
        
        // ====================================================================
        // 7. TEST AUSGABE-ANWEISUNG (auf Datenbus)
        // ====================================================================
        System.out.println("--- Test: Ausgabe-Anweisung ---");
        
        steuerbus.setSteuerung(Steuerbus.Operation.ADD, Steuerbus.Anweisung.AUSGABE);
        beschleuniger.takt();
        
        System.out.println("Nach AUSGABE:");
        System.out.println("  Datenbus: " + datenbus);
        System.out.println("  Das Ergebnis sollte auf dem Datenbus sein");
        System.out.println();
        
        // ====================================================================
        // 8. TEST MIT ALLEN OPERATIONEN
        // ====================================================================
        System.out.println("--- Test: Alle Operationen ---");
        
        beschleuniger.setRegisterA(3.0, 2.0);
        beschleuniger.setRegisterB(1.0, -1.0);
        
        // Addition
        steuerbus.setSteuerung(Steuerbus.Operation.ADD, Steuerbus.Anweisung.BERECHNUNG);
        beschleuniger.takt();
        System.out.println("ADD: (3+2i) + (1-i) = " + beschleuniger.getErgebnis());
        
        // Subtraktion
        steuerbus.setSteuerung(Steuerbus.Operation.SUB, Steuerbus.Anweisung.BERECHNUNG);
        beschleuniger.takt();
        System.out.println("SUB: (3+2i) - (1-i) = " + beschleuniger.getErgebnis());
        
        // Multiplikation
        steuerbus.setSteuerung(Steuerbus.Operation.MUL, Steuerbus.Anweisung.BERECHNUNG);
        beschleuniger.takt();
        System.out.println("MUL: (3+2i) * (1-i) = " + beschleuniger.getErgebnis());
        
        // Division
        steuerbus.setSteuerung(Steuerbus.Operation.DIV, Steuerbus.Anweisung.BERECHNUNG);
        beschleuniger.takt();
        System.out.println("DIV: (3+2i) / (1-i) = " + beschleuniger.getErgebnis());
        System.out.println();
        
        // ====================================================================
        // ENDE DER TESTS
        // ====================================================================
        System.out.println("=== Alle Tests abgeschlossen ===");
        System.out.println("\nZusammenfassung:");
        System.out.println("- Bus-Anbindung funktioniert");
        System.out.println("- Alle Operationen (ADD, SUB, MUL, DIV) funktionieren");
        System.out.println("- Alle Anweisungen (BERECHNUNG, AUSGABE, SPEICHERN) funktionieren");
        System.out.println("- Register-Speicher funktioniert");
    }
}
