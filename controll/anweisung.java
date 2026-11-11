package controll;

public class anweisung {

    private String anweisung;
    
    public anweisung(String anweisung) {
        if (!check(anweisung)) {
            throw new IllegalArgumentException("Ungültige Anweisung");
        }
        else {
            this.anweisung = anweisung;
        }
    }

    public boolean check (String anweisung) {
        if (anweisung.equals("ausgabe") || anweisung.equals("berechnung") || anweisung.equals("speichern")) {
            return true;
        } else {
            return false;
        }
    }

    public String getAnweisung() {
        return anweisung;
    }

    public void setAnweisung(String anweisung) {
        if (!check(anweisung)) {
            throw new IllegalArgumentException("Ungültige Anweisung");
        }
        else {
            this.anweisung = anweisung;
        }
    }
    
}
