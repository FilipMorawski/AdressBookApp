package adressbook.gui.panels;

import adressbook.gui.listeners.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonPanel extends JPanel {

    @Autowired
    private UpdateButtonListener updateButtonListener;
    @Autowired
    private ConnectionButtonListener connectionButtonListener;
    @Autowired
    private GetContactButtonListener getContactButtonListener;
    @Autowired
    private SaveButtonListener saveButtonListener;
    @Autowired
    private RemoveButtonListener removeButtonListener;

    private JButton connection, save, getContact, update, remove;
    private List<JButton> buttons;

    public ButtonPanel(ConnectionButtonListener connectionButtonListener,
                       GetContactButtonListener getContactButtonListener,
                       SaveButtonListener saveButtonListener, UpdateButtonListener updateButtonListener,
                       RemoveButtonListener removeButtonListener) {
        this.connectionButtonListener = connectionButtonListener;
        this.getContactButtonListener = getContactButtonListener;
        this.saveButtonListener = saveButtonListener;
        this.updateButtonListener = updateButtonListener;
        this.removeButtonListener = removeButtonListener;

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(border);
        connection = new JButton("Connect to database");
        connection.addActionListener(connectionButtonListener);
        save = new JButton("Add contact");
        save.addActionListener(saveButtonListener);
        save.setEnabled(false);
        getContact = new JButton("Get contact");
        getContact.addActionListener(getContactButtonListener);
        getContact.setEnabled(false);
        update = new JButton("Update");
        update.addActionListener(updateButtonListener);
        update.setEnabled(false);
        remove = new JButton("Delete");
        remove.addActionListener(removeButtonListener);
        remove.setEnabled(false);

        buttons = new ArrayList();
        buttons.add(save);
        buttons.add(getContact);
        buttons.add(update);
        buttons.add(remove);
        this.add(connection);
        this.add(save);
        this.add(getContact);
        this.add(update);
        this.add(remove);
    }

    public JButton getConnection() {
        return connection;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getGetContact() {
        return getContact;
    }

    public JButton getRemove() {
        return remove;
    }

    public JButton getUpdate() {
        return update;
    }

    public void enableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
        update.setEnabled(false);
        remove.setEnabled(false);
        connection.setText("Disconnect");
    }

    public void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        connection.setText("Connect");
    }

    public void reorderButtonsAfterDeleteOrUpdate() {
        update.setEnabled(false);
        remove.setEnabled(false);
        save.setEnabled(true);
    }

    public void reorderButttonsAfterChooseContact() {
        update.setEnabled(true);
        remove.setEnabled(true);
        save.setEnabled(false);

    }
}
