import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main_5_5 {
	public static void main(String[] args) {
		int year;
		int month;
		int day;
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日");

		System.out.println("************星座判定プログラム************");
		System.out.print("誕生年を入力してください >");
		year = scanner.nextInt();
		System.out.print("誕生月を入力してください >");
		month = scanner.nextInt();
		System.out.print("誕生日を入力してください >");
		day = scanner.nextInt();

		MonthDay monthDay = MonthDay.of(month, day);
		LocalDate date = LocalDate.of(year, month, day);

		Constellation constellation = Constellation.getType(monthDay);
		System.out.println(dateTimeFormatter.format(date) + "生まれのあなたの星座は、" + constellation.getJname() + "です。");
		scanner.close();
	}
}
