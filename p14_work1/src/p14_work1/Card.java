package p14_work1;

public class Card {
	private final Suit suit; //マーク
	private final Rank rank; //数字

//getter
	//マーク
	public String getSuit() {
		return suit.getSuit();
	}
	//表示名
	public String getLabel() {
		return rank.getLabel();
	}
	//数字の強さ
	public int getStrength() {
		return rank.getStrength();
	}

//コンストラクタ
	Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	//カードの表示用
	public String toString() {
		return suit.getSuit() + "-" + rank.getLabel();
	}
}
