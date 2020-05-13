package p14_work1;

//カードのマークのenum
public enum Suit {
	//CLUB("♣"),
	DIAMOND("♦"),
	//HEART("♥"),
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

//中断データ読込用
	static Suit getSuitValue(String mark) {
		for(Suit z : Suit.values()) {
			if(z.getSuit().equals(mark)) {
				return z;
			}
		}
		return null;
	}
}
