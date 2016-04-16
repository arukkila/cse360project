package cse360pro1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Graphic User Interface for viewing the rules of the game
 * 
 * @author Denise Perry
 */
public class RulesGuiInterface extends JPanel
{
	private final int WIDTH = 500, HEIGHT = 500;
	private JButton okay;

	/**
	 * Creates a new RulesGuiInterface
	 */
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
			if(event.getSource() == okay) {
                            getRootPane().getParent().setVisible(false);
                        }
		}
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.setColor(Color.black);
		page.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		page.drawString("1. Players roll 3 dice at a time.", WIDTH/2 - 200, HEIGHT/2 - 200);
		page.drawString("2. Players must roll and record those stats every turn.", WIDTH/2 - 200, HEIGHT/2 - 200 + 30);
		page.drawString("3. Any player to roll 3 1s automatically loses and", WIDTH/2 - 200, HEIGHT/2 - 200 + 60);
		page.drawString("must wait for the next game.", WIDTH/2 - 180, HEIGHT/2 - 200 + 90);
		page.drawString("4. The first player to roll all 6s wins the game", WIDTH/2 - 200, HEIGHT/2 - 200 + 120);
		page.drawString("no matter the scores.", WIDTH/2 - 180, HEIGHT/2 - 200 + 150);
		page.drawString("5. If a player rolls all 3s all other players scores", WIDTH/2 - 200, HEIGHT/2 - 200 + 180);
		page.drawString("reset to 0.", WIDTH/2 - 180, HEIGHT/2 - 200 + 210);
		page.drawString("6. If a player rolls two of a kind they can roll again.", WIDTH/2 - 200, HEIGHT/2 - 200 + 240);
		page.drawString("7. The first player with a total >= 100 wins the game.", WIDTH/2 - 200, HEIGHT/2 - 200 + 270);
		page.drawString("8. The end of the game will rank all of the players on", WIDTH/2 - 200, HEIGHT/2 - 200 + 300);
		page.drawString("who is closest to 100.", WIDTH/2 - 180, HEIGHT/2 - 200 + 330);
		page.drawString("9. Game requires four players to start.", WIDTH/2 - 200, HEIGHT/2 - 200 + 360);

	}
}
