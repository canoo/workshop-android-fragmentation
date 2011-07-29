package com.canoo.workshop.android.apilevel;

import android.hardware.Camera;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Andrei Socaciu
 */
class CameraFeatures {

    public static final String LOG_TAG = "CameraFeatures";
    private boolean fZoomSupported = false;

    CameraFeatures() {
        Camera camera = Camera.open();
        try {
            Method isZoomSupportedMethod = Camera.Parameters.class.getMethod("isZoomSupported");
            fZoomSupported = (Boolean) isZoomSupportedMethod.invoke(camera.getParameters());
            Log.d(LOG_TAG, "Zoom supported:" + fZoomSupported);
        } catch (NoSuchMethodException e) {
            Log.w(LOG_TAG, "isZoomSupported method not available");
        } catch (Exception e) {
            Log.e(LOG_TAG, "error detecting zoom support", e);
        }
        camera.release();
    }

    public boolean isZoomSupported() {
        return fZoomSupported;
    }

}
