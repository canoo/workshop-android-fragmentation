package com.canoo.workshop.android.apilevel;

import android.hardware.Camera;

/**
 * @author Andrei Socaciu
 */
class CameraInfoProvider {

    boolean checkFrontFacingCamera() {
        //front facing camera
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                return true;
            }
        }
        return false;
    }

}
