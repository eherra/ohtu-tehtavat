package ohtu.kivipaperisakset.domain;

import ohtu.kivipaperisakset.domain.AI.TekoalyParannettu;
import ohtu.kivipaperisakset.domain.IO.KonsoliIO;

public class KPSParempiTekoaly extends KiviPaperiSakset {
    private TekoalyParannettu tekoalyParannettu;

    public KPSParempiTekoaly(KonsoliIO io) {
        super(io);
        tekoalyParannettu = new TekoalyParannettu(20);
    }

    @Override
    protected String toisenSiirto() {
        String tekoalynSiirto = tekoalyParannettu.annaSiirto();
        tekoalyParannettu.asetaSiirto(io.getViimeisinSiirto());
        
        io.print("Tekoälyn siirto: " + tekoalynSiirto + "\n");
        return tekoalynSiirto;
    }
}
