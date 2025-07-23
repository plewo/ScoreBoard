package board;

public record Summary(Game game, Score score) {
    @Override
    public String toString() {
        return String.format("%s - %s: %d - %d", game.homeTeam(), game.awayTeam(), score.homeScore(), score.awayScore());
    }
}
