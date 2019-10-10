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
	private String expectedResult;
    private ByteArrayInputStream in;
    private ByteArrayOutputStream out;
    private PrintStream outputStream = System.out;
    
    public static void main(String args[]){
        org.junit.runner.JUnitCore.main("StringPrintTest");
    }
    
    @Before
    public void setUpOutput() {
    	expectedResult = null;
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
    	final String inputString = "src\\singleLine.txt\n7";
        in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        expectedResult = "Enter the file path: \nEnter a value: \nYou chose: Cheez-its"
        		.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        StringPrint.main(new String[0]);
        assertEquals(expectedResult, out.toString());
    }
}
