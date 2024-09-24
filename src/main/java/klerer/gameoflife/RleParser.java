package klerer.gameoflife;

public class RleParser {

    public void loadRle(GameOfLife gameOfLife, String rleString) {
        int row = 0;
        int col = 0;
        int count = 0;
        for (int i = 0; i < rleString.length(); i++) {
            char c = rleString.charAt(i);

            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else {
                if (count == 0) {
                    count = 1;
                }
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
