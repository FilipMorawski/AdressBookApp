package adressbook.domain;

import adressbook.entities.Contact;
import adressbook.gui.ChooseContactFrame;
import adressbook.gui.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.util.Iterator;
import java.util.List;

@Component
public class FrameController {
    @Autowired
    MainFrame mainFrame;
    private ChooseContactFrame contactListFrame;
    private DefaultListModel model;

    public Contact showContactFrame(List contactList) {
        model = new DefaultListModel();
        Contact chosenContact;
        Iterator iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact contact = (Contact) iterator.next();
            model.addElement(contact);
        }
        contactListFrame = new ChooseContactFrame(model);
        JOptionPane.showMessageDialog(mainFrame, contactListFrame,
                "Connected to database!", JOptionPane.INFORMATION_MESSAGE);
        chosenContact = contactListFrame.getChosenContact();
        if (chosenContact != null) {
            mainFrame.showContactToEdit(chosenContact);
        }
        return chosenContact;
    }

    public void showSavedDialog() {
        JOptionPane.showMessageDialog(mainFrame, "Contact succesfully saved",
                "Contact saved!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showConnectionDialog(EntityManagerFactory entityManagerFactory) {
        if (entityManagerFactory != null) {
            JOptionPane.showMessageDialog(mainFrame, "Connection Estabilished",
                    "Connected to database!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Cannot connect to database, check login parameters",
                    "Connection Denied!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void showDisconnectDialog(EntityManagerFactory entityManagerFactory) {
        if (!entityManagerFactory.isOpen()) {
            JOptionPane.showMessageDialog(mainFrame, "Succesfully disconnected",
                    "Disconnected!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showRemoveDialog() {
        JOptionPane.showMessageDialog(mainFrame, "Contact succesfully deleted",
                "Contact deleted!", JOptionPane.INFORMATION_MESSAGE);
        mainFrame.getButtonPanel().reorderButtonsAfterDeleteOrUpdate();
    }

    public void showUpdateDialog() {
        JOptionPane.showMessageDialog(mainFrame, "Contact succesfully updated",
                "Contact updated!", JOptionPane.INFORMATION_MESSAGE);
        mainFrame.getButtonPanel().reorderButtonsAfterDeleteOrUpdate();
    }
}
