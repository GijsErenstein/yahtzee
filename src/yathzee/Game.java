package yathzee;

import java.util.ArrayList;

public class Game {

	private DiceSet diceSet;
	private ScoreCard scoreCard;
	private YathzeeUIInterface ui;
	private int currentRound = 1;
	private int currentRoll = 1;

	public Game(DiceSet diceSet, ScoreCard scoreCard, YathzeeUIInterface ui) {
		this.diceSet = diceSet;
		this.scoreCard = scoreCard;
		this.ui = ui;
	}
	
	public void playGame() {
		while (!this.scoreCard.isFull()) {
			this.playRound();
			this.currentRound ++;
		}
	}
	
	private void playRound() {
		this.ui.showStatus(this.currentRound, this.currentRoll, this.diceSet.getValues());
		for(int i = 0; i < 2; i++) {
			this.rollDices();
			this.holdAndRelease();
			this.currentRoll++;
			this.ui.showStatus(this.currentRound, this.currentRoll, this.diceSet.getValues());
		}
		this.rollDices();
		this.captureScore();		
		
		this.releaseAllDices();
		this.currentRoll = 1;
	}
	
	private void rollDices() {
		this.diceSet.rollDices();
		this.ui.showDices(this.diceSet.getValues(), this.diceSet.getHeldValues());
	}
	
	private void holdAndRelease() {
		boolean finished = !this.ui.promptYesOrNo("Do you need to hold or release any dices?");
		while (!finished) {
			this.holdDices();
			this.releaseDices();
			finished = this.ui.promptYesOrNo("ready?");
		}
	}
	
	private void captureScore() {
		
		ArrayList<ScoreInterface> possibleScores = this.scoreCard.findPossibleScores(this.diceSet.getValues());
		
		for (ScoreInterface score : possibleScores) { 
			this.ui.showMessage(score.getName() + ": " + score.getValue());
		}
		
		// ask user to select one score
		
		// fill in score in scoreCard
		
		this.ui.showMessage("Whoops no way to store your score yet");
	}
	
	private void releaseAllDices() {
		this.diceSet.releaseAll();
	}

	private void holdDices() {
		int number = this.ui.promptDiceNumber("What number do you want to hold(leave empty for none)?");
		if (this.diceSet.holdNumber(number)) {
			this.ui.showDices(this.diceSet.getValues(), this.diceSet.getHeldValues());
		}
	}
	
	private void releaseDices() {
		int number = this.ui.promptDiceNumber("What number do you want to Release(leave empty for none)?");
		if (this.diceSet.releaseNumber(number)) {
			this.ui.showDices(this.diceSet.getValues(), this.diceSet.getHeldValues());
		}
	}
	
}
