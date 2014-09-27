package clueGame;

import java.util.HashMap;
import java.util.Map;

public class Board {
	private BoardCell[][] layouts;
	private Map<Character,String> rooms = new HashMap<Character,String>();
	private int numRows;
	private int numColumns;
	public void loadBoardConfig() {
		
	}
	public BoardCell getBoardCell(int row, int col) {
		return layouts[row][col];
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
}
