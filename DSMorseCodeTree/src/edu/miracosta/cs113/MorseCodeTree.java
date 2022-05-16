package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 2.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
	public MorseCodeTree() {
		super(new Node(null));
		readMorseCodeTree();
	}
	
    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws Exception {
    	String[] words = morseCode.split("\\s+");
    	Node<Character> current;
    	StringBuilder sb = new StringBuilder();
    	
    	for (String word : words) {
    		current = root;
    		for (int i = 0; i < word.length(); i++) {
        		if (word.charAt(i) != '*' && word.charAt(i) != '-') {
        			throw new Exception("Illegal variable used! Only '*' or '-' allowed.");
        		}
        		else if (word.charAt(i) == '-') {
        			if (current.right == null) {
        				throw new Exception("Code length exceeds number of possible traversals.");
        			}
        			current = current.right;
        		}
        		else if (word.charAt(i) == '*') {
        			if (current.left == null) {
        				throw new Exception("Code length exceeds number of possible traversals.");
        			}
        			current = current.left;
        		}
        	}
    		sb.append(current.data);
    	}	  	
        return sb.toString();
    }
    
    /**
     * Builds the MorseCodeTree using the input file that contains each alphabet character and its respective
     * morse code translation.
     */
    public void readMorseCodeTree() {
    	try {
    		File file = new File("src/edu/miracosta/cs113/MorseCode.txt");
    		Scanner scan = new Scanner(file);
    		
    		while (scan.hasNextLine()) {
    			String line = scan.nextLine().replaceAll("\\s", "");
    			Node<Character> letter = new Node<>(line.charAt(0));
    			line = line.substring(1);
    			Node<Character> next = root;
    			
    			while (line.length() > 1) {
    				if (line.charAt(0) == '-') {
        				next = next.right;
        			} 
        			else if (line.charAt(0) == '*') {      
        				next = next.left;
        			}
    				
    				line = line.substring(1);
    			}
    			
    			if (line.charAt(0) == '-') {
    				next.right = letter;
    			}
    			else if (line.charAt(0) == '*') {
    				next.left = letter;
    			}    					
    		}
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Recursive function that traverses the MorseCodeTree and populates a TreeMap with the 
     * alphabet character and its morse code translation. TreeMap will store the keys in
     * alphabetical order.
     * 
     * @param map	The TreeMap being populated.
     * @param current	The current node being traversed in the tree.
     * @param path	The path being traversed in the tree. 
     */
	private void getCode(TreeMap<Character, String> map, Node current, String path) {
        map.put((Character) current.data, path);

        if (current.left != null) {
            getCode(map, current.left, path + "*");
        }
        if (current.right != null) {
            getCode(map, current.right, path + "-");
        }
    }
	
	/**
     * Utilizes getCode function to populate TreeMap with alphabet character-morse code 
     * pairings. These pairings are then displayed in a tabular format.
     */
	public void displayMorseCodeValues() {
		TreeMap<Character, String> morseCode = new TreeMap<Character, String>();
		Node<Character> current = root;
		getCode(morseCode, current.left, "*");
		getCode(morseCode, current.right, "-");
		
		int currentRow = 0;
        int colCount = 4;
		
		for (Map.Entry<Character, String> code : morseCode.entrySet()) {
			String value = code.getValue();
			value += " ".repeat(6 - value.length());
            System.out.print("| " + code.getKey() + " : " + value + "|");

            if (currentRow == colCount - 1) {
                System.out.println();
            }
            currentRow++;
            currentRow = currentRow % colCount;
        }
		System.out.println();
	}

} // End of class MorseCodeTree
