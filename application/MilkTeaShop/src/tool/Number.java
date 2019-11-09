package tool;

import java.util.Random;

public class Number {
	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static int random(int max) {
		return new Random().nextInt(max) + 1;
	}
}
