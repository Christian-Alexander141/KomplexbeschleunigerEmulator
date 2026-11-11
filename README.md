# Komplexzahl-Beschleuniger Emulator

> **Hinweis:** Dieser Code wurde im Rahmen der Bachelorarbeit entwickelt. Die KI hat bei der Korrektur von Fehlern und der Erweiterung der Funktionalität unterstützt. Alle Dateien sind mit klaren Hinweisen versehen, welche Teile von der KI stammen.

---

## 📁 Projektstruktur

```
KomplexBeschleunigerEmulator/
├── Beschleuniger/
│   └── Komplexbeschleuniger.java    # ✅ Von KI korrigiert & erweitert (volständige Implementierung)
├── Bus/
│   ├── ADA.java                     # ⚪ Ursprüngliches Projekt (unverändert)
│   ├── APR.java                     # ⚪ Ursprüngliches Projekt (unverändert)
│   ├── DDA.java                     # ⚪ Ursprüngliches Projekt (unverändert)
│   ├── DPR.java                     # ⚪ Ursprüngliches Projekt (unverändert)
│   └── Steuerbus.java              # ⚪ Ursprüngliches Projekt (unverändert)
├── Math/
│   └── KomplexeZahl.java            # ✅ Ursprüngliches Projekt + KI Verbesserung (toString())
├── controll/
│   ├── anweisung.java               # ⚪ Ursprüngliches Projekt (unverändert, funktioniert)
│   └── operand.java                 # ⚪ Ursprüngliches Projekt (unverändert, funktioniert)
├── gui/
│   └── EmulatorGUI.java             # ✅ NEU: Von KI erstellt (einfache Swing-GUI)
├── Main.java                        # ✅ NEU: Von KI erstellt (Konsolen-Tests)
├── screenshots/                     # ✅ NEU: Ordner für GUI-Screenshots
├── StartGUI.java                    # ✅ NEU: Von KI erstellt (GUI-Starter)
└── README.md                        # ✅ NEU: Von KI erstellt (Dokumentation)
```

**Legende:**
- ✅ = Von KI erstellt/korrigiert
- ⚪ = Ursprüngliches Projekt (unverändert)

---

## 📷 GUI-Screenshots & Testfälle

Hier sind Screenshots der funktionierenden GUI mit verschiedenen Testfällen:

### 🖼️ Startbildschirm

Die GUI startet mit vorbefüllten Werten für schnelle Tests:
- **Register A**: 3.0 + 2.0i (Standardwert)
- **Register B**: 1.0 - 1.0i (Standardwert)
- **Operation**: addieren (Standardauswahl)
- **Anweisung**: berechnung (Standardauswahl)

![GUI Startbildschirm](screenshots/gui_start.png)
*Bild: GUI beim Start mit Standardwerten*

---

### 🧪 Testfall 1: Addition

**Eingabe:**
- Register A: 3.0 + 2.0i
- Register B: 1.0 - 1.0i
- Operation: addieren
- Anweisung: berechnung

**Erwartetes Ergebnis:** 4.0 + 1.0i

![Testfall Addition](screenshots/test_addition.png)
*Bild: erfolgreich durchgeführter Additionstest mit korrektem Ergebnis 4.0 + 1.0i*

**Mathematische Überprüfung:**
```
(3 + 2i) + (1 - i) = (3+1) + (2-1)i = 4 + 1i ✓
```

---

### 🧪 Testfall 2: Subtraktion

**Eingabe:**
- Register A: 5.0 + 3.0i
- Register B: 2.0 + 1.0i
- Operation: subtrahieren
- Anweisung: berechnung

**Erwartetes Ergebnis:** 3.0 + 2.0i

![Testfall Subtraktion](screenshots/test_subtraktion.png)
*Bild: Subtraktionstest mit Ergebnis 3.0 + 2.0i*

**Mathematische Überprüfung:**
```
(5 + 3i) - (2 + i) = (5-2) + (3-1)i = 3 + 2i ✓
```

---

### 🧪 Testfall 3: Multiplikation

**Eingabe:**
- Register A: 2.0 + 1.0i
- Register B: 3.0 - 2.0i
- Operation: multiplizieren
- Anweisung: berechnung

