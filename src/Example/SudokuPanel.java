package Example;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Created by diegobaldassare on 3/30/16.
 */
public class SudokuPanel extends JPanel {

    private static final long   serialVersionUID    = 1L;

    private SudokuFrame         frame;

    private SudokuPuzzle        model;

    public SudokuPanel(SudokuFrame frame, SudokuPuzzle model) {
        this.frame = frame;
        this.model = model;
        createPartControl();
    }

    private void createPartControl() {
        new JPanel();
        int width = model.getDrawWidth() * model.getPuzzleWidth() + 1;
        addMouseListener(new SetValueListener(frame, model));
        setPreferredSize(new Dimension(width, width));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        model.draw(g);
    }

}