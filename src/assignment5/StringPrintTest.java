package assignment5;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class StringPrintTest {
    private final InputStream inputStream = System.in;
	private StringPrint stringPrint;
	private String expectedResult, promptPath, promptValue, promptChoice;
	private String promptValueError, promptFileError;
	private String inputString;
    private ByteArrayInputStream in;
    private ByteArrayOutputStream out;
    private PrintStream outputStream = System.out;
    
    public static void main(String args[]){
        org.junit.runner.JUnitCore.main("StringPrintTest");
    }
    
    @Before
    public void setUpOutput() {
    	promptPath = "Enter the file path: \n";
    	promptValue = "Enter a value: \n";
    	promptChoice = "You chose: ";
    	promptValueError = "Value must be a nonnegative integer.\n";
    	promptFileError = "Please enter a valid filepath.\n";
    	expectedResult = null;
    	inputString = null;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }
    
    @After
    public void restoreSystemInputOutput() {
        System.setIn(inputStream);
        System.setOut(outputStream);
    }
	
//	@Test
//	public void oneCharacter() throws Exception {
//		// changed argument to String and relocated declaration 
//		stringPrint = new StringPrint("a");
//		stringPrint.setValue(7);
//		// changed assert to match a String
//		// updated output method call to getString
//		assertEquals("a", stringPrint.getString());
//	}
	
	@Test
	public void oneString() throws Exception {
		// relocated declaration
		stringPrint = new StringPrint("Hello, World");
		stringPrint.setValue(7);
		// updated output method call to getString
		// now set expectedResult and pass to assert
		expectedResult = "Hello, World";
		assertEquals(expectedResult, stringPrint.getString());
	}
	
//    @Test
//    public void oneStringFromCLI() throws IOException {
//        final String inputString = "Hello, World\n7";
//        in = new ByteArrayInputStream(inputString.getBytes());
//        System.setIn(in);
//        String expectedResult = "Enter a string: \nEnter a value: \nYou chose: Hello, World"
//        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
//        StringPrint.main(new String[0]);
//        assertEquals(expectedResult, out.toString());
//    }
    
     @Test
    public void oneStringFromFile() throws IOException {
    	inputString = "src\\singleLine.txt\n0";
        in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        expectedResult = promptPath.concat(promptValue).concat(promptChoice).concat("Cheez-its")
        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        StringPrint.main(new String[0]);
        assertEquals(expectedResult, out.toString());
    }
     
     @Test
    public void manyStringsFromFile() throws IOException {
    	inputString = "src\\multipleLines.txt\n1";
        in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        expectedResult = promptPath.concat(promptValue)
        		.concat(promptChoice).concat("Banana")
        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        StringPrint.main(new String[0]);
        assertEquals(expectedResult, out.toString());
    }  
     
     @Test
    public void outOfBoundsValue() throws IOException {
    	inputString = "src\\multipleLines.txt\n6";
        in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        expectedResult = promptPath.concat(promptValue).concat(promptChoice)
        		.concat("Apples")
        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        StringPrint.main(new String[0]);
        assertEquals(expectedResult, out.toString());
    } 
     
     @Test
    public void negativeValue() throws IOException {
    	inputString = "src\\multipleLines.txt\n-3\n2";
        in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        expectedResult = promptPath.concat(promptValue).concat(promptValueError)
        		.concat(promptValue).concat(promptChoice).concat("Cheez-its")
        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        StringPrint.main(new String[0]);
        assertEquals(expectedResult, out.toString());
    } 
     
     @Test
    public void nonIntValue() throws IOException {
    	inputString = "src\\multipleLines.txt\nseven\n2";
        in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        expectedResult = promptPath.concat(promptValue).concat(promptValueError)
        		.concat(promptValue).concat(promptChoice).concat("Cheez-its")
        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        StringPrint.main(new String[0]);
        assertEquals(expectedResult, out.toString());
    } 
     
     @Test
    public void invalidFIle() throws IOException {
    	inputString = "gobbledegook\nsrc\\multipleLines.txt\nseven\n2";
        in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        expectedResult = promptPath.concat(promptFileError).concat(promptPath)
        		.concat(promptValue).concat(promptValueError)
        		.concat(promptValue).concat(promptChoice).concat("Cheez-its")
        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        StringPrint.main(new String[0]);
        assertEquals(expectedResult, out.toString());
    } 
}
