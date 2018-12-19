package mobility;

/**
 * Represents a mobile object
 * 
 * @author or
 *
 */
public abstract class Mobile implements ILocatable {

	private Point location;
	private double totalDistance;

	public Mobile(Point location) {
		this.setLocation(location);
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public boolean setLocation(Point newLocation) {
		this.location = newLocation;
		return true;
        }
}
