
package cse360pro1;

/**
 * Generic interface for an active game.
 * @author Michael
 */
public interface GameModelInterface {
    /**
     * Sets the GUI to be notified for changes.
     * @param gameGui GUI object (can be null)
     */
    public void setGameGui(GameGuiInterface gameGui);

    /**
     * Gets the number of active players in the current game.
     * @return the number of players {@code >= 0}
     */
    public int getPlayersInCurrentGameCount();
    /**
     * Gets a player in the game
     * @param index from 0 to {@link #getPlayersInCurrentGameCount()}
     * @return the player (never null)
     */
    public Player getPlayerInCurrentGame(int index);

    /**
     * Gets the current player in the current game.
     * @return current player (never null)
     */
    public Player getCurrentPlayerInCurrentGame();

    /**
     * Rolls the dice for the current player.
    * @return if someone won the game.
     */
    public void roll();

    /**
     * Gets the last message to be displayed to the user.
     * @return a message (could be null)
     */
    //public String getLastMessageForPlayer();

    /**
     * Tells the current game to move to the next player's turn.
     */
   // public void nextTurn();

    /**
     * Tells the game to end the game prematurely.
     */
    public void endGame();
    
    /**
     * Gets win status of the game.
     * @return if game is won or not
     */
    public boolean gameWon();
    
    
    
}
