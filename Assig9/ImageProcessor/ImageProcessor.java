// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 9 
 * Name: Casey Glover   
 * Usercode: I worked with Chloe Graham 
 * ID: 300280613
 */ 

import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


/** ImageProcessor allows the user to display and edit a
  *  greyscale image in a number of ways.
  *  The program represents the image as a 2D array of integers, which must
  *   all be between 0 (black) and 255 (white).
  *  The class includes three methods (already written for you) that will
  *   - read a png or jpeg image file and store a 2D array of greyscale values
  *     into the image field.
  *   - render (display) the 2D arrray of greyscale values in the image field on the canvas
  *   - write the 2D array of greyscale values in the image field to a png file.
  *  
  *  You are to complete the methods to modify an image:
  *   - lighten the image
  *   - increase the contrast
  *   - Rotate the image 180 degrees and 90 degrees
  *   - flip the image horizontally or vertically.
  *   - merge another image with the current image
  *   - zoom in on the image (in two different ways)
  *
  *

 */
public class ImageProcessor implements UIButtonListener, UIMouseListener{
    // the current image (initialised to a very small 3x3 image)
    private int[][] image = new int[][]{{80,80,80},{80, 200, 80},{80,80,80}}; 
 
    // current selected point
    private int selectedRow = 0;
    private int selectedCol = 0;

    private final int pixelSize = 1;  // the size of the pixels as drawn on screen


    // Constructors
    /**
     * Construct a new ImageProcessor object
     * and set up the GUI
     */
    public ImageProcessor(){
        UI.setMouseListener(this);

        UI.addButton("Load", this);
        UI.addButton("Save", this);
        UI.addButton("Lighten", this);
        UI.addButton("Contrast", this);
        UI.addButton("Flip Horiz", this);
        UI.addButton("Flip Vert", this);
        UI.addButton("Rotate 180", this);
        UI.addButton("Rotate 90", this);
        UI.addButton("Merge", this);
        UI.addButton("Expand", this);
        UI.addButton("Zoom", this);
        UI.addButton("Quit", this);

        this.computeGreyColours();
    }

    /**
     * Respond to button presses
     */
    public void buttonPerformed(String cmd){
        if (cmd.equals("Load"))            { this.image = this.loadImage(UIFileChooser.open()); }
        else if (cmd.equals("Save"))       { this.saveImage();     }
        else if (cmd.equals("Lighten"))    { this.lightenImage();  }
        else if (cmd.equals("Rotate 180")) { this.rotateImage180();     }
        else if (cmd.equals("Flip Horiz")) { this.flipImageHorizontally();     }
        else if (cmd.equals("Flip Vert"))  { this.flipImageVertically();     }
        else if (cmd.equals("Rotate 90"))  { this.rotateImage90();     }
        else if (cmd.equals("Contrast"))   { this.contrastImage(); }
        else if (cmd.equals("Merge"))      { this.mergeImage();     }
        else if (cmd.equals("Expand"))     { this.expandImage();     }
        else if (cmd.equals("Zoom"))       { this.zoomImage(); }
        else if (cmd.equals("Quit"))       { UI.quit(); }

        this.selectedRow = 0;
        this.selectedCol = 0;
        this.redisplayImage();
    }

    /**
     * Respond to mouse events 
     */
    public void mousePerformed(String action, double x, double y) {
        if (action.equals("released")){
            this.setPos(x, y);
        }
    }

    /** CORE:
     * Make all pixels in the image lighter by 20 greylevels.
     * but make sure that you never go over 255 (maximum level - white).
     */
    public void lightenImage(){
        /*# YOUR CODE HERE */
        for(int row = 0; row < image.length; row++){ //loop through row
            for(int col = 0; col < image[row].length; col++){ //loop through col
                if(image[row][col] <= 235){ //if less or equal to 235
                    image[row][col] += 20; //add 20
                }else{
                  image[row][col]  = 255; //stop at 255
                }
            }
        }
    }  

    /** CORE:
     * Increase the contrast of the image -
     * make all lighter pixels in the image (above 128) even lighter (by 20%)
     * and make all darker pixels even darker (by 20%)
    */
    public void contrastImage(){
        /*# YOUR CODE HERE */
        int mid;
        double dist;
        for(int row = 0; row < image.length; row++){
            for(int col = 0; col < image[row].length; col++){
                if(image[row][col] >= 128 && image[row][col] <=233){ //conditions >= than 128 or <= than 233
                    mid = image[row][col] - 128; //midpoint 
                    dist = mid *0.2; //midpoint times 20%
                    image[row][col] = (int)Math.round(dist)+image[row][col]; //new color added to darken
                } else if(image[row][col] >223){
                    image[row][col] = 255; //keep in bounds
                }else if(image[row][col] < 128 && image[row][col] >= 22){
                    mid = 128 - image[row][col];
                    dist = mid * 0.2;
                    image[row][col] -= (int)Math.round(dist); //new color to lighten
                }
                
            }
        }

    } 


