import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main_8_5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("出力するログのレベルを入力してください。");
		System.out.println("例)`INFO`=>info");
		System.out.println("異常値入力時:既定値（INFO)");
		System.out.print(">");

		String typed = scanner.nextLine();
		switch (typed) {
			case "trace":
				System.setProperty("org.slf4j.simpleLogger.log.Main_8_5", "TRACE");
				break;
			case "debug":
				System.setProperty("org.slf4j.simpleLogger.log.Main_8_5", "DEBUG");
				break;
			case "info":
				System.setProperty("org.slf4j.simpleLogger.log.Main_8_5", "INFO");
				break;
			case "warn":
				System.setProperty("org.slf4j.simpleLogger.log.Main_8_5", "WARN");
				break;
			case "error":
				System.setProperty("org.slf4j.simpleLogger.log.Main_8_5", "ERROR");
				break;
			default:
				// 意図しない文字列が入力されたときは何もしない
				break;
		}

		Logger logger = LoggerFactory.getLogger(Main_8_5.class);
		logger.trace("トレースレベルログ");
		logger.debug("デバッグレベルログ");
		logger.info("情報レベルログ");
		logger.warn("ワーニングレベルログ");
		logger.error("エラーレベルログ");

		scanner.close();
	}
}