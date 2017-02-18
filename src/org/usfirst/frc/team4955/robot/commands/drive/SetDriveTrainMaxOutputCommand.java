package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.utils.driveTrain.DriveTrainControler;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveTrainMaxOutputCommand extends Command{

	private double newOutput;
	private double currentMaxOutput;
	
	public SetDriveTrainMaxOutputCommand(double newOutput) {
		super();
		this.newOutput = newOutput;
	}

	@Override
	protected void initialize() {
		currentMaxOutput = Robot.driveSubsystem.MaxMouvementInput;
		Robot.driveSubsystem.MaxMouvementInput = newOutput;
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
		Robot.driveSubsystem.MaxMouvementInput = currentMaxOutput;
	}

	@Override
	protected void interrupted() {
		Robot.driveSubsystem.MaxMouvementInput = currentMaxOutput;
	}

}
