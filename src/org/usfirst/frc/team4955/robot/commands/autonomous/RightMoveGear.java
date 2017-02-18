package org.usfirst.frc.team4955.robot.commands.autonomous;

import org.usfirst.frc.team4955.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightMoveGear extends CommandGroup {

	public RightMoveGear() {
		requires(Robot.driveSubsystem);
	}
}
