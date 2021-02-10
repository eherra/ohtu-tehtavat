package laskin;

public class Sovelluslogiikka {
 
    private int tulos, viimesinTila;
 
    public void plus(int luku) {
        viimesinTila = tulos;
        tulos += luku;
    }
     
    public void miinus(int luku) {
        viimesinTila = tulos;
        tulos -= luku;
    }
 
    public void nollaa() {
        viimesinTila = tulos;
        tulos = 0;
    }
 
    public int getTulos() {
        return tulos;
    }
    
    public void setViimesinTila() {
        tulos = viimesinTila;
    }
}