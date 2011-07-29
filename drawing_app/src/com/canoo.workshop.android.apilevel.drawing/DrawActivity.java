package com.canoo.workshop.android.apilevel.drawing;

import android.app.Activity;
import android.hardware.*;
import android.os.Bundle;

public class DrawActivity extends Activity
{

    private DrawView fDrawView;
    private AccelerometerListener fAccelerometerListener;
    public static final float ACC_THRESHOLD = 2.0f;
    private boolean fUseAccelerometer = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fDrawView = (DrawView) findViewById(R.id.DrawView);
        fAccelerometerListener = new AccelerometerListener();
        DrawState lastNonConfigurationInstance = (DrawState) getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance != null) {
            fDrawView.setDrawState(lastNonConfigurationInstance);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fDrawView.requestFocus();
        fDrawView.resumeAnimation();
        if (fUseAccelerometer) {
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(fAccelerometerListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        fDrawView.pauseAnimation();
        if (fUseAccelerometer) {
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            sensorManager.unregisterListener(fAccelerometerListener);
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return new DrawState(fDrawView.getBitmap(), fDrawView.getPointer());
    }

    private class AccelerometerListener implements SensorEventListener {
        public void onSensorChanged(SensorEvent sensorEvent) {
            float accX = sensorEvent.values[0];
            float accY = sensorEvent.values[1];
            if (accX > ACC_THRESHOLD) {
                fDrawView.setVx(-DrawView.SPEED);
            } else if (accX < -ACC_THRESHOLD) {
                fDrawView.setVx(DrawView.SPEED);
            } else {
                fDrawView.setVx(0);
            }

            if (accY > ACC_THRESHOLD) {
                fDrawView.setVy(DrawView.SPEED);
            } else if (accY < -ACC_THRESHOLD) {
                fDrawView.setVy(-DrawView.SPEED);
            } else {
                fDrawView.setVy(0);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    }
}
