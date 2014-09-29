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
			
			//first read line by line and split by ,'s
			temp = scan.nextLine();
			for( int i = 0; i < numRows; i++ ) {
				String[] tempLine = temp.split(",");
				for( int j = 0; j < numColumns; j++ ) {
	
					//next check if this is a doorway in a room and handle
					if( tempLine[j].length() > 1 ) {
						tempDir = tempLine[j].charAt(1);
						layout[i][j] = new RoomCell(i, j, tempLine[j].charAt(0), tempDir);
					}
					
					//if it is a walkway/hallway...
					if( tempLine[j].equalsIgnoreCase("h")) {
						layout[i][j] = new WalkwayCell(i, j);
					}
					
					//if not these, it must be a room without a doorway (or closet)
					else {
						layout[i][j] = new RoomCell(i, j, tempLine[j].charAt(0));
					}
				}
				//read next line if it's allowed
				if( scan.hasNextLine() ) {
					temp = scan.nextLine();
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
