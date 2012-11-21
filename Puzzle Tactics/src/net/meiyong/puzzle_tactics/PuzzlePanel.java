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
	private int[] selectedButton = new int[2];

	public PuzzlePanel (int x, int y, int w, int h, PuzzleTacticsMainScreen mainScreen) {
		super (x, y, w, h, mainScreen);
		Log.d(TAG, "PuzzlePanel constructor x=" + x + " y=" + y + " w=" + w + " h=" + h);
		rectf = new RectF();

		resetButtonSelection();
		pupulatePuzzle();
		refreshPuzzle();
	}
	
	private void resetButtonSelection() {
		selectedButton[0] = -500;
		selectedButton[1] = -500;
	}
	
	private boolean buttonSelected() {
		if ((selectedButton[0] == -500) && (selectedButton[1] == -500)) {
			Log.d(TAG, "return false");
			return false;
		}
		Log.d(TAG, "return true");
		return true;
	}
	
	private void refreshPuzzle() {
		boolean removeButton = true;
		while (checkPuzzle(removeButton)) {
			sortPuzzle();
			pupulatePuzzle();
		}
	}
	
	private boolean checkPuzzle(boolean removeButton) {
		boolean problemFound = false;
		List<int[]> buttonsToRemove = new ArrayList<int[]>();
		int nButtonsToRemove = 0;
		
		for (int i = 0; i < buttonArray.length; i++) {
			for (int j = 0; j < buttonArray[i].length; j++) {
				// If any button is null, it should be populated first
				if (buttonArray[i][j] == null) {
					//Log.d(TAG, i + " " + j + "null");
					problemFound = true;
					return (problemFound);
				}
				
				//check vertical triples
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
							nButtonsToRemove++;
						}
						problemFound = true;
					}
				}
				
				//check_horizontal
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
							buttonsToRemove.add(new int[] {k, j});
							nButtonsToRemove++;
						}
						problemFound = true;
					}
				}
			}
		}
		
		// Remove all the buttons collected
		if (removeButton) {
			for (int k=0; k<nButtonsToRemove; k++) {
				//Log.d(TAG, "buttonToRemove i=" + buttonsToRemove.get(k)[0] + " j=" + buttonsToRemove.get(k)[1]);
				buttonArray[buttonsToRemove.get(k)[0]][buttonsToRemove.get(k)[1]] = null;
			}
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
		int buttonX = 0;
		int buttonY = 0;
		int buttonWidth = w/8;
		int buttonHeight = h/8;
		int buttonColour = 0;
		Random random = new Random();
		
		for (int i = 0; i < buttonArray.length; i++) {
			for (int j = 0; j < buttonArray[i].length; j++) {
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
			}
		}
	}
	
	@Override
	public void draw (Canvas canvas) {
		paint.setColor(Color.GREEN);
		canvas.drawRect(rectf, paint);
		rectf.top = y - h/2;
		rectf.bottom = y + h/2;
		rectf.left = x - w/2;
		rectf.right = x + w/2;
		
		for (int i = 0; i < buttonArray.length; i++) {
			for (int j = 0; j < buttonArray[i].length; j++) {
				buttonArray[i][j].draw(canvas);
			}
		}
	}
	
	@Override
	public void handleEvent (MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			for (int i = 0; i < buttonArray.length; i++) {
				for (int j = 0; j < buttonArray[i].length; j++) {
					if ((event.getX() < (buttonArray[i][j].getX() + buttonArray[i][j].getWidth()/2) &&
							event.getX() > (buttonArray[i][j].getX() - buttonArray[i][j].getWidth()/2)) && 
							(event.getY() < (buttonArray[i][j].getY() + buttonArray[i][j].getHeight()/2) &&
									event.getY() > (buttonArray[i][j].getY() - buttonArray[i][j].getHeight()/2))) {
						
						Log.d(TAG, "button touched i=" + i + " j=" + j);
						if (!buttonSelected()) {
							Log.d(TAG, "1st buttonSelected i=" + i + " j=" + j);
							selectedButton[0] = i;
							selectedButton[1] = j;
						} else {
							if ((selectedButton[0] == i) && (selectedButton[1] == j)) {
								Log.d(TAG, "1st button deSelected i=" + i + " j=" + j);
								resetButtonSelection();
							} else {
								if ((selectedButton[0] + 1 == i) || (selectedButton[0] - 1 == i) ||
										(selectedButton[1] + 1 == j) || (selectedButton[1] - 1 == j)) {
									Log.d(TAG, "2nd buttonSelected i=" + i + " j=" + j);
									Log.d(TAG, "selectedButton[0]=" + selectedButton[0] + "selectedButton[1]" + selectedButton[1]);
									RoundButton tmpButton = null;
									tmpButton = buttonArray[selectedButton[0]][selectedButton[1]];
									buttonArray[selectedButton[0]][selectedButton[1]] = buttonArray[i][j];
									buttonArray[i][j] = tmpButton;
									if (!checkPuzzle(false)) {
										Log.d(TAG, "no resolvable, undo");
										tmpButton = null;
										tmpButton = buttonArray[selectedButton[0]][selectedButton[1]];
										buttonArray[selectedButton[0]][selectedButton[1]] = buttonArray[i][j];
										buttonArray[i][j] = tmpButton;
									}
									Log.d(TAG, "tmpButton coulour= " + tmpButton.getColour());
								}
								refreshPuzzle();
								resetButtonSelection();
							}
						}
					}
				}
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
		}
		
	}
}
