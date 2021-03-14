package ohtu.kivipaperisakset.domain.IO;

import java.util.Scanner;

public class KonsoliIO implements KonsoliIORajapinta {
    private Scanner lukija;
    private String viimeisinSiirto;
    
    public KonsoliIO(Scanner lukija) {
        this.lukija = lukija;
    }

    @Override
    public void print(String tulostettava) {
        System.out.println(tulostettava);
    }

    @Override
    public String lueSyote() {
        viimeisinSiirto = lukija.nextLine();
        return viimeisinSiirto;
    }
    
    public String getViimeisinSiirto() {
        return viimeisinSiirto;
    }
}
