package org.usfirst.frc.team4955.robot.utils.input;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroRatationInput implements TeleopInput{
	
	TeleopInput rotationInput;
	Gyro gyro;
	public double correctionFactor = 0.2;
	
	public GyroRatationInput(TeleopInput rotationInput, Gyro gyro) {
		super();
		this.gyro = gyro;
		this.rotationInput = rotationInput;
		SmartDashboard.putNumber("gyroCorr", 0.2);
	}
	
	
	@Override
	public double getInput() {
		correctionFactor = SmartDashboard.getNumber("gyroCorr", 0.2);
		if(RobotMap.driveTrain.moveInput.getInput() != 0){
			gyro.reset();
			return rotationInput.getInput();
			
		}else{
			if(rotationInput.getInput() == 0){
				gyro.reset();
				return 0;
				
			}else{
				System.out.println("Correction : " + (gyro.getAngle() * correctionFactor));
				return rotationInput.getInput() - gyro.getAngle() * correctionFactor;
			}
			
		}
	}
}
