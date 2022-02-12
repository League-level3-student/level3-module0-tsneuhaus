package _01_Simple_Array_Algorithms;

import java.util.ArrayList;
import java.util.Random;

public class MoreArrayFun {
    //1. Create a main method to test the other methods you write.
	
	// ALTERNATIVELY - create a JUnit Test??
	
	public static void main(String[] args) {
		String[] testList = new String[4];
		testList[0] = "Groucho";
		testList[1] = "Harpo";
		testList[2] = "Chico";
		testList[3] = "Zeppo";
		
		printForwards(testList);
		printBackwards(testList);
		printEveryOther(testList);
		printRandomly(testList);;
	}


    //2. Write a method that takes an array of Strings and prints all the Strings in the array.
	public static void printForwards (String[] array) {
		System.out.println("Print Forwards");
		for (String word : array) {
			System.out.print(word + " ");
		}
		System.out.println();
	}


    //3. Write a method that takes an array of Strings and prints all the Strings in the array
    //   in reverse order.
	public static void printBackwards (String[] array) {
		System.out.println("Print Backwards");
		for (int i = array.length - 1; i >= 0; i--) {
			System.out.print(array[i] + " ");
		}
		System.out.println();		
	}


    //4. Write a method that takes an array of Strings and prints every other String in the array.
	public static void printEveryOther (String[] array) {
		System.out.println("Print Every Other");
		for (int i = 0; i < array.length; i += 2) {
			System.out.print(array[i] + " ");
		}
		System.out.println();			
	}

    //5. Write a method that takes an array of Strings and prints all the Strings in the array
    //   in a completely random order. Almost every run of the program should result in a different order.
	public static void printRandomly (String[] array) {
		Random r = new Random();
		ArrayList<String> list = new ArrayList<String>();
		for (String word : array) {
			list.add(word);
		}
			
		int rIndex = 0;
		String word1 = "";
		System.out.println("Print Randomly");
		for (int i = 0; i < array.length; i++) {
			rIndex = r.nextInt(list.size());
			word1 = list.remove(rIndex);
			System.out.print(word1 + " ");
		}
		System.out.println();
		
		
	}

}
