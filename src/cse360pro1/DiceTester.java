package cse360pro1;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTester {

	@Test
	//Test Dice is not null
	public void testDice() 
	{
		Dice die =  new Dice(); 
		
		assertNotNull(die);
	}
	
	@Test
	//Test if dice roll in correct bounds
	public void testRoll()
	{
		Dice die = new Dice();
		boolean outOfBounds = true;
		
		int rolledNumber = die.roll(2);
		//System.out.println(rolledNumber);
		
		if( rolledNumber == 1 || rolledNumber == 2)
			outOfBounds = false;
		
		assertFalse(outOfBounds); 	
					
	}

}
