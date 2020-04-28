import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

public class Main_10_6 {
	private static final String PROPERTIES_FILE = "c:\\Users\\image\\Desktop\\p10-6_work1_output.properties";

	public static void main(String[] args) {
		Properties properties = new Properties();
		Scanner input = new Scanner(System.in);
		int sum = 0;
		int count = 0;
		int intInput = 0;
		String stringInput = "";
		final String QUIT = "q",
				REGEX = "[q]|([\\d]+)|-[\\d]+";

		try {
			Writer output = new FileWriter(PROPERTIES_FILE);
			while(true) {
				try {
					System.out.print("[数値データを入力してください：(終了：q) ]>");
					stringInput = input.next(REGEX);
					System.out.println("入力したのは/" + stringInput + "/です。");
				} catch (NoSuchElementException e) {
					System.out.println("数字以外の文字が入力されたため、強制終了しました。");
					System.exit(1);
				}
				if(stringInput.equals(QUIT)) {
					System.out.println("正常に処理が終了しました。入力された値の合計は、" + sum + "となりました。");
					break;
				}else if(!stringInput.equals(QUIT)) {
					try {
						intInput = Integer.parseInt(stringInput);
						checkInputInt(intInput);
						sum += intInput;
						count++;
						properties.setProperty("id", Integer.toString(count));
						properties.setProperty("sum", stringInput);
						System.out.println("正しいデータ" + stringInput + "が入力されました。");
						System.out.println("今の合計は、" + sum + "です。");
					} catch (NumberOutOfBoundException e) {
						System.out.println(e.getMessage());
					} catch (NumberFormatException e) {
						System.out.println("数字以外の文字" + e +"が入力されたため、強制終了しました。");
						System.exit(1);
					}
				}
			}
			properties.store(output, "プロパティファイルのテスト");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			input.close();
		}
	}

	public static void checkInputInt(int input) throws NumberOutOfBoundException {
		if (input < 1 || 10 < input) {
			throw new NumberOutOfBoundException("数値データは1〜10の範囲で入力してください。");
		}
	}

	public String toString() {
		return null;

	}
}