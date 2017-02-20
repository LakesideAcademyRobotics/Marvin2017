package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Turn135 extends Command{


	protected void initialize() {
		//Drive forward
		//RobotMap.brushTalon.set(0.5);
	}
	
	protected boolean isFinished() {
		
		if(RobotMap.gyro.getAngle()>20){
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void end() {
		//stopd driving
		//RobotMap.brushTalon.set(0);
		super.end();
	}
}
