package org.usfirst.frc.team4955.robot.utils.utils.commands;

import org.usfirst.frc.team4955.robot.utils.utils.CameraManager;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeShownCamera extends Command{

	String camName;
	
	public ChangeShownCamera(String camName) {
		this.camName = camName;
	}
	
	@Override
	protected void initialize() {
		CameraManager.getInstance().SwitchCamera(camName);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
