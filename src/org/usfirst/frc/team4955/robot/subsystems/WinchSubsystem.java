package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchSubsystem extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		if (isPresent()) {
			RobotMap.winchTalon.set(0);
		}
	}

	public boolean isPresent() {
		return RobotMap.winchTalon != null;
	}

}
