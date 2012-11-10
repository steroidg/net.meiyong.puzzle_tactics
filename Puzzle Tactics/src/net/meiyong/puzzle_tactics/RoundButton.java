package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * RoundButton.java - Class for drawing a round button sub-class of the Button class 
 * @author Dinan Yin
 *
 */
public class RoundButton extends Button {
	
	private static final String TAG = RoundButton.class.getSimpleName();
	private Paint paint = new Paint();
	private RectF rectf;
	
	/**
	 * Constructor for the RoundButton class
	 * @param x Centre position x of the button
	 * @param y Centre position y of the button
	 * @param h Height of the button
	 * @param w width of the button
	 */
	public RoundButton (int x, int y, int h, int w) {
		super(x,y,h,w);
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		rectf = new RectF();
	}
	
	/**
	 * Method to draw RoundButton on canvas
	 * @param canvas Canvas to draw on
	 */
	@Override
	public void draw (Canvas canvas) {
		paint.setColor(Color.YELLOW);
		Log.d(TAG, "this.x " + x + " this.y is " + y);
		rectf.top = x - h/2;
		rectf.bottom = x + h/2;
		rectf.left = y - w/2;
		rectf.right = y + w/2;
		canvas.drawOval(rectf, paint);
	}
}