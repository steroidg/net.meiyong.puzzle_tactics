package net.meiyong.puzzle_tactics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PuzzleTacticsMainScreen extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final String TAG = PuzzleTacticsMainScreen.class.getSimpleName();
	private SidePanel sidePanel;
	private PuzzlePanel puzzlePanel;
	private PuzzleTacticsMainThread mainThread;
	private int nTurn;
	private int screenWidth;
	private int screenHeight;
	private Character playerCharacter;
	
	/**
	 * Constructor of PuzzleTacticsMainScreen class
	 * @param context Context of the SurfaceView class
	 */
	public PuzzleTacticsMainScreen (Context context) {
		super (context);
		Log.d(TAG, "Constructor");
		SurfaceHolder surfaceHolder = getHolder();
		
		// Get the callback of this surfaceHolder, surfaceCreated won't be called if this is not done.
		surfaceHolder.addCallback(this);
		mainThread = new PuzzleTacticsMainThread(getHolder(), this);
		nTurn = 0;
		setFocusable(true);
		
	}
	
	/**
	 * Event handler for onTouchEvent
	 * @param event MotionEvent
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
		if ((event.getX() < (sidePanel.getX() + sidePanel.getWidth()/2) &&
				event.getX() > (sidePanel.getX() - sidePanel.getWidth()/2)) && 
				(event.getY() < (sidePanel.getY() + sidePanel.getHeight()/2) &&
						event.getY() > (sidePanel.getY() - sidePanel.getHeight()/2))) {
			sidePanel.handleEvent(event);
		}
		
		if ((event.getX() < (puzzlePanel.getX() + puzzlePanel.getWidth()/2) &&
				event.getX() > (puzzlePanel.getX() - puzzlePanel.getWidth()/2)) && 
				(event.getY() < (puzzlePanel.getY() + puzzlePanel.getHeight()/2) &&
						event.getY() > (puzzlePanel.getY() - puzzlePanel.getHeight()/2))) {
			puzzlePanel.handleEvent(event);
		}
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
		}
		return true;
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenWidth=getWidth();
		screenHeight=getHeight();
		playerCharacter = new Character(1, 1, 1, 1, 1, 1);
		sidePanel = new SidePanel (screenWidth/8/2, screenHeight/2, screenWidth/8, screenHeight, playerCharacter, this);
		puzzlePanel = new PuzzlePanel ((screenWidth-screenWidth/8)/2+screenWidth/8, screenHeight/2, (screenWidth-screenWidth/8), screenHeight, playerCharacter, this);
		boolean turnOn = true;
		this.setThreadStatus(turnOn);
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
	
	/**
	 * Render the output onto the canvas
	 * @param canvas Canvas to draw on
	 */
	protected void render(Canvas canvas) {
//		Log.d(TAG, "render");
		sidePanel.draw(canvas);
		puzzlePanel.draw(canvas);
	}
		
	protected void newTurn () {
		Log.d(TAG, "nTurn = " + nTurn);
		nTurn++;
	}
	
	/**
	 * Sets the status of the thread between on and off
	 * @param turnOn boolean variable of whether to turn on (true) or off (false) the thread
	 */
	protected void setThreadStatus(boolean turnOn) {
		if (turnOn) {
			mainThread.setRunning(true);
			mainThread.start();
		} else {
			mainThread.setRunning(false);
			((Activity)getContext()).finish();
		}
	}
}