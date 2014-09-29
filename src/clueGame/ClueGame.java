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
	
	public void loadLegend() throws FileNotFoundException {
		//setup filereader and scanner
		FileReader reader = new FileReader(legendFile);
		Scanner scan = new Scanner(reader);
		String temp;
		scan.useDelimiter(",");
		//scan first char
		temp = scan.next();
		//loop to get the rest and add to rooms
		while( scan.hasNext() ) {
			rooms.put(temp.charAt(0), scan.next());
			temp = scan.next();
		}
		scan.close();
	}
	
	public void loadConfigFiles() throws BadConfigFormatException, FileNotFoundException {
		//first load legend
		loadLegend();
		//then load board layout
		board.loadBoardConfig(layoutFile);
	}
	public Board getBoard() {
		return board;
	}
}
