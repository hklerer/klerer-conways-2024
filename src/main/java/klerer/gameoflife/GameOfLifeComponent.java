package klerer.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOfLifeComponent extends JComponent {
    private final GameOfLife gameOfLife;
    private final int cellSize = 20;

    public GameOfLifeComponent(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellX = e.getX() / cellSize;
                int cellY = e.getY() / cellSize;

                int currentState = gameOfLife.getCell(cellX, cellY);
                if (currentState == 1) {
                    gameOfLife.setCell(cellX, cellY, 0);
                } else {
                    gameOfLife.setCell(cellX, cellY, 1);
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.blue);
        for (int y = 0; y < gameOfLife.getHeight(); y++) {
            for (int x = 0; x < gameOfLife.getWidth(); x++) {
                if (gameOfLife.getCell(x, y) == 1) {
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }

        g.setColor(Color.darkGray);
        for (int y = 0; y <= gameOfLife.getHeight(); y++) {
            g.drawLine(0, y * cellSize, gameOfLife.getWidth() * cellSize, y * cellSize);
        }
        for (int x = 0; x <= gameOfLife.getWidth(); x++) {
            g.drawLine(x * cellSize, 0, x * cellSize, gameOfLife.getHeight() * cellSize);
        }
    }
}
