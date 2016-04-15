import java.util.Arrays;


/**
 * controller class controls the game functionality
 * @author Matt Dunning 
 * pin 425
 */
public class controller extends Player{
	
	controller(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
	// Class Variables or Fields
	// You declare constants with final
	
	public final String LOSERS = "ALL OF YOU LOST";
	public final String WINNER = "CONGRATS YOU ARE THE WINNER";
	
	// private fields are not visible outside of the class

	private int kickedOut = 0;
	private boolean gameWon = false;
	private Player[] masterList;
	private Player[] currentList;
	private int currentPlayer = 0;
	
	
	/**
	 * contains the master list
	 * @return the current player list without any altercations
	 */
	public Player[] getMasterList()
	{
		return masterList;
	}

	
	public int getCurrentPlayerCount(Player[] currentList){
		
		int numberOfPlayers = 0;
		
		for(int i = 0; i < currentList.length; i++ )
		{
			numberOfPlayers++;
		}
		return numberOfPlayers;
	}
	/**
	 * function that has current list
	 * @return returns the current list with player removals if any
	 */
	public Player[] getCurrentList()
	{
		return currentList;
	}
	
	/**
	 * function to iterate through the players
	 * @return the name of the current player
	 */
	public Player currentPlayer()
	{
		// listen for roll button
		Player currentRoller = null;
		
		if(currentPlayer == 0)
		{
			currentRoller = currentList[0];
			currentPlayer++;
		}
		else if(currentPlayer == 1)
		{
			currentRoller = currentList[1];
			currentPlayer++;
		}
		else if(currentPlayer == 2)
		{
			currentRoller = currentList[2];
			currentPlayer++;
		}
		else if(currentPlayer == 3)
		{
			currentRoller = currentList[3];
			currentPlayer++;
		}
		
		return currentRoller;
	
	}
	
	
	/**
	 * gets the current player
	 * @return returns string name of current player
	 */
	public Player getCurrentPlayer(Player[] currentList)
	{
		Player playerRolling = currentPlayer();
		
		for(int i = 0; i < currentList.length; i++)
		{
			if(playerRolling == currentList[i])
			{
				 playerRolling = currentList[i];
			}
		}
		
		return playerRolling;
	}
	
	/**
	 * function helper for reseting other players scores
	 * @return all players except for the current player
	 */
	public Player[] getOtherPlayers()
	{
		Player playerRolling = currentPlayer();
		Player tempList[] = new Player[3];
		int playerPos = 0;
		for(int listIndex = 0; listIndex < currentList.length; listIndex++)
		{
			while(playerRolling != currentList[listIndex])
			{
				tempList[playerPos] = currentList[listIndex];
				playerPos++;
			}
		}
		return tempList;
		
	}
	
	/**
	 * scores of players
	 * @return returns the scores of current player
	 */
	public int getScore()
	{
		Player currentPlayer = currentPlayer();
		int score  = currentPlayer.getScore();
		
		return score;
	}
	
	
	/**
	 * assigns the master list that is not changed
	 * @param players list of the initial players
	 */
	public void setMasterList(Player[] players)
	{
		 masterList = new Player[4];
		 System.arraycopy(players, 0, masterList, 0, players.length);
	}
	
	/**
	 * sets up the current list that can be altered 
	 * if a player needs to be removed
	 * @param players list of players from start
	 */
	public void setCurrentList(String[] players)
	{
		 currentList = new Player[4];
		 System.arraycopy(players, 0, masterList, 0, players.length);
	}
	
	/**
	 * removes a player from the game
	 * @param player the string name of the player to be removed
	 */
	public void removePlayer(Player player)
	{
		
		if(Arrays.asList(currentList).contains(player))
		{
			Arrays.asList(currentList).remove(player);
		}
		
	}
	
	/**
	 * terminates the program
	 */
	public void exitProgram()
	{
		System.exit(0);
	}
	
	/**
	 * sets the scores back to zero and returns the original players
	 * for a new game
	 * @return the original players with score set to 0
	 */
	public Player[] resetScores()
	{
		Player[] masterList = getMasterList();
		
		for(int player = 0; player < masterList.length; player++)
		{
			int resetScoreBy = masterList[player].getScore();
			
			masterList[player].updateScore(-resetScoreBy);
		}
		
		return masterList;
		
	}
	
	/**
	 * gets the stats of all the current players
	 * @return an int array with the scores of the current players
	 */
	public int[] getStatsCurrentGame()
	{
		
		Player[] playerStats = getCurrentList();
		int ammountOfPlayers = (int) playerStats.length;
		int[] stats = new int[ammountOfPlayers];
				
		for(int player = 0; player < playerStats.length; player++)
		{
			stats[player] = playerStats[player].getScore();
			
		}
		
		return stats;
		
	}
	
	/**
	 * after the game is finished the choice is made to
	 * start a new game, terminate the program, or view the stats
	 * @param choice the option that is decided
	 */
	public void gameOver(int choice)
	{
		// if player wants to play again 
		// if player wants to exit program
		// if player wants to view stats
		switch(choice)
		{
	
			case 1:
				resetScores();
				break;
		
			case 2:
				exitProgram();
				break;
		
			case 3:
				getStatsCurrentGame();
				break;
			
			default:
				System.out.println("please choose a correct option");
				break;

		}
		
	}
	
	

	public boolean isGameWon()
	{
		return gameWon;
	}
	
	//int returned is the number of the rule that is implemented, according to rules.doc
	private int ruleCheck(int die1, int die2, int die3)
	{
		int rule = 0;
		boolean sameThree = false;
	/*
	 * 1. Players roll 3 dice at a time.
	 * 2. Players must roll and record those stats every turn.
	 * 3. Any player to roll 3 1s automatically loses and must wait for the next game.
	 * 4. The first player to roll all 6s wins the game no matter the scores.
	 * 5. If a player rolls all 3s all other players scores reset to 0.
	 * 6. If a player rolls two of a kind they can roll again.
	 * 7. The first player with a total >= 100 wins the game.
	 * 8. The end of the game will rank all of the players on who is closest to 100.
	 * 9. Game requires four players to start.
	 */
		
		if(die1 == die2 && die2 == die3)	//three of a kind
			sameThree = true;
		
		if(sameThree && die1 == 1)	//player rolled three 1s
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
	
	public void roll(Dice die1, Dice die2, Dice die3)
	{
		Player[] others = new Player[3];
		Player name = currentPlayer();
		int roll1 = die1.roll();
		int roll2 = die2.roll();
		int roll3 = die3.roll();

		int rule = ruleCheck(roll1, roll2, roll3);
		
		if(rule == 2)
		{
			name.updateScore(roll1 + roll2 + roll3);
			if(name.getScore() >= 100)
				gameWon = true;
		}
		else if(rule == 3)
		{
			removePlayer(name);
			kickedOut++;
			
			if(kickedOut == 3)
			{				
				name.updateWinLoss(false);
				gameWon = true;
			}
		}
		else if(rule == 4)
			gameWon = true;
		else if(rule == 5)
			others = getOtherPlayers();
			//create a function for
			
		else if(rule == 6)
		{
			name.updateScore(roll1 + roll2 + roll3);
			//Probably some GUI stuff needed here
			roll(die1, die2, die3);
		}		
	}
	
	
