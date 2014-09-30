package clueGame;

import java.io.FileNotFoundException;

public class main {

	/**
	 * @param args
	 * @throws BadConfigFormatException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, BadConfigFormatException {
		///*
		ClueGame game = new ClueGame("ClueLayout.csv","ClueLegend.csv");
		Board testBoard = game.getBoard();
		try {
		game.loadConfigFiles();
		} catch (BadConfigFormatException e) {
			throw new BadConfigFormatException();
		}
		if(testBoard.getRoomCell(12, 6).isDoorway()) System.out.println("YEAH");
		//System.out.println(testBoard.getRoomCell(12, 6).getDoorDirection()); //check size of getRooms... returns 0 right now :(
		//*/
	}

}
