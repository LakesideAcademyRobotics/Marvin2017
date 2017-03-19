package org.usfirst.frc.team4955.robot.commands.autonomous;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.commands.autonomous.vision.AlignWithTarget;
import org.usfirst.frc.team4955.robot.commands.generics.DigitalOutputSetNoUnset;
import org.usfirst.frc.team4955.robot.vision.VisionThread.VisionState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGear extends CommandGroup {

	public AutonomousGear() {
		addSequential(new DigitalOutputSetNoUnset(RobotMap.frontCameraLight, Constants.CAMERA_LIGHT_ON));
		addSequential(new AlignWithTarget(VisionState.Gear, Constants.VISION_GEAR_CENTER_OFFSET));
		addSequential(new DigitalOutputSetNoUnset(RobotMap.frontCameraLight, !Constants.CAMERA_LIGHT_ON));
	}
}
