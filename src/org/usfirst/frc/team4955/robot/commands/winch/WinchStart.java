package org.usfirst.frc.team4955.robot.commands.winch;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.commands.generics.SetServoTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WinchStart extends CommandGroup {

	public WinchStart() {
		addSequential(new SetServoTo(RobotMap.cameraServo, Constants.CAMERA_TALON_IN_VALUE, 1.0));
		addSequential(new WinchRaise());
	}
}
