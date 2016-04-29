package cse360pro1;

import javax.swing.*;

/**
 * 
 * @author Denise Perry
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