package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	private static MorseCodeTree tree = new MorseCodeTree();
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		printMenu();
	}
	
	/**
	 * Function printMenu() prints four menu options for the user to select from: display morse code
	 * values, translate a morse code file, translate a text input, or exit. printMenu() switches 
	 * between various functions based on userChoice.	
	 */
	public static void printMenu() {
		String userChoice;
		
		
		System.out.println("~Welcome to the Morse Code Translator~");
		System.out.println("Select one of the following options (1-4):");
		System.out.println();
		System.out.println("1) Display Morse Code Values");
		System.out.println("2) Translate File");
		System.out.println("3) Translate Text Input");
		System.out.println("4) Exit");
		
		
		userChoice = scan.nextLine();
			
		
		switch(userChoice) {
		  case "1":
			  tree.displayMorseCodeValues();
			  System.out.println("\n");
			  printMenu();
			  break;
				  
		  case "2":
			  System.out.println(translateFile() + "\n");
			  printMenu();				 
			  break;
				  
		  case "3": 
			  System.out.println(translateLine() + "\n");
			  printMenu();				 
			  break;
				  
		  case "4":
			  break;
				  
		  default:
			  System.out.println("Unknown option. Please try again.\n");
			  printMenu();
			  break;
		}			
		
	}
	
	/**
	 * Using the path name of a file, translates file's morse code to English.
	 * @return String	Translation of the file in English.
	 */
	private static String translateFile() {
		System.out.println("Please enter the file name that you wish to translate: ");
		String fileName = scan.nextLine();
		String translation = "";
		
		try {
			Scanner file = new Scanner(new File(fileName));
			
			while (file.hasNext()) {
				translation += tree.translateFromMorseCode(file.nextLine());
			}
			
			file.close();		
		} catch (FileNotFoundException e) {
			System.err.println("The file could not be found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return translation;
	}
	
	/**
	 * Asks user to manually input morse code and returns its English translation.
	 * @return String	Translation of input text to English.	
	 */
	private static String translateLine() {
		
		System.out.println("\nEnter the morse code you wish to translate. Press 1 to return to the main menu: ");
		String userInput = scan.nextLine();
		String translation = "";
		
		try {	
			translation = tree.translateFromMorseCode(userInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return translation;
	}

}
