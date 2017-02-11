package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class WallSensor extends Command{

	public double AnalogToInchesMultiplier = 0.125;
	
	protected boolean isFinished() {
		
		if((RobotMap.frontRightSensor.getValue())*AnalogToInchesMultiplier<0){
			return true;
		}
		return false;
	}
	
	
}




