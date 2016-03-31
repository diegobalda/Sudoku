package Example;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

/**
 * Created by diegobaldassare on 3/30/16.
 */
public class SolvePuzzleRunnable implements Runnable {

    private static final boolean    DEBUG   = false;

    private int singleCount;
    private int stackCount;
    private int guessCount;

    private PuzzleStack puzzleStack;

    private Set guessLog;

    private SudokuFrame             frame;

    private SudokuPuzzle            model;

    public SolvePuzzleRunnable(SudokuFrame frame, SudokuPuzzle model) {
        this.frame = frame;
        this.model = model;
        this.puzzleStack = new PuzzleStack();
        this.guessLog = new HashSet();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        singleCount = 0;
        stackCount = 0;
        guessCount = 0;

        solveAllSingleValueCells();

        while (model.isIncomplete()) {
            SudokuCell sudokuCell = model.getSmallestPossibleValuesList();
            if (sudokuCell != null) {
                guessCellValue(sudokuCell);
                guessAllSingleValueCells();
            } else if (guessCellValueAgain()) {
                guessAllSingleValueCells();
            } else {
                break;
            }
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        new SolutionDialog(frame, singleCount, guessCount, elapsedTime);
    }

    private void solveAllSingleValueCells() {
        Point cellPosition = model.getSinglePossibleValue();
        while (cellPosition != null) {
            singleCount++;
            SudokuCell sudokuCell = model.getSudokuCell(cellPosition);
            List<Integer> list = sudokuCell.getPossibleValues();
            int value = list.get(0);
            sudokuCell.setValue(value);
            model.removePossibleValue(sudokuCell);
            sudokuCell.clearPossibleValues();
            repaintSudokuPanel();
            cellPosition = model.getSinglePossibleValue();
        }
    }

    private void guessCellValue(SudokuCell sudokuCell) {
        SudokuCell copyCell = sudokuCell.copy();
        PuzzlePosition puzzlePosition = new PuzzlePosition(copyCell,
                model.getCells());
        int value = puzzlePosition.getGuess();
        puzzlePosition.addGuess(value);
        puzzleStack.pushStack(puzzlePosition);
        stackCount++;
        guessCount++;
        if (DEBUG) {
            System.out.println("stackCount: " + stackCount + ", guess: "
                    + value);
            System.out.println(puzzlePosition);
        }
        GuessLogEntry guessLogEntry = new GuessLogEntry(stackCount, value);
        if (guessLog.contains(guessLogEntry)) {
            puzzleStack.popStack();
            puzzleStack.popStack();
            stackCount -= 2;
            if (DEBUG) {
                System.out.println("stackCount: " + stackCount + ", guess: "
                        + value);
                System.out.println(puzzlePosition);
            }
        } else {
            guessLog.add(guessLogEntry);
            sudokuCell.setValue(value);
            model.removePossibleValue(sudokuCell);
            while (model.isInaccurate()) {
                guessCellValueAgain();
            }
            repaintSudokuPanel();
        }
    }

    private boolean guessCellValueAgain() {
        PuzzlePosition puzzlePosition = puzzleStack.peekStack();
        if (puzzlePosition != null) {
            model.setCells(puzzlePosition.getPosition());
            int value = puzzlePosition.getGuess();
            if (DEBUG) {
                System.out.println("stackCount: " + stackCount + ", guess: "
                        + value);
                System.out.println(puzzlePosition);
            }
            if (value > 0) {
                puzzleStack.popStack();
                guessCount++;
                puzzlePosition.addGuess(value);
                SudokuCell sudokuCell = puzzlePosition.getSudokuCell();
                model.setCell(sudokuCell);
                sudokuCell.setValue(value);
                model.removePossibleValue(sudokuCell);
                repaintSudokuPanel();
                puzzleStack.pushStack(puzzlePosition);
                return true;
            } else {
                puzzleStack.popStack();
                stackCount--;
                if (DEBUG) {
                    System.out.println("stackCount: " + stackCount
                            + ", guess: " + value);
                    System.out.println(puzzlePosition);
                }
                return false;
            }
        } else {
            return false;
        }
    }

    private void guessAllSingleValueCells() {
        Point cellPosition = model.getSinglePossibleValue();
        while (cellPosition != null) {
            singleCount++;
            SudokuCell sudokuCell = model.getSudokuCell(cellPosition);
            List<Integer> list = sudokuCell.getPossibleValues();
            int value = list.get(0);
            sudokuCell.setValue(value);
            model.removePossibleValue(sudokuCell);
            repaintSudokuPanel();
            cellPosition = model.getSinglePossibleValue();
        }
    }

    private void repaintSudokuPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.repaintSudokuPanel();
            }
        });
    }

}