    /** CORE:
     * Flip the image vertically
     *   exchange the values on the top half of the image
     *   with the corresponding values on the bottom half
    */
    public void flipImageVertically(){
        /*# YOUR CODE HERE */
        int temp;
        for(int row = 0; row<image.length/2; row++){ 
            for(int col = 0; col< image[0].length; col++){
                temp=image[row][col];
                image[row][col] = image[image.length - row-1][col]; //less the row les 1 
                image[image.length - row-1][col] = temp; 
                
            }   
        }

    }

    /** CORE:
     * Flip the image horizontally
     *   exchange the values on the left half of the image
     *   with the corresponding values on the bottom half
    */
    public void flipImageHorizontally(){
        /*# YOUR CODE HERE */
        int temp;
        for(int row = 0; row < image.length; row++){
            for(int col = 0; col < image[0].length/2; col++){
                temp=image[row][col];
                image[row][col] = image[row][image[0].length-col-1]; //less the col les 1
                image[row][image[0].length-col-1] = temp;
            }
            
        }


    }

    /**  CORE:
     * Rotate the image 180 degrees
     * Each cell is swapped with the corresponding cell
     *  on the other side of the center of the images.
     * It is easier to make a new array, the same size as image, then
     *   copy each pixel in image to the right place in the new array
     *   and then assign the new array to the image field.
    */
    public void rotateImage180(){
        /*# YOUR CODE HERE */
        int[][] temp = new int[image.length][image[0].length]; //temp array
        int imageRow = 0;
        int imageCol = 0;
        for(int row = image.length; row>0; row--){
            for(int col =image[0].length; col>0; col--){
                temp[imageRow][imageCol] = image[row-1][col-1];
                imageCol ++;
            }
            imageCol=0;
            imageRow++;
        }
        image = temp;
        
    }

    
    /**  COMPLETION:
     * Rotate the image 90 degrees clockwise
     * Note, the resulting image will have different dimensions:
     *  the width of the new image will be the height of the old image.
     *  the height of the new image will be the width of the old image.
     * You will need to make a new image array of the new dimensions,
     *  fill it with the correct pixel values from the original array, 
     *  and then set the image field to contain the new image.
    */
    public void rotateImage90(){
         
        int [][] newimage = new int [image[0].length][image.length]; //switches the height and width
            for(int col = 0; col <image.length;   col++){
               for(int row =0 ; row <image[0].length ; row++){
                newimage[row][col] = image[image.length-(col+1)][row];   //less the col plus 1
            }
        }
       image = newimage; //reset
    }

    /** COMPLETION:
     * Expand the top left quarter of the image to fill the whole image
     * each pixel in the top left quarter will be copied to four pixels
     * in the new image.
     * Be careful not to try to access elements past the edge of the array!
     * Hint: It is actually easier to work backwards from the bottom right corner.
    */
    public void expandImage(){
        /*# YOUR CODE HERE */
        int expImage[][] = new int[image.length][image[0].length]; //temp array
        for (int row = 0 ; row < image.length ; row++){
            for( int col = 0 ; col < image[0].length ; col++){
                if (row*2 < image.length && col*2 < image[0].length){
                    expImage[row*2][col*2] = image[row][col]; //increase the size
                }
                  if (row*2+1 < image.length && col*2+1 < image[0].length){
                   expImage[row*2+1][col*2+1] = image[row][col];
                } 
                if (row*2 < image.length && col*2+1 < image[0].length){
                    expImage[row*2][col*2+1] = image[row][col];
                }
                if (row*2+1 < image.length && col*2 < image[0].length){
                    expImage[row*2+1][col*2] = image[row][col];     
                }
            }
        }
        image = expImage;
    }

