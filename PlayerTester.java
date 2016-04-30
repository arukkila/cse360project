package cse360pro1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *  j-unit test for player class
 */

public class PlayerTester {
			
	@Test
	public void testPlayer() 
	{
		
		Player player =  new Player("John Cena"); 
		
		assertNotNull(player);
	}

	@Test
	public void testUpdateScore() 
	{
		Player player =  new Player("Batman"); 
		player.updateScore(6);
		
		assertEquals(6, player.getScore());	
	}

	@Test
	public void testUpdateRollStats() 
	{
		Player player =  new Player("Batman"); 
		player.updateRollStats(2);
		player.updateRollStats(2);
		player.updateRollStats(2);
		
		assertEquals(3, player.getTwos());	
	}

	@Test
	public void testUpdateWinLoss() 
	{
		Player player =  new Player("Tom"); 
		
		player.updateWinLoss(true);
		
	}

	@Test
	public void testGetName() 
	{
		Player player =  new Player("John Cena"); 
		
		assertEquals("John Cena", player.getName());
	}

	@Test
	public void testGetScore() 
	{
		Player player = new Player("Yuo Ming");
		player.updateScore(50);
		
		assertEquals(50, player.getScore());
		
	}
	
	@Test
	public void testUpdateLifeTimeScore()
	{
		Player player = new Player("Ed");
		player.updateScore(9);
		player.updateLifeTimeScore();
		
		assertEquals(9, player.getLifeTimeScore());
	}

	@Test
	public void testGetLifeTimeScore() 
	{
		Player player = new Player("Ed");
		player.updateScore(9);
		player.updateLifeTimeScore();
		
		assertEquals(9, player.getLifeTimeScore());
	}
	

	@Test
	public void testGetNumberOfRolls() 
	{
		Player player = new Player("Mr. Slave");
		player.updateRollStats(1);
		player.updateRollStats(5);
		player.updateRollStats(3);
		
		assertEquals(3,player.getNumberOfRolls());
	}

	@Test
	public void testGetOnes() 
	{
		Player player = new Player("Kyle");
		player.updateRollStats(1);
		player.updateRollStats(1);
		
		assertEquals(2, player.getOnes());
	}

	@Test
	public void testGetTwos() 
	{
		Player player = new Player("MJ");
		player.updateRollStats(2);
		player.updateRollStats(2);
		player.updateRollStats(2);

		assertEquals(3, player.getTwos());	
	}

	@Test
	public void testGetThrees() 
	{
		Player player = new Player("Louis");
		player.updateRollStats(3);
		
		assertEquals(1, player.getThrees());	
	}

	@Test
	public void testGetFours() 
	{
		Player player = new Player("Gary");
		player.updateRollStats(4);
		player.updateRollStats(4);
		player.updateRollStats(4);
		player.updateRollStats(4);

		assertEquals(4, player.getFours());	
	}

	@Test
	public void testGetFives() 
	{
		Player player = new Player("Bill Bo");
		player.updateRollStats(5);
		
		assertEquals(1, player.getFives());	
	}

	@Test
	public void testGetSixes() 
	{
		Player player = new Player("Mr. Tumnus");
		
		player.updateRollStats(6);
		player.updateRollStats(6);
		player.updateRollStats(6);
		player.updateRollStats(6);
		
		assertEquals(4, player.getSixes());	
	}

	@Test
	public void testGetWins() 
	{
		Player player = new Player("No more good names");
		player.updateWinLoss(true);
		player.updateWinLoss(true);

		assertEquals(2, player.getWins());
	}

	@Test
	public void testGetLosses() 
	{
		Player player = new Player("I lost");
		player.updateWinLoss(false);
		player.updateWinLoss(false);
		player.updateWinLoss(false);
		player.updateWinLoss(false);

		assertEquals(4, player.getLosses());
	}

	@Test
	public void testGetRatio() 
	{
		Player player = new Player("Seth");
		player.updateWinLoss(false);
		player.updateWinLoss(false);
		player.updateWinLoss(true);
		player.updateWinLoss(false);
		player.updateWinLoss(true);
		player.updateWinLoss(false);
		
		assertEquals(2.0/6.0, player.getRatio(), .001);
	}

	@Test
	public void testGetNumberOfGame() 
	{
		Player player = new Player("Seth");
		player.updateWinLoss(false);
		player.updateWinLoss(false);
		player.updateWinLoss(true);
		player.updateWinLoss(false);
		player.updateWinLoss(true);
		player.updateWinLoss(false);
		
		assertEquals(6, player.getNumberOfGame());
	}

	@Test
	public void testGetPlayerStatus() 
	{
		Player player = new Player("Batmane");
		player.setPlayerStatus(false);
		
		assertFalse(player.getPlayerStatus());
	}

	@Test
	public void testSetPlayerStatus() 
	{
		Player player = new Player("Gucci");
		player.setPlayerStatus(false);
		player.setPlayerStatus(true);
		
		assertTrue(player.getPlayerStatus());
	}

	@Test
	public void testGetWonStatus() 
	{
		Player player = new Player("Mike Jones");
		player.updateWinLoss(true);
		
		assertTrue(player.getWonStatus());
	}

	@Test
	public void testGetLostStatus() 
	{
		Player player = new Player("Fred");
		player.updateWinLoss(false);
		
		assertFalse(player.getWonStatus());
	}

	@Test
	public void testResetScore()
	{
		Player player = new Player("Score be gone");
		player.updateScore(5);
		
		player.resetScore();
		
		assertEquals(0, player.getScore());
	}
}
