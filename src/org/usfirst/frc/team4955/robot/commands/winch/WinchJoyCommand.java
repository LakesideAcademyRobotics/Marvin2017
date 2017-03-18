package org.usfirst.frc.team4955.robot.commands.winch;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.commands.generics.SetServoTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WinchJoyCommand extends CommandGroup {

	public WinchJoyCommand() {
		addSequential(new SetServoTo(RobotMap.cameraFrontServo, Constants.CAMERA_TALON_IN_VALUE, 1.0));
		addSequential(new WinchRaise());
	}

	@Override
	protected void end() {
		new WinchStop().start();
	}

	@Override
	protected void interrupted() {
		new WinchStop().start();
	}
}
