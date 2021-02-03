
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, 
                            OLETUSKASVATUS = 5;  
    private int kasvatuskoko;     
    private int[] lukuJono;     
    private int alkioidenLkm;   

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti täytyy olla suurempi kuin 0.");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti täytyy olla suurempi kuin 0.");//heitin vaan jotain :D
        }
        
        lukuJono = new int[kapasiteetti];
        Arrays.fill(lukuJono, 0);
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }
 
    public void lisaaLukuJoukkoon(int luku) {
        if (alkioidenLkm == 0) {
            lukuJono[0] = luku;
            alkioidenLkm++;
        } else if (!kuuluukoLukuJoukkoon(luku)) {
            lukuJono[alkioidenLkm] = luku;
            alkioidenLkm++;
        }
        
        if (taulukonKasvatusTarpeellista()) {
            kastavaTaulukkoa();
        }
    }
    
    public boolean taulukonKasvatusTarpeellista() {
        return alkioidenLkm % lukuJono.length == 0;
    }
    
    public void kastavaTaulukkoa() {
        int[] taulukkoOld = new int[lukuJono.length];
        taulukkoOld = lukuJono;
        kopioiTaulukko(lukuJono, taulukkoOld);
        lukuJono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukuJono);
    }

    public boolean kuuluukoLukuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJono[i]) {
                return true;
            }
        }
        return false;
    }

    public void poistaLukuJoukostaJosLasna(int poistettavaLuku) {
        int poistettavanLuvunIndeksi = getPoistettavanLuvunIndeksi(poistettavaLuku);
        
        if (loytykoLukulistalta(poistettavanLuvunIndeksi)) {
            siirraLukujaPoistonJalkeenYhdellaIndeksilla(poistettavanLuvunIndeksi);
        }
    }
    
    public int getPoistettavanLuvunIndeksi(int poistettavaLuku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (poistettavaLuku == lukuJono[i]) {
                lukuJono[i] = 0;
                return i;
            }
        }
        return -1;
    }

    public boolean loytykoLukulistalta(int luku ) {
        return luku != -1;
    }
    
    public void siirraLukujaPoistonJalkeenYhdellaIndeksilla(int poistettavanLuvunIndeksi) {
        int apuMuuttuja;
        for (int j = poistettavanLuvunIndeksi; j < alkioidenLkm - 1; j++) {

            apuMuuttuja = lukuJono[j];
            lukuJono[j] = lukuJono[j + 1];
            lukuJono[j + 1] = apuMuuttuja;
        }
        alkioidenLkm--;
    }
    
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int getAlkioidenLukumaara() {
        return alkioidenLkm;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJono[i];
        }
        return taulu;
    }
       
    public static IntJoukko luoYhdisteJoukostaJaPalauta(IntJoukko ekaJoukko, IntJoukko tokaJoukko) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] ekanJoukonArvot = ekaJoukko.toIntArray();
        int[] tokanJoukonArvot = tokaJoukko.toIntArray();
        
        
        for (int i = 0; i < ekanJoukonArvot.length; i++) {
            yhdisteJoukko.lisaaLukuJoukkoon(ekanJoukonArvot[i]);
        }
        
        for (int i = 0; i < tokanJoukonArvot.length; i++) {
            yhdisteJoukko.lisaaLukuJoukkoon(tokanJoukonArvot[i]);
        }
        
        return yhdisteJoukko;
    }

    public static IntJoukko luoLeikkausJoukoistaJaPalauta(IntJoukko ekaJoukko, IntJoukko tokaJoukko) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] ekanJoukonArvot = ekaJoukko.toIntArray();
        int[] tokanJoukonArvot = tokaJoukko.toIntArray();
        
        for (int i = 0; i < ekanJoukonArvot.length; i++) {
            for (int j = 0; j < tokanJoukonArvot.length; j++) {
                if (ekanJoukonArvot[i] == tokanJoukonArvot[j]) {
                    leikkausJoukko.lisaaLukuJoukkoon(tokanJoukonArvot[j]);
                }
            }
        }
        return leikkausJoukko;
    }
    
    public static IntJoukko erotus (IntJoukko ekaJoukko, IntJoukko tokaJoukko) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] ekanJoukonArvot = ekaJoukko.toIntArray();
        int[] tokanJoukonArvot = tokaJoukko.toIntArray();
        
        for (int i = 0; i < ekanJoukonArvot.length; i++) {
            erotusJoukko.lisaaLukuJoukkoon(ekanJoukonArvot[i]);
        }
        for (int i = 0; i < tokanJoukonArvot.length; i++) {
            erotusJoukko.poistaLukuJoukostaJosLasna(tokanJoukonArvot[i]);
        }
 
        return erotusJoukko;
    }
    
    @Override
    public String toString() {
        StringBuilder palautusString = new StringBuilder();
        palautusString.append("{");
        
        if (alkioidenLkm == 0) {
            palautusString.append("}");
        } else if (alkioidenLkm == 1) {
            palautusString.append(lukuJono[0]).append("}");
        } else {
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                palautusString.append(lukuJono[i] + ", ");
            }
            palautusString.append(lukuJono[alkioidenLkm - 1] + "}");
        }
        
        return palautusString.toString();
    }
        
}
