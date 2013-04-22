/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * @author billy
 *
 */
public class NavigationView extends Container{
	private Paint paint;
	private RectF rectf;
	private static final String TAG = ControlPanel.class.getSimpleName();
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param mainScreen
	 */
	public NavigationView(int x, int y, int w, int h, int gridUnit,DaoMainScreen mainScreen) {
		super(x, y, w, h, mainScreen);
		Log.d(TAG, "Constructor: x=" + x + " y=" + y + " w=" + w + " h=" + h);
		rectf = new RectF();
		paint = new Paint();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void draw (Canvas canvas) {
//		Log.d(TAG,  "draw");
		paint.setColor(Color.YELLOW);
		rectf.top = y - h/2;
		rectf.bottom = y + h/2;
		rectf.left = x - w/2;
		rectf.right = x + w/2;
		canvas.drawRect(rectf, paint);
	}
}
