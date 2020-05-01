import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main_12_7 {
	public static void main(String[] args) {
		final	String	QUIT = "q";
		final	int		ZERO = 0;
		int		indexRow = ZERO,
				numberOfRows = ZERO;
		String	input,
				pnq = null,
				searchZipcode,
				searchPref,
				searchCity;
		Scanner scanner = new Scanner(System.in, "UTF-8");

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
					Statement stmt = connection.createStatement()
				) {
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
			connection.setAutoCommit(true);
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
					System.out.println("\n全件検索 ----------------------------------------");
					try (
						PreparedStatement pstmt = connection.prepareStatement("SELECT ZIP_CODE, PREF, CITY, TOWN FROM ZIP_CODE LIMIT ?, 10");
						) {
						A_LOOP: while(true) {
							pstmt.setInt(1, indexRow);
							try (
								ResultSet rs = pstmt.executeQuery();
								) {
								System.out.println("郵便番号\t\t都道府県名\t\t市\t\t\t\t町名");
								while (rs.next()) {
									String zipcode = rs.getString(1);
									String pref = rs.getString(2);
									String city = rs.getString(3);
									String town = rs.getString(4);
									System.out.printf(" %7s \t %5s \t %10s \t %15s \n", zipcode, pref, city, town);
								}
								INPUT_LOOP: while(true) {
									try {
										if(indexRow == ZERO) {
											System.out.print("[次の10件を表示:n] [終了:q] >");
											pnq = scanner.next().toLowerCase();
											checkNQ(pnq);
										} else if(numberOfRows == 10 && indexRow >= 10){
											System.out.print("[前の10件を表示:p] [次の10件を表示:n] [終了:q] >");
											pnq = scanner.next().toLowerCase();
											checkPNQ(pnq);
										} else if(numberOfRows < 10 && indexRow != ZERO) {
											System.out.print("[前の10件を表示:p] [終了:q] >");
											pnq = scanner.next().toLowerCase();
											checkPQ(pnq);
										}
										break INPUT_LOOP;
									} catch (InputOutOfBoundException e) {
										System.out.println(e.getMessage());
									}
								}
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
									System.out.println("全件検索を終了し、メニューに戻ります。\n");
									break A_LOOP;
								}
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break SWITCH;
				case "s":
					System.out.println("\n郵便番号検索 -------------------------------------");
					INPUT_LOOP: while(true) {
						System.out.print("検索する郵便番号を入力してください。 [終了:q] >");
						try {
							searchZipcode = scanner.next().toLowerCase();
							checkZIPCODE(searchZipcode);
							break INPUT_LOOP;
						} catch (InputOutOfBoundException e) {
							System.out.println(e.getMessage());
						}
					}
					S_LOOP: while(true) {
						try(
							PreparedStatement pstmt = connection.prepareStatement("SELECT ZIP_CODE, PREF, CITY, TOWN FROM ZIP_CODE WHERE ZIP_CODE = ?");
							) {
							if(searchZipcode.equals(QUIT)) {
								System.out.println("条件検索を終了し、メニューに戻ります。\n");
								break S_LOOP;
							} else {
								pstmt.setString(1, searchZipcode);
							}
							try (
								ResultSet rs = pstmt.executeQuery();
								) {
								System.out.println("郵便番号\t\t都道府県名\t\t市\t\t\t\t町名");
								while (rs.next()) {
									String zipcode = rs.getString(1);
									String pref = rs.getString(2);
									String city = rs.getString(3);
									String town = rs.getString(4);
									System.out.printf(" %7s \t %5s \t %10s \t %15s \n", zipcode, pref, city, town);
								}
							}
							INPUT_LOOP2: while(true) {
								try {
									System.out.print("続けて検索しますか?検索する場合は郵便番号を、終了する場合はqを入力してください。 >");
									searchZipcode = scanner.next().toLowerCase();
									checkZIPCODE(searchZipcode);
									break INPUT_LOOP2;
								} catch (InputOutOfBoundException e) {
									System.out.println(e.getMessage());
								}
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					break SWITCH;
				case "t":
					System.out.println("\n都道府県名・市町村名検索----------------------------");
					System.out.print("検索する都道府県名を入力してください。 [終了:q] >");
					searchPref = scanner.next();
					System.out.println("入力値：" + searchPref);
					System.out.print("検索する市町村名を入力してください。 [終了:q] >");
					searchCity = scanner.next();
					System.out.println("入力値：" + searchCity);
					indexRow = 0;
					T_LOOP: while(true) {
						try(
							PreparedStatement pstmt = connection.prepareStatement("SELECT ZIP_CODE, PREF, CITY, TOWN FROM ZIP_CODE"
									+ " WHERE PREF = ? AND CITY = ? LIMIT ?,10");
							) {
							if(searchPref.equals(QUIT) || searchCity.equals(QUIT)) {
								System.out.println("条件検索を終了し、メニューに戻ります。\n");
								break T_LOOP;
							} else {
								pstmt.setString(1, searchPref);
								pstmt.setString(2, searchCity);
								pstmt.setInt(3, indexRow);
							}
							try (
								ResultSet rs = pstmt.executeQuery();
								) {
								System.out.println("郵便番号\t\t都道府県名\t\t市\t\t\t\t町名");
								while (rs.next()) {
									String zipcode = rs.getString(1);
									String pref = rs.getString(2);
									String city = rs.getString(3);
									String town = rs.getString(4);
									System.out.printf(" %7s \t %5s \t %10s \t %15s \n", zipcode, pref, city, town);
									numberOfRows = rs.getRow();
								}
							}
							if(numberOfRows == 0 && indexRow == ZERO) {
								System.out.println("見つかりませんでした。");
								System.out.println("都道府県名・市町村名検索を終了し、メニューに戻ります。\n");
								break T_LOOP;
							} else if(numberOfRows < 10 && indexRow == ZERO) {
								System.out.println("検索結果：" + numberOfRows + "件");
								System.out.println("都道府県名・市町村名検索を終了し、メニューに戻ります。\n");
								break T_LOOP;
							} else {
								INPUT_LOOP: while(true) {
									try {
										if(indexRow == ZERO) {
											System.out.print("[次の10件を表示:n] [終了:q] >");
											pnq = scanner.next().toLowerCase();
											checkNQ(pnq);
										} else if(numberOfRows == 10 && indexRow >= 10){
											System.out.print("[前の10件を表示:p] [次の10件を表示:n] [終了:q] >");
											pnq = scanner.next().toLowerCase();
											checkPNQ(pnq);
										} else if(numberOfRows < 10 && indexRow != ZERO) {
											System.out.print("[前の10件を表示:p] [終了:q] >");
											pnq = scanner.next().toLowerCase();
											checkPQ(pnq);
										}
										break INPUT_LOOP;
									} catch (InputOutOfBoundException e) {
										System.out.println(e.getMessage());
									}
								}
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
									System.out.println("都道府県名・市町村名検索を終了し、メニューに戻ります。\n");
									break T_LOOP;
								}
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
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
	public static void checkNQ(String input) throws InputOutOfBoundException {
		if (!input.equals("n") && !input.equals("q")) {
			throw new InputOutOfBoundException("指定された文字で入力してください。");
		}
	}
	public static void checkPQ(String input) throws InputOutOfBoundException {
		if (!input.equals("p") && !input.equals("q")) {
			throw new InputOutOfBoundException("指定された文字で入力してください。");
		}
	}

	public static void checkZIPCODE(String input) throws InputOutOfBoundException {
		if (!input.matches("[0-9]{7}") && !input.equals("q")) {
			throw new InputOutOfBoundException("7桁の郵便番号か指定された文字で入力してください。");
		}
	}
}
