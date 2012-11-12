/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * This is the Container class it contains buttons
 * @author Dinan Yin
 *
 */
public class Container {

	protected int x; //the x coordinate 
	protected int y; //the y coordinate
	protected int w; //width
	protected int h; //height
	protected PuzzleTacticsMainScreen mainScreen;
	
	public Container (int x, int y, int w, int h, PuzzleTacticsMainScreen mainScreen) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.mainScreen = mainScreen;
	}
	
	/**
	 * Set the x position of the button
	 * @param x Position x of the button
	 */
	protected void setX (int x) {
		this.x = x;
	}
	
	/**
	 * Set the y position of the button
	 * @param y Position y of the button
	 */
	protected void setY (int y) {
		this.y = y;
	}
	
	/**
	 * Set the height of the button
	 * @param x Height of the button
	 */
	protected void setHeight (int h) {
		this.h = h;
	}
	
	/**
	 * Set the width of the button
	 * @param y Width of the button
	 */
	protected void setWidth (int w) {
		this.w = w;
	}
	
	/**
	 * Get the x position of the button
	 * @return Position x of the button
	 */
	protected int getX () {
		return x;
	}
	
	/**
	 * Get the y position of the button
	 * @return Position y of the button
	 */
	protected int getY () {
		return y;
	}
	
	/**
	 * Get the height of the button
	 * @return Height of the button
	 */
	protected int getHeight () {
		return h;
	}
	
	/**
	 * Get the width of the button
	 * @return width of the button
	 */
	protected int getWidth () {
		return w;
	}
	
	protected void handleEvent (MotionEvent event) {
	}
	
	protected void draw (Canvas canvas) {
	}
}