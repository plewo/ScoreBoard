package board;

public class Score {
    private int home = 0, away = 0;

    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }

        home = homeScore;
        away = awayScore;
    }

    public int homeScore() { return home; }
    public int awayScore() { return away; }
}
