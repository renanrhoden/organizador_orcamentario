package main.controller;

import com.opencsv.CSVReader;
import main.model.Row;
import main.view.Main;
import main.view.AddRow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JScrollPane;

public class MainController implements ActionListener{


    final JFileChooser fc = new JFileChooser();

    private Main mainView;
    private boolean isShowingAddWindow = false;


    public MainController(Main mainView) {
        this.mainView = mainView;
        this.mainView.getImportButton().addActionListener(this);
        this.mainView.getAddRowButton().addActionListener(this);
        this.mainView.getClearRowButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.mainView.getImportButton()) {
            int returnVal = fc.showOpenDialog(mainView);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println("Opening: " + file.getName() + ".");
                CSVReader reader = null;
                try {
                    reader = new CSVReader(new FileReader(file), ',' , '"' , 1);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //Read CSV line by line and use the string array as you want
                String[] nextLine;

                //Constrói um modelo de tabela vazia com os correspondentes nomes das colunas
                DefaultTableModel tm = new DefaultTableModel(null, new Object[]{"Nome", "Código", "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual"});
                try {
                    while ((nextLine = reader.readNext()) != null) {
                        if (nextLine != null) {
                            String[] values = Arrays.toString(nextLine).split(";");

                            if (values.length == 7) {
                                Row row = new Row(values);
                                //System.out.println(row);

                                //Adiciona uma linha na tabela
                                tm.addRow(new Object[]{row.getDescription(), row.getCode(), row.getPreviousBalance(), row.getPreviousDebt(), row.getPreviousCradit(), row.getCurrentBalance()});
                            }
                        }


                    }

                    this.mainView.settModel(tm);
                    this.mainView.setTable(new JTable(this.mainView.gettModel()));
                    this.mainView.getTable().setFillsViewportHeight(true);
                    this.mainView.getScrollP().setViewportView(this.mainView.getTable());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else {
                System.out.println("Open command cancelled by user.");
            }
        }

        if (actionEvent.getSource() == this.mainView.getAddRowButton()){

            if (this.mainView.getTable().getSelectedRow() != -1){
                ((DefaultTableModel) this.mainView.getTable().getModel()).insertRow(this.mainView.getTable().getSelectedRow() + 1, (Object[]) null);
                this.mainView.settModel((DefaultTableModel) (this.mainView.getTable().getModel()));
            } else {
                ((DefaultTableModel) this.mainView.getTable().getModel()).addRow((Object[]) null);
                this.mainView.settModel((DefaultTableModel) (this.mainView.getTable().getModel()));
            }

            /*
            if(!this.isShowingAddWindow){
                this.isShowingAddWindow = true;
                AddRow addRowWindow = new AddRow();
                JFrame fr = new JFrame();
                fr.add(addRowWindow.getAddRowPanel());
                fr.pack();
                fr.setVisible(true);
            }*/
        }

        if(actionEvent.getSource() == this.mainView.getClearRowButton()){
            if(this.mainView.getTable().getSelectedRow() != -1) {
                ((DefaultTableModel) this.mainView.getTable().getModel()).removeRow(this.mainView.getTable().getSelectedRow());
                this.mainView.settModel((DefaultTableModel) (this.mainView.getTable().getModel()));
            }
        }
    }
}
