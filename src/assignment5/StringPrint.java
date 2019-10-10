package assignment5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Scanner;

public class StringPrint {
	
	private long lineCount;
	private int randomVal;
	private String input;
	private String filePath;
	
	public StringPrint() {
		filePath = null;
		input = null;
		randomVal = 0;
	}
	
	public StringPrint(String input) {
		this.input = input;
		randomVal = 0;
	}
	
	public int getValue() {
		return this.randomVal;
	}
	
	public void setValue(int randomVal) {
		this.randomVal = randomVal;
	}
	
	public String getString() {
		return this.input;
	}
	
	public void setString(String input) {
		this.input = input;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public long getLineCount() {
		return this.lineCount;
	}
	
	public void setLineCount(long lineCount) {
		this.lineCount = lineCount;
	}
	
	public void printOutput() {
		System.out.print("You chose: " + input);
	}
	
	public static void main(String[] args) throws IOException{
		StringPrint stringPrint = new StringPrint();
		Scanner scanner = new Scanner(System.in);
		Path path;
		Stream<String> stream;
		
		// CLI
		System.out.println("Enter the file path: ");
		stringPrint.setFilePath(scanner.nextLine());
		System.out.println("Enter a value: ");
		stringPrint.setValue(scanner.nextInt());
		
		// get stream from file
		path = Paths.get(stringPrint.getFilePath());
		stream = Files.lines(path);
		
		// get file line count
		stringPrint.setLineCount(stream.count());
		
		// calculate random number within range
		stringPrint.setValue((int) (stringPrint.getValue() % stringPrint.getLineCount()));

		// fetch appropriate line
		stream = Files.lines(path);
		stringPrint.setString(stream.skip(stringPrint.getValue()).findFirst().get());

		// print the line
		stringPrint.printOutput();
		
		// close resources
		stream.close();
		scanner.close();
	}

}

