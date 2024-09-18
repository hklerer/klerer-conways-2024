package klerer.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifeFrame extends JFrame {
    private final GameOfLife gameOfLife = new GameOfLife(50, 50);
    private Timer timer;

    public GameOfLifeFrame() {
        setTitle("Game Of Life Frame");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        GameOfLifeComponent gameComponent = new GameOfLifeComponent(gameOfLife);
        add(gameComponent, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("play");
        JButton pauseButton = new JButton("pause");
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        add(buttonPanel, BorderLayout.SOUTH);

        blockPattern();

        playButton.addActionListener(e -> {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameOfLife.nextGeneration();
                    repaint();
                }
            });
            timer.start();
        });

        pauseButton.addActionListener(e -> {
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
        });
    }

    private void blockPattern() {
        gameOfLife.setCell(1, 1, 1);
        gameOfLife.setCell(1, 2, 1);
        gameOfLife.setCell(2, 1, 1);
        gameOfLife.setCell(2, 2, 1);
    }

}
