package net.meiyong.puzzle_tactics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class PuzzleTacticsMainScreen extends SurfaceView {
	
	private static final String TAG = PuzzleTacticsMainScreen.class.getSimpleName();
	/*
	private Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
	@SuppressWarnings("deprecation")
	private int width = display.getWidth();
	@SuppressWarnings("deprecation")
	private int height = display.getHeight();
	*/
	private RoundButton rbutton;
	private Canvas canvas;
	private PuzzleTacticsMainThread mainThread;
	
	
	
	public PuzzleTacticsMainScreen (Context context) {
		super (context);
		rbutton = new RoundButton (50, 50, 100, 50);
		mainThread = new PuzzleTacticsMainThread(getHolder(), this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");
		canvas = null;
		canvas = getHolder().lockCanvas();
		canvas.drawColor(Color.BLACK);
		rbutton.draw(canvas);
		getHolder().unlockCanvasAndPost(canvas);
		return true;
	}
}