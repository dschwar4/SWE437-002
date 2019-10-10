package assignment5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Scanner;

public class StringPrint {
	
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
	
	public void printOutput() {
		System.out.print("You chose: " + input);
	}
	
	public static void main(String[] args) throws IOException{
		StringPrint stringPrint = new StringPrint();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the file path: ");
		stringPrint.setFilePath(scanner.nextLine());
		
		System.out.println("Enter a value: ");
		stringPrint.setValue(scanner.nextInt());

		try (Stream<String> stream = Files.lines(Paths.get(stringPrint.getFilePath()), 
			StandardCharsets.UTF_8)) {
			stringPrint.setString(stream.skip(stringPrint.getValue()).findFirst().get());
		} catch (IOException e) {
			e.printStackTrace();
		}
		stringPrint.printOutput();
		
		scanner.close();
	}

}

