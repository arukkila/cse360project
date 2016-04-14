package cse360pro1;

import java.util.Arrays;
import java.util.Random;

public class controller{
	
	public final String LOSERS = "ALL OF YOU LOST";
	public final String WINNER = "CONGRATS YOU ARE THE WINNER";
	
	// private fields are not visible outside of the class
	public Player[] playerList = new Player[4];
	public int currentPlayer;
	private int score = 0;
	private boolean currentPlayer1 = true;
	
	// public variables are visible outside of the class
	public String name;
	
	controller(String name1, String name2, String name3, String name4)
	{
		playerList[0] = new Player(name1);
		playerList[1] = new Player(name2);
		playerList[2] = new Player(name3);
		playerList[3] = new Player(name4);
		
		Random rand = new Random();
		
		currentPlayer = rand.nextInt(4);	
	}
	
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
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
		int roll1 = die1.roll();
		int roll2 = die2.roll();
		int roll3 = die3.roll();

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
	

	public static void main(String[] args) 
	{
		controller control =  new controller("bob", "jim", "fred", "jane");
		
		Player[] list = control.playerList;
		
		for(int i = 0; i<4 ; i++)
			System.out.println(list[i].getName());
		
		Dice die = new Dice(6);
		
		control.roll(die, die, die, list[control.getCurrentPlayer()]);
		
		
		System.out.println( list[control.getCurrentPlayer()].getScore() );
		
		
	}
}