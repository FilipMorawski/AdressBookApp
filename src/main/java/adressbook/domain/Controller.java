package adressbook.domain;

import adressbook.entities.Contact;
import adressbook.gui.MainFrame;
import adressbook.gui.listeners.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;

@Component
public class Controller {

    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private ConnectionButtonListener connectionButtonListener;
    @Autowired
    private GetContactButtonListener getContactButtonListener;
    @Autowired
    private SaveButtonListener saveButtonListener;
    @Autowired
    private ContactDAO contactDAO;
    @Autowired
    private PersistenceSettings persistenceSettings;
    @Autowired
    private FrameController frameController;
    @Autowired
    private UpdateButtonListener updateButtonListener;
    @Autowired
    private RemoveButtonListener removeButtonListener;
    private EntityManagerFactory entityManagerFactory;
    private HashMap<String, String> textFieldsData;
    private Contact contactToEdit;
    private boolean connected;

    public void start() {
        listenToAction();
    }

    private void listenToAction() {

        while (true) {
            if (connectionButtonListener.isPressed()) {
                if (!connected) {
                    handleConnection();
                    frameController.showConnectionDialog(entityManagerFactory);
                    if (connected) {
                        mainFrame.getButtonPanel().enableButtons();
                    }
                } else {
                    mainFrame.getButtonPanel().disableButtons();
                    disconnect();
                    frameController.showDisconnectDialog(entityManagerFactory);
                }
                connectionButtonListener.setPressed(false);
            }
            if (saveButtonListener.isPressed()) {
                getText();
                contactDAO.addContact(entityManagerFactory, textFieldsData);
                frameController.showSavedDialog();
                saveButtonListener.setPressed(false);
            }
            if (getContactButtonListener.isPressed()) {
                List contactList = contactDAO.getContactList(entityManagerFactory);
                contactToEdit = frameController.showContactFrame(contactList);
                getContactButtonListener.setPressed(false);
            }
            if (updateButtonListener.isPressed()) {
                getText();
                contactDAO.updateContact(entityManagerFactory, contactToEdit.getId(), textFieldsData);
                updateButtonListener.setPressed(false);
                frameController.showUpdateDialog();
            }
            if (removeButtonListener.isPressed()) {
                contactDAO.removeContact(entityManagerFactory, contactToEdit.getId());
                removeButtonListener.setPressed(false);
                frameController.showRemoveDialog();
            }
        }
    }

    private void disconnect() {
        this.connected = false;
        this.entityManagerFactory.close();
    }

    private void handleConnection() {
        getText();
        try {
            entityManagerFactory = persistenceSettings.setPersistenceSettings(textFieldsData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (entityManagerFactory != null) {
            this.connected = true;
        }
    }

    private void getText() {
        textFieldsData = mainFrame.getTextFieldsData();
    }
}
