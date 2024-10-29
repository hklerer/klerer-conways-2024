package klerer.gameoflife;

import java.awt.event.MouseEvent;

public class GameOfLifeController {
    private final GameOfLife model;
    private final GameOfLifeComponent view;
    private final RleParser rleParser;

    public GameOfLifeController(GameOfLife model, GameOfLifeComponent view, RleParser rleParser) {
        this.model = model;
        this.view = view;
        this.rleParser = rleParser;
    }

    public void startTimer() {

    }

    public void stopTimer() {

    }

    public void paste() {

    }

    public void toggleCell(int screenX, int screenY) {
        int x = screenX / view.getCellSize();
        int y = screenY / view.getCellSize();

        int currentState = model.getCell(x, y);
        if (currentState == 1) {
            model.setCell(x, y, 0);
        } else {
            model.setCell(x, y, 1);
        }

        view.repaint();
    }
}
