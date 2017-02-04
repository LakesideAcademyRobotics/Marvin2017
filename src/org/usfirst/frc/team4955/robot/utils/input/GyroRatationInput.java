package org.usfirst.frc.team4955.robot.utils.input;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroRatationInput implements TeleopInput{
	
	TeleopInput rotationInput;
	
	public GyroRatationInput(TeleopInput rotationInput) {
		super();
		this.rotationInput = rotationInput;
	}
	
	
	@Override
	public double getInput() {
		if(RobotMap.driveTrain.yInput.getInput() != 0){
			return rotationInput.getInput();
		}else{
			return rotationInput.getInput() - SmartDashboard.getNumber("driftCorrection", 0.1);
		}
	}
}
