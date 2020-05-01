import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main_12_7 {
	public static void main(String[] args) {
		final String QUIT = "q";
		final int ZERO = 0;
		String input;
		String pnq;
		Scanner scanner = new Scanner(System.in);
		int indexRow = ZERO;


		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (
			Connection connection = DriverManager.getConnection("jdbc:h2:~\\exercisedb", "sa", "");
			) {
			connection.setAutoCommit(false);

			try (
					Statement stmt = connection.createStatement()) {
				String sql = "CREATE TABLE"
						+ " IF NOT EXISTS ZIP_CODE"
						+ " AS SELECT *"
						+ " FROM CSVREAD('~\\Documents\\zipcode\\zip_data_split_1.csv', null, 'UTF-8', ',')";
				stmt.executeUpdate(sql);

				sql = "INSERT INTO ZIP_CODE SELECT *"
						+ " FROM CSVREAD('~\\Documents\\zipcode\\zip_data_split_2.csv', null, 'UTF-8', ',')"
						+ " AS TMP WHERE NOT EXISTS(SELECT * FROM ZIP_CODE WHERE ZIP_CODE = '9570036')";
				stmt.executeUpdate(sql);

				sql = "INSERT INTO ZIP_CODE SELECT *"
						+ " FROM CSVREAD('~\\Documents\\zipcode\\zip_data_split_3.csv', null, 'UTF-8', ',')"
						+ " AS TMP WHERE NOT EXISTS(SELECT * FROM ZIP_CODE WHERE ZIP_CODE = '6750023')";
				stmt.executeUpdate(sql);

				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				throw e;
			}
			System.out.println("テーブルの作成、レコードの追加が完了しました。");

			LOOP:while (true) {
				printMenu();
				INPUT:while(true) {
					System.out.print("メニューを選択してください。>");
					input = scanner.next().toLowerCase();
					try {
						checkInput(input);
						break INPUT;
					} catch (InputOutOfBoundException e) {
						System.out.println(e.getMessage());
					}
				}
				SWITCH:switch (input) {
				case "c":
					break SWITCH;
				case "a":
					System.out.println("全件検索 ----------------------------------------");
					break SWITCH;
				case "s":
					System.out.println("郵便番号検索 -------------------------------------");
					break SWITCH;
				case "t":
					System.out.println("都道府県名・市町村名検索----------------------------");
					break SWITCH;
				case QUIT:
					System.out.println("\nEND");
					break SWITCH;
				}
				if (input.equals(QUIT)) {
					break LOOP;
				}
			}
			scanner.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void printMenu() {
		System.out.println("*************************************");
		System.out.println("*			郵便番号検索メニュー			*");
		System.out.println("* [C] 新規 郵便番号テーブル作成&データ追加	*");
		System.out.println("* [A] 郵便番号全件検索					*");
		System.out.println("* [S] 条件検索 (郵便番号)				*");
		System.out.println("* [T] 条件検索 (都道府県、市町村)		*");
		System.out.println("* [Q] 終了							*");
		System.out.println("*************************************");
	}

	public static void checkInput(String input) throws InputOutOfBoundException {
		if (!input.equals("c") && !input.equals("a") && !input.equals("s") && !input.equals("t") && !input.equals("q")) {
			throw new InputOutOfBoundException("指定された文字で入力してください。");
		}
	}
}
