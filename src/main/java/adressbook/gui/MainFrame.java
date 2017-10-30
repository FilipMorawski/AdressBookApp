package adressbook.gui;

import adressbook.entities.Contact;
import adressbook.gui.panels.ButtonPanel;
import adressbook.gui.panels.ConnectionPanel;
import adressbook.gui.panels.DataPanel;
import adressbook.gui.panels.TitlePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

@Component
public class MainFrame extends JFrame {
    @Autowired
    private ButtonPanel buttonPanel;
    @Autowired
    private ConnectionPanel connectionPanel;
    @Autowired
    private DataPanel dataPanel;
    @Autowired
    private TitlePanel titlePanel;

    private HashMap<String, String> textFieldsData;


    public MainFrame(ButtonPanel buttonPanel, ConnectionPanel connectionPanel,
                     DataPanel dataPanel, TitlePanel titlePanel)
            throws HeadlessException {

        this.buttonPanel = buttonPanel;
        this.connectionPanel = connectionPanel;
        this.dataPanel = dataPanel;
        this.titlePanel = titlePanel;

        this.setTitle("MySQL Adress Book");
        this.setSize(500, 360);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.add(this.dataPanel, BorderLayout.WEST);
        this.add(this.connectionPanel, BorderLayout.EAST);
        this.add(this.buttonPanel, BorderLayout.SOUTH);
        this.add(this.titlePanel, BorderLayout.NORTH);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public HashMap<String, String> getTextFieldsData() {
        HashMap<String, String> connectionFieldsData = connectionPanel.getTextFieldsData();
        HashMap<String, String> dataFieldsData = dataPanel.getTextFieldsData();
        textFieldsData = new HashMap<String, String>();
        textFieldsData.putAll(connectionFieldsData);
        textFieldsData.putAll(dataFieldsData);
        return textFieldsData;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public void showContactToEdit(Contact contactToEdit) {
        dataPanel.showContactToEdit(contactToEdit);
        buttonPanel.reorderButttonsAfterChooseContact();
    }
}
