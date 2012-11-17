/**
 * 
 */
package net.meiyong.puzzle_tactics;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * @author Dinan Yin
 *
 */
public class PuzzlePanel extends Container {
	private RectF rectf;
	private static final String TAG = PuzzlePanel.class.getSimpleName();
	private Paint paint = new Paint();
	private RoundButton[][] buttonArray = new RoundButton[8][8];

	public PuzzlePanel (int x, int y, int w, int h, PuzzleTacticsMainScreen mainScreen) {
		super (x, y, w, h, mainScreen);
		Log.d(TAG, "PuzzlePanel constructor x=" + x + " y=" + y + " w=" + w + " h=" + h);
		rectf = new RectF();
		while (checkPuzzle()) {
			pupulatePuzzle();
		}
	}
	
	private boolean checkPuzzle() {
		int i = 0;
		int buttonColour = 0;
		boolean problemFound = false;
		
		while (i < buttonArray.length) {
			int j = 0;
			while (j < buttonArray[i].length) {
				if (buttonArray[i][j] == null) {
//					Log.d(TAG, i + " " + j + "null");
					problemFound = true;
					return (problemFound);
				}
				
				//check_horizontal
				if ((j > 0) && (j < (buttonArray[i].length - 1))) {
					if ((buttonArray[i][j-1].getColour() == buttonArray[i][j].getColour()) &&
							(buttonArray[i][j].getColour() == buttonArray[i][j+1].getColour())) {
						Log.d(TAG, "triple horizontal found j=" + j + " i=" + i + " colour=" + buttonColour);
						buttonArray[i][j]=null;
						problemFound = true;
						return (problemFound);
					}
				}
				
				//check_vertical
				if ((i > 0) && (i < (buttonArray.length -1))) {
					if ((buttonArray[i-1][j].getColour() == buttonArray[i][j].getColour()) &&
							(buttonArray[i][j].getColour() == buttonArray[i+1][j].getColour())) {
						Log.d(TAG, "triple vertical found j=" + j + " i=" + i + " colour=" + buttonColour);
						buttonArray[i][j]=null;
						problemFound = true;
						return (problemFound);
					}
				}
				j++;
			}
			i++;
		}
		return (problemFound);
	}
	
	private void pupulatePuzzle () {
		int i = 0;
		int buttonX = 0;
		int buttonY = 0;
		int buttonWidth = w/8;
		int buttonHeight = h/8;
		int buttonColour = 0;
		Random random = new Random();
		while (i < buttonArray.length) {
			Log.d(TAG, "populatePuzzle i=" + i);
			int j=0;
			while (j < buttonArray[i].length) {
				if (buttonArray[i][j] == null) {
					Log.d(TAG, "populatePuzzle" + i + " " + j + "null");
					Log.d(TAG, "button null");
					buttonX=(x-w/2) + buttonWidth*j + buttonWidth/2;
					buttonY=(y-h/2) + buttonHeight*i + buttonHeight/2;
					switch (random.nextInt(6)) {
					case 0:
						buttonColour = Color.MAGENTA;
						break;
					case 1:
						buttonColour = Color.BLACK;
						break;
					case 2:
						buttonColour = Color.RED;
						break;
					case 3:
						buttonColour = Color.BLUE;
						break;
					case 4:
						buttonColour = Color.YELLOW;
						break;
					case 5:
						buttonColour = Color.CYAN;
						break;
					}
					//Log.d(TAG, "buttonPos x=" + buttonX + " y=" + buttonY + " width=" + buttonWidth + " height=" + buttonHeight + " colour=" + buttonColour);
					buttonArray[i][j] = new RoundButton (buttonX, buttonY, buttonWidth, buttonHeight, buttonColour);
				}
				j++;
			}
			i++;
		}
	}
	
	@Override
	public void draw (Canvas canvas) {
		paint.setColor(Color.GREEN);
		rectf.top = y - h/2;
		rectf.bottom = y + h/2;
		rectf.left = x - w/2;
		rectf.right = x + w/2;
		canvas.drawRect(rectf, paint);
		
		int i = 0;
		while (i < buttonArray.length) {
			int j=0;
			while (j < buttonArray[i].length) {
				buttonArray[i][j].draw(canvas);
				j++;
			}
			i++;
		}
	}
}
