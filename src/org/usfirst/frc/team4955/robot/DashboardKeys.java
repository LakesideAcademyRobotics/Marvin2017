package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardKeys {

	public static String INIT_OI = "init OI";

	public static String BALL_TROWING = "Ball throwing";
	public static String BALL_PICKUP = "Ball pickup";
	public static String WINCH_ACTIVE = "Winch Active";

	public static void init() {
		SmartDashboard.putBoolean(INIT_OI, false);

		SmartDashboard.putBoolean(BALL_TROWING, false);
		SmartDashboard.putBoolean(BALL_PICKUP, false);
		SmartDashboard.putBoolean(WINCH_ACTIVE, false);
	}
}
