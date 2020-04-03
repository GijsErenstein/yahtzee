package yathzee;

import java.util.ArrayList;

public interface YathzeeUIInterface {

	public int promptDiceNumber(String string);
	
	public boolean promptYesOrNo(String string);
	
	public void showDices(ArrayList<Integer> values, ArrayList<Integer> heldValues);
	
	public void showMessage(String message);

	public void showStatus(Integer round, Integer roll, ArrayList<Integer> values);
}
