package ohtu.kivipaperisakset.ui;

import java.util.Scanner;
import ohtu.kivipaperisakset.domain.IO.KonsoliIO;
import ohtu.kivipaperisakset.domain.KPSParempiTekoaly;
import ohtu.kivipaperisakset.domain.KPSPelaajaVsPelaaja;
import ohtu.kivipaperisakset.domain.KPSTekoaly;

public class Kayttoliittyma {
    private KonsoliIO io;
    
    public void kaynnista() {
        io = new KonsoliIO(new Scanner(System.in));
        loop:
        while (true) {
            tulostaPelimuodot();
            
            String valittuPelimuoto = io.lueSyote();
            tulostaValittavatSiirrot();

            switch (valittuPelimuoto) {
                case "1":
                    KPSPelaajaVsPelaaja kaksinpeli = new KPSPelaajaVsPelaaja(io);
                    kaksinpeli.pelaa();
                    break;
                    
                case "2":
                    KPSTekoaly yksinpeli = new KPSTekoaly(io);
                    yksinpeli.pelaa();
                    break;
                    
                case "3":
                    KPSParempiTekoaly pahaYksinpeli = new KPSParempiTekoaly(io);
                    pahaYksinpeli.pelaa();
                    break;
                    
                default:
                    break loop;
            }
        }
    }
    
    private void tulostaValittavatSiirrot() {
        io.print("Valittavat siirrot:");
        io.print("(p) - paperi");
        io.print("(k) - kivi");
        io.print("(s) - sakset");
        io.print("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s\n");
    }
    
    private void tulostaPelimuodot() {
        io.print("\nValitse pelimuoto:");
        io.print(" (1) ihmistä vastaan");
        io.print(" (2) tekoälyä vastaan");
        io.print(" (3) parannettua tekoälyä vastaan");
        io.print("muilla valinnoilla lopetetaan");
    }
}
