package cse360pro1;

import java.io.File;
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.*;

import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTester 
{

    @BeforeClass
    public static void setUp() {
		File statsDir = new File("./stats");
		for (File file : statsDir.listFiles())
		{
			file.delete();
		}
	}

	@Test
	public void test1_Database()
	{
		Database database =  new Database(); 
		assertNotNull(database);
	}

	@Test
	public void test2_SerializeObject()
	{
		Database database =  new Database();
		Player serialPlayer = new Player("EazyE");
		Player deserialPlayer;
		
		database.serializeObject(serialPlayer);
		deserialPlayer = database.deserializeObject("EazyE.stat");
		
		assertNotNull(deserialPlayer);	
	}

	@Test
	public void test3_DeserializeObject()
	{
		Database database =  new Database();
		Player deserialPlayer;
		
		deserialPlayer = database.deserializeObject("EazyE.stat");
		assertNotNull(deserialPlayer);
	}

	@Test
	public void test4_LoadPlayers()
	{
		Database database =  new Database();
		database.loadPlayers();
				
		assertEquals("EazyE", database.getPlayerForName("EazyE").getName());
	}

	@Test
	public void test5_SavePlayers()
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
	public void test6_GetPlayerForName()
	{
		Database database = new Database();
		Player serialPlayer = new Player("FindMe");
		
		assertEquals("FindMe", database.getPlayerForName("FindMe").getName());
		assertEquals("JIM", database.getPlayerForName("JIM").getName());
	}

	@Test
	// must be run with an empty stats folder to pass. otherwise the player will
	// be loaded in a different order than is being tested for
	public void test7_GetAllPlayerNames()
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
	public void test8_GetPlayerDatabase()
	{
		Database database = new Database();
		ArrayList<Player> list = database.getPlayerDatabase();
		
		
		assertEquals("BILL", list.get(0).getName());
		assertEquals("EazyE", list.get(1).getName());
		assertEquals("JIM", list.get(2).getName());
		assertEquals("STACY", list.get(3).getName());		
	}

}
