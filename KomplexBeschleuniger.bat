@echo off
REM Komplexzahl-Beschleuniger Emulator
REM ====================================
REM Doppelklick zum Starten der GUI
REM Benötigt Java Runtime Environment (JRE)

@echo Starten des Komplexzahl-Beschleuniger-Emulators...
timeout /t 1 >nul
start "KomplexBeschleuniger" javaw -jar KomplexBeschleunigerEmulator.jar
