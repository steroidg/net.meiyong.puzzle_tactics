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
 * This is the control panel of project Dao
 * @author Dinan Yin
 *
 */
public class ControlPanel extends Container {
	private Paint paint;
	private RectF rectf;
	private static final String TAG = ControlPanel.class.getSimpleName();
	private RoundButton closeButton;

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param gridUnit
	 * @param mainScreen
	 */
	protected ControlPanel(int x, int y, int w, int h, int gridUnit, DaoMainScreen mainScreen) {
		super(x, y, w, h, mainScreen);
		Log.d(TAG, "Constructor: x=" + x + " y=" + y + " w=" + w + " h=" + h);
		rectf = new RectF();
		paint = new Paint();
		closeButton = new RoundButton(x, h-gridUnit/2, gridUnit, gridUnit, Color.BLUE);
	}

	@Override
	protected void handleEvent (MotionEvent event) {
		if ((event.getX() < (closeButton.getX() + closeButton.getWidth()/2) &&
				event.getX() > (closeButton.getX() - closeButton.getWidth()/2)) && 
				(event.getY() < (closeButton.getY() + closeButton.getHeight()/2) &&
						event.getY() > (closeButton.getY() - closeButton.getHeight()/2))) {
			mainScreen.setThreadStatus(false);
		}
	}
	
	@Override
	protected void draw (Canvas canvas) {
//		Log.d(TAG,  "draw");
		paint.setColor(Color.RED);
		rectf.top = y - h/2;
		rectf.bottom = y + h/2;
		rectf.left = x - w/2;
		rectf.right = x + w/2;
		canvas.drawRect(rectf, paint);
		closeButton.draw(canvas);
	}
}