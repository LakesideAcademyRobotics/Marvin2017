package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class BallPickUpSubsystem extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isPresent(){
		return RobotMap.brushTalon != null && RobotMap.elavatorTalon != null;
	}

}
