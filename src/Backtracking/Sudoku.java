package Logic;

import javafx.beans.binding.MapBinding;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

public class Sudoku {

    private Map<Integer,SudokuQuadrant> sudoku = new HashMap<Integer,SudokuQuadrant>();
    private static int DIMENSION;
    private int[][] puzzle;

    public Sudoku(int dimension){
        if(!isValisDimension(dimension))
            throw new IllegalArgumentException("Illegal dimension");
        this.DIMENSION = dimension;
        this.puzzle = new int[DIMENSION][DIMENSION];
    }

    public boolean isValisDimension(int dimension){
        return dimension > 0 && (dimension%3 == 0);
    }

    public int[][] solvePuzle(){
        //backtracking
        return null;
    }



}
