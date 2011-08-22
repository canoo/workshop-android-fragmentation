package com.canoo.workshop.android.apilevel;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;

/**
 * @author Andrei Socaciu
 */
class HardwareFeatures {

    public static final String LOG_TAG = "HardwareFeatures";
    private boolean fTemperatureSensorPresent;
    private boolean fAccelerometerPresent;
    private boolean fFrontFacingCameraPresent;
    private boolean fHardwareKeyboardPresent;
    private boolean fMultitouchPresent;
    private int fApiLevel;
    private String fApiLevelCodename;

    HardwareFeatures(Context context) {
        //sensors
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor temperatureSensor = manager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        Log.d(LOG_TAG, "Temperature Sensor: " + temperatureSensor);
        Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.d(LOG_TAG, "Accelerometer: " + temperatureSensor);
        fTemperatureSensorPresent = temperatureSensor != null;
        fAccelerometerPresent = accelerometer != null;

        //CameraInfo class does not exist prior to api level 9
        try {
            fFrontFacingCameraPresent = new CameraInfoProvider().checkFrontFacingCamera();
        } catch (NoClassDefFoundError ve) {
            //api level < 9
            Log.d(LOG_TAG, "NoClassDefFound CameraInfo");
        } catch (VerifyError ve) {
            //api level < 9
            Log.d(LOG_TAG, "VerifyError CameraInfo");
        }

        //hardware keyboard
        fHardwareKeyboardPresent = context.getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS;

        try {
            fMultitouchPresent = PackageManagerProvider.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH, context);
        } catch (VerifyError ve) {
        }

        fApiLevel = Build.VERSION.SDK_INT;
        fApiLevelCodename = Build.VERSION.RELEASE;

    }

    boolean isTemperatureSensorPresent() {
        return fTemperatureSensorPresent;
    }

    boolean isAccelerometerPresent() {
        return fAccelerometerPresent;
    }

    boolean isFrontFacingCameraPresent() {
        return fFrontFacingCameraPresent;
    }

    boolean isHardwareKeyboardPresent() {
        return fHardwareKeyboardPresent;
    }

    boolean isMultitouchPresent() {
        return fMultitouchPresent;
    }

    int getApiLevel() {
        return fApiLevel;
    }

    String getApiLevelCodename() {
        return fApiLevelCodename;
    }
}
