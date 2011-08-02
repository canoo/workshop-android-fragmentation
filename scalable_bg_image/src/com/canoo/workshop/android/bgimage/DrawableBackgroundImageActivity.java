package com.canoo.workshop.android.bgimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class DrawableBackgroundImageActivity extends MenuActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_drawable);
        
        // set the scalable background
        Bitmap bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_image);
        View view = findViewById(R.id.drawable);
        view.setBackgroundDrawable(new ScalableDrawable(bgBitmap));
    }
	
}