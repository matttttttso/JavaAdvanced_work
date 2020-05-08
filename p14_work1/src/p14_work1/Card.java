package p14_work1;

public class Card {
	//フィールドメンバ
	private String	suit,	//マークは２つ「スペード」 「ダイヤ」
					num;
	private int	cardInt;	//数字1~26 (2*13)
	public final String	ACE = "A",
						JACK = "J",
						QUEEN = "Q",
						KING = "K";

	public int getCardInt() {
		return cardInt;
	}
	//コンストラクタ
	Card(int cardInt){
		//数字と数字の表記の決定
		this.cardInt = cardInt;
		double cardDouble = cardInt;
		this.num = String.valueOf(Math.ceil((cardDouble / 2) + 1));
		switch(this.num) {
			case "14":
				this.num = ACE;
				break;
			case "11":
				this.num = JACK;
				break;
			case "12":
				this.num = QUEEN;
				break;
			case "13":
				this.num = KING;
				break;
			default:
				this.num = String.valueOf(this.num);
				break;
		}
		//suitの決定
		int mod = cardInt % 2;
		switch(mod) {
			case 0:
				this.suit = "♠";
				break;
			case 1:
				this.suit = "♦";
				break;
		}
	}
	/*
	2が一番弱く、そこから数字が高い方が強くなり、Aは最も強いカードです。マークは関係なし

	トランプの表し方
	 数	♦ ♠
	2：	1,2
	3:	3,4
	 ...
	Q:	21,22
	K:	23,24
	A:	25,26

	2で割ったあまり
	 	1,0

	2で割って+1
 	2:	1.5 ~ 2.0
 	3:	2.5 ~ 3.0
 	 ...
 	Q:	11.5 ~ 12.0
	K:	12.5 ~ 13.0
	A:	13.5 ~ 14.0
	*/

	//メソッドメンバ
	//カードの表示
	public String toString() {
		return this.suit + "/" + this.num;
	}
}

