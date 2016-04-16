package cse360pro1;

//import java.util.Arrays;
import java.util.Random;

public class Controller implements GameModelInterface{
	
	public final String LOSERS = "ALL OF YOU LOST";
	public final String WINNER = "CONGRATS YOU ARE THE WINNER";
	
	// private fields are not visible outside of the class
	private Player[] playerList = new Player[4];
	private int currentPlayer;
	private GameGuiInterface guiInterface;
	private Dice die; 
	private int[] lastRoll;
	private int kickedOut = 0;
	
	private boolean gameWon = false;
	
	Controller(String name1, String name2, String name3, String name4)
	{
		playerList[0] = new Player(name1);
		playerList[1] = new Player(name2);
		playerList[2] = new Player(name3);
		playerList[3] = new Player(name4);
		
		die = new Dice(6);
		
		Random rand = new Random();
		
		lastRoll = new int[3];
		for(int index = 0; index < lastRoll.length; index++)
			lastRoll[index] = 0;
		
		currentPlayer = rand.nextInt(4);	
	}
	
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
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
	
	public void roll()
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
		guiInterface.notifyModelChanged();
		
		if(rule == 2)
		{
			if(playerList[currentPlayer].getScore() >= 100)
			{
				playerList[currentPlayer].updateWinLoss(true);
				gameWon = true;	
				guiInterface.notifyModelChanged();
				guiInterface.showMessage(WINNER);
			}
			else
			{
				guiInterface.notifyModelChanged();
				guiInterface.showMessage("You rolled " + total + " points!");
			}
		}
		else if(rule == 3)
		{
			playerList[currentPlayer].setPlayerStatus(false);
			kickedOut++;
			
			playerList[currentPlayer].updateWinLoss(false);
			
			if(kickedOut == 3)
			{
				playerList[getNextPlayer()].updateWinLoss(true);
				gameWon = true;
				guiInterface.notifyModelChanged();
				guiInterface.showMessage("Three of you really suck.");
			}
			else
			{
				guiInterface.notifyModelChanged();
				guiInterface.showMessage(playerList[currentPlayer].getName() + ", you just lost! Woohoo!");
			}
		}
		else if(rule == 4)
		{
			playerList[currentPlayer].updateWinLoss(true);
			gameWon = true;
			
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
		
		playerList[currentPlayer].updateScore(roll1 + roll2 + roll3);
		guiInterface.notifyModelChanged();
		
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
		
		//check if player is active and score is 100 or more
		if(playerList[currentPlayer].getScore() >= 100 && 
				playerList[currentPlayer].getPlayerStatus())
		{
			playerList[currentPlayer].updateWinLoss(true);
			guiInterface.notifyModelChanged();
			guiInterface.showMessage(WINNER);
		}
			
		//guiInterface.notifyModelChanged();
		//return gameWon;
	}
	
	@Override
	public void setGameGui(GameGuiInterface gameGui)
	{
		guiInterface = gameGui; 
	}
	
	@Override
	public int getPlayersInCurrentGameCount()
	{
		return playerList.length;
	}
	
	@Override
	public Player getCurrentPlayerInCurrentGame() 
	{
		return playerList[currentPlayer];
	}
	
	@Override
	public Player getPlayerInCurrentGame(int index)
	{
		return playerList[index];
	}

	@Override
	public void endGame() 
	{
		//nothing needs to happen
		
	}
	
	public boolean gameWon()
	{
		return gameWon;
	}
	
	public int[] getLastRoll()
	{
		return lastRoll;
	}
	


}