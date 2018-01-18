package main.model;

public class DataRange{
	private int firstYear;
	private int firstMonth;
	private int lastYear;
	private int lastMonth;

	public DataRange(){
		this.setFirstYear(0);
		this.setFirstMonth(1);
		this.setLastYear(0);
		this.setLastMonth(1);
	}

	public DataRange(int firstYear, int firstMonth, int lastYear, int lastMonth){
		this.setFirstYear(firstYear);
		this.setFirstMonth(firstMonth);
		this.setLastYear(lastYear);
		this.setLastMonth(lastMonth);
	}

	private static boolean validateInterval(int firstYear, int firstMonth, int lastYear, int lastMonth){
		return ((firstYear < lastYear) || (firstYear == lastYear && firstMonth <= lastMonth));
	}

	public void setFirstYear(int year){
		if (year >= 0 && DataRange.validateInterval(year, this.getFirstMonth(), this.getLastYear(), this.getLastMonth())){
			this.firstYear = year;
		}
	}

	public void setFirstMonth(int month){
		if ((month >= 1 && month <= 12) && DataRange.validateInterval(this.getFirstYear(), month, this.getLastYear(), this.getLastMonth())){
			this.firstMonth = month;
		}
	}

	public void setLastYear(int year){
		if (year >= 0 && DataRange.validateInterval(this.getFirstYear(), this.getFirstMonth(), year, this.getLastMonth())){
			this.lastYear = year;
		}
	}

	public void setLastMonth(int month){
		if ((month >= 1 && month <= 12) && DataRange.validateInterval(this.getFirstYear(), this.getFirstMonth(), this.getLastYear(), month)){
			this.lastMonth = month;
		}
	}

	public int getFirstYear(){
		return this.firstYear;
	}

	public int getFirstMonth(){
		return this.firstMonth;
	}

	public int getLastYear(){
		return this.lastYear;
	}

	public int getLastMonth(){
		return this.lastMonth;
	}

}