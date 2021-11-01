package org.example.virtualkey.screens;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.example.virtualkey.entities.Directory;

public class FileOptionsScreen implements Screen {
	
	private Directory dir = new Directory();
	//ArrayList is used to store the possible options there, in order to then iterate to each option via the for loop
	private ArrayList<String> options = new ArrayList<>();
	
	//the constructor of this class, that should be called, as soon as the new object of this class is created
    public FileOptionsScreen() {
    	options.add("1.Add a File");
        options.add("2.Delete A File");
        options.add("3.Search A File");
        options.add("4.Return to Menu");
    }
    //Expand and override the Show() method by Interface Screen
    @Override
    public void Show() {
    	System.out.println("File Options Menu");
        for (String s : options) {
            System.out.println(s);
        }
    }
    public void GetUserInput() {
        int selectedOption;
        while ((selectedOption = this.getOption()) != 4) {
            this.NavigateOption(selectedOption);
        }
    }
    //Expand and override the NavigateOption() method by Interface Screen
    @Override
    public void NavigateOption(int option) {
        
    	switch(option) {
        /* It is used to add a File in Directory
         * then the Show() method is called to show what is happening after calling Add() and a break to break the flow 
         */ case 1: 
                this.AddFile();
                this.Show();
                break;
            
         /* It is used to delete File 
          * then the Show() method is called to show what is happening after calling Add() and a break to break the flow
          */case 2: 
        	 this.DeleteFile();
                this.Show();
                break;
         /* It is used to search File 
          * then the Show() method is called to show what is happening after calling Add() and a break to break the flow
          */case 3:
                this.SearchFile();
                this.Show();
                break; 
        /*
         * And a default case, in case if the input is not in the range of options by switch
        */
            default:
                System.out.println("Invalid Option");
                break;
        }
    }
/*
 * This method add a file in directory. 
 * It first gets the file name that the user wants to add to the directory. 
 * Then through try and catch it is checked if the user's request to add a file to a certain path manages to end successfully.
 * If not, the catch returns the error message the flow does not end. 
 * The control logic is done through the if and else conditional clauses as well as some methods used like getPath (), toAbsolutePath (), 
 * getFiles (), add () etc.
 * */
    public void AddFile() {
        System.out.println("Please Enter the Filename:");
        String fileName = this.getInputString();
        System.out.println("You are adding a file named: " + fileName);
        
		try {
			Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
			File file = new File(dir.getName() + fileName);
			
		      if (file.createNewFile()) {
		    	  System.out.println("File created: " + file.getName());
		    	  dir.getFiles().add(file);
		    	  
		      } else {
		        System.out.println("This File Already Exits, no need to add another");
		      }
		}catch (IOException e){
			System.out.println(e + " Error");
		}
	}
    // Logically say that the same is used for the DeleteFile () method, except here the remove () method is called
    public void DeleteFile() {
    	
    	System.out.println("Please Enter the Filename:");
        String fileName = this.getInputString();
        System.out.println("You are deleting a file named: " + fileName);
        
	    //Now, here is the logic to delete a file 
		Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
		File file = path.toFile();
	      if (file.delete()) {
	    	  System.out.println("Deleted File: " + file.getName());
	    	  dir.getFiles().remove(file);
	      } else {
	        System.out.println("Failed to delete file:" + fileName + ", file was not found.");
	      }
    }
    
    
    /* The user will also be offered the opportunity to search for files, whether existing or file, 
     * that he will add during the course of the application.
     * An implementation of the search algorithm logic is presented in this method
     */
    public void SearchFile() {
    	//the initial state is false
    	Boolean found = false;
    	System.out.println("Please Enter the Filename:");
        String fileName = this.getInputString();
        System.out.println("You are searching for a file named: " + fileName);
        
        //Fix it so ArrayList obtains files
        ArrayList<File> files = dir.getFiles();
    
        for(int i = 0; i < files.size(); i++) {
			if(files.get(i).getName().equals(fileName)) {
				System.out.println("Found " + fileName);
				found = true;// if the file is found, then the found go to true 
			}
        }//if and else control statements, in case if the found is false and the message
        if (found == false) {
        	System.out.println("File not found");
        }
    }
    //During the Scanner class, it is here possible for users to type input 
    private String getInputString() {
        Scanner in = new Scanner(System.in);
        return(in.nextLine());

    }
    
    private int getOption() {
        Scanner in = new Scanner(System.in);
        int returnOption = 0;
        /* In case the entries are not correct, the flow of the application should not be stopped. 
         * This is achieved through Try and Catch
         */
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {
        	System.out.println("Invalid input");
        }
        return returnOption;
    }
}