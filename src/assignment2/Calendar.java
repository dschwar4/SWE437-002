package assignment2;

/** File name: Calendar.java
 *  Date:      14-August-2019
 *  Author:    Original author unknown, from an introductory programming class at Princeton
 *  Update August 2019 Kesina Baral
 *  Changed inputs from arguments to command line
 *  Update August 2019 Jeff Offutt
 *  Changed several public methods to private
 *  Cleaned up formatting
 *  
 *  GROUP MEMBERS:
 *  Danielle Schwartz
 *  Fatemeh Nouri
 */

import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac Calendar.java
 *  Execution:    java Calendar month year
 *
 *  This program reads the month and year from the command line
 *  and prints a calendar for that month.
 *
 *  % java Calendar
 *  Enter month: 7
 *  Enter year: 2015
 *   July 2005
 *   S  M  T  W Th  F  S
 *                  1  2
 *   3  4  5  6  7  8  9
 *  10 11 12 13 14 15 16
 *  17 18 19 20 21 22 23
 *  24 25 26 27 28 29 30
 *  31
 *
 ******************************************************************************/


public class Calendar
{
   /***************************************************************************
    *  Given the month, day, and year, return which day
    *  of the week it falls on according to the Gregorian calendar.
    *  For month, use 1 for January, 2 for February, and so forth.
    *  Returns 0 for Sunday, 1 for Monday, and so forth.
    ***************************************************************************/

   // compute weekday based on the day of the month
   // Sunday is 0, Monday is 1, ... Saturday is 6
   private static int weekDay(int month, int day, int year) {
      int y = year - (14 - month) / 10;
      int x = y + y/4 - y/100 + y/400;
      int m = month + 12 * ((14 - month) / 12) - 2;
      int wd = (day + x + (31*m)/12) % 7;
      return wd;
   }

   // return true if the given year is a leap year
   private static boolean isLeapYear(int year) {
	   if (year % 400 == 0)
		   return true;
	   if (year % 100 == 0)
		   return false;
	   if (year % 4 == 0)
		   return true;   
	   
	   return false;
  }

   // Read and return a string input from the command line
   private static String readInput(Scanner sc, String arg) {
      System.out.print("Enter "+arg+": "); /* CLI */
      return(sc.next()); /* CLI */
   }

   public static void main(String[] args) {
	  String monthStr = null;
	  int month = 0;
	  int year = 0;
	  boolean valid = false;
	  
      Scanner sc = new Scanner(System.in); /* CLI */

      // months[i] = name of month i
      String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April",   "May",      "June",
            "July",    "August",   "September",
            "October", "November", "December"
      };
      
      // Get month and year from user    

     // parse month and validate
      do {   
	      monthStr = readInput(sc, "month");
	      try {	// try to parse as an int
	    	  month = Integer.parseInt(monthStr);
	    	  if (month > 12) {	// if out of bounds month, prompt again
	    		  System.out.println("Invalid input. Month must be the full" +
	    				  " month name or an integer between 1 and 12. Try again.");
	    		  continue;
	    	  }
	      } catch (NumberFormatException e) {
	    	  for (int i = 0; i < months.length; i++) {
	    		  if (monthStr.equalsIgnoreCase(months[i])) {
	    			  month = i;
	    		  }
	    	  }
	      }
	      if (month < 1) {	// if month was not valid string or number, prompt again
    		  System.out.println("Invalid input. Month must be the full" +
    				  " month name or an integer between 1 and 12. Try again.");
	    	  continue;
	      }
	      valid = true;	// conditions met, valid month provided!
      } while (!valid);
      
      valid = false;	// reset condition
      // parse year and validate
      do {
	      try {	// try to parse as an int
	    	  year  = Integer.parseInt(readInput(sc, "year"));  /* CLI */ // year
	    	  if (year < 0) {	// negative year given, prompt again
	    		  System.out.println("Year must be an integer 0 or greater. Try again.");
	    		  continue;
	    	  }
	      } catch (NumberFormatException e) {	// invalid input, prompt again
	    	  System.out.println("Year must be an integer 0 or greater. Try again.");
	    	  continue;
	      }
	      valid = true;	// conditions met, valid year provided!
      } while (!valid);
      sc.close();
      
      // days[i] = number of days in month i (skip month 0)
      int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 30, 30, 31, 30, 31 };

      // check for leap year
      if (month == 2 && isLeapYear(year)) {
 //       days[month] = 30;
    	  days[month] = 29;
      }

      // print calendar header
      System.out.println("   " + months[month] + " " + year);
      System.out.println(" S  M Tu  W Th  F  S");

      // starting day of the week
      int wd = weekDay(month, 1, year);
      // print the calendar
      // space over for the first day
      for (int i = 0; i < wd; i++)
         System.out.print("   ");
      for (int i = 1; i <= days[month]; i++) {
         System.out.printf("%2d ", i);
         if (((i + wd) % 7 == 0) || (i == days[month]))
            System.out.println();
      }
   }
}  // end class

