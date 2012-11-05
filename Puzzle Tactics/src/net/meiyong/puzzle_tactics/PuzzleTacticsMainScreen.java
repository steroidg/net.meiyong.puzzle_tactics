package net.meiyong.puzzle_tactics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;

public class PuzzleTacticsMainScreen extends SurfaceView {
	
	private static final String TAG = PuzzleTacticsMainScreen.class.getSimpleName();
	private Paint paint = new Paint();
	private Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
	
	
	public PuzzleTacticsMainScreen (Context context) {
		super (context);
		this.setBackgroundColor(Color.WHITE);
		paint.setColor(Color.BLACK);
		
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		Log.d(TAG, "boo");
		
		@SuppressWarnings("deprecation")
		int width = display.getWidth();
		@SuppressWarnings("deprecation")
		int height = display.getHeight();
		
		canvas.drawLine(0, 0, width, height, paint);
		canvas.drawLine(width, 0, 0, height, paint);
	}
}