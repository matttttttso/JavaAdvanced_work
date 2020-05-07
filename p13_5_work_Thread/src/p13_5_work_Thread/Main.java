package p13_5_work_Thread;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Thread showThread = new ShowThread();

		System.out.print("何か文字を入力してください！>");
		showThread.start();
		String str = scan.next();
		System.out.print("入力文字は " + str + "です。 ");
		scan.close();
	}
}
