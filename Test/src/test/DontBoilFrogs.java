package test;

interface FrogBoilable {

	static int getCtoF(int cTemp) { // interface static method
		return (cTemp * 9 / 5) + 32;

	}

	default String hop() { // interface default method
		return "hopping";
	}

}

public class DontBoilFrogs implements FrogBoilable {
	public static void main(String[] args) {
		new DontBoilFrogs().go();
	}

	void go() {

		System.out.println(hop()); // 1 - ok for default m

		// System.out.println(getCtoF(100) ) ; // 2 - cannot find symbol

		System.out.println(FrogBoilable.getCtoF(100)); // 3 - ok for static m
		DontBoilFrogs dbf = new DontBoilFrogs();

		// System.out.println(dbf.getCtoF(100)); // 4 - cannot find symbol

	}
}