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
	final String NAME_THREE_OF_A_KIND = "Three of a kind";
	final String NAME_FOUR_OF_A_KIND = "Four of a kind";
	final String NAME_OMNI = "Omni score";
	final String NAME_FULL_HOUSE = "Full house";
	final String NAME_FREE = "Free score";

	final int SCORE_STRAIGHT_LARGE = 40;
	final int SCORE_STRAIGHT_SMALL = 30;
	final int SCORE_OMNI = 50;
	final int SCORE_FULL_HOUSE = 25;

	private ScoreInterface ones;
	private ScoreInterface twos;
	private ScoreInterface threes;
	private ScoreInterface fours;
	private ScoreInterface fives;
	private ScoreInterface sixes;
	private ScoreInterface threeOfAKind;
	private ScoreInterface fourOfAKind;
	private ScoreInterface omniScore;
	private ScoreInterface fullHouse;
	private ScoreInterface smallStraight;
	private ScoreInterface largeStraight;
	private ScoreInterface free;
	
	@Override
	public boolean isFull() {

		return null != this.ones && null != this.twos && null != this.threes && null != this.fours && null != this.fives &&
				null != this.sixes && null != this.threeOfAKind && null != this.fourOfAKind && null != this.omniScore &&
				null != this.fullHouse && null != this.smallStraight && null != largeStraight && null != this.free;
	}

	@Override
	public ArrayList<ScoreInterface> findPossibleScores(ArrayList<Integer> values) 
	{	
		ArrayList<ScoreInterface> possibleScores = new ArrayList<ScoreInterface>();
		possibleScores.addAll(this.findNumberScores(values));
		possibleScores.addAll(this.findStraightScores(values));
		possibleScores.addAll(this.findDuplicateNumberScores(values));
		possibleScores.addAll(this.findFullHouse(values));
		possibleScores.addAll(this.findFreeScore(values));

		return possibleScores;
	}

	@Override
	public boolean storeScore(ScoreInterface score) {
		switch(score.getName()) {
			case (NAME_ONES):
				this.ones = score;
				break;
			case NAME_TWOS:
				this.twos = score;
			case NAME_THREES:
				this.threes = score;
			case NAME_FOURS:
				this.fours = score;
			case NAME_FIVES:
				this.fives = score;
			case NAME_SIXES:
				this.sixes = score;
			case NAME_STRAIGHT_SMALL:
				this.smallStraight = score;
			case NAME_STRAIGHT_LARGE:
				this.largeStraight = score;
			case NAME_FULL_HOUSE:
				this.fullHouse = score;
			case NAME_THREE_OF_A_KIND:
				this.threeOfAKind = score;
			case NAME_FOUR_OF_A_KIND:
				this.fourOfAKind = score;
			case NAME_OMNI:
				this.omniScore = score;
			case NAME_FREE:
				this.free = score;
			default:
				return false;
		}

		return true;
	}


	private ArrayList<ScoreInterface> findFullHouse(ArrayList<Integer> values) {
		ArrayList<ScoreInterface> scores = new ArrayList<ScoreInterface>();

		if (this.findLongestDuplicateSubsequence(values) == 3 && this.findAmountOfDifferentNumbers(values) == 2) {
			scores.add(new Score(NAME_FULL_HOUSE, SCORE_FULL_HOUSE));
		}
		return scores;
	}

	private ArrayList<ScoreInterface> findFreeScore(ArrayList<Integer> values) {
		ArrayList<ScoreInterface> scores = new ArrayList<ScoreInterface>();

		scores.add(new Score(NAME_FREE, this.calculateSumOfAllValues(values)));

		return scores;
	}

	private ArrayList<ScoreInterface> findDuplicateNumberScores(ArrayList<Integer> values) {
		ArrayList<ScoreInterface> scores = new ArrayList<ScoreInterface>();

		int longestSequence = this.findLongestDuplicateSubsequence(values);

		if (longestSequence > 2) {
			scores.add(new Score(NAME_THREE_OF_A_KIND, this.calculateSumOfAllValues(values)));
		}

		if (longestSequence > 3) {
			scores.add(new Score(NAME_FOUR_OF_A_KIND, this.calculateSumOfAllValues(values)));
		}

		if (longestSequence > 4) {
			scores.add(new Score(NAME_OMNI, SCORE_OMNI));
		}

		return scores;

	}

	private int findAmountOfDifferentNumbers(ArrayList<Integer> values) {
		ArrayList<Integer> numbersPresent = new ArrayList<Integer>();

		for (Integer value : values) {
			if (!numbersPresent.contains(value)) {
				numbersPresent.add(value);
			}
		}

		return numbersPresent.size();
	}

	private int findLongestDuplicateSubsequence(ArrayList<Integer> values) {
		int longestSequence = 1;
		int currentSequence = 1;

		Collections.sort(values);

		for (int i= 1; i < values.size(); ++i)  {
			if ( values.get(i).equals(values.get(i - 1)) ) {
				currentSequence++;
				if (currentSequence > longestSequence) {
					longestSequence = currentSequence;
				}
			} else {
				currentSequence = 1;
			}
		}

		return longestSequence;
	}

	private int calculateSumOfAllValues(ArrayList<Integer> values) {

		int sum = 0;

		for (Integer value : values) {
			sum = sum + value;
		}

		return sum;
	}
	
	private ArrayList<ScoreInterface> findNumberScores(ArrayList<Integer> values)
	{
		ArrayList<ScoreInterface> scores = new ArrayList<ScoreInterface>();
		for (int i = 1; i < 7; i++) {
			int scoreValue = 0;
			for (Integer value : values) {
				if (i == value) {
					scoreValue = scoreValue + value;
				}
	        }
			if (scoreValue != 0) {
				Score score = new Score(this.pickNameForSingleNumbers(i), scoreValue );
				scores.add(score);
			}
		}
		
		return scores;
	}
	
	private String pickNameForSingleNumbers(int value)
	{
		switch(value) {
		case 1:
			return NAME_ONES;
		case 2:
			return NAME_TWOS;
		case 3:
			return NAME_THREES;
		case 4:
			return NAME_FOURS;
		case 5:
			return NAME_FIVES;
		case 6:
			return NAME_SIXES;
		default:
			return "Huh?";
		}
	}
	
	private ArrayList<ScoreInterface> findStraightScores(ArrayList<Integer> values)
	{
		ArrayList<ScoreInterface> scores = new ArrayList<ScoreInterface>();
		
		int longestSequence = this.findLongestConsecutiveSubsequence(values);
		
		if (longestSequence > 3) {
			scores.add(new Score(NAME_STRAIGHT_SMALL, SCORE_STRAIGHT_SMALL));
		}
		
		if (longestSequence > 4) {
			scores.add(new Score(NAME_STRAIGHT_LARGE, SCORE_STRAIGHT_LARGE));
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

	public String reportStatus()
	{
		String status = "ScoreCard status: \n";

		status += "Hoi!";

		return status;

	}

}
