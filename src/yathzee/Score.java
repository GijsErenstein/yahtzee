package yathzee;

public class Score implements ScoreInterface {
	
	private String name;
	private Integer value;

	public Score(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {

		return this.name;
	}

	public int getValue() {

		return this.value;
	}

}
