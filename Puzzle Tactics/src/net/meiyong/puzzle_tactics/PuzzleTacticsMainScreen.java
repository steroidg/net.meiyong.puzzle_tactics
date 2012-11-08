package net.meiyong.puzzle_tactics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class PuzzleTacticsMainScreen extends SurfaceView {
	
	private static final String TAG = PuzzleTacticsMainScreen.class.getSimpleName();
	private Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
	@SuppressWarnings("deprecation")
	private int width = display.getWidth();
	@SuppressWarnings("deprecation")
	private int height = display.getHeight();
	private Button button;
	private Canvas canvas;
	
	
	
	public PuzzleTacticsMainScreen (Context context) {
		super (context);
		button = new Button (0, 0);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouch called");
		canvas = null;
		canvas = getHolder().lockCanvas();
		int buttonX = button.getX() + 10;
		int buttonY = button.getY() + 10;
		Log.d(TAG, "buttonX is " + buttonX + "buttonY is " + buttonY);
		button.setX(buttonX);
		button.setY(buttonY);
		button.draw(canvas);
		getHolder().unlockCanvasAndPost(canvas);
		return true;
	}
}