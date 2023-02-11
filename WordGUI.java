package project4;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class WordGUI extends JFrame {//this class extends the JFrame so that our class can do anything a JFrame can do 
	
	JMenuBar menuBar = new JMenuBar(); //our menubar is made global so that we only have one and it isn't replaced by other menubars
	
	
	
	
	public WordGUI(String title, int height, int width) {//this constructor creates the necessary initializations
		setTitle(title); //makes the title of our window Project 3
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this is done so that our program terminates when the window is closed	
		setSize(height,width);//makes our window big enough for display
		createFileMenu(); //function that sets up our window so we have options displayed on the window 
		setVisible(true); //makes any changes to our window visible after the changes are done 
	}
	private void createFileMenu() {
		JMenuItem item;//item variable will hold any new items we want 
		JMenu fileMenu = new JMenu("File"); //we create an option on the menubar called file 
		FileMenuHandler fmh = new FileMenuHandler(this);//create an object(fmh) to handle any events occuring on the window 
		
		JMenu editMenu = new JMenu("Edit"); //we create another menu "Edit" so we can add words to the window 
		
		item = new JMenuItem("Add Word");//creating a drop down item will allow us to add words after the file has been read
		item.addActionListener(fmh);//however, we need an action listener to "hear" when we click  
		editMenu.add(item); //we need to add the item we just created to edit menu because we want it to show up when we click on editMenu
		
		item = new JMenuItem("Open");//We create an item called Open 
		item.addActionListener(fmh);//We need an action listener added to item so we know when an event occurs(clicking)
		fileMenu.add(item);//we add this item(Open) to fileMenu so when we click on file there is a drop down item called Open
		
		item = new JMenuItem("Quit");//we create another item called Quit 
		item.addActionListener(fmh);//we also need an action listener for when it is clicked 
		fileMenu.add(item); //we add quit to the menu so it is an option in the drop down menu
		
		setJMenuBar(menuBar);//we set the menu bar of the window to the menu bar we've created 
		menuBar.add(fileMenu);//we add the file menu to our menu bar so our options are visible 
		menuBar.add(editMenu); //right after the file menu is added we add the edit menu
		
	}
	
}
