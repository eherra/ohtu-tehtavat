package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void testSearch() {
        Player p = stats.search("Semenko");
        assertEquals(p.getName(), "Semenko");
        
        Player nullPlayer = stats.search("Ei kukaan");
        assertEquals(nullPlayer, null);

        Player partOfNameWorks = stats.search("urri");
        assertEquals(partOfNameWorks.getName(), "Kurri");
    }

    @Test
    public void testTeam() {
        Set<String> playerNames = getPlayerNamesAsSet(stats.team("EDM"));
        assertEquals(playerNames.size(), 3);
        
        assertEquals(true, playerNames.contains("Kurri"));
        assertEquals(true, playerNames.contains("Semenko"));
        assertEquals(true, playerNames.contains("Gretzky"));
        assertEquals(false, playerNames.contains("Yzerman"));
    }

    @Test
    public void testTopScorers() {
        List<Player> topPlayers = stats.topScorers(2);
        assertEquals(topPlayers.get(0).getName(), "Gretzky");
        assertEquals(topPlayers.get(1).getName(), "Lemieux");
    }
    
    public Set<String> getPlayerNamesAsSet(List<Player> players) {
        return players.stream()
                .map(name -> name.getName())
                .collect(Collectors.toSet());
    }
    
}
