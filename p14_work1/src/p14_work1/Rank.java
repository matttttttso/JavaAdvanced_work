package p14_work1;

public enum Rank {
	DEUCE("2", 2),
	TREY("3", 3),
	CATER("4", 4),
	CINQUE("5", 5),
	SICE("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 11),
	QUEEN("Q", 12),
	KING("K", 13),
	ACE("A", 100);

	private String label;
	private int strength;

	Rank(String label, int strength){
		this.label = label;
		this.strength = strength;
	}

	public String getLabel() {
		return label;
	}

	public int getStrength() {
		return strength;
	}
}
