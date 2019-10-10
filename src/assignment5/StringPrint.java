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
	
	public void setValue(int randomVal) {
		this.randomVal = randomVal;
	}
	
	public String getString() {
		return input;
	}
	
	public void printOutput() {
		System.out.print("You chose: " + input);
	}
	
	public static void main(String[] args) throws IOException{
		StringPrint stringPrint = new StringPrint();
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		System.out.println("Enter the file path: ");
		stringPrint.filePath = scanner.nextLine();

		try (Stream<String> stream = Files.lines(Paths.get(stringPrint.filePath), 
				StandardCharsets.UTF_8)) {
				stream.forEach(s -> sb.append(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stringPrint.input = sb.toString();

		System.out.println("Enter a value: ");
		stringPrint.setValue(scanner.nextInt());
		
		stringPrint.printOutput();
		
		scanner.close();
	}

}

