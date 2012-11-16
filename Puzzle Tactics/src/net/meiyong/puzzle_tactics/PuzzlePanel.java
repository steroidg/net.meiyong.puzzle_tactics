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
		pupulatePuzzle(buttonArray);
	}
	
	private void pupulatePuzzle (RoundButton[][] buttonArray) {
		int i = 0;
		int buttonX = 0;
		int buttonY = 0;
		int buttonWidth = w/8;
		int buttonHeight = h/8;
		while (i < buttonArray.length) {
			int j=0;
			Log.d(TAG, "i" + i);
			while (j < buttonArray[i].length) {
				Log.d(TAG, "j" + j);
				buttonX=(x-w/2) + buttonWidth*j + buttonWidth/2;
				buttonY=(y-h/2) + buttonHeight*i + buttonHeight/2;
				Log.d(TAG, "buttonPos x=" + buttonX + " y=" + buttonY + " width=" + buttonWidth + " height=" + buttonHeight);
				buttonArray[i][j] = new RoundButton (buttonX, buttonY, buttonWidth, buttonHeight);
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
