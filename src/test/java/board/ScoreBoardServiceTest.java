package board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardServiceTest {
    private ScoreBoardService scoreBoardService;

    @BeforeEach
    void setUp() {
        scoreBoardService = new ScoreBoardService();
    }

    @Test
    void shouldStartGameWithValidTeams() {
        String homeTeam = "Liverpool", awayTeam = "Manchester United";

        scoreBoardService.startGame(homeTeam, awayTeam);
        List<Game> gamesSummary = scoreBoardService.gamesSummary().keySet().stream().toList();
        Game startedGame = gamesSummary.getFirst();

        assertEquals(1, gamesSummary.size());
        assertEquals(homeTeam, startedGame.homeTeam());
        assertEquals(awayTeam, startedGame.awayTeam());
    }

    @Test
    void shouldStartGameWithInitialScore() {
        String homeTeam = "Liverpool", awayTeam = "Manchester United";
        final int expectedHomeScore = 0, expectedAwayScore = 0;
        Game startedGame = scoreBoardService.startGame(homeTeam, awayTeam);

        Map<Game, Score> gamesSummary = scoreBoardService.gamesSummary();
        Score startedScore = gamesSummary.get(startedGame);

        assertEquals(expectedHomeScore, startedScore.homeScore());
        assertEquals(expectedAwayScore, startedScore.awayScore());
    }

    @Test
    void shouldNotStartGameWithSameTeamNames() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.startGame("Liverpool", "Liverpool"));
    }

    @Test
    void shouldNotStartGameWithNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.startGame("Liverpool", null));
    }

    @Test
    void shouldNotStartGameWithEmptyStrings() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.startGame(" ", "Manchester United"));
    }
}