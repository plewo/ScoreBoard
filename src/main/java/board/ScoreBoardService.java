package board;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoardService {
    Map<Game, Score> games = new HashMap<>();

    public Game startGame(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Home and away team names cannot be null");
        }

        if (homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Home and away team names cannot be empty");
        }

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home and away team names cannot be the same");
        }

        Game newGame = new Game(homeTeam, awayTeam);
        games.put(newGame, new Score());
        return newGame;
    }

    public void finishGame(Game game) { }

    public Map<Game, Score> gamesSummary() {
        return games;
    }
}
