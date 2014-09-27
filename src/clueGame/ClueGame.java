package clueGame;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ClueGame {
	private Board board = new Board();
	private String layoutFile;
	private String legendFile;
	private Map<Character,String> rooms = new HashMap<Character,String>();
	public ClueGame(String layout, String legend) {
		layoutFile = layout;
		legendFile = legend;
	}
	public void loadConfigFiles() throws BadConfigFormatException, FileNotFoundException {
	}
	public Board getBoard() {
		return board;
	}
}
