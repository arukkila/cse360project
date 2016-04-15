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

		setPreferredSize(new Dimension(500, 500));
		
		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okay);
		this.add(buttonPanel,BorderLayout.SOUTH);
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
		page.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		String ruleList1 = "1. Players roll 3 dice at a time.";
		String ruleList2 = "2. Players must roll and record those stats every turn.";
		String ruleList3 = "3. Any player to roll 3 1s automatically loses and";
		String ruleList3a = "must wait for the next game.";
		String ruleList4 = "4. The first player to roll all 6s wins the game";
		String ruleList4a = "no matter the scores.";
		String ruleList5 = "5. If a player rolls all 3s all other players scores";
		String ruleList5a = "reset to 0.";
		String ruleList6 = "6. If a player rolls two of a kind they can roll again.";
		String ruleList7 = "7. The first player with a total >= 100 wins the game.";
		String ruleList8 = "8. The end of the game will rank all of the players on";
		String ruleList8a = "who is closest to 100.";
		String ruleList9 = "9. Game requires four players to start.";

		page.drawString(ruleList1, WIDTH/2 - 200, HEIGHT/2 - 200);
		page.drawString(ruleList2, WIDTH/2 - 200, HEIGHT/2 - 200 + 30);
		page.drawString(ruleList3, WIDTH/2 - 200, HEIGHT/2 - 200 + 60);
		page.drawString(ruleList3a, WIDTH/2 - 180, HEIGHT/2 - 200 + 90);
		page.drawString(ruleList4, WIDTH/2 - 200, HEIGHT/2 - 200 + 120);
		page.drawString(ruleList4a, WIDTH/2 - 180, HEIGHT/2 - 200 + 150);
		page.drawString(ruleList5, WIDTH/2 - 200, HEIGHT/2 - 200 + 180);
		page.drawString(ruleList5a, WIDTH/2 - 180, HEIGHT/2 - 200 + 210);
		page.drawString(ruleList6, WIDTH/2 - 200, HEIGHT/2 - 200 + 240);
		page.drawString(ruleList7, WIDTH/2 - 200, HEIGHT/2 - 200 + 270);
		page.drawString(ruleList8, WIDTH/2 - 200, HEIGHT/2 - 200 + 300);
		page.drawString(ruleList8a, WIDTH/2 - 180, HEIGHT/2 - 200 + 330);
		page.drawString(ruleList9, WIDTH/2 - 200, HEIGHT/2 - 200 + 360);

		}
}
