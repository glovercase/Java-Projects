// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.


/* Code for Assignment 6
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;

/** A FlatAccount keeps track of the balances of two kinds of expenses in a flat:
    rent and food.
    When a new Flat Account object is constructed, the balances are set to 0.
    The constructor should have one argument -
      the name of the flat, which should be stored in the object.

    FlatAccount has three methods:
     - printBalances(), which prints out a statement of the two balances.
     - expense(...), which makes a payment of some amount from the flat
       account out of one of the balances. (reduces the balance)
       Requires the name of the balance ("rent" or "food"), and the amount of payment.
     - contribute(...), which records a deposit of some amount into the flat account
       (puts the amount into the smallest balance)
       Requires the amount.
 */
public class FlatAccount{

    // fields:
    /*# YOUR CODE HERE */
    

    // Constructor: has one argument - the name of the flat.
    /*# YOUR CODE HERE */

    //methods

    /** getName():
        returns the name of the flat
    */
    /*# YOUR CODE HERE */

    /**printBalances():
       prints the name of the flat and the two balances.
     */
    /*# YOUR CODE HERE */

    /** expense(...),
        Parameters are the balance ("rent" or "food") and the amount.
        makes a payment out of the specified balance.
    */
    /*# YOUR CODE HERE */

    /** contribute(...),
        Parameter is the amount
        Makes a payment into the smallest balance of the flat account.
    */
    /*# YOUR CODE HERE */



    //-------------------------------------------------
    /** Run this method from blueJ to test your class.
        Makes two FlatAccount objects, and lets user record expenses and contributions */
    public static void testFlatAccount(){
        FlatAccount us = new FlatAccount("161 Kelburn Pde");
        FlatAccount them = new FlatAccount("172 Kelburn Pde");
        us.printBalances();
        them.printBalances();
        
        int tests = 0;
        while (tests<=6 & UI.askBoolean("Do more? (y/n): ") ){
            // choose a flat:
            FlatAccount flat;
            if (Math.random()<0.5) { flat = us; }
            else { flat = them;}

            //  expense or contribute
            if (Math.random() < 0.5){
                String category = UI.askString("Type of expense for " + flat.getName() + " ('rent' or 'food')");
                double amt = UI.askDouble("Amount:");
                flat.expense(category, amt);
            }
            else {
                double amt = UI.askDouble("Amount to contribute to " + flat.getName() + ":");
                flat.contribute(amt);
            }
            UI.println("-------------------------");
            flat.printBalances();
            tests++;
        }            
    }   

    public static void main(String[] args){
        testFlatAccount();
    }

}
