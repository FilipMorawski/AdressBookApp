package adressbook.gui;

import adressbook.entities.Contact;

import javax.swing.*;


public class ChooseContactFrame extends JPanel {
    private Box verticalBox;
    private JList contacts;
    private DefaultListModel list;
    private JLabel listLabel;
    private Contact chosenContact;

    public ChooseContactFrame(DefaultListModel list) {
        verticalBox = Box.createVerticalBox();
        this.list = list;
        contacts = new JList(this.list);
        contacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contacts.setSelectedIndex(0);
        JScrollPane scroll = new JScrollPane(contacts);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listLabel = new JLabel("Saved contacts list");
        verticalBox.add(listLabel);
        verticalBox.add(scroll);
        this.add(verticalBox);
    }

    public void setList(DefaultListModel list) {
        this.list = list;
    }

    public Contact getChosenContact() {
        chosenContact = (Contact) contacts.getSelectedValue();
        return chosenContact;
    }
}
