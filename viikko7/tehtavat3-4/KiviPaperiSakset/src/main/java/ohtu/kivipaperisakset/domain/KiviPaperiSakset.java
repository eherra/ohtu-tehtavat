package ohtu.kivipaperisakset.domain;

import ohtu.kivipaperisakset.domain.IO.KonsoliIO;

public abstract class KiviPaperiSakset {
    public KonsoliIO io;
    
    public KiviPaperiSakset(KonsoliIO io) {
        this.io = io;
    }
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        String ekanSiirto = ensimmaisenSiirto();
        String tokanSiirto = toisenSiirto();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            tulostaTilanne(tuomari);
            io.print("");

            ekanSiirto = ensimmaisenSiirto();
            tokanSiirto = toisenSiirto();
        }

        io.print("");
        io.print("Kiitos!");
        tulostaTilanne(tuomari);
    }
    
    protected void tulostaTilanne(Tuomari tuomari) {
        io.print(tuomari.toString());
    }
    
    protected String ensimmaisenSiirto() {
        io.print("Ensimmäisen pelaajan siirto: ");
        return io.lueSyote();
    }

    abstract protected String toisenSiirto();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}

   
