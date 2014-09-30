package clueTests;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;
import clueGame.RoomCell;

public class Tests {
	private static Board board;
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLS = 25;
	
	@Before public void setup() {
		ClueGame game = new ClueGame("ClueLayout.csv","ClueLegend.csv");
		try {
			game.loadConfigFiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board = game.getBoard();
	}
	
	// Tests that each room is read correctly. 
	@Test public void testRooms() {
		Map<Character,String> rooms = board.getRooms();
		assertEquals(NUM_ROOMS, rooms.size());
		assertEquals("Keg Room", rooms.get('K'));
		assertEquals("Bedroom", rooms.get('B'));
		assertEquals("Kitchen", rooms.get('N'));
		assertEquals("Family Room", rooms.get('F'));
		assertEquals("Cellar", rooms.get('C'));
		assertEquals("Bar Room", rooms.get('R'));
		assertEquals("Dining Room", rooms.get('D'));
		assertEquals("Keg Room", rooms.get('K'));
		assertEquals("Garage", rooms.get('G'));
		assertEquals("Library", rooms.get('L'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Hallway", rooms.get('H'));	
	}
	
	// Tests that the board is of the correct size.
	@Test public void testBoardDimensions() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLS, board.getNumColumns());
	}
	
	// Tests that doorways are recognized uniquely and that doors point in the correct direction.
	@Test  public void testDoorDirections(){
		// Tests right-facing door.
		RoomCell room = board.getRoomCell(1,4);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		// Tests left-facing door.
		room = board.getRoomCell(5,7);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		// Tests up-facing door.
		room = board.getRoomCell(6,19);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		// Tests down-facing door.
		room = board.getRoomCell(22,1);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		// Tests walkway.
		room = board.getRoomCell(22,1);
		assertFalse(room.isWalkway());
		// Tests room cell that is not a doorway. 
		room = board.getRoomCell(0,0);
		assertFalse(room.isDoorway());
	}
	
	// Tests number of doors.
	@Test public void testNumberOfDoorways() {
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		Assert.assertEquals(625, totalCells);
		for (int r = 0; r < board.getNumRows(); r++) {
			for (int c = 0; c < board.getNumColumns(); c++) {
				BoardCell cell = board.getBoardCell(r,c);
				if (cell.isDoorway()) {
					numDoors++;
				}
			}
		}
		Assert.assertEquals(27, numDoors);
	}
	
	// Tests room cell initials.
	@Test public void testRoomInitials() {
		assertEquals('G', board.getRoomCell(2,2).getInitial());
		// Hallway
		//assertEquals('H', board.getRoomCell(5,5).getInitial()); don't need to test hallway since this is not a roomcell but a walkwaycell
		assertEquals('N', board.getRoomCell(5,10).getInitial());
		assertEquals('R', board.getRoomCell(0,20).getInitial());
		assertEquals('D', board.getRoomCell(8,20).getInitial());
		assertEquals('X', board.getRoomCell(11,11).getInitial());
		assertEquals('B', board.getRoomCell(12,3).getInitial());
		assertEquals('K', board.getRoomCell(20,1).getInitial());
		assertEquals('F', board.getRoomCell(22,11).getInitial());
		assertEquals('L', board.getRoomCell(22,22).getInitial());
		// Cell at Edge of Domain
		assertEquals('C', board.getRoomCell(14,17).getInitial());
		// Doorway cell.
		assertEquals('B', board.getRoomCell(12,6).getInitial());
	}
	
	// Tests for exception if layout has wrong dimensions. 
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		ClueGame game = new ClueGame("ClueLayoutBadColumns.csv", "ClueLegend.csv");
		game.loadConfigFiles();
	}
	
