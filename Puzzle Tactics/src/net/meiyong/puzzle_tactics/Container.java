/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * This is the base class for containers that can handle events
 * @author Dinan Yin
 */
public class Container {

	protected int x; //the x coordinate 
	protected int y; //the y coordinate
	protected int w; //width
	protected int h; //height
	protected DaoMainScreen mainScreen;
	
	/**
	 * Constructor for Container class
	 * @param x Centre position x of the container
	 * @param y Centre position y of the container
	 * @param w width of the container
	 * @param h Height of the container
	 * @param mainScreen the screen upon which this container is created
	 */
	public Container (int x, int y, int w, int h, DaoMainScreen mainScreen) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.mainScreen = mainScreen;
	}
	
	/**
	 * Set the x position of the container
	 * @param x Position x of the container
	 */
	protected void setX (int x) {
		this.x = x;
	}
	
	/**
	 * Set the y position of the container
	 * @param y Position y of the container
	 */
	protected void setY (int y) {
		this.y = y;
	}
	
	/**
	 * Set the height of the container
	 * @param x Height of the container
	 */
	protected void setHeight (int h) {
		this.h = h;
	}
	
	/**
	 * Set the width of the container
	 * @param y Width of the container
	 */
	protected void setWidth (int w) {
		this.w = w;
	}
	
	/**
	 * Get the x position of the container
	 * @return Position x of the container
	 */
	protected int getX () {
		return x;
	}
	
	/**
	 * Get the y position of the container
	 * @return Position y of the container
	 */
	protected int getY () {
		return y;
	}
	
	/**
	 * Get the height of the container
	 * @return Height of the container
	 */
	protected int getHeight () {
		return h;
	}
	
	/**
	 * Get the width of the container
	 * @return width of the container
	 */
	protected int getWidth () {
		return w;
	}
	
	/**
	 * Dummy method for event handling
	 * @param event MotionEvent to handle 
	 */
	protected void handleEvent (MotionEvent event) {
	}
	
	/**
	 * Dummy method for the subclasses to draw the actual container
	 * @param canvas Canvas to draw on
	 */
	protected void draw (Canvas canvas) {
	}
}