package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	private Board board;
	private int boardRows;
	private int boardCols;
	private String layoutFile = "ClueLayout.csv";
	private String legendFile = "ClueLegend.csv";
	private Map<Character,String> rooms = new HashMap<Character,String>();
	public ClueGame(String layout, String legend) {
		layoutFile = layout;
		legendFile = legend;
		board = new Board();
	}
	public ClueGame(String layout, String legend, int boardRow, int boardCol) {
		layoutFile = layout;
		legendFile = legend;
		boardRows = boardRow;
		boardCols = boardCol;
		board = new Board(boardRows, boardCols);
	}
	
	public void loadConfigFiles() throws BadConfigFormatException, FileNotFoundException {
		try {
		board.loadLegend(legendFile);
		//then load board layout
		board.loadBoardConfig(layoutFile);
		} catch (BadConfigFormatException e) {
			throw new BadConfigFormatException();
		}
	}
	public Board getBoard() {
		return board;
	}
}