	// Tests for exception if layout has unrecognizable room.
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		ClueGame game = new ClueGame("ClueLayoutBadRoom.txt","ClueLegend.csv");
		game.loadConfigFiles();
	}
	
	// Tests for exception if legend file is unusable.
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		ClueGame game = new ClueGame("ClueLayout.csv","ClueLegendBadFormat.txt");
		game.loadConfigFiles();
	}
	
	// Tests adjacency for location with only adjacent walkways.
	@Test
	public void testAdjacency8_6() { 
		BoardCell cell = board.getBoardCell(8,6);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getBoardCell(7, 6)));
		Assert.assertTrue(testList.contains(board.getBoardCell(9, 6)));
		Assert.assertTrue(testList.contains(board.getBoardCell(8, 5)));
		Assert.assertTrue(testList.contains(board.getBoardCell(8, 7)));
		Assert.assertEquals(4, testList.size());
	}
	
	// Tests adjacency for cell at upper edge.
	@Test
	public void testAdjacency0_11() { 
		BoardCell cell = board.getBoardCell(0,11);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getBoardCell(0, 10)));
		Assert.assertTrue(testList.contains(board.getBoardCell(0, 12)));
		Assert.assertTrue(testList.contains(board.getBoardCell(1, 11)));
		Assert.assertEquals(3, testList.size());
	}
	
	// Tests adjacency for cell at right edge.
		@Test
		public void testAdjacency11_24() { 
			BoardCell cell = board.getBoardCell(11,24);
			LinkedList<BoardCell> testList = board.getAdjList(cell);
			Assert.assertTrue(testList.contains(board.getBoardCell(11, 23)));
			Assert.assertTrue(testList.contains(board.getBoardCell(12, 24)));
			Assert.assertEquals(2, testList.size());
		}
		
		// Tests adjacency for cell at lower edge.
		@Test
		public void testAdjacency24_1() { 
			BoardCell cell = board.getBoardCell(24,1);
			LinkedList<BoardCell> testList = board.getAdjList(cell);
			Assert.assertTrue(testList.contains(board.getBoardCell(24, 0)));
			Assert.assertTrue(testList.contains(board.getBoardCell(24, 2)));
			Assert.assertTrue(testList.contains(board.getBoardCell(23, 1)));
			Assert.assertEquals(3, testList.size());
		}
		// Tests adjacency for cell at left edge.
		@Test
		public void testAdjacency8_0() { 
			BoardCell cell = board.getBoardCell(8,0);
			LinkedList<BoardCell> testList = board.getAdjList(cell);
			Assert.assertTrue(testList.contains(board.getBoardCell(8, 1)));
			Assert.assertTrue(testList.contains(board.getBoardCell(9, 0)));
			Assert.assertEquals(2, testList.size());
		}
		
		// Tests adjacency for cell sandwiched between non-doorway room cells.
		@Test
		public void testAdjacency16_0() { 
			BoardCell cell = board.getBoardCell(16,0);
			LinkedList<BoardCell> testList = board.getAdjList(cell);
			Assert.assertTrue(testList.contains(board.getBoardCell(16, 1)));
			Assert.assertEquals(1, testList.size());
		}
		
		// Tests adjacency for cell next to non-doorway room cell.
				@Test
				public void testAdjacency8_17() { 
					BoardCell cell = board.getBoardCell(8,17);
					LinkedList<BoardCell> testList = board.getAdjList(cell);
					Assert.assertTrue(testList.contains(board.getBoardCell(8, 16)));
					Assert.assertTrue(testList.contains(board.getBoardCell(7, 17)));
					Assert.assertEquals(2, testList.size());
				}
				
		// Tests adjacency for cell next up-facing doorway.
				@Test
				public void testAdjacency1_14() { 
					BoardCell cell = board.getBoardCell(1,14);
					LinkedList<BoardCell> testList = board.getAdjList(cell);
					Assert.assertTrue(testList.contains(board.getBoardCell(1, 15)));
					Assert.assertTrue(testList.contains(board.getBoardCell(1, 13)));
					Assert.assertTrue(testList.contains(board.getBoardCell(2, 14)));
					Assert.assertTrue(testList.contains(board.getBoardCell(0, 14)));
					Assert.assertEquals(4, testList.size());
				}
				
		// Tests adjacency for cell next right-facing doorway.
				@Test
				public void testAdjacency1_5() { 
					BoardCell cell = board.getBoardCell(1,5);
					LinkedList<BoardCell> testList = board.getAdjList(cell);
					Assert.assertTrue(testList.contains(board.getBoardCell(0, 5)));
					Assert.assertTrue(testList.contains(board.getBoardCell(2, 5)));
					Assert.assertTrue(testList.contains(board.getBoardCell(1, 6)));
					Assert.assertTrue(testList.contains(board.getBoardCell(1, 4)));
					Assert.assertEquals(4, testList.size());
				}
				
		// Tests adjacency for cell next down-facing doorway.
				@Test
				public void testAdjacency11_23() { 
					BoardCell cell = board.getBoardCell(11,23);
					LinkedList<BoardCell> testList = board.getAdjList(cell);
					Assert.assertTrue(testList.contains(board.getBoardCell(10, 23)));
					Assert.assertTrue(testList.contains(board.getBoardCell(12, 23)));
					Assert.assertTrue(testList.contains(board.getBoardCell(11, 22)));
					Assert.assertTrue(testList.contains(board.getBoardCell(11, 24)));
					Assert.assertEquals(4, testList.size());
				}
		
		// Tests adjacency for cell next left-facing doorway.
				@Test
				public void testAdjacency16_22() { 
					BoardCell cell = board.getBoardCell(16,22);
					LinkedList<BoardCell> testList = board.getAdjList(cell);
					Assert.assertTrue(testList.contains(board.getBoardCell(16, 23)));
					Assert.assertTrue(testList.contains(board.getBoardCell(16, 21)));
					Assert.assertTrue(testList.contains(board.getBoardCell(17, 22)));
					Assert.assertEquals(3, testList.size());
				}
}

