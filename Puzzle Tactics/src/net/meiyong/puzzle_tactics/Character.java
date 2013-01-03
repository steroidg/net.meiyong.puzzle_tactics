/**
 * 
 */
package net.meiyong.puzzle_tactics;

/**
 * @author Dinan Yin
 *
 */
public class Character {
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
	}
}
