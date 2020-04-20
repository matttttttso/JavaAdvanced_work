import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main_2_3 {
	public static void main(String[] args) {
		int year;
		int month;

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd日 (E) ");

		while(true) {
			try {
				System.out.print("カレンダーの年を入力してください >");
				year = new Scanner(System.in).nextInt();
				checkYear(year);
				break;
			} catch (NumberOutOfBoundException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("半角数字で入力してください。");
			}
		}
		while(true) {
			try {
				System.out.print("カレンダーの月を入力してください >");
				month = new Scanner(System.in).nextInt();
				checkMonth(month);
				break;
			} catch (NumberOutOfBoundException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("半角数字で入力してください。");
			}
		}

		int day = 1;
		System.out.println(year + "年 " + month + "月 のカレンダー");
		LocalDate date = LocalDate.of(year, month, day);

		for(int i = day; i < date.lengthOfMonth() + 1 ; i++) {
			date = LocalDate.of(year, month, i);
			DayOfWeek week = date.getDayOfWeek();
			if(week.getValue() != 6) {
				System.out.print(dateTimeFormatter.format(date));
			}else if(week.getValue() == 6) {
				System.out.println(dateTimeFormatter.format(date));
			}
		}
		System.out.println("\n------------");

		/*
		System.out.println(date);	//表示形式：2020-04-01
		System.out.println(date.getDayOfWeek());	//表示形式：WEDNWSDAY
		System.out.println(date.getMonthValue());	//表示形式：4
		System.out.println(date.lengthOfMonth());	//表示形式：30
		System.out.println(dateTimeFormatter.format(date));	//表示形式：("d日 (E)")
		System.out.println(week.getValue());		//表示形式：3
		 */
	}

	public static void checkYear(int input) throws NumberOutOfBoundException{
		if(input < 1900 || 2100 < input) {
			throw new NumberOutOfBoundException("1900~2100の間で入力してください。");
		}
	}
	public static void checkMonth(int input) throws NumberOutOfBoundException{
		if(input < 1 || 12 < input) {
			throw new NumberOutOfBoundException("1~12の間で入力してください。");
		}
	}
}
