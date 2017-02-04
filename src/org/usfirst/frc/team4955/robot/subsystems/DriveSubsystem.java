package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.commands.driveCommands.JoystickDrive;
import org.usfirst.frc.team4955.robot.utils.driveTrain.DriveTrainControler;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	public DriveTrainControler controler;
	
	public DriveSubsystem() {
		controler = RobotMap.driveTrain;
	}
	
	@Override
	protected void initDefaultCommand() {
		//new JoystickDrive();
	}

}
