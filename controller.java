package cse360pro1;

import java.util.Arrays;

public class controller
{
	private boolean kickedOut = false;
	private boolean gameWon = false;
	private int placeholder = 0; //To be used for if/else statements that can't be implemented yet
	
	//int returned is the number of the rule that is implemented, according to rules.doc
	private int ruleCheck(int die1, int die2, int die3)
	{
		int rule = 0;
		boolean sameThree = false;
	/*
	 * 1. Players roll 3 dice at a time.
	 * 2. Players must roll and record those stats every turn.
	 * 3. The first player to roll 3 1s automatically loses and must wait for the next game.
	 * 4. The first player to roll all 6s wins the game no matter the scores.
	 * 5. If a player rolls all 3s all other players scores reset to 0.
	 * 6. If a player rolls two of a kind they can roll again with an option of recording their scores for each roll on that turn.
	 * 7. The first player with a total >= 100 wins the game.
	 * 8. The end of the game will rank all of the players on who is closest to 100.
	 */
		
		if(die1 == die2 && die2 == die3)	//three of a kind
			sameThree = true;
		
		if(sameThree && die1 == 1 && !kickedOut)	//player rolled three 1s
			rule = 3;
		else if(sameThree && die1 == 6)	//player rolled three 6s
			rule = 4;
		else if(sameThree && die1 == 3)	//player rolled three 3s
			rule = 5;
		else if(!sameThree && (die1 == die2 || die1 == die3 || die2 == die3))	//player rolled 2 of a kind 
			rule = 6;
		else
			rule = 2;
		
		return rule;
	}
	
	public void roll(Dice die1, Dice die2, Dice die3, Player name)
	{
		int roll1 = die1.roll(6);
		int roll2 = die2.roll(6);
		int roll3 = die3.roll(6);

		int rule = ruleCheck(roll1, roll2, roll3);
		
		if(rule == 2 || (rule == 3 && kickedOut))
		{
			name.updateScore(roll1 + roll2 + roll3);
			if(name.getScore() >= 100)
				gameWon = true;
		}
		else if(rule == 3)
			kickedOut = true;
		else if(rule == 4)
			gameWon = true;
		else if(rule == 5)
			placeholder = 0; //go through player list and update scores of everyone else
		else if(rule == 6)
		{
			name.updateScore(roll1 + roll2 + roll3);
			//Probably some GUI stuff needed here
			roll(die1, die2, die3, name);
		}		
	}
}
