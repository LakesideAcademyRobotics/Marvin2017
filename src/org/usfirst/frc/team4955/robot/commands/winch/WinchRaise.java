package org.usfirst.frc.team4955.robot.commands.winch;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.OI;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchRaise extends Command {

	public WinchRaise() {
		requires(Robot.winchSystem);
	}

	protected void initialize() {
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, true);
	}

	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void execute() {
		double speed = OI.winchInput.getInput() * Constants.WINCH_MAX_SPEED;
		RobotMap.winchTalon.set(speed);
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