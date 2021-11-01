package org.example.virtualkey.screens;

import org.example.virtualkey.services.DirectoryService;
import org.example.virtualkey.services.ScreenService;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WelcomeScreen implements Screen {
	
	/*	At the moment of execution of the application, as required in the project,
	 *	the first two strings of the Welcome Screen class will be displayed
	 */ 
	private String welcomeText = "Welcome to VirtualKeyApplication!";
    private String developerText = "Developer: Vetim Halimi";

    //ArrayList is used to store the possible options there, in order to then iterate to each option via the for loop
    private ArrayList<String> options = new ArrayList<>();

    /* And the constructor of this class, from where at the moment a new object of this class is created, 
     * the constructor will be executed at the beginning
     * */
    public WelcomeScreen() {
        options.add("1.Show Files");
        options.add("2.Show File Options Menu");
        options.add("3.Quit");
    }
    
    public void introWS() {
    	System.out.println(welcomeText);
        System.out.println(developerText);
        System.out.println("\n");
        Show();
    }
    //Expand and override the Show() method by Interface Screen
    @Override
    public void Show() {
    	System.out.println("Main Menu");
        for (String s : options)  {
            System.out.println(s);
        }

    }

    public void GetUserInput() {
        int selectedOption  = 0;
        while ((selectedOption = this.getOption()) != 3) {
            this.NavigateOption(selectedOption);
        }
    }
    
    //Expand and override the NavigateOption() method by Interface Screen
    @Override
    public void NavigateOption(int option) {
        switch(option) {

            case 1: // Show Files in Directory
                this.ShowFiles();
                this.Show();
                break;
                
            case 2: // Show File Options menu
            	ScreenService.setCurrentScreen(ScreenService.FileOptionsScreen);
                ScreenService.getCurrentScreen().Show();
                ScreenService.getCurrentScreen().GetUserInput();
                this.Show();
                break;
                
            default:
                System.out.println("Invalid Option");
                break;
        }
        
    }
    /*
     * Get the files from the Directory
	*/
    public void ShowFiles() {
        System.out.println("List of Files: ");
    	DirectoryService.PrintFiles();
    }
    
    private int getOption() {
        Scanner in = new Scanner(System.in);

        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {

        }
        return returnOption;

    }
}