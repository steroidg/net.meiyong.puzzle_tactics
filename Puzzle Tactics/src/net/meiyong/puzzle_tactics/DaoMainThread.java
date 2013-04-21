/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * @author billy
 *
 */
public class DaoMainThread extends Thread{
	private boolean running;
	private SurfaceHolder surfaceHolder;
	private DaoMainScreen mainScreen;
	private static final String TAG = DaoMainThread.class.getSimpleName();

	public DaoMainThread (SurfaceHolder surfaceHolder, DaoMainScreen mainScreen) {
		super();
		this.mainScreen = mainScreen;
		this.surfaceHolder = surfaceHolder;
		
	}

	
	@Override
	public void run() {
		//Log.d(TAG, "run");
		Canvas canvas;
		while (this.running) {
			canvas = null;
			canvas = this.surfaceHolder.lockCanvas();
			mainScreen.render(canvas);
			this.surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}
	
	protected void setRunning(boolean running) {
		this.running = running;
	}
}
