package p14_work1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
	Map<String, String> data = new HashMap<>();
	List<Card> hand;

	//getter
	//プレイヤー名
	public String getName() {
		return this.data.get(WarGame.MAP_KEY_NAME);
	}
	//手札数
	public int getHandNum() {
		return Integer.parseInt(this.data.get(WarGame.MAP_KEY_HAND_NUM));
	}
	//獲得数
	public int getGotNum() {
		return Integer.parseInt(this.data.get(WarGame.MAP_KEY_GOT_NUM));
	}
	//勝利数
	public int getWinCount() {
		return Integer.parseInt(this.data.get(WarGame.MAP_KEY_WIN_NUM));
	}
	//手札
	public List<Card> getHand() {
		return this.hand;
	}

	//setter
	//手札数
	public void setHandNum(List<Card> handPlayer) {
		this.data.put(WarGame.MAP_KEY_HAND_NUM, String.valueOf(handPlayer.size()));
	}
	//獲得数
	public void setGotNum(int gotNum) {
		this.data.put(WarGame.MAP_KEY_GOT_NUM, String.valueOf(gotNum));
	}
	//勝利数
	public void setWinCount(int winCount) {
		this.data.put(WarGame.MAP_KEY_WIN_NUM, String.valueOf(winCount));
	}

	//コンストラクタ(名前、手札)
	//初期化
	Player(String name, List<Card> handPlayer) {
		this.data.put(WarGame.MAP_KEY_NAME, name);
		this.data.put(WarGame.MAP_KEY_HAND_NUM, String.valueOf(handPlayer.size()));
		this.data.put(WarGame.MAP_KEY_GOT_NUM, String.valueOf(WarGame.ZERO));	//獲得枚数0
		this.data.put(WarGame.MAP_KEY_WIN_NUM, String.valueOf(WarGame.ZERO));	//勝利数。。。
		this.hand = handPlayer;
	}
}