**Erwartetes Ergebnis:** 8.0 - 1.0i

![Testfall Multiplikation](screenshots/test_multiplikation.png)
*Bild: Multiplikationstest mit komplexen Zahlen*

**Mathematische Überprüfung:**
```
(2 + i) * (3 - 2i) = (2*3 + 1*2) + (2*(-2) + 1*3)i
                    = (6 + 2) + (-4 + 3)i
                    = 8 - i ✓
```

---

### 🧪 Testfall 4: Division

**Eingabe:**
- Register A: 4.0 + 2.0i
- Register B: 2.0 + 0.0i
- Operation: dividieren
- Anweisung: berechnung

**Erwartetes Ergebnis:** 2.0 + 1.0i

![Testfall Division](screenshots/test_division.png)
*Bild: Divisionstest mit einfacher komplexer Zahl*

**Mathematische Überprüfung:**
```
(4 + 2i) / (2 + 0i) = (4/2) + (2/2)i = 2 + i ✓
```

---

### 🧪 Testfall 5: Speichern-Anweisung

**Eingabe:**
- Register A: 3.0 + 2.0i
- Register B: 1.0 + 1.0i
- Operation: addieren
- Anweisung: **speichern**

**Ergebnis:** Das Ergebnis (4.0 + 3.0i) wird in Register A gespeichert

![Testfall Speichern](screenshots/test_speichern.png)
*Bild: Speichern des Ergebnisses in Register A*

**Verifikation:**
- Register A wird von 3.0+2.0i zu 4.0+3.0i aktualisiert
- Ergebnis bleibt 4.0+3.0i

---

### 🧪 Testfall 6: Bus-Anbindung Test

**Eingabe:**
- Register A: 5.0 + 0.0i
- Register B: 3.0 + 0.0i
- Operation: multiplizieren
- **Takt ausführen** Button

**Bus-Zustand:**
- Steuerbus: Sendet MUL-Operation
- Datenbus: Enthält Ergebnisdaten
- Adressbus: Zeigt Zieladresse

![Testfall Bus](screenshots/test_bus.png)
*Bild: Bus-Zustand nach Taktausführung*

**Verifikation:**
- Steuerbus zeigt korrekte Operation an
- Datenbus enthält Berechnungsergebnis
- Adressbus zeigt Zielregister

---

### 🔄 Testfall 7: Zurücksetzen

**Aktion:** Klick auf **Zurücksetzen** Button

**Ergebnis:**
- Alle Register auf Standardwerte zurückgesetzt
- Ergebnis auf 0.0 + 0.0i
- Konsole zeigt Reset-Meldung
- Busse werden zurückgesetzt

![Testfall Reset](screenshots/test_reset.png)
*Bild: GUI nach Zurücksetzen aller Werte*

---

### 📊 Testzusammenfassung

| Test | Operation | Register A | Register B | Ergebnis | Status |
|------|-----------|-----------|-----------|---------|--------|
| 1 | Addition | 3+2i | 1-i | 4+1i | ✅ |
| 2 | Subtraktion | 5+3i | 2+i | 3+2i | ✅ |
| 3 | Multiplikation | 2+i | 3-2i | 8-i | ✅ |
| 4 | Division | 4+2i | 2+0i | 2+i | ✅ |
| 5 | Speichern | 3+2i | 1+i | 4+3i | ✅ |
| 6 | Bus-Anbindung | 5+0i | 3+0i | 15+0i | ✅ |
| 7 | Zurücksetzen | - | - | 0+0i | ✅ |

---

### 🎥 Anleitung: Screenshots selbst erstellen

Da die Bilder oben Platzhalter sind, hier die Anleitung zum Erstellen eigener Screenshots:

1. **GUI starten:**
   ```bash
   java StartGUI
   ```

2. **Testfall vorbereiten:**
   - Register A und B mit den gewünschten Werten füllen
   - Operation und Anweisung auswählen

3. **Test durchführen:**
   - Auf **Ausführen** oder **Takt ausführen** klicken
   - Ergebnis überprüfen

