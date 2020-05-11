package p14_work1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WarGame {
	//定数
	static final int	NUM_OF_PLAYER = 2,	//プレイヤー数
						ZERO = 0;
	public static final String	MAP_KEY_NAME = "プレイヤー名",
								MAP_KEY_HAND_NUM = "手札枚数",
								MAP_KEY_GOT_NUM = "獲得枚数",
								MAP_KEY_WIN_NUM = "勝利回数";
	//スキャナー
	static Scanner scanner = new Scanner(System.in);

//main
	public static void main(String[] args) {
	//フィールドメンバ	ここから↓
		final String	NAME_PLAYER_1 = "あなた",
						NAME_PLAYER_2 = "CPU";
		final int		COUNT_PER_GAME = 13;	//1ゲームあたり13ラウンド(13回戦)

		int	fieldStockCount = 0,	//場に積まれた札数
			roundCount = 0,			//ラウンド数
			winCountPlayer1,		//プレイヤー１の勝利数
			winCountPlayer2,		//プレイヤー２の勝利数
			gameCount;				//ゲーム（試合）を終了した数
	//フィールドメンバ	ここまで↑

	//プログラム記述		ここから↓
		List<Card> deck = Card.newDeck();
		Collections.shuffle(deck);

		int cardsPerHand = deck.size() / NUM_OF_PLAYER;		//デッキの枚数を人数で割る
		List<Card> handPlayer1 = deal(deck, cardsPerHand);	//プレイヤー１の手札作成
		List<Card> handPlayer2 = deal(deck, cardsPerHand);	//プレイヤー２の手札作成

		Player player1 = new Player(NAME_PLAYER_1, handPlayer1);
		Player player2 = new Player(NAME_PLAYER_2, handPlayer2);

		while(roundCount <= COUNT_PER_GAME) {
			roundCount++;
			printLines(roundCount, fieldStockCount, player1, player2);
			judge(fieldStockCount, player1, player2);
			handPlayer1.remove(0);
			handPlayer2.remove(0);
			player1.setHandNum(handPlayer1);
			player2.setHandNum(handPlayer2);
		}
		scanner.close();
	//プログラム記述		ここまで↑
	}

//メソッドメンバゾーン　ここから↓
	static ArrayList<Card> deal(List<Card> deck, int n) {
		int deckSize = deck.size();
		List<Card> handView = deck.subList(deckSize - n, deckSize);
		ArrayList<Card> hand = new ArrayList<Card>(handView);
		handView.clear();
		return hand;
	}

	static void printLines(int roundCount, int fieldStockCount, Player player1, Player player2) {
		System.out.println("\n\t### 第" + roundCount + "回戦 ###");
		System.out.println("\t場に積まれた札:\t" + fieldStockCount + "枚");
		System.out.printf("\t[%s]\t%s: %s枚,\t%s: %s枚\n",
				player2.getName(), MAP_KEY_HAND_NUM, player2.getHandNum(), MAP_KEY_GOT_NUM, player2.getGotNum());
		System.out.printf("\t[%s]\t%s: %s枚,\t%s: %s枚\n",
				player1.getName(), MAP_KEY_HAND_NUM, player1.getHandNum(), MAP_KEY_GOT_NUM, player1.getGotNum());
		System.out.print("\t札を切りますか?\t[ 'd':札を切る || 'q':中断 ] >");
		scanner.next();
		System.out.printf("\t[%s の札]\t: [ %s ]\n", player2.getName(), player2.getHand().get(0));
		System.out.printf("\t[%s の札]\t: [ %s ]\n", player1.getName(), player1.getHand().get(0));
	}

	static void judge(int fieldStockCount, Player player1, Player player2) {
		boolean winPlayer1 = (player1.getHand().get(0).getStrength() > player2.getHand().get(0).getStrength());
		boolean winPlayer2 = (player2.getHand().get(0).getStrength() > player1.getHand().get(0).getStrength());
		boolean draw = (player1.getHand().get(0).getStrength() == player2.getHand().get(0).getStrength());
		fieldStockCount += NUM_OF_PLAYER;
		if(winPlayer1) {
			System.out.printf("\n\t[%s]が獲得しました！\n", player1.getName());
			player1.setGotNum(player1.getGotNum() + fieldStockCount);
			fieldStockCount = 0;
		}else if(winPlayer2) {
			System.out.printf("\n\t[%s]が獲得しました！\n", player2.getName());
			player2.setGotNum(player2.getGotNum() + fieldStockCount);
			fieldStockCount = 0;
		}else if(draw) {
			System.out.println("\n引き分けです。");
		}
	}
//メソッドメンバゾーン	ここまで↑

	//例外処理ゾーン	ここから↓

	//例外処理ゾーン	ここまで↑
}
