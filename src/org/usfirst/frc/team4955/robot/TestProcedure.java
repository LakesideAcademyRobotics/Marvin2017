package org.usfirst.frc.team4955.robot;

import org.usfirst.frc.team4955.robot.utils.SensorEncoderUtils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestProcedure {

	private int currentItem;

	public TestProcedure() {
		currentItem = 0;
	}

	public void periodic() {

		run(currentItem);
	}

	private void run(int item) {
		if (item == 0) {
			RobotMap.brushTalon.set(Constants.BRUSH_SPEED);
			print("Brush talon");
		} else if (item == 1) {
			RobotMap.elevator.set(Constants.CONVEYOR_BELT_SPEED);
			print("Elevator talon");
		} else if (item == 2) {
			RobotMap.winchTalon.set(Constants.WINCH_MAX_SPEED);
			print("Winch talon");
		} else if (item == 3) {
			RobotMap.genevaWheelTalon.set(Constants.GENOVA_SPEED);
			print("Genova");
		} else if (item == 4) {
			RobotMap.throwingWheelTalon.set(Constants.THROWER_SPEED);
			print("Thrower Wheel");
		} else if (item == 5) {
			print("Front sensor : " + SensorEncoderUtils.calculateDistance(RobotMap.frontSensor));
		} else if (item == 6) {
			print("Back sensor : " + SensorEncoderUtils.calculateDistance(RobotMap.backSensor));
		}
	}

	private void stop(int item) {
		if (item == 0)
			RobotMap.brushTalon.set(0);
		else if (item == 1)
			RobotMap.elevator.set(0);
		else if (item == 2)
			RobotMap.winchTalon.set(0);
		else if (item == 3)
			RobotMap.genevaWheelTalon.set(0);
		else if (item == 4)
			RobotMap.throwingWheelTalon.set(0);
		// 5-6 nothing to do
		else if (item == 7)
			RobotMap.throwingWheelTalon.set(0);
	}

	private void print(String message) {
		SmartDashboard.putString("Test", "Running : " + message);
	}

}
