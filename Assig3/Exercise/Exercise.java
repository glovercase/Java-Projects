// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for Assignment 3 comp102 2012
 * Name: pondy
 */

import ecs100.*;
import java.awt.Color;


/** The code you should write is in MethodParametersExercise.java and
    NewObjectsExercise.java
    This class is just for the demo and to help you test your methods in the
    the other classes.
    Note that you do not need to understand or modify this code.
*/
public class Exercise implements UIButtonListener{

    public Exercise(){
        UI.addButton("NameTags", this);
        UI.addButton("SignalFlag", this);
        UI.addButton("BottleSong", this);
        UI.addButton("Flowers", this);
        UI.addButton("Balloons", this);
        UI.addButton("Bank Accounts", this);
        UI.addButton("Cars", this);
        UI.addButton("Student Marks", this);
        UI.addButton("Clear", this);
    }

    private MethodParametersExercise mpe = new MethodParametersExercise();
    private NewObjectsExercise noe = new NewObjectsExercise();

    public void buttonPerformed(String b){
        if (b.equals("Clear")){UI.clearText(); UI.clearGraphics();}

        else if (b.equals("NameTags")){mpe.nameTags();}
        else if (b.equals("SignalFlag")){mpe.signalZeroFlag();}
        else if (b.equals("BottleSong")){mpe.bottleSong();}

        else if (b.equals("Flowers")){noe.flowers();}
        else if (b.equals("Balloons")){noe.balloons();}
        else if (b.equals("Bank Accounts")){noe.bankAccounts();}
        else if (b.equals("Cars")){noe.cars();}
        else if (b.equals("Student Marks")){noe.studentMarks();}
    }

    public static void main(String[] args){
        new Exercise();
    }

}
