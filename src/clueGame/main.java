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
		game.loadConfigFiles();
		if(testBoard.getRoomCell(12, 6).isDoorway()) System.out.println("YEAH"); //doorways not adding properly
		System.out.println(testBoard.getRooms().size()); //check size of getRooms... returns 0 right now :(
		//*/
	}

}
