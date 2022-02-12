package _07_The_Wrong_Way_Cow;

public class Notes {
	
	final char[][] field = new char[5][5];
	
	   public static boolean horizRightCowTest (char[][] field, int r, int c) {
		   if (c < (field[r].length - 2) && field[r][c] == 'c' && field[r][c+1] == 'o' && field[r][c+2] == 'w') {
			   return true;
		   }
		   return false;	// not RIGHT
	   }
	   
	   void notes() {
			for (int i = 0; i < field.length; i++) {
	    		for (int j = 0; j < field[i].length; j++) {
	    			//.Notes..
	    				if (horizRightCowTest(field, i, j)) {}
	    					
	    		}
			}
	   }

}
