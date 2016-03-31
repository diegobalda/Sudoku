package Example;

import javax.swing.SwingUtilities;

/**
 * Created by diegobaldassare on 3/30/16.
 */
public class Solver implements Runnable {

    @Override
    public void run() {
        new SudokuFrame(new SudokuPuzzle());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Solver());
    }

}
