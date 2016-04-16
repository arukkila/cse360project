package cse360pro1;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Michael
 */
public class ControllerTest {

    private static class DummyGameGuiInterface implements GameGuiInterface {
        public int notifyModelChangedCallCount = 0;
        public ArrayList<String> showMessageStrings = new ArrayList<>();
        @Override
        public void setModel(GameModelInterface model) {
        }

        @Override
        public void notifyModelChanged() {
            notifyModelChangedCallCount++;
        }

        @Override
        public void showMessage(String messageYo) {
            showMessageStrings.add(messageYo);
        }
    }

    @Test
    public void testNotNull() {
        Controller controller = new Controller("", "", "", "");
        controller.setGameGui(new DummyGameGuiInterface());
        assertNotNull(controller);
    }

    @Test
    public void testGetCurrentPlayer() {
        Controller controller = new Controller("", "", "", "");
        controller.setGameGui(new DummyGameGuiInterface());
        assertTrue(controller.getCurrentPlayer() >= 0 &&
                   controller.getCurrentPlayer() < 4);
    }

    @Test
    public void testGetNextPlayer() {
        Controller controller = new Controller("", "", "", "");
        controller.setGameGui(new DummyGameGuiInterface());
        int currentPlayer = controller.getCurrentPlayer();
        int nextPlayer = controller.getNextPlayer();
        assertEquals(currentPlayer + 1, nextPlayer);
    }

    @Test
    public void testRoll() {
        Controller controller = new Controller("", "", "", "");
        DummyGameGuiInterface dummyGui = new DummyGameGuiInterface();
        controller.setGameGui(dummyGui);
        Player currentPlayer = controller.getCurrentPlayerInCurrentGame();
        int currentPlayerScore = currentPlayer.getScore();
        controller.roll();
        assertTrue(
            dummyGui.notifyModelChangedCallCount > 0 &&
            dummyGui.showMessageStrings.size() > 0 &&
            currentPlayer.getScore() > currentPlayerScore
        );
    }

    @Test
    public void testSetGameGui() {
        Controller controller = new Controller("", "", "", "");
        controller.setGameGui(new DummyGameGuiInterface());
    }

    @Test
    public void testGetPlayersInCurrentGameCount() {
        Controller controller = new Controller("", "", "", "");
        controller.setGameGui(new DummyGameGuiInterface());
        assertEquals(4, controller.getPlayersInCurrentGameCount());
    }

    @Test
    public void testGetCurrentPlayerInCurrentGame() {
        String[] playerNames = {
            "a", "b", "c", "d"
        };
        Controller controller = new Controller(playerNames[0], playerNames[1],
                                               playerNames[2], playerNames[3]);
        controller.setGameGui(new DummyGameGuiInterface());
        Player player = controller.getCurrentPlayerInCurrentGame();
        int currentPlayerIndex = controller.getCurrentPlayer();
        assertEquals(playerNames[currentPlayerIndex],
                     player.getName());
    }

    @Test
    public void testGetPlayerInCurrentGame() {
        String[] playerNames = {
            "a", "b", "c", "d"
        };
        Controller controller = new Controller(playerNames[0], playerNames[1],
                                               playerNames[2], playerNames[3]);
        controller.setGameGui(new DummyGameGuiInterface());
        assertTrue(
            playerNames[0].equals(controller.getPlayerInCurrentGame(0).getName()) &&
            playerNames[1].equals(controller.getPlayerInCurrentGame(1).getName()) &&
            playerNames[2].equals(controller.getPlayerInCurrentGame(2).getName()) &&
            playerNames[3].equals(controller.getPlayerInCurrentGame(3).getName())
        );
    }

    @Test
    public void testGetLastRoll() {
        Controller controller = new Controller("", "", "", "");
        DummyGameGuiInterface dummyGui = new DummyGameGuiInterface();
        controller.setGameGui(dummyGui);
        controller.roll();
        int[] lastRoll = controller.getLastRoll();
        assertTrue(
            lastRoll.length == 3 &&
            lastRoll[0] >= 1 && lastRoll[0] <= 6 &&
            lastRoll[1] >= 1 && lastRoll[1] <= 6 &&
            lastRoll[2] >= 1 && lastRoll[2] <= 6
        );
    }

}
