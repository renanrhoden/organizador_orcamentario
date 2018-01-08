package chart;

import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;

public class PieChart {
    private DefaultPieDataset data = new DefaultPieDataset();

    public void add(String name, float value){
        data.setValue(name, value);
    }

    public void show(){
        JFrame frame = Chart.crateChart(this.data);
        frame.setVisible(true);
        frame.pack();
    }

}
