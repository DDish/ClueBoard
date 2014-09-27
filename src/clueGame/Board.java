package clueGame;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Board {
	private BoardCell[][] layout;
	private Map<Character,String> rooms = new HashMap<Character,String>();
	private int numRows;
	private int numColumns;
	Board() {
		numRows = 25;
		numColumns = 25;
	}
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {
	
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
