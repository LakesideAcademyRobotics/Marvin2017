package org.usfirst.frc.team4955.robot.commands;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchRaise extends Command {

	public WinchRaise() {
		requires(Robot.winchSystem);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void initialize() {
		RobotMap.winchTalon.set(0.5);
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, true);
	}

	@Override
	protected void end() {
		RobotMap.winchTalon.set(0);
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, false);
	}

	@Override
	protected void interrupted() {
		RobotMap.winchTalon.set(0);
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, false);
	}
}