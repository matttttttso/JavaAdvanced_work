package p14_work1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static final int	NUM_OF_PLAYER = 2,	//プレイヤー数
							ZERO = 0;
	public static final String MAP_KEY_NAME = "プレイヤー名",
						MAP_KEY_HAND_NUM = "手札枚数",
						MAP_KEY_GOT_NUM = "獲得枚数",
						MAP_KEY_WIN_NUM = "勝利回数";
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
	//フィールドメンバ	ここから↓
		final String	NAME_PLAYER_1 = "あなた",
						NAME_PLAYER_2 = "CPU";
		int	fieldStockCount = 0,	//
			roundCount = 0,	//1ゲームあたり13ラウンド(13回戦)
			gotNumCountPlayer1 = 0,
			gotNumCountPlayer2 = 0,
			winCountPlayer1 = 0,
			winCountPlayer2 = 0,
			gameCount = 0;

	//フィールドメンバ	ここまで↑

	//プログラム記述		ここから↓
		List<Card> deck = Card.newDeck();
		int cardsPerHand = deck.size() / NUM_OF_PLAYER;
		Collections.shuffle(deck);

		List<Card> handPlayer1 = deal(deck, cardsPerHand);
		List<Card> handPlayer2 = deal(deck, cardsPerHand);

		Player player1 = new Player(NAME_PLAYER_1, handPlayer1);
		Player player2 = new Player(NAME_PLAYER_2, handPlayer2);

		while(roundCount <= 13) {
			roundCount++;
			System.out.println("\n\t### 第" + roundCount + "回戦 ###");
			System.out.println("\t場に積まれた札:\t" + fieldStockCount + "枚");
			System.out.printf("\t[%s]\t%s: %s枚,\t%s: %s枚\n",
					player2.getName(), MAP_KEY_HAND_NUM, player2.getHandNum(), MAP_KEY_GOT_NUM, player2.getGotNum());
			System.out.printf("\t[%s]\t%s: %s枚,\t%s: %s枚\n",
					player1.getName(), MAP_KEY_HAND_NUM, player1.getHandNum(), MAP_KEY_GOT_NUM, player1.getGotNum());
			System.out.print("\t札を切りますか?\t[ 'd':札を切る || 'q':中断 ] >");
			scanner.next();
			System.out.printf("\t[%s の札]\t: [ %s ]\n", player2.getName(), handPlayer2.get(0));
			System.out.printf("\t[%s の札]\t: [ %s ]\n", player1.getName(), handPlayer1.get(0));
			boolean winPlayer1 = ( handPlayer1.get(0).getStrength() > handPlayer2.get(0).getStrength() );
			boolean winPlayer2 = ( handPlayer2.get(0).getStrength() > handPlayer1.get(0).getStrength() );
			boolean draw = ( handPlayer1.get(0).getStrength() == handPlayer2.get(0).getStrength() );
			fieldStockCount += NUM_OF_PLAYER;
			if(winPlayer1) {
				System.out.printf("\t[%s]が獲得しました！\n", player1.getName());
				gotNumCountPlayer1 += fieldStockCount;
				player1.setGotNum(gotNumCountPlayer1);
				fieldStockCount = 0;
			}else if(winPlayer2) {
				System.out.printf("\t[%s]が獲得しました！\n", player2.getName());
				gotNumCountPlayer2 += fieldStockCount;
				player2.setGotNum(gotNumCountPlayer2);
				fieldStockCount = 0;
			}else if(draw) {
				System.out.println("\n引き分けです。");
			}
			handPlayer1.remove(0);
			handPlayer2.remove(0);
			player1.setHandNum(handPlayer1);
			player2.setHandNum(handPlayer2);
		}
		scanner.close();
	//プログラム記述		ここまで↑
	}

//メソッドメンバゾーン　ここから↓
	public static ArrayList<Card> deal(List<Card> deck, int n) {
		int deckSize = deck.size();
		List<Card> handView = deck.subList(deckSize - n, deckSize);
		ArrayList<Card> hand = new ArrayList<Card>(handView);
		handView.clear();
		return hand;
	}
//メソッドメンバゾーン	ここまで↑

	//例外処理ゾーン	ここから↓

	//例外処理ゾーン	ここまで↑
}
