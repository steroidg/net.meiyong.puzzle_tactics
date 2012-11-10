/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.view.SurfaceHolder;

/**
 * PuzzleTacticsMainThread.java - This is the main thread that runs all the important loops.
 * @author Dinan Yin
 *
 */
public class PuzzleTacticsMainThread extends Thread {
	
	private SurfaceHolder surfaceHolder;
	private PuzzleTacticsMainScreen mainScreen;

	public PuzzleTacticsMainThread (SurfaceHolder surfaceHolder, PuzzleTacticsMainScreen mainScreen) {
		this.surfaceHolder = surfaceHolder;
		this.mainScreen = mainScreen;
	}
	
}
