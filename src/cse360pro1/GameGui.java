package cse360pro1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * Graphical user interface for playing the game.
 * @author Michael
 */
public class GameGui extends javax.swing.JFrame implements GameGuiInterface {

    /**
     * Underlying game logic.
     */
    private GameModelInterface gameModel;

    /**
     * Constant model for the player table.
     */
    private final PlayerTableModel tableModel;

    /**
     * Reference to the {@link MainGui} to return to it once the game is done.
     * May be null.
     */
    private final MainGui mainGui;

    private final Timer timer = new Timer(250, new ActionListener() {
        final Random rand = new Random();
        private int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: clean this up
            count++;
            int side1;
            while ((side1 = rand.nextInt(6) + 1) == diceImage1.getSide());
            int side2;
            while ((side2 = rand.nextInt(6) + 1) == diceImage2.getSide());
            int side3;
            while ((side3 = rand.nextInt(6) + 1) == diceImage3.getSide());
            diceImage1.setSide(side1);
            diceImage2.setSide(side2);
            diceImage3.setSide(side3);
            if (count >= 8) {
                timer.stop();
                count = 0;
                if (gameModel != null) {
                    gameModel.roll();
                }
                rollButton.setEnabled(true);
            }
        }
    });
    
        /**
     * Creates a new GameGui without an associated {@link MainGui} to return to.
     * Primarily used for testing.
     */
    public GameGui() {
        this(null);
    }
    /**
     * Creates a new GameGui.
     * @param mainGui {@link MainGui} to return to once the game has ended.
     */
    public GameGui(MainGui mainGui) {
        this.mainGui = mainGui;
        tableModel = new PlayerTableModel();
        initComponents();
        notifyModelChanged();
    }

    /**
     * Sets the underlying logic model for the GUI.
     * The GUI will have no functionality until the model is set.
     * @param model The model. May be null.
     */
    @Override
    public void setModel(GameModelInterface model) {
        gameModel = model;
        notifyModelChanged();
    }
    
    @Override
    public void showMessage(String messageYo)
    {
    	JOptionPane.showMessageDialog(this, messageYo);  
    }

    /**
     * Notifies the GUI the refresh the display, probably because the
     * underlying model has changed in some way.
     */
    @Override
    public void notifyModelChanged() {
        if (gameModel != null) {
            Player currentPlayer = gameModel.getCurrentPlayerInCurrentGame();
            currentPlayerNameLabel.setText(currentPlayer.getName());
            currentTotalValueLabel.setText(String.valueOf(currentPlayer.getScore()));
            int index = 0;
            while (!currentPlayer.equals(gameModel.getPlayerInCurrentGame(index))) {
                index++;
            }
            playerTable.getSelectionModel().setSelectionInterval(index, index);
            
            int[] lastRoll = gameModel.getLastRoll();
            diceImage1.setSide(lastRoll[0]);
            diceImage2.setSide(lastRoll[1]);
            diceImage3.setSide(lastRoll[2]);

            // TODO: if player has won, change the score color
        } else {
            currentPlayerNameLabel.setText("");
            currentTotalValueLabel.setText("");
        }
        tableModel.fireTableRowsUpdated(0, 10);
    }

    /**
     * {@link javax.swing.table.TableModel} to use for {@link #playerTable}.
     */
    private class PlayerTableModel extends AbstractTableModel {

        // documented in interface
        @Override
        public int getRowCount() {
            int count;
            if (gameModel == null) {
                count = 0;
            } else {
                count = gameModel.getPlayersInCurrentGameCount();
            }
            return count;
        }

        // documented in interface
        @Override
        public int getColumnCount() {
            return 2;
        }

        // documented in interface
        @Override
        public String getColumnName(int column) {
            String name;
            if (column == 0) {
                name = "Player";
            } else {
                name = "Score";
            }
            return name;
        }

        // documented in interface
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Object.class;
        }

        // documented in interface
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object value;
            if (gameModel == null) {
                value = null;
            } else {
                Player player = gameModel.getPlayerInCurrentGame(rowIndex);
                if (columnIndex == 0) {
                    value = player.getName();
                } else {
                    if (player.getLostStatus()) {
                        value = "Lost";
                    } else if (player.getWonStatus()) {
                        value = "Won";
                    } else {
                        value = player.getScore();
                    }
                }
            }
            return value;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        currentPlayerLabel = new javax.swing.JLabel();
        currentPlayerNameLabel = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        playerTable = new javax.swing.JTable();
        scorePanel = new javax.swing.JPanel();
        currentTotalLabel = new javax.swing.JLabel();
        currentTotalValueLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        rollButton = new javax.swing.JButton();
        dicePanel = new javax.swing.JPanel();
        diceImage1 = new cse360pro1.DiceImage();
        diceImage2 = new cse360pro1.DiceImage();
        diceImage3 = new cse360pro1.DiceImage();
        endGameButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        titlePanel.setLayout(new java.awt.GridLayout(1, 0));

        currentPlayerLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currentPlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        currentPlayerLabel.setText("Current player:");
        titlePanel.add(currentPlayerLabel);

        currentPlayerNameLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currentPlayerNameLabel.setText("Bob");
        titlePanel.add(currentPlayerNameLabel);

        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);

        playerTable.setModel(tableModel);
        playerTable.setEnabled(false);
        tableScrollPane.setViewportView(playerTable);

        getContentPane().add(tableScrollPane, java.awt.BorderLayout.CENTER);

        scorePanel.setLayout(new java.awt.GridLayout(2, 0));

        currentTotalLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currentTotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentTotalLabel.setText("Current total:");
        currentTotalLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        scorePanel.add(currentTotalLabel);

        currentTotalValueLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currentTotalValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentTotalValueLabel.setText("42");
        currentTotalValueLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        scorePanel.add(currentTotalValueLabel);

        getContentPane().add(scorePanel, java.awt.BorderLayout.EAST);

        buttonPanel.setLayout(new java.awt.BorderLayout());

        rollButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rollButton.setIcon(new ImageIcon(DiceImage.getRollImage(90, 74)));
        rollButton.setToolTipText("Roll");
        rollButton.setPreferredSize(new java.awt.Dimension(150, 37));
        rollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(rollButton, java.awt.BorderLayout.WEST);

        dicePanel.setBackground(new java.awt.Color(0, 0, 0));
        dicePanel.setLayout(new java.awt.GridLayout(1, 3));

        diceImage1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dicePanel.add(diceImage1);

        diceImage2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dicePanel.add(diceImage2);

        diceImage3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dicePanel.add(diceImage3);

        buttonPanel.add(dicePanel, java.awt.BorderLayout.CENTER);

        endGameButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        endGameButton.setText("End game");
        endGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endGameButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(endGameButton, java.awt.BorderLayout.EAST);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /**
     * Handles Roll button click.
     * @param evt Event object.
     */
    private void rollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollButtonActionPerformed
        rollButton.setEnabled(false);
        timer.start();
    }//GEN-LAST:event_rollButtonActionPerformed

    /**
     * Handles End Game button click.
     * @param evt Event object.
     */
    private void endGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endGameButtonActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "End the current game?", "End game?", JOptionPane.YES_NO_OPTION);
        if (response == 0) {
            if (gameModel != null) {
                // let the gameModel know we are canceling the game
                // so it can do what it needs to
                gameModel.endGame();
            }
            setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_endGameButtonActionPerformed


    /**
     * Called when the window closes..
     * @param evt Event object.
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (mainGui != null) {
            mainGui.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosed

    /**
     * For testing.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameGui gui = new GameGui();
                Controller model = new Controller("Batmane","John Cena", "Seth","Could");
                model.setGameGui(gui);
                gui.setModel(model);
                gui.setVisible(true);
            }
        });
    }

    /**
     * For testing.
     */
    static class TestGame implements GameModelInterface {

        private GameGuiInterface gui;
        private Player[] players = {
            new Player("Bob"),
            new Player("Lisa"),
            new Player("Ron"),
        };
        private int player = 0;

        @Override
        public void setGameGui(GameGuiInterface gameGui) {
            gui = gameGui;
        }

        @Override
        public int getPlayersInCurrentGameCount() {
            return players.length;
        }

        @Override
        public Player getPlayerInCurrentGame(int index) {
            return players[index];
        }

        @Override
        public Player getCurrentPlayerInCurrentGame() {
            return players[player];
        }

        @Override
        public void roll() {
            getCurrentPlayerInCurrentGame().updateScore(5);
            gui.notifyModelChanged();
            gui.showMessage("Something happened");
        }

        @Override
        public boolean gameWon() {
            return false;
        }

        @Override
        public void endGame() {
        }

        @Override
        public int[] getLastRoll() {
            return new int[] {1, 2, 3};
        }


    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel currentPlayerLabel;
    private javax.swing.JLabel currentPlayerNameLabel;
    private javax.swing.JLabel currentTotalLabel;
    private javax.swing.JLabel currentTotalValueLabel;
    private cse360pro1.DiceImage diceImage1;
    private cse360pro1.DiceImage diceImage2;
    private cse360pro1.DiceImage diceImage3;
    private javax.swing.JPanel dicePanel;
    private javax.swing.JButton endGameButton;
    private javax.swing.JTable playerTable;
    private javax.swing.JButton rollButton;
    private javax.swing.JPanel scorePanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}