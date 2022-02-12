package _01_Simple_Array_Algorithms;

public class _00_1D_Array_Methods {
    //1. Complete the method so that it returns the sum of all
    //   of the integers in the array being passed in
    public static int sumIntArray(int[] values) {

        int sum = 0;
        for (int val : values) {
        	sum += val;
        }
    	return sum;
    }

    //2. Complete the method so that it returns the average of all
    //   of the integers in the array being passed in
    public static double averageIntArray(int[] values) {
    	int sum = sumIntArray(values);
    	
        return (double) sum / values.length;
    }


    //3. Complete the method so that it returns true if the integer
    //   array contains the value specified by the second parameter.
    //   It should otherwise return false.
    public static boolean containsIntValue(int[] array, int value) {
    	boolean answer = false;
    	for (int val : array) {
    		if (val == value) {
    			answer = true;
    		}
    	}
        return answer;
    }

    //4. Complete the method so that it returns the index of the,
    //   first instance that the specified value occurs in the array.
    //   If the array does not contain the specified value, it should return -1.
    public static int getIndex(int[] arr, int value) {
  
    	// (1) can't use for-each loop here
    	// (2) watch that if multiple instances of 'value', it returns index of FIRST instsance
    	for (int i = 0; i < arr.length; i++) {
    		if (arr[i] == value) {
    			return i;		// exit early
    		}
    	}
        return -1;	// value never found
    }
}
