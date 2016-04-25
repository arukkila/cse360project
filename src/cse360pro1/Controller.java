/**
 * Class that controls the inner workings of the dice game and its components.
 * 
 * @author Team 8 for CSE360
 * @version Apr 15 2016 
 */
package cse360pro1;

import java.util.Random;

public class Controller implements GameModelInterface 
{
	
	private final String LOSERS = "ALL OF YOU LOST";
	private final String WINNER = "CONGRATS YOU ARE THE WINNER";
	
	// private fields are not visible outside of the class
	private Player[] playerList = new Player[4];
	private Database database;
	private int currentPlayer;
	private GameGuiInterface guiInterface;
	private Dice die; 
	private int[] lastRoll;
	private int kickedOut = 0;
	private boolean gameWon = false;
	
	/**
	 * Creates the controller for the game. The controller has a dice and an array
	 * of four players to play the game. The controller has a array of the last three rolls
	 * and the currentPlayer index position which is randomly selected.
	 * 
	 * @param name1 - player name 1
	 * @param name2 - player name 2
	 * @param name3 - player name 3
	 * @param name4 - player name 4
	 */
	Controller(String name1, String name2, String name3, String name4)
	{
		database = Database.getSingleton();
		playerList[0] = database.getPlayerForName(name1);
		playerList[1] = database.getPlayerForName(name2);
		playerList[2] = database.getPlayerForName(name3);
		playerList[3] = database.getPlayerForName(name4);
		
		die = new Dice(6);
		Random rand = new Random();
		
		lastRoll = new int[3];
		
		for(int index = 0; index < lastRoll.length; index++)
			lastRoll[index] = 0;
		
		currentPlayer = rand.nextInt(4);	
	}
	
	/**
	 * Gets the current player's index that they are stored in the array of players.
	 * 
	 * @return currentPlayer index position
	 */
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	/**
	 * Get the next player's index position in the player array that is still active
	 * in the game
	 * 
	 * @return nextPlayer index position
	 */
	public int getNextPlayer()
	{
		if(currentPlayer >= 3)
			currentPlayer = 0;
		else
			currentPlayer++;
		
		while(!playerList[currentPlayer].getPlayerStatus())
			if(currentPlayer >= 3)
				currentPlayer = 0;
			else
				currentPlayer++;
		
		return currentPlayer;
	}	
	
	/**
	 * Returns the number of the rule that is implemented, according to rules.doc
	 * 
	 * @param die1 - roll from dice 1
	 * @param die2 - roll from dice 2
	 * @param die3 - roll from dice 3
	 * @return rule number - the rule that occurred based off three rolls
	 */
	private int ruleCheck(int die1, int die2, int die3)
	{
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
		int rule = 0;
		boolean sameThree = false;
		
		if(die1 == die2 && die2 == die3)//three of a kind
			sameThree = true;
		
		if(sameThree && die1 == 1)//player rolled three 1s
			rule = 3;
		else if(sameThree && die1 == 6)//player rolled three 6s
			rule = 4;
		else if(sameThree && die1 == 3)//player rolled three 3s
			rule = 5;
		else if(die1 == die2 || die1 == die3 || die2 == die3) //player rolled 2 of a kind 
			rule = 6;
		else
			rule = 2;
		
		return rule;
	}
	
