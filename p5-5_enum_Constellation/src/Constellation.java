import java.time.DateTimeException;
import java.time.MonthDay;

public enum Constellation {
	Aries("牡羊座", 3, 21, 4, 19),
	Taurus("牡牛座", 4, 20, 5, 20),
	Gemini("双子座", 5, 21, 6, 21),
	Cancer("蟹座", 6, 22, 7,22),
	Leo("獅子座", 7, 23, 8, 22),
	Virgo("乙女座", 8, 23, 9, 22),
	Libra("天秤座", 9, 23, 10, 23),
	Scorpius("蠍座", 10, 24, 11, 22),
	Sagittarius("射手座", 11, 23, 12, 21),
	Capricornus("山羊座", 12, 22, 1, 19) {
		boolean contains(MonthDay target) { //年をまたがるのでこれだけ式が違う
			return start.compareTo(target) <= 0 || target.compareTo(end) <= 0;
		}
	},
	Aquarius("水瓶座", 1, 20, 2, 18),
	Pisces("魚座", 2, 19, 3, 20);

	private String jname;
	protected MonthDay start, end;

	Constellation(String jname, int startMonth, int startDay, int endMonth, int endDay) {
		this.jname = jname;
		this.start = MonthDay.of(startMonth, startDay);
		this.end = MonthDay.of(endMonth, endDay);
	}

	public String getJname() {
		return jname;
	}

	boolean contains(MonthDay target) {
		return start.compareTo(target) <= 0 && target.compareTo(end) <= 0;
	}

	static Constellation getType(MonthDay target) {
		for(Constellation z : values()) {
			if(z.contains(target)) {
				return z;
			}
		}
		throw new DateTimeException("定義に洩れがあるようです. target="+target);
	}
}

