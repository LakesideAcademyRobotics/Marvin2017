package org.usfirst.frc.team4955.robot;

import java.text.NumberFormat;

import org.usfirst.frc.team4955.robot.utils.SensorEncoderUtils;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestProcedure {

	private int currentItem;
	public boolean itemRunning;
	private double valueChangerFactor = 0.1;

	String[] items = new String[] { "Brush talon", "Elevator talon", "Ball pick-up", "Winch talon", "Genova",
			"Thrower Wheel", "Ball Shooting", "Front sensor", "Back sensor", "Gyro", "Left Encoder", "Right Encoder",
			"Camera light" };

	public TestProcedure() {
		currentItem = 10;
		SmartDashboard.putString("Current Item", "");
		SmartDashboard.putString("Constant", "");
		SmartDashboard.putNumber("Value", 0);
		itemRunning = false;

		makeCommands();
		CurrentItemChanged();
	}

	private void makeCommands() {
		JoystickButton command;
		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.RB.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				stop(currentItem);
				currentItem++;
				CurrentItemChanged();
			}
		});

		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.LB.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				stop(currentItem);
				currentItem--;
				CurrentItemChanged();
			}
		});

		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.Start.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				init(currentItem);
			}
		});

		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.Back.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				stop(currentItem);
			}
		});
	}

	void CurrentItemChanged() {
		if (currentItem >= items.length)
			currentItem = items.length - 1;
		if (currentItem < 0)
			currentItem = 0;
		print(items[currentItem]);
		SmartDashboard.putNumber("Value", 0);
		if (currentItem == 7 || currentItem == 8 || currentItem == 9 || currentItem == 12) {
			init(currentItem);
		}
	}

	public void periodic() {
		double speedChange = OI.testLeftRight.getInput();
		if (speedChange != 0) {
			double v = getValue();
			SmartDashboard.putNumber("Value", v + speedChange * valueChangerFactor);
		}
		if (itemRunning)
			run(currentItem);

	}

	private void init(int item) {
		itemRunning = true;
		valueChangerFactor = 0.02;

		if (item == 0) {
			SmartDashboard.putNumber("Value", Constants.BRUSH_SPEED);
			SmartDashboard.putString("Constant", "BRUSH_SPEED");
		} else if (item == 1) {
			SmartDashboard.putNumber("Value", Constants.CONVEYOR_BELT_SPEED);
			SmartDashboard.putString("Constant", "CONVEYOR_BELT_SPEED");
		} else if (item == 2) {

		} else if (item == 3) {
			SmartDashboard.putString("Constant", "WINCH_MAX_SPEED");
			SmartDashboard.putNumber("Value", Constants.WINCH_MAX_SPEED);

		} else if (item == 4) {
			SmartDashboard.putString("Constant", "GENOVA_SPEED");
			SmartDashboard.putNumber("Value", Constants.GENOVA_SPEED);
		} else if (item == 5) {
			SmartDashboard.putString("Constant", "THROWER_SPEED");
			SmartDashboard.putNumber("Value", Constants.THROWER_RPM);
		} else if (item == 6) {
			printValue(0);
		} else if (item == 7) {
			printValue(0);
		} else if (item == 8) {
			printValue(0);
		} else if (item == 9) {
			printValue(0);
		} else if (item == 10 || item == 11) {
			SmartDashboard.putNumber("Value", Constants.ENCODER_ROTATIONS_PER_FOOT);
			RobotMap.leftEncoder.reset();
			RobotMap.rightEncoder.reset();
		} else if (item == 12) {
			RobotMap.backCameraLight.set(Constants.CAMERA_LIGHT_ON);
			RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
		}

		// Movement
	}

	private void printValue(double value) {
		SmartDashboard.putNumber("Value", value);
	}

	private void run(int item) {
		double value = getValue();

		///// Ball pick-up
		if (item == 0) {
			Constants.BRUSH_SPEED = value;
			RobotMap.brushTalon.set(value);

		} else if (item == 1) {
			Constants.CONVEYOR_BELT_SPEED = value;
			RobotMap.elevator.set(value);

		} else if (item == 2) {
			RobotMap.brushTalon.set(Constants.BRUSH_SPEED);
			RobotMap.elevator.set(Constants.CONVEYOR_BELT_SPEED);

			//// Winch
		} else if (item == 3) {
			RobotMap.winchTalon.set(Constants.WINCH_MAX_SPEED);

			//// Ball Throwing
		} else if (item == 4) {
			RobotMap.genevaWheelTalon.set(Constants.GENOVA_SPEED);

		} else if (item == 5) {
			RobotMap.throwingWheelTalon.set(Constants.THROWER_RPM);

		} else if (item == 6) {
			RobotMap.genevaWheelTalon.set(Constants.GENOVA_SPEED);
			RobotMap.throwingWheelTalon.set(Constants.THROWER_RPM);

		} else if (item == 7) {
			double val = SensorEncoderUtils.calculateDistance(RobotMap.frontSensor);
			print("Front sensor : " + NumberFormat.getInstance().format(val) + "\"");
		} else if (item == 8) {
			double val = SensorEncoderUtils.calculateDistance(RobotMap.backSensor);
			print("Back sensor : " + NumberFormat.getInstance().format(val) + "\"");
		} else if (item == 9) {
			double val = RobotMap.gyro.getAngle();
			RobotMap.driveTrain.arcadeDrive(0, OI.controlerRotationInput.getInput());
			print("Gyro Angle : " + NumberFormat.getInstance().format(val));
		} else if (item == 10) {
			double val = RobotMap.leftEncoder.get() / value;
			RobotMap.driveTrain.arcadeDrive(OI.controlerMovementInput.getInput(), OI.controlerRotationInput.getInput());
			print("Left Encoder : " + NumberFormat.getInstance().format(val));
		} else if (item == 11) {
			double val = RobotMap.rightEncoder.get() / value;
			RobotMap.driveTrain.arcadeDrive(OI.controlerMovementInput.getInput(), OI.controlerRotationInput.getInput());
			print("Right Encoder : " + NumberFormat.getInstance().format(val));
		}
	}

	private double getValue() {
		return SmartDashboard.getNumber("Value", 0);
	}

	private void stop(int item) {
		itemRunning = false;
		SmartDashboard.putString("Constant", "");
		SmartDashboard.putNumber("Value", 0);
		if (item == 0)
			RobotMap.brushTalon.set(0);
		else if (item == 1)
			RobotMap.elevator.set(0);
		else if (item == 2) {
			RobotMap.elevator.set(0);
			RobotMap.brushTalon.set(0);

		} else if (item == 3)
			RobotMap.winchTalon.set(0);
		else if (item == 4)
			RobotMap.genevaWheelTalon.set(0);
		else if (item == 5)
			RobotMap.throwingWheelTalon.set(0);
		else if (item == 6) {
			RobotMap.genevaWheelTalon.set(0);
			RobotMap.throwingWheelTalon.set(0);
		} else if (item == 12) {
			RobotMap.backCameraLight.set(Constants.CAMERA_LIGHT_ON);
			RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
		}
		// 5-6 nothing to do
	}

	private void print(String message) {
		SmartDashboard.putString("Current Item", currentItem + " - " + message);
	}

}
