package clueGame;

public class RoomCell extends BoardCell {
	private enum DoorDirection {
		UP, DOWN, LEFT, RIGHT, NONE
	}
	private DoorDirection doorDirection;
	private char roomInitial;
	public boolean isRoom() {
		return true;
	}
	/*public void draw(){
	}*/
}
