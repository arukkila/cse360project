
package cse360pro1;

/**
 * Generic interface for a GUI of an active game.
 * @author Michael
 */
public interface GameGuiInterface {
    /**
     * Sets the underlying model for the GUI to use.
     * @param model game model (could be null)
     */
    public void setModel(GameModelInterface model);

    /**
     * Notifies the GUI that the state of the underlying model has changed
     * and therefore should update the display.
     */
    public void notifyModelChanged();
    
    public void showMessage(String messageYo);
}
