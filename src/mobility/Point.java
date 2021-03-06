package mobility;

/**
 * Represents a point
 * 
 *
 */
public class Point {
	private int x; 
        private int y; 
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
        
	public int getX() {
		return x;
	}
        
	public int getY() {
		return y;
	}
        
	public boolean setX(int x) {
		this.x = x;
		return true;
	}
        
	public boolean setY(int y) {
		this.y = y;
		return true;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
