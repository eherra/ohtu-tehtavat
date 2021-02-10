
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        sovellus.miinus(getArvoSyotekentasta());
        tuloskentta.setText(String.valueOf(sovellus.getTulos()));  
    }

    @Override
    public void peru() {
        sovellus.setViimesinTila();
        tuloskentta.setText(String.valueOf(sovellus.getTulos()));
    }
    
    public int getArvoSyotekentasta() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            System.out.println("Syötekentän arvo ei integer-arvo.");
        }
        return arvo;
    }
}
