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
		try {
			board = new Board(layoutFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadConfigFiles() throws FileNotFoundException, BadConfigFormatException {
		board.loadLegend(legendFile);
		//then load board layout
		board.loadBoardDimensions(layoutFile);
		board.loadBoardConfig(layoutFile);
		
	}
	
	public Board getBoard() {
		return board;
	}
}
