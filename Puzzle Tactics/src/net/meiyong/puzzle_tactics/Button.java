package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;

/**
 * Button.java - Base class for all Buttons
 * @author Dinan Yin
 */
public class Button {
	
	protected int x; //the x coordinate 
	protected int y; //the y coordinate
	protected int w; //width
	protected int h; //height
	
	/**
	 * Constructor for Button class
	 * @param x Centre position x of the button
	 * @param y Centre position y of the button
	 * @param h Height of the button
	 * @param w width of the button
	 */
	public Button (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
	
	/**
	 * Dummy method for the subclasses to draw the actual button 
	 * @param canvas Canvas to draw on
	 */
	protected void draw (Canvas canvas) {
	}
}