package project4;


import java.util.regex.*;

public class Word {

private String word;//Ultimately our "word" will be of type String 
//this tells the compiler what type word is/what it looks like
	
public Word(String word) {//constructor that takes in a string 
	//this is done so that new "word" objects can be created when we use "new"
		try {//we can read illegal word values from the file so the code below is included in a try block
			if(isValidWord(word)) {//we check to see if the word read in is valid 
				this.word = word;//the parameter passed is assigned to the object
				//this is used to avoid confusion and to differentiate between a formal parameter(word)
				//and instance variable(this.word)
			}else {
				throw new IllegalWordException("Illegal word value: " + word);//if the word isn't valid we throw an exception 
			}
			
			
		}catch (IllegalWordException e){//we have this catch block to handle our exception
			System.out.println(e.getMessage());//this will let users know what words read from the file are invalid 	
			
		}
	}

public boolean isValidWord(String a) {//this method takes in a string and determines if it is valid 
	Pattern p;//p will hold the pattern we want to compare to 
	Matcher m;//matcher compare the pattern and string a
	String WORD_PATTERN = "^[a-zA-Z]+$";//the pattern we want to compare to is held in a string 
	//^ and $ means the pattern has to hold in the string from beginning to end 
	//this means the string has to contain only letters
	//[a-zA-Z] means the letters can be letters from a - z or A - Z
	//the + means the pattern has to repeat one or more times
	//with the ^ and $ it means the pattern has to repeat throughout the whole string 
	p = Pattern.compile(WORD_PATTERN);//before we compare the WORD_PATTERN string has to be compiled to a pattern format
	m = p.matcher(a);//here we compare the string with the pattern 
	if(m.find()) {//if we find a match 
		return true;//we return true - the string is valid
	}else {
		return false;//we return false - the string is not valid 
	}
}
	
public void setWord(String word) {//this allows us to change the string contained in a word object
	this.word = word; //this instance of word equals to the string word
}

public String getWord() {//get method that allows us to get a word object 
	return word; 
}

public String toString() {//this method allows us to use methods that come in the String class later on 
//with word class objects by casting word as a String 
	return (String)word; 
}


}//end of Word
