package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WallSensor extends Command{

	public double AnalogToInchesMultiplier = 0.125;
	@Override
	protected void execute() {
		boolean isClose = (RobotMap.frontSensor.getValue()-240)*AnalogToInchesMultiplier<=0;
		SmartDashboard.putBoolean("Is too close", isClose);
	}
	

	protected boolean isFinished() {
		
		return false;
	}
	
	
}




