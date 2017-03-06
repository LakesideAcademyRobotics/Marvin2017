package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardKeys {

	public static String INIT_OI = "init OI";

	public static String BALL_TROWING = "Ball throwing";
	public static String BALL_PICKUP = "Ball pickup";
	public static String WINCH_ACTIVE = "Winch Active";

	public static String DRIVE = "Drive";
	public static String GYRO = "Gyro";
	public static String PICKUP = "Pickup";
	public static String THROWER = "Thrower";
	public static String WINCH = "Winch";
	public static String GYRO_VALUE = "Gyro angle";

	public static String MOVE_DISTANCE_LEFT = "MOVED LEFT";
	public static String MOVE_DISTANCE_RIGHT = "MOVED RIGHT";

	public static String AUTONOMOUS_STATUS = "Autonomous status";

	public static void init() {
		SmartDashboard.putBoolean(INIT_OI, false);

		SmartDashboard.putBoolean(GYRO, false);

		// Mecanism active
		SmartDashboard.putBoolean(BALL_TROWING, false);
		SmartDashboard.putBoolean(BALL_PICKUP, false);
		SmartDashboard.putBoolean(WINCH_ACTIVE, false);

		SmartDashboard.putString(AUTONOMOUS_STATUS, "");
		SmartDashboard.putNumber(MOVE_DISTANCE_LEFT, 0);
		SmartDashboard.putNumber(MOVE_DISTANCE_RIGHT, 0);
	}
}
