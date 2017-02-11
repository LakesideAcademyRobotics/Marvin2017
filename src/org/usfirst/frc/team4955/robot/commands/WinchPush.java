package org.usfirst.frc.team4955.robot.commands;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchPush extends Command {

	@Override
	protected boolean isFinished() {
		
		return false;
	}
	protected void initialize() {
		RobotMap.winchTalon.set(-0.5);
		SmartDashboard.putBoolean("Winch Active", true);
	}
	@Override
	protected void end() {
		RobotMap.winchTalon.set(0);
		SmartDashboard.putBoolean("Winch Active", false);
		super.end();
	}
	@Override
	protected void interrupted() {
		RobotMap.winchTalon.set(0);
		SmartDashboard.putBoolean("Winch Active", false);
		super.interrupted();
	}
	public WinchPush() {
		SmartDashboard.putBoolean("Winch Active", false);
	}
}