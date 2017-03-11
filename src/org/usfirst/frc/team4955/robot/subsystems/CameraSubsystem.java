package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.vision.VisionThread;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraSubsystem extends Subsystem {

	public VisionThread visionThread;

	public enum CameraSide {
		FRONT, BACK
	}

	public static CvSource VisionSource;

	private CameraSide LookingSide;

	private static CameraServer CS = CameraServer.getInstance();

	public CameraSubsystem() {
		visionThread = new VisionThread();
		visionThread.setDaemon(true);
		visionThread.start();
		LookingSide = CameraSide.FRONT;
	}

	public void reverseCams() {
		if (CameraSide.FRONT.equals(LookingSide)) {
			setCameraSide(CameraSide.BACK);
		} else {
			setCameraSide(CameraSide.FRONT);
		}
	}

	public void setCameraSide(CameraSide side) {
		LookingSide = side;
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, side.name());

		if (CameraSide.FRONT.equals(LookingSide)) {
			CameraServer.getInstance().getVideo("Front").setSource(RobotMap.frontCamera);
			CameraServer.getInstance().getVideo("Back").setSource(RobotMap.backCamera);
		} else {
			CameraServer.getInstance().getVideo("Front").setSource(RobotMap.backCamera);
			CameraServer.getInstance().getVideo("Back").setSource(RobotMap.frontCamera);
		}

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
