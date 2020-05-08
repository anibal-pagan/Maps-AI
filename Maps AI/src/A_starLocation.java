
public class A_starLocation {
	private Location location;
	private Location previous;
	
	public A_starLocation(Location current, Location previous) {
		setLocation(current);
		setPrevious(previous);
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void setPrevious(Location previous) {
		this.previous = previous;
	}

	public Location getLocation() {
		return location;
	}

	public Location getPrevious() {
		return previous;
	}
}
