package org.usfirst.frc.team4955.robot;

public class Constants {

	// Drive base

	// How much the slow speed button slows down ( % of normal speed)
	public static double	DRIVE_SLOWER_SPEED_FACTOR			= 0.5;
	public static int		DRIVE_SLOW_DOWN_BY_WALL_DISTANCE	= 240;

	// Rotations needed to make 1 foot in real life for drive train
	public static double ENCODER_ROTATIONS_PER_INCH = 110;
	// Rotations needed to make 1 full rotation in real life for drive train
	public static double	LEFT_ENCODER_ROTATIONS_PER_360	= 9400.0;
	public static double	RIGHT_ENCODER_ROTATIONS_PER_360	= 7600;

	/**
	 * CORRECTION_FACTOR - Factor applied on the angle offset when driving
	 * staigth.
	 */
	public static double	CORRECTION_FACTOR	= 0.35;
	/**
	 * MAX_CORRECTION - Maximum of gyro correction done on the rotation when the
	 * driver tries to go in a straight line. Over about 0.4 allows for over
	 * correction and makes the robot wiggle.
	 */
	public static double	MAX_CORRECTION		= 0.3;

	// Too close, new full speed
	public static double	DRIVE_SLOWN_DOWN_MAXOUTPUT	= 0.4;
	public static double	DRIVE_NORMAL_MAXOUTPUT		= 1;	// Normal full
																// speed

	// Gear pusher
	public static double	GEAR_PUSHER_PUSH_POSITION	= 1;
	public static double	GEAR_PUSHER_BACK_POSITION	= 0;

	// BALL
	public static double	CONVEYOR_BELT_SPEED	= 0.7;
	public static double	BRUSH_SPEED			= -0.7;
	public static double	THROWER_RPM			= 4500;
	public static double	GENOVA_SPEED		= 1;

	// WINCH
	public static double WINCH_MAX_SPEED = 1;

	// SENSOR
	public static double	SENSOR_ZERO					= 50;
	public static double	SENSOR_MAX_DISTANCE_FEET	= 5;
	public static double	SENSOR_MAX_DISTANCE_VALUE	= 360;

	public static boolean IS_R2 = true;

	public static boolean CAMERA_LIGHT_ON = true;

	/*
	 * VISION
	 */
	public static double[]	hsvThresholdHue			= { 69, 126 };
	public static double[]	hsvThresholdSaturation	= { 0, 255.0 };
	public static double[]	hsvThresholdValue		= { 205, 255.0 };

	public static double	VISION_DISTANCE_PIXEL_TARGET_HEIGHT	= 29.0;
	public static double	VISION_DISTANCE_REAL_INCH_DISTANCE	= 58.0;
	public static double	VISION_DISTANCE_RATIO				= VISION_DISTANCE_PIXEL_TARGET_HEIGHT
			* VISION_DISTANCE_REAL_INCH_DISTANCE;

	// (normalised cameraValue)
	public static double VISION_GEAR_CENTER_OFFSET = 0.1;

	public static double	CAMERA_TALON_OUT_VALUE	= 0.24;
	public static double	CAMERA_TALON_IN_VALUE	= 0.9;

	public static double EaseTurn(double t) {
		return t * t * 0.25 + 0.75;
	}

	public static double EaseTurn(double t, double minSpeed) {
		return (1 - minSpeed) * t * t + minSpeed;
	}

	public static double EaseMove(double t) {
		return t * t * 0.25 + 0.75;
	}

	// Change for Robot R1. (all the values in constants are set for R2)
	public static void initForRobotR1() {
		IS_R2 = false;
		CONVEYOR_BELT_SPEED *= -1;
		BRUSH_SPEED *= 1;
		THROWER_RPM *= 1;
		GENOVA_SPEED *= 1;
		CORRECTION_FACTOR = -0.72;
		MAX_CORRECTION = 0.5;
		// RobotMap.rightEncoder.setReverseDirection(true);
		DRIVE_SLOWN_DOWN_MAXOUTPUT = 0.4;
		DRIVE_NORMAL_MAXOUTPUT = 0.8;
	}
}
