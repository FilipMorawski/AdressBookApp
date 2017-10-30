package adressbook.gui.panels;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class TitlePanel extends JPanel {
    private JPanel leftPanel, rightPanel;
    private JLabel rightLabel, leftLabel;

    public TitlePanel() {
        this.setLayout(new BorderLayout());
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();
        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);
    }

    public class LeftPanel extends JPanel {

        public LeftPanel() {
            this.setLayout(new BorderLayout());
            leftLabel = new JLabel("ENTER CONTACT INFORMATION");
            this.add(leftLabel, BorderLayout.CENTER);
        }
    }

    public class RightPanel extends JPanel {

        public RightPanel() {
            this.setLayout(new BorderLayout());
            rightLabel = new JLabel("ENTER DATABASE INFORMATION");
            this.add(rightLabel, BorderLayout.CENTER);
        }
    }
}

