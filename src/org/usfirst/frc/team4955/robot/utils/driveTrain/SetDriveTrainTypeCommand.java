package org.usfirst.frc.team4955.robot.utils.driveTrain;

import org.usfirst.frc.team4955.robot.utils.driveTrain.DriveTrainControler.DriveType;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveTrainTypeCommand extends Command{

	private DriveTrainControler driveTrainControler;
	private DriveType driveType;
	
	
	public SetDriveTrainTypeCommand(DriveTrainControler driveTrainControler, DriveType driveType) {
		super();
		this.driveTrainControler = driveTrainControler;
		this.driveType = driveType;
	}

	@Override
	protected void initialize() {
		driveTrainControler.setDriveType(driveType);
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
