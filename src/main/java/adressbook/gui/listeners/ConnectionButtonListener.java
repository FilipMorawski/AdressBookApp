package adressbook.gui.listeners;

import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ConnectionButtonListener implements ActionListener {
    private boolean pressed;

    public ConnectionButtonListener() {
    }

    public void actionPerformed(ActionEvent e) {
        pressed = true;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
