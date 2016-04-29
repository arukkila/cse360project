package cse360pro1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		makebutton("Button1", gridbag, c);
		makebutton("Button2", gridbag, c);
		makebutton("Button3", gridbag, c);

		c.gridwidth = GridBagConstraints.REMAINDER; //end row
		makebutton("Button4", gridbag, c);
		
/*		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 1.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		JLabel instructions = new JLabel("Select two players from the dropdown lists\nto compare statistics");
		gridbag.setConstraints(instructions, c);
		add(instructions);
		
*/		
		
		
		okay = new JButton("Done");
		okay.addActionListener(new ButtonListener());

		add(okay);

		setPreferredSize(new Dimension(500, 500));
		
		this.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okay);
		this.add(buttonPanel,BorderLayout.SOUTH);
		
/*		JTextField playerOneStats = new JTextField();
		JTextField playerTwoStats = new JTextField();
		playerOneStats.setText("Player One's Stats here");
		playerOneStats.setEditable(false);
        add(playerOneStats);
        playerTwoStats.setText("Player Two's Stats here");
		playerTwoStats.setEditable(false);
        add(playerTwoStats);
*/	}
	
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == okay) {
                            getRootPane().getParent().setVisible(false);
                        }
		}
	}
	protected void makebutton(String name,
			GridBagLayout gridbag, GridBagConstraints c)
		{
			Button button = new Button(name);
			gridbag.setConstraints(button, c);
			add(button);
		}
	
/*	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.setColor(Color.black);
		page.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		page.drawString("Stats go here.", 50, 50);

	}
*/
}