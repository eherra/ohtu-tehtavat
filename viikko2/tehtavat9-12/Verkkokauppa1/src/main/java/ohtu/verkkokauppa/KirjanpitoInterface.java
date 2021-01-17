package ohtu.verkkokauppa;

import java.util.ArrayList;
import java.util.List;

public interface KirjanpitoInterface {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
