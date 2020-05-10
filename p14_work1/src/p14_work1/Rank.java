package p14_work1;

//カードの数字のenum
public enum Rank {
	//(表示名[label], 強さ[strength])
	DEUCE("2", 2),
	TREY("3", 3),
	CATER("4", 4),
	CINQUE("5", 5),
	SICE("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 11),
	QUEEN("Q", 12),
	KING("K", 13),
	ACE("A", 100);	//エースが一番強いように設定

	private String	label;	//表示名
	private int		strength;	//強さ

	//getter
	//表示名
	public String getLabel() {
		return label;
	}
	//強さ
	public int getStrength() {
		return strength;
	}

	//コンストラクタ
	Rank(String label, int strength){
		this.label = label;
		this.strength = strength;
	}
}
