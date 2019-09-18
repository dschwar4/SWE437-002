package assignment1;

import java.util.Scanner;

public class FindLast {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int[] x = populateArray(scan);
		System.out.print("Value to test: y = ");
		int y = scan.nextInt();
		System.out.println("Result: " +  findLast(x, y));
		
		scan.close();
	}
	
	public static int[] populateArray (Scanner scan) {
		System.out.print("Enter size of test array: ");
			int[] x = new int[scan.nextInt()];
		System.out.print("Enter test array: ");
		for (int i = 0; i < x.length; i++) {
			x[i] = scan.nextInt();
		}
		return x;
	}
	
	public static int findLast (int[] x, int y) {
		for (int i = x.length - 1; i >= 0; i--) {
			if (x[i] == y) {
				return i;
			}
		}
		return -1;
	}

}

