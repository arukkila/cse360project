/**
 * Class that store player data such as score and other various statistics.
 * 
 * @author Thomas Gottschlich for CSE360
 * @version Apr 22 2016 
 */
package cse360pro1;

import java.io.*;
import java.util.ArrayList;

public class Database
{
	private ArrayList<Player> playerData;
	private File folder;
	private File[] fileList;

	/**
	 * Creates a Database of all players that have or will ever play the game.
	 */
	Database()
	{
		folder = new File("./stats");
		fileList = folder.listFiles();
		playerData =  new ArrayList<Player>();
		
		loadPlayers();
	}
	
	/**
	 * Stores a player object into a file.
	 * 
	 * @param object - player to be serialize into a file
	 */
	public void serializeObject(Player object)
	{
		if(object == null)
			return;
		try
		{
			FileOutputStream outFile = new FileOutputStream("./stats/" + object.getName() +".stat");
			ObjectOutputStream storage = new ObjectOutputStream(outFile);
			
			storage.writeObject(object);
			storage.close();
			
			outFile.close();
		}
		catch(IOException exception)
		{
			exception.printStackTrace();
		}	
	}
	
	/**
	 * Loads a player from file.
	 * 
	 * @param filename - name of the player object to load
	 * @return loadedPlayer - player loaded from file
	 */
	public Player deserializeObject(String filename)  
	{		
		Player loadedPlayer = null;
			
		try
		{
			FileInputStream inFile = new FileInputStream("./stats/" + filename);
			ObjectInputStream data = new ObjectInputStream(inFile);
			
			try 
			{
				loadedPlayer = (Player) data.readObject();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			data.close();
			inFile.close();
		}
		catch(IOException exception)
		{
			exception.printStackTrace();
		}
		
		return loadedPlayer;			
	}
	
	/**
	 * Load all players file into an Player ArrayList
	 */
	public void loadPlayers()
	{
		String extension;
		playerData.clear();
		
		for(int index = 0; index < fileList.length ; index++)
		{
			extension = fileList[index].getName().substring(fileList[index].getName().lastIndexOf(".") + 1, fileList[index].getName().length());
			
			if(fileList[index].isFile() && extension.equals("stat"))
			{
				playerData.add(index, deserializeObject(fileList[index].getName()));
			}
			
		}	
	}
	
	/**
	 * Save all players from last game into a their own unique file.
	 * 
	 * @param playerList
	 */
	public void savePlayers(Player[] playerList)
	{
		for(int index = 0; index < playerList.length; index++)
		{
			serializeObject( playerList[index] );
		}
	}
	
	/**
	 * Gets a player of playerName if it exists else it creates a player with playerName
	 * 
	 * @param playerName - name of player being search for
	 * @return existing player of with playerName or creates a player with playerName 
	 */
	public Player getPlayerForName(String playerName)
	{
		Player temp = new Player(playerName);
		
		for(int index = 0; index < playerData.size() ; index++)
		{
			if(playerData.get(index).getName().equals(playerName))
				temp = playerData.get(index);
		}
		
		return temp;
	}
	
	/**
	 * Gets a arraylist of all player names
	 * 
	 * @return playerNames - an ArrayList of all player names
	 */
	public ArrayList<String> getAllPlayerNames()
	{
		ArrayList<String> playerNames = new ArrayList<String>();
		
		for(int index = 0; index < playerData.size() ; index++)
		{
			playerNames.add(playerData.get(index).getName());
		}
			
		return playerNames;
	}
	
	/**
	 * Get an Arraylist of players
	 * 
	 * @return playerData - arraylist of players
	 */
	public ArrayList<Player> getPlayerDatabase()
	{
		return playerData;
	}
	
	//Just testing stuffs
	//seems to work to my knowledge
	public static void main(String[] args) 
	{
		//Player JohnCena = new Player("John");
		//Player[] test = new Player[100];
		//String extension;
		Database store = new Database();
		
		//JohnCena.updateScore(9);
		//dfgd
		//store.serializeObjece(JohnCena);
		//int playerIndex = 0;
		store.loadPlayers();
		
		System.out.println("Name: " + store.getPlayerDatabase().get(0).getName());
		System.out.println("Score: " + store.getPlayerDatabase().get(0).getScore());
		System.out.println("Overall Score: " + store.getPlayerDatabase().get(0).getLifeTimeScore());
		

		System.out.println("Name: " + store.getPlayerDatabase().get(1).getName());
		System.out.println("Score: " + store.getPlayerDatabase().get(1).getScore());
		System.out.println("Overall Score: " + store.getPlayerDatabase().get(1).getLifeTimeScore());
		
	}
	
}
