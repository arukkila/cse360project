package cse360pro1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Graphic User Interface for viewing the rules of the game
 * 
 * @author Denise Perry
 */
public class StatsGuiPanel extends JPanel
{
	private final int WIDTH = 800;
	private final int HEIGHT = 300;
	private JButton btnDone;

	/**
	 * Creates a new StatsGuiPanel
	 */
	public StatsGuiPanel()
	{
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ButtonListener());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnDone);
		this.add(buttonPanel,BorderLayout.SOUTH);
		
		Object[] columnNames = { "Score", "Player", "Wins", "Losses", "Ratio", "Games", 
								 "Rolls", "1s", "2s", "3s", "4s", "5s", "6s" };
		ArrayList<Player> players = Database.getSingleton().getPlayerDatabase();
		DefaultTableModel dataModel = new DefaultTableModel(columnNames, 0);

		// add player data
		for (Player p : players) {
			Object[] data = new Object[13];
			data[0] = p.getLifeTimeScore();
			data[1] = p.getName();
			data[2] = p.getWins();
			data[3] = p.getLosses();
			data[4] = Math.round(100*p.getRatio())+"%";
			data[5] = p.getNumberOfGame();
			data[6] = p.getNumberOfRolls();
			data[7] = p.getOnes();
			data[8] = p.getTwos();
			data[9] = p.getThrees();
			data[10] = p.getFours();
			data[11] = p.getFives();
			data[12] = p.getSixes();
			dataModel.addRow(data);
			dataModel.fireTableDataChanged();
		}
		
		// create sorter
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dataModel);
		Comparator<Integer> intComparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer arg0, Integer arg1)
			{
				return arg1.intValue() - arg0.intValue();
			}
		};
		Comparator<String> stringComparator = new Comparator<String>()
		{
			@Override
			public int compare(String arg0, String arg1)
			{
				return arg0.toLowerCase().compareTo(arg1.toLowerCase());
			}
		};
		Comparator<String> percentComparator = new Comparator<String>()
		{
			@Override
			public int compare(String arg0, String arg1)
			{
				// there are better ways to do this
				return Integer.valueOf(arg1.substring(0, arg1.length()-1)).
						compareTo(Integer.valueOf(arg0.substring(0, arg0.length()-1)));
			}
		};
		sorter.setComparator(0, intComparator);
		sorter.setComparator(2, intComparator);
		sorter.setComparator(3, intComparator);
		sorter.setComparator(5, intComparator);
		sorter.setComparator(6, intComparator);
		sorter.setComparator(7, intComparator);
		sorter.setComparator(8, intComparator);
		sorter.setComparator(9, intComparator);
		sorter.setComparator(10, intComparator);
		sorter.setComparator(11, intComparator);
		sorter.setComparator(12, intComparator);
		sorter.setComparator(1, stringComparator);
		sorter.setComparator(4, percentComparator);
		sorter.toggleSortOrder(0);

		JTable tableStats = new JTable(dataModel);
		tableStats.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableStats.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableStats.setRowSorter(sorter);
		tableStats.setColumnSelectionAllowed(true);
		tableStats.setShowVerticalLines(false);
		tableStats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tableStats);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == btnDone) {
				getRootPane().getParent().setVisible(false);
			}
		}
	}
}