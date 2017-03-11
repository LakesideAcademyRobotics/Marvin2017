package org.usfirst.frc.team4955.robot;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Drive
	public static AnalogInput	backSensor;
	public static AnalogInput	frontSensor;
	public static RobotDrive	driveTrain;
	public static Encoder		leftEncoder;
	public static Encoder		rightEncoder;

	// Gyro
	public static ADXRS450_Gyro gyro;

	public static Servo gearPusher;

	// Winch
	public static Talon winchTalon;

	// Ball pickup
	public static Talon	brushTalon;
	public static Talon	elevator;

	// Ball shoot
	public static CANTalon	throwingWheelTalon;
	public static CANTalon	genevaWheelTalon;

	public static Servo cameraServo;

	public static UsbCamera		frontCamera;
	public static UsbCamera		backCamera;
	public static DigitalOutput	frontCameraLight;
	public static DigitalOutput	backCameraLight;

	public static void init() {
		driveTrain = new RobotDrive(2, 3, 0, 1);
		driveTrain.setMaxOutput(Constants.DRIVE_NORMAL_MAXOUTPUT);

		InverseDriveTrain(driveTrain);

		leftEncoder = tryInitEncoder(0, 1);
		rightEncoder = tryInitEncoder(2, 3);
		if (rightEncoder != null && !Constants.IS_R2)
			rightEncoder.setReverseDirection(true);

		gyro = tryInitGyro();

		gearPusher = new Servo(9);

		frontSensor = new AnalogInput(0);
		backSensor = new AnalogInput(1);

		LiveWindow.addSensor("Sensor", "frontSensor", frontSensor);
		LiveWindow.addActuator("Sensor", "backSensor", backSensor);

		winchTalon = tryInitTalon(4);

		brushTalon = tryInitTalon(5);
		elevator = tryInitTalon(6);
		LiveWindow.addActuator("Ballpick", "Brush", brushTalon);
		LiveWindow.addActuator("Ballpick", "Elevator", elevator);

		genevaWheelTalon = tryInitCanTalon(5);
		throwingWheelTalon = tryInitCanTalon(6);

		LiveWindow.addActuator("Ballshoot", "Geneva", genevaWheelTalon);
		LiveWindow.addActuator("Ballshoot", "Wheel", throwingWheelTalon);

		cameraServo = tryInitServo(8);

		frontCameraLight = new DigitalOutput(5);
		backCameraLight = new DigitalOutput(6);
		frontCameraLight.set(false);
		backCameraLight.set(false);
		// Cameras

		backCamera = initLogitechHd720p(0);
		frontCamera = initLogitechHd1080p(1);
	}

	public static UsbCamera initLogitechHd1080p(int channel) {
		UsbCamera cam = new UsbCamera("Front", channel);
		cam.setResolution(640, 480);
		CameraServer.getInstance().startAutomaticCapture(cam);
		return cam;
	}

	public static UsbCamera initLogitechHd720p(int channel) {
		UsbCamera cam = new UsbCamera("Back", channel);
		cam.setResolution(640, 480);
		CameraServer.getInstance().startAutomaticCapture(cam);
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
		Talon talon = new Talon(channel);
		talon.set(0);
		talon.stopMotor();
		return talon;
	}

	public static CANTalon tryInitCanTalon(int channel) {
		try {
			CANTalon talon = new CANTalon(channel);
			talon.set(0);
			return talon;
		} catch (RuntimeException re) {
			if (re.getMessage().contains("Code: -1029")) {
				System.err.println("Handled Error! CanTalon at channel " + channel + " is not pluged-in.");
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
			gyro.calibrate();
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
