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
	public static ADXRS450_Gyro gyro;


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
	
	public static void init(){
		//driveTrain = new RobotDrive(2,3);
		
		gyro = tryInitGyro();
		
		//frontLeftSensor = new AnalogInput(0);	
		frontRightSensor = new AnalogInput(0);
		
		//brushTalon = tryInitTalon(3);
		//winchTalon = tryInitTalon(3);

		//elavatorTalon = tryInitTalon(3);
		//feedWheelTalon = tryInitTalon(3);
		//shootWheelTalon = tryInitCanTalon(3);
		//feederBallSensor = tryInitTalon(3);
	}
	
	public static Talon tryInitTalon(int channel){
		try{
			Talon talon = new Talon(channel);
			return talon;
		}catch (RuntimeException re){
			if(re.getMessage().contains("Code: -1029")){
				System.err.println("ERRROR! Talon " + channel + " is not pluged-in.");
			}else{
				System.err.println(re.getMessage());
			}
		}
		return null;
	}
	
	public static CANTalon tryInitCanTalon(int channel){
		try{
			CANTalon talon = new CANTalon(channel);
			return talon;
		}catch (RuntimeException re){
			if(re.getMessage().contains("Code: -1029")){
				System.err.println("ERRROR! CanTalon " + channel + " is not pluged-in.");
			}else{
				System.err.println(re.getMessage());
			}
		}
		return null;
	}
	
	public static ADXRS450_Gyro tryInitGyro(){
		try{
			ADXRS450_Gyro gyro = new ADXRS450_Gyro();
			return gyro;
		}catch (RuntimeException re){
			if(re.getMessage().contains("Code: -1029")){
				System.err.println("ERRROR! Gyro is not pluged-in.");
			}else{
				System.err.println(re.getMessage());
			}
		}
		return null;
	}
}
