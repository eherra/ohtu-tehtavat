
package ohtu;

public class Player {
    private String name, nationality, team;
    private int assists, goals, penalties, games;

    public String getNationality() {
        return nationality;
    }
    
    public int getTotalScore() {
        return goals + assists;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getTabs(String name) {
        return name.length() > 15 ? "\t": "\t\t";
    }

    @Override
    public String toString() {
        return name + getTabs(name) + team + "\t" + goals + " + " + assists + " = " + getTotalScore();
    }
      
}
