package invetigaciónDeOperaciones;

import java.util.Arrays; 
import java.util.LinkedHashSet;
import java.util.Set;

public class HungarianSolver {

	 Menu menu = new Menu();
	 int[][] matrix; // initial matrix (our helper matrix)

	    // markers in the matrix
	    int[] squareInRow, squareInCol, rowIsCovered, columnIsCovered, staredZeroesInRow;

	    public HungarianSolver(int[][] matrix) {
	    

	        this.matrix = matrix;
	        squareInRow = new int[matrix.length];       // squareInRow & squareInCol indicate the position
	        squareInCol = new int[matrix[0].length];    // of the marked zeroes

	        rowIsCovered = new int[matrix.length];      // indicates whether a row is covered
	        columnIsCovered = new int[matrix[0].length];   // indicates whether a column is covered
	        staredZeroesInRow = new int[matrix.length]; // storage for the []
	        Arrays.fill(staredZeroesInRow, -1);
	        Arrays.fill(squareInRow, -1);
	        Arrays.fill(squareInCol, -1);
	    }

	  
	    public int[][] findOptimalAssignment() { 
	      
	        searchZeros();    // mark independent zeroes
	        createLines();    // cover columns which contain a marked zero
	        //we obtain an optimal assignment 
	        while (!allColumnsAreCovered()) {
	            int[] mainZero = findZerosnotCovered();
	            while (mainZero == null) {      // while no zero found 
	                secondChance();
	                mainZero = findZerosnotCovered();//it is called again to find a new zero
	            }
	            if (squareInRow[mainZero[0]] == -1) {//the new zero found is not marked
	                // there is no square mark(imaginary lines) in the mainZero line
	                obtainNewValues(mainZero);
	                createLines();    // cover columns which contain a marked zero
	            } else {
	            	//we found a zero and we didnt modified the matrix
	                // there is square mark in the mainZero line
	                // step 5
	                rowIsCovered[mainZero[0]] = 1;  // cover row of mainZero
	                //rowIsCovered[mainZero[0]] = 1;: It sets the value at index mainZero[0] in 
	                //the rowIsCovered array to 1, indicating that the row containing mainZero[0] is covered.
	                
	                columnIsCovered[squareInRow[mainZero[0]]] = 0;  // uncover column of mainZero
	                //colIsCovered[squareInRow[mainZero[0]]] = 0;: It sets the value at index squareInRow[mainZero[0]] 
	                //in the colIsCovered array to 0, indicating that the column containing the 
	                //"square" mark in the mainZero line is uncovered.
	                secondChance();
	                //continue the process of finding the optimal assignment by 
	                //iterating through the remaining elements in the matrix.
	            }
	            
	            
	        }
	        int rows = matrix.length;
	        int columns=rows;
	        menu.create3rdTable(rows,columns,matrix);
	        int[][] optimalAssignment = new int[matrix.length][];
	        for (int i = 0; i < squareInCol.length; i++) {
	            optimalAssignment[i] = new int[]{i, squareInCol[i]};
	        }
	        
	        //iterates over the squareInCol array, which contains the rows where the zeros 
	        //marked with squares are located in each column. For each column, a new array 
	        //of size 2 is created using new int[]{i, squareInCol[i]}, where i represents 
	        //the column index, and squareInCol[i] represents the corresponding row index 
	        //in the cost matrix.
	        //Then, this new array is assigned to the corresponding 
	        //row of the optimalAssignment array using 
	        //optimalAssignment[i] = new int[]{i, squareInCol[i]}
	        return optimalAssignment;

	    }

	    /*
	      Check if all columns are covered. If that's the case then the
	      optimal solution is found
	     
	      true or false
	     */
	    private boolean allColumnsAreCovered() {
	        for (int i : columnIsCovered) {
	            if (i == 0) {
	                return false;
	            }
	        }
	        return true;
	    }


	    /*
	    
	      mark each 0 with a "square", if there are no other marked zeroes in the same row or column
	     */
	    private void searchZeros() {
	        int[] rowHasSquare = new int[matrix.length];
	        int[] colHasSquare = new int[matrix[0].length];

	        for (int i = 0; i < matrix.length; i++) {
	            for (int j = 0; j < matrix.length; j++) {
	                // mark if current value == 0 & there are no other marked zeroes in the same row or column
	                if (matrix[i][j] == 0 && rowHasSquare[i] == 0 && colHasSquare[j] == 0) {
	                    rowHasSquare[i] = 1;
	                    colHasSquare[j] = 1;
	                    squareInRow[i] = j; // save the row-position of the zero
	                    squareInCol[j] = i; // save the column-position of the zero
	                    continue; // jump to next row
	                }
	            }
	        }
	    }

	    /*
	     
	      Cover all columns which are marked with a "square"
	     */
	    private void createLines() {
	        for (int i = 0; i < squareInCol.length; i++) {
	            columnIsCovered[i] = squareInCol[i] != -1 ? 1 : 0;
	            //if the columns has a different value than -1, that means there
	            //is a zero in that columns, so we need to set that the column is covered with 1
	            //but if in that position there is a 0, that means that col is not covered
	            //and can be used to repeat the process 
	        }
	    }

