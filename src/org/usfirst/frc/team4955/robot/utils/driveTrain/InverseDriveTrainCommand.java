package org.usfirst.frc.team4955.robot.utils.driveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InverseDriveTrainCommand extends Command {

	private DriveTrainControler driveTrainControler;
	
	public InverseDriveTrainCommand(DriveTrainControler driveTrainControler) {
		super();
		this.driveTrainControler = driveTrainControler;
	}

	@Override
	protected void initialize() {
		driveTrainControler.inverse();
		SmartDashboard.putNumber("test", 123);
		SmartDashboard.putString("test123", "Kwame");
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
