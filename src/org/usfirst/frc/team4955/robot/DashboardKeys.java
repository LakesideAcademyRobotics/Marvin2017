package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardKeys {

	public static String VISION_DISTANCE = "Target distance";

	public static String	BALL_TROWING	= "Ball throwing";
	public static String	BALL_PICKUP		= "Ball pickup";
	public static String	WINCH_ACTIVE	= "Winch Active";

	public static String	DRIVE	= "Drive";
	public static String	GYRO	= "Gyro";
	public static String	PICKUP	= "Pickup";
	public static String	THROWER	= "Thrower";
	public static String	WINCH	= "Winch";

	public static String AUTONOMOUS_STATUS = "Autonomous status";

	public static void init() {
		SmartDashboard.putBoolean(GYRO, false);

		// Mecanism active
		SmartDashboard.putBoolean(BALL_TROWING, false);
		SmartDashboard.putBoolean(BALL_PICKUP, false);
		SmartDashboard.putBoolean(WINCH_ACTIVE, false);

		SmartDashboard.putString(AUTONOMOUS_STATUS, "");
	}
}
