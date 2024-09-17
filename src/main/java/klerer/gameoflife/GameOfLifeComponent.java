package klerer.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameOfLifeComponent extends JComponent {
    private final GameOfLife gameOfLife;

    public GameOfLifeComponent(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               // gameOfLife.put(e.getX(), e.getY(), 10, 10, .3);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //gameOfLife.put(e.getX(), e.getY(), 10, 10, .3);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

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
                    g.fillRect(x * 20, y * 20, 20, 20);
                }
            }
        }

        g.setColor(Color.darkGray);
        for (int y = 0; y < gameOfLife.getHeight(); y++) {
            for (int x = 0; x < gameOfLife.getWidth(); x++) {
                g.drawRect(x * 20, y * 20, 20, 20);
            }
        }
    }
}


