package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * RoundButton.java - Class for drawing a round button sub-class of the Button class 
 * @author Dinan Yin
 *
 */
public class RoundButton extends Button {
	
	private Paint paint = new Paint();
	private RectF rectf;
	
	/**
	 * Constructor for the RoundButton class
	 * @param x Centre position x of the button
	 * @param y Centre position y of the button
	 * @param w width of the button
	 * @param h Height of the button
	 */
	public RoundButton (int x, int y, int w, int h, int c) {
		super(x,y,w,h,c);
		rectf = new RectF();
	}
	
	/**
	 * Method to draw RoundButton on canvas
	 * @param canvas Canvas to draw on
	 */
	@Override
	public void draw (Canvas canvas) {
		paint.setColor(c);
		rectf.top = y - h/2;
		rectf.bottom = y + h/2;
		rectf.left = x - w/2;
		rectf.right = x + w/2;
		canvas.drawOval(rectf, paint);
	}
}