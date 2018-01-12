package main.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;

public class Main extends JFrame{
    public static final String CODIGO = "Código";
    private JPanel mainPanel;
    private JButton importButton;
    private JTable table;
    private JScrollPane scrollP;
    private JButton addRowButton;
    private JButton clearRowButton;
    private JButton graphicButton;
    private JButton saveButton;
    private JComboBox graphicComboBox;
    private JLabel graphicTypeLabel;
    private JButton gerarTemplateButton;
    private DefaultTableModel tModel;

    public static final String PIE_CHART = "Pizza";
    public static final String BAR_CHART = "Barras";

    public Main() {
        this.setTitle("main");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.tModel = new DefaultTableModel(null, new Object[]{"Nome", CODIGO, "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual"});
        this.table = new JTable(this.tModel);
        this.table.setFillsViewportHeight(true);
        this.scrollP.setViewportView(this.table);
        try {
            this.importButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("importTable.png"))));
            this.getGraphicButton().setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("graphic.png"))));
            this.getSaveButton().setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("save.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.graphicComboBox.addItem(PIE_CHART);
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

    public JButton getGraphicButton() {
        return graphicButton;
    }

    public void setGraphicButton(JButton graphicButton) {
        this.graphicButton = graphicButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JComboBox getGraphicComboBox() {
        return graphicComboBox;
    }

    public void setGraphicComboBox(JComboBox graphicComboBox) {
        this.graphicComboBox = graphicComboBox;
    }

    public JLabel getGraphicTypeLabel() { return graphicTypeLabel; }

    public void setGraphicTypeLabel(JLabel graphicTypeLabel) { this.graphicTypeLabel = graphicTypeLabel; }

    public JButton getGerarTemplateButton() {
        return gerarTemplateButton;
    }
}
