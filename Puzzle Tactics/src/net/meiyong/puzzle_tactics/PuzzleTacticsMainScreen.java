package net.meiyong.puzzle_tactics;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PuzzleTacticsMainScreen extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final String TAG = PuzzleTacticsMainScreen.class.getSimpleName();
	private RoundButton rbutton;
	private PuzzleTacticsMainThread mainThread;
	
	public PuzzleTacticsMainScreen (Context context) {
		super (context);
		SurfaceHolder surfaceHolder = getHolder();
		Log.d(TAG, "Constructor");
		
		
		// Get the callback of this surfaceHolder, surfaceCreated won't be called if this is not done.
		surfaceHolder.addCallback(this);
		rbutton = new RoundButton (50, 50, 100, 50);
		mainThread = new PuzzleTacticsMainThread(getHolder(), this);
		setFocusable(true);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");
		return true;
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mainThread.setRunning(true);
		mainThread.start();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				mainThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}
	
	protected void render(Canvas canvas) {
		rbutton.draw(canvas);
	}
}