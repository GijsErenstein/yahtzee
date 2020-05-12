package yathzee;

import java.util.ArrayList;

public class YathzeeBasicUI implements YathzeeUIInterface{

	@Override
	public int promptDiceNumber(String prompt) {
		int result;
		try {
			result = Integer.parseInt(this.input(prompt).trim());
		} catch (Exception e) {
			result = 0;
		}
		
		if (result > 6) {
			result = 0;
		}
		return result;
	}
	
	@Override
	public boolean promptYesOrNo(String prompt) {
		boolean result = false;
		String input = input(prompt).trim();
		if (input.equals("y")) {
			result = true;
		}
		
		return result;
	}
	
	public String input(String prompt) {
	    String inputLine = "";
	    
	    System.out.print(prompt);
	    
	    try {
	      java.io.InputStreamReader sys = new java.io.InputStreamReader(
	          System.in);
	      java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
	      inputLine = inBuffer.readLine();
	    } catch (Exception e) {
	      String err = e.toString();
	      System.out.println(err);
	    }
	    return inputLine;
	}

	@Override
	public void showDices(ArrayList<Integer> values, ArrayList<Integer> heldValues) {
		String dicesAsString = "Dices: ";
		
		for (Integer value : values ) {
			dicesAsString += this.getDiceAscii(value);
	    }
		
		dicesAsString += " currently held: ";
		
		for (Integer value : heldValues ) {
			dicesAsString += this.getDiceAscii(value);
	    }
		
		System.out.println(dicesAsString);
		
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	private String getDiceAscii(int number) {
		switch(number) {
		case 1:
			return "⚀";
		case 2:
		    return "⚁";
		case 3:
			return "⚂";
		case 4:
			return "⚃";
		case 5:
			return "⚄";
		case 6:
			return "⚅";
		default:
			return Integer.toString(number);
		}
	}

	@Override
	public void showStatus(Integer round, Integer roll, ArrayList<Integer> values) {
		System.out.println("-------STATUS-------");

		System.out.println("rnd: " + round + " roll: " + roll);
		System.out.println("--------------------");
	}

}
