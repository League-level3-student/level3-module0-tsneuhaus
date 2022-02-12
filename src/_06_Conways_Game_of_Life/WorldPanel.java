/*******************************************
 * NOT FINISHED
 * Q: Is cellsPerRow the same as per column?  i.e. square grid  YES
 * 
******************************************* */

package _06_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
    private static final long serialVersionUID = 1L;
    private int cellsPerRow;
    private int cellSize;

    private Timer timer;
    private Random r;

    // 1. Create a 2D array of Cells. Do not initialize it.
    Cell[][] cells;


    public WorldPanel(int w, int h, int cpr) {
        setPreferredSize(new Dimension(w, h));
        addMouseListener(this);
        timer = new Timer(500, this);
        this.cellsPerRow = cpr;
        r = new Random();

        // 2. Calculate the cell size.
//... Cell.java assumes each cell is square!
        cellSize = w/cpr;
        
        // 3a. Initialize the cell array to the appropriate size.  Array is square.
      
        cells = new Cell[cellsPerRow][cellsPerRow];

        // 3b. Iterate through the array and initialize each cell.
        //    Don't forget to consider the cell's dimensions when 
        //    passing in the location.
  // INSTEAD, in paintComponents I multiplied x and y by cell dimensions
        for (int i = 0; i < cells.length; i++) {
        	for (int j = 0; j < cells[i].length; j++) {
        		cells[i][j] = new Cell(i,j,cellSize);
        	}
        }

    }

    public void randomizeCells() {
        // 4. Iterate through each cell and randomly set each
        //    cell's isAlive memeber to true of false
 
       	for (Cell[] row : cells) {
    		for (Cell c : row) {
    			int ran = r.nextInt(2);
    			if (ran == 0)
    				c.isAlive = false;
    			else
    				c.isAlive = true;
    		}
    	}
//System.out.println("randomizeCells()");
       	repaint();
    }

    public void clearCells() {
        // 5. Iterate through the cells and set them all to dead.
    	for (Cell[] row : cells) {
    		for (Cell c : row) {
    			c.isAlive = false;
    		}
    	}
//System.out.println("clearCells()");
        repaint();
    }

    public void startAnimation() {
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }

    public void setAnimationDelay(int sp) {
        timer.setDelay(sp);
    }

    @Override
    public void paintComponent(Graphics g) {
        // 6. Iterate through the cells and draw them all
        for (int i = 0; i < cells.length; i++) {
        	for (int j = 0; j < cells[i].length; j++) {
        		Cell c = cells[i][j];
        		if (c.isAlive) {
        			g.setColor(Color.BLUE);		// that's the color used in the jar file version
        		}
        		else {
        			g.setColor(Color.LIGHT_GRAY);
        		}
        		g.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
        			// like in Pixel_Art class GridPanel,
        			// x & y locations of each cell corner s/b multiplied by cellSize
        		// draw black boundary
        		g.setColor(Color.BLACK);
        		g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
        	}
        }

        // Draw the perimeter of the grid
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    // Advances world one step
    public void step() {
        // 7. iterate through cells and fill in the livingNeighbors array
        //    using the getLivingNeighbors method.
        int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
        for (int i = 0; i < livingNeighbors.length; i++) {
        	for (int j = 0; j < livingNeighbors[i].length; j++) {
        		livingNeighbors[i][j] = getLivingNeighbors(cells, i, j);
        	}
        }

        // 8. check if each cell should live or die
// q; how access rules for liveOrDie??
// IT'S IN Cell.java!!!  meanwhile, getLivingNeightors() is below 
// ...already filled a 'livingNeighbors' grid ins tep 7
        for (int i = 0; i < cells.length; i++) {
        	for (int j = 0; j < cells[i].length; j++) {
        		Cell c = cells[i][j];
    			c.liveOrDie(livingNeighbors[i][j]);
    		}
    	}        

        repaint();
    }

    // The method below gets the number of living neighbors around a
    // particular cell in the 2D array. A cell can have up to 8 neighbors.
    //        1   2    3
    //        4  cell  5
    //        6   7    8
    public int getLivingNeighbors(Cell[][] cells, int cellRow, int cellCol){
        int livingNeighbors = 0;
        
        if(cellRow != 0) {
            if(cells[cellRow - 1][cellCol].isAlive) livingNeighbors++;
        }
        
        if(cellRow != cellsPerRow - 1) {
            if(cells[cellRow + 1][cellCol].isAlive) livingNeighbors++;
        }
        if(cellCol != 0) {
            if(cells[cellRow][cellCol - 1].isAlive) livingNeighbors++;
        }
        if(cellCol != cellsPerRow - 1) {
            if(cells[cellRow][cellCol + 1].isAlive) livingNeighbors++;
        }
        if(cellRow != 0 && cellCol != 0) {
            if(cells[cellRow - 1][cellCol - 1].isAlive) livingNeighbors++;
        }
        if(cellRow != cellsPerRow - 1 && cellCol != cellsPerRow - 1) {
            if(cells[cellRow + 1][cellCol + 1].isAlive) livingNeighbors++;
        }
        if(cellRow != 0 && cellCol != cellsPerRow - 1) {
            if(cells[cellRow - 1][cellCol + 1].isAlive) livingNeighbors++;
        }
        if(cellRow != cellsPerRow - 1 && cellCol != 0) {
            if(cells[cellRow + 1][cellCol - 1].isAlive) livingNeighbors++;
        }
        
        return livingNeighbors;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // 9. Use e.getX() and e.getY() to get the mouse PIXEL (not cell)
        //    location that was clicked. Each cell is a square with a length of
        //    cellSize, meaning it's possible to click inside of a cell. You
        //    have to determine the cell that was clicked from the pixel
        //    location and toggle the 'isAlive' variable for that cell.
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	int cellX = mouseX/cellSize;	// divide by cellSize?
    	int cellY = mouseY/cellSize;
    	cells[cellX][cellY].isAlive = !cells[cellX][cellY].isAlive;	// toggle
// REALLY SHOULD HAVE METHOD IN Cell.java to do this, rather than isAlive being public!!!

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }
}
