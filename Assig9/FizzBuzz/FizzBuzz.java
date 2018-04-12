import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class FizzBuzz
{

    
    public void FizzBuzz(int n, int number)
    {

        if(isPrime(number, n)){
           UI.println("Mike is Awesome");
        }
        else if(n %3 == 0 && n %5 == 0){
            UI.println("FizzBuzz");
        }else if(n %5 == 0){
            UI.println("Buzz");
        }else if(n %3 == 0){
            UI.println("Fizz");
        }else{
            UI.println(n); 
        }

        if(n == 100){
            return;
        }
        
        n ++;
        FizzBuzz(n, number);
    }

    public static boolean isPrime(int number, int n){
        
        
           
        if(number <= 2){
              number=2;
        }
                number++;

        if(n%number == 0){
            
            return false; 
        }

        return true;

               isPrime(number, n);

    }

    //Read more: http://javarevisited.blogspot.com/2012/04/java-program-to-print-prime-numbers-in.html#ixzz32ii8RS7H

    public static void main(){
        FizzBuzz ob = new FizzBuzz();
    }
}
