package main.controller;

import chart.PieChart;
import com.opencsv.CSVReader;
import main.model.Row;
import main.view.Main;
import main.model.DB_Row;
import org.jfree.data.io.CSV;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static main.view.Main.PIE_CHART;

public class MainController implements ActionListener {


    final JFileChooser fc = new JFileChooser();

    private Main mainView;
    private boolean isShowingAddWindow = false;


    public MainController(Main mainView) {
        this.mainView = mainView;
        this.mainView.getImportButton().addActionListener(this);
        this.mainView.getAddRowButton().addActionListener(this);
        this.mainView.getClearRowButton().addActionListener(this);
        this.mainView.getSaveButton().addActionListener(this);
        this.mainView.getGraphicButton().addActionListener(this);
        this.mainView.getGerarTemplateButton().addActionListener(this);

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
                    reader = new CSVReader(new FileReader(file), ';', '"', 1);
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
                            if (nextLine.length == 7) {
                                Row row = new Row(nextLine);
                                //System.out.println(row);
                                CSVData.getInstance().getData().add(row);

                                //Adiciona uma linha na tabela
                                tm.addRow(new Object[]{row.getDescription(), row.getCode(), row.getPreviousBalance(), row.getPreviousDebt(), row.getPreviousCredit(), row.getCurrentBalance()});
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

        if (actionEvent.getSource() == this.mainView.getAddRowButton()) {

            if (this.mainView.getTable().getSelectedRow() != -1) {
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

        if (actionEvent.getSource() == this.mainView.getClearRowButton()) {
            if (this.mainView.getTable().getSelectedRow() != -1) {
                ((DefaultTableModel) this.mainView.getTable().getModel()).removeRow(this.mainView.getTable().getSelectedRow());
                this.mainView.settModel((DefaultTableModel) (this.mainView.getTable().getModel()));
            }
        }

        if (actionEvent.getSource() == this.mainView.getSaveButton()) {
            //IMPLEMENTAR AQUI A LÓGICA PARA SALVAR
            //System.out.println("save");
            DB_Row dbrow = new DB_Row();

            Row row = new Row("Dummy", 54321, (float)500.3, (float)200.1, (float)400.2, (float)700.53, true, (float)10.3, 2018, 11, false, false);

            dbrow.insertRow(row);
        }

        if (actionEvent.getSource() == this.mainView.getGraphicButton()) {
            //IMPLEMENTAR AQUI A LÓGICA DO GRÁFICO
            String option = (String) (this.mainView.getGraphicComboBox().getSelectedItem());

            switch (option) {
                case PIE_CHART:
                    if (CSVData.getInstance().getData() != null){
                        List<Row> rows =  CSVData.getInstance().getData();
                        for (Row row:
                                rows) {
                            if(row.getCode() == 100){
                                PieChart pieChart = new PieChart();
                                pieChart.add("Débito Anterior", row.getPreviousDebt());
                                pieChart.add("Credito Anterior", row.getPreviousCredit());
                                pieChart.add("Balanço Atual", row.getCurrentBalance());
                                pieChart.show();
                            }
                        }
                    }
                    break;

            }
            System.out.println("graphic");
        }

        if (actionEvent.getSource() == mainView.getGerarTemplateButton()){
            JFileChooser chooser = new JFileChooser();
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION){
                try {
                    FileWriter writer = new FileWriter(chooser.getSelectedFile() + " .csv");
                    Scanner scanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("template.csv"));
                    String csvFile = scanner.useDelimiter("%A").next();
                    writer.write(csvFile);
                    writer.flush();
                    writer.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

