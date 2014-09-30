package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	private Board board = new Board();
	private String layoutFile = "ClueLayout.csv";
	private String legendFile = "ClueLegend.csv";
	private Map<Character,String> rooms = new HashMap<Character,String>();
	public ClueGame(String layout, String legend) {
		layoutFile = layout;
		legendFile = legend;
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
