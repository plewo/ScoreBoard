package board;

public record Game(String homeTeam, String awayTeam) {
    public Game(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Home and away team names cannot be null");
        }

        if (homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Home and away team names cannot be empty");
        }

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home and away team names cannot be the same");
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
