package Example;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Created by diegobaldassare on 3/30/16.
 */
public class SudokuPuzzle {

    private boolean isSetValues;

    private int drawWidth;
    private int puzzleWidth;

    private int[][] cellPosition    = { { 1, 4, 7, 1, 4, 7, 1, 4, 7 },
            { 2, 5, 8, 2, 5, 8, 2, 5, 8 }, { 3, 6, 9, 3, 6, 9, 3, 6, 9 },
            { 1, 4, 7, 1, 4, 7, 1, 4, 7 }, { 2, 5, 8, 2, 5, 8, 2, 5, 8 },
            { 3, 6, 9, 3, 6, 9, 3, 6, 9 }, { 1, 4, 7, 1, 4, 7, 1, 4, 7 },
            { 2, 5, 8, 2, 5, 8, 2, 5, 8 }, { 3, 6, 9, 3, 6, 9, 3, 6, 9 } };

    private SudokuCell[][] cells;

    public SudokuPuzzle() {
        this.drawWidth = 80;
        this.puzzleWidth = 9;
        this.cells = new SudokuCell[puzzleWidth][puzzleWidth];
        set(puzzleWidth);
    }

    private void set(int puzzleWidth) {
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                cells[i][j] = new SudokuCell();
                cells[i][j].setCellLocation(new Point(i, j));
            }
        }
    }

    public void init() {
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                cells[i][j].init(puzzleWidth);
            }
        }
    }

    public boolean isSetValues() {
        return isSetValues;
    }

    public void setSetValues(boolean isSetValues) {
        this.isSetValues = isSetValues;
    }

    public SudokuCell[][] getCells() {
        SudokuCell[][] cellcopy = new SudokuCell[puzzleWidth][puzzleWidth];
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                cellcopy[i][j] = cells[i][j].copy();
            }
        }
        return cellcopy;
    }

    public void setCells(SudokuCell[][] cells) {
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                this.cells[i][j] = cells[i][j].copy();
            }
        }
    }

    public void setCell(SudokuCell cell) {
        Point point = cell.getCellLocation();
        this.cells[point.x][point.y] = cell;
    }

    public int getDrawWidth() {
        return drawWidth;
    }

    public int getPuzzleWidth() {
        return puzzleWidth;
    }

    public SudokuCell getSudokuCellLocation(Point point) {
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                if (cells[i][j].contains(point)) {
                    return cells[i][j];
                }
            }
        }
        return null;
    }

    public SudokuCell getSudokuCell(Point cellPosition) {
        return cells[cellPosition.x][cellPosition.y];
    }

    // public void setSudokuCell(Example.SudokuCell sudokuCell, Point point) {
    // cells[point.x][point.y] = sudokuCell;
    // }

    public SudokuCell getSmallestPossibleValuesList() {
        int minCount = Integer.MAX_VALUE;
        Point point = new Point(-1, -1);
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                if (cells[i][j].getValue() <= 0) {
                    int count = cells[i][j].getPossibleValuesCount();
                    if ((count > 1) && (count < minCount)) {
                        minCount = count;
                        point.x = i;
                        point.y = j;
                    }
                    if (count == 2) {
                        return cells[i][j];
                    }
                }
            }
        }
        if ((point.x < 0) || (point.y < 0)) {
            return null;
        } else {
            return cells[point.x][point.y];
        }
    }

    public void removePossibleValue(SudokuCell cell) {
        int value = cell.getValue();
        Point point = cell.getCellLocation();

        for (int i = 0; i < puzzleWidth; i++) {
            cells[i][point.y].removePossibleValue(value);
        }
        for (int j = 0; j < puzzleWidth; j++) {
            cells[point.x][j].removePossibleValue(value);
        }

        int ii = point.x / 3;
        int jj = point.y / 3;
        for (int i = ii * 3; i < (ii + 1) * 3; i++) {
            for (int j = jj * 3; j < (jj + 1) * 3; j++) {
                cells[i][j].removePossibleValue(value);
            }
        }
    }

    public boolean isIncomplete() {
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                if (cells[i][j].getValue() <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInaccurate() {
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                if ((cells[i][j].getValue() <= 0)
                        && (cells[i][j].getPossibleValuesCount() <= 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Point getSinglePossibleValue() {
        for (int i = 0; i < puzzleWidth; i++) {
            for (int j = 0; j < puzzleWidth; j++) {
                if (cells[i][j].getValue() <= 0) {
                    if (cells[i][j].getPossibleValuesCount() == 1) {
                        return cells[i][j].getCellLocation();
                    }
                }
            }
        }
        return null;
    }

    public void draw(Graphics g) {
        int x = 0;
        for (int i = 0; i < puzzleWidth; i++) {
            int y = 0;
            for (int j = 0; j < puzzleWidth; j++) {
                Rectangle r = new Rectangle(x, y, drawWidth, drawWidth);
                cells[i][j].setBounds(r);
                cells[i][j].draw(g, x, y, drawWidth, cellPosition[i][j]);
                y += drawWidth;
            }
            x += drawWidth;
        }
    }
}
