// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 7 
 * Name: casey Glover 
 * Usercode:300280613
 * ID: i wor =  =  =    =

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * This is an extension of the StockAnalyser program from assignment 4
 * which plots a sequence of stock prices and prints some statistics.
 * However, instead of reading data from the user, it reads the data from
 * a file into an array, which means that it can analyse the numbers
 * more easily and in more different ways.
 * It can also cope with much larger sets of numbers.
 * It plots the data in two different ways.
 * The numbers are guaranteed to be integers.
 * The first number in the file is always the number of numbers. // 
 *
 * The constructor sets up three buttons and the buttonPerformed
 * method should respond to them:
 * Read Data: Reads numbers from a file into an array (stored
 *            in a field) and prints statistics of the data.
 *       It should assume that all the values are integers.
 * Plot:      Plots a line graph (not a bar graph) of all the numbers
 * Histogram: [completion] Computes and plots a histogram of the numbers
 *            (the number of times each possible number occurs in the file)
 *
 * There are 7 methods you are to complete, all focusing on the array of data.
 * readData:  asks user for file and reads numbers (integers) into an array
 * minValue:  returns the smallest value in the array 
 * maxValue:  returns the largest value in the array 
 * mean:      returns the mean of the values in the array
 * drawPlot:  plots a line graph of all the values in the array
 *            (partly completed for you)
 * histogram: [completion] computes and draws a histogram of the numbers. 
 * arrayOfCounts: [completion] used by histogram.
 * standardDeviation: [completion] returns the standard deviation of the values.
 */

public class Plotter implements UIButtonListener
{
    // Fields 
    private int[] allNumbers;   
    private int[] count;// the field to hold the array of data

    // (You shouldn't add any more fields for core or completion)

    // Constant fields holding the dimensions of the graph for the plotData method
    public static final int GRAPH_LEFT = 10;
    public static final int GRAPH_TOP = 10;
    public static final int GRAPH_RIGHT = GRAPH_LEFT + 500;
    public static final int GRAPH_BOT = GRAPH_TOP + 450;

    /** Constructor:
     * Set up the three buttons
     */
    public Plotter(){
        UI.addButton("Read Data", this);
        UI.addButton("Plot Data", this);
        UI.addButton("Histogram", this);
    }

    /**
     * Respond to button presses 
     */
    public void buttonPerformed(String button){
        if (button.equals("Read Data") ){
            UI.clearGraphics();
            UI.clearText();
            this.readData();
            this.reportStats();
        }
        else if (button.equals("Plot Data") ){
            this.plotData();
        }
        else if (button.equals("Histogram") ){
            this.histogram();
        }
    }

    /**
     * Asks user for the file (using UIFileChooser.open() and
     * creates an array stored in a field, then 
     * reads data from the file into the array
     * Assumes all data values are positive integers. 
     */
    public void readData(){
        // Read first value from the file and create the array of that size
        // Read that many numbers from the file into the array
        /*# YOUR CODE HERE */

        try{
            File file = new File(UIFileChooser.open()); // new file
            Scanner scan = new Scanner(file); // new scanner
            int length = scan.nextInt(); //the lenght of the array
            allNumbers = new int[length]; //new array called allNumbers
            while(scan.hasNext()){ //while there is a token
                for(int i = 0; i < allNumbers.length && scan.hasNextInt(); i++){
                    allNumbers[i] = scan.nextInt(); // ints go into array

                }
            }

        } catch(IOException e){UI.println("File reading failed");}

    }

    /**
     * Computes and writes out the min, max, mean, standard deviation, and median of the data.
     * Appends the data to the textArea.
     */
    public void reportStats(){
        if (this.allNumbers==null){ //there is no data to analyse
            UI.println("No data to report on");
            return;
        }
        UI.println("\n\nStatistics:\n-------------");
        UI.printf("Count:  %d\n", this.allNumbers.length);
        UI.printf("Min:    %d\n", this.minValue());
        UI.printf("Max:    %d\n", this.maxValue());
        UI.printf("Mean:   %4.3f\n", this.mean());
        UI.printf("Standard Deviation:    %4.3f\n", this.standardDeviation());
    }

    /**
     * Returns the smallest value in the data array. 
     * Assumes there is at least one value 
     */
    public int minValue(){
        /*# YOUR CODE HERE */
        int minnum = 99999999; //large number
        for(int i = 0; i < allNumbers.length; i++){ //loops through all the numbers
            
            if(minnum > allNumbers[i]){ //if minimum number is greater than i
                minnum = allNumbers[i]; // set min number to i

            }

        }

        return minnum; //returns minimum number in array

    }

    /**
     * Returns the largest value in the data array. 
     * Assumes there is at least one value 
     */
    public int maxValue(){
        /*# YOUR CODE HERE */
        int maxnum = 0; // max number at 0
        for(int i = 0; i < allNumbers.length; i++){ //loops through all the numbers
            
            if(allNumbers[i] > maxnum){ //if i greater than max number then
                maxnum = allNumbers[i]; //set max num to i

            }

        }
        return maxnum; //returns maximum number in array

    }

