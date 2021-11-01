package org.example;

import org.example.virtualkey.screens.WelcomeScreen;

/* In the xx interface, three methods are initialized: show(), introWS(), GetUserInput(). 
 * The two main classes of this application will implement the xx Interface. 
 * Where the methods of this interface will be overridden. All this is done to increase efficiency and reduce redundantly and unnecessary code. 
 * So, for the same methods, which we will use in more than one class and more once, we do not need to declare them in many classes.  
 * It is enough to override from the interface and then implement this interface to the other classes that they could implement, 
 * extend their methods
 * */
public class VirtualKeyApplication {

    public static void main(String[] args) {
    	
    	WelcomeScreen welcome = new WelcomeScreen();
    	welcome.introWS();
    	welcome.GetUserInput();

    }
}