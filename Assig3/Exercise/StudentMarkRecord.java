// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 3
 * Name: pondy
 */

import ecs100.*;


/** Describes Student Mark records that have
     - a student name,
     - the number of assignments submitted so far
     - the average mark so far
    Making a new StudentMarkRecord requires the student's name.
    recordMark(double mark)  will add a new assignment to the record, and update the average
    display()  will print  withdraw an amount from the balance of the account, as long
      as the current balance is big enough.
 */
public class StudentMarkRecord{
    // Fields
    private String studentName;
    private int numAssigs;
    private double average;

    /** Construct a new StudentMarkRecord object; parameter is the name of the student.
    */
    public StudentMarkRecord(String name){
        this.studentName = name;
        this.numAssigs = 0;
        this.average = 0.0;
    }

    /** record a mark for a new assignment*/
    public void recordMark(double mark){
        this.average = ((this.average * this.numAssigs) + mark)/(this.numAssigs+1);
        this.numAssigs = this.numAssigs + 1;
    }

    /** print out the student's mark record */
    public void display(){
        UI.printf("%s submitted %d assignments. Average = %4.2f\n",
                  this.studentName, this.numAssigs, this.average);
    }
    

}
