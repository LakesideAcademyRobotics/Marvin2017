package org.usfirst.frc.team4955.robot.utils.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmarterDashboard {

	public static double multiplyNumber(String key, double factor) {
		double v = SmartDashboard.getNumber(key, 0);
		SmartDashboard.putNumber(key, v * factor);

		return v * factor;
	}

	public static double addNumber(String key, double defaultValue, double factor) {
		double v = SmartDashboard.getNumber(key, defaultValue);
		SmartDashboard.putNumber(key, v + factor);

		return v + factor;
	}

	public static double clampNumber(String key, double defaultValue, double lower, double upper) {
		double v = SmartDashboard.getNumber(key, defaultValue);
		if (v > upper)
			v = upper;
		else if (v < lower)
			v = lower;
		SmartDashboard.putNumber(key, v);

		return v;
	}
}
