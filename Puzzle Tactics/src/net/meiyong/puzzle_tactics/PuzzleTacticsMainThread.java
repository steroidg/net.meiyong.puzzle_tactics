/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * PuzzleTacticsMainThread.java - This is the main thread that runs all the important loops.
 * @author Dinan Yin
 *
 */
public class PuzzleTacticsMainThread extends Thread {
	
	private static final String TAG = PuzzleTacticsMainThread.class.getSimpleName();
	private SurfaceHolder surfaceHolder;
	private PuzzleTacticsMainScreen mainScreen;
	private boolean running;

	public PuzzleTacticsMainThread (SurfaceHolder surfaceHolder, PuzzleTacticsMainScreen mainScreen) {
		super();
		Log.d(TAG, "Constructor");
		this.surfaceHolder = surfaceHolder;
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void run() {
		Log.d(TAG, "run");
		Canvas canvas;
		while (this.running) {
			canvas = null;
			canvas = this.surfaceHolder.lockCanvas();
			canvas.drawColor(Color.BLACK);
			mainScreen.render(canvas);
			this.surfaceHolder.unlockCanvasAndPost(canvas);
		}
		Log.d(TAG, "run3");
	}
	 
	public void setRunning(boolean running) {
		this.running = running;
	}
}
