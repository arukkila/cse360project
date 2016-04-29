package cse360pro1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;

import javax.swing.*;

/**
 * Graphic User Interface for viewing the rules of the game
 * 
 * @author Denise Perry
 */
public class StatsGuiPanel extends JFrame
{
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	private JButton okay;
	private JButton compare;
	private JTextField player1Stats;
	private JTextField player2Stats;
	private JComboBox<String> player1Combo;
	private JComboBox<String> player2Combo;
	private JLabel player1Label;
	private JLabel player2Label;
	private JLabel player1Name;
	private JLabel player2Name;
	private JLabel player1Temp;
	private JLabel player2Temp;
	private JLabel instructions1;
	private JLabel instructions2;
	private JFrame layout;
	
	
	/**
	 * Creates a new StatsGuiPanel
	 */
	public StatsGuiPanel()
	{
		setPreferredSize(new Dimension(500, 500));
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		player1Stats = new JTextField();
		player2Stats = new JTextField();
		okay = new JButton("Done");
		okay.addActionListener(new ButtonListener());
		compare = new JButton("Compare Stats");
		instructions1 = new JLabel("Select two players from the dropdown lists");
		instructions2 = new JLabel("to compare statistics.");
		player1Stats.setText("Player One's Stats here");
		player1Stats.setEditable(false);
		player2Stats.setText("Player Two's Stats here");
		player2Stats.setEditable(false);
		player1Label = new JLabel("Player 1");
		player2Label = new JLabel("Player 2");
		
		player1Name = new JLabel("TempName 1");
		player2Name = new JLabel("TempName 2");
		player1Temp = new JLabel("Temp 1");
		player2Temp = new JLabel("Temp 2");


		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(instructions1)
						.addComponent(instructions2)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(player1Label)
										.addComponent(player1Temp))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(player2Label)
										.addComponent(player2Temp)))
						.addComponent(compare)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(player1Name)
										.addComponent(player1Stats))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(player2Name)
										.addComponent(player2Stats)))
						.addComponent(okay)
				)
			);

		layout.linkSize(SwingConstants.HORIZONTAL, okay, compare);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(instructions1)
				.addComponent(instructions2)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(player1Label)
						.addComponent(player2Label))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(player1Temp)
						.addComponent(player2Temp))
				.addComponent(compare)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(player1Name)
								.addComponent(player2Name))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(player1Stats)
								.addComponent(player2Stats))
						)
				.addComponent(okay)
				);
		
        setTitle("Statistics");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
	
/*	
	private void updatePlayerList()
	{
		ArrayList<String> playerNameList = Database.getSingleton().getAllPlayerNames();
		String[] playerNameArray = playerNameList.toArray(new String[playerNameList.size()]);
		JComboBox[] comboBoxes =
		{
				player1Combo,
				player2Combo,
		}:
	
	for (int index = 0; index < 2; index++)
	{
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(playerNameArray);
		model.setSelectedItem("");
		comboBoxes[index].setModel(model);
	}
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
	protected void makebutton(String name,
			GridBagLayout gridbag, GridBagConstraints c)
		{
			Button button = new Button(name);
			gridbag.setConstraints(button, c);
			add(button);
		}
	

	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.setColor(Color.black);
		page.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		page.drawString("Stats go here.", 50, 50);

	}
*/
}