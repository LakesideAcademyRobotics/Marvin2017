package org.usfirst.frc.team4955.robot.commands.driveCommands;

import org.usfirst.frc.team4955.robot.OI;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.utils.utils.SmarterDashboard;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		
		Robot.driveSubsystem.controler.Periodic();
		
		
		/*if(OI.mainJoystick.getRawButton(OI.DRIVE_OUTPUT_LOWER_BUTTOM_NUMBER.value())){
			SmarterDashboard.addNumber("DriveMaxOutput", 1, -0.01);
			SmarterDashboard.clampNumber("DriveMaxOutput", 1, 0, 1);
			
		}else if(OI.mainJoystick.getRawButton(OI.DRIVE_OUTPUT_RAISE_BUTTOM_NUMBER.value())){
			SmarterDashboard.addNumber("DriveMaxOutput", 1, 0.01);
			SmarterDashboard.clampNumber("DriveMaxOutput", 1, 0, 1);
		}*/
		
		//Robot.driveSubsystem.controler.setMaxOuput(SmartDashboard.getNumber("DriveMaxOutput", 1));
		super.execute();
	}

}
