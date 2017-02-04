package org.usfirst.frc.team4955.robot.utils.input;

import edu.wpi.first.wpilibj.Joystick;


public class JoystickInput implements TeleopInput{

	Joystick controlStick;
	int axies;
	double joystick_zero_threshold;
	
	
	public JoystickInput(Joystick controlStick, int axies, double joystick_zero_threshold) {
		this.controlStick = controlStick;
		this.axies = axies;
		this.joystick_zero_threshold = joystick_zero_threshold;
	}
	
	public double getInput(){
		double inputedYaw = controlStick.getRawAxis(axies);
		
		if (Math.abs(inputedYaw) > joystick_zero_threshold) 
			return adjustForDeadZone(inputedYaw,joystick_zero_threshold);
		else
			return 0;
	}
	
	public double adjustForDeadZone(double value, double threashold){
		return (value - Math.signum(value) * threashold) / ( 1.0 - threashold);
	}
	
}
