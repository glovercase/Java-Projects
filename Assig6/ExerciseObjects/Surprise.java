// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.


/* Code for Assignment 6
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;


/** A Surprise object remembers the word it was given when it was constructed
    and has one method that says "Boo " followed by the word.
    It is a totally useless class!!!
 */
public class Surprise{

    //field
    /*# YOUR CODE HERE */

    /** Constructor: 
        Stores the word it is passed in the field
    */
    public Surprise(String wd){
    /*# YOUR CODE HERE */
    }

    /** sayBoo method
        Prints out "Boo " followed by the stored word
     */
    public void sayBoo(){
    /*# YOUR CODE HERE */
    }        



    //-------------------------------------------------
    /** Run this method from BlueJ to test your class.
        Makes several Surprise objects, and calls methods on them */
    public static void testSurprise(){
        Surprise s1 = new Surprise("Pumpkin");
        Surprise s2 = new Surprise("Squash");
        s1.sayBoo();
        s2.sayBoo();
        s1.sayBoo();
        Surprise s3 = new Surprise("Elephant");
        s3.sayBoo();
        s2.sayBoo();
        s1.sayBoo();
        //change the value in s1 to a new object  
        s1 = new Surprise("Ant");
        s1.sayBoo();
        s2.sayBoo();
        s3.sayBoo();
    }        

    public static void main(String[] args){
        testSurprise();
    }


}
