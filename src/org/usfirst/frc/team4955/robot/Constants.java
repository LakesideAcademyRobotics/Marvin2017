package org.usfirst.frc.team4955.robot;

public class Constants {

	// Drive base

	// How much the slow speed button slows down ( % of normal speed)
	public static double DRIVE_SLOWER_SPEED_FACTOR = 0.5;
	public static int DRIVE_SLOW_DOWN_BY_WALL_DISTANCE = 240;

	// Rotations needed to make 1 foot in real life for drive train
	public static double ENCODER_ROTATIONS_PER_FOOT = 1315;
	// Rotations needed to make 1 full rotation in real life for drive train
	public static double ENCODER_ROTATIONS_PER_360 = 8.2 * ENCODER_ROTATIONS_PER_FOOT;

	/**
	 * CORRECTION_FACTOR - Factor applied on the angle offset when driving
	 * staigth.
	 */
	public static double CORRECTION_FACTOR = 0.35;
	/**
	 * MAX_CORRECTION - Maximum of gyro correction done on the rotation when the
	 * driver tries to go in a straight line. Over about 0.4 allows for over
	 * correction and makes the robot wiggle.
	 */
	public static double MAX_CORRECTION = 0.3;

	// Too close, new full speed
	public static double DRIVE_SLOWN_DOWN_MAXOUTPUT = 0.4;
	public static double DRIVE_NORMAL_MAXOUTPUT = 1; // Normal full speed

	// BALL
	public static double CONVEYOR_BELT_SPEED = 0.7;
	public static double BRUSH_SPEED = -0.7;
	public static double THROWER_RPM = 4500;
	public static double GENOVA_SPEED = 1;

	// WINCH
	public static double WINCH_MAX_SPEED = 1;

	// SENSOR
	public static double SENSOR_ZERO = 50;
	public static double SENSOR_MAX_DISTANCE_FEET = 5;
	public static double SENSOR_MAX_DISTANCE_VALUE = 360;

	public static boolean IS_R2 = true;

	public static boolean CAMERA_LIGHT_ON = true;

	// Change for Robot R1. (all the values in constans are set for R2)
	public static void initForRobotR1() {
		IS_R2 = false;
		CONVEYOR_BELT_SPEED *= -1;
		BRUSH_SPEED *= 1;
		THROWER_RPM *= 1;
		GENOVA_SPEED *= 1;
		CORRECTION_FACTOR = 0.30;
		// RobotMap.rightEncoder.setReverseDirection(true);
		DRIVE_SLOWN_DOWN_MAXOUTPUT = 0.4;
		DRIVE_NORMAL_MAXOUTPUT = 0.8;
	}
}
