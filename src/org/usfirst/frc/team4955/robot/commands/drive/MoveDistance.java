package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;


public class MoveDistance extends Command{
	
	
	private double distanceInRotation;
	private double distanceToFinishRight;
	private double distanceToFinishLeft;
	
	public MoveDistance(double distanceInFeet) {
		this.distanceInRotation = distanceInFeet*Constants.ENCODER_ROTATIONS_PER_FOOT;
	}
	
	protected void initialize() {
		distanceToFinishRight = RobotMap.rightEncoder.get()+ distanceInRotation;
		distanceToFinishLeft = RobotMap.leftEncoder.get() + distanceInRotation;
	}
	
	protected boolean isFinished() {
		
		if(RobotMap.leftEncoder.get()>=distanceToFinishLeft||RobotMap.rightEncoder.get()>=distanceToFinishRight){
			return true;
		}
		return false;
	}

}
