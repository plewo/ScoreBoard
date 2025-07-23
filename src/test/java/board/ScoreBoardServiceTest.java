package board;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardServiceTest {

    @Test
    void shouldStartGameWithValidTeams() {
        ScoreBoardService scoreBoardService = new ScoreBoardService();
        String homeTeam = "Liverpool", awayTeam = "Manchester United";

        scoreBoardService.startGame(homeTeam, awayTeam);
        List<Game> gamesSummary = scoreBoardService.gamesSummary();
        Game startedGame = gamesSummary.getFirst();

        assertEquals(1, gamesSummary.size());
        assertEquals(homeTeam, startedGame.homeTeam);
        assertEquals(awayTeam, startedGame.awayTeam);
    }

    @Test
    void shouldStartGameWithInitialScore() {
        ScoreBoardService scoreBoardService = new ScoreBoardService();
        String homeTeam = "Liverpool", awayTeam = "Manchester United";
        final int homeScore = 0, awayScore = 0;

        scoreBoardService.startGame(homeTeam, awayTeam);
        List<Game> gamesSummary = scoreBoardService.gamesSummary();
        Game startedGame = gamesSummary.getFirst();

        assertEquals(homeScore, startedGame.homeScore);
        assertEquals(awayScore, startedGame.awayScore);
    }

    @Test
    void shouldNotStartGameWithSameTeamNames() {
        ScoreBoardService scoreBoardService = new ScoreBoardService();
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.startGame("Liverpool", "Liverpool"));
    }

    @Test
    void shouldNotStartGameWithNullArguments() {
        ScoreBoardService scoreBoardService = new ScoreBoardService();
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.startGame("Liverpool", null));
    }

    @Test
    void shouldNotStartGameWithEmptyStrings() {
        ScoreBoardService scoreBoardService = new ScoreBoardService();
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.startGame(" ", "Manchester United"));
    }
}