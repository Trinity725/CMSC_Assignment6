public class Town implements Comparable<Town> 
{

	private String name;
	/**
	 * Constructor
	 * @param name the town name
	 */
	public Town(String name) {
		this.name = name;
	}
	/**
	 * Copy constructor
	 * @param templateTown an instance of Town
	 */
	public Town(Town templateTown) {
		name = templateTown.getName();
	}
	/**
	 * Compare to method
	 * @param o town to compare to
	 * @return 0 if names are equal, a positive or negative number if the names are not equal
	 */
	@Override
	public int compareTo(Town o) {
		if (getName().equals(o.getName()))
			return 0;
		return 1;
	}
	/**
	 * equals method
	 * @return true if the town names are equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Town)
			if (getName().equals(((Town) obj).getName()))
				return true;
		return false;
	}
	/**
	 * Returns the town's name
	 * @return town's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * hash code method
	 * @return the hashcode for the name of the town
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	/**
	 * To string method
	 * @return the town name
	 */
	@Override
	public String toString() {
		return getName();
	}
}
