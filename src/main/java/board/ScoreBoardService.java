package board;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoardService {
    Map<Game, Score> games = new HashMap<>();

    public Game startGame(String homeTeam, String awayTeam) {
        Game newGame = new Game(homeTeam, awayTeam);
        games.put(newGame, new Score());
        return newGame;
    }

    public void finishGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }

        if(games.remove(game) == null) {
            throw new IllegalArgumentException("Game has not been started");
        };
    }

    public void updateScore(Game game, int homeScore, int awayScore) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }

        Score gameScore = games.get(game);

        if (gameScore == null) {
            throw new IllegalArgumentException("Game has not been started");
        }

        gameScore.updateScore(homeScore, awayScore);
    }

    public Map<Game, Score> gamesSummary() {
        return games;
    }
}
