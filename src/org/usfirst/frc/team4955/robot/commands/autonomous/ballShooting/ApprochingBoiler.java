package org.usfirst.frc.team4955.robot.commands.autonomous.ballShooting;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ApprochingBoiler extends Command {

	public ApprochingBoiler() {
		requires(Robot.driveSubsystem);
	}

	protected void initialize() {
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_THROWER_STATUS, "Approching the boiler");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_THROWER_STATUS, "Ready to shoot");
	}

}
