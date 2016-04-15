package cse360pro1;

import java.io.*;
import java.util.*;

public class Database
{
	private LinkedList<Player> list;

	Database()
	{
		list = new LinkedList();
	}
	
	public void serializeObjece(Player object)
	{
		if(object == null)
			return;
	
		try
		{
			FileOutputStream outFile = new FileOutputStream("players.ser");
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
	
	public Player deserializeObject()  
	{		
		Player loadedPlayer = null;
		
		try
		{
			FileInputStream inFile = new FileInputStream("players.ser");
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
	
	//Just testing stuffs
	//seems to work to my knowledge
	public static void main(String[] args) 
	{
		//Player JohnCena = new Player("John Cena");
		Player test;
		Database store = new Database();
		
		//JohnCena.updateScore(9);
		
		//store.serializeObjece(JohnCena);
		
		test = store.deserializeObject();
		
		System.out.println("Name: " + test.getName());
		System.out.println("Score: " + test.getScore());
		System.out.println("Overall Score: " + test.getLifeTimeScore());
		
	}
	
}
