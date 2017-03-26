package org.usfirst.frc.team4955.robot;

import java.text.NumberFormat;

import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.TurnRobot;
import org.usfirst.frc.team4955.robot.utils.SensorEncoderUtils;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad;
import org.usfirst.frc.team4955.robot.vision.VisionThread.VisionState;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestProcedure {

	private int			currentItem;
	public boolean		itemRunning;
	private double		valueChangerFactor	= 0.1;
	private double[]	value1MinMax		= new double[2];
	private double[]	value2MinMax		= new double[2];

	private Command			runningCommand;
	private TestItem		currentTestItem;
	public static boolean	madeCommands;

	public TestProcedure() {
		currentItem = TestItem.CamLightFront.ordinal();
		SmartDashboard.putString("Current Item", "");
		SmartDashboard.putNumber("Value", 0);
		SmartDashboard.putNumber("Value2", 0);
		value1MinMax = new double[] { -1, 1 };
		value2MinMax = new double[] { -1, 1 };
		itemRunning = false;

		if (!madeCommands) {
			makeCommands();
			madeCommands = true;
		}

		CurrentItemChanged();
	}

	private void makeCommands() {
		JoystickButton command;
		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.RB.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				if (!Constants.IsInTestMod())
					return;
				stop(currentItem);
				currentItem++;
				CurrentItemChanged();
			}
		});

		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.LB.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				if (!Constants.IsInTestMod())
					return;
				stop(currentItem);
				currentItem--;
				CurrentItemChanged();
			}
		});

		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.Start.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				if (!Constants.IsInTestMod())
					return;
				init(currentItem);
			}
		});

		command = new JoystickButton(OI.mainJoystick, Gamepad.GamepadButton.Back.value());
		command.whenActive(new OneShotCommand() {
			protected void initialize() {
				if (!Constants.IsInTestMod())
					return;
				stop(currentItem);
			}
		});
	}

	void CurrentItemChanged() {
		if (currentItem >= TestItem.values().length)
			currentItem = TestItem.values().length - 1;
		if (currentItem < 0)
			currentItem = 0;
		print(TestItem.values()[currentItem].name);
		SmartDashboard.putNumber("Value", 0);
		SmartDashboard.putNumber("Value2", 0);
		// if(TestItem.values()[currentItem].)
	}

	public void periodic() {

		// Removes the "errror"
		RobotMap.driveTrain.arcadeDrive(0, 0);

		if (itemRunning) {
			double speedChange = OI.testValue.getInput();
			if (speedChange != 0) {
				double v = SmartDashboard.getNumber("Value", 0);
				v = v - speedChange * valueChangerFactor;
				v = Math.max(v, value1MinMax[0]);
				v = Math.min(v, value1MinMax[1]);
				SmartDashboard.putNumber("Value", v);
			}

			double value2Change = OI.testValue2.getInput();
			if (value2Change != 0) {
				double v = SmartDashboard.getNumber("Value2", 0);
				v = v - value2Change * valueChangerFactor;
				v = Math.max(v, value2MinMax[0]);
				v = Math.min(v, value2MinMax[1]);
				SmartDashboard.putNumber("Value2", v);
			}
			run(currentItem);
		} else {

			RobotMap.driveTrain.arcadeDrive(OI.controlerMovementInput.getInput(), OI.controlerRotationInput.getInput());
		}

	}

	private void init(int item) {
		if (runningCommand != null) {
			runningCommand.cancel();
			runningCommand = null;
		}
		itemRunning = true;
		valueChangerFactor = 0.02;
		value1MinMax = new double[] { -1, 1 };
		value2MinMax = new double[] { -1, 1 };
		currentTestItem = TestItem.values()[currentItem];

		if (currentTestItem.equals(TestItem.Brush)) {
			SmartDashboard.putNumber("Value", Constants.BRUSH_SPEED);
		} else if (currentTestItem.equals(TestItem.Conveyor)) {
			SmartDashboard.putNumber("Value", Constants.CONVEYOR_BELT_SPEED);

		} else if (currentTestItem.equals(TestItem.Genova)) {
			SmartDashboard.putNumber("Value", Constants.GENOVA_SPEED);
		} else if (currentTestItem.equals(TestItem.Thrower)) {
			SmartDashboard.putNumber("Value", Constants.THROWER_RPM);

		} else if (currentTestItem.equals(TestItem.GearBack)) {
			SmartDashboard.putNumber("Value", Constants.GEAR_KICKER_BACK_POSITION);
			changeValuesStuff(0.05, new double[] { 0, 1 }, 0.1, new double[] { -1, 1 });
		} else if (currentTestItem.equals(TestItem.GearPush)) {
			SmartDashboard.putNumber("Value", Constants.GEAR_KICKER_PUSH_POSITION);
			changeValuesStuff(0.05, new double[] { 0, 1 }, 0.1, new double[] { -1, 1 });

		} else if (currentTestItem.equals(TestItem.Winch)) {
			RobotMap.cameraFrontServo.set(Constants.CAMERA_TALON_IN_VALUE);
			SmartDashboard.putNumber("Value", Constants.WINCH_MAX_SPEED);
			changeValuesStuff(0.05, new double[] { 0, 1 }, 0.1, new double[] { -1, 1 });

		} else if (currentTestItem.equals(TestItem.LeftEncoder) || currentTestItem.equals(TestItem.RightEncoder)) {
			changeValuesStuff(0.5, new double[] { 0, 99999 }, 0.5, new double[] { 0, 255 });
			SmartDashboard.putNumber("Value", Constants.ENCODER_ROTATIONS_PER_INCH);
			RobotMap.leftEncoder.reset();
			RobotMap.rightEncoder.reset();

		} else if (currentTestItem.equals(TestItem.GyroDrive)) {
			SmartDashboard.putNumber("Value", Constants.CORRECTION_FACTOR);
			SmartDashboard.putNumber("Value2", Constants.MAX_CORRECTION);

		} else if (currentTestItem.equals(TestItem.Move2Feets)) {
			runningCommand = new MoveDistance(10 * 12, 0.8);
			runningCommand.start();

		} else if (currentTestItem.equals(TestItem.Turn360)) {
			changeValuesStuff(0.5, new double[] { 0, 99999 }, 0.5, new double[] { 0, 255 });
			SmartDashboard.putNumber("Value", Constants.LEFT_ENCODER_ROTATIONS_PER_360);
			runningCommand = new TurnRobot(360, 0.8);
			runningCommand.start();

		} else if (currentTestItem.equals(TestItem.TurnNeg360)) {
			changeValuesStuff(0.5, new double[] { 0, 99999 }, 0.5, new double[] { 0, 255 });
			SmartDashboard.putNumber("Value", Constants.RIGHT_ENCODER_ROTATIONS_PER_360);
			runningCommand = new TurnRobot(-360, 0.8);
			runningCommand.start();

		} else if (currentTestItem.equals(TestItem.CamLightFront)) {
			RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
			/*
			 * } else if (currentTestItem.equals(TestItem.CamLightBack)) {
			 * RobotMap.backCameraLight.set(Constants.CAMERA_LIGHT_ON);
			 */
		} else if (currentTestItem.equals(TestItem.CamTalonIn)) {
			SmartDashboard.putNumber("Value", Constants.CAMERA_TALON_IN_VALUE);
			changeValuesStuff(0.02, new double[] { 0, 1 }, 0.02, new double[] { -1, 2 });
		} else if (currentTestItem.equals(TestItem.CamTalonOut)) {
			SmartDashboard.putNumber("Value", Constants.CAMERA_TALON_OUT_VALUE);
			changeValuesStuff(0.02, new double[] { 0, 1 }, 0.02, new double[] { -1, 2 });

			/*
			 * } else if (currentTestItem.equals(TestItem.CamBackDown)) {
			 * SmartDashboard.putNumber("Value", Constants.CAMERA_BACK_DOWN);
			 * changeValuesStuff(0.02, new double[] { -1, 1 }, 0.02, new
			 * double[] { -1, 2 });
			 * } else if (currentTestItem.equals(TestItem.CamBackUp)) {
			 * SmartDashboard.putNumber("Value", Constants.CAMERA_BACK_UP);
			 * changeValuesStuff(0.02, new double[] { -1, 1 }, 0.02, new
			 * double[] { -1, 2 });
			 */
		} else if (currentTestItem.equals(TestItem.VisionHue)) {
			changeValuesStuff(1, new double[] { 0, 255 }, 1, new double[] { 0, 255 });
			Robot.cameraSubsystem.visionThread.setVisionState(VisionState.Gear);
			RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
			SmartDashboard.putNumber("Value", Constants.hsvThresholdHue[0]);
			SmartDashboard.putNumber("Value2", Constants.hsvThresholdHue[1]);
		} else if (currentTestItem.equals(TestItem.VisionSaturation)) {
			Robot.cameraSubsystem.visionThread.setVisionState(VisionState.Gear);
			changeValuesStuff(1, new double[] { 0, 255 }, 1, new double[] { 0, 255 });
			RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
			SmartDashboard.putNumber("Value", Constants.hsvThresholdSaturation[0]);
			SmartDashboard.putNumber("Value2", Constants.hsvThresholdSaturation[1]);
		} else if (currentTestItem.equals(TestItem.VisionValue)) {
			Robot.cameraSubsystem.visionThread.setVisionState(VisionState.Gear);
			changeValuesStuff(1, new double[] { 0, 255 }, 1, new double[] { 0, 255 });
			RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
			SmartDashboard.putNumber("Value", Constants.hsvThresholdValue[0]);
			SmartDashboard.putNumber("Value2", Constants.hsvThresholdValue[1]);

		} else if (currentTestItem.equals(TestItem.VisionDistance)) {
			Robot.cameraSubsystem.visionThread.setVisionState(VisionState.Gear);
			changeValuesStuff(0.2, new double[] { 0, 9999 }, 0.2, new double[] { 0, 9999 });
			RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
			SmartDashboard.putNumber("Value", Constants.VISION_DISTANCE_PIXEL_TARGET_HEIGHT);
			SmartDashboard.putNumber("Value2", Constants.VISION_DISTANCE_REAL_INCH_DISTANCE);
			/*
			 * }else if (currentTestItem.equals(TestItem.VisionGear)) {
			 * Robot.cameraSubsystem.visionThread.setVisionState(VisionState.
			 * Gear);
			 * RobotMap.frontCameraLight.set(Constants.CAMERA_LIGHT_ON);
			 * } else if (currentTestItem.equals(TestItem.VisionBoiler)) {
			 * Robot.cameraSubsystem.visionThread.setVisionState(VisionState.
			 * Boiler);
			 * RobotMap.backCameraLight.set(Constants.CAMERA_LIGHT_ON);
			 */
		}

		// Movement
	}

	void changeValuesStuff(double value1ChangeFactor, double[] value1MinMax, double value2ChangeFactor,
			double[] value2MinMax) {
		valueChangerFactor = value1ChangeFactor;
		this.value1MinMax = value1MinMax;
		this.value2MinMax = value2MinMax;
	}

	private void run(int item) {
		double value = SmartDashboard.getNumber("Value", 0);
		double value2 = SmartDashboard.getNumber("Value2", 0);

		///// Ball pick-up
		if (currentTestItem.equals(TestItem.Brush)) {
			Constants.BRUSH_SPEED = value;
			RobotMap.brushTalon.set(value);

		} else if (currentTestItem.equals(TestItem.Conveyor)) {
			Constants.CONVEYOR_BELT_SPEED = value;
			RobotMap.elevator.set(value);

		} else if (currentTestItem.equals(TestItem.Pickup)) {
			RobotMap.brushTalon.set(Constants.BRUSH_SPEED);
			RobotMap.elevator.set(Constants.CONVEYOR_BELT_SPEED);

		} else if (currentTestItem.equals(TestItem.Genova)) {
			Constants.GENOVA_SPEED = value;
			RobotMap.genevaWheelTalon.set(value);

		} else if (currentTestItem.equals(TestItem.Thrower)) {
			Constants.THROWER_RPM = value;
			RobotMap.throwingWheelTalon.set(value);

		} else if (currentTestItem.equals(TestItem.GyroDrive)) {
			Constants.CORRECTION_FACTOR = value;
			Constants.MAX_CORRECTION = value;
			Robot.driveSubsystem.Periodic(OI.controlerMovementInput.getInput(), OI.controlerRotationInput.getInput());

		} else if (currentTestItem.equals(TestItem.Winch)) {
			Constants.WINCH_MAX_SPEED = value;
			RobotMap.winchTalon.set(value * value2);

		} else if (currentTestItem.equals(TestItem.BallShooting)) {
			RobotMap.genevaWheelTalon.set(Constants.GENOVA_SPEED);
			RobotMap.throwingWheelTalon.set(Constants.THROWER_RPM);

		} else if (currentTestItem.equals(TestItem.FrontSensor)) {
			double val = SensorEncoderUtils.calculateDistance(RobotMap.frontSensor);
			print("Front sensor : " + NumberFormat.getInstance().format(val) + "\"");
		} else if (currentTestItem.equals(TestItem.BackSensor)) {
			double val = SensorEncoderUtils.calculateDistance(RobotMap.backSensor);
			print("Back sensor : " + NumberFormat.getInstance().format(val) + "\"");
		} else if (currentTestItem.equals(TestItem.Gyro)) {
			double val = RobotMap.gyro.getAngle();
			RobotMap.driveTrain.arcadeDrive(0, OI.controlerRotationInput.getInput());
			print("Gyro Angle : " + NumberFormat.getInstance().format(val));

		} else if (currentTestItem.equals(TestItem.GearBack)) {
			Constants.GEAR_KICKER_BACK_POSITION = value;
			RobotMap.gearKicker.set(value);
		} else if (currentTestItem.equals(TestItem.GearPush)) {
			Constants.GEAR_KICKER_PUSH_POSITION = value;
			RobotMap.gearKicker.set(value);

		} else if (currentTestItem.equals(TestItem.CamTalonIn)) {
			Constants.CAMERA_TALON_IN_VALUE = value;
			RobotMap.cameraFrontServo.set(value);
		} else if (currentTestItem.equals(TestItem.CamTalonOut)) {
			Constants.CAMERA_TALON_OUT_VALUE = value;
			RobotMap.cameraFrontServo.set(value);

			/*
			 * } else if (currentTestItem.equals(TestItem.CamBackDown)) {
			 * Constants.CAMERA_BACK_DOWN = value;
			 * RobotMap.cameraBackServo.set(value);
			 * } else if (currentTestItem.equals(TestItem.CamBackUp)) {
			 * Constants.CAMERA_BACK_UP = value;
			 * RobotMap.cameraBackServo.set(value);
			 */
		} else if (currentTestItem.equals(TestItem.LeftEncoder)) {
			Constants.ENCODER_ROTATIONS_PER_INCH = value;
			double val = RobotMap.leftEncoder.get();
			RobotMap.driveTrain.arcadeDrive(OI.controlerMovementInput.getInput(), OI.controlerRotationInput.getInput());
			String distance = String.format("%.2f%n", val / value);
			print("Left Encoder : " + val + ", " + distance + "\"");
		} else if (currentTestItem.equals(TestItem.RightEncoder)) {
			Constants.ENCODER_ROTATIONS_PER_INCH = value;
			double val = RobotMap.rightEncoder.get();
			RobotMap.driveTrain.arcadeDrive(OI.controlerMovementInput.getInput(), OI.controlerRotationInput.getInput());
			String distance = String.format("%.2f%n", val / value);
			print("Right Encoder : " + val + ", " + distance + "\"");

		} else if (currentTestItem.equals(TestItem.Turn360)) {
			Constants.LEFT_ENCODER_ROTATIONS_PER_360 = value;
			print("360 turn encoder value : " + RobotMap.leftEncoder.get());
		} else if (currentTestItem.equals(TestItem.TurnNeg360)) {
			Constants.RIGHT_ENCODER_ROTATIONS_PER_360 = value;
			print("-360 turn encoder value : " + RobotMap.rightEncoder.get());

		} else if (currentTestItem.equals(TestItem.VisionHue)) {
			Constants.hsvThresholdHue[0] = value;
			Constants.hsvThresholdHue[1] = value2;
		} else if (currentTestItem.equals(TestItem.VisionSaturation)) {
			Constants.hsvThresholdSaturation[0] = value;
			Constants.hsvThresholdSaturation[1] = value2;
		} else if (currentTestItem.equals(TestItem.VisionValue)) {
			Constants.hsvThresholdValue[0] = value;
			Constants.hsvThresholdValue[1] = value2;
		} else if (currentTestItem.equals(TestItem.VisionDistance)) {
			if (Robot.cameraSubsystem.visionThread.target == null) {
				print("Vision target height : no target");
			} else {
				double val = Robot.cameraSubsystem.visionThread.target.height;
				print("Vision target height :" + NumberFormat.getInstance().format(val) + " pixels");
			}

			Constants.VISION_DISTANCE_PIXEL_TARGET_HEIGHT = value;
			Constants.VISION_DISTANCE_REAL_INCH_DISTANCE = value2;
			Constants.VISION_DISTANCE_RATIO = Constants.VISION_DISTANCE_REAL_INCH_DISTANCE
					* Constants.VISION_DISTANCE_PIXEL_TARGET_HEIGHT;
		}
	}

	private void stop(int item) {
		itemRunning = false;
		SmartDashboard.putNumber("Value", 0);
		SmartDashboard.putNumber("Value2", 0);
		RobotMap.frontCameraLight.set(!Constants.CAMERA_LIGHT_ON);

		Robot.cameraSubsystem.visionThread.setVisionState(VisionState.None);
		if (runningCommand != null) {
			runningCommand.cancel();
			runningCommand = null;
		}

		if (currentTestItem == null) {

		} else if (currentTestItem.equals(TestItem.Brush))
			RobotMap.brushTalon.set(0);
		else if (currentTestItem.equals(TestItem.Conveyor))
			RobotMap.elevator.set(0);
		else if (currentTestItem.equals(TestItem.Pickup)) {
			RobotMap.elevator.set(0);
			RobotMap.brushTalon.set(0);

		} else if (currentTestItem.equals(TestItem.Winch)) {
			RobotMap.winchTalon.set(0);

		} else if (currentTestItem.equals(TestItem.Genova))
			RobotMap.genevaWheelTalon.set(0);
		else if (currentTestItem.equals(TestItem.Thrower))
			RobotMap.throwingWheelTalon.set(0);
		else if (currentTestItem.equals(TestItem.BallShooting)) {
			RobotMap.genevaWheelTalon.set(0);
			RobotMap.throwingWheelTalon.set(0);

		} else if (currentTestItem.equals(TestItem.CamTalonIn) || currentTestItem.equals(TestItem.CamTalonOut)) {
			// RobotMap.cameraFrontServo.set(0);
		}
	}

	private void print(String message) {
		SmartDashboard.putString("Current Item", currentItem + " - " + message);
	}

	public enum TestItem {
		Brush("Brush talon"), Conveyor("Conveyor Talon"), Pickup("Ball Pick-up"), Genova("Genova Talon"),
		Thrower("Thrower Talon"), BallShooting("Ball Shooting"), GearPush("Gear push"), GearBack("Gear back"),
		Winch("Winch"), FrontSensor("Front Sensor", true), BackSensor("Back Sensor", true),
		LeftEncoder("Left Encoder", true), RightEncoder("Right Encoder", true), Gyro("Gyro", true),
		GyroDrive("Gyro Drive"), Move2Feets("Move 2 Feets"), Turn360("+360 Turn"), TurnNeg360("-360 Turn"),
		CamTalonOut("Camera Talon Out"), CamTalonIn("Camera Talon In"), CamLightFront("Camera Light Front", false),
		VisionHue("Vision Hue", true), VisionSaturation("Vision Saturation", true), VisionValue("Vision Value", true),
		VisionDistance("Vision distance", true);
		// CamBackDown("Back Camera Down"), CamBackUp("Back Camera
		// Up"),CamLightBack("Camera Light Back", false), VisionGear("Vision
		// Gear", true), VisionBoiler("Vision Boiler", true)

		String	name;
		boolean	autoInit;

		TestItem(String name) {
			this.name = name;
		}

		TestItem(String name, boolean autoInit) {
			this.name = name;
			this.autoInit = autoInit;
		}
	}
}
