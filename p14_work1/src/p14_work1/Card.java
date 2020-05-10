package p14_work1;

import java.util.ArrayList;
import java.util.List;

public class Card {
	private final Rank rank;
	private final Suit suit;

	private Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public String toString() {
		return suit.getSuit() + "-" + rank.getLabel();
	}

	private static final List<Card> protoDeck = new ArrayList<Card>();

	// Initialize prototype deck
	static {
		for (Suit suit : Suit.values())
			for (Rank rank : Rank.values())
				protoDeck.add(new Card(rank, suit));
	}

	public static ArrayList<Card> newDeck() {
		return new ArrayList<Card>(protoDeck); // Return copy of prototype deck
	}

	public String getLabel() {
		return rank.getLabel() ;
	}

	public int getStrength() {
		return rank.getStrength();
	}

	public String getSuit() {
		return suit.getSuit();
	}
}
