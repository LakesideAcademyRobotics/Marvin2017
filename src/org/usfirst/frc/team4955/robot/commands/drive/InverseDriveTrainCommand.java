package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class InverseDriveTrainCommand extends Command {

	public InverseDriveTrainCommand() {
		super();
	}

	@Override
	protected void initialize() {
		Robot.driveSubsystem.reverseInput();
		Robot.cameraSubsystem.reverseCams();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
