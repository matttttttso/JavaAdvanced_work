package p14_work1;

import java.util.ArrayList;
import java.util.List;

public class Card {
	private final Rank rank; //数字
	private final Suit suit; //マーク

	//getter
	//表示名
	public String getLabel() {
		return rank.getLabel();
	}
	//数字の強さ
	public int getStrength() {
		return rank.getStrength();
	}
	//マーク
	public String getSuit() {
		return suit.getSuit();
	}

	//コンストラクタ
	private Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	private static final List<Card> protoDeck = new ArrayList<Card>();
	//デッキの初期化
	static {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				protoDeck.add(new Card(rank, suit));
			}
		}
	}

	//初期化したデッキを返す
	public static ArrayList<Card> newDeck() {
		return new ArrayList<Card>(protoDeck);
	}

	//カードの表示用
	public String toString() {
		return suit.getSuit() + "-" + rank.getLabel();
	}
}
