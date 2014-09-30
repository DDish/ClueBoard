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
		String temp = null;

		//loop through to add rooms
		while( scan.hasNextLine() ) {
			if( scan.hasNextLine() ) {
				temp = scan.nextLine();
			}
			String[] tempLine = temp.split(",");
			rooms.put(tempLine[0].charAt(0), tempLine[1]);
			System.out.println("adding: " + tempLine[0].charAt(0) + " + " + tempLine[1]);
			
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
