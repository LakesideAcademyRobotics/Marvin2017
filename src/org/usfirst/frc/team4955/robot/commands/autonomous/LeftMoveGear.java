package org.usfirst.frc.team4955.robot.commands.autonomous;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.commands.PutValueInDashboard;
import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftMoveGear extends CommandGroup {

	public LeftMoveGear() {
		requires(Robot.driveSubsystem);

		addSequential(new TurnRobot(-180, 0.75));
		addSequential(new MoveDistance(1, 0.5));
		addSequential(new TurnRobot(180, 0.75));
		addSequential(new MoveDistance(1, 0.5));
		addSequential(new PutValueInDashboard(DashboardKeys.AUTONOMOUS_STATUS, "DONE"));
	}
}
