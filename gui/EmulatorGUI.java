package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Math.KomplexeZahl;
import Beschleuniger.Komplexbeschleuniger;
import Bus.Steuerbus;
import Bus.DDA;
import Bus.ADA;
import controll.Anweisung;
import controll.Operand;

/**
 * GUI für den Komplexzahl-Beschleuniger mit Bus-Anbindung
 * 
 * ============================================================================
 * 
 * Diese GUI nutzt den Komplexbeschleuniger mit Bus-System.
 * Sie ermöglicht:
 * - Manuelle Eingabe der Registerwerte
 * - Auswahl der Operation und Anweisung
 * - Ausführung der Berechnung
 * - Anzeige des Bus-Zustands
 * 
 * ============================================================================
 * 
 * @author Alex (Bachelorarbeit) + KI-Unterstützung
 * @version 2.0 - 11.11.2024
 */
public class EmulatorGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    // ========================================================================
    // KOMPONENTEN FÜR DIE EINGABE DER REGISTER
    // ========================================================================
    
    private JTextField realteilAField;
    private JTextField imaginaerteilAField;
    private JTextField realteilBField;
    private JTextField imaginaerteilBField;
    
    // ========================================================================
    // KOMPONENTEN FÜR OPERATION UND ANWEISUNG
    // ========================================================================
    
    private JComboBox<String> operationComboBox;
    private JComboBox<String> anweisungComboBox;
    
    // ========================================================================
    // BUTTONS
    // ========================================================================
    
    private JButton executeButton;
    private JButton resetButton;
    private JButton updateButton;
    private JButton taktButton;
    
    // ========================================================================
    // ANZEIGE-KOMPONENTEN
    // ========================================================================
    
    private JLabel ergebnisLabel;
    private JLabel registerALabel;
    private JLabel registerBLabel;
    
    // BUS-ANZEIGE
    private JLabel steuerbusLabel;
    private JLabel datenbusLabel;
    private JLabel adressbusLabel;
    
    // ========================================================================
    // KONSOLEN-AUSGABE
    // ========================================================================
    
    private JTextArea consoleArea;
    private JScrollPane consoleScroll;
    
    // ========================================================================
    // BESCHLEUNIGER UND BUS-INSTANZEN
    // ========================================================================
    
    private Komplexbeschleuniger beschleuniger;
    private Steuerbus steuerbus;
    private DDA datenbus;
    private ADA adressbus;

    // ========================================================================
    // KONSTRUKTOR
    // ========================================================================

    /**
     * Erzeugt eine neue GUI für den Emulator
     */
    public EmulatorGUI() {
        initializeComponents();
        setupLayout();
        initializeBeschleuniger();
        setupEventHandlers();
        
        setTitle("Komplexzahl-Beschleuniger Emulator (mit Bus-Anbindung)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    // ========================================================================
    // INITIALISIERUNG DER KOMPONENTEN
    // ========================================================================

    private void initializeComponents() {
        // Register Eingabefelder
        realteilAField = new JTextField("3.0", 10);
        imaginaerteilAField = new JTextField("2.0", 10);
        realteilBField = new JTextField("1.0", 10);
        imaginaerteilBField = new JTextField("-1.0", 10);
        
        // Operationen und Anweisungen
        String[] operationen = {"addieren", "subtrahieren", "multiplizieren", "dividieren"};
        operationComboBox = new JComboBox<>(operationen);
        
        String[] anweisungen = {"berechnung", "ausgabe", "speichern"};
        anweisungComboBox = new JComboBox<>(anweisungen);
        
        // Ergebnis-Anzeige
        ergebnisLabel = new JLabel("Ergebnis: ", SwingConstants.LEFT);
        ergebnisLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ergebnisLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        registerALabel = new JLabel("Register A: ", SwingConstants.LEFT);
        registerALabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        registerBLabel = new JLabel("Register B: ", SwingConstants.LEFT);
        registerBLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Bus-Anzeige
        steuerbusLabel = new JLabel("Steuerbus: ", SwingConstants.LEFT);
        steuerbusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        datenbusLabel = new JLabel("Datenbus: ", SwingConstants.LEFT);
        datenbusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        adressbusLabel = new JLabel("Adressbus: ", SwingConstants.LEFT);
        adressbusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Konsolenbereich
        consoleArea = new JTextArea(10, 40);
        consoleArea.setEditable(false);
        consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        consoleScroll = new JScrollPane(consoleArea);
        
        // Buttons
        executeButton = new JButton("Ausführen");
        resetButton = new JButton("Zurücksetzen");
        updateButton = new JButton("Register aktualisieren");
        taktButton = new JButton("Takt ausführen");
        
        // Button-Stile
        executeButton.setBackground(new Color(0, 150, 0));
        executeButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(150, 0, 0));
        resetButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(0, 100, 200));
        updateButton.setForeground(Color.WHITE);
        taktButton.setBackground(new Color(100, 50, 150));
        taktButton.setForeground(Color.WHITE);
    }
    
    // ========================================================================
    // LAYOUT-EINRICHTUNG
    // ========================================================================

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Eingabepanel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.add(new JLabel("Register A - Realteil:", SwingConstants.RIGHT));
        inputPanel.add(realteilAField);
        inputPanel.add(new JLabel("Register A - Imaginärteil:", SwingConstants.RIGHT));
        inputPanel.add(imaginaerteilAField);
        inputPanel.add(new JLabel("Register B - Realteil:", SwingConstants.RIGHT));
        inputPanel.add(realteilBField);
        inputPanel.add(new JLabel("Register B - Imaginärteil:", SwingConstants.RIGHT));
        inputPanel.add(imaginaerteilBField);
        
        // Steuerpanel
        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        controlPanel.add(new JLabel("Operation:", SwingConstants.RIGHT));
        controlPanel.add(operationComboBox);
        controlPanel.add(new JLabel("Anweisung:", SwingConstants.RIGHT));
        controlPanel.add(anweisungComboBox);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(updateButton);
        buttonPanel.add(executeButton);
        buttonPanel.add(taktButton);
        buttonPanel.add(resetButton);
        
        // Ergebnis Panel
        JPanel resultPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Register & Ergebnis"));
        resultPanel.add(registerALabel);
        resultPanel.add(registerBLabel);
        resultPanel.add(ergebnisLabel);
        
        // Bus Panel
        JPanel busPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        busPanel.setBorder(BorderFactory.createTitledBorder("Bus-Zustand"));
        busPanel.add(steuerbusLabel);
        busPanel.add(datenbusLabel);
        busPanel.add(adressbusLabel);
        
        // Konsolen Panel
        JPanel consolePanel = new JPanel(new BorderLayout());
        consolePanel.setBorder(BorderFactory.createTitledBorder("Konsole"));
        consolePanel.add(consoleScroll, BorderLayout.CENTER);
        
        // Rechtes Panel (Ergebnis + Bus + Konsole)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(resultPanel, BorderLayout.NORTH);
        rightPanel.add(busPanel, BorderLayout.CENTER);
        rightPanel.add(consolePanel, BorderLayout.SOUTH);
        
        // Hauptlayout
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        
        // Größen festlegen
        mainPanel.setPreferredSize(new Dimension(350, 250));
        rightPanel.setPreferredSize(new Dimension(300, 500));
    }
    
    // ========================================================================
    // BESCHLEUNIGER-INITIALISIERUNG
    // ========================================================================

    private void initializeBeschleuniger() {
        // Busse erstellen
        steuerbus = new Steuerbus();
        datenbus = new DDA();
        adressbus = new ADA();
        
        // Beschleuniger mit Bus-Anbindung erstellen
        beschleuniger = new Komplexbeschleuniger(steuerbus, datenbus, adressbus);
        
        // Anfangswerte aus den Feldern lesen und setzen
        double aReal = parseDouble(realteilAField.getText(), 0.0);
        double aImag = parseDouble(imaginaerteilAField.getText(), 0.0);
        double bReal = parseDouble(realteilBField.getText(), 0.0);
        double bImag = parseDouble(imaginaerteilBField.getText(), 0.0);
        
        beschleuniger.setRegisterA(aReal, aImag);
        beschleuniger.setRegisterB(bReal, bImag);
        
        // Anfangswerte anzeigen
        updateDisplay();
        logMessage("Emulator initialisiert mit Bus-Anbindung.");
        logMessage("Register A: " + beschleuniger.getRegisterA());
        logMessage("Register B: " + beschleuniger.getRegisterB());
    }
    
    // ========================================================================
    // EVENT-HANDLER
    // ========================================================================

    private void setupEventHandlers() {
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeOperation();
            }
        });
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAll();
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRegister();
            }
        });
        
        taktButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeTakt();
            }
        });
    }
    
    // ========================================================================
    // AKTIONEN
    // ========================================================================

    private void executeOperation() {
        try {
            updateRegister();
            
            String op = (String) operationComboBox.getSelectedItem();
            String anw = (String) anweisungComboBox.getSelectedItem();
            
            Operand operation = new Operand(op);
            Anweisung anweisungObj = new Anweisung(anw);
            
            // Führe die Operation aus
            beschleuniger.execute(operation, anweisungObj);
            
            // Aktualisiere die Bus-Anzeige
            updateBusDisplay();
            
            updateDisplay();
            logMessage("Ausgeführt: " + op + " (" + anw + ") -> Ergebnis: " + beschleuniger.getErgebnis());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Fehler: " + e.getMessage(), 
                "Fehler", 
                JOptionPane.ERROR_MESSAGE);
            logMessage("Fehler: " + e.getMessage());
        }
    }
    
    private void executeTakt() {
        try {
            // Setze die Steuerung auf den Bus
            String op = (String) operationComboBox.getSelectedItem();
            String anw = (String) anweisungComboBox.getSelectedItem();
            
            // Konvertiere in Steuerbus-Formate
            Steuerbus.Operation busOp = convertToBusOperation(op);
            Steuerbus.Anweisung busAnw = convertToBusAnweisung(anw);
            
            if (busOp != null && busAnw != null) {
                steuerbus.send(busOp, busAnw, Steuerbus.Direction.WRITE);
                
                // Führe einen Taktzyklus aus
                beschleuniger.takt();
                
                updateBusDisplay();
                updateDisplay();
                logMessage("Takt ausgeführt: " + op + " (" + anw + ")");
                logMessage("Ergebnis: " + beschleuniger.getErgebnis());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Fehler beim Takt: " + e.getMessage(), 
                "Fehler", 
                JOptionPane.ERROR_MESSAGE);
            logMessage("Fehler: " + e.getMessage());
        }
    }
    
    private void updateRegister() {
        try {
            double aReal = parseDouble(realteilAField.getText(), 0.0);
            double aImag = parseDouble(imaginaerteilAField.getText(), 0.0);
            double bReal = parseDouble(realteilBField.getText(), 0.0);
            double bImag = parseDouble(imaginaerteilBField.getText(), 0.0);
            
            beschleuniger.setRegisterA(aReal, aImag);
            beschleuniger.setRegisterB(bReal, bImag);
            
            updateDisplay();
            logMessage("Register aktualisiert: A=" + beschleuniger.getRegisterA() + 
                      ", B=" + beschleuniger.getRegisterB());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Bitte gültige Zahlen eingeben!", 
                "Eingabefehler", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetAll() {
        realteilAField.setText("3.0");
        imaginaerteilAField.setText("2.0");
        realteilBField.setText("1.0");
        imaginaerteilBField.setText("-1.0");
        operationComboBox.setSelectedIndex(0);
        anweisungComboBox.setSelectedIndex(0);
        
        beschleuniger.reset();
        steuerbus.reset();
        datenbus.write(0, 0);
        adressbus.write(0);
        
        updateDisplay();
        updateBusDisplay();
        logMessage("Alle Werte zurückgesetzt.");
    }
    
    // ========================================================================
    // HILFSMETHODEN
    // ========================================================================

    private void updateDisplay() {
        registerALabel.setText("Register A: " + beschleuniger.getRegisterA());
        registerBLabel.setText("Register B: " + beschleuniger.getRegisterB());
        ergebnisLabel.setText("Ergebnis: " + beschleuniger.getErgebnis());
    }
    
    private void updateBusDisplay() {
        steuerbusLabel.setText("Steuerbus: " + steuerbus);
        datenbusLabel.setText("Datenbus: " + datenbus);
        adressbusLabel.setText("Adressbus: " + adressbus);
    }
    
    private void logMessage(String message) {
        consoleArea.append(message + "\n");
        consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
    }
    
    private double parseDouble(String text, double defaultValue) {
        try {
            text = text.replace(',', '.');
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    private Steuerbus.Operation convertToBusOperation(String op) {
        if (op == null) return null;
        switch (op) {
            case "addieren": return Steuerbus.Operation.ADD;
            case "subtrahieren": return Steuerbus.Operation.SUB;
            case "multiplizieren": return Steuerbus.Operation.MUL;
            case "dividieren": return Steuerbus.Operation.DIV;
            default: return null;
        }
    }
    
    private Steuerbus.Anweisung convertToBusAnweisung(String anw) {
        if (anw == null) return null;
        switch (anw) {
            case "berechnung": return Steuerbus.Anweisung.BERECHNUNG;
            case "ausgabe": return Steuerbus.Anweisung.AUSGABE;
            case "speichern": return Steuerbus.Anweisung.SPEICHERN;
            default: return null;
        }
    }
    
    // ========================================================================
    // HAUPTMETHODE
    // ========================================================================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                new EmulatorGUI().setVisible(true);
            }
        });
    }
}
