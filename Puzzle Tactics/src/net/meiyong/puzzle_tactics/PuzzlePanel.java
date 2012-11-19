/**
 * 
 */
package net.meiyong.puzzle_tactics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author Dinan Yin
 *
 */
public class PuzzlePanel extends Container {
	private RectF rectf;
	private static final String TAG = PuzzlePanel.class.getSimpleName();
	private Paint paint = new Paint();
	private RoundButton[][] buttonArray = new RoundButton[8][8];
	private RoundButton[] selectedButtons = new RoundButton[2];

	public PuzzlePanel (int x, int y, int w, int h, PuzzleTacticsMainScreen mainScreen) {
		super (x, y, w, h, mainScreen);
		Log.d(TAG, "PuzzlePanel constructor x=" + x + " y=" + y + " w=" + w + " h=" + h);
		rectf = new RectF();
		pupulatePuzzle();
		while (checkPuzzle()) {
			sortPuzzle();
			pupulatePuzzle();
		}
	}
	
	private boolean checkPuzzle() {
		int i = 0;
		boolean problemFound = false;
		List<int[]> buttonsToRemove = new ArrayList<int[]>();
		
		int n = 0;
		while (i < buttonArray.length) {
			int j = 0;
			while (j < buttonArray[i].length) {
				// If any button is null, it should be populated first
				if (buttonArray[i][j] == null) {
//					Log.d(TAG, i + " " + j + "null");
					problemFound = true;
					return (problemFound);
				}
				
				//TODO need to handle this more globally, this function should just mark
				//out the triples detected and perhaps set them to null, another function
				// should drop the buttons, the the other populate it.
				
				//check horizontal triples
				if ((j > 0) && (j < (buttonArray[i].length - 1))) {
					if ((buttonArray[i][j-1].getColour() == buttonArray[i][j].getColour()) &&
							(buttonArray[i][j].getColour() == buttonArray[i][j+1].getColour())) {
						int buttonTop = j-1;
						int buttonBottom = j+1;
						if (((j - 2) >= 0 ) && (buttonArray[i][j].getColour() == buttonArray[i][j-2].getColour())) {
							buttonTop = j-2;
						}
						if (((j + 2) <= (buttonArray[i].length -1) ) && (buttonArray[i][j].getColour() == buttonArray[i][j+2].getColour())) {
							buttonBottom = j+2;
						}
						Log.d(TAG, "triple vertical line i=" + i +" j=[" + buttonTop+ "-" + buttonBottom + "] colour=" + buttonArray[i][j].getColour());
						for (int k=buttonTop; k<=buttonBottom; k++) {
							buttonsToRemove.add(new int[] {i, k});
							n++;
						}
						/*
						for (int k=i; (k-1)>=0; k--) {
							for (int l=buttonFirst; l<=buttonLast; l++) {
								buttonArray[k][l]= buttonArray[k-1][l];
								buttonArray[k-1][l] = null;
							}
						}
						*/
						problemFound = true;
						//return (problemFound);
					}
				}
				
				//check_vertical
				if ((i > 0) && (i < (buttonArray.length -1))) {
					if ((buttonArray[i-1][j].getColour() == buttonArray[i][j].getColour()) &&
							(buttonArray[i][j].getColour() == buttonArray[i+1][j].getColour())) {
						int buttonFirst = i - 1;
						int buttonLast = i + 1;
						if (((i - 2) >= 0 ) && (buttonArray[i][j].getColour() == buttonArray[i-2][j].getColour())) {
							buttonFirst = i - 2;
						}
						if (((i + 2) <= (buttonArray.length-1) ) && (buttonArray[i][j].getColour() == buttonArray[i+2][j].getColour())) {
							buttonLast = i + 2;
						}
						
						Log.d(TAG, "triple horizontal found j=" + j + " i=[" + buttonFirst + "-" + buttonLast + "] colour=" + buttonArray[i][j].getColour());
						for (int k = buttonFirst; k <= buttonLast; k++) {
//							buttonArray[k][j] = null;
							buttonsToRemove.add(new int[] {k, j});
							n++;
						}
						/*
						int buttonCount = buttonBottom - buttonTop;
						for (int k = buttonTop; (k-1)>=0; k--) {
							buttonArray[k+buttonCount][j]=buttonArray[k-1][j];
							buttonArray[k-1][j] = null;
						}
						*/
						problemFound = true;
						//return (problemFound);
					}
				}
				j++;
			}
			i++;
		}
		
		// Remove all the buttons collected
		for (int k=0; k<n; k++) {
//			Log.d(TAG, "buttonToRemove i=" + buttonsToRemove.get(k)[0] + " j=" + buttonsToRemove.get(k)[1]);
			buttonArray[buttonsToRemove.get(k)[0]][buttonsToRemove.get(k)[1]] = null;
		}
		return (problemFound);
	}
	
