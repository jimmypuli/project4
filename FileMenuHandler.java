package project4;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FileMenuHandler implements ActionListener {//this class implements Action Listener so the method headings in the interface
	//ActionListener have bodies. In other words, this tells the program what to do when we click on an option. 
	
	Character firstLetter; //this will be used later on to get the first letter of the words we read from the file
	
	JFrame jframe; //we need a reference to the jframe so we can add text areas to it to display the words we've sorted
	
	StringTokenizer myTokens; 
	
	
	//we make our TextAreas global so it can be accessed by multiple methods
	TextArea a = new TextArea();//Creates a text area which will be used to contain words that start with A or a
	TextArea e = new TextArea();//this is done so we have a place to put words that start with E or e
	TextArea i = new TextArea();//this is done so we have a place to put words that start with I or i 
	TextArea o = new TextArea();//this is done so we have a place to put words that start with O or o 
	TextArea u = new TextArea();//this is done so we have a place to put words that start with U or u
	TextArea other = new TextArea();//words that do not fit any of the categories above will utilize this TextArea 
	

	public FileMenuHandler (JFrame jf) {
		jframe = jf; //sets our reference to jframe
	}
	
	public void actionPerformed(ActionEvent e) {//tells the program what to do when an option is clicked
		String menuName = e.getActionCommand();//gets the name of the option that what clicked 
		if(menuName.equals("Open"))
			openFile();//if we clicked on open we call the openFile method
		else if (menuName.equals("Quit"))
			System.exit(0); //if we clicked on quit we exit the program
		else if (menuName.equals("Add Word"))
			addWord();//if we clicked on Add Word then we call the addWord method 
	}//actionPreformed
	
	
	private void addWord() {
		String inputWord = JOptionPane.showInputDialog(null, "Enter a word to add"); //we do this so we get the word from the user
		Character firstLetter = inputWord.charAt(0);//we also need the firstLetter of the to determine which cell to put it in   
		Word wordFromUser = new Word(inputWord); //Word object is created for exception handling 
		if(wordFromUser.isValidWord(inputWord)) {//if we have a valid word we add it to the appropriate cell
			if(firstLetter.equals('A')||firstLetter.equals('a')) {//if the Word starts with A or a
				a.append(wordFromUser + "\n");//we add it to text area a 
			}else if(firstLetter == 'E'||firstLetter == 'e') {//if the Word starts with E or e
				e.append(wordFromUser + "\n");//we add it to text area e
			}else if(firstLetter == 'I'||firstLetter == 'i') {//if the Word starts with I or i 
				i.append(wordFromUser + "\n");//we add it to text area i
			}else if(firstLetter == 'O'||firstLetter == 'o') {//if the Word starts with O or o
				o.append(wordFromUser + "\n");//we add it to text area o
			}else if(firstLetter == 'U'||firstLetter == 'u') {//if the Word starts with U or u 
				u.append(wordFromUser + "\n");//we add it to text area u
			}else {
				other.append(wordFromUser + "\n");//we add it to text area other for words that do not start with vowels
			}
		}		
		jframe.setVisible(true);//we make our changes to the window visible 
		
	}
	
	
	
	private void openFile() {//method that allows us to choose files from our computer
		int status;//tells us the status of the file 
		JFileChooser chooser = new JFileChooser();//chooser object created so we can access the computer files 
		status = chooser.showOpenDialog(null); //shows us the files to choose from 
		readSource(chooser.getSelectedFile());//this is done so that we can read the file that was selected by the user
	}//openFile
	
	private void readSource(File chosenFile) {//this method takes in the file we chose as a parameter and reads it
		String chosenFileName = chosenFile.getName();//we get the name of the file so we can use TextFileInput to read the file based on its name 
		TextFileInput inFile = new TextFileInput(chosenFileName);//Tfi object will be used with methods to read the file
		String line;//line will hold the words we read in from the file 
		
		jframe.setLayout(new GridLayout(2,3));//this is done so our window will contain the appropriate rows and cols to fit our info

		Container myContentPane = jframe.getContentPane(); //gets the area inside our window so we can manipulate it 
		myContentPane.add(a); //we add TextArea a into the container order so our output shows and is organized
		myContentPane.add(e); //we add e into the container so our output shows and is organized
		myContentPane.add(i); //we add i into the container so our output shows and is organized
		myContentPane.add(o); //we add o into the container so our output shows and is organized
		myContentPane.add(u); //we add u into the container so our output shows and is organized
		myContentPane.add(other);// lastly we add other so our output in this TextArea shows and is organized

		
		StringTokenizer myTokens; //we will be splitting the lines from the file read so a stringtokenizer needs to be declared
		
		
		
		line = inFile.readLine();//set line to the first word in the file  
	
		TreeMap <Word, Word> map = new TreeMap(new WordComparator());//we instantiate our tree map with the word comparator so our words are sorted how we want
			
		while(line != null) {//this is done so we make sure we read the entire file 
			myTokens = new StringTokenizer(line, " ");//our tokens is created by splitting the line according to a whitespace  			
			while(myTokens.hasMoreTokens()) {//while we have more tokens to go through
				String wordFromToken = myTokens.nextToken();//we need the string from the token to check for periods or commas or for vaild words
				Pattern p;//p holds the pattern we want to compare
				Matcher m;//matcher will compare the pattern and a given string 
				String WORD_PATTERN = "[,.]{1}";//initially our pattern is being held in a string
				//the matcher will look for comma or period for an occurrence 1 time in the string according to the regular expression
				//either a comma or period has to be found one time exactly
				p = Pattern.compile(WORD_PATTERN);//before we compare the WORD_PATTERN string it has to be compiled to a pattern format
				m = p.matcher(wordFromToken);//here we compare the string with the pattern
				if(m.find()) //if we find a match 
					wordFromToken = wordFromToken.substring(0, wordFromToken.length()-1);//if we find a match we need to get a substring of token so it doesn't include a comma or period 			
				try {
					Word wordFromFile = new Word(wordFromToken);//we create a word object from the token
					firstLetter = wordFromFile.toString().charAt(0);//we need the first letter of the wordFromFile to determine was values to give it 
					if(firstLetter == 'A'||firstLetter == 'a') {//if it starts with A or a 
						map.put(wordFromFile, new Word("A"));//we put the Word in our map with the value A
					}else if(firstLetter == 'E'||firstLetter == 'e') {//if the Word starts with E or e
						map.put(wordFromFile, new Word("E"));//we put the Word in our map with the value E 
					}else if(firstLetter == 'I'||firstLetter == 'i') {//if the Word starts with I or i 
						map.put(wordFromFile, new Word("I"));//we put the Word in our map with the value I
					}else if(firstLetter == 'O'||firstLetter == 'o') {//if the Word starts with O or o
						map.put(wordFromFile, new Word("O"));//we put the Word in our map with the value O
					}else if(firstLetter == 'U'||firstLetter == 'u') {//if the Word starts with U or u 
						map.put(wordFromFile, new Word("U"));//we put the Word in our map with the value U
					}else {
						map.put(wordFromFile, new Word("Other"));//else we put the Word in our map with the value Other
					}
				}catch(NullPointerException excep) {
					System.out.println("Skipping this word.....");//this is done to let users know the program will continue after a invalid word is caught
				}			
			}
				line = inFile.readLine();//we go to the next line of the file so it can be read
		}//while
		
		Set set = map.entrySet();//this gets the entry set of our map 
		Iterator iter = set.iterator();//iter will traverse the set(entries of the map)
		Map.Entry<Word,Word> me;//me will give us access to the keys and values through get methods 
		
		while(iter.hasNext()) {//while we still have more entries
			me = (Map.Entry)iter.next();//me will be set equal to the first entry 
			if(me.getValue().toString() == "A") {//if the value of this entry is A 
				a.append(me.getKey().toString()+"\n");//we append the key to text area a
			}else if(me.getValue().toString() == "E") {//if the value of this entry is E
				e.append(me.getKey().toString()+"\n");//we append the key to text area e
			}else if(me.getValue().toString() == "I") {//if the value of this entry is I
				i.append(me.getKey().toString()+"\n");//we append the key to text area i
			}else if(me.getValue().toString() == "O") {//if the value of this entry is O
				o.append(me.getKey().toString()+"\n");//we append the key to text area o
			}else if(me.getValue().toString() == "U") {//if the value of this entry is U
				u.append(me.getKey().toString()+"\n");//we append the key to text area u
			}else {
				other.append(me.getKey().toString()+"\n");//else we append they key to the text area other
			}
		}
		inFile.close();//we close the file when our program is done 
		jframe.setVisible(true);//we make the changes to our window visible
	}//readSource
	
	
	
	
}//ActionListener
