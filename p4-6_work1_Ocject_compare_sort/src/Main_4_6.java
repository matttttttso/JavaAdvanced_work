import java.util.ArrayList;
import java.util.Comparator;

public class Main_4_6 {
	public static void main(String args[]) {
		Employee e1 = new Employee(1, "田中 太郎", 20);
		Employee e2 = new Employee(2, "鈴木 次郎", 40);
		Employee e3 = new Employee(3, "山田 花子", 19);
		Employee e4 = new Employee(4, "橋本 士郎", 31);
		Employee e5 = new Employee(5, "高橋 五朗", 54);
		Employee e6 = new Employee(6, "吉田 歩", 22);
		Employee e7 = new Employee(7, "和田 花", 30);
		Employee e8 = new Employee(8, "山崎 蓮", 28);
		Employee e9 = new Employee(9, "三浦 一美", 33);

		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
		employees.add(e5);
		employees.add(e6);
		employees.add(e7);
		employees.add(e8);
		employees.add(e9);

		Comparator<Employee> comparatorAge = new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return Integer.compare(o1.getAge(), o2.getAge());
			}
		};

		employees.sort(comparatorAge);

		for (Employee Employee : employees) {
			System.out.println(Employee.toString());
		}
	}
}
