public class Employee {
	private int num;
	private String name;
	private int age;

	public Employee(int num, String name, int age) {
		this.num = num;
		this.name = name;
		this.age = age;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "社員番号： \t" + num + "\t 社員名： \t\t" + name + "\t\t 年齢：" + age;
	}
}