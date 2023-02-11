package project4;

public class IllegalWordException extends IllegalArgumentException {//IllegalWordException extends IllegalArgumentException so it obtains the properties of its class
	public IllegalWordException(String s) {//constructor takes in a string(message)
		super(s);//this calls the constructor of the super class and passes in a string as a parameter
		//this is done so our IllegalWordException works and outputs the appropriate message 
		//using the IllegalArgumentException constructor 
		//this is made possible by using extends 
	}
	
}
