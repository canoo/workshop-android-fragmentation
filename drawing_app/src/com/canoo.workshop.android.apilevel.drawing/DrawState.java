package com.canoo.workshop.android.apilevel.drawing;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * @author Andrei Socaciu
 */
class DrawState {

    Bitmap fBitmap;
    Point fPointer;

    DrawState(Bitmap bitmap, Point pointer) {
        fBitmap = bitmap;
        fPointer = pointer;
    }
    
}
