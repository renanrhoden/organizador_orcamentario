package main.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

public class Row {
    private String description;
    private int code;
    private float previousBalance;
    private float previousDebt;
    private float previousCradit;
    private float currentBalance;

    public Row(String description, int code, float previousBalance, float previousDebt, float previousCradit, float currentBalance) {
        this.description = description;
        this.code = code;
        this.previousBalance = previousBalance;
        this.previousDebt = previousDebt;
        this.previousCradit = previousCradit;
        this.currentBalance = currentBalance;
    }

    public Row(String[] values) {
        this.description = values[0];
        this.code = Integer.parseInt(StringUtils.remove(values[1], ' '));
        this.previousBalance = Float.parseFloat(StringUtils.remove(values[2], ' ').replace(".", "").replace(',', '.'));
        this.previousDebt = Float.parseFloat(StringUtils.remove(values[3], ' ').replace(".", "").replace(',', '.'));
        this.previousCradit = Float.parseFloat(StringUtils.remove(values[4], ' ').replace(".", "").replace(',', '.'));
        this.currentBalance = Float.parseFloat(StringUtils.remove(values[5], ' ').replace(".", "").replace(',', '.'));
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(float previousBalance) {
        this.previousBalance = previousBalance;
    }

    public float getPreviousDebt() {
        return previousDebt;
    }

    public void setPreviousDebt(float previousDebt) {
        this.previousDebt = previousDebt;
    }

    public float getPreviousCradit() {
        return previousCradit;
    }

    public void setPreviousCradit(float previousCradit) {
        this.previousCradit = previousCradit;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        String space = ";  ";
        return description + space
                + code + space
                + previousBalance + space
                + previousDebt + space
                + previousCradit + space
                + currentBalance;
    }
}
