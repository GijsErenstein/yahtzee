package yathzee;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreCard implements ScoreCardInterface {

	final String NAME_ONES = "ones";
	final String NAME_TWOS = "two's";
	final String NAME_THREES = "threes";
	final String NAME_FOURS = "fours";
	final String NAME_FIVES = "fives";
	final String NAME_SIXES = "sixes";
	
	final String NAME_STRAIGHT_LARGE = "Large straight";
	final String NAME_STRAIGHT_SMALL = "Small straight";
	
	final int SCORE_STRAIGHT_LARGE = 40;
	final int SCORE_STRAIGHT_SMALL = 30;
	
	private Score ones;
	private Score twos;
	private Score threes;
	private Score fours;
	private Score fives;
	private Score sixes;
	
	private Score threeofakind;
	private Score fourofakind;
	private Score omniscore;
	
	private Score fullhouse;
	private Score smallstraight;
	private Score largestraight;
	
	private Score free;
	
	@Override
	public boolean isFull() {

		return false;
	}

	@Override
	public ArrayList<ScoreInterface> findPossibleScores(ArrayList<Integer> values) 
	{	
		ArrayList<ScoreInterface> possibleScores = new ArrayList<ScoreInterface>();
		possibleScores.addAll(this.findNumberScores(values));
		possibleScores.addAll(this.findStraightScores(values));
		
		return possibleScores;
	}
	
	private ArrayList<ScoreInterface> findNumberScores(ArrayList<Integer> values)
	{
		ArrayList<ScoreInterface> scores = new ArrayList<ScoreInterface>();
		for (int i = 1; i < 7; i++) {
			Integer scoreValue = 0;
			for (Integer value : values) {
				if (i == value) {
					scoreValue = scoreValue + value;
				}
	        }
			Score score = new Score(this.pickNameForSingleNumbers(i), scoreValue );
			scores.add(score);
		}
		
		return scores;
	}
	
	private String pickNameForSingleNumbers(int value)
	{
		switch(value) {
		case 1:
			return this.NAME_ONES;
		case 2:
			return this.NAME_TWOS;
		case 3:
			return this.NAME_THREES;
		case 4:
			return this.NAME_FOURS;
		case 5:
			return this.NAME_FIVES;
		case 6:
			return this.NAME_SIXES;
		default:
			return this.NAME_ONES;
		}
	}
	
	private ArrayList<ScoreInterface> findStraightScores(ArrayList<Integer> values)
	{
		ArrayList<ScoreInterface> scores = new ArrayList<ScoreInterface>();
		
		int longestSequence = this.findLongestConsecutiveSubsequence(values);
		
		System.out.println("LONGEST SEQUENCE: " + longestSequence);
		
		if (longestSequence > 3) {
			scores.add(new Score(this.NAME_STRAIGHT_SMALL, this.SCORE_STRAIGHT_SMALL));
		}
		
		if (longestSequence > 4) {
			scores.add(new Score(this.NAME_STRAIGHT_LARGE, this.SCORE_STRAIGHT_LARGE));
		}
		
		return scores;
	}
	
	private int findLongestConsecutiveSubsequence(ArrayList<Integer> values) {
		int longestSequence = 1;
		int currentSequence = 1;
		
		Collections.sort(values);
		
		for (int i= 1; i < values.size(); ++i)  {			
			if ( (values.get(i) - values.get(i - 1)) == 1 ) {
				currentSequence++;	
				if (currentSequence > longestSequence) {
					longestSequence = currentSequence;
				}
			} else if (values.get(i) - values.get(i - 1) != 0 ) {
				currentSequence = 1;
			}
		}
		
		return longestSequence;
	}

}
