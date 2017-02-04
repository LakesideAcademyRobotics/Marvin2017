package org.usfirst.frc.team4955.robot.utils.input;

import edu.wpi.first.wpilibj.Joystick;


public class DualAxisInput implements TeleopInput {

	Joystick controlStickPositif;
	int axiesPositif;
	Joystick controlStickNegatif;
	int axiesNegatif;
	double joystickZeroThresholdPositif;
	double joystickZeroThresholdNegatif;
	
	public DualAxisInput(Joystick controlStickNegatif, int axiesNegatif, Joystick controlStickPositif, int axiesPositif, double joystickZeroThresholdPositif, double joystickZeroThresholdNegatif) {
		this.controlStickPositif = controlStickPositif;
		this.axiesPositif = axiesPositif;
		this.controlStickNegatif = controlStickNegatif;
		this.axiesNegatif = axiesNegatif;
		this.joystickZeroThresholdPositif = joystickZeroThresholdPositif;
		this.joystickZeroThresholdNegatif = joystickZeroThresholdNegatif;
	}

	@Override
	public double getInput() {
		double inputedPositif = controlStickPositif.getRawAxis(axiesPositif);
		double inputedNegatif = controlStickNegatif.getRawAxis(axiesNegatif);
		
		if(inputedPositif > joystickZeroThresholdPositif || inputedNegatif > joystickZeroThresholdNegatif){
			inputedPositif = adjustForDeadZone(inputedPositif,joystickZeroThresholdPositif);
			inputedNegatif = adjustForDeadZone(inputedNegatif,joystickZeroThresholdNegatif);
			return inputedPositif - inputedNegatif;
		}
			
		else
			return 0;
	}
	
	public double adjustForDeadZone(double value, double threashold){
		return (value - Math.signum(value) * threashold) / ( 1.0 - threashold);
	}

}

