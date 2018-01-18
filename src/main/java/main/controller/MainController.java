package main.controller;

import chart.PieChart;
import com.opencsv.CSVReader;
import main.model.Row;
import main.view.Main;
import org.jfree.data.io.CSV;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static main.view.Main.PIE_CHART;

public class MainController implements ActionListener {


    final JFileChooser fc = new JFileChooser();

    private Main mainView;
    private int tabNumber = 1;


    public MainController(Main mainView) {
        this.mainView = mainView;
        this.mainView.getImportButton().addActionListener(this);
        this.mainView.getAddRowButton().addActionListener(this);
        this.mainView.getClearRowButton().addActionListener(this);
        this.mainView.getSaveButton().addActionListener(this);
        this.mainView.getGraphicButton().addActionListener(this);
        this.mainView.getNovaAbaButton().addActionListener(this);
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
                    reader = new CSVReader(new FileReader(file), ',', '"', 1);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //Read CSV line by line and use the string array as you want
                String[] nextLine;

                DefaultTableModel tm = new DefaultTableModel(null, new Object[]{"Nome", "Código", "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual"});
                //Constrói um modelo de tabela vazia com os correspondentes nomes das colunas
                try {
                    while ((nextLine = reader.readNext()) != null) {
                        if (nextLine != null) {
                            String[] values = Arrays.toString(nextLine).split(";");

                            if (values.length == 7) {
                                Row row = new Row(values);
                                //System.out.println(row);
                                CSVData.getInstance().getData().add(row);

                                //Adiciona uma linha na tabela
                                tm.addRow(new Object[]{row.getDescription(), row.getCode(), row.getPreviousBalance(), row.getPreviousDebt(), row.getPreviousCradit(), row.getCurrentBalance()});
                            }
                        }


                    }

                    //this.mainView.settModel(tm);
                    //this.mainView.setTable(new JTable(this.mainView.gettModel()));
                    //this.mainView.getTable().setGridColor(Color.black);
                    //this.mainView.getTable().setFillsViewportHeight(true);
                    //this.mainView.getScrollP().setViewportView(this.mainView.getTable());

                    JTable tbl = new JTable(tm);
                    tbl.setGridColor(Color.black);
                    JScrollPane sclp = new JScrollPane(tbl);


                    this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), sclp);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else {
                System.out.println("Open command cancelled by user.");
            }
        }

        if (actionEvent.getSource() == this.mainView.getAddRowButton()) {

            JScrollPane scp = (JScrollPane)(this.mainView.getTabbedPane().getComponentAt(this.mainView.getTabbedPane().getSelectedIndex()));
            JViewport v = scp.getViewport();
            JTable t = (JTable) v.getView();

            if (t.getSelectedRow() != -1) {
                ((DefaultTableModel) t.getModel()).insertRow(t.getSelectedRow() + 1, (Object[]) null);
                //this.mainView.settModel((DefaultTableModel) (this.mainView.getTable().getModel()));
            } else {
                ((DefaultTableModel) t.getModel()).addRow((Object[]) null);
                //this.mainView.settModel((DefaultTableModel) (this.mainView.getTable().getModel()));
            }


            this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), scp);


        }

        if (actionEvent.getSource() == this.mainView.getClearRowButton()) {
            JScrollPane scp = (JScrollPane)(this.mainView.getTabbedPane().getComponentAt(this.mainView.getTabbedPane().getSelectedIndex()));
            JViewport v = scp.getViewport();
            JTable t = (JTable) v.getView();
            if (t.getSelectedRow() != -1) {
                ((DefaultTableModel) t.getModel()).removeRow(t.getSelectedRow());
                //this.mainView.settModel((DefaultTableModel) (this.mainView.getTable().getModel()));
            }
            this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), scp);
        }

        if (actionEvent.getSource() == this.mainView.getSaveButton()) {
            //IMPLEMENTAR AQUI A LÓGICA PARA SALVAR
            System.out.println("save");
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
                                pieChart.add("Credito Anterior", row.getPreviousCradit());
                                pieChart.add("Balanço Atual", row.getCurrentBalance());
                                pieChart.show();
                            }
                        }
                    }
                    System.out.println("pegou np pie");
                    break;

            }

        }

        if(actionEvent.getSource() == this.mainView.getNovaAbaButton()){
            this.tabNumber++;
            JTable t = new JTable(new DefaultTableModel(null, new Object[]{"Nome", "Código", "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual"}));
            t.setGridColor(Color.black);
            JScrollPane scrlp = new JScrollPane(t);
            this.mainView.getTabbedPane().addTab("Tabela "+ this.tabNumber,scrlp);
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
