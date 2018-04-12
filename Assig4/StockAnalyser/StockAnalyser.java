// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 4 2010
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;


/** The program contains two methods for analysing the prices of a stock over 
  *  the course of a day on the stock market.  There are several things that
  *  an invester may be interested in:
  *  The opening and closing prices (the first and the last prices for the day)
  *    and whether the stock rose or fell in price, and by how much
  *  The maximum and the minimum prices at any point during the day
  *  The average price during the day.
  *
  *  The program has two methods.  They both read a sequence of prices from
  *  the user (through the terminal window).
  *  One method computes and prints out the six numbers of interest:
  *    maximum, minimum, and average price.
  *    open and close price,
  *    amount it rose (or fell)
  *  The other method plots the prices through the day as a simple bar graph.
  */
public class StockAnalyser{

    /**
     * analysePrices reads a sequence of prices from the user  and prints out
     *   the maximum, minimum, and average price over the day
     *   the opening and closing prices,
     *   the amount the price rose or fell,
     * The sequence is terminated by any word (non-number) such as "close" or "end".
     * All the prices are in cents (as positive integers) 
     * The method must keep track of the maximum and minimum prices, the count and
     * sum of the prices, and the opening price.
     * It will need variables for each of these quantities, all of which 
     * need to be initialised to an appropriate value.
     * It will need a loop to keep reading the prices until there isn't an integer next.
     * [Core]
     *   - There is guaranteed to be at least one price,
     *   - You only need to print the maximum, minimum, and average price
     *   - Hint, keep track of the sum of the prices using a variable of type double
     * [Completion]
     *   - Print the opening and closing prices as well, along with the rise/fall.
     *   - The method should work even if there were no prices for the day
     * [Challenge] The method should also compute and print out
     *   - the standard deviation of the prices
     *   - the average size of the change between prices (where a rise of 10c is
     *     the same size change as a fall of 10c)
     *   - the second highest and second lowest price
     */
    public void analysePrices() {
        UI.clearText();

        // Initialise variables
        
        // Prompt for input
        UI.print("Part (a)\n Enter prices; end with 'close': ");
        if(!UI.hasNextInt()){
            UI.println("There were no prices");
            return;
        }
        int price = UI.nextInt();
        int max = price;
        int min = price;
        int opening = price;
        int count = 1;
        double sum = price;
        // Loop, reading numbers and updating variables
        while(UI.hasNextInt()){
            price = UI.nextInt();
            count += 1;
            sum += price;
            if(price > max){
                max = price;
            }
            if(price < min){
               min = price; 
            }
            
        }
        UI.println("--------- Analysis-------");
        double mean = sum/count;
        UI.printf("Minimum price was: %dc\n", min);
        UI.printf("Maximum price was: %dc\n", max);
        UI.printf("Average price was: %7.2fc\n", mean);
        UI.println(" Completion: ");
        UI.printf("Opening price was: %dc\n", opening);
        UI.printf("Closing price was: %dc\n", price);
        if(opening>price){
            UI.printf("Price fell by: %dc\n", opening-price);
        }else{
            UI.printf("Price rose by: %dc\n", price-opening);
        }
        // Compute and print out the analysis

        /*# YOUR CODE HERE */
        

        UI.nextLine(); // to clear out the input
    }



    /**
     * Reads a sequence of prices (integers) from the user (using Scanner
     * and the terminal window) and plots a bar graph of them, using narrow 
     * rectangles whose heights are equal to the price.
     * The sequence is terminated by any word (non-number) such as "close" or "end".
     * The method may assume that there are at most 24 numbers.
     * The method will need a loop to keep reading the prices until there isn't a number next.
     *  Each time round the loop, it needs to read the next price and work out where
     *  to draw the rectangle for the bar. 
     * [Core]
     *   - assume that all the numbers are between 0 and 380
     * [Completion]
     *   - Any price greater than 380 should be plotted 
     *      as if it were just 380.
     *   - The graph should also have a horizontal green line at the 
     *      height of the opening price and a red line at the height of the closing price.
     * [Challenge:] 
     *   - The graph should also have labels on the axes, roughly every 50 pixels
     *   - Make the method ask for a maximum price first, then scale the y-axis and values 
     *     so that the largest numbers just fit on the graph.
     *   - The numbers on the y axis should reflect the scaling.
     */
    public void plotPrices() {
        UI.clearText();
        UI.clearGraphics();
        //  Initialise variables and prompt for input
        // Loop, reading numbers and drawing bars
        /*# YOUR CODE HERE */
        int base = 400;
        int left = 50;
        int step = 25;
        UI.drawLine(left, base, (left+(24*step)+20), base);
        UI.print("Part (b) Core\nEnter prices, end with 'close': ");
        
        int x = left;
        while(UI.hasNextInt()){
            int price = UI.nextInt();
            UI.fillRect(x, base-price, step-2,price);
            x+= step;
        }
        UI.nextLine(); // to clear out the input
        UI.println("Done");
    }
    
    public void plotPricesCompletion(){
        UI.print("Part (b) Core\nEnter prices, end with 'close': ");
        if(!UI.hasNextInt()){
            UI.println("No prices");
            return;
        }
        int base = 400;
        int left = 50; 
        int step = 25;
        int right = (left + (24 * step) + 20);
        int maxValue = 380;
        
        UI.drawLine (left, base, right, base);
        int price = -1;
        int open = -1;
        int x = left;
        while(UI.hasNextInt()){
            price = UI.nextInt();
            if (open == -1){ open = price;}
            if (price > maxValue){
                UI.fillRect(x, base-maxValue, step - 2, maxValue);
                UI.drawString("*", x+step/3, base-maxValue-5);
            }else{
                UI.fillRect(x, base - price, step-2, price);
            }
            x+=step;
            }
            UI.setColor(Color.green);
            UI.drawLine(left, base-open, right, base-open);
            UI.setColor(Color.red);
            UI.drawLine(left, base-price, right, base-price);
            
            UI.nextLine();
            UI.println("Done");
        }
        
    }






