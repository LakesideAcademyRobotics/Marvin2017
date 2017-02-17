package org.usfirst.frc.team4955.robot.commands.Thrower;

import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ThrowerWhileActive extends Command {

	public ThrowerWhileActive() {
		requires(Robot.throwerSubsystem);
	}

	public void initialize() {

	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {

	}

	protected void end() {

	}
}
