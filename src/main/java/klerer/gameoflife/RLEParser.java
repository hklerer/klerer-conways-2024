package klerer.gameoflife;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RLEParser {

    public void loadRLE(GameOfLife gameOfLife, String rleString) {
        int row = 0, col = 0, count = 0;
        for (int i = 0; i < rleString.length(); i++) {
            char c = rleString.charAt(i);

            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else {
                if (count == 0) count = 1;

                switch (c) {
                    case 'b':
                        col += count;
                        break;
                    case 'o':
                        for (int j = 0; j < count; j++) {
                            if (col < gameOfLife.getWidth() && row < gameOfLife.getHeight()) {
                                gameOfLife.setCell(col, row, 1);
                            }
                            col++;
                        }
                        break;
                    case '$':
                        row++;
                        col = 0;
                        break;
                    case '!':
                        return;
                }
                count = 0;
            }
        }
    }
}
