
package org.usfirst.frc.team4955.robot;

import org.usfirst.frc.team4955.robot.commands.autonomous.LeftMoveGear;
import org.usfirst.frc.team4955.robot.commands.autonomous.RightMoveGear;
import org.usfirst.frc.team4955.robot.commands.drive.JoystickDrive;
import org.usfirst.frc.team4955.robot.subsystems.BallPickUpSubsystem;
import org.usfirst.frc.team4955.robot.subsystems.DriveGameSubsystem;
import org.usfirst.frc.team4955.robot.subsystems.ThrowerSubsystem;
import org.usfirst.frc.team4955.robot.subsystems.WinchSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Subsystems
	public static DriveGameSubsystem driveSubsystem;
	public static BallPickUpSubsystem ballPickUpSystem;
	public static WinchSubsystem winchSystem;
	public static ThrowerSubsystem throwerSubsystem;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	///
	/// INIT
	///
	@Override
	public void robotInit() {
		DashboardKeys.init();

		RobotMap.init();
		subsystemInit();
		OI.init();
		initAutonomousCommands();
	}

	private void subsystemInit() {
		driveSubsystem = new DriveGameSubsystem(RobotMap.driveTrain, RobotMap.gyro);
		ballPickUpSystem = new BallPickUpSubsystem();
		winchSystem = new WinchSubsystem();
		throwerSubsystem = new ThrowerSubsystem();

		SmartDashboard.putBoolean(DashboardKeys.DRIVE, driveSubsystem.isPresent());
		SmartDashboard.putBoolean(DashboardKeys.GYRO, RobotMap.gyro != null);
		SmartDashboard.putBoolean(DashboardKeys.PICKUP, ballPickUpSystem.isPresent());
		SmartDashboard.putBoolean(DashboardKeys.THROWER, throwerSubsystem.isPresent());
		SmartDashboard.putBoolean(DashboardKeys.WINCH, winchSystem.isPresent());
	}

	private void initAutonomousCommands() {
		chooser.addDefault("Left move and gear", new LeftMoveGear());
		chooser.addObject("Right move and gear", new RightMoveGear());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	///
	/// AUTONOMOUS
	///

	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	///
	/// TELEOP
	///

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		if (driveSubsystem.isPresent()) {
			Command drive = new JoystickDrive();
			Scheduler.getInstance().add(drive);
			drive.start();
		}
	}

	@Override
	public void teleopPeriodic() {

		Scheduler.getInstance().run();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	///
	/// Other
	///
	@Override
	public void robotPeriodic() {

	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
