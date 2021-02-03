package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;

    @Before
    public void setUp() {
    pankki = mock(Pankki.class);
    viite = mock(Viitegeneraattori.class);
    varasto = mock(Varasto.class);
    kauppa = new Kauppa(varasto, pankki, viite);              
}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanJaParametritOvatOikein() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void kaksiEriTuotettaKoriinJaOikeatParametritKutsuttu() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olvi3", 3));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);   
        kauppa.lisaaKoriin(2);          
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(8));   
    }

    @Test
    public void kaksiSamaaTuotettaKoriinJaOikeatParametritKutsuttu() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);   
        kauppa.lisaaKoriin(1);          
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));   
    }

    @Test
    public void kaksiTuotettaKoriinJoistaToinenLoppuJaOikeatParametritKutsuttu() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olvi3", 3));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);   
        kauppa.lisaaKoriin(2);          
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
    }

    @Test
    public void aloitaOstoksetNollaaEdellisenOstoksenTiedot() {
        when(viite.uusi())
            .thenReturn(1)
            .thenReturn(2);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "123");

        verify(pankki).tilisiirto(eq("pekka"), eq(1), anyString(), anyString(), eq(5));

        kauppa.aloitaAsiointi();
        kauppa.tilimaksu("jonne", "1222");

        verify(pankki).tilisiirto(eq("jonne"), eq(2), anyString(), anyString(), eq(0));
    }

    @Test
    public void uusiViiteNumeroPyydettyJokaiselleMaksutapahtumalle() {        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.tilimaksu("pekka", "123");

        verify(viite, times(1)).uusi();
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("jonne", "1222");

        verify(viite, times(2)).uusi();
    }

    @Test
    public void poistaKoristaToimiiOikein() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("pekka", "123");

        verify(pankki).tilisiirto(eq("pekka"), eq(1), anyString(), anyString(), eq(0));
     
    }
}