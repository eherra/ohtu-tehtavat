package ohtu.kivipaperisakset.domain;

public class Tuomari {
    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {        
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    private static boolean tasapeli(String eka, String toka) {
        return eka.equals(toka);
    }

    private static boolean ekaVoittaa(String eka, String toka) {
        return onkoKivenVoitto(eka, toka) || onkoSaksienVoitto(eka, toka) || onkoPaperinVoitto(eka, toka);
    }
    
    private static boolean onkoKivenVoitto(String eka, String toka) {
        return eka.equals("k") && toka.equals("s");
    }
    
    private static boolean onkoSaksienVoitto(String eka, String toka) {
        return eka.equals("s") && toka.equals("p");
    }
    
    private static boolean onkoPaperinVoitto(String eka, String toka) {
        return eka.equals("p") && toka.equals("k");
    }

    public String toString() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
            + "Tasapelit: " + tasapelit;
    }
}