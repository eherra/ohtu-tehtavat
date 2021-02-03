package ohtu;

public class TennisGame {
    private Player player1;
    private Player player2;

    public TennisGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
    }

    public void wonPoint(String whoWonThePointName) {
        increasePointWinnersPoints(whoWonThePointName).increasePoints();
    }
    
    public Player increasePointWinnersPoints(String whoWonThePointName) {
        return whoWonThePointName.equals(player1.getName()) ? player1 : player2;
    }
    
    public boolean isGameTie() {
        return player1.getPoints() == player2.getPoints();
    }
    
    public boolean isFortyPointsReached() {
        return player1.getPoints() >= 4 || player2.getPoints() >= 4;
    }
    
    public int getDifferenceOnAdvantageOrWinStage() {
        return player1.getPoints() - player2.getPoints();
    }
    

    public String getGameScore() {
        StringBuilder buildScore = new StringBuilder();
        
        if (isGameTie()) {
            buildScore.append(getTieString(player1.getPoints()));
        } else if (isFortyPointsReached()) {
            int difference = getDifferenceOnAdvantageOrWinStage();
            buildScore.append(getAdvantageOrWinnerString(difference));
        } else {
            buildScore.append(getScoreStringFromPlayersScore(player1.getPoints()))
                .append("-")
                .append(getScoreStringFromPlayersScore(player2.getPoints()));
        }
        return buildScore.toString();
    }
    
    public String getTieString(int points) {
        switch (points)  {
            case 0:
                return "0-0";
            case 1:
                return "Fifteen-0";
            case 2:
                return "Thirty-0";
            case 3:
                return "Forty-0";
            default:
                return "40-40";
        }
    }
    
    
    public String getAdvantageOrWinnerString(int difference) {
        switch (difference) { 
            case 1: 
                return "Advantage " + player1.getName();
            case -1:
                return "Advantage " + player2.getName();
            case 2: 
            case 3: 
            case 4: 
               return "Win for " + player1.getName();
        }
        return "Win for " + player2.getName();
    }
    
    public String getScoreStringFromPlayersScore(int playerScore) {
        switch (playerScore) {
            case 0:
                return "0";
            case 1:
                return "Fifteen";
            case 2:
                return"Thirty";
            case 3:
                return "Forty";
        }
        return "";
    }
}