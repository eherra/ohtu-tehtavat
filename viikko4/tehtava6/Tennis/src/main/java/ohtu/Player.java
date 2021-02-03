
package ohtu;

public class Player {
    private String name;
    private int points;
    
    public Player(String name) {
        this.name = name;
        points = 0;
    }

    public String getName() {
        return name;
    }

    public void increasePoints() {
        points++;
    }

    public int getPoints() {
        return points;
    }
}
