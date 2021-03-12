package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    private Matcher matcherToGetNegationFrom;
    
    public Not(Matcher matcher) {
        matcherToGetNegationFrom = matcher;
    }

    public boolean matches(Player p) {
        return !matcherToGetNegationFrom.matches(p);
    }
    
}
