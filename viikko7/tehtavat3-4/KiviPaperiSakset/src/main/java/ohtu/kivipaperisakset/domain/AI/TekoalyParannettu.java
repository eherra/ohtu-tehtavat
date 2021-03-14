package ohtu.kivipaperisakset.domain.AI;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class TekoalyParannettu {
    private ArrayList<String> muisti;
    private int muistinKoko;

    public TekoalyParannettu(int muistinKoko) {
        this.muistinKoko = muistinKoko;
        muisti = new ArrayList<>();
    }
  
    public void asetaSiirto(String siirto) {
        if (onkoMuistinVapautusTarpeen()) {
            muisti.remove(0);
        }
    
        muisti.add(siirto); 
    }
  
    private boolean onkoMuistinVapautusTarpeen() {
        return muisti.size() == muistinKoko;
    }
  
    public String annaSiirto() {
        if (muisti.isEmpty()) {
            return "k";
        }
    
        HashMap<String, Integer> pelatutMaarat = alustaPelatutSiirrot();

        if (onkoPaperinSiirtoParas(pelatutMaarat)) {
            return "p";
        } else if (onkoSaksienSiirtoParas(pelatutMaarat)) {
            return "s";
        } else {
            return "k";
        }
    }
    
    private HashMap<String, Integer> alustaPelatutSiirrot() {
        String viimeisinSiirto = muisti.get(muisti.size() - 1);
        HashMap<String, Integer> pelatutMaaratPalautus = alustaMahdollisetSiirrot();

        for (int i = 0; i < muisti.size() - 1; i++) {
            if (viimeisinSiirto.equals(muisti.get(i))) {
                String seuraava = muisti.get(i+1);
                pelatutMaaratPalautus.put(seuraava, pelatutMaaratPalautus.get(seuraava) + 1);
            }
        }
        
        return pelatutMaaratPalautus;
    }
    
    private HashMap<String, Integer> alustaMahdollisetSiirrot() {
        HashMap<String, Integer> palautus = new HashMap();
        palautus.put("k", 0);
        palautus.put("p", 0);
        palautus.put("s", 0);
        return palautus;
    }
    
    private boolean onkoPaperinSiirtoParas(HashMap<String, Integer> pelatut) {
        int kivenSiirrot = pelatut.get("k");
        int saksienSiirrot = pelatut.get("s");
        int paperinSiirrot = pelatut.get("p");
        
        return kivenSiirrot > Math.max(paperinSiirrot, saksienSiirrot);
    }
    
    private boolean onkoSaksienSiirtoParas(HashMap<String, Integer> pelatut) {
        int kivenSiirrot = pelatut.get("k");
        int saksienSiirrot = pelatut.get("s");
        int paperinSiirrot = pelatut.get("p");
        
        return paperinSiirrot > Math.max(saksienSiirrot, kivenSiirrot);
    }
    
}