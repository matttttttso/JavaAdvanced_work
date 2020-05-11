package p14_work1;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WarGame {
	//定数
	static final int	NUM_OF_PLAYER = 2,	//プレイヤー数
						ZERO = 0,
						CARD_PUT_OUT = 0;
	static final String	PLAY = "p",
						BREAK = "b";
	public static final String NEW_LINE = System.lineSeparator();
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
		List<Card> deck = Deck.newDeck();
		Collections.shuffle(deck);

		int cardsPerHand = deck.size() / NUM_OF_PLAYER;			//最初に配る手札の枚数決定
		List<Card> handPlayer1 = Deck.deal(deck, cardsPerHand);	//プレイヤー１の手札作成
		List<Card> handPlayer2 = Deck.deal(deck, cardsPerHand);	//プレイヤー２の手札作成

		Player player1 = new Player(NAME_PLAYER_1, handPlayer1);
		Player player2 = new Player(NAME_PLAYER_2, handPlayer2);

		while(roundCount <= COUNT_PER_GAME) {
			roundCount++;
			printLines(roundCount, fieldStockCount, player1, player2);
			judge(fieldStockCount, player1, player2);
			player1.getHand().remove(CARD_PUT_OUT);
			player2.getHand().remove(CARD_PUT_OUT);
		}
		scanner.close();
	//プログラム記述		ここまで↑
	}

//メソッドメンバゾーン　ここから↓


	static void printLines(int roundCount, int fieldStockCount, Player player1, Player player2) {
		System.out.print(NEW_LINE);
		System.out.println("### 第" + roundCount + "回戦 ###");
		System.out.println("\t場に積まれた札:\t" + fieldStockCount + "枚");
		System.out.printf("\t[%s]\t手札枚数: %s枚,\t獲得枚数: %s枚%n",
				player2.getName(), player2.getHandNum(), player2.getGotNum());
		System.out.printf("\t[%s]\t手札枚数: %s枚,\t獲得枚数: %s枚%n",
				player1.getName(), player1.getHandNum(), player1.getGotNum());
		System.out.print(NEW_LINE);
		System.out.print("\t勝負しますか?\t[ '" + PLAY +"':勝負  || '" + BREAK + "':中断 ] >");
		checkChoicePlayGame();
		System.out.printf("\t[%s の札]\t: [ %s ]%n", player2.getName(), player2.getHand().get(CARD_PUT_OUT));
		System.out.printf("\t[%s の札]\t: [ %s ]%n", player1.getName(), player1.getHand().get(CARD_PUT_OUT));
	}

	static void judge(int fieldStockCount, Player player1, Player player2) {
		boolean winPlayer1 = (player1.getHand().get(CARD_PUT_OUT).getStrength() > player2.getHand().get(CARD_PUT_OUT).getStrength());
		boolean winPlayer2 = (player2.getHand().get(CARD_PUT_OUT).getStrength() > player1.getHand().get(CARD_PUT_OUT).getStrength());
		boolean draw = (player1.getHand().get(CARD_PUT_OUT).getStrength() == player2.getHand().get(CARD_PUT_OUT).getStrength());
		fieldStockCount += NUM_OF_PLAYER;
		System.out.print(NEW_LINE);
		if(winPlayer1) {
			System.out.printf("[%s]が獲得しました！%n", player1.getName());
			player1.setGotNum(player1.getGotNum() + fieldStockCount);
			fieldStockCount = ZERO;
		}else if(winPlayer2) {
			System.out.printf("[%s]が獲得しました！%n", player2.getName());
			player2.setGotNum(player2.getGotNum() + fieldStockCount);
			fieldStockCount = ZERO;
		}else if(draw) {
			System.out.println("引き分けです。");
		}
	}
//メソッドメンバゾーン	ここまで↑

	//例外処理ゾーン	ここから↓
	static void checkChoicePlayGame() {
		INPUT_LOOP: while(true) {
			try {
				String input = scanner.next().toLowerCase();
				checkChoice(input);
				break INPUT_LOOP;
			} catch (InputOutOfBoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public static void checkChoice(String input) throws InputOutOfBoundException {
		if (!input.equals(PLAY) && !input.equals(BREAK)) {
			throw new InputOutOfBoundException("'" + PLAY + "'あるいは'" + BREAK + "'で入力してください");
		}
	}
	//例外処理ゾーン	ここまで↑
}
