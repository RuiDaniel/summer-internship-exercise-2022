package main.java.com.premiumminds.internship.screenlocking;

/*****************************************************************************************************
 * (Java Class) Rui Daniel
 * Last Modified: 23/05/2022
 *
 * Name:
 *  Graph.java
 *
 * Description:
 *   Implementation of a directed Graph using an adjacency list representation, and
 *   some operations.
 *
 * Comments : I get most of this code in 
 * 			  https://www.geeksforgeeks.org/find-paths-given-source-destination/
 * 			  There are some methods with the objective of detecting a valid Screen 
 * 			  Lock Pattern sequence 
 * 			
 *****************************************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
 
public class Graph {
 
    /* No. of vertices in graph */
    private int v;
 
    /* Adjacency list */
    private ArrayList<Integer>[] adjList;
    
    /* Counts the number of valid sequences */
    private int counter = 0;
    
    /* Constructor: initialize v and adjacency list */
    public Graph(int vertices) {
        this.v = vertices; 
        initAdjList();
    }
 
    /* Initialize adjacency list for each vertex */
    @SuppressWarnings("unchecked")
    private void initAdjList() {
        adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
 
    /* Add edge from u to v */
    public void addEdge(int u, int v) {
        /* Add v to u's list */
        adjList[u].add(v);
    }
    
    /* Remove edge from u to v */
    public void removeEdge(int u, int v) {
    	/* Search v in u's list and remove */
    	for (int i = 0; i < adjList[u].size(); i++) {
    		if (v == adjList[u].get(i)) {
    			adjList[u].remove(i);
    		}
    	}
    }
 
    /* Gets all valid screen lock paths from 's' to 'd' */
    public int countAllPaths(int s, int d, int length) {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();
        counter = 0;
        
        /* Add source to path[] */
        pathList.add(s);
                
        /* Call recursive utility */
        counter += countAllPathsUtil(s, d, isVisited, pathList, length);
        
        return counter;
    }
 
    /* A recursive function to get all paths from 'u' to 'd'.
     * isVisited[] keeps track of vertices in current path.
     * localPathList<> stores actual vertices in the current path */
    private int countAllPathsUtil(Integer u, Integer d, boolean[] isVisited,
                                  List<Integer> localPathList, int length) {
    	int aux_counter = 0, aux0 = -1, aux1 = -1;
    	
    	/* If our recursive method leads to the end of a path: u = d */
    	if (u.equals(d)) {
    		/* Checks if the size of the obtained path has the pretended length */
        	if (localPathList.size() == length) {
        		boolean[] isVisited2 = new boolean[9];
        		/* isVisited2 objective is to check the vertex already 
        		 * traveled in the path's sweep */
        		for (int i = 0; i < 9; i++) {
        			isVisited2[i] = false;
        		}
        		ListIterator<Integer> it = localPathList.listIterator();
        		aux0 = -1;
        		aux1 = -1;
        		/* Checks if the path is valid, comparing each connection */
        		while (it.hasNext()) {
        			aux1 = it.next();
        			if (aux0 != -1) {
        				isVisited2[aux1] = true;
        				isVisited2[aux0] = true;
        				if (!ValidConnection(aux0, aux1, isVisited2)) {
        					/* If the path is invalid returns the value 
        					 * of counter without increment it */
        					return aux_counter;
        				}
        			}
        			aux0 = aux1;
                }
        		aux_counter++;
        		/* System.out.println(localPathList); */
        	}            
        }
    	
        /* Mark the current node as visited */
        isVisited[u] = true;
        
        /* Recur for all the vertices
         * adjacent to current vertex */

        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                /* Store current node in path[] */
                localPathList.add(i);
                aux_counter += countAllPathsUtil(i, d, isVisited, localPathList, length);
 
                /* Remove current node in path[] */
                localPathList.remove(i);
            }
        }
        isVisited[u] = false;
        return aux_counter;
    }
    
    /* Checks if a connection is valid 
     * For understanding the implementation, I created a two dimensional table
     * that relates the starting and the arrival node:
     * Y = Valid connection
     * N = Invalid connection
     * i = {1, 2, ..., 9}: Valid connection if the node i has already been visited 
     * 
     * start/arrival | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 
     *             1 | N | Y | 2 | Y | Y | Y | 4 | Y | 5 |
     *             2 | Y | N | Y | Y | Y | Y | Y | 5 | Y |
     *             3 | 2 | Y | N | Y | Y | Y | 5 | Y | 6 |
     *             4 | Y | Y | Y | N | Y | 5 | Y | Y | Y |
     *             5 | Y | Y | Y | Y | N | Y | Y | Y | Y |
     *             6 | Y | Y | Y | 5 | Y | N | Y | Y | Y |
     *             7 | 4 | Y | 5 | Y | Y | Y | N | Y | 8 |
     *             8 | Y | 5 | Y | Y | Y | Y | Y | N | Y |
     *             9 | 5 | Y | 6 | Y | Y | Y | 8 | Y | N | */
    private boolean ValidConnection(int a, int b, boolean[] isVisited) {
    	if (a == b) {
    		return false;
    	}
    	switch (a) {
	        case 1 - 1:
		          if (b == 3 - 1 && !isVisited[2 - 1]) {
		        	  return false;
		          }
		          else if (b == 7 - 1 && !isVisited[4 - 1]) {
		        	  return false;
		          }
		          else if (b == 9 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          break;
	        case 2 - 1:
		          if (b == 8 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          break;
	        case 3 - 1:
		          if (b == 1 - 1 && !isVisited[2 - 1]) {
		        	  return false;
		          }
		          else if (b == 7 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          else if (b == 9 - 1 && !isVisited[6 - 1]) {
		        	  return false;
		          }
		          break;
	        case 4 - 1:
		          if (b == 6 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          break;
	        case 6 - 1:
		          if (b == 4 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          break;
	        case 7 - 1:
		          if (b == 1 - 1 && !isVisited[4 - 1]) {
		        	  return false;
		          }
		          else if (b == 3 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          else if (b == 9 - 1 && !isVisited[8 - 1]) {
		        	  return false;
		          }
		          break;
	        case 8 - 1:
		          if (b == 2 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          break;
	        case 9 - 1:
		          if (b == 1 - 1 && !isVisited[5 - 1]) {
		        	  return false;
		          }
		          else if (b == 3 - 1 && !isVisited[6 - 1]) {
		        	  return false;
		          }
		          else if (b == 7 - 1 && !isVisited[8 - 1]) {
		        	  return false;
		          }
		          break;      
	      }
    	return true;
    }
}
