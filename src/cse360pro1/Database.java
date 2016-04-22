package cse360pro1;

import java.io.*;

public class Database
{
	private Player[] playerData;
	private File folder;
	private File[] fileList;
	//private int playerIndex;

	Database()
	{
		folder = new File("./stats");
		fileList = folder.listFiles();
		//playerIndex = 0;
		playerData =  new Player[1000];
	}
	
	public void serializeObjece(Player object)
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
	
	public void loadPlayers()
	{
		String extension;
		int playerIndex = 0;
		
		for(int index = 0; index < fileList.length ; index++)
		{
			extension = fileList[index].getName().substring(fileList[index].getName().lastIndexOf(".") + 1, fileList[index].getName().length());
			
			if(fileList[index].isFile() && extension.equals("stat"))
			{
				playerData[playerIndex] = deserializeObject( fileList[index].getName() );
				playerIndex++;
			}
			
		}	
	}
	
	public Player getPlayerForName(String playerName)
	{
		Player temp = null;
		
		for(int index = 0; index < playerData.length; index++)
		{
			if(playerData[index].getName().equals(playerName))
				temp = playerData[index];
		}
		
		return temp;
	}
	
	public String[] getAllPlayerNames()
	{
		String[] playerNames = new String[1000];
		
		for(int index = 0; index < playerData.length; index++)
		{
			playerNames[index] = playerData[index].getName();
		}
			
		return playerNames;
	}
	
	public Player[] getPlayerDatabase()
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
		//hi
		
		System.out.println("Name: " + store.getPlayerDatabase()[0].getName());
		System.out.println("Score: " + store.getPlayerDatabase()[0].getScore());
		System.out.println("Overall Score: " + store.getPlayerDatabase()[0].getLifeTimeScore());
		

		System.out.println("Name: " + store.getPlayerDatabase()[1].getName());
		System.out.println("Score: " + store.getPlayerDatabase()[1].getScore());
		System.out.println("Overall Score: " + store.getPlayerDatabase()[1].getLifeTimeScore());
		
	}
	
}
