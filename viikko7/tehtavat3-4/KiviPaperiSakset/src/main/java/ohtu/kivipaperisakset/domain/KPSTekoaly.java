package ohtu.kivipaperisakset.domain;

import ohtu.kivipaperisakset.domain.AI.Tekoaly;
import ohtu.kivipaperisakset.domain.IO.KonsoliIO;

public class KPSTekoaly extends KiviPaperiSakset  {
    private Tekoaly tekoaly;
    
    public KPSTekoaly(KonsoliIO io) {
        super(io);
        tekoaly = new Tekoaly();
    }
   
    @Override
    protected String toisenSiirto() {
        String tekoalynSiirto = tekoaly.annaSiirto();
        io.print("Tekoälyn siirto: " + tekoalynSiirto + "\n");
        return tekoalynSiirto;
    }
}