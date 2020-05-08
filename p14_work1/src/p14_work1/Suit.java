package p14_work1;

public enum Suit {
//	CLUB("♣"),
	DIAMOND("♦"),
//	HEART("♥"),
	SPADE("♠");

	private String suit;

	Suit(String mark){
		this.suit = mark;
	}

	public String getSuit() {
		return suit;
	}
}
