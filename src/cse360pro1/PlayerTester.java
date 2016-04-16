package cse360pro1;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTester {
			
	@Test
	//test player constructor
	public void testPlayer() 
	{
		
		Player player =  new Player("John Cena"); 
		
		assertNotNull(player);
	}

	@Test
	//test update the score
	public void testUpdateScore() 
	{
		Player player =  new Player("Batman"); 
		player.updateScore(6);
		
		assertEquals(6, player.getScore());	
	}

	@Test
	//test updateRollStats
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
	public void testGetScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLifeTimeScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfRolls() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOnes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTwos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetThrees() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFours() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFives() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSixes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWins() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLosses() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRatio() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPlayerStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWonStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLostStatus() {
		fail("Not yet implemented");
	}

}
