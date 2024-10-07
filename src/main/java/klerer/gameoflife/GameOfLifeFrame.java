package klerer.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class GameOfLifeFrame extends JFrame {
    private final GameOfLife gameOfLife = new GameOfLife(50, 50);  // Initialize with a smaller grid
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
        JButton pasteButton = new JButton("paste");
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(pasteButton);
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

        pasteButton.addActionListener(e -> handlePasteAction());
    }

    private void blockPattern() {
        gameOfLife.setCell(1, 1, 1);
        gameOfLife.setCell(1, 2, 1);
        gameOfLife.setCell(2, 1, 1);
        gameOfLife.setCell(2, 2, 1);
    }

    private void handlePasteAction() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String clipboardContent = (String) clipboard.getData(DataFlavor.stringFlavor);

            if (clipboardContent.startsWith("http")) {
                loadRLEFromURL(clipboardContent);
            } else if (new File(clipboardContent).exists()) {
                loadRLEFromFile(clipboardContent);
            } else {
                loadRLEFromText(clipboardContent);
            }

        } catch (UnsupportedFlavorException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Clipboard does not contain valid RLE data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadRLEFromURL(String urlString) {
        try (InputStream inputStream = new URL(urlString).openStream()) {
            String rleContent = IOUtils.toString(inputStream, "UTF-8");
            loadRLEIntoGameOfLife(rleContent);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(this, "Invalid URL", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load RLE from URL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadRLEFromFile(String filePath) {
        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            String rleContent = IOUtils.toString(inputStream, "UTF-8");
            loadRLEIntoGameOfLife(rleContent);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load RLE from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadRLEFromText(String rleContent) {
        loadRLEIntoGameOfLife(rleContent);
    }

    private void loadRLEIntoGameOfLife(String rleContent) {
        int gridSize = Math.max(100, Math.max(gameOfLife.getWidth(), gameOfLife.getHeight()));
        gameOfLife.resizeGrid(gridSize, gridSize);
        gameOfLife.loadRLEInCenter(rleContent);
        repaint();
    }
}
