//******************************************************************************//
// I'm not sure if I implemented the code correctly or got the intended results when
// I ran the program.  Especially STEP 5.
//******************************************************************************//

package _05_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GridPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private int windowWidth;
    private int windowHeight;
    private int pixelWidth;
    private int pixelHeight;
    private int rows;
    private int cols;

    // 1. Create a 2D array of pixels. Do not initialize it yet.
    Pixel[][] pixels;

    private Color color;

    public GridPanel(int w, int h, int r, int c) {
        this.windowWidth = w;
        this.windowHeight = h;
        this.rows = r;
        this.cols = c;

        this.pixelWidth = windowWidth / cols;
        this.pixelHeight = windowHeight / rows;
        
     // Tried running with screen size 400 x 400, and row-col grid 10 x 10
     // ... then pixeelWidth/Height is 40; windowWidth/Height is 400
     //        System.out.println("Pixel dim: " + pixelWidth + " " + pixelHeight);
     //	System.out.println("GridPanel dim: " + windowWidth + " " + windowHeight);

        color = Color.BLACK;

        setPreferredSize(new Dimension(windowWidth, windowHeight));

        // 2. Initialize the pixel array using the rows and cols variables.
        pixels = new Pixel[rows][cols];


        // 3. Iterate through the array and initialize each element to a new pixel.
 // Q: what use for new Pixel's x & y values??  loop control variables i and j?
 // A: that's one way ... if you do this, then you need to multiple by pixelWidth/Height
 //		in paintComponents .. 
// (CURRENT COD) easier to do that *once* here, thus storing the upper left
 // 	corner of pixel square in GridPanel
       	for (int i = 0; i < pixels.length; i++) {
    		for (int j = 0; j < pixels[i].length; j++)  {
        		pixels[i][j] = new Pixel(i*pixelWidth, j*pixelHeight);
        	}
        }

    }

    // NOTE: called by mousePressed in PixelArtMaker
    public void setColor(Color c) {
        color = c;
    }

    // NOTE: called by mousePressed in PixelArtMaker; use 'color' instance variable
    // that was updated in setColor() method above.  color is controlled by ColorSelectionPanel
    public void clickPixel(int mouseX, int mouseY) {
        // 5. Use the mouseX and mouseY variables to change the color
        //    of the pixel that was clicked. *HINT* Use the pixel's dimensions.
 
    	int rowNum = mouseY/pixelHeight;
    	int colNum = mouseX/pixelWidth;
    	System.out.println("pixel row/col: " + rowNum + " " + colNum);
 //Q: For some reason, I've swapped row and col!! WHY??
 //   	Pixel chosen = pixels[rowNum][colNum];		// *should* work
    	Pixel chosen = pixels[colNum][rowNum];		// why do I need to do this swap?
    	chosen.color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        // 4. Iterate through the array.
        //    For every pixel in the list, fill in a rectangle using the pixel's color.
        //    Then, use drawRect to add a grid pattern to your display.
// NOTE: If in step 3 above, only stored the indices of each pixel in pixels[][],
// then in g.fillRect() and g.drawRect(), need to multiply p.x*pixelWidth, p.y*pixelHeight
       	for (int i = 0; i < pixels.length; i++) {
    		for (int j = 0; j < pixels[i].length; j++)  {
    			Pixel p = pixels[i][j];
    			// draw a filled rect with given color
        		g.setColor(p.color);
        		g.fillRect(p.x, p.y, pixelWidth, pixelHeight);
        		// draw rect boundary in black
        		g.setColor(Color.BLACK);
        		g.drawRect(p.x, p.y, pixelWidth, pixelHeight);
//        		System.out.println("paint pixel size: " + p.x + " " + p.y + " " + 
//        								pixelWidth + " " + pixelHeight);;
        	}
        }   	

    }
}
