package org.usfirst.frc.team4955.robot.commands;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Thrower_Start  extends Command{
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}	
	@Override	
	protected void interrupted () {
		RobotMap.feedWheelTalon.set(0);
	}
	public void initialize() {
		RobotMap.shootWheelTalon.set(0);
			setTimeout(2);
	}
	@Override
	protected void end() {
		SmartDashboard.putBoolean("Ball Pickup", false);
		RobotMap.feedWheelTalon.set(0);
	}
}
