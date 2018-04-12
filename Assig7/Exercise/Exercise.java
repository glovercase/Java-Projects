// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 7 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Six methods that use arrays or ArrayLists
 * cartoonCrowd(): read values from file, create an array of objects, call methods on objects
 *  Uses an array. Relevant to DominoGame
 *
 * plotNumbers(): read numbers from file into an ArrayList of numbers then access values.
 *  Uses an ArrayList. Relevant to Plotter
 *
 * readAndReverse(): read tokens from file into an ArrayList of Strings and
 *  then access values in reverse order
 *  Uses an ArrayList. Relevant to Plotter and DominoGame
 *
 * sentenceGame(): construct array of objects, move them around
 *  Uses an array.  Relevant to DominoGame
 *
 * duckGame(): construct array of objects, call methods on them, remove them
 *  Uses an array with nulls.  Relevant to DominoGame
 *
 * countScores(): read integers from file and construct an array of counts
 *  Uses an array of counts. Relevant to Plotter (part 2)
 */

public class Exercise implements UIButtonListener{

    /** Parameter is the name of a file.
    Reads a file specifying a collection of cartoonFigures,
    First line contains the number of CartoonFigures;
    Remaining lines each contain a string and an x and y value.
    Should read the number on the first line and create an array of that size.
    Using a for loop running through each index of the array (from 0),
    it should repeatedly
    read the three values on a line
    create a new CartoonFigure with those values
    store the CartoonFigure in the next place in the array
    It should then 
    make each cartoonFigure look left and frown, 
    then make each cartoonFigure walk forward 40 units
     */
    public void cartoonCrowd (String filename){
        CartoonFigure[] figs;// the array of figures
        try{
            Scanner sc = new Scanner(new File(filename));
            /*# YOUR CODE HERE */
            int length = sc.nextInt(); // first int is equal to length
            figs = new CartoonFigure[length]; //
            for(int i = 0; i < figs.length; i++){
                figs[i] = new CartoonFigure(sc.nextInt(), sc.nextInt(), sc.next());
            }
            for(int i = 0; i < figs.length; i++){
                figs[i].lookLeft();
                figs[i].frown();
            }
            
            for(int i = 0; i < figs.length; i++){
                figs[i].walk(40);
            }
            sc.close();

        } catch(IOException e){UI.println("File reading failed");}
    }

    /** Reads a sequence of numbers from the file "numbers.txt" into an ArrayList
    keeping track of how many numbers have been read.
    Finds the number half way through the sequence of numbers
    [if the size of the list is s, the middle number is at index (s-1)/2 ]
    Then plots the numbers as a sequence of rectangles hanging from the top
    of the graphics pane, width = 5, height = the number being plotted,
    The color is
    green if the number is less than the middle number,
    black if it is equal to the middle number, 
    red otherwise.
     */
    public void plotNumbers(){
        ArrayList<Double> nums = new ArrayList<Double>(); // the ArrayList of numbers
        double width = 5;
        int middle = 0;
        try{
            Scanner sc = new Scanner(new File("numbers.txt"));
            // read numbers from the file into the ArrayList
            int s = nums.size();
            UI.println(s);
             middle = (s-1)/2;

            /*# YOUR CODE HERE */
            while(sc.hasNext()){
                nums.add(sc.nextDouble());
            }
            for(int i = 0; i<nums.size(); i++){
                nums.get(i);
                
            }

            
            //  UI.setColor(Color.green); //if the number is less than the middle number draw green
            //  UI.setColor(Color.black); //middle number is set to black
            //  UI.setColor(Color.red); // if the number is greater than the middle number set to red;

            // UI.drawRect(); //draw rect


            sc.close();
        } catch(IOException e){UI.println("Fail: " + e);}
        // plot the numbers, 
        double mid = nums.get(middle);
        double x = 0; // x position of first number (increment by 6 each time)
        /*# YOUR CODE HERE */
        for(int i =0; i<nums.size(); i++){
            if(nums.get(i) < mid){
                UI.setColor(Color.green); 
            }
            if(i == mid){
                UI.setColor(Color.black);
            }
            if(nums.get(i) > mid){
                UI.setColor(Color.red);
            }
            double height = nums.get(i);
            
            UI.fillRect(x, 0, width, height );
            x = x+5;
        }

    }
    
