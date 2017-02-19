package org.usfirst.frc.team4955.robot;

public class Constants {

	// Drive base

	// How much the slow speed button slows down ( % of normal speed)
	public static double DRIVE_SLOWER_SPEED_FACTOR = 0.5;

	public static int DRIVE_SLOW_DOWN_BY_WALL_DISTANCE = 240;

	// Too close, new full speed
	public static double DRIVE_SLOWN_DOWN_BY_WALL_NEW_MAX_SPEED = 0.4;

	public static double DRIVE_MAX_SPEED = 1; // Normal full speed

	// BALL
	public static double CONVEYOR_BELT_SPEED = 0.7;
	public static double BRUSH_SPEED = -0.7;
	public static double THROWER_SPEED = 1;
	public static double BALL_FEEDER = 1;

	// WINCH
	public static double WINCH_SPEED = 1;

	public static float PEG_HEIGHT_FROM_GROUND = 13;

	// Change for Robot R1. (all the values in constans are set for R2)
	public static void initForRobotR1() {
		CONVEYOR_BELT_SPEED *= -1;
		BRUSH_SPEED *= 1;
		THROWER_SPEED *= 1;
		BALL_FEEDER *= 1;

	}
}
