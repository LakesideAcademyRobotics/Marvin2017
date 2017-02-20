package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.utils.driveTrain.DriveTrainControler;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveInputFactor extends Command{

	private double newOutput;
	private double previousMovementInputFactor;
	
	public SetDriveInputFactor(double newOutput) {
		super();
		this.newOutput = newOutput;
	}

	@Override
	protected void initialize() {
		previousMovementInputFactor = Robot.driveSubsystem.MouvementInputFactor;
		Robot.driveSubsystem.MouvementInputFactor = newOutput;
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
		Robot.driveSubsystem.MouvementInputFactor = previousMovementInputFactor;
	}

	@Override
	protected void interrupted() {
		Robot.driveSubsystem.MouvementInputFactor = previousMovementInputFactor;
	}

}
