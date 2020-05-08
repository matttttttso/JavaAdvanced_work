package p14_work1;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private final int NUMBER_OF_DECK = 26;			//１デッキのカードの枚数

	ArrayList<Integer> deck = new ArrayList<>();

	//行列に1~26の数字を順に入れていく
	public void setDeck() {
		for(int i = 0; i < NUMBER_OF_DECK; i++) {
			deck.add(i + 1);
		}
	}

	//シャッフルする
	public void shuffle() {
		for(int i = 0; i < deck.length; i++) {
			Random random = new Random();
			int randNum = random.nextInt(NUMBER_OF_DECK);
			int tmp = deck[i];
			deck[i] = deck[randNum];
			deck[randNum] = tmp;
		}
	}

	//デッキ内容表示（動作確認用）
	public void showDeck() {
		int count = 0;
		for(int i = 0; i < deck.length; i++) {
			System.out.print(deck[i] + "/");
			count++;
			if(count % 10 == 0) {
				System.out.println("");
			}
		}
	}

	public int deckToInt(int i) {
		return deck[i];
	}
}
