// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 8 
 * Name:Casey Glover
 * Usercode: 
 * ID: 300280613
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/** Reads a genealogy database from a file, and allows the user
to display information about people in the database.
Each line of the file (except the first) contains information
about one person:
- their name
- their gender (M or F)
- the year of their birth
- the name of their mother (or ? if the mother is unknown)
- the name of their father (or ? if the father is unknown)

The program will read the data into an list of Person objects.

The program then allows the user to print out
- the names of all the people in the database
(note, names are just the first name - no spaces)
- name, gender and date of birth of a given person
- parents of a person (if known) and their gender / dates of birth
- the number of (known) children of a person and all their names
and dates of birth.

 */

public class Genealogy implements UIButtonListener, UITextFieldListener{
    // Fields
    private ArrayList<Person> db = new ArrayList<Person>();  // list of Person object
    private Person currentPerson;  // The person currently specified by the user.
    /**
     * Construct a new Genealogy object
     * and set up the GUI
     */
    public Genealogy(){
        UI.addButton("Load DB", this);
        UI.addButton("All Names", this);
        UI.addTextField("Name", this);
        UI.addButton("Details", this);
        UI.addButton("Parents", this);
        UI.addButton("Children", this);
        UI.addButton("GrandChildren", this);
        UI.addButton("Decendants", this);
        UI.addButton("Ancestors", this);
        UI.addButton("Partners", this);
        UI.addButton("Clear", this);
    }

    // GUI Methods
    /**
     * Respond to button presses
     */
    public void buttonPerformed(String action){
        if (action.equals("Load DB") ){
            this.loadDatabase(UIFileChooser.open("Choose database file"));
        }
        else if (action.equals("All Names") )     { this.printAllNames(); }
        else if (action.equals("Details") )         { this.printPerson(); }
        else if (action.equals("Parents") )       { this.printParents(); }
        else if (action.equals("Children") )      { this.printChildren(); }
        else if (action.equals("GrandChildren") ) { this.grandChildren(); }
        else if (action.equals("Decendants") ) { this.printDesendants(currentPerson, ""); }
        else if (action.equals("Ancestors") ) { this.printAncestors(currentPerson, ""); }
        else if (action.equals("Partners") ) { this.printPartners(currentPerson); }
        else if (action.equals("Clear") )         { UI.clearText(); }
        UI.println("----------------------");
    }

    /**
     * Respond to text field: select the person with the given name
     */
    public void textFieldPerformed(String field, String value){
        if (field.equals("Name")){
            Person p = this.getPerson(value);
            if (p==null){
                UI.println(value + " not in database");
            }
            else {
                currentPerson = p;
                UI.println("found " + p.getName() +" in database");
            }
        }
        UI.println("----------------------");
    }

    /**
     * Reads the data from the database file into the ArrayList in the db field
     * Ensures that the list in db is empty
     * Reads the data on each line and constructs a Person object,
     *  and puts the Person object into the list
     *   (Should use the second or third Person constructor, either
     *    passing to the constructor a line from the file, or the scanner itself)
     * The method may assume that the database is correctly formatted,
     *  and does not need to do any checking of the input.
     */
    public void loadDatabase(String filename){
        UI.println("Reading Database from "+ filename + ".....");
        try {
            /*# YOUR CODE HERE */
            Scanner scan = new Scanner (new File(filename));
            this.db.clear();
            while(scan.hasNext()){
                Person p = new Person(scan);
                db.add(p);
            }
            scan.close();
            UI.println("Database loaded");
        }
        catch(Exception e){UI.println("Error while reading database file");}
    }

    /**
     * Print out names of all the people in the database
     */
    public void printAllNames(){
        UI.println("All names:");
        /*# YOUR CODE HERE */
        for(Person p : db){
            UI.println(p.getName());
        }
    }

    /**
     * Looks for, and returns, a Person with the given name in the database.
     * Returns null if not found
     */
    public Person getPerson(String name){
        /*# YOUR CODE HERE */
        for(Person p: db){
            if(p.getName().equals(name)){
                return p;                    
            }
        }
        return null;
    }

    /**
     * Prints the name, gender, and year of birth of the currently selected person.
     * If no current person, prints a message.
     *  [Note, the toString() method of the Person class returns a string
     * containing the name, gender, and year of birth of the person.]
     */
    public void printPerson(){
        /*# YOUR CODE HERE */
        if(currentPerson != null){
            UI.println(currentPerson.toString());
        }else{
            UI.println("Not Found");
        }
    }

