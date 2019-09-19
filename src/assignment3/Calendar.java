package assignment3;

import java.util.InputMismatchException;

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
 *  Partners: Fatemeh Nouri and Danielle Schwartz
 *  
 *  Compilation:  javac Calendar.java
 *  Execution:    java Calendar choice
 *
 *  This program reads the user choice from the menu printed and asks for more
 *  inputs depending on the option chosen by the user.
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
	// months[i] = name of month i
	final static String[] months = {
	      "",                               // leave empty so that months[1] = "January"
	      "January", "February", "March",
	      "April",   "May",      "June",
	      "July",    "August",   "September",
	      "October", "November", "December"
	};

   // compute weekday based on the day of the month
   // Sunday is 0, Monday is 1, ... Saturday is 6
   private static int weekDay(int month, int day, int year) {
      int y = year - (14 - month) / 12;
      int x = y + y/4 - y/100 + y/400;
      int m = month + 12 * ((14 - month) / 12) - 2;
      int wd = (day + x + (31*m)/12) % 7;
      return wd;
   }

	/*
	Here is the logic for the following algorithm:
	if the year is divisible by 400 => leap year
	if year is evenly divisible by 4 and not 100 => leap year
	if the year is divisible by 4 and 100 and 400 => leap year
	if the year is divisible by 100 and not 400 => not a leap year
	*/
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

   /**
    * Prints a single month based on the Gregorian calendar.
    * 
    * @param month an integer representing the month
    * @param year an integer representing the year
    */
   private static void printMonth(int month, int year) {
	     
	      // days[i] = number of days in month i (skip month 0)
	      int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	      // check for leap year
	      if (month == 2 && isLeapYear(year)) {
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
	      System.out.println("*****************************");
	   }
   
   /**
    * Prompts the user for input of a month and year as month:year and returns an
    * array of those integer values.
    * 
    * @param sc	Scanner to receive input from command line
    * @param arg	An optional String argument to add to the prompt
    * @return			An integer array consisting of the month and year
    */
   private static int[] promptMonthYear(Scanner sc, String arg) {
	   boolean valid = false;
	   int month = 0;
	   int year = 0;
	   String[] input;
	   int[] rv = new int[2];		// return value array of ints; rv[0] = month, rv[1] = year
	   
	   do {
		   System.out.print("Enter a" + arg + " month and year in the format \"month:year\":"); /* CLI prompt*/
		  input = sc.next().split(":");

		   try {
			   month = Integer.parseInt(input[0]);
			   year = Integer.parseInt(input[1]);
			   
			   if ((month >=1) && (month <= 12) && (year >= 0)) {	// valid date provided
				   valid = true;
				   System.out.println("You chose " + months[month] + " " + year + "\n");
			   } else {	// incorrect selection
				   System.out.println("\nMonth must be an integer between 1 and 12 and "
						   + "year must be a nonnegative integer.\n");
			   }
		   } catch (Exception e) {
			   System.out.println("\nMonth must be an integer between 1 and 12 and "
					   + "year must be a nonnegative integer.\n");
		   }	
      } while (!valid);

	   rv[0] = month;
	   rv[1] = year;
	   return rv; /* CLI */
   }
   
   /**
    * Prompts the user for input of a year as and returns that value.
    * @param sc	Scanner to receive input from command line
    * @return			an integer representing the chosen year
    */
   private static int promptYear(Scanner sc) {
	   boolean valid = false;
	   int year = 0;
	   
	   do {
		   System.out.println("Enter a year you would like to see as an integer. Example: 2018");
		   System.out.print("Enter year now: ");

		   try {
			   year = sc.nextInt();
			   if (year >= 0) {	// year successfully provided
				   valid = true;
				   System.out.println("You chose the year " + year + "\n");
			   } else {	// incorrect selection
				   System.out.println("\nSelection must be a nonnegative integer.\n");
			   }
		   } catch (InputMismatchException e) {
			   System.out.println("\nSelection must be a nonnegative integer.\n");
			   sc.next();
		   }	    	  
	   } while(!valid);
	   
	   return year;
   }

   
   /** Prompt user for program option. If invalid option selected, prompts again.
    * 
    * Option 1: Given a month:year pair, print a single month
    * Option 2: Given a specified year, print an entire calendar year
    * Option 3: Given two pairs of month:year, print a calendar from the first
    * 	month to the last
	* Option 4: Exit the program.
    * 
    * @return the chosen option as an integer
    */
   private static int promptSelection(Scanner sc) {
	   boolean valid = false;
	   int option = 0;
	   
	   do {
		   System.out.println("Please enter a number to select an option.\n"
			+ "Option 1: Print a specific month of a single calendar year.\n"
		   + "Option 2: Print a single calendar year.\n"
		   + "Option 3: Print a calendar from a starting month to an ending month.\n"
		   + "Option 4: Exit the program.");
		   System.out.print("Select 1, 2, 3 or 4: ");
		   
		   try {
			   option = sc.nextInt();
			   if ((option > 0) && (option < 5)) {	// chose 1, 2, or 3; selection successfully made
				   valid = true;
				   System.out.println("You chose option " + option + "\n");
			   } else {	// incorrect selection
				   System.out.println("\nSelection must be an integer, 1, 2, 3 or 4.\n");
			   }
		   } catch (InputMismatchException e) {
			   System.out.println("\nSelection must be an integer, 1, 2, 3 or 4.\n");
			   sc.next();
		   }	    	  
	   } while(!valid);
	   
	   return option;
   }

   /**
    * Main method
    * 
    * @param args
    */
   public static void main(String[] args) {
	  int year = 0;
	  boolean valid = false;
	  int[] startVals, endVals;
	  
      Scanner sc = new Scanner(System.in); /* CLI */


      
      // Determine which task to perform
      int option = promptSelection(sc);

      // Perform chosen task
      switch(option) {
      case 1:	// Option 1: print a single month
    	  // prompt for a month and year
    	  startVals = promptMonthYear(sc, "");
    	  
    	  // print month
    	  printMonth(startVals[0], startVals[1]);
    	  break;
      case 2:	// Option 2: print an entire year
    	  // prompt for a single year
    	  year = promptYear(sc);
    	  
    	  // print entire year, month by month
    	  for (int month = 1; month <= 12; month++) {
    		  printMonth(month, year);
    	  }
    	  break;
      case 3:	// Option 3: print a calendar from start month to end month
    	  do {
        	  // prompt for 2 pairs
        	  startVals = promptMonthYear(sc, " starting");
        	  endVals = promptMonthYear(sc, "n ending");
        	  
        	  // ensure start date is before end date
	    	  if (startVals[1] >= endVals[1]) {
	    		  if (startVals[0] > endVals[0]) {
	    			  ///// failed, reprompt
	    			  System.out.println("Start date must be before end date. Try again.\n");
	    			  continue;
	    		  }
	    	  }
	    	  valid = true;
    	  } while (!valid);
    	  
    	  // print calendar
    	  if (startVals[1] == endVals[1]) {	// if only 1 calendar year requested
	    	  for (int month = startVals[0]; month <= endVals[0]; month ++) {
	    		  printMonth(month, startVals[1]);
	    	  }
    	  } else {	// else print all years requested
    		  // first year...
    		  for (int month = startVals[0]; month <= 12; month++) {
    			  printMonth(month, startVals[1]);
    		  }
    		  
        	  // middle years, if any...
        	  if (endVals[1] > (startVals[1] + 1)) {
    	    	  for (int yr = startVals[1] + 1; yr < endVals[1]; yr++) {	// for each year...
    	    		  for (int month = 1; month <= 12; month++) {		// for each month
    	    			  printMonth(month, yr);
    	    		  }
    	    	  }
        	  }
        	  // final year...
        	  for (int month = 1; month <= endVals[0]; month++) {
        		  printMonth(month, endVals[1]);
        	  }
    	  }
    	  break;
      case 4:
		  	System.out.println("Exiting program...");
		  	System.exit(0);
      default:
    		  System.out.println("An error occurred. Exiting program...");
    		  System.exit(1);
      }
     
      sc.close();
      
   } // end main
}  // end class

