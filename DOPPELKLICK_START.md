# Anleitung: JAR-Datei per Doppelklick starten

## 🎯 Ziel: KomplexBeschleunigerEmulator.jar per Doppelklick starten

Es gibt verschiedene Möglichkeiten, je nach Betriebssystem.

---

## 🪟 Windows

### Methode 1: Batch-Datei verwenden (empfohlen)

Die einfachste Lösung ist, die **`KomplexBeschleuniger.bat`**-Datei zu verwenden:

1. **Datei finden:** `KomplexBeschleuniger.bat` (im Projektverzeichnis)
2. **Doppelklick** auf die Datei
3. ✅ Fertig! Die GUI startet

### Methode 2: JAR-Datei direkt per Doppelklick starten

Falls die JAR-Datei nicht direkt per Doppelklick funktioniert:

#### Schritt 1: Prüfen, ob Java installiert ist
- Öffne die Eingabeaufforderung: `Win + R` → `cmd` → Enter
- Gib ein: `java -version`
- Falls Java nicht installiert ist: [Java herunterladen](https://www.java.com/de/download/)

#### Schritt 2: JAR-Dateiendung mit javaw verknüpfen

1. **Dateiendungen anzeigen lassen:**
   - Öffne den Windows Explorer
   - Klicke auf "Ansicht" → "Dateinamenerweiterungen" aktivieren

2. **JAR-Dateiendung verknüpfen:**
   - Rechtsklick auf `KomplexBeschleunigerEmulator.jar`
   - Wähle "Öffnen mit..." → "Andere App auswählen"
   - Wähle "Java(TM) Platform SE binary" oder "javaw.exe"
   - Aktivieren: "Immer diese App zum Öffnen von .jar-Dateien verwenden"
   - Klicke auf "OK"

3. **Doppelklick testen:**
   - Doppelklick auf `KomplexBeschleunigerEmulator.jar`
   - ✅ Die GUI sollte starten

#### Schritt 3: (Optional) Verknüpfung auf dem Desktop erstellen

1. Rechtsklick auf `KomplexBeschleunigerEmulator.jar`
2. Wähle "Senden an" → "Desktop (Verknüpfung erstellen)"
3. Jetzt kannst du die Verknüpfung per Doppelklick starten

---

## 🐧 Linux (GNOME/KDE)

### Methode 1: Desktop-Datei verwenden

1. **Desktop-Datei bearbeiten:**
   - Öffne `start_linux_desktop.desktop` in einem Texteditor
   - Ändere den Pfad in der Zeile `Exec=` und `Path=` falls nötig
   - Speichern

2. **Desktop-Datei ausführbar machen:**
   ```bash
   chmod +x start_linux_desktop.desktop
   ```

3. **Desktop-Datei in den Autostart-Ordner kopieren:**
   ```bash
   cp start_linux_desktop.desktop ~/.local/share/applications/
   ```

4. **Doppelklick testen:**
   - Die Datei sollte jetzt im Anwendungsmenü erscheinen
   - Oder kopiere sie auf den Desktop

### Methode 2: JAR-Datei direkt ausführbar machen

1. **JAR-Datei ausführbar machen:**
   ```bash
   chmod +x KomplexBeschleunigerEmulator.jar
   ```

2. **Desktop-Verknüpfung erstellen:**
   ```bash
   # Verknüpfung auf dem Desktop erstellen
   ln -s /home/alex/Schreibtisch/komplexBeschleunigerEmulator/KomplexBeschleunigerEmulator.jar ~/Desktop/KomplexBeschleuniger.jar
   ```

3. **Doppelklick testen:**
   - Doppelklick auf die Verknüpfung

---

## 🍎 macOS

### Methode 1: Terminal-Befehl als Application erstellen

1. **Automator öffnen** (im Ordner "Dienstprogramme")
2. **Neues Dokument** → "Anwendung" auswählen
3. **Aktion hinzufügen:** "Shell-Skript ausführen"
4. **Skript eingeben:**
   ```bash
   cd /Pfad/zu/KomplexBeschleunigerEmulator
   java -jar KomplexBeschleunigerEmulator.jar
   ```
5. **Speichern als:** "KomplexBeschleuniger.app" (auf dem Desktop)
6. **Doppelklick testen:**
   - Doppelklick auf die .app-Datei

### Methode 2: JAR-Dateiendung verknüpfen

1. **Terminal öffnen**
2. **JAR-Datei ausführbar machen:**
   ```bash
   chmod +x KomplexBeschleunigerEmulator.jar
   ```
3. **Verknüpfung erstellen:**
   ```bash
   ln -s /Pfad/zu/KomplexBeschleunigerEmulator/KomplexBeschleunigerEmulator.jar ~/Desktop/KomplexBeschleuniger.jar
   ```

---

## 📋 Zusammenfassung der Dateien

| **Datei** | **Betriebssystem** | **Verwendung** |
|-----------|-------------------|----------------|
| `KomplexBeschleunigerEmulator.jar` | Alle | Haupt-JAR-Datei (benötigt Java) |
| `KomplexBeschleuniger.bat` | Windows | Doppelklick zum Starten |
| `start.sh` | Linux/Mac | Terminal: `./start.sh` |
| `start_linux_desktop.desktop` | Linux | Desktop-Integration |

---

## ⚠️ Wichtige Hinweise

1. **Java muss installiert sein!**
   - Ohne Java funktioniert die JAR-Datei nicht
   - Teste mit: `java -version`

2. **Javaw vs Java:**
   - **`javaw`** = Java ohne Konsolenfenster (für GUI-Programme)
   - **`java`** = Java mit Konsolenfenster
   - Die Batch-Dateien verwenden `javaw` für ein sauberes GUI

3. **Pfad anpassen:**
   - Falls du die Dateien verschiebst, musst du die Pfade in den Skripten anpassen

4. **Fehlerbehebung:**
   - Falls nichts passiert: Rechtsklick → "Öffnen mit" → Java auswählen
   - Falls Fehler erscheint: Java installieren oder Pfade prüfen

---

## 🎉 Empfohlene Lösung

**Für Windows-User:**
- Verwende **`KomplexBeschleuniger.bat`** (per Doppelklick)
- Oder erstelle eine Verknüpfung zu `KomplexBeschleunigerEmulator.jar`

**Für Linux-User:**
- Verwende **`start.sh`** (im Terminal)
- Oder installiere die Desktop-Datei

**Für macOS-User:**
- Erstelle eine Automator-Anwendung
- Oder verwende das Terminal

---

**Falls du Fragen hast oder Hilfe brauchst, frag einfach!** 😊
