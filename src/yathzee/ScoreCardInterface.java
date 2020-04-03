package yathzee;

import java.util.ArrayList;

public interface ScoreCardInterface {

	public boolean isFull();
	
	public ArrayList<ScoreInterface> findPossibleScores(ArrayList<Integer> values);
}
