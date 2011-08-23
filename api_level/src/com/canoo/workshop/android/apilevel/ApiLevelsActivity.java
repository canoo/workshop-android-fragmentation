package com.canoo.workshop.android.apilevel;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ApiLevelsActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        CheckBox zoom = (CheckBox) findViewById(R.id.Zoom);
        CheckBox temperature = (CheckBox) findViewById(R.id.Temperature);
        CheckBox accelerometer = (CheckBox) findViewById(R.id.Accelerometer);
        CheckBox frontCamera = (CheckBox) findViewById(R.id.FrontCamera);
        CheckBox hardwareKeyboard = (CheckBox) findViewById(R.id.Keyboard);
        CheckBox multitouch = (CheckBox) findViewById(R.id.Multitouch);
        TextView apiLevel = (TextView) findViewById(R.id.ApiLevel);

        //camera
        CameraFeatures cameraFeatures = new CameraFeatures();
        zoom.setChecked(cameraFeatures.isZoomSupported());

        HardwareFeatures hardwareFeatures = new HardwareFeatures(this);
        temperature.setChecked(hardwareFeatures.isTemperatureSensorPresent());
        accelerometer.setChecked(hardwareFeatures.isAccelerometerPresent());
        frontCamera.setChecked(hardwareFeatures.isFrontFacingCameraPresent());
        hardwareKeyboard.setChecked(hardwareFeatures.isHardwareKeyboardPresent());
        multitouch.setChecked(hardwareFeatures.isMultitouchPresent());
        apiLevel.setText("Software version: " + hardwareFeatures.getApiLevel() + " (" + hardwareFeatures.getApiLevelCodename() + ") ");

        //backup service
        Button button = (Button) findViewById(R.id.BackupButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BackupProvider.backup(ApiLevelsActivity.this);
            }
        });
        if (!BackupProvider.isBackupServicePresent()) {
            button.setEnabled(false);
        }
    }
}
