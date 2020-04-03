package yathzee;

public class Yathzee {

	public static void main(String[] args) {

		DiceSet diceSet = new DiceSet();
		ScoreCard scoreCard = new ScoreCard();
		YathzeeUIInterface ui = new YathzeeBasicUI();
		
		Game game = new Game(diceSet, scoreCard, ui);
		
		game.playGame();
		
	}

}
