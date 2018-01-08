import chart.Chart;
import chart.PieChart;
import main.controller.MainController;
import main.view.Main;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        Main frame = new Main();
        new MainController(frame);
        frame.setVisible(true);
    }
}
