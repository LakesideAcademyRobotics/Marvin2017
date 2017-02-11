package org.usfirst.frc.team4955.robot;


import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
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
	
	//Gyro


	// Winch
	public static Talon winchTalon;
	
	//Gear
	public static AnalogInput frontLeftSensor;
	public static AnalogInput frontRightSensor;
	
	// Ball pickup 
	public static Talon brushTalon;
	public static Talon elavatorTalon;
	
	//Ball shoot
	public static Talon feedWheelTalon;
	public static CANTalon shootWheelTalon;
	public static AnalogInput feederBallSensor;
	
	public static ADXRS450_Gyro gyro;
	
	public static void init(){
		driveTrain = new RobotDrive(2,3);
		
		gyro = new ADXRS450_Gyro();
		
		//frontLeftSensor = new AnalogInput(0);	
		frontRightSensor = new AnalogInput(0);
		
		brushTalon = new Talon(2);
		//winchTalon = new Talon(0);

		//elavatorTalon = new Talon(0);
		//feedWheelTalon = new Talon(0);
		//shootWheelTalon = new CANTalon(0);
		//feederBallSensor = new AnalogInput(0);
	}
}
