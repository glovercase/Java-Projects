// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for Assignment 4, COMP 102 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
/**
 * A program that plays a guessing game in which the computer  "thinks of"
 * a number between 1 and 40 (or two numbers for the more advanced version),
 * and then gives the player a limited number of chances to guess the number(s). 
 * If the player guesses the number within the maximum number of guesses, they win points.
 * The program should prompt the player for each guess, and then display each guess
 * as a mark on a "number line" in the graphics pane,
 * It should also tell the player whether the guess is correct, or too low or too high.
 * 
 * For the simple (core) version, 
 *  The program will play rounds of the game until the player says they have had enough.
 *  For each round, the player has 5 chances to guess the number.
 *  After each guess, the program says whether the guess is correct, too low, or too high.
 *  If the player guesses the number within the 5 chances, the player gains a point
 *   otherwise, the player loses a point (but the score never goes below 0).
 *  At the end, the program reports the player's score
 *  To get full marks for core,  the program should also show each guess
 *   on a display on the graphics pane.
 *
 * For the completion version,
 *  - The program should think of two (different) numbers each round and must
 *    say whether a wrong guess is less than the smaller number, greater
 *    than the larger number, or between the numbers.
 *    The player only wins the round if they guess both numbers (not if they
 *     guess one number twice!)
 *    The program should still display the guesses on the graphics pane.
 *    The program should disallow any guesses outside the range 1 to 40, and make the
 *      player enter the guess again.
 *  - The player gains 1 point for the round if they guess both numbers, plus
 *    an extra point for each remaining guess that they didn't use up.  The player
 *    loses a point if they didn't guess either number (though the score still
 *    never goes below 0).
 * 
 * For the challenge version,
 *  - The program should display the regions of the number line that are now
 *    impossible. For example, if the player guesses 6 and the program says that
 *    this is less than both numbers, then the numbers couldn't be between 1 and 6,
 *    so the program should display that region of the line as "impossible".
 *  - Even better (though this requires more features of Java than the lectures have
 *    covered so far) the program should not allow the player to make a guess that
 *    is in any of the impossible regions (ie, it should tell the user that it is
 *    impossible and make them enter a valid guess).
 *  - Rig the game so that the player will always win at the very last possible
 *    guess. 
 */
public class GuessingGame{

    public static final int maxValue = 40;
    public static final int maxGuesses = 5;


    // used for displaying the result in the Core
    public static final int barLeft = 50;
    public static final int barTop = 100;
    public static final int size = 10;   // width of each number on the bar.

    /** CORE
     * Plays one round of the one-number version of the guessing game
     * Computer randomly chooses an integer between 1 and maxValue (40)
     * Draws a horizontal line in the graphics pane labeled with 1 at one end
     *  and maxValue at the other end.
     * Player is offered up to maxGuesses chances to guess this number
     *    Whenever they guess outside the range 1 to maxValue, it asks them to try again
     *    Displays their guess on the line by drawing a bar at the appropriate place
     *     and putting the number above it.
     *    Tells Player if their guess is higher or lower than the answer
     *    Tells the player when they have guessed correctly and stops if they win
     * Returns true if the player guessed correctly, else returns false.
     */
    public boolean playRound(){
        /*# YOUR CODE HERE */
        int comp = 1+(int) (Math.random()*maxValue);
        Trace.println("computer chose "+comp); //Debugging
        
        UI.println("I've thought of a number betweeen 1 and " + maxValue +"; can you guess it? ");
        UI.println("You have " + maxGuesses + " chances. ");
        this.displayBar();
        int attempt = 1;
        while(attempt <= maxGuesses){
            int guess = UI.askInt("Guess " + attempt + " of "+maxGuessess+ ": ");
            //check the guess
            if(guess == comp){
                this.displayGuess(guess, Color.red);
                UI.println("Yes, you win, with "+attempt+ " guesses");
                return true;
            } else if(guess > comp){
                this.displayGuess(guess, Color.blue);
                UI.println("No, that guess was too high");
               
            }else{
             this.displayGuess(guess, Color.green);
             UI.println("No, that guess was too low");
            }
            attempt +=1;
            
        }
        UI.println("That's all your guesses; you lose");
        UI.println("The number was "+comp);
        return false;
    }

