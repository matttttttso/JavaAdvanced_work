package p14_work1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
	Map<String, String> data = new HashMap<>();

	public String getName() {
		return this.data.get(Main.MAP_KEY_NAME);
	}

	public int getHandNum() {
		return Integer.parseInt(this.data.get(Main.MAP_KEY_HAND_NUM));
	}
	public void setHandNum(List<Card> handPlayer) {
		this.data.put(Main.MAP_KEY_HAND_NUM, String.valueOf(handPlayer.size()));
	}

	public int getGotNum() {
		return Integer.parseInt(this.data.get(Main.MAP_KEY_GOT_NUM));
	}
	public void setGotNum(int gotNum) {
		this.data.put(Main.MAP_KEY_GOT_NUM, String.valueOf(gotNum));
	}

	public int getWinCount() {
		return Integer.parseInt(this.data.get(Main.MAP_KEY_WIN_NUM));
	}
	public void setWinCount(int winCount) {
		this.data.put(Main.MAP_KEY_WIN_NUM, String.valueOf(winCount));
	}

	Player(String name, List<Card> handPlayer) {
		this.data.put(Main.MAP_KEY_NAME, name);
		this.data.put(Main.MAP_KEY_HAND_NUM, String.valueOf(handPlayer.size()));
		this.data.put(Main.MAP_KEY_GOT_NUM, String.valueOf(Main.ZERO));
		this.data.put(Main.MAP_KEY_WIN_NUM, String.valueOf(Main.ZERO));
	}
}
