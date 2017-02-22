package org.usfirst.frc.team4955.robot.commands.thrower;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ThrowerStop extends Command {

	public ThrowerStop() {
		requires(Robot.throwerSubsystem);
	}

	public void initialize() {
		RobotMap.genevaWheelTalon.set(0);
		setTimeout(0.75);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_TROWING, false);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_THROWER_STATUS, "");
		RobotMap.throwingFeedTalon.set(0);
	}

	protected void interrupted() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_TROWING, false);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_THROWER_STATUS, "");
		RobotMap.throwingFeedTalon.set(0);
	}
}
