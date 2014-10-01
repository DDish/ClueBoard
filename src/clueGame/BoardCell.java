package clueGame;

public abstract class BoardCell {
	private int row;
	private int column;
	
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + "]";
	}
	
	public BoardCell(int R, int C) {
		row = R;
		column = C;
		
	}
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}
	
	/*public void draw() {
	}*/
}
