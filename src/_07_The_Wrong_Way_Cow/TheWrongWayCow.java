/*
 * https://www.codewars.com/kata/the-wrong-way-cow
 * 
 * Task
 * Given a field of cows find which one is the Wrong-Way Cow and return her
 * position.
 * 
 * Notes:
 * 
 * There are always at least 3 cows in a herd
 * There is only 1 Wrong-Way Cow!
 * Fields are rectangular
 * The cow position is zero-based [col,row] of her head (i.e. the letter c)
 * Examples
 * Ex1
 * 
 * cow.cow.cow.cow.cow
 * cow.cow.cow.cow.cow
 * cow.woc.cow.cow.cow
 * cow.cow.cow.cow.cow
 * Answer: [6,2]
 * 
 * Ex2
 * 
 * c..........
 * o...c......
 * w...o.c....
 * ....w.o....
 * ......w.cow
 * Answer: [8,4]
 * 
 * Notes
 * The test cases will NOT test any situations where there are "imaginary" cows,
 * so your solution does not need to worry about such things!
 * 
 * To explain - Yes, I recognize that there are certain configurations where an
 * "imaginary" cow may appear that in fact is just made of three other "real" cows.
 * 
 * In the following field you can see there are 4 real cows (3 are facing south and
 * 1 is facing north). There are also 2 imaginary cows (facing east and west).
 * 
 * ...w...
 * ..cow..
 * .woco..
 * .ow.c..
 * .c.....
*/

package _07_The_Wrong_Way_Cow;

public class TheWrongWayCow {

   	final static int RIGHT = 0;
	final static int LEFT = 1;
	final static int UP = 2;
	final static int DOWN = 3;
	
    public static int[] findWrongWayCow(final char[][] field) {
    	System.out.println("CALL FIND_WRONG_WAY_COW");
    	
    	int[] numCowsInDirection = new int[4];
    			// numCowsInDirection[0] represents RIGHT; etc
    	
        // Fill in the code to return the [col, row] coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	
    	int horiz = 0, vert = 0;	// temp variables
    	
    	// visit every cell - test horizontally and vertically
    	for (int i = 0; i < field.length; i++) {
    		for (int j = 0; j < field[i].length; j++) {
 //   			System.out.println("visit cell " + i + " " + j);
    			horiz = horizontalCowTest(field, i, j);
    			if (horiz != -1) {
    				numCowsInDirection[horiz]++;		// RIGHT or LEFT
    			}
    			vert = verticalCowTest(field, i, j);
    			if (vert != -1) {
    				numCowsInDirection[vert]++;		// UP or DOWN
    			}
    		}
    	}
//    	System.out.println("HELLO");
//    	System.out.println("num in direction R/L/U/D: " + numCowsInDirection[0] + " " +
//    			numCowsInDirection[1] + " " + numCowsInDirection[2] + " " + numCowsInDirection[3]);
    	
    	
    	// (there always are at least 3 cows); there always is one wrong way;
    	// figure out which direction is the wrong way
    	int wrongWay = -1;
    	if (numCowsInDirection[RIGHT] == 1) {
    		wrongWay = RIGHT;
    	}
    	else if (numCowsInDirection[LEFT] == 1) {
    		wrongWay = LEFT;
    	}
    	else if (numCowsInDirection[UP] == 1) {
    		wrongWay = UP;
    	}
    	else {
    		wrongWay = DOWN;
    	}
    //    	System.out.println("wrong way: " + wrongWay);
    	
    	// revist field to find the location of the head of the wrong way cow and return it		
        return locationWrongCow(field, wrongWay);
    }
    
    public static int[] locationWrongCow (char[][] field, int wrongWay) {
    	// vist every cell - look in direction specified by wrongWay;
    	// once found, method exits
    	for (int i = 0; i < field.length; i++) {
    		for (int j = 0; j < field[i].length; j++) {
    			if (wrongWay == RIGHT) {
    				if (horizRightCowTest(field, i, j)) {
    					System.out.println("row/col head loc: " + i + " " + j);
    					return cowHead(i,j);		// break!
    				}
    			}
    			else if (wrongWay == LEFT) {
    				if (horizLeftCowTest(field, i, j)) {
     					System.out.println("row/col head loc: " + i + " " + j);
    					return cowHead(i,j);		// break! 
    				}
    			}
    			else if (wrongWay == UP) {
    				if (vertUpCowTest(field, i, j)) {
     					System.out.println("row/col head loc: " + i + " " + j);
    					return cowHead(i,j);		// break!
    				}
    			}
    			else {		// DOWN
    				if (vertDownCowTest(field, i, j)) {
     					System.out.println("row/col head loc: " + i + " " + j);
    					return cowHead(i,j);		// break!
    				}
    			}
    		}
    	} 
    	return null;		// shouldn't happen...
    }
    	
    public static int[] cowHead(int r, int c) {
    	int[] locationOfHead = new int[2];
    	locationOfHead[0] = c;		// col
    	locationOfHead[1] = r;		// row
 // at first, I thought it was supposed to be stored in opposite order!!
    	return locationOfHead;
    }
    
    public static int horizontalCowTest (char[][] field, int r, int c) {
    	boolean result1 =  horizRightCowTest(field, r, c);
    	boolean result2 =  horizLeftCowTest(field, r, c);
 
    	if (result1) {
    		return RIGHT;
    	}
    	else if (result2) {
    		return LEFT;
    	}
    	// neither
    	return -1;
    }
    
   public static int verticalCowTest (char[][] field, int r, int c) {
	   boolean result1 =  vertUpCowTest(field, r, c);	   
	   boolean result2 =  vertDownCowTest(field, r, c);

	   if (result1) {
    		return UP;
    	}
    	else if (result2) {
    		return DOWN;
    	}
   		// neither    	
	   	return -1;
    }
    
   /**********************************************
    * In this set of methods, make sure do not
    * go out of bounds of 'field' array
    * ********************************************/
   
   public static boolean horizRightCowTest (char[][] field, int r, int c) {
	   if (c < (field[r].length - 2) && field[r][c] == 'c' && field[r][c+1] == 'o' && field[r][c+2] == 'w') {
		   return true;
	   }
	   return false;	// not RIGHT
   }
   
   public static boolean horizLeftCowTest (char[][] field, int r, int c) {
	   if (c >= 2 && field[r][c] == 'c' && field[r][c-1] == 'o' && field[r][c-2] == 'w') {
		   return true;
	   }
	   return false;	// not LEFT
   }
   
   public static boolean vertUpCowTest (char[][] field, int r, int c) {
	   
	   if (r >= 2 && field[r][c] == 'c' && field[r-1][c] == 'o' && field[r-2][c] == 'w') {
  			return true;
  		}
	   return false;	// not UP
   }
   
   public static boolean vertDownCowTest (char[][] field, int r, int c) {
 		if (r < (field.length - 2) && field[r][c] == 'c' && field[r+1][c] == 'o' && field[r+2][c] == 'w') {
   			return true;
   		}
	   return false;	// not DOWN
   }
   
   
   
}
