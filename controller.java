import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * controller class controls the game functionality
 * @author Matt Dunning
 *
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
	private Player[] masterList;
	private Player[] currentList;
	private int score = 0;
	private int currentPlayer = 0;
	// public variables are visible outside of the class
	public String name;
	
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
	 * @return returns the scores of individual player
	 */
	public int getScore()
	{
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
	
	/**
	 * checks against the games rules and makes decisions based on 
	 * the rule 
	 * @param ruleNumber the rule that has happened from the roll
	 */
	public void ruleCheck(int ruleNumber)
	{
	
		// if ruleNumber is -1 no rule match proceed normally
		 if(ruleNumber == -1)
		 {
			 //add score to players overall
			 //update gui
		 }
		 // if rule is 0 the player rolled
		 else if(ruleNumber == 0)
		 {
					 
			 //check who the current player is
			
			 
			 // remove player from the game
			
		 }
		 // player roled 3 1s reset everyone else
		 else if(ruleNumber == 1)
		 {
			 
			 
			 
		 }
		
		
	}
	
	/**
	 * default construct
	 */
		
	
}