    public void modifyArray(int [ ] data){
        
        int n=0;
        UI. println (data [3]);
        UI. println (data[n+2]);
        UI. println (data[data[n ]]);
        for( int i=0; i<data.length; i++){
            if ( data[ i ] < 10){
                data[ i ] = data[ i ] + 10;
            }
        }
        for( int i=0; i<data.length; i++){
            UI. println (data[ i ]);
        }
    }


    /** CORE
     * Plays the guessing game.
     * Prints an introduction, then
     * repeatedly calls playRound to play a single round of the game,
     * keeping track of the score.
     * After each round, it asks if they want to play again.
     * When finished it prints the score (how many times they guessed
     * correctly and how many rounds they tried).
     */
    public void playGame(){
        UI.initialise();
        UI.println("-----CORE GAME-----------");
        UI.println("Hello, let's play the guessing game");
        UI.println("Each round, you must guess my number;");
        /*# YOUR CODE HERE */
        int score = 0;
        int count = 0;
        while (true){
            if(this.playRound()){
                score += 1;
            }else{
                score -= 1;
            }
            if(score < 0){
                score = 0;
            }
            count += 1;
            UI.println("----------------------");
            if(!UI.askBoolean("Play again?")){Break;}
            
        }
        UI.println("----------------------");
        UI.println("Your score was "+score+" time out of "+count+" games");
    }

    /**
     * Displays the bar.
     * Clears the graphics pane then draws line labeled with 1 and maxValue at the ends
     */
    public void displayBar(){
        /*# YOUR CODE HERE */
        UI.clearGraphics();
        int width = maxValue * size;
        UI.setColor(Color.black);
        UI.fillRect(barLeft,barTop,width, 5);
        UI.drawString("1", barLeft, barTop+10);
        UI.drawString(""+maxValue, barLeft+width, barTop-10);
    }

    /**
     * Displays a guess on the bar, using the specified color.
     * Clears the graphics pane then draws line labeled with 1 and maxValue at the ends
     */
    public void displayGuess(int guess, Color col){
        /*# YOUR CODE HERE */
        int x = barLeft + ((guess-1) * size);
        UI.setColor(col);
        UI.fillRect(x+2, barTop-10, size-4, 15);
        UI.drawString(""+guess, x, barTop-15);
        UI.setColor(Color.black);
    }


    /** playRound: COMPLETION
     * Computer randomly chooses two different numbers between 1 and maxValue
     * Draws a horizontal line in the graphics pane labeled with 1 at one end
     *  and maxValue at the other end.
     * User is offered up to maxGuesses chances to guess these two numbers 
     *  Whenever they guess outside the range 1 to maxValue, it asks them to try again
     *  Displays their guess on the line by drawing a bar at the appropriate place
     *  After each guess, it tells the user whether  their guess is:
     *  - correct,
     *  - higher than both numbers,
     *  - lower than both numbers, or
     *  - between the two numbers.
     * Stops when they have used up all their guesses, or have guessed both numbers correctly
     * It returns a score which is
     *  -1, if they guessed neither number,
     *  0, if they guessed just one number
     *  1 plus the number of remaining guesses that they didn't
     *    use, if they guessed both numbers
     */
    public int playRoundCompletion(){
        return 0;
    }

    /** playGame:  COMPLETION  
     * Plays a better version of the guessing game.
     * Prints an introduction, then
     * repeatedly calls playRound to play a single round of the game,
     * keeping track of the score.
     * After each round, it asks if they want to play again.
     * When finished it prints the score and how many rounds they went through
     */
    public void playGameCompletion(){
        GuessingGame game = new GuessingGame();
        UI.initialise();
        game.playGame();
    }



    public static void main(String[] arguments){
        int [ ] data = new int [ ]{4, 3, 5, 2, 16, 4, 20};

    }        

}
