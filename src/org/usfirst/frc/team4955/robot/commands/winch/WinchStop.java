package org.usfirst.frc.team4955.robot.commands.winch;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.OneShotCommand;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchStop extends OneShotCommand {

	public WinchStop() {
		requires(Robot.winchSystem);
	}

	protected void initialize() {
		RobotMap.winchTalon.set(0);
		SmartDashboard.putBoolean(DashboardKeys.WINCH_ACTIVE, false);
	}

}