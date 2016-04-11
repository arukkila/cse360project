package cse360pro1;

import java.util.Arrays;

public class controller{
	
	public final String LOSERS = "ALL OF YOU LOST";
	public final String WINNER = "CONGRATS YOU ARE THE WINNER";
	
	// private fields are not visible outside of the class
	private String masterList[];
	private String currentList[];
	private int score = 0;
	private boolean currentPlayer = true;
	
	// public variables are visible outside of the class
	public String name;
	
	public String[] getMasterList()
	{
		return masterList;
	}
	
	public String[] getCurrentList()
	{
		return currentList;
	}
	
	public boolean checkCurrentPlayer()
	{
		
	}
	
	
	
	public String getCurrentPlayer()
	{
		for(int i = 0; i < currentList.length; i++)
		{
			if(checkCurrentPlayer() == true)
			{
				return currentList[i];
			}
		}
		return "Player not found";
		
	}
	
	public String[] getOtherPlayers()
	{
		String tempList[] = new String[3];
		int playerPos = 0;
		for(int listIndex = 0; listIndex < currentList.length; listIndex++)
		{
			while(checkCurrentPlayer() == false)
			{
				tempList[playerPos] = currentList[listIndex];
				playerPos++;
			}
		}
		return tempList;
		
	}
	
	public int getScore()
	{
		return score;
	}
	
	
	public void setMasterList(String[] players)
	{
		 masterList = new String[4];
		 System.arraycopy(players, 0, masterList, 0, players.length);
	}
	
	public void setCurrentList(String[] players)
	{
		currentList = new String[4];
		 System.arraycopy(players, 0, masterList, 0, players.length);
	}
	
	public void removePlayer(String player)
	{
		
		if(Arrays.asList(currentList).contains(player))
		{
			Arrays.asList(currentList).remove(player);
		}
		
	}
	
	public void exitProgram()
	{
		System.exit(0);
	}
	
	public void gameOver(int choice)
	{
		// if player wants to play again 
		// if player wants to exit program
		// if player wants to view stats
		switch(choice)
		{
	
			case 1:
				//start new game with same players
				
				break;
		
			case 2:
				//terminate the program
				exitProgram();
				break;
		
			case 3:
				//show the stats gui
				break;
			
			default:
				System.out.println("please choose a correct option");
				break;

		}
		
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
	
	public controller()
	{
		
	}
}