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
		return "社員番号:" + num + "社員名:" + name + "年齢:" + age;
	}
}