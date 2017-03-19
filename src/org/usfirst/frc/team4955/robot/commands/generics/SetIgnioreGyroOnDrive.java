package org.usfirst.frc.team4955.robot.commands.generics;

import org.usfirst.frc.team4955.robot.OneShotCommand;
import org.usfirst.frc.team4955.robot.Robot;

public class SetIgnioreGyroOnDrive extends OneShotCommand {

	boolean ignore;

	public SetIgnioreGyroOnDrive(boolean ignore) {
		this.ignore = ignore;
	}

	@Override
	protected void initialize() {
		Robot.driveSubsystem.ignoroGyro = ignore;
	}
}
