package clueGame;

import java.io.FileNotFoundException;
import java.util.LinkedList;

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
		
		testBoard.calcAdjacencies();
		
		testBoard.calcTargets(testBoard.getBoardCell(10, 23), 3);
		
	}

}
