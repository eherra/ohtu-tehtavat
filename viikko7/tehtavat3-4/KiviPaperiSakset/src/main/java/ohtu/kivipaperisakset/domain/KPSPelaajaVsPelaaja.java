package ohtu.kivipaperisakset.domain;

import ohtu.kivipaperisakset.domain.IO.KonsoliIO;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    public KPSPelaajaVsPelaaja(KonsoliIO io) {
        super(io);
    }

    @Override
    protected String toisenSiirto() {
        io.print("Toisen pelaajan siirto: ");
        return io.lueSyote();
    }

}