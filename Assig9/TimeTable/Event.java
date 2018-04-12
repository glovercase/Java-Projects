// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 9
 * Name: Casey Glover
 * Usercode:
 * ID:
 */

/** Represents a event.
    This is a very simple class.  It needs
    - two fields (Strings) for storing the description and location
    - a constructor (which is passed a description and a location
    - two methods (getDesc() and getLoc()) to return the description and location
 */
public class Event{
 
    /*# YOUR CODE HERE */
    
    //Fields
    
    private String description;
    private String location;
   
     
    //constructor
    public Event(String d, String l){
        description = d;
        location = l;
        
        
    }
    //methods
    public String getDesc(){   
        return description; 
    }
    
    public String getLoc(){
        return location;
    }
    
    

}
