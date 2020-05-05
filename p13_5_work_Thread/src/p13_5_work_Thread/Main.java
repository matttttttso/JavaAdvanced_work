package p13_5_work_Thread;

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		System.out.println("何か文字を入力してください！");
		Thread showThread = new ShowThread();
		showThread.start();
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		System.out.println("入力文字は " + str + " です。");
	}
}