    /**
     * Prints the name, gender, and year of birth of the mother and the father of the
     *  currently selected person, if they are known.
     * Prints appropriate messages if they are unknown.
     * (Must find the Person objects in the database)
     */
    public void printParents(){
        /*# YOUR CODE HERE */
        if(currentPerson != null){
            UI.println("Parents of " + currentPerson.getName() +":");
            if(currentPerson.getMotherName() == null){
                UI.println("Mother Unknown");
            }
            for(Person p: db){
                if(p.getName().equals(currentPerson.getMotherName())){
                    UI.println(p.toString());
                }
                if(p.getName().equals(currentPerson.getFatherName())){
                    UI.println(p.toString());
                }
            }
            if(currentPerson.getFatherName() == null){
                UI.println("Father Unknown");
            }
        }
    }

    /**
     * Prints the number of children of the given person,
     * followed by the names, genders, and years of birth of all the children.
     * Searches the database for Persons who have the currently specified person
     * as one of their parents. Any such person is added to a list
     * It then prints out the information from the list of children.
     */
    public void printChildren(){
        /*# YOUR CODE HERE */
        ArrayList<Person> children = getChildren(currentPerson); // person array of children
        printPersonList(children, "child", "children"); //parses the 3 arguments into the person list
    }

    /**
     * Returns a list of the children of a person.
     */
    public ArrayList<Person> getChildren(Person p){
        ArrayList<Person> children = new ArrayList<Person>(); 
        /*# YOUR CODE HERE */
        if(p != null){
            for(Person c: db){ 
                if((c.getMotherName() != null && c.getMotherName().equals(p.getName())) || 
                (c.getFatherName() != null && c.getFatherName().equals(p.getName()))){
                    children.add(c);
                }
            }
        }
        return children;
    }

    /** Completion:
     * Prints (to textArea) names of all grandchildren (if any) 
     * of the currently specified person
     */
    public void grandChildren(){
        /*# YOUR CODE HERE */
        //selected person check if they have children then check if the children have children
        ArrayList<Person> grandchildren = getGrandChildren(currentPerson);
        printPersonList(grandchildren, "grandchild", "grandchildren"); //parses the 3 arguments
    }

    private void printPersonList(ArrayList<Person> people, String singlular, String plural){
        if(people.size() == 0){ //print the current person
            UI.println(currentPerson.getName() + " has no " +plural+".");
        } else if(people.size() == 1){
            UI.println(currentPerson.getName() + " has 1 " +singlular+":");
        } else{
            UI.println(currentPerson.getName() + " has " +people.size()+ " " +plural+ ":");
        }
        for(Person person: people){ //
            if(person != null){
                UI.println(person.toString()); //prints details of person(s)
            } 
        }
    }

    public ArrayList<Person> getGrandChildren(Person p){
        ArrayList<Person> children = getChildren(p); //children calling get children method parsing in current person
        ArrayList<Person> grandchildren = new ArrayList<Person>(); //new array list of grandchildren
        for(Person kid: children){ //loop through the children
            ArrayList<Person> tempgrandkid = getChildren(kid); //puts all the grandkids into a temporay array
            grandchildren.addAll(tempgrandkid); //adds the list of temporay grandkids to the children array
        }
        return grandchildren;
    }
    
    //Challenge

    private void printAncestors(Person p, String prefix){
        if(prefix == ""){
            UI.println(prefix+ p.toString());
        }else{
            UI.println(prefix+" : "+ p.toString());
        }
        String mothername = p.getMotherName(); //gets mother name of the person converts to a string
        if(mothername != null){
            Person mother = getPerson(mothername); 
            printAncestors(mother, prefix + "--m"); //recursion
        }
        String fathername = p.getFatherName(); //gets father name of the person converts to a string
        if(fathername != null){
            Person father = getPerson(fathername);
            printAncestors(father, prefix + "--f"); //recursion
        }
    }

    private void printDesendants(Person p, String prefix){ //two arguments
        UI.println(prefix+ p.toString()); //prints the decendants
        ArrayList<Person> children = getChildren(p); //getchildren array
        for(Person kid: children){ //loopthrough children array
            printDesendants(kid, prefix +". "); //recursion 
        } 
    }

    private void printPartners(Person p){ //person as an argument
        ArrayList<Person> children = getChildren(p); //children array equal to the get childrn method
        ArrayList<Person> tempPartner = new ArrayList<Person>(); //temporay array
        for(Person kid: children){ //loop through the children array
            //check if partner has been printed out if true then add it to the list and print
            String partnername; //set string as a partner name
            if((kid.getMotherName() != null && kid.getMotherName().equals(p.getName()))){
                //if the mother name is not equal to null and the mother name is equal to name in the person in the children array
                partnername = kid.getFatherName(); //get the father name
            }else{
                partnername = kid.getMotherName(); // get the mother name
            }

            if(partnername != null){ //if not equal to null
                Person partner = getPerson(partnername); //sting to array
                if(!tempPartner.contains(partner)){ //if the array doesn't contain the partners name then add it to the array
                    tempPartner.add(partner); //adds the partner to the temp array
                    UI.println(partnername); // print the partners names

                }
            }
        }

    }

    // Main
    public static void main(String[] arguments){
        Genealogy g = new Genealogy();
    }   

}
