package cse360pro1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Graphic User Interface for viewing the rules of the game
 * 
 * @author Denise Perry
 */
public class StatsGuiPanel extends JPanel
{
	private final int WIDTH = 500, HEIGHT = 500;
	private JButton okay;

	/**
	 * Creates a new StatsGuiPanel
	 */
	public StatsGuiPanel()
	{
		okay = new JButton("Done");
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
		
		page.drawString("Stats go here.", 50, 50);

	}
}