package chart;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;

import static org.jfree.chart.ChartFactory.createPieChart;

public class Chart {
    private JPanel chartPanel;

    static DefaultPieDataset data = new DefaultPieDataset();


    public static JFrame crateChart(DefaultPieDataset data) {
//        data.setValue("mercado", 160);
//        data.setValue("farmacia", 160);
//        data.setValue("escola", 160);
//        data.setValue("livraria", 160);
//        data.setValue("combustivel", 160);

        JFreeChart chart = createPieChart("", data, true, false, false);
        return new ChartFrame("Gráfico", chart);
    }
}
