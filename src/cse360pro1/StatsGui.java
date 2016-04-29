package cse360pro1;

import javax.swing.*;

/**
 * 
 * @author Denise Perry
 */
public class StatsGui
{
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
								  "javax.swing.plaf.metal.MetalLookAndFeel");
								//  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
								//UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				new StatsGuiPanel().setVisible(true);
			}
		});
	}
}