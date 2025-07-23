package board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<Summary> gamesSummary = scoreBoardService.gamesSummary();
        Game startedGame = gamesSummary.getFirst().game();

        assertEquals(1, gamesSummary.size());
        assertEquals(homeTeam, startedGame.homeTeam());
        assertEquals(awayTeam, startedGame.awayTeam());
    }

    @Test
    void shouldStartGameWithInitialScore() {
        String homeTeam = "Liverpool", awayTeam = "Manchester United";
        final int expectedHomeScore = 0, expectedAwayScore = 0;
        scoreBoardService.startGame(homeTeam, awayTeam);

        List<Summary> gamesSummary = scoreBoardService.gamesSummary();
        Score startedScore = gamesSummary.getFirst().score();

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

    @Test
    void shouldRemoveGameFromSummaryWhenGameFinished() {
        String homeTeam = "Liverpool", awayTeam = "Manchester United";
        Game startedGame = scoreBoardService.startGame(homeTeam, awayTeam);

        scoreBoardService.finishGame(startedGame);
        List<Game> liveGames = scoreBoardService.gamesSummary().stream().map(Summary::game).toList();

        assertFalse(liveGames.contains(startedGame));
        assertEquals(0, liveGames.size());
    }

    @Test
    void shouldFailWhenTryingToFinishUnknownGame() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.finishGame(null));
    }

    @Test
    void shouldFailWhenTryingToFinishNotStartedGame() {
        Game notStartedGame = new Game("Liverpool", "Manchester United");

        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.finishGame(notStartedGame));
    }

    @Test
    void shouldUpdateScoreWithValidArguments() {
        String homeTeam = "Liverpool", awayTeam = "Manchester United";
        Game startedGame = scoreBoardService.startGame(homeTeam, awayTeam);
        final int expectedHomeScore = 1, expectedAwayScore = 2;

        scoreBoardService.updateScore(startedGame, expectedHomeScore, expectedAwayScore);
        Summary gameSummary = scoreBoardService.gamesSummary().getFirst();

        assertEquals(startedGame, gameSummary.game());
        assertEquals(expectedHomeScore, gameSummary.score().homeScore());
        assertEquals(expectedAwayScore, gameSummary.score().awayScore());
    }

    @Test
    void shouldFailWhenTryingToUpdateScoreWithNegativeScore() {
        String homeTeam = "Liverpool", awayTeam = "Manchester United";
        Game startedGame = scoreBoardService.startGame(homeTeam, awayTeam);

        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.updateScore(startedGame, -1, 2));
    }

    @Test
    void shouldFailWhenTryingToUpdateScoreWithNonExistingGame() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.updateScore(null, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> scoreBoardService.updateScore(new Game("Liverpool", "Manchester United"), 1, 2));
    }

    @Test
    void summaryShouldContainOnlyStartedGames() {
        Game started = scoreBoardService.startGame("Liverpool", "Manchester United");
        Game notStarted = new Game("Real Madrid", "FC Barcelona");

        List<Game> startedGames = scoreBoardService.gamesSummary().stream().map(Summary::game).toList();

        assertFalse(startedGames.contains(notStarted));
        assertTrue(startedGames.contains(started));
    }

    @Test
    void summaryShouldBeSortedBySummaryScoreDescending() {
        Game started1 = scoreBoardService.startGame("Liverpool", "Manchester United");
        Game started2 = scoreBoardService.startGame("Real Madrid", "FC Barcelona");

        scoreBoardService.updateScore(started2, 0, 1);

        List<Summary> gamesSummary = scoreBoardService.gamesSummary();

        assertEquals(started2, gamesSummary.getFirst().game());
        assertEquals(started1, gamesSummary.getLast().game());
    }

    @Test
    void moreRecentGameOccursEarlierWhenSummaryScoreIsTheSame() {
        Game firstAdded = scoreBoardService.startGame("Liverpool", "Manchester United");
        Game secondAdded = scoreBoardService.startGame("Real Madrid", "FC Barcelona");

        List<Summary> gamesSummary = scoreBoardService.gamesSummary();

        assertEquals(gamesSummary.getFirst().score().summaryScore(), gamesSummary.getLast().score().summaryScore());
        assertEquals(secondAdded, gamesSummary.getFirst().game());
        assertEquals(firstAdded, gamesSummary.getLast().game());
    }
}