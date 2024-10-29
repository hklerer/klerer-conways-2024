package klerer.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameOfLifeControllerTest {

    @Test
    void toggleCellOn() {
        // given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleParser rleParser = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, rleParser);
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(1000).when(model).getHeight();

        // when
        controller.toggleCell(50, 50);

        // then
        verify(model).setCell(5, 10, 1);
        verify(view).repaint();
    }

    @Test
    void toggleCellOff() {
        // given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleParser rleParser = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, rleParser);
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(1000).when(model).getHeight();

        // when
        controller.toggleCell(50, 50);

        // then
        verify(model).setCell(5, 10, 0);
        verify(view).repaint();
    }


}