	private void sortPuzzle () {
		for (int i = 0; i < buttonArray.length; i++) {
			int notNullIndex = buttonArray[i].length - 1;
			for (int j = buttonArray[i].length - 1; j >=0; j--) {
				if (buttonArray[i][j] != null && (j == notNullIndex)) {
					notNullIndex--;
				} else if(buttonArray[i][j] != null && (j < notNullIndex))  {
					buttonArray[i][notNullIndex] = buttonArray[i][j];
					buttonArray[i][j]=null;
					notNullIndex--;
				}
			}
		}
	}
	
	private void pupulatePuzzle () {
		int i = buttonArray.length - 1;
		int buttonX = 0;
		int buttonY = 0;
		int buttonWidth = w/8;
		int buttonHeight = h/8;
		int buttonColour = 0;
		Random random = new Random();
		while (i >= 0) {
//			Log.d(TAG, "populatePuzzle i=" + i);
			int j=buttonArray[i].length - 1;
			while (j >= 0) {
//				Log.d(TAG, "populatePuzzle j=" + j);
//				buttonX=(x-w/2) + buttonWidth*j + buttonWidth/2;
//				buttonY=(y-h/2) + buttonHeight*i + buttonHeight/2;
				buttonX=(x-w/2) + buttonWidth*i + buttonWidth/2;
				buttonY=(y-h/2) + buttonHeight*j + buttonHeight/2;
				
				if (buttonArray[i][j] == null) {
					Log.d(TAG, "i=" + i + " j=" + j + " null");
					switch (random.nextInt(5)) {
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
					}
					//Log.d(TAG, "buttonPos x=" + buttonX + " y=" + buttonY + " width=" + buttonWidth + " height=" + buttonHeight + " colour=" + buttonColour);
					buttonArray[i][j] = new RoundButton (buttonX, buttonY, buttonWidth, buttonHeight, buttonColour);
				} else {
					//Make sure the button that has moved is placed at the right place
					buttonArray[i][j].setX(buttonX);
					buttonArray[i][j].setY(buttonY);
					buttonArray[i][j].setWidth(buttonWidth);
					buttonArray[i][j].setHeight(buttonHeight);
				}
				j--;
			}
			i--;
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
	
	@Override
	public void handleEvent (MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			int i = 0;
			while (i < buttonArray.length) {
				int j=0;
				while (j < buttonArray[i].length) {
					if ((event.getX() < (buttonArray[i][j].getX() + buttonArray[i][j].getWidth()/2) &&
							event.getX() > (buttonArray[i][j].getX() - buttonArray[i][j].getWidth()/2)) && 
							(event.getY() < (buttonArray[i][j].getY() + buttonArray[i][j].getHeight()/2) &&
									event.getY() > (buttonArray[i][j].getY() - buttonArray[i][j].getHeight()/2))) {
						
						int k=0;
						while (k < selectedButtons.length) {
							if (selectedButtons[k] == null) {
								selectedButtons[k] = buttonArray[i][j];
								Log.d(TAG, "button selected i=" + i + " j=" + j);
								break;
							} else if (selectedButtons[k] == buttonArray[i][j]){
								selectedButtons[k] = null;
								Log.d(TAG, "button deselected i=" + i + " j=" + j);
								break;
							}
							k++;
						}
					}
					j++;
				}
				i++;
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
		}
		
	}
}
