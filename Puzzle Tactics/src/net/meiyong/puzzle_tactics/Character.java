/**
 * 
 */
package net.meiyong.puzzle_tactics;

import android.util.Log;

/**
 * @author Dinan Yin
 *
 */
public class Character {
	private static final String TAG = Character.class.getSimpleName();
	protected int hitPoint;
	protected int redMana;
	protected int blueMana;
	protected int yellowMana;
	protected int purpleMana;
	protected int whiteMana;
	
	public Character (int hitPoint, int redMana, int blueMana, int yellowMana, int purpleMana, int whiteMana) {
		this.hitPoint = hitPoint;
		this.redMana = redMana;
		this.blueMana = blueMana;
		this.yellowMana = yellowMana;
		this.purpleMana = purpleMana;
		this.whiteMana = whiteMana;
		Log.d(TAG, "chacacter Hitpoint = " + this.hitPoint);
	}
	
	protected void addHitPoint (int hp) {
		this.hitPoint += hp;
	}
	
	protected int getHitPoint () {
		Log.d(TAG, "chacacter Hitpoint = " + this.hitPoint);
		return this.hitPoint;
	}
}
