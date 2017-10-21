package main.controller;

import com.opencsv.CSVReader;
import main.model.Row;
import main.view.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MainController implements ActionListener{


    final JFileChooser fc = new JFileChooser();

    private Main mainView;

    public MainController(Main mainView) {
        this.mainView = mainView;
        this.mainView.getImportButton().addActionListener(this);

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
                try {
                    while ((nextLine = reader.readNext()) != null) {
                        if (nextLine != null) {
                            String[] values = Arrays.toString(nextLine).split(";");
                            if (values.length == 7) {
                                Row row = new Row(values);
                                System.out.println(row);
                            }
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.println("Open command cancelled by user.");
            }
        }
    }
}
