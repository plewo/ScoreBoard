import board.Game;
import board.ScoreBoardService;

public class PrintTest {
    public static void main(String[] args) {
        ScoreBoardService scoreBoardService = new ScoreBoardService();
        Game game1 = scoreBoardService.startGame("Mexico", "Canada");
        scoreBoardService.updateScore(game1, 0, 5);
        Game game2 = scoreBoardService.startGame("Spain", "Brazil");
        scoreBoardService.updateScore(game2, 10, 2);
        Game game3 = scoreBoardService.startGame("Germany", "France");
        scoreBoardService.updateScore(game3, 2, 2);
        Game game4 = scoreBoardService.startGame("Uruguay", "Italy");
        scoreBoardService.updateScore(game4, 6, 6);
        Game game5 = scoreBoardService.startGame("Argentina", "Australia");
        scoreBoardService.updateScore(game5, 3, 1);
        Game game6 = scoreBoardService.startGame("Poland", "Norway");
        scoreBoardService.updateScore(game6, 4, 0);

        scoreBoardService.gamesSummary().forEach(System.out::println);
        System.out.println("==========================");

        scoreBoardService.finishGame(game6);
        scoreBoardService.gamesSummary().forEach(System.out::println);
    }
}
