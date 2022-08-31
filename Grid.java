
//Project Recording--> https://youtu.be/AlFIYm1stt8
import java.util.Random;

public class Grid {

	private boolean [][]bombGrid;
	private int [][]countGrid;
	private int numRows;	
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		createBombGrid();//10 rows, 10 columns, (10 x 10 Grid), 25 bombs
		createCountGrid();
	}
	
	public Grid(int someRows, int someColumns) {
		if(someRows>=0&&someColumns>=0) {
		numRows=someRows; 
		numColumns = someColumns;
		numBombs = 25; 
		createBombGrid();
		createCountGrid();
		}
	}
	
	Grid(int someRows, int someColumns, int someNumBombs){
		if(someNumBombs>=0&&someRows>=0&&someColumns>=0&&someNumBombs<someRows*someColumns) {
		numRows=someRows; 
		numColumns = someColumns;
		numBombs = someNumBombs;
		}
		createBombGrid();
		createCountGrid();
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public int getNumBombs() {
		return numBombs;
	}
	public boolean [][] getBombGrid(){
		//from Zybook, to returns a copy of the 2 dimensional boolean array.
		boolean [][] bombArr = new boolean[bombGrid.length][];
		
		for (int i = 0; i < bombGrid.length; ++i) {
		//looping through the bombtGrid array and copy from 0 to bombArr length,
            bombArr[i] = new boolean[bombGrid[i].length];
            System.arraycopy(bombGrid[i], 0, bombArr[i], 0, bombArr[i].length);
            //using Java built-in system array to copy.
       }
		return bombArr;
	}
		
	

	public int  [][] getCountGrid(){
		//From Zybook, to returns a copy of the 2 dimensional integer array.
		int [][] countArr = new int[countGrid.length][];
		
		for (int i = 0; i < countGrid.length; i++) {
		//looping through the countGrid array and copy from 0 to countGrid array length,
            countArr[i] = new int[countGrid[i].length];
            System.arraycopy(countGrid[i], 0, countArr[i], 0, countArr[i].length);
            //using Java built-in system array to copy.
       }
		return countArr;
	}
	public boolean isBombAtLocation(int someRow, int someColumn) {
		if(someRow>=0 && someRow < numRows&&someColumn>=0 && someColumn < numColumns) {
				if (bombGrid[someRow][someColumn]==true) {
						return true;
					}
		}
		return false;
		
	}
	public int getCountAtLocation(int someRow, int someColumn) {
		//get the number in the grid
		//count how many bomb nearby
		return countGrid[someRow][someColumn];
		
		
	}
	public void createBombGrid(){
		bombGrid = new boolean[numRows][numColumns];
		if(numBombs < numColumns*numRows) {
			for(int row = 0; row < numRows; row++) {
				for(int column = 0; column < numColumns; column++) {
					bombGrid[row][column] = false;
				}
			}
			Random random = new Random();
			int bombCount = numBombs;
			while(bombCount >0) {
				int row = random.nextInt(numRows);
				int column = random.nextInt(numColumns);
				if(!isBombAtLocation(row, column)) {
					bombGrid[row][column] = true;
					bombCount--;
				}
			}
		}
	}
	public void createCountGrid() {
		countGrid = new int[numRows][numColumns];
		//called by constructors to create and populate countGrid from bombGrid
				for(int row=0;row<numRows;row++){
					for(int column=0;column<numColumns;column++) {
						int count = 0;
					
					
				//check current location 
					if(isBombAtLocation(row,column)==true) {
						count++;
					}
				// check everything inside
					if(row-1>=0&&row+1<numRows&&column-1>=0&&column+1<numColumns) {
							
							
							if(isBombAtLocation(row, column-1)) {
								count++;
							}
							if(isBombAtLocation(row, column+1)) {
								count++;			
							}
							if(isBombAtLocation(row-1, column-1)) {
								count++;
							}
							if(isBombAtLocation(row-1, column)) {
								count++;
							}
							if(isBombAtLocation(row-1, column+1)) {
								count++;
							}
							if(isBombAtLocation(row+1, column-1)) {
								count++;
							}
							if(isBombAtLocation(row+1, column)) {
								count++;
							}
							if(isBombAtLocation(row+1, column+1)) {
								count++;
							}
					}
					
					// Checks if left side and not the left top and bottom corner
					if(column== 0&&row!=0&&row!=numRows-1 && numRows>1 && numColumns>1 ) {
						if(isBombAtLocation(row-1, column)) {
							count++;
						}
						if(isBombAtLocation(row-1, column+1)) {
							count++;
						}
						if(isBombAtLocation(row, column+1)) {
							count++;			
						}
						if(isBombAtLocation(row+1, column)) {
							count++;
						}
						if(isBombAtLocation(row+1, column+1)) {
							count++;
						}
					}
					// Checks if right side and not at the right top and bottom corner
					if(column==numColumns-1&&row!=0&&row!=numRows-1 && numRows>1 && numColumns>1) {
						if(isBombAtLocation(row-1, column-1)) {
							count++;
						}
						if(isBombAtLocation(row-1, column)) {
							count++;
						}
						if(isBombAtLocation(row, column-1)) {
							count++;
						}
						if(isBombAtLocation(row+1, column-1)) {
							count++;
						}
						if(isBombAtLocation(row+1, column)) {
							count++;
						}
					}
				
				//Check top and not at the top right corner or top left corner
				if(row == 0 && column!=0&&column!=numColumns-1   && numRows>1 && numColumns>1 ) {
				// Top Center
					if(isBombAtLocation(row, column-1)) {
						count++;			
					}
					if(isBombAtLocation(row, column+1)) {
						count++;
					}
					if(isBombAtLocation(row+1, column-1)) {
						count++;
					}
					if(isBombAtLocation(row+1, column)) {
						count++;			
					}
					if(isBombAtLocation(row+1, column+1)) {
						count++;
					}
				}
				// Check Bottom and not the bottom left corner or bottom corner
				if(row ==numRows-1&& column!=0&&column!=numColumns-1 && numColumns>1 ) {
					if(isBombAtLocation(row, column-1)) {
						count++;			
					}
					if(isBombAtLocation(row, column+1)) {
						count++;
					}
					if(isBombAtLocation(row-1, column-1)) {
						count++;
					}
					if(isBombAtLocation(row-1, column)) {
						count++;
					}
					if(isBombAtLocation(row-1, column+1)) {
						count++;
					}
				}
				// Top left Corner
				 if(column == 0 && row == 0 && numRows>1 && numColumns>1 ) {
					if(isBombAtLocation(row, column+1)) {
						count++;			
					}
					if(isBombAtLocation(row+1, column)) {
						count++;
					}
					if(isBombAtLocation(row+1, column+1)) {
						count++;
					}
				}
				//Top right corner
				 if(column == numColumns-1 && row == 0&& numRows>1 && numColumns>1) {
						if(isBombAtLocation(row, column-1)) {
							count++;			
						}
						if(isBombAtLocation(row+1, column)) {
							count++;
						}
						if(isBombAtLocation(row+1, column-1)) {
							count++;
						}
				}
				
				// Check Bottom Left Corner
				 if(column== 0 && row == numRows-1&& numRows>1 && numColumns>1) {
						if(isBombAtLocation(row, column+1)) {
							count++;			
						}
						if(isBombAtLocation(row-1, column)) {
							count++;
						}
						if(isBombAtLocation(row-1, column+1)) {
							count++;
						}
					}
				// Check Bottom Right Corner
				if(row==numRows-1&&column==numColumns-1&& numRows>1 && numColumns>1) {
						if(isBombAtLocation(row, column-1)) {
							count++;			
						}
						if(isBombAtLocation(row-1, column)) {
							count++;
						}
						if(isBombAtLocation(row-1, column-1)) {
							count++;
						}
				}
				if(numRows == 1 && column != 0 && column < numColumns-1) {
					if(isBombAtLocation(row, column+1)){
						count++;
					}
					if(isBombAtLocation(row, column-1)){
						count++;
					}
				}
				if(numColumns == 1 && row != 0 && row < numRows-1) {
					if(isBombAtLocation(row+1, column)){
						count++;
					}
					if(isBombAtLocation(row -1, column)){
						count++;
					}
				}
				countGrid[row][column] = count;
					
					
				
					
					}
				}
	}	
			
			



	
	}
	

	





