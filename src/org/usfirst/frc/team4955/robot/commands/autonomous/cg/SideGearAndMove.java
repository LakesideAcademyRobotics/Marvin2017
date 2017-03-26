package org.usfirst.frc.team4955.robot.commands.autonomous.cg;

import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.turn.TurnCommandGroup;
import org.usfirst.frc.team4955.robot.commands.generics.SetIgnioreGyroOnDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SideGearAndMove extends CommandGroup {
	public SideGearAndMove(double gearAngleSignum, double farOffAngleFactor) {
		addSequential(new SetIgnioreGyroOnDrive(true));

		addSequential(new MoveDistance(55, 0.8));
		addSequential(new WaitCommand(0.25));
		addSequential(new TurnCommandGroup(gearAngleSignum * 55, 0.75));
		addSequential(new WaitCommand(0.5));

		// addSequential(new AutonomousGear());
		/*
		 * addSequential(new MoveDistance(23, 0.8));
		 * // addSequential(new AutonomousGear());
		 * addSequential(new MoveDistance(20, 0.7));
		 * 
		 * // At the airship! Take my gear please!
		 * addSequential(new GearKickerCommand(Constants.AUTO_KICKER_TIME));
		 * 
		 * addSequential(new MoveDistance(-40, 0.8));
		 * addSequential(new WaitCommand(0.25));
		 * addSequential(new TurnCommandGroup(gearAngleSignum * -40, 0.9));
		 * addSequential(new MoveDistance(144, 0.9));
		 * addSequential(new WaitCommand(0.25));
		 * 
		 * if (farOffAngleFactor != 0) {
		 * addSequential(new TurnCommandGroup(farOffAngleFactor * 22, 0.7));
		 * }
		 */
		addSequential(new SetIgnioreGyroOnDrive(false));
	}
}
