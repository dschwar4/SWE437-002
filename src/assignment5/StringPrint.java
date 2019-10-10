package assignment5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StringPrint {
	
	private long lineCount;
	private int randomVal;
	private String input;
	private String filePath;
	
	public StringPrint() {
		filePath = null;
		input = null;
		randomVal = -1;
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
		Path path = null;
		Stream<String> stream = null;
		boolean valid = false;
		
		// CLI - get file path
		do {
			System.out.println("Enter the file path: ");
			try {
				stringPrint.setFilePath(scanner.nextLine());
				// get stream from file
				path = Paths.get(stringPrint.getFilePath());
				stream = Files.lines(path);
				valid = true;
			} catch (NoSuchFileException e) {
				System.out.println("Please enter a valid filepath.");
			}
		} while (!valid);
		
		// reset valid toggle
		valid = false;
		
		// CLI - get random value
		do {
			System.out.println("Enter a value: ");
			try {
				stringPrint.setValue(scanner.nextInt());
				   if (stringPrint.getValue() >= 0) {	// valid number provided
					   valid = true;
				   } else {
					   System.out.println("Value must be a nonnegative integer.");		
					   valid = false;
				   }
			} catch (InputMismatchException e) {
				   System.out.println("Value must be a nonnegative integer.");
				   scanner.next();				
			}
		} while (!valid);
		
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