	    /**
	     * 
	     * 1. Find the smallest uncovered value in the matrix.
	     * 2. Subtract it from all uncovered values
	     * 3. Add it to all twice-covered values, this means there is a intersection
	     */
	    private void secondChance() {
	        // Find the smallest uncovered value in the matrix
	        int minUncoveredValue = Integer.MAX_VALUE;
	        for (int i = 0; i < matrix.length; i++) {
	            if (rowIsCovered[i] == 1) {
	                continue;
	            }
	            for (int j = 0; j < matrix[0].length; j++) {
	                if (columnIsCovered[j] == 0 && matrix[i][j] < minUncoveredValue) {
	                    minUncoveredValue = matrix[i][j];
	                }
	            }
	        }

	        if (minUncoveredValue > 0) {
	            for (int i = 0; i < matrix.length; i++) {
	                for (int j = 0; j < matrix[0].length; j++) {
	                    if (rowIsCovered[i] == 1 && columnIsCovered[j] == 1) {
	                        // Add min to all intersections values
	                        matrix[i][j] += minUncoveredValue;
	                    } else if (rowIsCovered[i] == 0 && columnIsCovered[j] == 0) {
	                        // Subtract min from all uncovered values
	                        matrix[i][j] -= minUncoveredValue;
	                    }
	                }
	            }
	        }
	    }

	    /*
	     
	     Find zero value Z_0 and mark it as "[]".
	     we search for a zero that is not covered
	      @return position of Z_0 in the matrix
	     */
	    private int[] findZerosnotCovered() {
	        for (int i = 0; i < matrix.length; i++) {
	            if (rowIsCovered[i] == 0) {
	                for (int j = 0; j < matrix[i].length; j++) {
	                    if (matrix[i][j] == 0 && columnIsCovered[j] == 0) {
	                        staredZeroesInRow[i] = j; // mark as []
	                        return new int[]{i, j};
	                    }
	                }
	            }
	        }
	        return null;
	    }

	    /**
	     * 
	     * Create a chain Positions of alternating "squares" and "[]"
	     *
	    
	     */
	    private void obtainNewValues(int[] mainZero) {
	    	//obtain the position of the z_0 of the findZerosnotCovered()
	        int i = mainZero[0];
	        int j = mainZero[1];

	        Set<int[]> Positions = new LinkedHashSet<>();
	        //Positions = chain
	        //(a)
	        // add Z_0 to Positions
	        Positions.add(mainZero);
	        boolean found = false;
	        do {
	            // (b)
	            // add Z_1 to Positions if(Z_1 zero already found)
	            // there is a zero Z_1 which is marked with a "square " in the column of Z_0
	            if (squareInCol[j] != -1) {//if the zero we found with findZerosnotCovered() 
	            	//is in a column that has already a square
	                Positions.add(new int[]{squareInCol[j], j});
	                found = true;
	            } else {
	                found = false;
	            }

	            // if no zero element Z_1 marked with "square" exists in the column of Z_0, then cancel the loop
	            if (!found) {
	                break;
	            }

	            // (c)
	            // replace Z_0 with the [] in the row of Z_1
	            i = squareInCol[j];
	            //we get the correspondent row to the columns j that has been found an element marked with a square
	            j = staredZeroesInRow[i];
	            //we get correspondent column to the row i, that has been found an element marked with a []
	            // add the new Z_0 to Positions(we add the position)
	            if (j != -1) {//we found an element marked with []
	                Positions.add(new int[]{i, j});//we add a new pair of values that represent the position
	                //that the new element found is in the matrix 
	                found = true;
	            } else {
	                found = false;
	            }

	        } while (found); // (d) as long as no new "square" marks are found
	        
	        // (e)
	        for (int[] zero : Positions) {
	            // remove all "square" marks in K
	            if (squareInCol[zero[1]] == zero[0]) {
	            	//This condition checks if the element at column zero[1] in the squareInCol 
	            	//array is equal to zero[0]. If it matches, it means that the element 
	            	//at position [zero[0], zero[1]] was previously marked with a "square".
	            	
	                squareInCol[zero[1]] = -1;//we set that are no squares marked 
	                squareInRow[zero[0]] = -1;
	            }
	            // replace the [] marks in Positions with "square" marks
	            if (staredZeroesInRow[zero[0]] == zero[1]) {
	            	//If it matches, it means that the element at position [zero[0], zero[1]] 
	            	//was previously marked with a "[]".
	                squareInRow[zero[0]] = zero[1];//we simply remove the marks 
	                squareInCol[zero[1]] = zero[0];
	            }
	        }
	        //every element on k means an assignment 
	        // (f)
	        // remove all marks
	        Arrays.fill(staredZeroesInRow, -1);
	        Arrays.fill(rowIsCovered, 0);
	        Arrays.fill(columnIsCovered, 0);
	        
	        
//Arrays.fill(staredZeroesInRow, -1);: It fills the staredZeroesInRow array with -1, indicating that no elements are marked with "0*".
// Arrays.fill(rowIsCovered, 0);: It fills the rowIsCovered array with 0, indicating that no rows are covered.
// Arrays.fill(colIsCovered, 0);: It fills the colIsCovered array with 0, indicating that no columns are covered.
	    }
	
	
}
