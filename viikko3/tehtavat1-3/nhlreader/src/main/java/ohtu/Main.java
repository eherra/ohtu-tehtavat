package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        Gson mapper = new Gson();
        List<Player> players = getFinnishPlayersSortedByTotalScore(mapper.fromJson(bodyText, Player[].class));

        System.out.println("Players from Finland sorted by total score:");
        for (Player player : players) {
            System.out.println(player);
        }   
    }
    
    public static List<Player> getFinnishPlayersSortedByTotalScore(Player[] players) {
        return Arrays.stream(players)
                .filter(p -> p.getNationality().equals("FIN"))
                .sorted(Comparator.comparingInt(Player::getTotalScore).reversed())
                .collect(Collectors.toList());
    }
  
}