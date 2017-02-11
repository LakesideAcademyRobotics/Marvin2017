package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.OI;
import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
public class JoystickDrive extends Command{

	
	public JoystickDrive() {
		//requires(Robot.driveSubsystem);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	public void start(){
	}
	
	@Override
	protected void interrupted() {
		
	}
	@Override
	protected void end() {
		
	}
	
	@Override
	protected void execute() {
		
		Robot.driveSubsystem.Periodic(OI.controlerMovementInput.getInput(), OI.controlerRotationInput.getInput());
	
		super.execute();
	}

}