    /**
     * Returns the mean of the values in the data array. 
     * Assumes there is at least one value
     */
    public double mean(){
        /*# YOUR CODE HERE */
        double meannum = 0; //intialise
        double sum = 0;
        for(int i = 0; i < allNumbers.length; i++){
            sum += allNumbers[i]; //add up all the numbers in the array
            
        }
        meannum = sum / allNumbers.length; // mean is equal to the sum of the numbers divided bu the length of the array
        return meannum; // return the mean

    }

    /**
     * Returns the standard deviation of the values in the data array. 
     * Assumes there is at least one value
     * (COMPLETION)
     */
    public double standardDeviation(){
        /*# YOUR CODE HERE */
        double standardDev = 0;
        double difference = 0;
        double pow = 0;
        double total = 0;
        for(int i = 0; i < allNumbers.length; i++){ //loop through array
            difference = allNumbers[i] - mean(); //array of numbers less the mean
            pow = Math.pow(difference, 2); //the differece to the power of 2
            total += pow; //total is all the pow added together
            
            
        }
        total = total / allNumbers.length; //total divided by the length of the array
        standardDev = Math.sqrt(total); //standard deviation is equal to the square root of the total
        return standardDev;

    }

    /**
     * Plots a line graph of the data
     * Core version: assume all data values are positive and fewer than 500 values.
     * Draws the x and y axis,
     * Plots a line graph of all the points with a blue line between
     *  each pair of adjacent points
     * Uses the GRAPH_LEFT, GRAPH_TOP, etc fields for the dimensions and positions of the graph.
     */
    public void plotData(){
        if (this.allNumbers==null || this.allNumbers.length<2){ //there is no data to analyse
            UI.println("No data to plot");
            return;
        }
        UI.clearGraphics();

        // draw axes
        UI.setColor(Color.black);
        UI.drawLine(this.GRAPH_LEFT, this.GRAPH_TOP, this.GRAPH_LEFT, this.GRAPH_BOT); 
        UI.drawLine(this.GRAPH_LEFT, this.GRAPH_BOT, this.GRAPH_RIGHT, this.GRAPH_BOT);

        // plot points: line between each pair of values
        /*# YOUR CODE HERE */
        double x = GRAPH_LEFT;
        double y = GRAPH_BOT;
        double X = 0;
        double Y = 0;
        
        for(int i = 0; i < allNumbers.length; i++){ //loop the array 
            UI.setColor(Color.blue); //line color
            X = x + 1; // x2 to x plus 1
            Y = this.GRAPH_BOT - allNumbers[i]; //y2 to the array of numbers
            UI.drawLine(x,y,X,Y); //drawline
            y = Y; //set y2 to y1
            x = X; //set x2 to x1
            Y = 0; //set y2 back to zero
        }
       
       

    }

    /**
     * Draw histogram
     * Assumes all values are between 0 and 450, and
     * also that the count of any single value is less than 450.
     * Plots a (green) vertical line for each possible value.
     * Height of line is 5 times the count of that value.
     * Uses the arrayOfCounts method to construct the data for the
     * histogram.
     * (COMPLETION)
     */
    public void histogram(){
        if (this.allNumbers==null){ //there is no data to analyse
            UI.println("No data to plot histogram");
            return;
        }
        UI.clearGraphics();

        // draw axes
        UI.setColor(Color.black);
        UI.drawLine(this.GRAPH_LEFT, this.GRAPH_TOP, this.GRAPH_LEFT, this.GRAPH_BOT); 
        UI.drawLine(this.GRAPH_LEFT, this.GRAPH_BOT, this.GRAPH_RIGHT, this.GRAPH_BOT);

        // Plots a (green) vertical line for each possible value.
        // Height of line is 5 times the count of that value.
        double x = GRAPH_LEFT;
        double y = GRAPH_BOT;
        double X = 0;
        double Y = this.GRAPH_BOT;
        /*# YOUR CODE HERE */
        int[] count = this.arrayOfCounts();
        for(int i = 0; i < count.length; i++){ //loop the array 
            UI.setColor(Color.green); //line color
            X = x + 1; // x            int Y = arrayOfCounts() ; //y2 to the array of numbers2 to x plus 1
            Y = y - count[i] *5;
            UI.drawLine(x,y,X,Y); //drawline
            x = X; //set x2 to x1
            Y = this.GRAPH_BOT; //set y2 back to zero
        }

    }

    /**
     * Constructs and returns an array of counts of each value.
     * (the "count array")
     * Assumes that all values are non-negative.
     * The count array will have a count for each integer from 0
     * to the maximum value in the data
     * (COMPLETION)
     */
    public int[] arrayOfCounts(){
        /*# YOUR CODE HERE */
        count = new int[allNumbers.length+1];
        
        for(int i = 0; i < allNumbers.length; i++){
            
            count[i]++;
            

        }
        return count;

    }

    /**
     * Returns the median value in the data array. 
     * Assumes there is at least one value 
     *         (CHALLENGE)
     */
    public double median(){
        /*# YOUR CODE HERE */
       

        return -1;   // Required to make it compile. You may change this line.
    }

    // Main
    public static void main(String[] arguments){
        new Plotter();
    }        

}
