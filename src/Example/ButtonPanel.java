package Example;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Created by diegobaldassare on 3/30/16.
 */
public class ButtonPanel {

    protected static final Insets   buttonInsets    = new Insets(10, 10, 0, 10);

    private boolean                 isSolveButtonFirstTime;

    private JButton                 printButton;

    private JToggleButton           resetPuzzleButton;
    private JToggleButton           setValuesButton;
    private JToggleButton           solveButton;

    private JPanel                  panel;

    private SudokuFrame             frame;

    private SudokuPuzzle            model;

    public ButtonPanel(SudokuFrame frame, SudokuPuzzle model) {
        this.frame = frame;
        this.model = model;
        this.isSolveButtonFirstTime = true;
        createPartControl();
    }

    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        int gridy = 0;

        ToggleListener tListener = new ToggleListener();

        resetPuzzleButton = new JToggleButton("Reset Puzzle");
        resetPuzzleButton.addChangeListener(tListener);
        resetPuzzleButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                if (resetPuzzleButton.isSelected()) {
                    isSolveButtonFirstTime = true;
                    model.init();
                    frame.repaintSudokuPanel();
                    resetPuzzleButton.setSelected(false);
                    setValuesButton.setSelected(true);
                }
            }
        });
        addComponent(panel, resetPuzzleButton, 0, gridy++, 1, 1, buttonInsets,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        setValuesButton = new JToggleButton("Set Initial Values");
        setValuesButton.addChangeListener(tListener);
        setValuesButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                model.setSetValues(setValuesButton.isSelected());
            }
        });
        addComponent(panel, setValuesButton, 0, gridy++, 1, 1, buttonInsets,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        solveButton = new JToggleButton("Solve Puzzle");
        solveButton.addChangeListener(tListener);
        solveButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                if (isSolveButtonFirstTime && solveButton.isSelected()) {
                    SolvePuzzleRunnable runnable = new SolvePuzzleRunnable(
                            frame, model);
                    new Thread(runnable).start();
                    isSolveButtonFirstTime = false;
                }
            }
        });
        addComponent(panel, solveButton, 0, gridy++, 1, 1, buttonInsets,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        printButton = new JButton("Print Puzzle");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new Thread(new PrintActionListener(frame)).start();
            }
        });
        addComponent(panel, printButton, 0, gridy++, 1, 1, buttonInsets,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        tListener.setToggleButtons(resetPuzzleButton, setValuesButton,
                solveButton);

        setValuesButton.setSelected(true);
    }

    private void addComponent(Container container, Component component,
                              int gridx, int gridy, int gridwidth, int gridheight, Insets insets,
                              int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

}
