/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

/**
 * This is the class for side panel
 * @author Dinan Yin
 *
 */
public class SidePanel extends Container{

	private static final String TAG = SidePanel.class.getSimpleName();
	private Paint paint = new Paint();
	private RectF rectf;
	private RoundButton closeButton;
	private RoundButton endTurnButton;
	
	public SidePanel (int x, int y, int w, int h, PuzzleTacticsMainScreen mainScreen) {
		super (x, y, w, h, mainScreen);
		Log.d(TAG, "SidePanel constructor x=" + x + " y=" + y + " w=" + w + " h=" + h);
		rectf = new RectF();
		closeButton = new RoundButton (x, h/8, w, h/4, Color.RED);
		endTurnButton = new RoundButton (x, 3*(h/8), w, h/4, Color.GRAY);
	}
	
	@Override
	public void handleEvent (MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			
			// This is the endTurn button
			if ((event.getX() < (endTurnButton.getX() + endTurnButton.getWidth()/2) &&
					event.getX() > (endTurnButton.getX() - endTurnButton.getWidth()/2)) && 
					(event.getY() < (endTurnButton.getY() + endTurnButton.getHeight()/2) &&
							event.getY() > (endTurnButton.getY() - endTurnButton.getHeight()/2))) {
				mainScreen.newTurn();
			}
			
			// This is the close button
			if ((event.getX() < (closeButton.getX() + closeButton.getWidth()/2) &&
					event.getX() > (closeButton.getX() - closeButton.getWidth()/2)) && 
					(event.getY() < (closeButton.getY() + closeButton.getHeight()/2) &&
							event.getY() > (closeButton.getY() - closeButton.getHeight()/2))) {
				mainScreen.setThreadStatus(false);
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
		}
	}
	
	@Override
	public void draw (Canvas canvas) {
		paint.setColor(Color.BLUE);
		rectf.top = y - h/2;
		rectf.bottom = y + h/2;
		rectf.left = x - w/2;
		rectf.right = x + w/2;
		canvas.drawRect(rectf, paint);
		closeButton.draw(canvas);
		endTurnButton.draw(canvas);
	}
}
