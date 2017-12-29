package main.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JFrame{
    private JPanel mainPanel;
    private JButton importButton;
    private JTable table;
    private JScrollPane scrollP;
    private JButton addRowButton;
    private JButton clearRowButton;
    private JButton graphicButton;
    private JButton saveButton;
    private DefaultTableModel tModel;

    public Main() {
        this.setTitle("main");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.tModel = new DefaultTableModel(null, new Object[]{"Nome", "Código", "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual"});
        this.table = new JTable(this.tModel);
        this.table.setFillsViewportHeight(true);
        this.scrollP.setViewportView(this.table);
        try {
            this.importButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("importTable.png"))));
            this.graphicButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("graphic.png"))));
            this.saveButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("save.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setVisible(true);

    }


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

    public JTable getTable() {
        return this.table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public TableModel gettModel() {
        return this.tModel;
    }

    public void settModel(DefaultTableModel tModel) { this.tModel = tModel; }

    public JScrollPane getScrollP() { return scrollP; }

    public void setScrollP(JScrollPane scrollP) { this.scrollP = scrollP; }

    public JButton getAddRowButton() { return addRowButton; }

    public void setAddRowButton(JButton addRowButton) { this.addRowButton = addRowButton; }

    public JButton getClearRowButton() { return clearRowButton; }

    public void setClearRowButton(JButton clearRowButton) { this.clearRowButton = clearRowButton; }

}
