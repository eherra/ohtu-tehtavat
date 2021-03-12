
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {
    private Matcher hasAtLeast;

    public HasFewerThan(int value, String category) {
        hasAtLeast = new HasAtLeast(value, category);
    }

    @Override
    public boolean matches(Player p) {
        return !hasAtLeast.matches(p);
    }   
    
}
