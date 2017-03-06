package org.usfirst.frc.team4955.robot.commands.autonomous.ballShooting;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AlignWithBoiler extends Command {

	public AlignWithBoiler() {
		requires(Robot.driveSubsystem);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Aliging with boiler");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
	}

}
