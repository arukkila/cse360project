package cse360pro1;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Michael
 */
public class MainGui extends javax.swing.JFrame {

    private GameCreatorInterface creator;

    /**
     * Creates new form MainGui
     */
    public MainGui() {
        initComponents();
    }

    public void updatePlayerList(StatsModelInterface stats) {
        String[] playerList = new String[stats.getAllPlayerEverCount()];
        for (int index = 0; index < stats.getAllPlayerEverCount(); index++) {
            playerList[index] = stats.getPlayer(index).getName();
        }
        JComboBox[] comboBoxes = {
            player1Combo,
            player2Combo,
            player3Combo,
            player4Combo,
        };
        for (int index = 0; index < 4; index++) {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(playerList);
            model.setSelectedItem("");
            comboBoxes[index].setModel(model);
        }
    }

    public void setGameCreator(GameCreatorInterface creator) {
        this.creator = creator;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        playerListPanel = new javax.swing.JPanel();
        player1Label = new javax.swing.JLabel();
        player1Combo = new javax.swing.JComboBox<>();
        player2Label = new javax.swing.JLabel();
        player2Combo = new javax.swing.JComboBox<>();
        player3Label = new javax.swing.JLabel();
        player3Combo = new javax.swing.JComboBox<>();
        player4Label = new javax.swing.JLabel();
        player4Combo = new javax.swing.JComboBox<>();
        buttonPanel = new javax.swing.JPanel();
        startGameButton = new javax.swing.JButton();
        rulesButton = new javax.swing.JButton();
        statsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Dice Simulator 2016");
        getContentPane().add(titleLabel, java.awt.BorderLayout.NORTH);

        playerListPanel.setLayout(new java.awt.GridLayout(0, 2));

        player1Label.setText("Player 1");
        playerListPanel.add(player1Label);

        player1Combo.setEditable(true);
        playerListPanel.add(player1Combo);

        player2Label.setText("Player 2");
        playerListPanel.add(player2Label);

        player2Combo.setEditable(true);
        playerListPanel.add(player2Combo);

        player3Label.setText("Player 3");
        playerListPanel.add(player3Label);

        player3Combo.setEditable(true);
        playerListPanel.add(player3Combo);

        player4Label.setText("Player 4");
        playerListPanel.add(player4Label);

        player4Combo.setEditable(true);
        playerListPanel.add(player4Combo);

        getContentPane().add(playerListPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.GridLayout(0, 1));

        startGameButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        startGameButton.setText("Start game");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(startGameButton);

        rulesButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rulesButton.setText("Rules");
        buttonPanel.add(rulesButton);

        statsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        statsButton.setText("Stats");
        buttonPanel.add(statsButton);

        exitButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(exitButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameButtonActionPerformed
        if (creator != null) {
            String[] playerNames = {
                player1Combo.getSelectedItem().toString(),
                player2Combo.getSelectedItem().toString(),
                player3Combo.getSelectedItem().toString(),
                player4Combo.getSelectedItem().toString(),
            };
            // TODO: check for empty names
            // TODO: check for duplicate names
            GameModelInterface game = creator.createGame(playerNames);
        }
    }//GEN-LAST:event_startGameButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainGui gui = new MainGui();
                DummyStats stats = new DummyStats();
                gui.updatePlayerList(stats);
                gui.setVisible(true);
            }
        });
    }


    private static class DummyStats implements StatsModelInterface {

        private Player[] players = {
            new Player("Bob"),
            new Player("Lisa"),
            new Player("Ron"),
        };
        
        @Override
        public Player getPlayer(int index) {
            return players[index];
        }

        @Override
        public int getAllPlayerEverCount() {
            return players.length;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JComboBox<String> player1Combo;
    private javax.swing.JLabel player1Label;
    private javax.swing.JComboBox<String> player2Combo;
    private javax.swing.JLabel player2Label;
    private javax.swing.JComboBox<String> player3Combo;
    private javax.swing.JLabel player3Label;
    private javax.swing.JComboBox<String> player4Combo;
    private javax.swing.JLabel player4Label;
    private javax.swing.JPanel playerListPanel;
    private javax.swing.JButton rulesButton;
    private javax.swing.JButton startGameButton;
    private javax.swing.JButton statsButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}