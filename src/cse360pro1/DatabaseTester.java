package cse360pro1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DatabaseTester 
{

	@Test
	public void testDatabase() 
	{
		Database database =  new Database(); 
		assertNotNull(database);
	}

	@Test
	public void testSerializeObject() 
	{
		Database database =  new Database();
		Player serialPlayer = new Player("EazyE");
		Player deserialPlayer;
		
		database.serializeObject(serialPlayer);
		deserialPlayer = database.deserializeObject("EazyE.stat");
		
		assertNotNull(deserialPlayer);	
	}

	@Test
	public void testDeserializeObject() 
	{
		Database database =  new Database();
		Player deserialPlayer;
		
		deserialPlayer = database.deserializeObject("EazyE.stat");
		assertNotNull(deserialPlayer);
	}

	@Test
	public void testLoadPlayers() 
	{
		Database database =  new Database();
		database.loadPlayers();
				
		assertEquals("EazyE", database.getPlayerForName("EazyE").getName());
	}

	@Test
	public void testSavePlayers() 
	{
		Database database = new Database();
		Player[] playerList = new Player[3];
		playerList[0] = new Player("JIM");
		playerList[1] = new Player("BILL");
		playerList[2] = new Player("STACY");
		
		database.savePlayers(playerList);
		database.loadPlayers();
		assertEquals("JIM", database.getPlayerForName("JIM").getName());
		assertEquals("BILL", database.getPlayerForName("BILL").getName());
		assertEquals("STACY", database.getPlayerForName("STACY").getName());
	}

	@Test
	public void testGetPlayerForName() 
	{
		Database database = new Database();
		Player serialPlayer = new Player("FindMe");
		
		assertEquals("FindMe", database.getPlayerForName("FindMe").getName());
		assertEquals("JIM", database.getPlayerForName("JIM").getName());
	}

	@Test
	// must be run with an empty stats folder to pass. otherwise the player will
	// be loaded in a different order than is being tested for
	public void testGetAllPlayerNames() 
	{
		Database database = new Database();
		
		ArrayList<String> playerNames = database.getAllPlayerNames();
		
		assertEquals("BILL", playerNames.get(0));
		assertEquals("EazyE", playerNames.get(1));
		assertEquals("JIM", playerNames.get(2));
		assertEquals("STACY", playerNames.get(3));		
	}

	@Test
	//This test need to be run twice to pass.
	//Players are not saved in the stats folder until the test is run once.
	public void testGetPlayerDatabase() 
	{
		Database database = new Database();
		ArrayList<Player> list = database.getPlayerDatabase();
		
		
		assertEquals("BILL", list.get(0).getName());
		assertEquals("EazyE", list.get(1).getName());
		assertEquals("JIM", list.get(2).getName());
		assertEquals("STACY", list.get(3).getName());		
	}

}
