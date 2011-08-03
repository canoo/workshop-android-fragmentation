package com.canoo.workshop.android.prescaling;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PrescalingActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final ImageView imageView = (ImageView) findViewById(R.id.icon);
        final TextView textView = (TextView) findViewById(R.id.text);
        
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		        String text = imageView.getWidth()+", "+imageView.getHeight()+" | "+imageView.getDrawable().getBounds();
				textView.setText(text);
				Log.i("measure", text);
			}
		});
        
    }
}