package org.usfirst.frc.team4955.robot;

import org.usfirst.frc.team4955.robot.commands.ball.StartPickup;
import org.usfirst.frc.team4955.robot.commands.ball.StopPickup;
import org.usfirst.frc.team4955.robot.commands.drive.InverseDriveTrainCommand;
import org.usfirst.frc.team4955.robot.commands.drive.SetDriveTrainMaxOutputCommand;
import org.usfirst.frc.team4955.robot.commands.drive.Turn135;
import org.usfirst.frc.team4955.robot.commands.thrower.ThowerStartSquence;
import org.usfirst.frc.team4955.robot.commands.thrower.ThrowerStop;
import org.usfirst.frc.team4955.robot.commands.winch.WinchLower;
import org.usfirst.frc.team4955.robot.commands.winch.WinchRaise;
import org.usfirst.frc.team4955.robot.utils.input.DualAxisInput;
import org.usfirst.frc.team4955.robot.utils.input.JoystickInput;
import org.usfirst.frc.team4955.robot.utils.input.TeleopInput;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadAxis;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick mainJoystick;
	public static TeleopInput controlerRotationInput;
	public static TeleopInput controlerMovementInput;

	public static GamepadButton REVERSE_DRIVE_BUTTOM_NUMBER = GamepadButton.A;
	public static GamepadButton SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER = GamepadButton.B;
	public static GamepadButton BALL_THROWER_START = GamepadButton.LB;
	public static GamepadButton BALL_THROWER_STOP = GamepadButton.RB;
	public static GamepadButton BALL_PICKUP = GamepadButton.X;
	public static GamepadButton STOP_BALL_PICKUP = GamepadButton.Y;
	public static GamepadButton WINCH_RAISE = GamepadButton.Start;
	public static GamepadButton WINCH_LOWER = GamepadButton.Back;
	public static GamepadButton THROWER_STOP = GamepadButton.LB;
	public static GamepadButton TURN_BUTTON = GamepadButton.Start;

	public static void init() {

		// Set up the joystick
		mainJoystick = new Joystick(0);

		controlerMovementInput = new JoystickInput(mainJoystick, GamepadAxis.LeftY.value(), 0.14);
		controlerRotationInput = new DualAxisInput(mainJoystick, GamepadAxis.RightTrigger.value(), mainJoystick,
				GamepadAxis.LeftTrigger.value(), 0, 0);

		if (mainJoystick == null || mainJoystick.getName().equals("")) {
			// We dont have joystick!
			System.err.println("There is no joystick");
			return;
		}
		JoystickButton command = null;

		// Drive
		if (Robot.driveSubsystem.isPresent()) {
			command = new JoystickButton(mainJoystick, REVERSE_DRIVE_BUTTOM_NUMBER.value());
			command.whenPressed(new InverseDriveTrainCommand());
			command = new JoystickButton(mainJoystick, SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER.value());
			command.toggleWhenActive(new SetDriveTrainMaxOutputCommand(0.2));
		}

		// Ball pick-up
		if (Robot.ballPickUpSystem.isPresent()) {
			command = new JoystickButton(mainJoystick, BALL_PICKUP.value());
			command.toggleWhenActive(new StartPickup());
			command = new JoystickButton(mainJoystick, STOP_BALL_PICKUP.value());
			command.toggleWhenActive(new StopPickup());
		}

		// Ball Thrower
		if (Robot.throwerSubsystem.isPresent()) {
			command = new JoystickButton(mainJoystick, BALL_THROWER_START.value());
			command.toggleWhenActive(new ThowerStartSquence());
			command = new JoystickButton(mainJoystick, BALL_THROWER_STOP.value());
			command.toggleWhenActive(new ThrowerStop());
		}

		// Turn
		command = new JoystickButton(mainJoystick, TURN_BUTTON.value());
		command.toggleWhenActive(new Turn135());

		// Winch
		if (Robot.winchSystem.isPresent()) {
			command = new JoystickButton(mainJoystick, WINCH_RAISE.value());
			command.toggleWhenActive(new WinchRaise());

			command = new JoystickButton(mainJoystick, WINCH_LOWER.value());
			command.toggleWhenActive(new WinchLower());
		}

		SmartDashboard.putBoolean(DashboardKeys.INIT_OI, true);
	}
}
