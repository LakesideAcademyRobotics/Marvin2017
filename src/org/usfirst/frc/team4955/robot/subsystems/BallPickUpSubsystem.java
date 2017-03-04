package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class BallPickUpSubsystem extends Subsystem{

	public BallPickUpSubsystem() {
		if (this.isPresent()) {
			RobotMap.brushTalon.set(0);
			RobotMap.elevator.set(0);
		}
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isPresent(){
		return RobotMap.brushTalon != null && RobotMap.elevator != null;
	}

}
