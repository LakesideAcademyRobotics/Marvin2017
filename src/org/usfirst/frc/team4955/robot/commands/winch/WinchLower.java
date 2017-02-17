package org.usfirst.frc.team4955.robot.commands.winch;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchLower extends Command {

	public WinchLower() {
		requires(Robot.winchSystem);
	}

	protected void initialize() {
		RobotMap.winchTalon.set(-0.5);
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, true);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		RobotMap.winchTalon.set(0);
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, false);
	}

	protected void interrupted() {
		RobotMap.winchTalon.set(0);
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, false);
	}
}