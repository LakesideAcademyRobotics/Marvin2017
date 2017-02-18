package org.usfirst.frc.team4955.robot.commands.autonomous;

import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftMoveGear extends CommandGroup {

	public LeftMoveGear() {
		requires(Robot.driveSubsystem);
	}
}
