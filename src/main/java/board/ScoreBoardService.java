package board;

import java.util.*;

public class ScoreBoardService {
    private final LinkedHashMap<Game, Score> games = new LinkedHashMap<>();

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

    public List<Summary> gamesSummary() {
        return games.reversed().sequencedEntrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().summaryScore(), e1.getValue().summaryScore()))
                .map(entry -> new Summary(entry.getKey(), entry.getValue()))
                .toList();
    }
}
