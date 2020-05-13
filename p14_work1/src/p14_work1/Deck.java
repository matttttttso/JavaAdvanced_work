package p14_work1;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	//デッキの初期化
	private static final List<Card> protoDeck = new ArrayList<Card>();
	static {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				protoDeck.add(new Card(suit, rank));
			}
		}
	}

	//初期化したデッキを返す
	public static ArrayList<Card> newDeck() {
		return new ArrayList<Card>(protoDeck);
	}

	//カードを配る
	static ArrayList<Card> deal(List<Card> deck, int n) {
		int deckSize = deck.size();
		List<Card> handView = deck.subList(deckSize - n, deckSize);
		ArrayList<Card> hand = new ArrayList<Card>(handView);
		handView.clear();
		return hand;
	}
}
