/**
 * Class that creates a virtual dice that can roll/generate a random number. 
 * The number of sides can be changed as desired.
 * 
 * @author Thomas Gottschlich for CSE360
 * @version Apr 8 2016 
 * @pin 326
 */
package cse360pro1;

import java.util.Random;

public class Dice 
{
	private Random number;
	private int sides;
	
	/**
	 * Creates a dice with a random number generator.
	 */
	Dice(int sides)
	{
		this.sides = sides;
		number = new Random(); 
	}
	
	/**
	 * Generates a number between 1 and the number of sides given.
	 * 
	 * @param sides - number of sides on the dice
	 * @return number - integer that the dice rolls
	 */
	public int roll()
	{
		return number.nextInt(this.sides) + 1; 
	}	

}
