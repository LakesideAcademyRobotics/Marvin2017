package org.usfirst.frc.team4955.robot.commands.autonomous.kickInside;

import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.TurnRobot;
import org.usfirst.frc.team4955.robot.commands.gear.GearKickerCommand;
import org.usfirst.frc.team4955.robot.commands.generics.SetIgnioreGyroOnDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class KickInside extends CommandGroup {
	public KickInside(double kickAngle) {

		addSequential(new SetIgnioreGyroOnDrive(true));
		addSequential(new MoveDistance(55, 0.8));
		addSequential(new WaitCommand(0.25));
		addSequential(new TurnRobot(kickAngle * 90, 0.75));

		// You put the gear please!
		addSequential(new GearKickerCommand(0.5));

		addSequential(new TurnRobot(kickAngle * -90, 0.75));

		addSequential(new WaitCommand(0.25));

		addSequential(new MoveDistance(144, 0.9));

		addSequential(new SetIgnioreGyroOnDrive(false));
	}
}