    /** COMPLETION:
     * Merge two images 
     * Ask the user to select another image file, and load it into another array.
     *  Work out the rows and columns shared by the images
     *  For each pixel value in the shared region, replace the current pixel value
     *  by the average of the pixel value in current image and the corresponding
     *  pixel value in the other image.
    */
    public void mergeImage(){
        int [][] other = this.loadImage(UIFileChooser.open());
        int rows = Math.min(this.image.length, other.length);       // rows and cols
        int cols = Math.min(this.image[0].length, other[0].length); // common to both
        //only change image in region 0..rows-1, 0..cols-1
        /*# YOUR CODE HERE */
        for(int row = 0; row < rows; row++){ //less than the min of both
            for(int col = 0; col < cols; col++){
                image[row][col]=(image[row][col] + other[row][col])/2; //merge the two images
            }
        }
    }
        
 
    /** CHALLENGE:
     * Zoom in on the image, expanding by 133%, centered on the currently
     * selected pixel.
     * (The user can use the mouse to select a pixel which will be highlighted)
     * Hint: the selected pixel should stay where it is, and other pixels should be
     *  moved away from it by a factor or 4/3.
     *  Be careful not to try to access elements past the edge of the array!
     *  Be careful not to leave gaps in the image.
     *  Hint: It is easier to make a new array, copy the image over, expanding as you go
     *  and then assign the new array to the image field.
    */
    public void zoomImage(){
        /*# YOUR CODE HERE */
        int zoom [][] = new int[image.length][image[0].length];
        double x=0;
        double y=0;
        for (int row = 0 ; row < image.length ; row++){
            for( int col = 0 ; col < image[0].length ; col++){
                
                //selcted point
                //if point is near left/top/right/bottom edges only zoom from the oppiste side
                //if not out of bounds zoom in by 133%
                
                
            }
            
        }
        
        image = zoom;


    }




    //=========================================================================
    // Methods below here are already written for you -x = (double)row;
      //          y = (double)col;
      //          setPos(x,y);
    // for loading an image file into the image array,
    // for saving the image array into a file
    // for redisplaying the image array on the canvas.
    // for setting the mouse position

    /** field and helper methods to precompute and store all the possible grey colours,
        so the redisplay method does not have to constantly construct new color objects */
    private Color[] greyColors = new Color[256];

    /** Display the image on the screen with each pixel as a square of size pixelSize.
        To speed it up, all the possible colours from 0 - 255 have been precalculated.
    */
    public void redisplayImage(){
        UI.clearGraphics(false);
        for(int row=0; row<this.image.length; row++){
            int y = row * this.pixelSize;
            for(int col=0; col<this.image[0].length; col++){
                int x = col * this.pixelSize;
                UI.setColor(this.greyColor(this.image[row][col]));
                UI.fillRect(x, y, this.pixelSize, this.pixelSize, false);
            }
        }
        UI.setColor(Color.red);
        UI.drawRect(this.selectedCol*this.pixelSize,this.selectedRow*this.pixelSize,
                             this.pixelSize,this.pixelSize);
        UI.repaintGraphics();
    }

    /** Get and return an image as a two-dimensional grey-scale image (from 0-255). 
        This method will cause the image to be returned as a grey-scale image,
        regardless of the original colouration.
    */
    public int[][] loadImage(String imageName) {
        int[][] ans = null;
        if (imageName==null) return null;
        try {
            BufferedImage img = ImageIO.read(new File(imageName));
            UI.printMessage("loaded image height(rows)= " + img.getHeight() +
                            "  width(cols)= " + img.getWidth());
            ans = new int[img.getHeight()][img.getWidth()];
            for (int row = 0; row < img.getHeight(); row++){
                for (int col = 0; col < img.getWidth(); col++){
                    Color c = new Color(img.getRGB(col, row), true);
                    // Use a common algorithm to move to greyscale
                    ans[row][col] = (int)Math.round((0.3 * c.getRed()) + (0.59 * c.getGreen())
                                                    + (0.11 * c.getBlue()));
                }
            }
        } catch(IOException e){UI.println("Image reading failed: "+e);}
        return ans;
    }


    /**
       Write the current greyscale image to the specified filename
    */
    public  void saveImage() {
        // For speed, we'll assume every row of the image is the same length!
        int height = this.image.length;
        int width = this.image[0].length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int greyscaleValue = this.image[row][col];
                Color c = new Color(greyscaleValue, greyscaleValue, greyscaleValue);
                img.setRGB(col, row, c.getRGB());
            }
        }
        try {
            String fname = UIFileChooser.save("save to png image file");
            if (fname==null) return;
            File imageFile = new File(fname);
            ImageIO.write(img, "png", new File(fname));
        } catch(IOException e){UI.println("Image reading failed: "+e);}
    }

    private void computeGreyColours(){
        for (int i=0; i<256; i++){
            this.greyColors[i] = new Color(i, i, i);
        }
    }
    private Color greyColor(int grey){
        if (grey < 0){
            return Color.blue;
        }
        else if (grey > 255){
            return Color.red;
        }
        else {
            return this.greyColors[grey];
        }
    }


    /** Set the selected Row and Col to the pixel on the mouse position x, y */
    public void setPos(double x, double y){
        int row = (int)(y/this.pixelSize);
        int col = (int)(x/this.pixelSize);
        if (this.image != null && row < this.image.length && col < this.image[0].length){
            this.selectedRow = row;
            this.selectedCol = col;
            this.redisplayImage();
        }
    }



    // Main
    public static void main(String[] arguments){
        ImageProcessor ob = new ImageProcessor();
    }   


}
