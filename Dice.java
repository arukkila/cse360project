
package cse360pro1;

import java.util.Random;

/**
 * Class that creates a virtual dice that can roll/generate a random number. 
 * The number of sides can be changed as desired.
 * 
 * @author team 8
 */
public class Dice 
{
	/**
	 * variables to hold value rolled and number of die sides
	 */
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
	 * @return number - integer that the dice rolls
	 */
	public int roll()
	{
		return number.nextInt(this.sides) + 1; 
	}	

}