    /** Asks the user for a file, and reads each token from the file, storing
    them in an ArrayList.
    It prints out the number of tokens it read and then
    prints the tokens in reverse order.
     */
    public void readAndReverse(){
        ArrayList<String> tokens = new ArrayList<String>(); // the ArrayList of tokens
        String token = ""; //varible token
        int num = 0; //varible num
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            /*# YOUR CODE HERE */
            for(int i = 0; sc.hasNext(); i++){
                token = sc.next();
                tokens.add(token);
                
            }
            num = tokens.size();
            UI.println("The file is " + num + " words long");
            for(int i = num-1; i >= 0; i--){
                String currentToken = tokens.get(i);
                UI.println(currentToken);
            }

        } catch(IOException e){UI.println("File reading failed");}
    }

    /** Sentence Game:
     * Constructs an array of words,
     * User has to incrementally reorder words into a sentence:
     * The method repeatedly
     *  - prints the words out on one line (using a loop to print each word in turn)
     *  - ask the user for the index of a word to move to the left.
     *  - breaks out of the loop if the user enters 0
     *  - swap the word at that index with the word at the previous index
     */
    public void sentenceGame(){
        UI.println("Target:  Jane saw the man with a telescope");
        UI.println();
        String[] words = new String[]{"telescope", "the", "a", "saw", "Jane", "with", "man"};
        String currentWord = "";
        while(true){
            //print out the words in the array.
            /*# YOUR CODE HERE */
            for(int i = 0; i < words.length; i++){
                currentWord = words[i];
                UI.println(currentWord);
            }

            int index = UI.askInt("Word to move:");
            if (index==0){ break; }
            //swap words at index and index-1 (if within range)
            /*# YOUR CODE HERE */
            String temp = words[index];
            words[index] = words[index-1];
            words[index-1] = temp;

        }
        UI.println("Done");
    }

    /**
     * Simple duck shooting game
     *  Uses the Duck class, which has a constructor and two methods:
     *   - To construct a duck, specify its horizontal position: eg new Duck(100);
     *   - shuffle() makes the duck move across the screen to the left
     *   - shoot() turns it upside down. You can't shuffle a duck after you have shot it.
     * 
     * The duckGame method should
     * - Construct an array to hold 5 Ducks,
     * - Fill the array with Ducks placed across the screen (at 200, 300, 400, 500, 600)
     *       The Duck constructor has one parameter - the position of the duck.
     *       the position of the i'th duck should be 200+(i*100)
     * - Have a loop that repeats 5 times:
     *   - Ask the user for the number of a duck to shoot ( 0 to 4)
     *   - IF the answer is valid and that element of the array contains a duck, THEN
     *      shoot the duck (call the shoot method on the duck), 
     *      remove it from the array (put null in the array element)
     *   - shuffle all the remaining ducks (each array element that isn't null)
     */
    public void duckGame(){
        Duck[] ducks = new Duck[5];
        // make five Ducks and put them in the array.
        /*# YOUR CODE HERE */
        int x = 200;
        for (int i = 0; i < ducks.length;  i++){
            Duck d = new Duck(x);
            ducks[i] = d;
            x += 100;
        }

        // repeat five times:
        for (int loop=0; loop<5; loop++){
            int index = UI.askInt("Which duck should I shoot?");
            // shoot the duck, (if it is still in the array)
            // and remove it from the array.
            // make each remaining duck shuffle one step
            /*# YOUR CODE HERE */
            if( index < ducks.length && index >= 0){
                ducks[index].shoot();
                ducks[index] = null;
                
                for(int i =0; i < ducks.length; i++){
                    if(ducks[i] != null){
                        ducks[i].shuffle();
                    }
                }
            }

        }
        // 
        UI.println("That's all your shots");
    }

    /**
     * Reads and counts a sequence of scores from a file.
     * The scores are all numbers between 1 and 10, inclusive.
     * The method keeps a count for each score in an array:
     *  count[n] has the count of the number of n's.
     *  When it reads a score, it increments that count for that score
     * At the end, it prints the counts for each score.
     */
    public void countScores(){
        int[] counts = new int[11]; // the array of counts
        try{
            Scanner scan = new Scanner(new File("scores.txt"));
            /*# YOUR CODE HERE */
            while(scan.hasNext()){
                int score = scan.nextInt();
                counts[score]++;
            }
            for(int i = 1; 1<counts.length; i++){
                UI.println(i + ": " + counts[i]);
            }

            scan.close();
        } catch(IOException e){UI.println("Fail: " + e);}
    }
     public int[] arrayOfCounts(){
        /*# YOUR CODE HERE */
        int count = 0;
        for(int i = 0; i < allNumbers.length; i++){
            
            count = allnumbers[i];
            arrayOfCounts[count]++;

        }
        return count;

    }


    /**
     * Constructor to set up an interface with buttons to call all the methods
     */
    public Exercise(){
        UI.addButton("Show file", this);
        UI.addButton("CartoonCrowd (little)", this);
        UI.addButton("CartoonCrowd (big)", this);
        UI.addButton("Plot Numbers", this);
        UI.addButton("ReadAndReverse", this);
        UI.addButton("Duck Game", this);
        UI.addButton("Sentence Game", this);
        UI.addButton("Scores", this);
        UI.addButton("Quit", this);
    }

    /**
     * Respond to the buttons
     */
    public void buttonPerformed(String button){
        UI.clearGraphics(); UI.clearText();
        if (button.equals("Show file")) {
            this.showFile();
        }
        else if (button.equals("CartoonCrowd (little)")) {
            this.cartoonCrowd("little-crowd.txt");
        }
        else if (button.equals("CartoonCrowd (big)")) {
            this.cartoonCrowd("big-crowd.txt");
        }
        else if (button.equals("ReadAndReverse")) {
            this.readAndReverse();
        }
        else if (button.equals("Plot Numbers")) {
            this.plotNumbers();
        }
        else if (button.equals("Duck Game")) {
            this.duckGame();
        }
        else if (button.equals("Sentence Game")) {
            this.sentenceGame();
        }
        else if (button.equals("Scores")) {
            this.countScores();
        }
        else if (button.equals("Quit")) {
            UI.quit();
        }
    }

    /**
     * Utility method to list the contents of a file.
     */
    public void showFile(){
        String fname = UIFileChooser.open();
        UI.println("Contents of " + fname+":\n----------------");
        try{
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                UI.println(sc.nextLine());
            }
            sc.close();
            UI.println("--------------");
        } catch(IOException e){System.out.printf("Fail: %s\n", e);}
    }

    public static void main(String[] args){
        Exercise obj = new Exercise();
    }        

}
