package project4;

import java.util.Comparator;

public class WordComparator implements Comparator<Word>{//Comparator interface tells the tree map how to sort the keys 
	//here we will be comparing Word class types 

	public int compare(Word word1, Word word2) {//body to a method heading in Comparator 
		return word1.toString().compareTo("0");//by comparing the Word we want to input as a key to 0
		//we maintain the order by which the words from the file come in 
		//this means the map isn't sorted alphabetically but they are sorted based on values
		//they are assigned appropriate values as they come in 
	}

}
