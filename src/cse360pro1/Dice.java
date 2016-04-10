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
	
	/**
	 * Creates a dice with a random number generator.
	 */
	Dice()
	{
		number = new Random(); 
	}
	
	/**
	 * Generates a number between 1 and the number of sides given.
	 * 
	 * @param numberOfSides - number of sides on the dice
	 * @return number - integer that the dice rolls
	 */
	public int roll(int sides)
	{
		return number.nextInt(sides) + 1; 
	}	

}