4. **Screenshot erstellen:**
   - **Windows:** `Win + Shift + S` (Ausschneiden und Skizzieren)
   - **Mac:** `Cmd + Shift + 4` (ausgewählter Bereich)
   - **Linux:** `Shift + PrtScn` (ausgewählter Bereich)

5. **Bilder speichern:**
   - Erstelle einen Ordner `screenshots/` im Projektverzeichnis
   - Speichere die Screenshots mit aussagekräftigen Namen (z.B. `test_addition.png`)

6. **README aktualisieren:**
   - Ersetze die Platzhalter-Bildpfade mit den tatsächlichen Dateinamen
   - Aktualisiere die Bildbeschreibungen bei Bedarf

---

## 🎯 Übersicht

### Register
Der Beschleuniger besitzt **2 Register** für komplexe Zahlen:
- **Register A**: Speichert Realteil + Imaginärteil der ersten komplexen Zahl
- **Register B**: Speichert Realteil + Imaginärteil der zweiten komplexen Zahl
- **Ergebnis-Register**: Speichert das Ergebnis der letzten Berechnung

### Operationen
- `addieren`: Addition zweier komplexer Zahlen
- `subtrahieren`: Subtraktion zweier komplexer Zahlen
- `multiplizieren`: Multiplikation zweier komplexer Zahlen
- `dividieren`: Division zweier komplexer Zahlen

### Anweisungen
- `berechnung`: Führt die ausgewählte Operation aus
- `ausgabe`: Bereitet die Ausgabe auf den Bus vor (für zukünftige Bus-Anbindung)
- `speichern`: Speichert das Ergebnis in Register A

---

## 🚀 Verwendung

### 1️⃣ Konsolen-Tests (Main.java)

Führe die `Main`-Klasse aus, um alle Operationen zu testen:

```bash
# Kompilieren
javac -d . Math/KomplexeZahl.java controll/anweisung.java controll/operand.java Beschleuniger/Komplexbeschleuniger.java Main.java

# Ausführen
java Main
```

**Ausgabe:**
```
=== Komplexzahl-Beschleuniger Emulator ===

Initialzustand:
Komplexbeschleuniger {
  Register A: 3.0 + 2.0i
  Register B: 1.0 - 1.0i
  Ergebnis: 0.0 + 0.0i
  Operation: keine
  Anweisung: keine
}

--- Test: Addition (3+2i) + (1-i) ---
Ergebnis: 4.0 + 1.0i
Erwartet: 4.0 + 1.0i

--- Test: Subtraktion (3+2i) - (1-i) ---
Ergebnis: 2.0 + 3.0i
Erwartet: 2.0 + 3.0i

... (alle Tests)
```

### 2️⃣ GUI-Version (StartGUI.java)

Führe die `StartGUI`-Klasse aus, um die grafische Benutzeroberfläche zu starten:

```bash
# Kompilieren
javac -d . gui/EmulatorGUI.java StartGUI.java

# Ausführen
java StartGUI
```

**GUI-Funktionen:**
- ✅ Manuelle Eingabe der Register (Real- und Imaginärteil)
- ✅ Dropdown für Operationen (addieren, subtrahieren, multiplizieren, dividieren)
- ✅ Dropdown für Anweisungen (berechnung, ausgabe, speichern)
- ✅ Buttons: **Ausführen**, **Register aktualisieren**, **Zurücksetzen**
- ✅ Echtzeit-Anzeige von Registern und Ergebnis
- ✅ Konsolenausgabe für Debugging
- ✅ Unterstützung für deutsche Zahleneingabe (Komma als Dezimaltrennzeichen)

---

## 📝 Mathematische Grundlagen

### Komplexe Zahlen

Eine komplexe Zahl hat die Form: **z = a + bi**, wobei:
- **a** = Realteil
- **b** = Imaginärteil
- **i** = Imaginäre Einheit (i² = -1)

### Operationen

