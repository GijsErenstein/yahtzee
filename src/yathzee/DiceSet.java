package yathzee;

import java.util.ArrayList;

public class DiceSet {
	
	private ArrayList<Dice> dices = new ArrayList<Dice>();
	
	public DiceSet() {
		for(int i = 0; i < 5; i++) {
			this.dices.add(new Dice());
		}
	}
	
	public void rollDices() {
		for (Dice dice : dices) {
            dice.roll();
        }
	}
	
	public ArrayList<Integer> getValues() {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		for (Dice dice : dices) {
			values.add(dice.getValue());
        }
		
		return values;
	}
	
	public ArrayList<Integer> getHeldValues() {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		for (Dice dice : dices) {
			if (dice.isHeld()) {
				values.add(dice.getValue());
			}
        }
		
		return values;
	}
	
	public boolean holdNumber(int number) {
		for (Dice dice : dices) {
            if (dice.getValue() == number && !dice.isHeld()) {
            	dice.hold();
            	return true;
            }
        } 
		return false;
	}
	
	public boolean releaseNumber(int number) {
		for (Dice dice : dices) {
            if (dice.getValue() == number && dice.isHeld()) {
            	dice.release();
            	return true;
            }
        } 
		return false;
	}
	
	public void releaseAll() {
		for (Dice dice : dices) {
			dice.release();
        } 
	}

}
