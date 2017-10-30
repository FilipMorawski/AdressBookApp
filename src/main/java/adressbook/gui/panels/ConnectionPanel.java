package adressbook.gui.panels;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ConnectionPanel extends JPanel {
    private JLabel url, database, user, password;
    private JTextField enterUrl, enterDatabase, enterUser;
    private JPasswordField enterPassword;
    private ArrayList<JComponent> components;
    private HashMap<String, String> textFieldsData;


    public ConnectionPanel() {
        components = new ArrayList<JComponent>();

        url = new JLabel("Url");
        enterUrl = new JTextField();
        enterUrl.setName("url");
        components.add(url);
        components.add(enterUrl);

        database = new JLabel("Database");
        enterDatabase = new JTextField();
        enterDatabase.setName("database");
        components.add(database);
        components.add(enterDatabase);

        user = new JLabel("User");
        enterUser = new JTextField();
        enterUser.setName("user");
        components.add(user);
        components.add(enterUser);

        password = new JLabel("Password");
        enterPassword = new JPasswordField();
        enterPassword.setEchoChar('*');
        enterPassword.setName("password");
        components.add(password);
        components.add(enterPassword);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int i = 0;
        for (JComponent jComponent : components) {

            if (i % 2 != 0) {
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = i - 1;
                c.gridx = 1;
                this.add(jComponent, c);

                JTextField textField = (JTextField) jComponent;
                textField.setColumns(12);
                textField.setHorizontalAlignment(SwingConstants.RIGHT);
            } else {
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = i;
                c.gridx = 0;
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
}
