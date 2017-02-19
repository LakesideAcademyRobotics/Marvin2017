package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ThrowerSubsystem extends Subsystem {

	public ThrowerSubsystem() {
		if (isPresent()) {
			RobotMap.throwingFeedTalon.set(0);
			RobotMap.genevaWheelTalon.set(0);
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public boolean isPresent() {
		return RobotMap.throwingFeedTalon != null && RobotMap.genevaWheelTalon != null;
	}

}
