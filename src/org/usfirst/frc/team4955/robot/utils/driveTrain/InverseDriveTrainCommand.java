package org.usfirst.frc.team4955.robot.utils.driveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class InverseDriveTrainCommand extends Command {

	private DriveTrainControler driveTrainControler;
	
	public InverseDriveTrainCommand(DriveTrainControler driveTrainControler) {
		super();
		this.driveTrainControler = driveTrainControler;
	}

	@Override
	protected void initialize() {
		driveTrainControler.inverse();
		//System.err.println("ERROR : Ca marche inverse");
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
