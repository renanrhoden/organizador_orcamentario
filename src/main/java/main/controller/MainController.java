package main.controller;

import chart.PieChart;
import com.opencsv.CSVReader;
import com.sun.org.apache.xpath.internal.operations.Bool;
import main.model.Row;
import main.view.AddRow;
import main.view.Main;
import main.model.DB_Row;
import org.jfree.data.io.CSV;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.event.*;

import static main.view.Main.PIE_CHART;

public class MainController implements ActionListener,TableModelListener {


    final JFileChooser fc = new JFileChooser();

    private Main mainView;
    private int tabNumber = 1;
    private AddRow addRow = new AddRow();
    private AddRowController addRowController = new AddRowController(addRow);


    public MainController(Main mainView) {
        this.mainView = mainView;
        this.mainView.getImportButton().addActionListener(this);
        this.mainView.getAddRowButton().addActionListener(this);
        this.mainView.getClearRowButton().addActionListener(this);
        this.mainView.getSaveButton().addActionListener(this);
        this.mainView.getGraphicButton().addActionListener(this);
        this.mainView.getNovaAbaButton().addActionListener(this);
        this.mainView.getGerarTemplateButton().addActionListener(this);
        this.mainView.getRemoverAbaButton().addActionListener(this);
        this.mainView.getCarregaDadosButton().addActionListener(this);


        showYearWindow();

        JScrollPane scp = (JScrollPane)(this.mainView.getTabbedPane().getComponentAt(this.mainView.getTabbedPane().getSelectedIndex()));
        JViewport v = scp.getViewport();
        JTable t = (JTable) v.getView();
        t.getModel().addTableModelListener((TableModelListener) this);

        this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), scp);


    }

    public void showYearWindow(){
        this.addRow = new AddRow();
        this.addRowController = new AddRowController(this.addRow);

        addRow.setAlwaysOnTop(true);
        addRow.setLocationByPlatform(true);
        addRow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addRow.setVisible(true);

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!addRow.getAnoTextField().getText().equals("") && !addRow.getMesTextField().getText().equals("")){
                    addRowController.setCount(addRowController.getCount() + 1);
                    //System.out.println(addRowController.getCount());
                    System.out.println(addRowController.getAddRowView().getAnoTextField().getText());
                    System.out.println(addRowController.getAddRowView().getMesTextField().getText());
                    addRow.setVisible(false);
                }
            }
        };

        addRow.getAddButton().addActionListener(a);
        
    }



    public void tableChanged(TableModelEvent e) {


        JScrollPane scp = (JScrollPane)(this.mainView.getTabbedPane().getComponentAt(this.mainView.getTabbedPane().getSelectedIndex()));
        JViewport v = scp.getViewport();
        JTable t = (JTable) v.getView();
        if (t.getSelectedRow() != -1){
            if(t.getModel().getValueAt(t.getSelectedRow(), 10) != null){
               if (!(Boolean) t.getModel().getValueAt(t.getSelectedRow(), 10)) {
                    t.getModel().setValueAt(new Boolean(true), t.getSelectedRow(), 10);
                }
            }else{
                t.getModel().setValueAt(new Boolean(true), t.getSelectedRow(), 10);
            }
            this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), scp);
        }


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

                DefaultTableModel tm = new DefaultTableModel(null, new Object[]{"Nome", "Código", "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual", "Porcentagem?", "Mudança de valor", "Ano", "Mês", "Atualizado?", "Carregado?"});
                //Constrói um modelo de tabela vazia com os correspondentes nomes das colunas

                tm.getColumnClass(6).getClass();
                try {
                    while ((nextLine = reader.readNext()) != null) {
                        if (nextLine != null) {
                            String[] values = Arrays.toString(nextLine).split(";");

                            if (values.length == 7) {
                                Row row = new Row(values);
                                //System.out.println(row);
                                CSVData.getInstance().getData().add(row);

                                //Adiciona uma linha na tabela
                                tm.addRow(new Object[]{row.getDescription(), row.getCode(), row.getPreviousBalance(), row.getPreviousDebt(), row.getPreviousCredit(), row.getCurrentBalance(), new Boolean(false), null, null, null, false, false});
                            }
                        }


                    }



                    JTable tbl = new JTable(tm){
                        @Override
                        public Class getColumnClass(int column) {
                            switch (column) {
                                case 0:
                                    return String.class;
                                case 1:
                                    return Integer.class;
                                case 2:
                                    return Float.class;
                                case 3:
                                    return Float.class;
                                case 4:
                                    return Float.class;
                                case 5:
                                    return Float.class;
                                case 6:
                                    return Boolean.class;
                                case 7:
                                    return Float.class;
                                case 8:
                                    return Integer.class;
                                case 9:
                                    return Integer.class;
                                case 10:
                                    return Boolean.class;
                                default:
                                    return Boolean.class;
                            }
                        }
                    };
                    tbl.setGridColor(Color.black);
                    //TableColumnModel tcm = tbl.getColumnModel();
                    tbl.getColumnModel().removeColumn(tbl.getColumn("Ano"));
                    tbl.getColumnModel().removeColumn(tbl.getColumn("Mês"));
                    tbl.getColumnModel().removeColumn(tbl.getColumn("Atualizado?"));
                    tbl.getColumnModel().removeColumn(tbl.getColumn("Carregado?"));
                    tbl.getModel().addTableModelListener((TableModelListener) this);
                    JScrollPane sclp = new JScrollPane(tbl);

                    showYearWindow();

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
            } else {
                ((DefaultTableModel) t.getModel()).addRow((Object[]) null);
            }


            this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), scp);


        }

        if (actionEvent.getSource() == this.mainView.getClearRowButton()) {
            JScrollPane scp = (JScrollPane)(this.mainView.getTabbedPane().getComponentAt(this.mainView.getTabbedPane().getSelectedIndex()));
            JViewport v = scp.getViewport();
            JTable t = (JTable) v.getView();
            if (t.getSelectedRow() != -1) {
                ((DefaultTableModel) t.getModel()).removeRow(t.getSelectedRow());
            }
            this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), scp);
        }

        if (actionEvent.getSource() == this.mainView.getSaveButton()) {
            //IMPLEMENTAR AQUI A LÓGICA PARA SALVAR
            DB_Row dbrow = new DB_Row();
            ArrayList<Row> rows;

            Row row = new Row("Another Dummy", 322, (float)23.3, (float)50.1, (float)20.2, (float)10.53, false, (float)10.3, 2017, 11, false, false);

            dbrow.insertRow(row);

            rows = dbrow.selectDate(2018, 11);

            for (int i = 0; i < rows.size(); i++){
                System.out.println(rows.get(i).toString());
            }


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
                    System.out.println("pegou np pie");
                    break;

            }

        }

        if(actionEvent.getSource() == this.mainView.getNovaAbaButton()){
            this.tabNumber++;
            JTable t = new JTable(new DefaultTableModel(null, new Object[]{"Nome", "Código", "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual", "Porcentagem?", "Mudança de valor", "Ano", "Mês", "Atualizado?", "Carregado?"}))
            {
                @Override
                public Class getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return String.class;
                        case 1:
                            return Integer.class;
                        case 2:
                            return Float.class;
                        case 3:
                            return Float.class;
                        case 4:
                            return Float.class;
                        case 5:
                            return Float.class;
                        case 6:
                            return Boolean.class;
                        case 7:
                            return Float.class;
                        case 8:
                            return Integer.class;
                        case 9:
                            return Integer.class;
                        case 10:
                            return Boolean.class;
                        default:
                            return Boolean.class;
                    }
                }
            };
            t.setGridColor(Color.black);
            t.getModel().addTableModelListener((TableModelListener) this);
            t.getColumnModel().removeColumn(t.getColumn("Ano"));
            t.getColumnModel().removeColumn(t.getColumn("Mês"));
            t.getColumnModel().removeColumn(t.getColumn("Atualizado?"));
            t.getColumnModel().removeColumn(t.getColumn("Carregado?"));

            showYearWindow();

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

        if (actionEvent.getSource() == this.mainView.getRemoverAbaButton()){
            if (this.mainView.getTabbedPane().getSelectedIndex() != 0){
                this.mainView.getTabbedPane().removeTabAt(this.mainView.getTabbedPane().getSelectedIndex());
            }
        }

        if (actionEvent.getSource() == this.mainView.getCarregaDadosButton()){
            ArrayList<Row> rows;
            DB_Row dbrow = new DB_Row();

            rows = dbrow.selectAll();

            DefaultTableModel tm = new DefaultTableModel(null, new Object[]{"Nome", "Código", "Balanço Anterior", "Débito Anterior", "Crédito Anterior", "Balanço Atual", "Porcentagem?", "Mudança de valor", "Ano", "Mês", "Atualizado?", "Carregado?"});
                //Constrói um modelo de tabela vazia com os correspondentes nomes das colunas

            tm.getColumnClass(6).getClass();
            try {
                for (int i = 0; i < rows.size(); i++) {
                    Row row = rows.get(i);
                    tm.addRow(new Object[]{row.getDescription(), row.getCode(), row.getPreviousBalance(), row.getPreviousDebt(), row.getPreviousCredit(), row.getCurrentBalance(), row.getIsPercent(), row.getValueChange(), row.getYear(), row.getMonth(), row.getUpdated(), row.getLoaded()});
                }

                JTable tbl = new JTable(tm){
                    @Override
                    public Class getColumnClass(int column) {
                        switch (column) {
                            case 0:
                            return String.class;
                            case 1:
                            return Integer.class;
                            case 2:
                            return Float.class;
                            case 3:
                            return Float.class;
                            case 4:
                            return Float.class;
                            case 5:
                            return Float.class;
                            case 6:
                            return Boolean.class;
                            case 7:
                            return Float.class;
                            case 8:
                            return Integer.class;
                            case 9:
                            return Integer.class;
                            case 10:
                            return Boolean.class;
                            default:
                            return Boolean.class;
                        }
                    }
                };
                tbl.setGridColor(Color.black);
                    //TableColumnModel tcm = tbl.getColumnModel();
                tbl.getColumnModel().removeColumn(tbl.getColumn("Ano"));
                tbl.getColumnModel().removeColumn(tbl.getColumn("Mês"));
                tbl.getColumnModel().removeColumn(tbl.getColumn("Atualizado?"));
                tbl.getColumnModel().removeColumn(tbl.getColumn("Carregado?"));
                tbl.getModel().addTableModelListener((TableModelListener) this);
                JScrollPane sclp = new JScrollPane(tbl);


                this.mainView.getTabbedPane().setComponentAt(this.mainView.getTabbedPane().getSelectedIndex(), sclp);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

}
