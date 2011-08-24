package com.canoo.workshop.android.apilevel.drawing;

import android.content.Context;
import android.graphics.*;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This View allows the user to draw lines by using Keyboard input.
 * <p/>
 * onKeyDown and onKeyUp are overridden, drawing happens between these calls (starting when 'onKeyDown'
 * is called for a specific input, line is drawn until 'onKeyUp' is called for this key)
 * <p/>
 * The drawing is handled in a separate animation thread (using a Timer), after each animation step
 * the view is invalidated and repainted into the UI
 *
 * @author Andrei Socaciu
 */
public class DrawView extends View {
    /**
     * The image being drawn
     */
    private Bitmap fBitmap;
    /**
     * Canvas used to draw on the Bitmap
     */
    private Canvas fBitmapCanvas;
    /**
     * Current drawing pointer location
     */
    private Point fPointer;
    /**
     * Paint used for drawing the Pointer
     */
    private Paint fPointerPaint;
    /**
     * Paint used for drawing the Line
     */
    private Paint fLinePaint;
    /**
     * Animation timer
     */
    private Timer fTimer;
    /**
     * TimerTask handling the input/drawing into the image
     */
    private InputTask fInputTask;
    /**
     * Callback used to invalidate the view
     */
    private Handler fHandler;

    /**
     * Drawing speed
     */
    public static final int SPEED = 30;// px / sec
    /**
     * Animation speed
     */
    public static final int FPS = 30;
    /**
     * Lock used for drawing, to avoid concurrency problems between the main thread and
     * the timer thread
     */
    private final Object fDrawLock = new Object();
    
    /**
     * Whether key presses should be captured and used for drawing
     */
    private boolean fUseKeyboardInput = false;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initBitmap() {
        synchronized (fDrawLock) {
            //Initialize image to the size of the view
            fBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
            fPointer = new Point(getWidth() / 2, getHeight() / 2);
            fBitmap.eraseColor(Color.WHITE);
            initOthers();
        }
    }

    private void initOthers() {
        fBitmapCanvas = new Canvas(fBitmap);
        fPointerPaint = new Paint();
        fPointerPaint.setColor(Color.RED);
        fLinePaint = new Paint();
        fLinePaint.setColor(Color.BLACK);
        fLinePaint.setStrokeWidth(3.0f);
    }

    void setVx(int vx) {
        fInputTask.fVx = vx;
    }

    void setVy(int vy) {
        fInputTask.fVy = vy;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (fUseKeyboardInput) {
	        if (keyCode == KeyEvent.KEYCODE_Q) {
	            fInputTask.fVy = -SPEED;
	            return true;
	        } else if (keyCode == KeyEvent.KEYCODE_A) {
	            fInputTask.fVy = SPEED;
	            return true;
	        } else if (keyCode == KeyEvent.KEYCODE_O) {
	            fInputTask.fVx = -SPEED;
	            return true;
	        } else if (keyCode == KeyEvent.KEYCODE_P) {
	            fInputTask.fVx = SPEED;
	            return true;
	        }
    	}
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
    	if (fUseKeyboardInput) {
	        if (keyCode == KeyEvent.KEYCODE_Q) {
	            fInputTask.fVy = 0;
	            return true;
	        } else if (keyCode == KeyEvent.KEYCODE_A) {
	            fInputTask.fVy = 0;
	            return true;
	        } else if (keyCode == KeyEvent.KEYCODE_O) {
	            fInputTask.fVx = 0;
	            return true;
	        } else if (keyCode == KeyEvent.KEYCODE_P) {
	            fInputTask.fVx = 0;
	            return true;
	        }
    	}
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (fDrawLock) {
            canvas.drawBitmap(fBitmap, new Matrix(), null);
        }
        canvas.drawCircle(fPointer.x, fPointer.y, 5.0f, fPointerPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (fBitmap == null) {
            initBitmap();
        }
    }

    void resumeAnimation() {
        fInputTask = new InputTask();
        fHandler = new Handler();

        fTimer = new Timer();
        fTimer.scheduleAtFixedRate(fInputTask, 0, 1000 / FPS);
    }

    void pauseAnimation() {
        fTimer.cancel();
        fTimer = null;
    }

    public Bitmap getBitmap() {
        return fBitmap;
    }

    public Point getPointer() {
        return fPointer;
    }

    void setDrawState(DrawState drawState) {
        fBitmap = drawState.fBitmap;
        fPointer = drawState.fPointer;
        initOthers();
    }
    
    void setUseKeyboardInput(boolean useKeyboardInput) {
		this.fUseKeyboardInput = useKeyboardInput;
	}


	class InputTask extends TimerTask {

        private int fVx = 0;
        private int fVy = 0;
        private Point fPreviousPosition = new Point();
        private DrawView.InvalidateRunnable fInvalidateRunnable = new InvalidateRunnable();

        @Override
        public void run() {
            if (fBitmap == null) {
                //bitmap has not been initialized yet
                return;
            }
            //compute distance on the 2 axes
            int dx = fVx / FPS;
            int dy = fVy / FPS;
            //retain previous position to draw a line to the current position
            fPreviousPosition.x = fPointer.x;
            fPreviousPosition.y = fPointer.y;
            //move pointer
            fPointer.set(fPointer.x + dx, fPointer.y + dy);
            //check bounds
            if (fPointer.x < 0) {
                fPointer.x = 0;
            }
            if (fPointer.y < 0) {
                fPointer.y = 0;
            }
            if (fPointer.x > getWidth()) {
                fPointer.x = getWidth();
            }
            if (fPointer.y > getHeight()) {
                fPointer.y = getHeight();
            }
            //if we moved, draw line and invalidate
            if (!fPreviousPosition.equals(fPointer)) {
                synchronized (fDrawLock) {
                    fBitmapCanvas.drawLine(fPreviousPosition.x, fPreviousPosition.y, fPointer.x, fPointer.y, fLinePaint);
                }
            }
            fHandler.post(fInvalidateRunnable);
        }
    }

    class InvalidateRunnable implements Runnable {
        public void run() {
            invalidate();
        }
    }

}
