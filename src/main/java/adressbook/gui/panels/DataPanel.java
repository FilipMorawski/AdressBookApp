package adressbook.gui.panels;

import adressbook.entities.Contact;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class DataPanel extends JPanel {
    private JLabel firstName, lastName,
            nickname, email, phoneNumber, street,
            streetNumber, city, zipcode, info;
    private JTextField enterFirstName, enterLastName,
            enterNickname, enterEmail, enterPhoneNumber, enterStreet,
            enterStreetNumber, enterCity, enterZipcode;
    private JTextArea enterInfo;
    private ArrayList<JComponent> components;
    private HashMap<String, String> textFieldsData;

    public DataPanel() {
        components = new ArrayList<JComponent>();

        firstName = new JLabel("First name");
        components.add(firstName);
        enterFirstName = new JTextField("First name");
        enterFirstName.setName("firstName");
        components.add(enterFirstName);

        lastName = new JLabel("Last name");
        components.add(lastName);
        enterLastName = new JTextField("Last name");
        enterLastName.setName("lastName");
        components.add(enterLastName);

        nickname = new JLabel("Nickname");
        components.add(nickname);
        enterNickname = new JTextField("Nickname");
        enterNickname.setName("nickname");
        components.add(enterNickname);

        email = new JLabel("e-mail");
        components.add(email);
        enterEmail = new JTextField("E-mail");
        enterEmail.setName("email");
        components.add(enterEmail);

        phoneNumber = new JLabel("Phone number");
        components.add(phoneNumber);
        enterPhoneNumber = new JTextField("Phone number");
        enterPhoneNumber.setName("phoneNumber");
        components.add(enterPhoneNumber);

        street = new JLabel("Street");
        components.add(street);
        enterStreet = new JTextField("Street");
        enterStreet.setName("street");
        components.add(enterStreet);

        streetNumber = new JLabel("Street number");
        components.add(streetNumber);
        enterStreetNumber = new JTextField("Street number");
        enterStreetNumber.setName("streetNumber");
        components.add(enterStreetNumber);

        zipcode = new JLabel("Zip-code");
        components.add(zipcode);
        enterZipcode = new JTextField("Zip-code");
        enterZipcode.setName("zipcode");
        components.add(enterZipcode);

        city = new JLabel("City");
        components.add(city);
        enterCity = new JTextField("City");
        enterCity.setName("city");
        components.add(enterCity);

        info = new JLabel("Additional info");
        components.add(info);
        enterInfo = new JTextArea();
        enterInfo.setName("info");
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        enterInfo.setBorder(border);
        enterInfo.setRows(5);
        enterInfo.setLineWrap(true);
        enterInfo.addFocusListener(new TextFieldFocusListener());
        components.add(enterInfo);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int i = 0;
        for (JComponent jComponent : components) {
            if (i == components.size() - 1) {
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = i - 1;
                this.add(jComponent, c);
                break;
            }
            if (i % 2 != 0) {
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = i - 1;
                this.add(jComponent, c);
                JTextField textField = (JTextField) jComponent;
                textField.setColumns(12);
                textField.setHorizontalAlignment(SwingConstants.RIGHT);
                textField.setCaretPosition(SwingConstants.RIGHT);
                textField.addFocusListener(new TextFieldFocusListener());
            } else {
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = i;
                this.add(jComponent, c);
            }
            i++;
        }


    }

    public HashMap<String, String> getTextFieldsData() {
        int i = 0;
        textFieldsData = new HashMap<String, String>();
        for (JComponent jComponent : components) {
            if (i % 2 != 0) {
                JTextComponent textField = (JTextComponent) jComponent;
                String text = textField.getText();
                textFieldsData.put(textField.getName(), text);
            }
            i++;
        }
        return textFieldsData;
    }

    public void showContactToEdit(Contact contactToEdit) {
        enterFirstName.setText(contactToEdit.getFirstName());
        enterLastName.setText(contactToEdit.getLastName());
        enterNickname.setText(contactToEdit.getNickname());
        enterEmail.setText(contactToEdit.getEmail());
        enterPhoneNumber.setText(contactToEdit.getPhoneNumber());
        enterStreet.setText(contactToEdit.getStreet());
        enterStreetNumber.setText(contactToEdit.getStreetNumber());
        enterCity.setText(contactToEdit.getCity());
        enterZipcode.setText(contactToEdit.getZipCode());
        enterInfo.setText(contactToEdit.getAdditionalInfo());

    }

    public class TextFieldFocusListener implements FocusListener {
        public void focusGained(FocusEvent e) {
            JTextComponent source = (JTextComponent) e.getSource();
            source.selectAll();
        }

        public void focusLost(FocusEvent e) {

        }
    }
}
