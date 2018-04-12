// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 8
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 *  Creates an array that holds Person objects, with data read from the
 *   file "family.txt".
 *  Then allows the user to ask four questions about the people:
 *    What are their names
 *    Who is the mother of Catherine
 *    Who is the mother of Michael
 *    Who are the children of Michael
 *   Note: The family tree represented in the file has five people:
 *    Carole and Michael are the parents of Catherine, Philippa and James.
 */

public class PersonExercise implements UIButtonListener{

    ArrayList<Person> data = new ArrayList<Person>();

    /**
     * Add Buttons to the UI
     */
    public PersonExercise(){
        UI.addButton("Load DB", this);
        UI.addButton("All Names", this);
        UI.addButton("Mother of Catherine", this);
        UI.addButton("Mother of Michael", this);
        UI.addButton("Children of Michael", this);
    }

    public void buttonPerformed(String button){
        if (button.equals("Load DB")) {
            this.load();
        }
        else if (button.equals("All Names")) {
            this.allNames();
        }
        else if (button.equals("Mother of Catherine")){
            this.findMotherOf("Catherine");
        }
        else if (button.equals("Mother of Michael")){
            this.findMotherOf("Michael");
        }
        else if (button.equals("Children of Michael")){
            this.findChildrenOfFather("Michael");
        }
        UI.println("---------");
    }

    /**
     * Read each line of the file, (id, name, dob, motherID, fatherID)
     *  constructing a Person object for the line
     *  and putting it in the ArrayList
     * Close the file and print "Loaded" when finished.
     * Note: the Person class has three constructors; you can choose to use either one.
     */
    public void load(){
        try{
            Scanner scan = new Scanner(new File("family.txt"));
            this.data.clear();
            while (scan.hasNext()){
                /*# YOUR CODE HERE */
                Person p = new Person(scan);
                data.add(p); //while there is more data add a new person
            }
            scan.close();
            UI.println("Loaded");
        }
        catch(IOException e){UI.println("Error while reading database file");}

    }

    /**
     * Print out the name, gender, and year of birth of each person in the array.
     * Note, the Person class has a toString method that is useful.
     */
    public void allNames(){
        /*# YOUR CODE HERE */
        for(Person p: data){
            UI.println(p.toString());
        }

    }

    /**
     * Steps through the list to find a person with the specified name
     * Then prints out that person's mother, if known
     * otherwise print out "Mother unknown"
     */
    public void findMotherOf(String name){
        /*# YOUR CODE HERE */
        for(Person p: data){

            if(p.getName().equals(name)){
                if(p.getName().equals("Carole") || p.getName().equals("Michael")) {
                    UI.println("Mother unknown");  
                }else{
                    UI.println(p.getMotherName());
                }
            }

        }
    }
    /**
     * Steps through the list to find each person whose father is the specified name
     * Prints out the name of each such person
     * Note, for each person in the array, you need to get their father's id, and if
     * it isn't -1, look up that id and see if their name is the specified one.
     */
    public void findChildrenOfFather(String name){
        /*# YOUR CODE HERE */
        for(Person p: data){
            if(p.getFatherName() != null){
                UI.println(p.getName());
                //}
            }

        }
    }

    public static void main(String[] args){
        new PersonExercise();
    }

}
