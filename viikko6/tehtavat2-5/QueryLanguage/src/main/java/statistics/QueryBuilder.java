package statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {
    private List<Matcher> matchers;
    
    public QueryBuilder() {
        matchers = new ArrayList();
    }
        
    public Matcher build() {
        Matcher[] listOfMatchers = matchers.toArray(new Matcher[0]);
        matchers.clear();
        return new And(listOfMatchers);
    }
    
    public QueryBuilder oneOf(Matcher first, Matcher second) {
        matchers.add(new Or(first, second));
        return this;
    }
    
    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }
}
