package org.usfirst.frc.team4955.robot;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static RobotDrive driveTrain;

	// Gyro
	public static ADXRS450_Gyro gyro;

	// Winch
	public static Talon winchTalon;

	// Gear
	public static AnalogInput frontLeftSensor;
	public static AnalogInput frontRightSensor;

	// Ball pickup
	public static Talon brushTalon;
	public static Talon elavatorTalon;

	// Ball shoot
	public static Talon throwingFeedTalon;
	public static CANTalon throwingWheelTalon;
	public static AnalogInput feederBallSensor;

	
	public static void init1(){
		//driveTrain = new RobotDrive(2,3);
		

	}
	//public static UsbCamera frontCamera;
	//public static UsbCamera backCamera;

	public static void init() {
		driveTrain = new RobotDrive(0, 1, 2, 3);
		InverseDriveTrain(driveTrain);

		driveTrain.setSafetyEnabled(false);
		
		gyro = tryInitGyro();

		// frontLeftSensor = new AnalogInput(0);
		frontRightSensor = new AnalogInput(0);

		
		//brushTalon = tryInitTalon(3);
		//winchTalon = tryInitTalon(3);

		//elavatorTalon = tryInitTalon(3);
		//feedWheelTalon = tryInitTalon(3);
		//shootWheelTalon = tryInitTalon(3);
		//feederBallSensor = tryInitTalon(3);


		// brushTalon = tryInitTalon(3);
		// winchTalon = tryInitTalon(3);

		// elavatorTalon = tryInitTalon(3);
		// feedWheelTalon = tryInitTalon(3);
		// shootWheelTalon = tryInitCanTalon(3);
		// feederBallSensor = tryInitTalon(3);

		// Cameras
		//frontCamera = initLogitechHd1080p(0);
		//backCamera = initLogitechHd720p(0);
	}

	public static UsbCamera initLogitechHd1080p(int channel) {

		UsbCamera cam = new UsbCamera("Front", 0);

		cam.setFPS(30);
		cam.setResolution(1920, 1080);
		CameraServer.getInstance().addCamera(cam);

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

	public static Talon tryInitTalon(int channel) {
		try {
			Talon talon = new Talon(channel);
			return talon;
		} catch (RuntimeException re) {
			if (re.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! Talon " + channel + " is not pluged-in.");
			} else {
				System.err.println(re.getMessage());
			}
		}
		return null;
	}

	public static CANTalon tryInitCanTalon(int channel) {
		try {
			CANTalon talon = new CANTalon(channel);
			return talon;
		} catch (RuntimeException re) {
			if (re.getMessage().contains("Code: -1029")) {
				System.err.println("ERRROR! CanTalon " + channel + " is not pluged-in.");
			} else {
				System.err.println(re.getMessage());
			}
		}
		return null;
	}

	public static ADXRS450_Gyro tryInitGyro() {
		try {
			ADXRS450_Gyro gyro = new ADXRS450_Gyro();
			return gyro;
		} catch (Exception re) {
			if (re.getMessage().contains("ADXRS450")) {
				System.err.println("ERRROR! Gyro is not pluged-in.");
			} else {
				System.err.println(re.getMessage());
			}
		}
		return null;
	}
}
