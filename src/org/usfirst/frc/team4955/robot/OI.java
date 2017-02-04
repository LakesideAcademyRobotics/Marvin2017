package org.usfirst.frc.team4955.robot;

import org.usfirst.frc.team4955.robot.utils.driveTrain.InverseDriveTrainCommand;
import org.usfirst.frc.team4955.robot.utils.driveTrain.SetDriveTrainMaxOutputCommand;
import org.usfirst.frc.team4955.robot.utils.input.GyroRatationInput;
import org.usfirst.frc.team4955.robot.utils.input.JoystickInput;
import org.usfirst.frc.team4955.robot.utils.input.TeleopInput;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadAxis;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick mainJoystick;
	private static TeleopInput controlerXInput;
	public static GyroRatationInput controlerGyroInput;
	public static TeleopInput controlerYInput;
	
	public static GamepadButton REVERSE_DRIVE_BUTTOM_NUMBER = GamepadButton.A;
	public static GamepadButton SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER = GamepadButton.B;
	public static GamepadButton DRIVE_OUTPUT_LOWER_BUTTOM_NUMBER = GamepadButton.LB;
	public static GamepadButton DRIVE_OUTPUT_RAISE_BUTTOM_NUMBER = GamepadButton.RB;
	
	public static void init(){
		mainJoystick = new Joystick(0);
		
		controlerXInput = new JoystickInput(mainJoystick, GamepadAxis.LeftX.value(), 0.14);
		controlerGyroInput = new GyroRatationInput(controlerXInput);
		controlerYInput = new JoystickInput(mainJoystick, GamepadAxis.LeftY.value(), 0.14);
		
		RobotMap.driveTrain.xInput = controlerGyroInput;
		RobotMap.driveTrain.yInput = controlerYInput;
		
		
		JoystickButton driveCommand = null;
		
		driveCommand = new JoystickButton(mainJoystick, REVERSE_DRIVE_BUTTOM_NUMBER.value());
        driveCommand.whenPressed(new InverseDriveTrainCommand(RobotMap.driveTrain));

        driveCommand = new JoystickButton(mainJoystick, SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER.value());
        driveCommand.toggleWhenActive(new SetDriveTrainMaxOutputCommand(RobotMap.driveTrain, 0.2));
        //driveCommand.whileHeld(new SetDriveTrainMaxOutputCommand(RobotMap.driveTrain, 0.05));
	}
}
