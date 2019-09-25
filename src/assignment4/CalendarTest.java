//Tests for the Calendar.java class
//Authors: Fatemeh Nouri, Danielle Schwartz

//import org.junit.*;
//import static org.junit.Assert.*;
//import java.util.*;
//import java.io.PrintStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;
import java.util.*;
public class CalendarTest
{
//    private PrintStream sysOut;
//    private InputStream sysIn;
//    private ByteArrayOutputStream calendarResult;
////    private ByteArrayInputStream calendarInput;
//    @BeforeClass
//    public void setUp(){
//            sysIn=System.in;
//            sysOut=System.out;
//    }
//    @Before
//    public void setUpStreams(){
////        calendarInput=new ByteArrayInputStream();
//        calendarResult=new ByteArrayOutputStream();
//        System.setOut(new PrintStream(calendarResult));
////        System.setIn(new InputStream(calendarInput));
//    }
//    @After
//    public void cleanUpStreams() {
//        System.setOut(null);
//        System.setIn(null);
//        System.setOut(sysOut);
//        System.setIn(sysIn);
//    }
//    //  @Test public void HelloLab_main() {
////    String expect = "Hello, Lab!\n";
////    HelloLab.main(empty);
////    String actual = localOut.toString();
////    String actualNewline = actual.replaceAll("\\r?\\n","\n"); // Eliminate windows linebreaks
////    String msg = String.format("Output mismatch\nEXPECTED:\n%s\nACTUAL:\n%s\n",expect,actual);
////    assertTrue(msg,expect.equals(actualNewline));
////  }
//    @Test public void happyPath()
//    {
//        String testMonth = "9";
//        String testYear = "2019";
//        String expect="   September 2019\n"+" S  M Tu  W Th  F  S\n"+" 1  2  3  4  5  6  7\n"+
//                " 8  9 10 11 12 13 14\n"+"15 16 17 18 19 20 21\n"+"22 23 24 25 26 27 28\n"+
//                "29 30\n";
//        ByteArrayInputStream calendarInput=new ByteArrayInputStream(testMonth.getBytes());
//        System.setIn(calendarInput);
//        calendarInput=new ByteArrayInputStream(testYear.getBytes());
//        System.setIn(calendarInput);
//        Calendar.main(new String[0]);
//        String got = calendarResult.toString();
//        String res = got.replaceAll("\\r?\\n","\n");
//        assertTrue("failed",expect.equals(res));
//
//    }
private final InputStream systemIn = System.in;
    public static void main(String args[]){
        org.junit.runner.JUnitCore.main("CalendarTest");
    }
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testCase1() {
        final String testString = "9\n2019";
        provideInput(testString);
//        final String testString2 = "2019\n";
//        provideInput(testString2);
        String x = "Enter month:\nEnter year:\n    September 2019\n"+" S  M Tu  W Th  F  S\n"+" 1  2  3  4  5  6  7 \n"+
                " 8  9 10 11 12 13 14 \n"+"15 16 17 18 19 20 21 \n"+"22 23 24 25 26 27 28 \n"+
                "29 30 \n";
        Calendar.main(new String[0]);

        assertEquals(x, getOutput());
    }
}





//import org.junit.*;
//import static org.junit.Assert.*;
//// import org.junit.BeforeClass;
//// import org.junit.Before;
//// import org.junit.After;
//// import org.junit.Test;
//// import org.junit.Assert;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.*;
//
//
//public class Tester1e {
//
//  /*Main method runs tests in this file*/
//  public static void main(String args[]) {
//    org.junit.runner.JUnitCore.main("Tester1e");
//  }
//
//  static ByteArrayOutputStream localOut, localErr;
//  static PrintStream sysOut, sysErr;
//  static String [] empty = {};
//
//  @BeforeClass
//  public static void setUp() throws Exception {
//    sysOut = System.out;
//    sysErr = System.err;
//  }
//
//  // Before every test is run, reset the streams to capture
//  // stdout/stderr
//  @Before
//  public void setUpStreams() {
//    localOut = new ByteArrayOutputStream();
//    localErr = new ByteArrayOutputStream();
//    System.setOut(new PrintStream(localOut));
//    System.setErr(new PrintStream(localErr));
//  }
//
//  // After every test, restore stdout/stderr
//  @After
//  public void cleanUpStreams() {
//    System.setOut(null);
//    System.setErr(null);
//    System.setOut(sysOut);
//    System.setErr(sysErr);
//  }
//
//  // Determine what the newline is on the running system
//  String newline = System.getProperty("line.separator");
//
//  // Test the HelloLab class. This is a bit more complicated than usual
//  // since we're sneaking a copy of the output from standard output!
//  @Test public void HelloLab_main() {
//    String expect = "Hello, Lab!\n";
//    HelloLab.main(empty);
//    String actual = localOut.toString();
//    String actualNewline = actual.replaceAll("\\r?\\n","\n"); // Eliminate windows linebreaks
//    String msg = String.format("Output mismatch\nEXPECTED:\n%s\nACTUAL:\n%s\n",expect,actual);
//    assertTrue(msg,expect.equals(actualNewline));
//  }
//

//}
