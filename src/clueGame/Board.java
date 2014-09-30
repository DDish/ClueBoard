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
			boolean roomMade = false;
			//first read line by line and split by ,'s
			temp = scan.nextLine();
			if(temp.length() < (numColumns*2 - 1)) throw new BadConfigFormatException();
			for( int i = 0; i < numRows; i++ ) {
				String[] tempLine = temp.split(",");
				System.out.println("__________________row line __________________");
				for( int j = 0; j < numColumns; j++ ) {
					roomMade = false;
					//next check if this is a doorway in a room and handle
					if(!rooms.containsKey(tempLine[j].charAt(0))) throw new BadConfigFormatException();
					if( tempLine[j].length() > 1 ) {
						//System.out.println("Doorway: " + tempLine[j].charAt(0) + "|" + tempLine[j].charAt(1));
						tempDir = tempLine[j].charAt(1);
						layout[i][j] = new RoomCell(i, j, tempLine[j].charAt(0), tempDir);
						roomMade = true;
					}
					
					//if it is a walkway/hallway...
					if( tempLine[j].equalsIgnoreCase("h")) {
						//System.out.println("walkway");
						layout[i][j] = new WalkwayCell(i, j);
						roomMade = true;
					}
					
					//if not these, it must be a room without a doorway (or closet)
					if( !roomMade ) {
						//System.out.println("else: " + tempLine[j].charAt(0));
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
	
	public void loadLegend(String legendFile) throws FileNotFoundException, BadConfigFormatException {
		//setup filereader and scanner
		FileReader reader = new FileReader(legendFile);
		Scanner scan = new Scanner(reader);
		String temp = null;

		//loop through to add rooms
		while( scan.hasNextLine() ) {
			if( scan.hasNextLine() ) {
				temp = scan.nextLine();
			}
			if (!((temp.charAt(1))==',')) throw new BadConfigFormatException();
			String[] tempLine = temp.split(",");
			rooms.put(tempLine[0].charAt(0), tempLine[1]);
			//System.out.println("adding: " + tempLine[0].charAt(0) + " + " + tempLine[1]);
			//System.out.println("added: " + tempLine[0] + "|" + rooms.get(tempLine[0].charAt(0)));
			
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
