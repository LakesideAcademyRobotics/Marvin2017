package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class DriveGameSubsystem extends Subsystem{

	public RobotDrive robotDrive;
	private double MaxOutputCap = 1; //The maximum value for MaxOutput of robotDrive;
	public boolean reverseInput = false;
	public double MaxMouvementInput = 1;
	
	//Gyro
	public Gyro gyro;
	public double correctionFactor = 0.35;
	public double MAX_CORRECTION = 0.3;
	
	
	public DriveGameSubsystem(RobotDrive robotDrive, Gyro gyro) {
		super();
		this.gyro = gyro;
		this.robotDrive = robotDrive;
	}
	
	public void setMaxOutputCap(double newCap){
		MaxOutputCap = newCap;
	}
	
	public void SetMaxOutput(double maxOutput){
		// Use maxOutput if it's smaller then MaxOutputCap
		double newMaxOutput = Math.min(maxOutput, MaxOutputCap);
		robotDrive.setMaxOutput(newMaxOutput);
	}
	
	
	public void Periodic(double movement, double rotation){
		if(reverseInput){
			movement = -movement;
			rotation = -rotation;
		}
		movement *= MaxMouvementInput;
			
			
		//correctionFactor = SmartDashboard.getNumber("gyroCorr", 0.2);
		//SmartDashboard.putNumber("Correction", 0);
		
		if(gyro == null){
			robotDrive.arcadeDrive(movement, rotation);
		}else{
			//We work with Gyro! We will use the correction and stuff
			PeriodicWithGyro(movement, rotation);
		}
	}
	

	private void PeriodicWithGyro(double movement, double rotation) {
		
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
				
				//SmartDashboard.putBoolean("GyroPause", false);
				//SmartDashboard.putNumber("Correction", correction);
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
	
	public boolean isPresent(){
		return RobotMap.driveTrain != null;
	}

	@Override
	protected void initDefaultCommand() {
		
	}

	public void reverseInput() {
		reverseInput = !reverseInput;
		
	}

}
