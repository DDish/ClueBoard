package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private BoardCell[][] layout;
	private Map<Character,String> rooms = new HashMap<Character,String>();
	private int numRows;
	private int numColumns;
	public Board() {
		numRows = 25;
		numColumns = 25;
		layout = new BoardCell[numRows][numColumns];
	}
	
	public void loadBoardConfig(String layoutFile) throws BadConfigFormatException, FileNotFoundException {
			//load board layout
			FileReader reader = new FileReader(layoutFile);
			Scanner scan = new Scanner(reader);
			String temp;
			Character tempDir;
			scan.useDelimiter(",");
			
			//first get each token from layout config
			for( int i = 0; i < numRows; i++ ) {
				for( int j = 0; j < numColumns; j++ ) {
					temp = scan.next();
					
					//next check if this is a doorway in a room and handle
					if( temp.length() > 1 ) {
						tempDir = temp.charAt(1);
						layout[i][j] = new RoomCell(i, j, temp.charAt(0), tempDir);
					}
					
					//if it is a walkway/hallway...
					if( temp.equalsIgnoreCase("h")) {
						layout[i][j] = new WalkwayCell(i, j);
					}
					
					//if not these, it must be a room without a doorway (or closet)
					else {
						layout[i][j] = new RoomCell(i, j, temp.charAt(0));
					}
				}
			}
			scan.close();
	}
	
	public BoardCell getBoardCell(int row, int col) {
		return layout[row][col];
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public Map<Character,String> getRooms() {
		return rooms;
	}
	
	public RoomCell getRoomCell(int row, int col) {
		return (RoomCell) layout[row][col];
	}
}
