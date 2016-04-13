/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse360pro1;

/**
 *
 * @author Michael
 */
public interface MainModelInterface {
    /**
     * Gets the number of players a game can have
     * (basically always return 4).
     * @return 4.
     */
    public int getPlayerCount();
    public String getPlayer(int index);
    public void setPlayer(int index, String name);
    public GameModelInterface startGame();
}
