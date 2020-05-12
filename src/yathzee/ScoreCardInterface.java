package yathzee;

import java.util.ArrayList;

public interface ScoreCardInterface {

	public boolean isFull();
	
	public ArrayList<ScoreInterface> findPossibleScores(ArrayList<Integer> values);

	/**
	 * Stores a score on the scorecard. returns true if successful
	 *
	 * @param score a score to be stored on the card
	 * @return returns true if is was stored successfully
	 */
	public boolean storeScore(ScoreInterface score);

	public String reportStatus();

}
