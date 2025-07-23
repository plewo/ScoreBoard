package board;

class Score {
    private int home = 0, away = 0;

    void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }

        home = homeScore;
        away = awayScore;
    }

    int summaryScore() {
        return home + away;
    }

    int homeScore() { return home; }
    int awayScore() { return away; }
}
