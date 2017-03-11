package org.usfirst.frc.team4955.robot.commands.autonomous;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.commands.autonomous.vision.AlignWithTarget;
import org.usfirst.frc.team4955.robot.commands.generics.DigitalOutputFlash;
import org.usfirst.frc.team4955.robot.vision.VisionThread.VisionState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGear extends CommandGroup {

	public AutonomousGear() {
		addSequential(new AlignWithTarget(VisionState.Gear, Constants.VISION_GEAR_CENTER_OFFSET));
		// addSequential(new ApprocheTarget(VisionState.Gear, 28));
		// addSequential(new MoveDistance(18, 0.7));
		// addSequential(new MoveDistance(9, 0.6));
		addSequential(new DigitalOutputFlash(RobotMap.frontCameraLight, 8, 0.25, 0.25));
	}
}
