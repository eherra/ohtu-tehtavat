package ohtu.kivipaperisakset.domain.AI;

import ohtu.kivipaperisakset.*;
import java.util.Random;

public class Tekoaly {
    private Random ran;
    
    public Tekoaly() {
        ran = new Random();
    }

    public String annaSiirto() {
        int siirto = ran.nextInt(3);
        return getSiirto(siirto);
    }
    
    public String getSiirto(int siirto) {
        switch (siirto) {
            case 0:
                return "k";
            case 1:
                return "s";
            case 2: 
                return "p";
            default:
                return "vaaraSyote";
        }
    }
}
