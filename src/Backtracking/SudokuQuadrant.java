package Logic;

public class SudokuQuadrant {

    private final int[][] quadrant = new int[3][3];
    private static final int DIMENSION = 3;

    public SudokuQuadrant(){
        startQuadrant();
    }

    public void addValue(int x, int y, int value){
        checkExceptions(value, x);
        checkExceptions(value, y);
    }

    private boolean isValidValue(int value){
        return value > 0 && value < 10;
    }

    private boolean isValidPosition(int x, int y){
        return x > 0 && x <= DIMENSION && y > 0 && y <= DIMENSION;
    }

    public boolean checkQuadrant(int value){
        for(int i = 0; i < DIMENSION; i++)
            for(int j = 0; j < DIMENSION; j++)
                if(compareValues(value,quadrant[i][j]))
                    return false;
        return true;
    }

    public boolean checkRow(int value, int row){
        checkExceptions(value, row);
        for(int i = 0; i < DIMENSION; i++)
            if(compareValues(value,quadrant[i][row]))
                return false;
        return true;
    }

    public boolean checkColumn(int value, int column){
        checkExceptions(value, column);
        for(int i = 0; i < DIMENSION; i++)
            if(compareValues(value,quadrant[column][i]))
                return false;
        return true;
    }

    public boolean compareValues(int i, int j){
        return i == j;
    }

    public boolean validDimension(int dimension){
        return dimension > 0 && dimension < DIMENSION;
    }

    private void checkExceptions(int value, int dimension){
        if(!isValidValue(value))
            throw new IllegalArgumentException("value must be between 1 and 9");
        if(!validDimension(dimension))
            throw new IndexOutOfBoundsException("invalid columns");
    }

    private void startQuadrant(){
        for(int i = 0; i < DIMENSION; i++)
            for(int j = 0; j < DIMENSION; j++)
                addValue(i,j,-1);
    }

    public boolean isCompleted(){
        return checkQuadrant(-1);
    }

}
