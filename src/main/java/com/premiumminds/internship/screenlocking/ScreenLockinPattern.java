package main.java.com.premiumminds.internship.screenlocking;

import java.util.concurrent.ExecutionException;

/*****************************************************************************************************
 * (Java Class) Rui Daniel
 * Last Modified: 23/05/2022
 *
 * Name:
 *  ScreenLockinPatternTest.java
 *
 * Description:
 *   Implementation of the calculation of the number of possible screen lock patterns 
 *   knowing the starting point and path's length.
 *
 * Comments : 
 * 			
 *****************************************************************************************************/

import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2022.
 */
class ScreenLockinPattern implements IScreenLockinPattern {

 /**
  * Method to count patterns from firstPoint with the length
  * @param firstPoint initial matrix position
  * @param length the number of points used in pattern
  * @return number of patterns
  */
	
	/* public static void main(String[] args) {
		ScreenLockinPattern s = new ScreenLockinPattern();
		Future<Integer> answer = s.countPatternsFrom(3, 2);
		try {
			System.out.println(answer.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
	} */
	
	/* Calculates number of possible screen lock patterns 
	 * knowing the starting point and path's length */
	public Future<Integer> countPatternsFrom(int firstPoint, int count) {
	  /* Validates input */
		int length = count;
		Future<Integer> count1 = new future_int().generate(0);
		if (firstPoint < 1 || firstPoint > 9) {
			/* System.out.println("Invalid first parameter!\n"); */
			return count1;
		}
		else if (length < 1 || length > 9) {
			/* System.out.println("Invalid second parameter!\n"); */
			return count1;
		}
		int i = 0, j = 0;
		
		/* Java arrays start in 0 instead of 1 */
		firstPoint--;
	    
		/* Create a sample graph with 9 vertex all connected */
	    Graph g = new Graph(9);
	    for (i = 0; i < 9; i++) {
	    	for (j = 0; j < 9; j++) {
	    		if (i != j) {
	    			g.addEdge(i, j);
	    		}
		    }
	    }

	    /* Get number of paths from starting point to any of the other nodes */
	    for (i = 0; i < 9; i++) {
	    	try {
				count1 = new future_int().generate(count1.get() + g.countAllPaths(firstPoint, i, length));
			} catch (InterruptedException | ExecutionException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return count1;
	};
	
}