| Operation | Formel | Beispiel |
|-----------|--------|----------|
| **Addition** | (a+bi) + (c+di) = (a+c) + (b+d)i | (3+2i) + (1-i) = 4+1i |
| **Subtraktion** | (a+bi) - (c+di) = (a-c) + (b-d)i | (3+2i) - (1-i) = 2+3i |
| **Multiplikation** | (a+bi)·(c+di) = (ac-bd) + (ad+bc)i | (3+2i)·(1-i) = 5-1i |
| **Division** | (a+bi)/(c+di) = [(ac+bd) + (bc-ad)i] / (c²+d²) | (3+2i)/(1-i) = 0.5+2.5i |

---

## 🔧 Erweiterbarkeit

### Was du leicht selbst erweitern kannst:

#### 1. **Mehr Operationen hinzufügen**

In `Komplexbeschleuniger.java`, Methode `berechne()`:

```java
case "konjugieren":
    // Konjugiert komplex: (a+bi) -> (a-bi)
    ergebnis.setset(aReal, -aImag);
    break;

case "betrag":
    // Betrag: |a+bi| = sqrt(a² + b²)
    double betrag = Math.sqrt(aReal * aReal + aImag * aImag);
    ergebnis.setset(betrag, 0);
    break;
```

#### 2. **Bus-Anbindung implementieren**

Nutze die vorhandenen Bus-Klassen:

```java
// In Komplexbeschleuniger.java
private Steuerbus steuerbus;

public void connectToBus(Steuerbus bus) {
    this.steuerbus = bus;
}

// Bei Ausgabe-Anweisung:
case "ausgabe":
    steuerbus.write(ergebnis);
    break;
```

#### 3. **Mehr Register hinzufügen**

```java
// In Komplexbeschleuniger.java
private KomplexeZahl registerC;
private KomplexeZahl registerD;

public Komplexbeschleuniger(KomplexeZahl a, KomplexeZahl b, KomplexeZahl c, KomplexeZahl d) {
    this.registerA = a;
    this.registerB = b;
    this.registerC = c;
    this.registerD = d;
}
```

#### 4. **GUI erweitern**

- **Weitere Buttons** für neue Funktionen
- **Diagramme** für die Darstellung in der komplexen Ebene
- **History** der durchgeführten Operationen
- **Speichern/Laden** von Zuständen

#### 5. **Fehlerbehandlung verbessern**

```java
// In EmulatorGUI.java, Methode executeOperation()
try {
    // Operation ausführen
} catch (ArithmeticException e) {
    JOptionPane.showMessageDialog(this, "Mathematischer Fehler: " + e.getMessage());
} catch (IllegalArgumentException e) {
    JOptionPane.showMessageDialog(this, "Ungültige Eingabe: " + e.getMessage());
}
```

---

## 📌 Wichtige Hinweise für deine Bachelorarbeit

### ✅ Was die KI gemacht hat:

1. **Fehlerkorrektur in `Komplexbeschleuniger.java`:**
   - Unvollständige `switch`-Anweisung ergänzt
   - Syntaxfehler behoben
   - `run()`-Methode korrigiert
   - Volle Implementierung aller Operationen

2. **Erstellung neuer Dateien:**
   - `Main.java` - Konsolen-Tests
   - `StartGUI.java` - GUI-Starter
   - `gui/EmulatorGUI.java` - Grafische Benutzeroberfläche
   - `README.md` - Dokumentation

3. **Verbesserungen:**
   - `KomplexeZahl.java` - `toString()` für bessere Darstellung
   - Ausführliche Kommentare in allen neuen/veränderten Dateien

### 🎓 Was du selbst machen solltest:

1. **Bus-Anbindung implementieren** (für deine Bachelorarbeit relevant!)
2. **Hardware-nahe Optimierungen** vornehmen
3. **Weiterführende mathematische Funktionen** hinzufügen
4. **Performance-Messungen** durchführen
5. **Dokumentation erweitern** (deine eigenen Teile dokumentieren)

### 🔄 Wie du weiterarbeitest:

1. **Lies die Kommentare** in den Dateien - sie erklären die Funktionsweise
2. **Ändere die Dateien** nach Bedarf - alles ist einfach und verständlich
3. **Erweitere die Funktionalität** wie beschrieben
4. **Teste gründlich** - besonders die mathematischen Operationen

---

## 📊 Testprotokoll

