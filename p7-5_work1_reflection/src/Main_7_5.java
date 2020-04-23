import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main_7_5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("実行するクラス名を入力してください。例)クラスAの場合は、`A`と入力>");
		String input = scanner.nextLine();
		if (input.equals("A")) {
			//Classクラスのインスタンスを取得
			Class<ReflectionClassA> clazz = ReflectionClassA.class;
			//ReflectionTestクラスのインスタンスを作成（このインスタンスのメソッドを呼び出すことになる）
			ReflectionClassA reflectionClassObj = new ReflectionClassA();
			//ReflectionTestクラスに定義されているメソッドを取得
			Method[] methods = clazz.getDeclaredMethods();
			//取得したメソッドを一つずつ取り出す
			for (Method method : methods) {
				//メソッド名が「hoge」で始まっていないならcontinue
				if (!method.getName().startsWith("execute")) {
					continue;
				}
				try {
					//reflectionTestObjのメソッド実行 引数はなし
					method.invoke(reflectionClassObj, null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} else if (input.equals("B")) {
			//Classクラスのインスタンスを取得
			Class<ReflectionClassB> clazz = ReflectionClassB.class;
			//ReflectionTestクラスのインスタンスを作成（このインスタンスのメソッドを呼び出すことになる）
			ReflectionClassB reflectionClassObj = new ReflectionClassB();
			//ReflectionTestクラスに定義されているメソッドを取得
			Method[] methods = clazz.getDeclaredMethods();
			//取得したメソッドを一つずつ取り出す
			for (Method method : methods) {
				//メソッド名が「hoge」で始まっていないならcontinue
				if (!method.getName().startsWith("execute")) {
					continue;
				}
				try {
					//reflectionTestObjのメソッド実行 引数はなし
					method.invoke(reflectionClassObj, null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
