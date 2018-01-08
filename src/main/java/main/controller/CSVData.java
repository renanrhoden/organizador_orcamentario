package main.controller;

import main.model.Row;

import java.util.ArrayList;
import java.util.List;

public class CSVData {
    private static CSVData ourInstance = new CSVData();

    public static CSVData getInstance() {
        return ourInstance;
    }

    private CSVData() {
    }
    private List<Row> data = new ArrayList<>();

    public List<Row> getData() {
        return data;
    }

    public void setData(List<Row> data) {
        this.data = data;
    }
}
