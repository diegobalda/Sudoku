package Example;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by diegobaldassare on 3/30/16.
 */

public class SudokuFrame {

    private ButtonPanel     buttonPanel;

    private JFrame          frame;

    private SudokuPanel     sudokuPanel;

    private SudokuPuzzle    model;

    public SudokuFrame(SudokuPuzzle model) {
        this.model = model;
        createPartControl();
    }

    private void createPartControl() {
        sudokuPanel = new SudokuPanel(this, model);
        buttonPanel = new ButtonPanel(this, model);

        frame = new JFrame();
        frame.setTitle("Sudoku Puzzle Example.Solver");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(sudokuPanel);

        JPanel holderPanel = new JPanel();
        holderPanel.setLayout(new FlowLayout());
        holderPanel.add(buttonPanel.getPanel());
        mainPanel.add(holderPanel);

        frame.setLayout(new FlowLayout());
        frame.add(mainPanel);
        frame.pack();
        frame.setBounds(getBounds());
        frame.setVisible(true);
    }

    public void exitProcedure() {
        frame.dispose();
        System.exit(0);
    }

    protected Rectangle getBounds() {
        Rectangle f = frame.getBounds();
        Rectangle w = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();
        f.x = (w.width - f.width) / 2;
        f.y = (w.height - f.height) / 2;
        return f;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getSudokuPanel() {
        return sudokuPanel;
    }

    public void repaintSudokuPanel() {
        sudokuPanel.repaint();
    }

}
