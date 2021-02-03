package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    private static IntJoukko A, B, C;

    private static String lueKayttajanSyoteJaPalauta() {
        Scanner lukijaSyotteenLukuun = new Scanner(System.in);
        String luettu = lukijaSyotteenLukuun.nextLine();
        return luettu.toLowerCase();
    }

    private static IntJoukko getJoukkoKayttajalta() {
        String luettu = lueKayttajanSyoteJaPalauta();
        
        while (true) {
            switch (luettu) {
                case "a":
                    return A;
                case "b":
                    return B;
                case "c":
                    return C;
                default:   
                    System.out.println("Joukkoa ei olemassa. Valitse jokin joukoista A, B tai C.");
                    luettu = lueKayttajanSyoteJaPalauta();
            }
        }
    }
        
    private static void lisaaLuku() {
        System.out.println("Mihin joukkoon? ");
        IntJoukko joukko = getJoukkoKayttajalta();
        
        System.out.println("Mikä luku lisätään? ");
        int lisattavaLuku = getNumeroArvo();
        
        joukko.lisaaLukuJoukkoon(lisattavaLuku);
    }

    private static void luoYhdisteJoukoista() {
        System.out.println("1. joukko? ");
        IntJoukko ekaJoukko = getJoukkoKayttajalta();
        
        System.out.println("2. joukko? ");
        IntJoukko  tokaJoukko = getJoukkoKayttajalta();
        
        IntJoukko yhteisetArvot = IntJoukko.luoYhdisteJoukostaJaPalauta(ekaJoukko, tokaJoukko);
        System.out.println("1. joukon ja 2. joukon yhdiste = " + yhteisetArvot.toString());
    }

    private static void luoLeikkausJoukoista() {
        System.out.println("1. joukko? ");
        IntJoukko ekaJoukko = getJoukkoKayttajalta();
        
        System.out.println("2. joukko? ");
        IntJoukko tokaJoukko = getJoukkoKayttajalta();
        
        IntJoukko joukkojenLeikkaus = IntJoukko.luoLeikkausJoukoistaJaPalauta(ekaJoukko, tokaJoukko);
        System.out.println("1. joukon ja 2. joukon leikkaus = " + joukkojenLeikkaus.toString());
    }

    private static void luoErotusJoukoista() {
        System.out.println("1. joukko? ");
        IntJoukko ekaJoukko = getJoukkoKayttajalta();
        
        System.out.println("2. joukko? ");
        IntJoukko tokaJoukko = getJoukkoKayttajalta();
        
        IntJoukko joukkojenErotus = IntJoukko.erotus(ekaJoukko, tokaJoukko);
        System.out.println("1. joukon ja 2. joukon erotus = " + joukkojenErotus.toString());
    }

    private static void poistaArvoJoukosta() {
        System.out.println("Mistä joukosta? ");
        IntJoukko joukko = getJoukkoKayttajalta();
        
        System.out.println("Mikä luku poistetaan? ");
        int poistettavaLuku = getNumeroArvo();
        
        joukko.poistaLukuJoukostaJosLasna(poistettavaLuku);
    }

    private static void kuuluukoJoukkoon() {
        System.out.println("Mihin joukkoon? ");
        IntJoukko joukko = getJoukkoKayttajalta();
        
        System.out.println("Mikä luku? ");
        int tarkastettavaLuku = getNumeroArvo();
        boolean lukuKuuluuJoukkoon = joukko.kuuluukoLukuJoukkoon(tarkastettavaLuku);
        
        tulostaKuuluvuus(lukuKuuluuJoukkoon, tarkastettavaLuku);
    }
    
    public static void tulostaKuuluvuus(boolean lukuKuuluuJoukkoon, int tarkastettavaLuku) {
        if (lukuKuuluuJoukkoon) {
            System.out.println(tarkastettavaLuku + " kuuluu joukkoon ");
        } else {
            System.out.println(tarkastettavaLuku + " ei kuulu joukkoon ");
        }
    }

    public static void main(String[] args) {
        Scanner lukijaPaaKomennoille = new Scanner(System.in);
        alustaJoukot();
        tulostaTervetuloaTeksti();
        boolean ohjelmaKaynnissa = true;
        
        while (ohjelmaKaynnissa) {
            String komento = tarkastaKomentoJaPalauta(lukijaPaaKomennoille.nextLine());
            
            switch (komento) {
                case ("lisää"):
                    lisaaLuku();
                    break;
                case ("poista"):
                    poistaArvoJoukosta();
                    break;
                case ("kuuluu"):
                    kuuluukoJoukkoon();
                    break;
                case ("yhdiste"):
                    luoYhdisteJoukoista();
                    break;
                case ("leikkaus"):
                    luoLeikkausJoukoista();
                    break;
                case ("erotus"):
                    luoErotusJoukoista();
                    break;
                case ("A"):
                    System.out.println(A);
                    break;
                case ("B"):
                    System.out.println(B);
                    break;
                case ("C"):
                    System.out.println(C);
                    break;
                case ("lopeta"):
                    System.out.println("Lopetetaan, moikka!");
                    ohjelmaKaynnissa = false;
                    break;
                    
                default:
                    System.out.println("Virheellinen komento! Kokeile toista komentoa!");
            }           
            
            System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
        }
    }
    
    public static String tarkastaKomentoJaPalauta(String luettu) {
        if (luettu.equalsIgnoreCase("lisää") || luettu.equalsIgnoreCase("li")) {
                return "lisää";
            } else if (luettu.equalsIgnoreCase("poista") || luettu.equalsIgnoreCase("p")) {
                return "poista";
            } else if (luettu.equalsIgnoreCase("kuuluu") || luettu.equalsIgnoreCase("k")) {
                return "kuuluu";
            } else if (luettu.equalsIgnoreCase("yhdiste") || luettu.equalsIgnoreCase("y")) {
                return "yhdiste";
            } else if (luettu.equalsIgnoreCase("leikkaus") || luettu.equalsIgnoreCase("le")) {
                return "leikkaus";
            } else if (luettu.equalsIgnoreCase("erotus") || luettu.equalsIgnoreCase("e")) {
                return "erotus";
            } else if (luettu.equalsIgnoreCase("A")) {
                return "A";
            } else if (luettu.equalsIgnoreCase("B")) {
                return "B";
            } else if (luettu.equalsIgnoreCase("C")) {
                return "C";
            } else if (luettu.equalsIgnoreCase("lopeta") || luettu.equalsIgnoreCase("quit") || luettu.equalsIgnoreCase("q")) {
                return "lopeta";
            } else {
                return "virhe";
            }
    }
    public static void alustaJoukot() {
        A = new IntJoukko();
        B = new IntJoukko();
        C = new IntJoukko();
    }
    
    public static void tulostaKomentoVaihtoehdot() {
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
    }
    
    public static void tulostaTervetuloaTeksti() {
        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        tulostaKomentoVaihtoehdot();
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");
    }
    
    public static int getNumeroArvo() {
        Scanner numeroLukija = new Scanner(System.in);
        int palautettavaArvo = 0;
        while (true) {
            try {
                palautettavaArvo = numeroLukija.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Syötetty arvo ei numeroarvo. Syötä ystävällisesti merkeillä 0-9 haluamasi luku.");
                numeroLukija.next();
            }
        }
        
        return palautettavaArvo;
    }
}


