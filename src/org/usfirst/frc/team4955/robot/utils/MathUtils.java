package org.usfirst.frc.team4955.robot.utils;

public class MathUtils {

	public static boolean equalEpsilon(int a, int b, int epsilon) {
		return Math.abs(a - b) < epsilon;
	}

	public static boolean equalEpsilon(double a, double b, double epsilon) {
		return Math.abs(a - b) < epsilon;
	}

	public static String formatInch(double inchs) {
		return String.format("%d' %d\"", (int) (inchs / 12), (int) (inchs % 12));
	}
}
