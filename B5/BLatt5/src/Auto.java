import java.io.Serializable;
public class Auto {
    private String farbe, marke;
    private int ps;
    private double gewichtInTonnen;
    public Auto(String farbe, int ps, double gewichtInTonnen, String marke) {
        this.farbe = farbe;
        this.ps = ps;
        this.gewichtInTonnen = gewichtInTonnen;
        this.marke = marke;
    }
    public String holeBeschreibung() {
        return "Ich bin ein "+farbe+"er "+marke;
    }
    public String holeSpassfaktor() {
        String result = "Ich wiege "+gewichtInTonnen+" Tonnen mit "+ps+" PS.";
        if (ps/gewichtInTonnen > 130) {
            result += " Ich bin sehr schnell!";
        } else if (ps/gewichtInTonnen > 80){
            result +=" Ich bin flott!";
        }
        return result;
    }
}