import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

					try (
						PreparedStatement pstmt = connection.prepareStatement("SELECT ZIP_CODE, PREF, CITY, TOWN FROM ZIP_CODE LIMIT ?, 10");
						) {
						A_LOOP: while(true) {
							pstmt.setInt(1, indexRow);
							try (
								ResultSet rs = pstmt.executeQuery();
								) {
								connection.commit();
								System.out.println("郵便番号\t\t都道府県名\t\t市\t\t\t\t町名");
								while (rs.next()) {
									int zipcode = rs.getInt(1);
									String pref = rs.getString(2);
									String city = rs.getString(3);
									String town = rs.getString(4);
									System.out.printf(" %7d \t %5s \t %10s \t %15s \n", zipcode, pref, city, town);
								}
								if(indexRow == ZERO) {
									System.out.print("[次の10件を表示:n] [終了:q] >");
								}else if(indexRow >= 10){
									System.out.print("[前の10件を表示:p] [次の10件を表示:n] [終了:q] >");
								}
								pnq = scanner.next().toLowerCase();
								checkPNQ(pnq);
								PNQ: switch (pnq) {
								case "p":
									if(indexRow >= 10) {
										indexRow -= 10;
									}else if(indexRow == 0){
										System.out.println("最初のページです。");
									}
									break PNQ;
								case "n":
									indexRow += 10;
									break PNQ;
								case "q":
									System.out.print("全件検索を終了します。");
									break A_LOOP;
								}
							} catch (SQLException e) {
								connection.rollback();
								throw e;
							} catch (InputOutOfBoundException e) {
								System.out.println(e.getMessage());
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
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

	public static void checkPNQ(String input) throws InputOutOfBoundException {
		if (!input.equals("p") && !input.equals("n") && !input.equals("q")) {
			throw new InputOutOfBoundException("指定された文字で入力してください。");
		}
	}
}
