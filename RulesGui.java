package cse360pro1;

import javax.swing.*;

/**
 * initializes and sets the sizes for the rules gui
 * @author Denise Perry
 */
public class RulesGui
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Rules");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RulesGuiPanel panel = new RulesGuiPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
}