package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveDistance extends Command{

	protected void execute() {

		SmartDashboard.putNumber("Enconder value for left", RobotMap.leftEncoder.getDistance());
		
		SmartDashboard.putNumber("Encoder value for right", RobotMap.rightEncoder.getDistance());
	}
	
	protected boolean isFinished() {
		
		return false;
	}

}
