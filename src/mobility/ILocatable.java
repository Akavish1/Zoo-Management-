package mobility;

/**
 * 
 * @author or
 *
 */
public interface ILocatable {

	/**
	 * @return The current location
	 */
	public Point getLocation();

	/**
	 * 
	 * @param location
	 *            - The new location
	 * @return True if assignment is successful
	 */
	public boolean setLocation(Point location);
}
