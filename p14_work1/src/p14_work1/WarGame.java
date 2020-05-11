package p14_work1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WarGame {
	public static final String NEW_LINE = System.lineSeparator();
	//ファイルの場所
	private static final String FILE_PLACE = System.getProperty("user.home") + "/Desktop/game_result.csv";
	//定数
	static final int	NUM_OF_PLAYER = 2,	//プレイヤー数
						ZERO = 0,
						CARD_PUT_OUT = 0,
						INITIAL_VALUE = 0;
	static final String	PLAY = "p",
						BREAK = "b",
						YES = "y",
						NO = "n";
	static Scanner scanner = new Scanner(System.in);

//main
	public static void main(String[] args) {
	//フィールドメンバ	ここから↓
		final String	NAME_PLAYER_1 = "あなた",
						NAME_PLAYER_2 = "CPU";
		final int		COUNT_PER_GAME = 13;	//1ゲームあたり13ラウンド(13回戦)
		int	fieldStockCount = 0,	//場に積まれた札数
			roundCount = 0;			//ラウンド数
	//フィールドメンバ	ここまで↑

	//プログラム記述		ここから↓
		//CSV読み込み処理
		choiceRestart();
		//デッキ作成、シャッフル
		List<Card> deck = Deck.newDeck();
		Collections.shuffle(deck);

		int cardsPerHand = deck.size() / NUM_OF_PLAYER;			//最初に配る手札の枚数決定
		List<Card> handPlayer1 = Deck.deal(deck, cardsPerHand);	//プレイヤー１の手札作成
		List<Card> handPlayer2 = Deck.deal(deck, cardsPerHand);	//プレイヤー２の手札作成

		Player player1 = new Player(NAME_PLAYER_1, handPlayer1);
		Player player2 = new Player(NAME_PLAYER_2, handPlayer2);
		ROUND_LOOP: while(roundCount <= COUNT_PER_GAME) {
			roundCount++;
			printLines(roundCount, fieldStockCount, player1, player2);
			judge(fieldStockCount, player1, player2);
			player1.getHand().remove(CARD_PUT_OUT);
			player2.getHand().remove(CARD_PUT_OUT);
		}
	;
	//プログラム記述		ここまで↑
	}

//メソッドメンバゾーン	ここから↓
	//CSV読み込み、ゲーム再開か新規ゲームか
	static void choiceRestart() {
		List<String> csvHeader = new ArrayList<String>();
		csvHeader.add(0, "ゲーム数");
		csvHeader.add(1, "勝利数");
		csvHeader.add(2, "最大獲得カード数");
		List<String> readData = new ArrayList<String>();
		File gameResult = new File(FILE_PLACE);
		int 	gameCount = 0,	//ゲーム数
				winCount = 0,	//勝利数
				maxGotNum = 0;	//最大獲得カード数
		if(gameResult.exists()) {
			System.out.printf("中断したゲームを再開しますか? [%s/%s] >", YES, NO);
			String choiceRestart = scanner.next().toLowerCase();
			if(choiceRestart.equals(YES)) {
				try (
						BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PLACE));
					) {
					for(int rowNum = 0; rowNum < 2; rowNum++) {
						String line = reader.readLine();
						if(rowNum == 0) {
							csvHeader = Arrays.asList(line.split(","));
						} else if(rowNum == 1) {
							readData = Arrays.asList(line.split(","));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("中断したゲームを再開します。");
				gameCount = Integer.parseInt(readData.get(0));
				winCount = Integer.parseInt(readData.get(1));
				maxGotNum = Integer.parseInt(readData.get(2));
			} else if(choiceRestart.equals(NO)) {
				System.out.println("新しくゲームを開始します。");
				gameCount = INITIAL_VALUE;
				winCount = INITIAL_VALUE;
				maxGotNum = INITIAL_VALUE;
			}
		} else {
			System.out.println("新規ゲーム開始。");
			gameCount = INITIAL_VALUE;
			winCount = INITIAL_VALUE;
			maxGotNum = INITIAL_VALUE;
		}
		System.out.printf("[%s]:%d,%n[%s]:%d,%n[%s]:%d%n", csvHeader.get(0), gameCount, csvHeader.get(1), winCount, csvHeader.get(2), maxGotNum);
	}
	//文字列の表示
	static void printLines(int roundCount, int fieldStockCount, Player player1, Player player2) {
		System.out.print(NEW_LINE);
		System.out.println("### 第" + roundCount + "回戦 ###");
		System.out.println("\t場に積まれた札:\t" + fieldStockCount + "枚");
		System.out.printf("\t[%s]\t手札枚数: %s枚,\t獲得枚数: %s枚%n",player2.getName(), player2.getHandNum(), player2.getGotNum());
		System.out.printf("\t[%s]\t手札枚数: %s枚,\t獲得枚数: %s枚%n",player1.getName(), player1.getHandNum(), player1.getGotNum());
		System.out.print(NEW_LINE);
		System.out.printf("\t勝負しますか?\t[ '%s':勝負  || '%s':中断 ] >", PLAY, BREAK);
		checkChoicePlayGame();
		System.out.printf("\t[%s の札]\t: [ %s ]%n", player2.getName(), player2.getHand().get(CARD_PUT_OUT));
		System.out.printf("\t[%s の札]\t: [ %s ]%n", player1.getName(), player1.getHand().get(CARD_PUT_OUT));
	}
	//勝敗判定
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
	//入力文字確認
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
			throw new InputOutOfBoundException("'" + PLAY + "'か'" + BREAK + "'で入力してください");
		}
	}
//例外処理ゾーン	ここまで↑
}
