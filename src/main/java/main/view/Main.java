package main.view;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JButton getImportButton() {
        return importButton;
    }

    public void setImportButton(JButton importButton) {
        this.importButton = importButton;
    }

    private JPanel mainPanel;
    private JButton importButton;

    public Main() {
        this.setTitle("main");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
