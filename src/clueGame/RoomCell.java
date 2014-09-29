package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection {
		UP, DOWN, LEFT, RIGHT, NONE
	}
	private DoorDirection doorDirection;
	private char roomInitial;
	//constructor for rooms with door
	public RoomCell(int R, int C, Character roomInit, Character dir) {
		super(R, C);
		roomInitial = roomInit;
		
		//handle door direction
		if(dir.equals('U')) {
			doorDirection = DoorDirection.UP;
		}
		
		if(dir.equals('D')) {
			doorDirection = DoorDirection.DOWN;
		}
		
		if(dir.equals('R')) {
			doorDirection = DoorDirection.RIGHT;
		}
		
		if(dir.equals('L')) {
			doorDirection = DoorDirection.LEFT;
		}

	}
	
	public RoomCell(int R, int C, Character roomInit) {
		super(R, C);
		roomInitial = roomInit;
		doorDirection = DoorDirection.NONE;
	}
	
	public boolean isRoom() {
		return true;
	}
	
	public char getInitial() {
		return roomInitial;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	/*public void draw() {
	}*/
}
