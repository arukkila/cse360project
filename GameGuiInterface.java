
package cse360pro1;

/**
 * Generic interface for a GUI of an active game.
 * @author team 8
 */
public interface GameGuiInterface 
{
	
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

    /**
     * Tell the GUI to display a message to the user.
     * @param messageYo The message to display, yo.
     */
    public void showMessage(String messageYo);
}
