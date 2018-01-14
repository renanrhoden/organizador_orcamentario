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
    private boolean isPercent;
    private float valueChange;
    private int year;
    private int month;

    public Row(String description, int code, float previousBalance, float previousDebt, float previousCradit, float currentBalance, boolean isPercent, float valueChange, int year, int month) {
        this.description = description;
        this.code = code;
        this.previousBalance = previousBalance;
        this.previousDebt = previousDebt;
        this.previousCradit = previousCradit;
        this.currentBalance = currentBalance;
        this.isPercent = isPercent;
        this.valueChange = valueChange;
        this.year = year;
        this.month = month;
    }

    public Row(String[] values) {
        this.description = values[0];
        this.code = Integer.parseInt(StringUtils.remove(values[1], ' '));
        this.previousBalance = Float.parseFloat(StringUtils.remove(values[2], ' ').replace(".", "").replace(',', '.'));
        this.previousDebt = Float.parseFloat(StringUtils.remove(values[3], ' ').replace(".", "").replace(',', '.'));
        this.previousCradit = Float.parseFloat(StringUtils.remove(values[4], ' ').replace(".", "").replace(',', '.'));
        this.currentBalance = Float.parseFloat(StringUtils.remove(values[5], ' ').replace(".", "").replace(',', '.'));
        this.isPercent = NULL;
        this.valueChange = NULL;
        this.year = NULL;
        this.month = NULL;
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

    public boolean getIsPercent(){
        return isPercent;
    }

    public void setIsPercent(boolean isPercent){
        this.isPercent = isPercent;
    }

    public float getValueChange(){
        return valueChange;
    }
    
    public void setValueChange(float valueChange){
        this.valueChange = valueChange;
    }

    public int getYear(){
        return year;
    }
    
    public void setYear(int year){
        this.year = year;
    }

    public int getMonth(){
        return month;
    }
    
    public void setMonth(int month){
        this.month = month;
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
