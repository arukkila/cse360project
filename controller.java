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
		return "player not found";
		
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
			 String currentPlayer = getCurrentPlayer();
			 
			 // remove player from the game
			  removePlayer(currentPlayer);
		 }
		 // player roled 3 1s reset everyone else
		 else if(ruleNumber == 1)
		 {
			 //get current player
			 String[] otherPlayers = getOtherPlayers();
			 
			 // reset the other 3 players
			 for(int resetIndex = 0 ; resetIndex < otherPlayers.length; resetIndex++)
			 {
				 
			 }
			 
			 
		 }
		
		
	}
	
	
	public controller()
	{
		
	}
	
	
	
