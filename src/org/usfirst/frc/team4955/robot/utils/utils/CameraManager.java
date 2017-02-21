package org.usfirst.frc.team4955.robot.utils.utils;

import java.awt.Image;
import java.util.HashMap;

public class CameraManager {

	boolean frameFixedByUser = false;

	// Cam names with it's camera Id
	HashMap<String, Integer> cameras;

	public int currentCamId = -1;

	private static CameraManager instance = new CameraManager();

	public static CameraManager getInstance() {
		return instance;
	}

	protected CameraManager() {
		cameras = new HashMap<>();
	}

	public void robotInit() {

	}

	public void InitCam(String camName) {
		/*
		 * try{ int camId = NIVision.IMAQdxOpenCamera(camName,
		 * NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		 * cameras.put(camName, camId);
		 * 
		 * }catch (VisionException ve){ printError(camName, "InitCam", ve); }
		 */
	}

	/*
	 * private void printError(String camName, String calledMethod,
	 * VisionException ve) { String message =
	 * "ERROR AT : CameraManager."+calledMethod+"(" + camName + ")";
	 * 
	 * if(ve.getMessage().contains("1074360310")){ message +=
	 * "\tCamera is already in use. (Called init twice with the same cam?)";
	 * }else if(ve.getMessage().contains("1074360311")){ message +=
	 * "\tCamera not found."; }else{ message += ve.getMessage(); }
	 * System.err.println(message); }
	 */

	/**
	 * CAll at teleopInit or AutonomousInit
	 */
	public void init() {
		/*
		 * for (int camId : cameras.values()) { try{
		 * //NIVision.IMAQdxStartAcquisition(camId); }catch(VisionException ve){
		 * printError(camId + "", "init", ve); } }
		 */
	}

	public void SwitchCamera(String cameraName) {
		if (!cameras.containsKey(cameraName)) {
			System.err.println("ERROR : CameraManager doesnt know cam : " + cameraName);
			return;
		}

		// Stop Current camera
		/*
		 * if(currentCamId != -1) NIVision.IMAQdxStopAcquisition(currentCamId);
		 * 
		 * //Star the new Cam currentCamId = cameras.get(cameraName);
		 * frameFixedByUser = false;
		 * 
		 * NIVision.IMAQdxConfigureGrab(currentCamId);
		 * NIVision.IMAQdxStartAcquisition(currentCamId);
		 */
	}

	public void StopDisplayCamera() {
		currentCamId = -1;
	}

	public Image GetCurrentFrame() {
		/*
		 * NIVision.IMAQdxGrab(currentCamId, currentFrame, 0); return
		 * currentFrame;
		 */
		return null;
	}

	public void periodic() {
		if (currentCamId == -1)
			return;

		if (!frameFixedByUser)
			GetCurrentFrame();

		// CameraServer.getInstance().setImage(currentFrame);
	}

	public void SetCamImage(String cameraName, Image camImage) {
		if (!cameras.containsKey(cameraName))
			return;

		// int camId = cameras.get(cameraName);
		// if(camId == currentCamId){
		// currentFrame = camImage;
		frameFixedByUser = true;
		// }
	}

	public void SetCamImage(Image camImage) {
		// currentFrame = camImage;
		frameFixedByUser = true;
	}
}