	/**
	 * Rolls three dice for the player and checks the rules that follow based off of the 
	 * rolls.
	 */
	public void roll()
	{
		if (!gameWon)
		{
			//may need more sprankles
			//little buggy still but works
			int roll1 = die.roll();
			int roll2 = die.roll();
			int roll3 = die.roll();

			lastRoll[0] = roll1;
			lastRoll[1] = roll2;
			lastRoll[2] = roll3;

			int total = roll1 + roll2 + roll3;

			int rule = ruleCheck(roll1, roll2, roll3);

			playerList[currentPlayer].updateRollStats(roll1);
			playerList[currentPlayer].updateRollStats(roll2);
			playerList[currentPlayer].updateRollStats(roll3);
			playerList[currentPlayer].updateScore(roll1 + roll2 + roll3);

			guiInterface.notifyModelChanged();

			int winningPlayerIndex = -1;

			if(rule == 2)
			{
				guiInterface.notifyModelChanged();
				guiInterface.showMessage("You rolled " + total + " points!");
			}
			else if(rule == 3)
			{
				playerList[currentPlayer].setPlayerStatus(false);
				kickedOut++;

				if(kickedOut == 3)
				{
					gameWon = true;
					guiInterface.notifyModelChanged();
					guiInterface.showMessage("Three of you really suck.");
					for (int index = 0; winningPlayerIndex < 0 && index < playerList.length; index++)
					{
						if (playerList[index].getPlayerStatus())
							winningPlayerIndex = index;
					}
				}
				else
				{
					guiInterface.notifyModelChanged();
					guiInterface.showMessage(playerList[currentPlayer].getName() + ", you just lost! Woohoo!");
				}
			}
			else if(rule == 4)
			{
				gameWon = true;
				winningPlayerIndex = currentPlayer;

				guiInterface.notifyModelChanged();
				guiInterface.showMessage(WINNER);
			}
			else if(rule == 5)
			{
				for(int index = 0; index < 4; index++)
				{
					if(index != currentPlayer)
						playerList[index].resetScore();
				}
				guiInterface.notifyModelChanged();
				guiInterface.showMessage("All your score are belong to us.");
			}

			guiInterface.notifyModelChanged();

			//check if player is active and score is 100 or more
			if(playerList[currentPlayer].getScore() >= 10 &&
			   playerList[currentPlayer].getPlayerStatus())
			{
							gameWon = true;
				guiInterface.notifyModelChanged();
				guiInterface.showMessage(WINNER);
				winningPlayerIndex = currentPlayer;
			}
			else
			{
				if(rule != 6)
				{
					guiInterface.notifyModelChanged();
					getNextPlayer();
					guiInterface.notifyModelChanged();
				}
				else
				{
					guiInterface.notifyModelChanged();
					guiInterface.showMessage("You get to roll again!");
				}
			}

			if (winningPlayerIndex >= 0)
			{
				for (int index = 0; index < playerList.length; index++)
				{
					Player player = playerList[index];
					if (winningPlayerIndex == index)
					{
						player.updateWinLoss(true);
					}
					else
					{
						player.updateWinLoss(false);
					}
					player.updateLifeTimeScore();
					database.savePlayers(playerList);
				}
			}
		}
		else
			guiInterface.showMessage("What? You're still here?\nThe game's over. Go home.");
	}
	
	/**
	 * Sets the Game gui
	 */
	@Override
	public void setGameGui(GameGuiInterface gameGui)
	{
		guiInterface = gameGui; 
	}
	
	/**
	 * Gets the number of players. Current version only has 4 total players
	 * @return player list length
	 */
	@Override
	public int getPlayersInCurrentGameCount()
	{
		return playerList.length;
	}
	
	/**
	 * Gets the current player
	 * @return current player
	 */
	@Override
	public Player getCurrentPlayerInCurrentGame() 
	{
		return playerList[currentPlayer];
	}
	
	/**
	 * Gets player of a specific index
	 * @param index - specific index of player
	 * @return player at index
	 */
	@Override
	public Player getPlayerInCurrentGame(int index)
	{
		return playerList[index];
	}

	/**
	 * Nothing to see here
	 */
	@Override
	public void endGame() 
	{
		//nothing needs to happen
		
	}
	
	/**
	 * Gets the status of the game. If the game is won or not
	 * 
	 * @return gameWon status
	 */
	public boolean gameWon()
	{
		return gameWon;
	}
	
	/**
	 * Gets the last three rolls made by the dice
	 * @return array of last 3 dice rolls
	 */
	public int[] getLastRoll()
	{
		return lastRoll;
	}
}