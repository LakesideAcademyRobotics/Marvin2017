package org.usfirst.frc.team4955.robot.commands.thrower;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ThrowerStart extends Command {

	public ThrowerStart() {
		requires(Robot.throwerSubsystem);
	}

	public void initialize() {
		RobotMap.throwingWheelTalon.set(Constants.THROWER_RPM);
		// setTimeout(0.25);
		setTimeout(2);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void interrupted() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_TROWING, true);
		RobotMap.genevaWheelTalon.set(Constants.GENOVA_SPEED);
	}

	protected void end() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_TROWING, true);
		RobotMap.genevaWheelTalon.set(Constants.GENOVA_SPEED);
	}
}
