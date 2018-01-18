package main.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

public class Row {
    private String description;
    private int code;
    private float previousBalance;
    private float previousDebt;
    private float previousCredit;
    private float currentBalance;
    private boolean isPercent;
    private float valueChange;
    private int year;
    private int month;
    private boolean updated;
    private boolean loaded;

    public Row(String description, int code, float previousBalance, float previousDebt, float previousCredit, float currentBalance, boolean isPercent, float valueChange, int year, int month, boolean updated, boolean loaded) {
        this.setDescription(description);
        this.setCode(code);
        this.setPreviousBalance(previousBalance);
        this.setPreviousDebt(previousDebt);
        this.setPreviousCredit(previousCredit);
        this.setCurrentBalance(currentBalance);
        this.setIsPercent(isPercent);
        this.setValueChange(valueChange);
        this.setYear(year);
        this.setMonth(month);
        this.setUpdated(updated);
        this.setLoaded(loaded);
    }

    public Row(String[] values) {
        this.setDescription(values[0]);
        this.setCode( Integer.parseInt(StringUtils.remove(values[1], ' ')) );
        this.setPreviousBalance( Float.parseFloat(StringUtils.remove(values[2], ' ').replace(".", "").replace(',', '.')) );
        this.setPreviousDebt( Float.parseFloat(StringUtils.remove(values[3], ' ').replace(".", "").replace(',', '.')) );
        this.setPreviousCredit( Float.parseFloat(StringUtils.remove(values[4], ' ').replace(".", "").replace(',', '.')) );
        this.setCurrentBalance( Float.parseFloat(StringUtils.remove(values[5], ' ').replace(".", "").replace(',', '.')) );
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

    public float getPreviousCredit() {
        return previousCredit;
    }

    public void setPreviousCredit(float previousCredit) {
        this.previousCredit = previousCredit;
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

    public boolean getUpdated(){ 
        return updated;
    }

    public void setUpdated(boolean updated){
        this.updated = updated;
    }

    public boolean getLoaded(){
        return loaded;
    }

    public void setLoaded(boolean loaded){
        this.loaded = loaded;
    }


    @Override
    public String toString() {
        String space = ";  ";
        return description + space
                + code + space
                + previousBalance + space
                + previousDebt + space
                + previousCredit + space
                + currentBalance;
    }
}
