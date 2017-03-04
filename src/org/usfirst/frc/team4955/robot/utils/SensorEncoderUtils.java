package org.usfirst.frc.team4955.robot.utils;

import org.usfirst.frc.team4955.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;

public class SensorEncoderUtils {

	public static double calculateDistance(AnalogInput sensor) {
		double distanceInFeet = (sensor.getValue() - Constants.SENSOR_ZERO) / Constants.SENSOR_MAX_DISTANCE_VALUE
				* Constants.SENSOR_MAX_DISTANCE_FEET;
		return distanceInFeet;
	}
}
