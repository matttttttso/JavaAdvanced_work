package p14_work1;

//カードのマークのenum
public enum Suit {
//	CLUB("♣"),
	DIAMOND("♦"),
//	HEART("♥"),
	SPADE("♠");

	private String suit;

	//getter
	public String getSuit() {
		return suit;
	}

	//コンストラクタ
	Suit(String mark){
		this.suit = mark;
	}
}
