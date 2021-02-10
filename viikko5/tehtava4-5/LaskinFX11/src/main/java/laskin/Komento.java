
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    
    protected Button nollaa, undo;
    protected TextField tuloskentta, syotekentta;
    protected Sovelluslogiikka sovellus;
    
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
    }

    public abstract void suorita();
    public abstract void peru();

}
