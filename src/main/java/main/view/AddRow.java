package main.view;

import javax.swing.*;

public class AddRow extends JFrame{
    private JPanel addRowPanel;
    private JLabel anoLabel;
    private JTextField anoTextField;
    private JLabel mesLabel;
    private JTextField mesTextField;
    private JButton addButton;
    private JLabel cabecalhoLabel;



    public JPanel getAddRowPanel() {
        return addRowPanel;
    }

    public void setAddRowPanel(JPanel addRowPanel) {
        this.addRowPanel = addRowPanel;
    }

    public JLabel getAnoLabel() {
        return anoLabel;
    }

    public void setAnoLabel(JLabel anoLabel) {
        this.anoLabel = anoLabel;
    }

    public JTextField getAnoTextField() {
        return anoTextField;
    }

    public void setAnoTextField(JTextField anoTextField) {
        this.anoTextField = anoTextField;
    }

    public JLabel getMesLabel() {
        return mesLabel;
    }

    public void setMesLabel(JLabel mesLabel) {
        this.mesLabel = mesLabel;
    }

    public JTextField getMesTextField() {
        return mesTextField;
    }

    public void setMesTextField(JTextField mesTextField) {
        this.mesTextField = mesTextField;
    }


    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public AddRow(){
        //this.setTitle("main");
        this.setContentPane(addRowPanel);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAddButton(this.addButton);
        this.setAnoLabel(anoLabel);
        this.setAnoTextField(anoTextField);
        this.setMesLabel(mesLabel);
        this.setMesTextField(mesTextField);
        this.pack();

    }

}