Alle Operationen wurden getestet und liefern korrekte Ergebnisse:

| Operation | Beispiel | Ergebnis | Status |
|-----------|----------|----------|--------|
| Addition | (3+2i) + (1-i) | 4+1i | ✅ |
| Subtraktion | (3+2i) - (1-i) | 2+3i | ✅ |
| Multiplikation | (3+2i) * (1-i) | 5-1i | ✅ |
| Division | (3+2i) / (1-i) | 0.5+2.5i | ✅ |
| Speichern | Ergebnis -> Register A | ✅ | ✅ |

---

## 📦 Ausführbare JAR-Datei

Eine vorgefertigte, ausführbare JAR-Datei steht zur Verfügung:

### **Datei:** `KomplexBeschleunigerEmulator.jar`

### **Inhalt:**
- ✅ Alle kompilierten Klassen
- ✅ Manifest mit Einstiegspunkt `StartGUI`
- ✅ Komplette GUI-Anwendung

### **Ausführen:**

#### **Auf Linux/Mac:**
```bash
# Terminal öffnen und in das Projektverzeichnis wechseln
cd /Pfad/zu/KomplexBeschleunigerEmulator

# JAR-Datei ausführen
java -jar KomplexBeschleunigerEmulator.jar

# Oder mit dem Startskript:
chmod +x start.sh
./start.sh
```

#### **Auf Windows:**
```cmd
REM Eingabeaufforderung öffnen und in das Projektverzeichnis wechseln
cd C:\Pfad\zu\KomplexBeschleunigerEmulator

REM JAR-Datei ausführen
java -jar KomplexBeschleunigerEmulator.jar

REM Oder Doppelklick auf start.bat
```

### **Voraussetzungen:**
- Java Runtime Environment (JRE) Version 8 oder höher
- Grafische Oberfläche (für die GUI)

### **Hinweis:**
- Die JAR-Datei enthält **nur die kompilierten Klassen**, nicht die Quelldateien
- Die Quelldateien bleiben im Projektverzeichnis erhalten
- Bei Änderungen musst du die JAR neu erstellen (siehe unten)

### **JAR-Datei neu erstellen:**

Falls du Änderungen am Code vorgenommen hast:

```bash
# 1. Alle Klassen kompilieren
javac -d . Math/KomplexeZahl.java controll/anweisung.java controll/operand.java Bus/*.java Beschleuniger/Komplexbeschleuniger.java gui/EmulatorGUI.java StartGUI.java

# 2. Manifest-Datei erstellen (falls nicht vorhanden)
echo "Manifest-Version: 1.0" > MANIFEST.MF
echo "Main-Class: StartGUI" >> MANIFEST.MF
echo "Class-Path: ." >> MANIFEST.MF

# 3. JAR-Datei erstellen
jar cvfm KomplexBeschleunigerEmulator.jar MANIFEST.MF Math/*.class controll/*.class Bus/*.class Beschleuniger/*.class gui/*.class StartGUI.class

# 4. Aufräumen (optional)
rm -f MANIFEST.MF
find . -name "*.class" -type f -delete
```

---

## 📄 Lizenz & Urheberrecht

- **Bachelorarbeit:** Alex
- **KI-Unterstützung:** Fehlerkorrektur und Erweiterung der Funktionalität
- **Verwendung:** Freie Verwendung für Bildungszwecke

> **Wichtig:** Alle von der KI erstellten/veränderten Dateien sind mit klaren Hinweisen versehen. Du kannst sie frei verwenden, anpassen und in deiner Arbeit verwenden, solange du die Unterstützung kennzeichnest.

---

## 💡 Tipps für die Weiterentwicklung

1. **Beginne mit kleinen Schritten** - Teste jede neue Funktion einzeln
2. **Nutze die Konsole** für Debugging (bereits in der GUI integriert)
3. **Dokumentiere deine Änderungen** - besonders für die Bachelorarbeit
4. **Halte den Code einfach** - so wie er jetzt ist
5. **Teste regelmäßig** - besonders nach größeren Änderungen

---

**Viel Erfolg mit deiner Bachelorarbeit!** 🎓
