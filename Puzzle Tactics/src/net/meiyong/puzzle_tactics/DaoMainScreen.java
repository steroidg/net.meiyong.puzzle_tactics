/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Main screen of project Dao
 * <p>This is the real base class of the project. It starts the display, thread and sound.
 * @author Dinan Yin
 */
public class DaoMainScreen extends SurfaceView implements SurfaceHolder.Callback {
	private DaoMainThread mainThread;
	private ControlPanel controlPanel;
	private static final String TAG = DaoMainScreen.class.getSimpleName();
	private int screenWidth;
	private int screenHeight;
	// Magic number of the size of each grid, always a square
	private static final int gridUnit = 180;
	
	/**
	 * Constructor of PuzzleTacticsMainScreen class
	 * @param context Context of the SurfaceView class
	 */
	public DaoMainScreen(Context context) {
		super(context);
		SurfaceHolder surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		mainThread = new DaoMainThread(getHolder(), this);
		setFocusable(true);
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		final boolean turnOn = true;
		this.setThreadStatus(turnOn);
		screenWidth=getWidth();
		screenHeight=getHeight();
		
		// Generate the control panel with buttons
		int cpanel_x = screenWidth - gridUnit / 2;
		int cpanel_y = screenHeight / 2;
		int cpanel_width = gridUnit;
		int cpanel_height = screenHeight;
		controlPanel = new ControlPanel (cpanel_x, cpanel_y, cpanel_width, cpanel_height, this);
//		navigationView = new NavigationView (screenWidth/2, screenHeight/2)
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
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
		//Log.d(TAG,  "render");
		canvas.drawColor(Color.WHITE);
		controlPanel.draw(canvas);
	}
	
	protected void setThreadStatus(boolean turnOn) {
		Log.d(TAG, "method: setThreadStatus turnOn: " + turnOn);
		
		if (turnOn) {
			mainThread.setRunning(true);
			mainThread.start();
		} else {
			mainThread.setRunning(false);
			((Activity)getContext()).finish();
		}
	}

}