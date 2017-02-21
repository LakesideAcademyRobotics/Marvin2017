package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveInputFactor extends Command {

	private double factor;
	private double previousValue;

	public SetDriveInputFactor(double factor) {
		this.factor = factor;
	}

	@Override
	protected void initialize() {
		previousValue = Robot.driveSubsystem.MouvementInputFactor;
		Robot.driveSubsystem.MouvementInputFactor = previousValue * factor;
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.driveSubsystem.MouvementInputFactor = previousValue;
	}

	@Override
	protected void interrupted() {
		Robot.driveSubsystem.MouvementInputFactor = previousValue;
	}

}
