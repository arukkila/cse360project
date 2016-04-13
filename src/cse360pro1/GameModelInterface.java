
package cse360pro1;

/**
 *
 * @author Michael
 */
public interface GameModelInterface {
    public void setGui(GameGuiInterface gameGui);
    public int getPlayerCount();
    public Player getPlayer(int index);
    public Player getCurrentPlayer();
    public boolean roll();
    public String getLastMessageForPlayer();
    public void nextTurn();
    public void endGame();
}
