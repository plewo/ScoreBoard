# Description
Simple maven project that provides service to manage scoreboard.

# Usage
Create class `ScoreBoardService` and use its API to manage scoreboard.
Example usage you can see in `PrintTest` class.

# API
`Game startGame(String homeTeam, String awayTeam)` - return reference for started game

`void finishGame(Game game)`

`void updateScore(Game game, int homeScore, int awayScore)`

`List<Summary> gamesSummary()` - return list of printable summary ( `Summary.toString()` )

# Limitation
- Project use in-memory collection for simplicity (limited storage)
