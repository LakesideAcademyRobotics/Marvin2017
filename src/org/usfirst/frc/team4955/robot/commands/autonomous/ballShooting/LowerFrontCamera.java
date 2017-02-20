package org.usfirst.frc.team4955.robot.commands.autonomous.ballShooting;

import edu.wpi.first.wpilibj.command.Command;

public class LowerFrontCamera extends Command {

	protected void initialize() {
		// RobotMap.cameraTalon.set(0);
		setTimeout(0.5);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
