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

		//scan first line
		temp = scan.nextLine();	
		//loop to get the rest and add to rooms
		while( scan.hasNextLine() ) {
			String[] tempLine = temp.split(",");
			rooms.put(tempLine[0].charAt(0), tempLine[1]);
			if( scan.hasNextLine() ) {
				temp = scan.nextLine();
			}
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
