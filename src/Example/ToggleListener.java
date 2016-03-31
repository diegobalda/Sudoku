package Example; /**
 * Created by diegobaldassare on 3/30/16.
 */
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ToggleListener implements ChangeListener {

    private JToggleButton[] toggleButtons;

    public void setToggleButtons(JToggleButton... buttons) {
        toggleButtons = new JToggleButton[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            toggleButtons[i] = buttons[i];
        }
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        JToggleButton toggleButton = (JToggleButton) event.getSource();
        if (toggleButton.isSelected()) {
            for (JToggleButton button : toggleButtons) {
                if (!button.equals(toggleButton)) {
                    button.setSelected(false);
                }
            }
        }
    }

}
