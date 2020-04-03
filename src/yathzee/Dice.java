package yathzee;

import java.util.Random;

public class Dice {

	private int value = 5;
	
	private boolean held = false;
	
	public Dice() {
		this.roll();
	}
	
	public int getValue() {
		
		return this.value;
	}
	
	public boolean isHeld() {
		
		return this.held;
	}
	
	public void hold() {
		this.held = true;
	}
	
	public void release() {
		this.held = false;
	}
	
	public void roll() {
		if (false == this.held) {
			this.value = new Random().nextInt(6) + 1;
		}
	}

}
