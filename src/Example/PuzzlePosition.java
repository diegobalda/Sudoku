package Example; /**
 * Created by diegobaldassare on 3/30/16.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuzzlePosition {

    private List<Integer> guesses;

    private Random random;

    private SudokuCell sudokuCell;

    private SudokuCell[][] position;

    public PuzzlePosition(SudokuCell sudokuCell, SudokuCell[][] position) {
        this.guesses = new ArrayList();
        this.sudokuCell = sudokuCell;
        this.position = position;
        this.random = new Random();
    }

    public void addGuess(int guess) {
        this.guesses.add(guess);
    }

    public SudokuCell getSudokuCell() {
        return sudokuCell;
    }

    public SudokuCell[][] getPosition() {
        return position;
    }

    public List getPossibleValues() {
        return sudokuCell.getPossibleValues();
    }

    public boolean guessesLeft() {
        return guesses.size() < sudokuCell.getPossibleValuesCount();
    }
    public int getGuess() {
        List<Integer> list = new ArrayList();
        for (Integer number : sudokuCell.getPossibleValues()) {
            list.add(number);
        }
        for (Integer number : guesses) {
            list.remove(number);
        }
        if (list.size() >= 1) {
            int index = random.nextInt(list.size());
            return list.get(index);
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Puzzle position guesses: ");
        for (int i = 0; i < guesses.size(); i++) {
            builder.append(guesses.get(i));
            if (i < (guesses.size() - 1)) {
                builder.append(", ");
            }
        }
        builder.append("; ");
        builder.append(sudokuCell);
        return builder.toString();
    }
}
