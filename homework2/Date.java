/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
  private int month;
  private int day;
  private int year;
  //private int allDays;
  private int days;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
	  if(!isValidDate(month, day, year)) {
		System.exit(0);
	  }else{
		this.month =month;
		this.day =day;
		this.year =year;
	  }
  }
  //additional method : This method calculated all the days from the given date to 1/1/1
  private int getAllDays(){
	int days =0;
	int leapYearCnt =0;
	for(int i=1; i<year; i++){//this year dont need to check leap or not 
		if(isLeapYear(i)){
			leapYearCnt ++;//leap year is greater than normal year
		}
	}
	days = (year-1)* 365 + leapYearCnt+ dayInYear();
	/*
	dayInYear() means days in this years 
	*/
	return days;
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
	  String[] str= s.split("/"); 
	  month = Integer.parseInt(str[0]);
	  day = Integer.parseInt(str[1]);
	  year = Integer.parseInt(str[2]);
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
	  if(year<0) return false;
	  if(((year%4==0)&& (year%100!=0)) || year%400 ==0 ){
		  return true;
	  }
    return false;                        // *done: replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
	  switch(month){
		  case 2:
			if(isLeapYear(year))
			{
				return 29;	
			}else{
				return 28;
			}
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				return 31;
	  }                           // *done, replace this line with your solution
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
	  
	  if(month<0 || month>12) return false;
	  if(year==0) return false;
	  if(day<1 || day >daysInMonth(month,year)) return false;
	  if(month==2 && day==29 && !isLeapYear(year)) return false;
	  return true;
	  /*if(month >=1 && month<=12){
		switch(month){
			case 2:
			if(isLeapYear(year)==true){
				return day ==29;
			}else{
				return day==28;
			}
			case 4:
			case 6:
			case 9:
			case 11:
				return day ==30;
			default:
				return day ==31;			
		}
	  }
	  */                     
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
	  return month +"/"+day+"/"+year;                    // replace this line with your solution
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
	  /*if(this.year < d.year) return true;
	  if(this.year >d.year) return false;
	  if(this.month< d.month) return true;
	  if(this.month>d.month) return false;
	  return (this.day < d.day); 
	  */
       return (this.getAllDays() - d.getAllDays())<0;
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
	  /*
    if(this.year > d.year) return true;
	  if(this.year <d.year) return false;
	  if(this.month> d.month) return true;
	  if(this.month<d.month) return false;
	  return (this.day > d.day); */                        // replace this line with your solution

	  return (this.getAllDays() - d.getAllDays())>0;
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
	  int res = 0;
	  //if(month ==1) return day;
	  for(int i=1; i< month; i++){
		res = res+daysInMonth(i,year);
	  }
	  res = res+ day;
    return res;                           // replace this line with your solution
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
   
  public int difference(Date d) {
	  return this.getAllDays()-d.getAllDays();

  }                        // replace this line with your solution
 

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */
	System.out.println("\nTesting leapYear");
	System.out.println(d2.year+" is a leapYear should be true: "+isLeapYear(d2.year));
	System.out.println(d1.year+" is a leapYear should be false: "+isLeapYear(d1.year));
	

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
