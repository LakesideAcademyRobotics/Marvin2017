package org.usfirst.frc.team4955.robot.utils.driveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveTrainMaxOutputCommand extends Command{

	private DriveTrainControler driveTrainControler;
	private double newOutput;
	private double currentMaxOutput;
	
	public SetDriveTrainMaxOutputCommand(DriveTrainControler driveTrainControler, double newOutput) {
		super();
		this.driveTrainControler = driveTrainControler;
		this.newOutput = newOutput;
	}

	@Override
	protected void initialize() {
		currentMaxOutput = driveTrainControler.getMaxOutput();
		driveTrainControler.setMaxOuput(newOutput);
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
		driveTrainControler.setMaxOuput(currentMaxOutput);
	}

	@Override
	protected void interrupted() {
		driveTrainControler.setMaxOuput(currentMaxOutput);
	}

}
