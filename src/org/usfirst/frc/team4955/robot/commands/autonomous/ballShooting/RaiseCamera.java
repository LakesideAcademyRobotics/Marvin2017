package org.usfirst.frc.team4955.robot.commands.autonomous.ballShooting;

import edu.wpi.first.wpilibj.command.Command;

public class RaiseCamera extends Command {

	protected void initialize() {
		// RobotMap.cameraTalon.set(0.3);
		setTimeout(0.5);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}