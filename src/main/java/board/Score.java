package board;

public class Score {
    private int home = 0, away = 0;

    public void updateScore(int homeScore, int awayScore) {
        home = homeScore;
        away = awayScore;
    }

    public int homeScore() { return home; }
    public int awayScore() { return away; }
}
