package cse360pro1;

import javax.swing.*;

/**
 * stats gui used for displaying the scores of the players
 * @author team 8
 */
public class StatsGui
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Statistics");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		StatsGuiPanel panel = new StatsGuiPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
}