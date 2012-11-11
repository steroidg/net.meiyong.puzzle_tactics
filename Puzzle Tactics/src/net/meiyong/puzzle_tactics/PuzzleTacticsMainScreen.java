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
	private RoundButton closeButton;
	private RoundButton endTurnButton;
	private RoundButton moveButton;
	private PuzzleTacticsMainThread mainThread;
	private int nTurn;
	
	public PuzzleTacticsMainScreen (Context context) {
		super (context);
		SurfaceHolder surfaceHolder = getHolder();
		Log.d(TAG, "Constructor");
		
		// Get the callback of this surfaceHolder, surfaceCreated won't be called if this is not done.
		surfaceHolder.addCallback(this);
		closeButton = new RoundButton (50, 25, 100, 50);
		endTurnButton = new RoundButton (200, 25, 100, 50);
		moveButton = new RoundButton (400, 25, 100, 50);
		mainThread = new PuzzleTacticsMainThread(getHolder(), this);
		nTurn = 0;
		setFocusable(true);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			
			if ((event.getX() < (endTurnButton.getX() + endTurnButton.getWidth()/2) &&
					event.getX() > (endTurnButton.getX() - endTurnButton.getWidth()/2)) && 
					(event.getY() < (endTurnButton.getY() + endTurnButton.getHeight()/2) &&
							event.getY() > (endTurnButton.getY() - endTurnButton.getHeight()/2))) {
				this.newTurn();
			}
			// This is the close button
			
			if ((event.getX() < (closeButton.getX() + closeButton.getWidth()/2) &&
					event.getX() > (closeButton.getX() - closeButton.getWidth()/2)) && 
					(event.getY() < (closeButton.getY() + closeButton.getHeight()/2) &&
							event.getY() > (closeButton.getY() - closeButton.getHeight()/2))) {
				mainThread.setRunning(false);
				((Activity)getContext()).finish();
			}
			
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
		}
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
	
	protected void newTurn () {
		Log.d(TAG, "nTurn = " + nTurn);
		nTurn++;
		moveButton.setY(moveButton.getY() + 10);
	}
	
	protected void render(Canvas canvas) {
		closeButton.draw(canvas);
		moveButton.draw(canvas);
		endTurnButton.draw(canvas);
	}
}