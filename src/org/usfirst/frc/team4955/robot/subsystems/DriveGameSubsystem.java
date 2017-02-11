package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.utils.input.TeleopInput;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveGameSubsystem extends Subsystem{

	public RobotDrive robotDrive;
	
	//Gyro
	public Gyro gyro;
	public double correctionFactor = 0.2;
	public double MAX_CORRECTION = 0.2;
	
	public DriveGameSubsystem(RobotDrive robotDrive, Gyro gyro) {
		super();
		this.gyro = gyro;
		this.robotDrive = robotDrive;
	}
	
	public void Periodic(float movement, float rotation){
		correctionFactor = SmartDashboard.getNumber("gyroCorr", 0.2);
		SmartDashboard.putNumber("Correction", 0);
		
		if(gyro == null){
			robotDrive.arcadeDrive(movement, rotation);
		}else{
			//We work with Gyro! We will use the correction and stuff
			PeriodicWithGyro(movement, rotation);
		}
	}
	

	private void PeriodicWithGyro(float movement, float rotation) {
		
		if(movement == 0){
			gyro.reset();
			//Move as normal
			
		}else{
			if(rotation != 0){
				//We don't apply correction when the inputed rotation wants to turn. ( not zero )
				gyro.reset();
				
			}else{
				double correction  = (gyro.getAngle() * correctionFactor);
				
				correction = CapValue(correction, -MAX_CORRECTION , MAX_CORRECTION);
				
				SmartDashboard.putBoolean("GyroPause", false);
				SmartDashboard.putNumber("Correction", correction);
				//We will apply the correction the correction
				rotation -= correction;
			}
			
		}
		

		PeriodicWithGyro(movement, rotation);
	}
	
	private double CapValue(double value, double min, double max){
		if(value > max){
			return max;
		}else if(value < min){
			return min;
		}else{
			return value;
		}
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
