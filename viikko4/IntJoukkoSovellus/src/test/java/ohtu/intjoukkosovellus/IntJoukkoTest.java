package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaaLukuJoukkoon(10);
        joukko.lisaaLukuJoukkoon(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaaLukuJoukkoon(4);
        assertEquals(3, joukko.getAlkioidenLukumaara());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaaLukuJoukkoon(10);
        joukko.lisaaLukuJoukkoon(3);
        assertEquals(2, joukko.getAlkioidenLukumaara());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluukoLukuJoukkoon(10));
        assertFalse(joukko.kuuluukoLukuJoukkoon(5));
        assertFalse(joukko.kuuluukoLukuJoukkoon(0));
        assertTrue(joukko.kuuluukoLukuJoukkoon(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poistaLukuJoukostaJosLasna(3);
        assertFalse(joukko.kuuluukoLukuJoukkoon(3));
        assertEquals(1, joukko.getAlkioidenLukumaara());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        joukko.lisaaLukuJoukkoon(55);
        joukko.poistaLukuJoukostaJosLasna(10);
        joukko.lisaaLukuJoukkoon(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            joukko.lisaaLukuJoukkoon(luku);
        }
        assertEquals(14, joukko.getAlkioidenLukumaara());
        assertTrue(joukko.kuuluukoLukuJoukkoon(11));
        joukko.poistaLukuJoukostaJosLasna(11);
        assertFalse(joukko.kuuluukoLukuJoukkoon(11));
        assertEquals(13, joukko.getAlkioidenLukumaara());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", joukko.toString());
    }
    
    @Test
    public void toStringToimiiYhdenKokoiselleJoukolla(){
        joukko = new IntJoukko();
        joukko.lisaaLukuJoukkoon(1);
        assertEquals("{1}", joukko.toString());
    }

    @Test
    public void toStringToimiiTyhjallaJoukolla(){
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }     
}
