package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.OI;
import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive extends Command {

	public JoystickDrive() {
		requires(Robot.driveSubsystem);
	}

	@Override
	protected void initialize() {
		// To avoid the first : Robot Drive... Output not updated often enough.
		Robot.driveSubsystem.Periodic(0, 0);
	}

	@Override
	protected void execute() {
		double r = OI.controlerRotationInput.getInput();
		r = r * 0.8;
		Robot.driveSubsystem.Periodic(OI.controlerMovementInput.getInput(), r);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
