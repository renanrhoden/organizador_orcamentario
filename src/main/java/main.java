

import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class main extends Component{
    private JPanel mainPanel;
    private JButton importButton;

    final JFileChooser fc = new JFileChooser();

    public main() {

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == importButton) {
                    int returnVal = fc.showOpenDialog(main.this);

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
                                    //Verifying the read data here
                                    System.out.println(Arrays.toString(nextLine));
                                }
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
            }
        }});
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("main");
        frame.setContentPane(new main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
