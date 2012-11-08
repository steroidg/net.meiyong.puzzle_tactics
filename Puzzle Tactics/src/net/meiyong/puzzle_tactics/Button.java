package net.meiyong.puzzle_tactics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Button {
	
	private Paint paint = new Paint();
	private int x; //the x coordinate 
	private int y; //the y coordinate
	
	
	//constructor
	public Button (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public void draw (Canvas canvas) {
		paint.setColor(Color.GREEN);
		canvas.drawCircle(x, y, 100, paint);
//		canvas.drawLine(x+100, 0, 0, y+1020, paint);
	}
}
