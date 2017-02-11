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
			return rotationInput.getInput();
			
		}else{
			if(rotationInput.getInput() == 0){
				double correction  = (gyro.getAngle() * correctionFactor);
				
				if(correction > MAX_CORRECTION){
					correction = MAX_CORRECTION;
				}else if(correction < -MAX_CORRECTION){
					correction = -MAX_CORRECTION;
				}
				SmartDashboard.putBoolean("GyroPause", false);
				SmartDashboard.putNumber("Correction", correction);
				return rotationInput.getInput() - correction;

			}else{
				resetGyro();
				return rotationInput.getInput();
			}
			
		}
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
