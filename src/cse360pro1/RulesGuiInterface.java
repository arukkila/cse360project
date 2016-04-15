package cse360pro1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author Denise Perry
 */

public class RulesGuiInterface extends JPanel
{
	private final int WIDTH = 500, HEIGHT = 500;
	private JButton okay;

	public RulesGuiInterface()
	{
		okay = new JButton("Got it");
		okay.addActionListener(new ButtonListener());

		add(okay);

		Color color = Color.decode("0xD6D9DF");
		setBackground(color);
		setPreferredSize(new Dimension(500, 500));
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == okay)
				System.exit(0);
		}
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.setColor(Color.black);
		
		String ruleList1 = "1. Players roll 3 die at a time.\n";
		String ruleList2 = "2. Players must roll and record those stats every turn.\n";
		String ruleList3 = "3. Any player to roll 3 1s automatically loses and must wait for the next game.\n";
		String ruleList4 = "4. The first player to roll all 6s wins the game no matter the scores.\n";
		String ruleList5 = "5. If a player rolls all 3s all other players scores reset to 0.\n";
		String ruleList6 = "6. If a player rolls two of a kind they can roll again.\n";
		String ruleList7 = "7. The first player with a total ≥ 100 wins the game.\n";
		String ruleList8 = "8. The end of the game will rank all of the players on who is closest to 100.\n";
		String ruleList9 = "9. Game requires four players to start.";

		page.drawString(ruleList1, WIDTH/2 - 30, HEIGHT/2 - 75);
		page.drawString(ruleList2, WIDTH/2 - 75, HEIGHT/2 - 75 + 15);
		page.drawString(ruleList3, WIDTH/2 - 100, HEIGHT/2 - 75 + 30);
		page.drawString(ruleList4, WIDTH/2 - 140, HEIGHT/2 - 75 + 45);
		page.drawString(ruleList5, WIDTH/2 - 130, HEIGHT/2 - 75 + 60);
		page.drawString(ruleList6, WIDTH/2 - 110, HEIGHT/2 - 75 + 75);
		page.drawString(ruleList7, WIDTH/2 - 50, HEIGHT/2 - 75 + 90);
		page.drawString(ruleList8, WIDTH/2 - 50, HEIGHT/2 - 75 + 105);
		page.drawString(ruleList9, WIDTH/2 - 50, HEIGHT/2 - 75 + 120);

		}
}