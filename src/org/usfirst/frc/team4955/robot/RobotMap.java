package org.usfirst.frc.team4955.robot;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Drive
	public static AnalogInput backSensor;
	public static AnalogInput frontSensor;
	public static RobotDrive driveTrain;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;

	// Gyro
	public static ADXRS450_Gyro gyro;

	// Winch
	public static Talon winchTalon;

	// Ball pickup
	public static Talon brushTalon;
	public static Talon elavatorTalon;

	// Ball shoot
	public static CANTalon throwingFeedTalon;
	public static CANTalon genevaWheelTalon;
	public static AnalogInput feederBallSensor;

	public static Servo cameraServo;

	public static void init1() {
		// driveTrain = new RobotDrive(2,3);

	}

	public static UsbCamera frontCamera;
	public static UsbCamera backCamera;

	public static void init() {
		driveTrain = new RobotDrive(2, 3, 0, 1);
		driveTrain.setSensitivity(0.5);
		driveTrain.setMaxOutput(Constants.DRIVE_NORMAL_MAXOUTPUT);

		InverseDriveTrain(driveTrain);

		leftEncoder = tryInitEncoder(0, 1);
		rightEncoder = tryInitEncoder(2, 3);
		rightEncoder.setReverseDirection(true);

		gyro = tryInitGyro();

		frontSensor = new AnalogInput(0);
		backSensor = new AnalogInput(1);

		brushTalon = tryInitTalon(5);
		winchTalon = tryInitTalon(4);
		elavatorTalon = tryInitTalon(6);

		genevaWheelTalon = tryInitCanTalon(5);
		throwingFeedTalon = tryInitCanTalon(6);

		cameraServo = tryInitServo(7);

		// Cameras

		// frontCamera = initLogitechHd1080p(0);
		backCamera = initLogitechHd720p(0);
		// CameraServer.getInstance().startAutomaticCapture();

	}

	public static UsbCamera initLogitechHd1080p(int channel) {

		UsbCamera cam = new UsbCamera("Front", 0);

		cam.setFPS(30);
		cam.setResolution(1920, 1080);
		CameraServer.getInstance().addCamera(cam);
		CameraServer.getInstance().startAutomaticCapture(cam);
		return cam;
	}

	public static UsbCamera initLogitechHd720p(int channel) {

		UsbCamera cam = new UsbCamera("Back", 0);

		cam.setFPS(30);
		cam.setResolution(1280, 720);
		CameraServer.getInstance().addCamera(cam);

		return cam;
	}

	private static void InverseDriveTrain(RobotDrive driveTrain) {
		for (RobotDrive.MotorType type : RobotDrive.MotorType.values()) {
			driveTrain.setInvertedMotor(type, true);
		}

	}

	public static Encoder tryInitEncoder(int sourceA, int sourceB) {
		try {
			Encoder encoder = new Encoder(sourceA, sourceB);
			return encoder;

		} catch (RuntimeException re) {
			if (re.getMessage().contains("Code: -1029")) {
				System.err.println(
						"ERRROR! Encoder at source A:" + sourceA + " and source B:" + sourceB + " is not pluged-in.");
			} else {
				System.err.println(re.getMessage());
			}
		}
		return null;
	}

	public static Talon tryInitTalon(int channel) {
		try {
			Talon talon = new Talon(channel);
			talon.set(0);
			System.out.println(channel + " : " + talon.isAlive());
			if (talon.isAlive()) {
				return talon;
			} else {
				return null;
			}

		} catch (RuntimeException re) {

			if (re.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! Talon at channel " + channel + " is not pluged-in.");
			} else {
				System.err.println(re.getMessage());
			}
		}
		return null;
	}

	public static CANTalon tryInitCanTalon(int channel) {
		try {
			CANTalon talon = new CANTalon(channel);
			talon.set(0);
			return talon;
		} catch (RuntimeException re) {
			if (re.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! CanTalon at channel " + channel + " is not pluged-in.");
			} else {
				System.err.println(re.getMessage());
			}
		}
		return null;
	}

	public static Servo tryInitServo(int channel) {
		try {
			Servo servo = new Servo(channel);
			return servo;
		} catch (RuntimeException re) {
			if (re.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! Servo at channel " + channel + " is not pluged-in.");
			} else {
				System.err.println(re.getMessage());
			}
		}
		return null;
	}

	public static ADXRS450_Gyro tryInitGyro() {
		try {
			ADXRS450_Gyro gyro = new ADXRS450_Gyro();
			gyro.reset();
			return gyro;
		} catch (Exception re) {
			System.out.println("ERRROR! Gyro is not pluged-in.");
			/*
			 * if (re.getMessage().contains("gyro")) {
			 * System.err.println("ERRROR! Gyro is not pluged-in."); } else {
			 * System.err.println("autre " + re.getMessage()); }
			 */
		}
		return null;
	}
}
